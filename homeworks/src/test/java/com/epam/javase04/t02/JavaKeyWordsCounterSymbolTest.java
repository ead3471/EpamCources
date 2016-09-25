package com.epam.javase04.t02;

import org.junit.Test;

/**
 * Created by Freemind on 2016-09-25.
 */
public class JavaKeyWordsCounterSymbolTest {
    @Test
    public void parseFileTest() throws Exception {
            new JavaKeyWordsCounterSymbol("src\\test\\resources\\java_keywords.txt").
                    parseFile("src/main/java/com/epam/javase01/t06/Notebook.java","src/test/resources/java_words_counted_symbols.txt");

    }

}