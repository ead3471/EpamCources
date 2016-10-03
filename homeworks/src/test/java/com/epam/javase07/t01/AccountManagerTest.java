package com.epam.javase07.t01;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

/**
 * Created by Freemind on 2016-10-02.
 */
public class AccountManagerTest {

    @Test
    public void testLoadTransfers() throws IOException, SAXException, ParserConfigurationException, InterruptedException {
        AccountManager manager=new AccountManager();
        //manager.readAccountsTransferInfo("src/test/resources/Transfers.xml");

        List<Account> generatedAccounts=generateAccounts();

        double sumMoneyBeforeOperations= getSumMoney(generatedAccounts);

        System.out.println(generatedAccounts);
        List<List<MoneyTransferInfo>> moneyTransferInfos=generateTransactions(generatedAccounts);
        System.out.println("Transactions count:"+moneyTransferInfos.size());

        CountDownLatch latch=new CountDownLatch(moneyTransferInfos.size());
        for(int i=0;i<moneyTransferInfos.size();i++)
        {
            List<MoneyTransferInfo> transferBlock=moneyTransferInfos.get(i);
            Thread t=new Thread(new Runnable() {
                @Override
                public void run() {

                    Random rnd=new Random();
                    for(MoneyTransferInfo transfer:transferBlock)
                    {
                        try {
                            manager.doTransferWithSynchronized(transfer.from,transfer.to,transfer.money);
                           // manager.doTransferWithoutSynchronization(transfer.from,transfer.to,transfer.money);
                        } catch (InsufficientFundsException e) {
                            //System.out.println("Transaction "+transfer +"faied!");
                        }
                        /*try {
                            Thread.sleep(rnd.nextInt(100));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                    }
                    latch.countDown();
                }
            });
            t.start();
        }

        latch.await();

        System.out.println("All complete!!!!");
        for(Account account:generatedAccounts)
        {
            assertTrue(account.getDepositMoney()>0);
            System.out.println(account);
        }

        double sumMoneyAfterOperations= getSumMoney(generatedAccounts);

        System.out.println(sumMoneyBeforeOperations-sumMoneyAfterOperations);
        assertTrue(Math.abs(sumMoneyBeforeOperations-sumMoneyAfterOperations)<0.01);
    }


    private double getSumMoney(List<Account> accounts)
    {
        double summMoney=0;

        for(Account account:accounts)
        {
            summMoney+=account.getDepositMoney();
        }
        return summMoney;
    }


    private List<Account> generateAccounts()
    {
        Random rnd=new Random();
        int numberOfAccounts=rnd.nextInt(1)+2;
        ArrayList<Account> accounts = new ArrayList<>();

        while(numberOfAccounts-->0)
        {
            double accountMoney=rnd.nextDouble()*5000;
            accounts.add(new Account(numberOfAccounts,accountMoney));

        }
       return accounts;
    }



    private  List<List<MoneyTransferInfo>> generateTransactions(List<Account> accounts) {
        Random rnd=new Random();
        int countOfOperators=rnd.nextInt(50)+20;
        List<List<MoneyTransferInfo>> result=new ArrayList<>();
        while(countOfOperators-->0)
        {
            int numberOfOperatorsTransactions=rnd.nextInt(500)+500;
            List<MoneyTransferInfo> operatorTransfers=new ArrayList<>();
            while(numberOfOperatorsTransactions-->0)
            {

                Account fromAccount=accounts.get(rnd.nextInt(accounts.size()));
                Account toAccount;
                do{
                    toAccount=accounts.get(rnd.nextInt(accounts.size()));
                }
                while (toAccount.equals(fromAccount));

                operatorTransfers.add(new MoneyTransferInfo(fromAccount,toAccount,rnd.nextDouble()*5000));
            }
            result.add(operatorTransfers);
        }
        return result;
    }

}