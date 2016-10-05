package com.epam.javase07.t01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Freemind on 2016-10-04.
 */
class Account
{
    private final int accountId;
    private double depositMoney =0;

    private final Lock accountLock=new ReentrantLock();

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

    public Lock getAccountLock() {
        return accountLock;
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
