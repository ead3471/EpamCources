package com.epam.javase05.t02;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Created by Freemind on 2016-09-26.
 * Создать “универсальный” класс, позволяющий получить значение из любого properties-
 * файла. Физическое чтение файла должно происходить только один раз. Обработайте
 * следующие исключительные ситуации: нет файла *.properties, нет ключа в properties-файле.
 */
public class PropertiesFileReader {

    HashMap<String,String> propertiesMap=new HashMap<>();
    public static final String PROPERTIES_DELIMITER="=";

    public static PropertiesFileReader loadFromFile(String filePath) throws IOException {
            List<String> fileLines=Files.readAllLines(Paths.get(filePath));
            PropertiesFileReader loadedProperties=new PropertiesFileReader();

            for(String fileLine:fileLines)
            {
                int indexOfDelimiter=fileLine.indexOf(PROPERTIES_DELIMITER);
                if(indexOfDelimiter<=0)
                {
                    continue;
                }
                loadedProperties.propertiesMap.put(fileLine.substring(0,indexOfDelimiter),fileLine.substring(indexOfDelimiter+1,fileLine.length()));
            }

            return loadedProperties;
    }

    public int getInt(String key,int defaultValue)
    {
        if(!propertiesMap.containsKey(key))
            return defaultValue;
        return
                Integer.parseInt(propertiesMap.get(key));
    }

    public double getDouble(String key, double defaultValue)
    {

        if(!propertiesMap.containsKey(key))
            return defaultValue;
        return
                Double.parseDouble(propertiesMap.get(key));
    }

    public float getFloat(String key, float defaultValue)
    {

        if(!propertiesMap.containsKey(key))
            return defaultValue;
        return
                Float.parseFloat(propertiesMap.get(key));
    }

    public String getString(String key, String defaultValue)
    {

        if(!propertiesMap.containsKey(key))
            return defaultValue;
        return
                propertiesMap.get(key);
    }

    public void put(String key,String value)
    {
        if(key.length()>0)
            propertiesMap.put(key,value);
    }
    public void putFormattedValue(String key,String value, String valueFormat)
    {
        propertiesMap.put(key,String.format(value,valueFormat));
    }

    public void save(String saveFileName) throws IOException {
        try(BufferedWriter fileWriter=new BufferedWriter(new FileWriter(saveFileName)))
        {
            for(String key:propertiesMap.keySet())
            {
                fileWriter.write(key+"="+propertiesMap.get(key)+"\n");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PropertiesFileReader reader = (PropertiesFileReader) o;

        return propertiesMap.equals(reader.propertiesMap);

    }

    @Override
    public int hashCode() {
        return propertiesMap.hashCode();
    }
}
