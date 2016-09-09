package com.epam.javase02.t02;

import com.epam.javase02.t02.chancellery.Chancellery;
import com.epam.javase02.t02.chancellery.items.SimpleChancellery;
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

        workPlace.addStationery( new SimpleChancellery(10.0,"Pen",1));
        workPlace.addStationery( new SimpleChancellery(10.0,"Pen",1));
        workPlace.addStationery( new SimpleChancellery(10.0,"Pen",2));
        workPlace.addStationery( new SimpleChancellery(10.0,"Stepler",3));
        workPlace.addStationery( new SimpleChancellery(10.0,"BookKnife",4));
        assertThat(workPlace.calcWorkerStationeryPrice(),is(40.0));
    }

    @Test
    public void testAddNewStationary()
    {
        WorkPlace workPlace=new WorkPlace();

        Chancellery pen= new SimpleChancellery(0.0,"Pen",12);
        workPlace.addStationery(pen);

        assertThat(workPlace.contains(pen),is(true));

    }

    @Test
    public void testRemoveStationary()
    {
        WorkPlace workPlace=new WorkPlace();

        Chancellery pen= new SimpleChancellery(1.0,"Pen",12);
        Chancellery pen1= new SimpleChancellery(2.0,"Pen1",13);
        Chancellery pen2= new SimpleChancellery(3.0,"Pen2",13);

        workPlace.addStationery(pen);
        workPlace.addStationery(pen1);
        workPlace.addStationery(pen2);
        workPlace.removeStationery(pen1);

        assertThat(workPlace.contains(pen1),is(false));
    }


}