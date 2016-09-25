package com.epam.javase02.t05;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Freemind on 2016-09-20.
 */
public class PersonTest {
    @Test
    public void gettersTest() throws Exception {
        Person testPerson =new Person("Vasya","Pupkin",123);
        assertThat(testPerson.getFirstName().equals("Vasya"),is(true));
        assertThat(testPerson.getLastName().equals("Pupkin"),is(true));
        assertThat(testPerson.getId()==123,is(true));
    }

    @Test
    public void equalsTest() throws Exception {

        Person testPerson =new Person("Vasya","Pupkin",123);
        Person testPerson1 =new Person("Vasya","Pupkin",1233);
        Person testPerson2 =new Person("Maria","Kulebyakina",123);
        assertThat(testPerson.equals(testPerson1),is(false));
        assertThat(testPerson.equals(testPerson2),is(true));
    }



}