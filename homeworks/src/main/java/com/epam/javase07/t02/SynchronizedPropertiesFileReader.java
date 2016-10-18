package com.epam.javase07.t02;

import com.epam.javase05.t02.PropertiesFileReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Создать “универсальный” класс, позволяющий получить значение из любого properties файла.
 * Физическое чтение файла должно происходить только один раз. Учтите ситуацию, когда
 * несколько потоков одновременно обращаются к одному и тому же файлу.
 */
public class SynchronizedPropertiesFileReader extends PropertiesFileReader {

    ReentrantLock loadFileLock=new ReentrantLock();

    {
        propertiesMap = new ConcurrentHashMap<>();
    }


    @Override
    public void load(String filePath) throws IOException {
        loadFileLock.lock();
        try{
            super.load(filePath);
        }
        finally {
            loadFileLock.unlock();
        }
    }









}
