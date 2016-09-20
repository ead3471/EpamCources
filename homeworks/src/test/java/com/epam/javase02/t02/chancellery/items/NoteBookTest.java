package com.epam.javase02.t02.chancellery.items;

import org.junit.Test;



import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Freemind on 2016-09-20.
 */
public class NoteBookTest {


@Test
public void testConstructors()
{

    double testPrice=15.0;
    String testName="SomeName";
    PaperFormat testFormat= PaperFormat.A3;
    int testPagesNumber=42;

    NoteBook noteBook=new NoteBook(testName,testPrice,testFormat,testPagesNumber);
    assertThat(noteBook.getManufacturerName(),is(testName));
    assertThat(noteBook.getPrice(),is(testPrice));
    assertThat(noteBook.getNumberOfPages(),is(testPagesNumber));
    assertThat(noteBook.getPaperFormat(),is(testFormat));

    noteBook=new NoteBook(testName,testPrice,testFormat);
    assertThat(noteBook.getManufacturerName(),is(testName));
    assertThat(noteBook.getPrice(),is(testPrice));
    assertThat(noteBook.getPaperFormat(),is(testFormat));
    assertThat(noteBook.getNumberOfPages(),is(96));

    noteBook=new NoteBook(testName,testPrice);
    assertThat(noteBook.getManufacturerName(),is(testName));
    assertThat(noteBook.getPrice(),is(testPrice));
    assertThat(noteBook.getPaperFormat(),is(PaperFormat.A4));
    assertThat(noteBook.getNumberOfPages(),is(96));

    noteBook=new NoteBook(testPrice);
    assertThat(noteBook.getManufacturerName(),is("Noname"));
    assertThat(noteBook.getPrice(),is(testPrice));
    assertThat(noteBook.getPaperFormat(),is(PaperFormat.A4));
    assertThat(noteBook.getNumberOfPages(),is(96));

    noteBook=new NoteBook();
    assertThat(noteBook.getManufacturerName(),is("Noname"));
    assertThat(noteBook.getPrice(),is(15.0));
    assertThat(noteBook.getPaperFormat(),is(PaperFormat.A4));
    assertThat(noteBook.getNumberOfPages(),is(96));
}





}