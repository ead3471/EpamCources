package com.epam.javase02.t05;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Freemind on 2016-09-20.
 */
public class StudentTest {
    @Test
    public void gettersTest() throws Exception {
        Student testStudent=new Student("Vasya","Pupkin",123);
        assertThat(testStudent.getFirstName().equals("Vasya"),is(true));
        assertThat(testStudent.getLastName().equals("Pupkin"),is(true));
        assertThat(testStudent.getId()==123,is(true));
    }

    @Test
    public void equalsTest() throws Exception {

        Student testStudent=new Student("Vasya","Pupkin",123);
        Student testStudent1=new Student("Vasya","Pupkin",1233);
        Student testStudent2=new Student("Maria","Kulebyakina",123);
        assertThat(testStudent.equals(testStudent1),is(false));
        assertThat(testStudent.equals(testStudent2),is(true));
    }



}