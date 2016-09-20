package com.epam.javase02.t02;

import com.epam.javase02.t02.chancellery.Chancellery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Freemind on 2016-09-06.
 *Напишите приложение, позволяющее вести учет канцелярских товаров на рабочем месте
 *сотрудника. Определите полную стоимость канцтоваров у определенного сотрудника.
 *
 *
 */
 
public class WorkPlace {
    Collection<Chancellery> workerChancellery =new ArrayList();

    public void addChancellery(Chancellery newChancellery)
    {
        workerChancellery.add(newChancellery);
    }

    public boolean removeStationery(Chancellery removingChancellery)
    {
        return workerChancellery.remove(removingChancellery);
    }

    public double calcWorkerChancellerySumPrice()
    {
        double totalSumm=0.0;
        for (Chancellery chancellery : workerChancellery) {
            totalSumm+= chancellery.getPrice();
        }
        return totalSumm;
    }

    public boolean contains(Chancellery chancellery)
    {
        return workerChancellery.contains(chancellery);
    }

}
