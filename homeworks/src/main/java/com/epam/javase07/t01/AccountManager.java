package com.epam.javase07.t01;




import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


/**
 * В текстовом (или xml) файле содержится информация о переводах средств со счета на
 * счет. Создайте приложение, позволяющее в параллельном режиме обработать эту информацию
 * (счета в приложении представляются собой объекты). Синхронизируйте код приложения
 * используя ключевое слово synchronized (1 вариант) и библиотеку java.util.concurrent (2 вариант).
 */
public abstract class AccountManager {


    protected void doUnsafeTransfer(Account from, Account to, double money) throws InsufficientFundsException {
        //    doSomething();
        from.withdraw(money);
        // doSomething();
        to.deposit(money);
        //  doSomething();
    }

    protected void doSomething() {
        Random rnd = new Random();
        if (rnd.nextBoolean()) {
            try {
                Thread.sleep(rnd.nextInt(50));
            } catch (InterruptedException e) {

            }
        } else
            Thread.yield();
    }

    abstract void doMoneyTransfer(Account from, Account to, double money) throws InsufficientFundsException;

    public static AccountManager getNotSynchronizedManager(){return new NotSynchronizedManager();}
    public static AccountManager getSynchronizedManager(){return new SynchronizedManager();}
    public static AccountManager getSynchronizedWithLockManager(){return new SynchronizedWithLockManager();}

    private static class NotSynchronizedManager extends AccountManager {
        @Override
        void doMoneyTransfer(Account from, Account to, double money) throws InsufficientFundsException {


            doUnsafeTransfer(from, to, money);

        }

    }
    private static class SynchronizedManager extends AccountManager {
        @Override
        void doMoneyTransfer(Account from, Account to, double money) throws InsufficientFundsException {
            if (from.getAccountId() < to.getAccountId()) {
                synchronized (from) {
                    synchronized (to) {
                        doUnsafeTransfer(from, to, money);
                    }
                }
            } else {
                synchronized (to) {
                    synchronized (from) {
                        doUnsafeTransfer(from, to, money);
                    }
                }
            }
        }
    }
    private static class SynchronizedWithLockManager extends AccountManager {
        @Override
        void doMoneyTransfer(Account from, Account to, double money) throws InsufficientFundsException {

            if(from.getAccountId()<to.getAccountId())
            {
                from.getAccountLock().lock();
                to.getAccountLock().lock();
            }
            else{
                to.getAccountLock().lock();
                from.getAccountLock().lock();
            }

            try {
                from.withdraw(money);
                to.deposit(money);
            }
            finally {
                from.getAccountLock().unlock();
                to.getAccountLock().unlock();
            }
        }
    }





    public static void createTransfersInfoXml(List<Account> accounts, List<TransferInfo> transfers, String fileSavePath) throws IOException {
        Element root = new Element("transfers-info");

        Element accountsElement = new Element("accounts");
        for (Account account : accounts) {
            accountsElement.addContent(createAccountElement(account));
        }
        Element transfersElement = new Element("transfers");
        for (TransferInfo transfer : transfers) {
            transfersElement.addContent(createTransferElement(transfer));
        }

        root.addContent(accountsElement).addContent(transfersElement);

        Document document=new Document(root);
        XMLOutputter out=new XMLOutputter(Format.getPrettyFormat());

        out.output(document,new FileOutputStream(fileSavePath));
    }

    public static void loadTransfersInfoFromFile(List<Account> accounts,List<TransferInfo> transfers,String fileName) throws JDOMException, IOException {
        Document document=new SAXBuilder().build(fileName);
        Element rootElement=document.getRootElement();

        List<Element> accountElements=rootElement.getChild("accounts").getChildren("account");

        HashMap<Integer,Account> loadedAccounts=new HashMap();

        for (Element accountElement:accountElements) {
            Account loadedAccount= createAccountFromElement(accountElement);
            loadedAccounts.put(loadedAccount.getAccountId(),loadedAccount);
        }

        List<Element> transfersElements=rootElement.getChild("transfers").getChildren("transfer");

        for(Element transferElement:transfersElements){
            TransferInfo loadedTransfer= createTransferInfoFromElement(transferElement,loadedAccounts);
            if(loadedTransfer!=null)
            {
                transfers.add(loadedTransfer);
            }
        }
        accounts.addAll(loadedAccounts.values());
    }

    private static Element createAccountElement(Account account)
    {
        Element accountElement=new Element("account");
        accountElement.setAttribute("id",String.valueOf(account.getAccountId()));
        accountElement.setAttribute("moneyAmount",String.valueOf(account.getDepositMoney()));
        return accountElement;
    }

    private static Element createTransferElement(TransferInfo transfer)
    {
        Element transferElement=new Element("transfer");

        Element fromElement=new Element("from");
        fromElement.addContent(String.valueOf(transfer.from.getAccountId()));

        Element toElement=new Element("to");
        toElement.addContent(String.valueOf(transfer.to.getAccountId()));

        Element moneyElement=new Element("money");
        moneyElement.addContent(String.valueOf(transfer.money));

        transferElement.addContent(fromElement).addContent(toElement).addContent(moneyElement);

        return transferElement;
    }

    private static Account createAccountFromElement(Element accountElement)
    {
        int accountID=Integer.parseInt(accountElement.getAttributeValue("id"));
        double accountMoney=Double.parseDouble(accountElement.getAttributeValue("moneyAmount"));
        return new Account(accountID,accountMoney);
    }

    private static TransferInfo createTransferInfoFromElement(Element transferElement, HashMap<Integer,Account> accountsMap)
    {
            int fromAccountId= Integer.parseInt(transferElement.getChild("from").getText());
            Account fromAccount=accountsMap.get(fromAccountId);

            int toAccountId= Integer.parseInt(transferElement.getChild("to").getText());
            Account toAccount=accountsMap.get(toAccountId);

            double money= Double.parseDouble(transferElement.getChild("money").getText());

        if (fromAccount!=null&& toAccount!=null)
            return new TransferInfo(fromAccount,toAccount,money);
        else
            return null;

    }




}



