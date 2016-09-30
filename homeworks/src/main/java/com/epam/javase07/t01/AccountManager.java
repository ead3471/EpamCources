package com.epam.javase07.t01;

import java.util.List;

/**
 * Created by Freemind on 2016-09-29.
 * В текстовом (или xml) файле содержится информация о переводах средств со счета на
 * счет. Создайте приложение, позволяющее в параллельном режиме обработать эту информацию
 * (счета в приложении представляются собой объекты). Синхронизируйте код приложения
 * используя ключевое слово synchronized (1 вариант) и библиотеку java.util.concurrent (2 вариант).
 */
public class AccountManager {

    public List<MoneyTransferInfo> readAccountsTransferInfo()
    {
        return  null;
    }

    public void doTransfer(Account from,Account to, double money) throws InsufficientFundsException {
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

    public int getAccountId() {
        return accountId;
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

}
