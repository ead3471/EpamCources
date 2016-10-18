package com.epam.javase07.t02;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Freemind on 2016-10-12.
 */
public class SynchronizedPropertiesFileReaderTest {

    @Test
    public void testLoadFromFile() throws IOException {
        SynchronizedPropertiesFileReader reader=new SynchronizedPropertiesFileReader();
        reader.load("src/test/resources/TestProperties_sync.properties");
        int a=50;
    }

}