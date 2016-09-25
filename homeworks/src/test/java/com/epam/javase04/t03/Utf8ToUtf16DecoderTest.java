package com.epam.javase04.t03;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Freemind on 2016-09-25.
 */
public class Utf8ToUtf16DecoderTest {
    @Test
    public void decodeFileTest() throws Exception {
        String inUtf8File="src/test/resources/utf8file.txt";
        String outUtf16File="src/test/resources/utf16file.txt";
        new Utf8ToUtf16Decoder().decodeFile(inUtf8File,outUtf16File);
    }

}