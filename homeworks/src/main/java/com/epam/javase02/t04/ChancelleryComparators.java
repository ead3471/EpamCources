package com.epam.javase02.t04;

import com.epam.javase02.t02.chancellery.Chancellery;
import com.epam.javase02.t03.ChancellerySets;

import java.util.Comparator;
import java.util.List;

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

    public static Comparator<Chancellery> getByNameComparator()
    {
        return new Comparator<Chancellery>() {
            @Override
            public int compare(Chancellery o1, Chancellery o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
    }

    public static Comparator<Chancellery> getByNameAndPriceComparator()
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

    public static void main(String[] args) {


        List<Chancellery> chancellerySet= ChancellerySets.createRandomSet();

        System.out.println(chancellerySet);
            chancellerySet.sort(ChancelleryComparators.getByNameAndPriceComparator());

        System.out.println(chancellerySet);

//            for(int i=0;i<chancellerySet.size()-1;i++)
//            {
//              //  assertThat(chancellerySet.get(i).getPrice()<=chancellerySet.get(i+1).getPrice(),is(true));
//                System.out.println();
//            }
     }

}

