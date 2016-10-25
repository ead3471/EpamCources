package com.epam.javase09.t02;

import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Freemind on 2016-10-18.
 */
public class DomMenuParserTest {
    @Test
    public void testDomMenuParser() throws IOException, SAXException {
        List<Food> loadedList=DomMenuParser.parseMenu("src/test/resources/breakfast_menu.xml");
        assertThat(loadedList.size(),is(5));
    }
}