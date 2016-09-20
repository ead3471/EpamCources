package com.epam.javase02.t02.chancellery.items;

import com.epam.javase02.t01.Color;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Freemind on 2016-09-20.
 */
public class ScotchTapeTest {

    @Test

    public void testConstructors()
    {
        double testPrice=15.0;
        Color testColor=Color.RED;
        String testName="SomeName";
        int testWidthInMm=42;


        ScotchTape tape=new ScotchTape(testName,testPrice,testColor,testWidthInMm);
        assertThat(tape.getManufacturerName(),is(testName));
        assertThat(tape.getPrice(),is(testPrice));
        assertThat(tape.getColor(),is(testColor));
        assertThat(tape.getWidthInMm(),is(testWidthInMm));

        tape=new ScotchTape(testName,testPrice,testColor);
        assertThat(tape.getManufacturerName(),is(testName));
        assertThat(tape.getPrice(),is(testPrice));
        assertThat(tape.getColor(),is(testColor));
        assertThat(tape.getWidthInMm(),is(25));

        tape=new ScotchTape(testName,testPrice);
        assertThat(tape.getManufacturerName(),is(testName));
        assertThat(tape.getPrice(),is(testPrice));
        assertThat(tape.getColor(),is(Color.TRANSPARENT));
        assertThat(tape.getWidthInMm(),is(25));

        tape=new ScotchTape(testPrice);
        assertThat(tape.getManufacturerName(),is("Noname"));
        assertThat(tape.getPrice(),is(testPrice));
        assertThat(tape.getColor(),is(Color.TRANSPARENT));
        assertThat(tape.getWidthInMm(),is(25));

        tape=new ScotchTape();
        assertThat(tape.getManufacturerName(),is("Noname"));
        assertThat(tape.getPrice(),is(50.0));
        assertThat(tape.getColor(),is(Color.TRANSPARENT));
        assertThat(tape.getWidthInMm(),is(25));



    }


}