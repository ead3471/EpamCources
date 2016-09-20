package com.epam.javase02.t02;

import com.epam.javase02.t01.*;
import com.epam.javase02.t02.chancellery.Chancellery;
import com.epam.javase02.t02.chancellery.items.*;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Freemind on 2016-09-06.
 *Напишите приложение, позволяющее вести учет канцелярских товаров на рабочем месте
 *сотрудника. Определите полную стоимость канцтоваров у определенного сотрудника.
 *
 *
 */
public class WorkPlaceTest {

    @Test
    public void testWorkPlaceTotalSumm()
    {
        WorkPlace workPlace=new WorkPlace();

        workPlace.addChancellery(new NoteBook("Moleskine", 10.0, PaperFormat.A5));
        workPlace.addChancellery(new ScotchTape(15.0));
        workPlace.addChancellery( new Pen(12.0));
        workPlace.addChancellery(new Stapler(10));

        assertThat(workPlace.calcWorkerChancellerySumPrice(),is(47.0));
     }

    @Test
    public void testAddNewStationary()
    {
        WorkPlace workPlace=new WorkPlace();

        Chancellery pen= new Pen(12);
        workPlace.addChancellery(pen);

        assertThat(workPlace.contains(pen),is(true));

    }

    @Test
    public void testRemoveStationary()
    {
        WorkPlace workPlace=new WorkPlace();

        Chancellery pen= new Pen();
        Chancellery pen1= new Stapler();
        Chancellery pen2= new ScotchTape();

        workPlace.addChancellery(pen);
        workPlace.addChancellery(pen1);
        workPlace.addChancellery(pen2);
        workPlace.removeStationery(pen1);

        assertThat(workPlace.contains(pen1),is(false));
    }


}