package com.epam.javase02.t03;

import com.epam.javase02.t01.Color;
import com.epam.javase02.t01.Pen;
import com.epam.javase02.t02.chancellery.Chancellery;
import com.epam.javase02.t02.chancellery.items.NoteBook;
import com.epam.javase02.t02.chancellery.items.PaperFormat;
import com.epam.javase02.t02.chancellery.items.ScotchTape;
import com.epam.javase02.t02.chancellery.items.Stapler;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Freemind on 2016-09-20.
 */
public class ChancellerySetsTest {
    @Test
    public void getNewcomerSetTest() throws Exception {

        /*
         newComerSet.add(new Pen("BIC", 15.0, Color.TRANSPARENT, Color.BLACK));
        newComerSet.add(new Pen("BIC", 25.0, Color.TRANSPARENT, Color.RED));
        newComerSet.add(new Pen("BIC", 25.0, Color.TRANSPARENT, Color.GREEN));
        newComerSet.add(new ScotchTape(15.0));
        newComerSet.add(new Stapler("ErichCrause", 20.0));
        newComerSet.add(new NoteBook("MOLESKIN", 50.0, PaperFormat.A5));
        */
        List<Chancellery> newcomerSet=ChancellerySets.getNewcomerSet();
        assertThat(newcomerSet.contains(new Pen("BIC", 15.0, Color.TRANSPARENT, Color.BLACK)),is(true));
        assertThat(newcomerSet.contains(new Pen("BIC", 25.0, Color.TRANSPARENT, Color.RED)),is(true));
        assertThat(newcomerSet.contains(new Pen("BIC", 25.0, Color.TRANSPARENT, Color.GREEN)),is(true));
        assertThat(newcomerSet.contains(new ScotchTape(15.0)),is(true));
        assertThat(newcomerSet.contains(new Stapler("ErichCrause", 20.0)),is(true));
        assertThat(newcomerSet.contains(new NoteBook("MOLESKIN", 50.0, PaperFormat.A5)),is(true));

    }

}