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

    final Account from;
    final Account to;
    final double money;

    @Override
    public String toString() {
        return "("+from.getAccountId() +
                "->" + to.getAccountId() +
                " money=" + money+")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransferInfo that = (TransferInfo) o;

        if (Double.compare(that.money, money) != 0) return false;
        if (!from.equals(that.from)) return false;
        return to.equals(that.to);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = from.hashCode();
        result = 31 * result + to.hashCode();
        temp = Double.doubleToLongBits(money);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
