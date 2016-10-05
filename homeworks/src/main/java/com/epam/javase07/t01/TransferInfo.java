package com.epam.javase07.t01;

/**
 * Created by Freemind on 2016-10-04.
 */
class TransferInfo {

    public TransferInfo(Account from, Account to, double money) {
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
