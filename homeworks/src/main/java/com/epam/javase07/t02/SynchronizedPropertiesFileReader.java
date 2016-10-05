package com.epam.javase07.t02;

import com.epam.javase05.t02.PropertiesFileReader;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Создать “универсальный” класс, позволяющий получить значение из любого properties файла.
 * Физическое чтение файла должно происходить только один раз. Учтите ситуацию, когда
 * несколько потоков одновременно обращаются к одному и тому же файлу.
 */
public class SynchronizedPropertiesFileReader extends PropertiesFileReader {
    {
        propertiesMap = new ConcurrentHashMap<>();
    }



}
