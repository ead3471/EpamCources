package com.epam.javase07.t01;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Freemind on 2016-09-29.
 * В текстовом (или xml) файле содержится информация о переводах средств со счета на
 * счет. Создайте приложение, позволяющее в параллельном режиме обработать эту информацию
 * (счета в приложении представляются собой объекты). Синхронизируйте код приложения
 * используя ключевое слово synchronized (1 вариант) и библиотеку java.util.concurrent (2 вариант).
 */
public abstract class AccountManager {

    public List<List<TransferInfo>> readAccountsTransferInfo(String accountsInfo) throws ParserConfigurationException, IOException, SAXException {

        Document xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(accountsInfo));
        NodeList accounts=xmlDocument.getElementsByTagName("account");

        HashMap<Integer,Account> loadedAccounts=new HashMap<>();
        for(int i=0;i<accounts.getLength();i++)
        {
            Node currentNode=accounts.item(i);
            if(currentNode.getNodeType()==Node.ELEMENT_NODE){
                int accountId= Integer.parseInt(((Element)(accounts.item(i))).getAttribute("id"));
                double moneyAmount=Double.parseDouble(((Element)(accounts.item(i))).getAttribute("moneyAmount"));
                loadedAccounts.put(accountId,new Account(accountId,moneyAmount));
            }
        }

        NodeList operators=xmlDocument.getElementsByTagName("operator");

        List<List<TransferInfo>> moneyTransferOperatorsTasks=new ArrayList<>();

        for(int operatorsCount=0;operatorsCount<operators.getLength();operatorsCount++) {


            ArrayList<TransferInfo> currentOperatorTasks=new ArrayList<>();
            NodeList transfers=((Element)(operators.item(operatorsCount))).getElementsByTagName("transfer");
            for(int i=0;i<transfers.getLength();i++)
            {
                Node currentNode=transfers.item(i);
                if(currentNode.getNodeType()==Node.ELEMENT_NODE&& currentNode.getNodeName()=="transfer") {

                    int fromAccountId= Integer.parseInt((((Element)(currentNode)).getElementsByTagName("from")).item(0).getTextContent());
                    int toAccountId= Integer.parseInt((((Element)(currentNode)).getElementsByTagName("to")).item(0).getTextContent());
                    double moneyForTransfer= Double.parseDouble((((Element)(currentNode)).getElementsByTagName("money")).item(0).getTextContent());

                    if(loadedAccounts.containsKey(fromAccountId)&&loadedAccounts.containsKey(toAccountId))
                    {
                        currentOperatorTasks.add(new TransferInfo(loadedAccounts.get(fromAccountId),loadedAccounts.get(toAccountId),moneyForTransfer));
                    }
                }
            }
            System.out.println("operator "+operatorsCount+":"+"\n       "+currentOperatorTasks);
            moneyTransferOperatorsTasks.add(currentOperatorTasks);
        }


        return moneyTransferOperatorsTasks;
    }


    abstract void doMoneyTransfer(Account from, Account to, double money) throws InsufficientFundsException;

    public static class NotSynchronizedManager extends AccountManager {
        @Override
        void doMoneyTransfer(Account from, Account to, double money) throws InsufficientFundsException {


            doUnsafeTransfer(from, to, money);

            }

        }

    public static class SynchronizedManager extends AccountManager {
        @Override
        void doMoneyTransfer(Account from, Account to, double money) throws InsufficientFundsException{
            if(from.getAccountId()<to.getAccountId()){
                synchronized (from)
                {
                    synchronized (to)
                    {
                        doUnsafeTransfer(from, to, money);
                    }
                }
            }
            else{
                synchronized (to)
                {
                    synchronized (from)
                    {
                        doUnsafeTransfer(from, to, money);
                    }
                }
            }
        }
    }

    public static class SynchronizedWithLockManager extends AccountManager {
        @Override
        void doMoneyTransfer(Account from, Account to, double money) throws InsufficientFundsException {
            boolean transferIsDone = false;

          //  System.out.println("start process transfer:" + from.getAccountId() + "->" + to.getAccountId() + " " + money);
            while (!transferIsDone) {
                if (from.getAccountLock().tryLock()) {
                    try {
                        if (to.getAccountLock().tryLock()) {
                            transferIsDone=true;
                            doUnsafeTransfer(from, to, money);
                        }
                    }
                    finally {
                        synchronized (this)
                        {
                            from.getAccountLock().unlock();
                            to.getAccountLock().unlock();

                        }


                    }
                }

                }
            }
        }
//                try {
//                    if (from.getAccountLock().tryLock() && to.getAccountLock().tryLock()) {
//                        System.out.println("take all lock for " + from.getAccountId() + "->" + to.getAccountId() + " "+money+" thread" + Thread.currentThread().getName());
//                        doUnsafeTransfer(from, to, money);
//                        System.out.println("transfer " + from.getAccountId() + "->" + to.getAccountId() +" "+money+ "  is done");
//
//
//                        //    System.out.println("transfer " + from.getAccountId() + "->" + to.getAccountId() + ex);
//                    }
//                }
//
//                    finally{
//                          System.out.println("unlock From:"+from.getAccountId()+ " transfer:"+from.getAccountId() + "->" + to.getAccountId()+" "+money);
//                        from.getAccountLock().unlock();
//                        System.out.println("unlock to:"+to.getAccountId()+from.getAccountId()+ " transfer:"+from.getAccountId() + "->" + to.getAccountId()+" "+money);
//                        to.getAccountLock().unlock();
//                        // System.out.println("here2");
//                        transferIsDone = true;
//                        //  from.getAccountLock().notifyAll();
//                        // to.getAccountLock().notifyAll();
//
//                    }
//
//
//            }
//
//            System.out.println("exit while for transaction:"+from.getAccountId()+"->"+to.getAccountId()+" "+money);

       // }
    //}

    protected void doUnsafeTransfer(Account from, Account to, double money) throws InsufficientFundsException {
    //    doSomething();
        from.withdraw(money);
  //      doSomething();
        to.deposit(money);
     //  doSomething();
    }


    protected void doSomething()
    {
        Random rnd=new Random();
        if(rnd.nextBoolean()){
            try {
                Thread.sleep(rnd.nextInt(50));
            } catch (InterruptedException e) {

            }
        }
        else
            Thread.yield();

    }

}



