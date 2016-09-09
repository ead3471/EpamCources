package com.epam.javase02.t04;

import com.epam.javase02.t02.chancellery.Chancellery;
import com.epam.javase02.t03.ChancellerySets;

import java.util.Collections;
import java.util.List;

/**
 * Created by Freemind on 2016-09-09.
 */
public class Sorter {

    public static void main(String[] args) {

        List<Chancellery> newcomerSet=ChancellerySets.getNewcomerSet();
        System.out.println(newcomerSet);
        Collections.sort(newcomerSet,ChancelleryComparators.getByPriceComparator());
        System.out.println(newcomerSet);

        Collections.sort(newcomerSet,ChancelleryComparators.getByPriceAndNameComparator());
        System.out.println(newcomerSet);



    }
}
