package com.epam.javase02.t03;

import com.epam.javase02.t01.Color;
import com.epam.javase02.t01.Pen;
import com.epam.javase02.t02.chancellery.Chancellery;
import com.epam.javase02.t02.chancellery.items.NoteBook;
import com.epam.javase02.t02.chancellery.items.PaperFormat;
import com.epam.javase02.t02.chancellery.items.ScotchTape;
import com.epam.javase02.t02.chancellery.items.Stapler;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Freemind on 2016-09-07.
 */
@SuppressWarnings("unused")
public class ChancellerySets {

    /**
     * @return <code>List</code> of Chancellery for newcomer
     */
    public static List<Chancellery> getNewcomerSet() {
        ArrayList<Chancellery> newComerSet = new ArrayList<>();

        newComerSet.add(new Pen("BIC", 15.0, Color.TRANSPARENT, Color.BLACK));
        newComerSet.add(new Pen("BIC", 25.0, Color.TRANSPARENT, Color.RED));
        newComerSet.add(new Pen("BIC", 25.0, Color.TRANSPARENT, Color.GREEN));
        newComerSet.add(new ScotchTape(15.0));
        newComerSet.add(new Stapler("ErichCrause", 20.0));
        newComerSet.add(new NoteBook("MOLESKIN", 50.0, PaperFormat.A5));
        return newComerSet;
    }

    /**
     * @return <code>List</code> of random Chancellery
     */
    public static List<Chancellery> createRandomSet() {

        Random rand = new Random();

        int numberOfItems = rand.nextInt(15) + 1;

        ArrayList<Chancellery> resultSet = new ArrayList<>();


        while (numberOfItems --> 0) {
            int chancelleryType = rand.nextInt(3);

            switch (chancelleryType) {
                case 0:
                    resultSet.add(new Pen());
                    break;
                case 1:
                    resultSet.add(new ScotchTape());
                    break;
                case 2:
                    resultSet.add(new NoteBook());
                    break;
            }
        }

        return resultSet;

    }
}




