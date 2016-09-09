package com.epam.javase02.t02;

import com.epam.javase02.t02.chancellery.Chancellery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Freemind on 2016-09-06.
 */
public class WorkPlace {
    Collection<Chancellery> workerChancellery =new ArrayList();

    public void addStationery(Chancellery newChancellery)
    {
        workerChancellery.add(newChancellery);
    }

    public boolean removeStationery(Chancellery removingChancellery)
    {
        return workerChancellery.remove(removingChancellery);
    }

    public double calcWorkerStationeryPrice()
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
