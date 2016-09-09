package com.epam.javase02.t03;

import com.epam.javase02.t01.Color;
import com.epam.javase02.t01.Pen;
import com.epam.javase02.t02.chancellery.Chancellery;
import com.epam.javase02.t02.chancellery.items.NoteBook;
import com.epam.javase02.t02.chancellery.items.PaperFormat;
import com.epam.javase02.t02.chancellery.items.ScotchTape;
import com.epam.javase02.t02.chancellery.items.Stepler;


import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Freemind on 2016-09-07.
 */
public class ChancellerySets {

    public static Collection<Chancellery> getNewcomerSet()
    {
        ArrayList<Chancellery> newComerSet=new ArrayList<>();

        newComerSet.add(new Pen("BIC",15.0, Color.TRANSPARENT,Color.BLACK));
        newComerSet.add(new Pen("BIC",25.0, Color.TRANSPARENT,Color.RED));
        newComerSet.add(new Pen("BIC",25.0, Color.TRANSPARENT,Color.GREEN));
        newComerSet.add(new ScotchTape(15.0));
        newComerSet.add(new Stepler("ErichCrause",20.0));
        newComerSet.add(new NoteBook("MOLESKIN", PaperFormat.A5,50.0));

        return newComerSet;
    }



}
