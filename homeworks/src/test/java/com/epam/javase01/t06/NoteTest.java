package com.epam.javase01.t06;

import org.junit.Test;

import static org.junit.Assert.*;

import static org.hamcrest.core.Is.is;

/**
 * Created by Freemind on 2016-09-02.
 */
public class NoteTest {
    @Test
    public void testGetTitle()
    {
        assertThat(new Note("title1","sometext").getNoteTitle(),is("title1"));
    }

    @Test
    public void testGetNullTitle()
    {
        assertNull(new Note(null,"sometext").getNoteTitle());
    }




}