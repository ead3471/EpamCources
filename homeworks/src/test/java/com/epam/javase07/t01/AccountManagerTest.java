package com.epam.javase07.t01;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

/**
 * Created by Freemind on 2016-10-02.
 */
public class AccountManagerTest {



    @Test
    public void testNotSynchronizedManager() throws InterruptedException {
    testAccountManagerByRandom(new AccountManager.NotSynchronizedManager());
    }

    @Test
    public void testSynchronizedManager() throws InterruptedException {
        testAccountManagerByRandom(new AccountManager.SynchronizedManager());
    }

    @Test
    public void testSynchronizedWithLockManager() throws InterruptedException {
        testAccountManagerByRandom(new AccountManager.SynchronizedWithLockManager());
    }



    private void testAccountManagerByRandom(AccountManager manager) throws InterruptedException {
        final int accountsMaxNumber=30;
        final int operatorsMaxNumber=100;
        final int operatorTransfersMaxNumber=1000;
        List<Account> generatedAccounts=generateAccounts(accountsMaxNumber);
        List<List<TransferInfo>> generatedTransfers= generateTransfers(generatedAccounts,operatorsMaxNumber,operatorTransfersMaxNumber);
        doAllOperationsAndTestResult(manager,generatedAccounts,generatedTransfers);
    }

    private void doAllOperationsAndTestResult(AccountManager manager,List<Account> accounts, List<List<TransferInfo>> operatorsTasks) throws InterruptedException {
        double sumMoneyBeforeOperations= getAccountsSumMoney(accounts);
        CountDownLatch latch=new CountDownLatch(operatorsTasks.size());
        ExecutorService executorService= Executors.newCachedThreadPool();
        for(List<TransferInfo> currentOperatorTransfers:operatorsTasks)
        {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for(TransferInfo transfer:currentOperatorTransfers)
                    {
                        try {
                            manager.doMoneyTransfer(transfer.from,transfer.to,transfer.money);
                        } catch (InsufficientFundsException e) {

                        }
                    }
                    latch.countDown();
                }
            });
        }

        latch.await();

        double sumMoneyAfterOperations= getAccountsSumMoney(accounts);

        assertTrue(Math.abs(sumMoneyBeforeOperations-sumMoneyAfterOperations)<0.000000001);
        for(Account account:accounts)
        {
            assertTrue(account.getDepositMoney()>0);
        }


    }

    private double getAccountsSumMoney(List<Account> accounts)
    {
        double sumMoney=0;

        for(Account account:accounts)
        {
            sumMoney+=account.getDepositMoney();
        }
        return sumMoney;
    }


    private List<Account> generateAccounts(int accountsMaxNumber)
    {
        Random rnd=new Random();
        int numberOfAccounts=rnd.nextInt(accountsMaxNumber)+accountsMaxNumber/2;
        ArrayList<Account> accounts = new ArrayList<>();

        while(numberOfAccounts-->0)
        {
            double accountMoney=rnd.nextDouble()*5000;
            accounts.add(new Account(numberOfAccounts,accountMoney));

        }
       return accounts;
    }
    private  List<List<TransferInfo>> generateTransfers(List<Account> accounts, int operatorsMaxNumber, int operatorTransfersMaxNumber) {
        Random rnd=new Random();
        int numberOfOperators=rnd.nextInt(operatorsMaxNumber)+operatorsMaxNumber/2;
        List<List<TransferInfo>> result=new ArrayList<>();
        while(numberOfOperators-->0)
        {
            int numberOfOperatorsTransactions=rnd.nextInt(operatorTransfersMaxNumber)+operatorTransfersMaxNumber/2;
            List<TransferInfo> operatorTransfers=new ArrayList<>();
            while(numberOfOperatorsTransactions-->0)
            {

                Account fromAccount=accounts.get(rnd.nextInt(accounts.size()));
                Account toAccount;
                do{
                    toAccount=accounts.get(rnd.nextInt(accounts.size()));
                }
                while (toAccount.equals(fromAccount));

                operatorTransfers.add(new TransferInfo(fromAccount,toAccount,rnd.nextDouble()*5000));
            }
            result.add(operatorTransfers);
        }
        return result;
    }

}