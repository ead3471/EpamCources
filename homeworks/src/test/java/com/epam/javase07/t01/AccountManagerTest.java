package com.epam.javase07.t01;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.*;

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
        final int accountsMaxNumber=2;
        final int operatorsNumber=15;
        final int operatorTransfersMaxNumber=15;
        List<Account> generatedAccounts=generateAccounts(accountsMaxNumber);

        List<TransferInfo> generatedTransfers= generateTransfers(generatedAccounts,operatorTransfersMaxNumber);

       // doAllOperationsAndTestResult(manager,generatedAccounts,generatedTransfers,operatorsMaxNumber);
        doAllOperationsByQueue(manager,generatedAccounts,generatedTransfers,operatorsNumber);

    }


    private void doAllOperationsByQueue(AccountManager manager, List<Account> accounts,List<TransferInfo> transfers, int workingThreadsNumber) throws InterruptedException {
        BlockingQueue<TransferInfo> transfersQueue=new ArrayBlockingQueue<>(transfers.size()) ;
        transfersQueue.addAll(transfers);
        ExecutorService executor=Executors.newCachedThreadPool();
        double sumMoneyBeforeOperations= getAccountsSumMoney(accounts);
        CountDownLatch latch=new CountDownLatch(workingThreadsNumber);
        for(;workingThreadsNumber-->0;)
        {
            executor.submit(new Runnable() {
                @Override
                public void run() {

                    TransferInfo transfer;
                    while((transfer=transfersQueue.poll())!=null)

                    {
                       // System.out.println(Thread.currentThread().getName()+"get transfer"+transfer);
                        try {
                            manager.doMoneyTransfer(transfer.from,transfer.to,transfer.money);
                        } catch (InsufficientFundsException e) {
                          //  System.out.println(e);
                        }

                    }

                    latch.countDown();
                 //   System.out.println("countdown:"+latch.getCount());
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
        int numberOfAccounts=/*rnd.nextInt(accountsMaxNumber)+*/accountsMaxNumber;
        ArrayList<Account> accounts = new ArrayList<>();

        while(numberOfAccounts-->0)
        {
            double accountMoney=rnd.nextDouble()*5000;
            accounts.add(new Account(numberOfAccounts,accountMoney));

        }
       return accounts;
    }
    private  List<TransferInfo> generateTransfers(List<Account> accounts, int transfersOperatorsNumber) {
        Random rnd=new Random();

       // int numberOfOperatorsTransactions=/*rnd.nextInt(operatorTransfersMaxNumber)+*/operatorTransfersMaxNumber;
        List<TransferInfo> transfers=new ArrayList<>();
            while(transfersOperatorsNumber-->0)
            {

                Account fromAccount=accounts.get(rnd.nextInt(accounts.size()));
                Account toAccount;
                do{
                    toAccount=accounts.get(rnd.nextInt(accounts.size()));
                }
                while (toAccount.equals(fromAccount));
                transfers.add(new TransferInfo(fromAccount,toAccount,rnd.nextDouble()*5000));
            }
        return transfers;
    }

}