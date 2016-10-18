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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return (getAccountId() == account.getAccountId());


    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getAccountId();
        temp = Double.doubleToLongBits(getDepositMoney());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getAccountLock() != null ? getAccountLock().hashCode() : 0);
        return result;
    }
}
