package com.epam.javase02.t04;

import com.epam.javase02.t02.chancellery.Chancellery;

import java.util.Comparator;

/**
 * Created by Freemind on 2016-09-09.
 */
public class ChancelleryComparators  {

    public static Comparator<Chancellery> getByPriceComparator()
    {
        return new Comparator<Chancellery>() {
            @Override
            public int compare(Chancellery o1, Chancellery o2) {
                if(o1.getPrice()>o2.getPrice()) return 1;
                if(o1.getPrice()<o2.getPrice()) return -1;
                return 0;
            }
        };
    }


    public static Comparator<Chancellery> getByPriceAndNameComparator()
    {
        return new Comparator<Chancellery>() {
            @Override
            public int compare(Chancellery o1, Chancellery o2) {
                int nameCompareResult=o1.getName().compareTo(o2.getName());
                if(nameCompareResult==0)
                {
                    return getByPriceComparator().compare(o1,o2);
                }
                return nameCompareResult;
            }
        };
    }

}
