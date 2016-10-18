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
public class SaxMenuParserTest {

    @Test
    public void SaxMenuParserTest() throws IOException, SAXException {
        List<Food> loadedFood=SaxMenuParser.parseMenu("E:\\Projects\\GitHub\\EpamCources\\homeworks\\src\\test\\resources\\breakfast_menu.xml");
        assertThat(loadedFood.size(),is(5));
    }

}