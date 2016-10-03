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
import java.util.HashSet;
import java.util.List;

/**
 * Created by Freemind on 2016-09-29.
 * В текстовом (или xml) файле содержится информация о переводах средств со счета на
 * счет. Создайте приложение, позволяющее в параллельном режиме обработать эту информацию
 * (счета в приложении представляются собой объекты). Синхронизируйте код приложения
 * используя ключевое слово synchronized (1 вариант) и библиотеку java.util.concurrent (2 вариант).
 */
public class AccountManager {


    public List<List<MoneyTransferInfo>> readAccountsTransferInfo(String accountsInfo) throws ParserConfigurationException, IOException, SAXException {

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

        List<List<MoneyTransferInfo>> moneyTransferOperatorsTasks=new ArrayList<>();

        for(int operatorsCount=0;operatorsCount<operators.getLength();operatorsCount++) {


            ArrayList<MoneyTransferInfo> currentOperatorTasks=new ArrayList<>();
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
                        currentOperatorTasks.add(new MoneyTransferInfo(loadedAccounts.get(fromAccountId),loadedAccounts.get(toAccountId),moneyForTransfer));
                    }
                }
            }
            System.out.println("operator "+operatorsCount+":"+"\n       "+currentOperatorTasks);
            moneyTransferOperatorsTasks.add(currentOperatorTasks);
        }


        return moneyTransferOperatorsTasks;
    }

    public void doTransferWithSynchronized(Account from,Account to, double money) throws InsufficientFundsException {
      if(from.getAccountId()<to.getAccountId()){
           synchronized (from)
           {
               synchronized (to)
               {
                    from.withdraw(money);
                    to.deposit(money);
               }
           }
       }
       else{
           synchronized (to)
           {
               synchronized (from)
               {
                   from.withdraw(money);
                   to.deposit(money);
               }
           }
       }
    }

    public void doTransferWithoutSynchronization(Account from, Account to, double money) throws InsufficientFundsException {

        from.withdraw(money);
        to.deposit(money);

    }



    public void doTransferWithLock(Account from,Account to,double money) throws InsufficientFundsException{



    }

}
class Account
{
    private final int accountId;
    private double depositMoney =0;

    public Account(int accountId,double startingMoney) {
        this.depositMoney = startingMoney;
        this.accountId=accountId;
    }

    public void deposit(double money)
    {
        depositMoney +=money;
    }

    public void withdraw(double money) throws InsufficientFundsException {
        if(depositMoney <money)
            throw new InsufficientFundsException();
        depositMoney -=money;

    }

    public double getDepositMoney() {
        return depositMoney;
    }

    public int getAccountId() {
        return accountId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", depositMoney=" + depositMoney +
                '}';
    }
}

class MoneyTransferInfo {

    public MoneyTransferInfo(Account from, Account to, double money) {
        this.from = from;
        this.to = to;
        this.money = money;
    }

    Account from;
    Account to;
    double money;

    @Override
    public String toString() {
        return "("+from.getAccountId() +
                "->" + to.getAccountId() +
                " money=" + money+")";
    }
}
