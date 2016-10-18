package com.epam.javase07.t01;

import org.jdom2.JDOMException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

import static org.junit.Assert.*;


public class AccountManagerTest {



    @Test
    public void testNotSynchronizedManager() throws InterruptedException {
    testAccountManagerByRandom(AccountManager.getNotSynchronizedManager());
    }

    @Test
    public void testSynchronizedManager() throws InterruptedException {
        testAccountManagerByRandom(AccountManager.getSynchronizedManager());
    }

    @Test
    public void testSynchronizedWithLockManager() throws InterruptedException {
        testAccountManagerByRandom(AccountManager.getSynchronizedWithLockManager());
    }

    @Test
    public void testWorkWithFile() throws IOException, JDOMException, InterruptedException {

        String fileForXmlSaving = "src/test/resources/Transfers_test.xml";
        try {
            List<Account> generatedAccount = generateAccounts(10);
            List<TransferInfo> generatedTransfers = generateTransfers(generatedAccount, 100_000);

            AccountManager.createTransfersInfoXml(generatedAccount, generatedTransfers, fileForXmlSaving);

            List<Account> loadedAccounts = new ArrayList<>();
            List<TransferInfo> loadedTransfers = new ArrayList<>();
            AccountManager.loadTransfersInfoFromFile(loadedAccounts, loadedTransfers, fileForXmlSaving);

            assertTrue(generatedAccount.equals(loadedAccounts));
            assertTrue(generatedTransfers.equals(loadedTransfers));


            int workingThreads = 100;

            doAllOperationsByQueue(AccountManager.getSynchronizedWithLockManager(), loadedAccounts, loadedTransfers, workingThreads);
        }
        finally{

            File savedFile=new File(fileForXmlSaving);
            if(savedFile.exists())
                savedFile.delete();
        }

    }



    private void testAccountManagerByRandom(AccountManager manager) throws InterruptedException {
        final int accountsNumber=2;
        final int operatorsNumber=100;
        final int operatorTransfersNumber=1000_000;
        List<Account> generatedAccounts=generateAccounts(accountsNumber);
        List<TransferInfo> generatedTransfers= generateTransfers(generatedAccounts,operatorTransfersNumber);

        doAllOperationsByQueue(manager,generatedAccounts,generatedTransfers,operatorsNumber);
    }


    private void doAllOperationsByQueue(AccountManager manager, List<Account> accounts,List<TransferInfo> transfers, int workingThreadsNumber) throws InterruptedException {
        BlockingQueue<TransferInfo> transfersQueue=new LinkedBlockingDeque<>(transfers.size()) ;
        transfersQueue.addAll(transfers);
        ExecutorService executor=Executors.newCachedThreadPool();
        double sumMoneyBeforeOperations= getAccountsSumMoney(accounts);
        CountDownLatch latch=new CountDownLatch(workingThreadsNumber);
        for(;workingThreadsNumber-->0;)
        {
            executor.submit(() -> {
                TransferInfo transfer;
                while((transfer=transfersQueue.poll())!=null)
                {
                    try {
                        manager.doMoneyTransfer(transfer.from,transfer.to,transfer.money);
                    } catch (InsufficientFundsException e) {

                    }
                    catch(Exception ex)
                    {
                        System.out.println(transfer+" "+ex);
                    }
                }
                latch.countDown();
            });
        }

        latch.await();

        double sumMoneyAfterOperations=getAccountsSumMoney(accounts);
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


    private List<Account> generateAccounts(int accountsNumber)
    {
        Random rnd=new Random();
        ArrayList<Account> accounts = new ArrayList<>();

        int currentAccountId=0;
        while(currentAccountId++<=accountsNumber)
        {
            double accountMoney=rnd.nextDouble()*5000;
            accounts.add(new Account(currentAccountId,accountMoney));

        }
       return accounts;
    }
    private  List<TransferInfo> generateTransfers(List<Account> accounts, int transfersOperatorsNumber) {
        Random rnd=new Random();

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