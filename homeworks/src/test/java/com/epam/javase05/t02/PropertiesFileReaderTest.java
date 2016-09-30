package com.epam.javase05.t02;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by Freemind on 2016-09-26.
 */
public class PropertiesFileReaderTest {
    @Test
    public void saveTester() throws IOException {
        final String savePropertiesFileName="src/test/resources/TestProperties.properties";
        PropertiesFileReader reader = new PropertiesFileReader();
        Map<String, String> randomPropertiesMap = createRandomMap(150);
        for (String key : randomPropertiesMap.keySet()) {
            reader.put(key, randomPropertiesMap.get(key));
        }

        String saveFileName = savePropertiesFileName;
        reader.save(saveFileName);

        PropertiesFileReader uploadedReader = PropertiesFileReader.loadFromFile(saveFileName);

        new File(savePropertiesFileName).delete();

        assertTrue(uploadedReader.equals(reader));

    }

    private Map<String, String> createRandomMap(int numberOfKeys) {
        Random rnd = new Random();
        Map<String, String> generatedMap = new HashMap<>();

        while (numberOfKeys-- > 0)
            generatedMap.put(generateWord(rnd), generateWord(rnd));

        return generatedMap;
    }

    private String generateWord(Random rnd) {
        int numberOfChars = rnd.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();

        while (numberOfChars-- > 0)
            builder.append((char) (rnd.nextInt(59) + 65));

        return builder.toString();
    }
}