package com.epam.javase09.t02;

import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Freemind on 2016-10-18.
 */
public class STaxMenuParserTest {

    @Test
    public void STaxMenuParserTest() throws IOException, XMLStreamException {
        List<Food> loadedFood=STaxMenuParser.parseMenu("E:\\Projects\\GitHub\\EpamCources\\homeworks\\src\\test\\resources\\breakfast_menu.xml");
        assertThat(loadedFood.size(),is(5));
    }

}