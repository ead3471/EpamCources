package com.epam.javase05.t01;

import com.epam.javase05.t01.exceptions.LocationIsNotDirectoryException;
import com.epam.javase05.t01.exceptions.LocationNotFoundException;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Freemind on 2016-09-25.
 * Разработать приложение, позволяющее просматривать файлы и каталоги файловой
 * системы, а также создавать и удалять текстовые файлы. Для работы с текстовыми файлами
 * необходимо реализовать функциональность записи (дозаписи) в файл. Требуется определить
 * исключения для каждого слоя приложения и корректно их обработать.
 */
public class FilesCommander {
    //String currentLocation;
    private File currentLocation;
    private final static String PATH_DELIMITER = "/";
    public  FilesCommander()
    {
        currentLocation= new File(System.getProperty("user.dir"));
    }

    public void goToLocation(String locationPath) throws LocationNotFoundException, LocationIsNotDirectoryException {

       if(locationPath.startsWith(PATH_DELIMITER))
           locationPath=currentLocation.getPath()+locationPath;

        File newLocation = new File(locationPath);


        if (!newLocation.exists())
            throw new LocationNotFoundException(locationPath);
        if (!newLocation.isDirectory())
            throw new LocationIsNotDirectoryException(locationPath);

        currentLocation = newLocation;
    }

    public File getCurrentLocation() {
        return currentLocation;
    }

    public List<File> getCurrentLocationFiles() {
        return Arrays.asList(currentLocation.listFiles());
    }

    public List<File> getCurrentLocationFiles(FileFilter filter) {
        return Arrays.asList(currentLocation.listFiles(filter));
    }

    public boolean createFile(String filePath) throws IOException {


        filePath = prepareFilePath(filePath);


        if (filePath.lastIndexOf(PATH_DELIMITER) > 0) {
            new File(currentLocation.getPath() + filePath.substring(0, filePath.lastIndexOf(PATH_DELIMITER))).mkdirs();
        }


        File newFile = new File(currentLocation.getPath() + filePath);
        return newFile.createNewFile();
    }

    public boolean removeFile(String filePath) {
        filePath = prepareFilePath(filePath);
        File removedFile = new File(currentLocation.getPath() + filePath);
        return removedFile.delete();

    }


    private boolean filePathIsValid(String filePath) {
        return (filePath != null) && (filePath.length() > 0);
    }

    private String prepareFilePath(String filePath) {
        if (!filePathIsValid(filePath)) {
            throw new IllegalArgumentException(filePath);
        }

        if (!filePath.startsWith(PATH_DELIMITER))
            filePath = PATH_DELIMITER + filePath;
        return filePath;
    }

    public void appendStringToFile(String filePath, String appendedString) throws IOException {
        filePath = prepareFilePath(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentLocation.getPath() + filePath, true))) {
            writer.write(appendedString);
        }
    }

    public void rewriteFileByString(String filePath, String writedString) throws IOException {
        filePath = prepareFilePath(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentLocation.getPath() + filePath))) {
            writer.write(writedString);
        }
    }

    public void rewriteFileFromReader(String filePath, Reader reader) throws IOException {
        filePath = prepareFilePath(filePath);
        char[] readBuffer = new char[1024];

        int readedBytesCount = 0;
        if ((readedBytesCount = reader.read(readBuffer)) > 0) {
            rewriteFileByString(filePath, new String(readBuffer, 0, readedBytesCount));
        }
        while ((readedBytesCount = reader.read(readBuffer)) > 0) {
            appendStringToFile(filePath, new String(readBuffer, 0, readedBytesCount));
        }
    }

    public void appendFileFromReader(String filePath, Reader reader) throws IOException {
        filePath=prepareFilePath(filePath);
        char[] readBuffer = new char[1024];
        int readedBytesCount = 0;

        while ((readedBytesCount = reader.read(readBuffer)) > 0) {
            appendStringToFile(filePath, new String(readBuffer, 0, readedBytesCount));
        }
    }




}
