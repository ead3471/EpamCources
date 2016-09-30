package com.epam.javase05.t01;

import com.epam.javase05.t01.exceptions.LocationIsNotDirectoryException;
import com.epam.javase05.t01.exceptions.LocationNotFoundException;

import java.io.*;
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
    private File currentLocation;
    private final static String PATH_SEPARATOR = File.separator;

    public  FilesCommander()
    {
        currentLocation= new File(System.getProperty("user.dir"));
    }

    public void goToLocation(String locationPath) throws LocationNotFoundException, LocationIsNotDirectoryException {

        locationPath=prepareFilePath(locationPath);

        File newLocation = new File(locationPath);
        if (!newLocation.exists())
            throw new LocationNotFoundException(locationPath);
        if (!newLocation.isDirectory())
            throw new LocationIsNotDirectoryException(locationPath);

        currentLocation = newLocation;
    }

    public String getCurrentLocationAbsolutePath() {
        return currentLocation.getAbsolutePath();
    }

    public List<File> getCurrentLocationContentFiles() {
        return Arrays.asList(currentLocation.listFiles());
    }

    public List<File> getCurrentLocationContentFiles(FileFilter filter) {
        return Arrays.asList(currentLocation.listFiles(filter));
    }

    public boolean createFile(String filePath) throws IOException {

        filePath = prepareFilePath(filePath);

        if (filePath.lastIndexOf(PATH_SEPARATOR) > 0) {
            new File(filePath.substring(0, filePath.lastIndexOf(PATH_SEPARATOR))).mkdirs();
        }
        File newFile = new File(filePath);
        return newFile.createNewFile();
    }

    public boolean createDir(String filePath)
    {
        filePath = prepareFilePath(filePath);

        File newFile = new File(filePath);
        return newFile.mkdirs();
    }

    public boolean removeFile(String filePath) {
        filePath = prepareFilePath(filePath);
        File removedFile = new File(filePath);
        return removedFile.delete();

    }


    private boolean filePathIsValid(String filePath) {
        return (filePath != null) && (filePath.length() > 0);
    }

    private String prepareFilePath(String filePath) {
        filePath=filePath.replace("/", PATH_SEPARATOR);
        if (!filePathIsValid(filePath)) {
            throw new IllegalArgumentException(filePath);
        }
        if(filePath.startsWith(PATH_SEPARATOR))
            filePath=currentLocation.getAbsolutePath()+filePath;
        else if(!Paths.get(filePath).isAbsolute())
            filePath=currentLocation+ PATH_SEPARATOR +filePath;

        return filePath;
    }

    public void appendStringToFile(String filePath, String appendedString) throws IOException {
        filePath = prepareFilePath(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(appendedString);
        }
    }

    public void rewriteFileByString(String filePath, String writedString) throws IOException {
        filePath = prepareFilePath(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(writedString);
        }
    }

    public void rewriteFileFromReader(String filePath, Reader reader) throws IOException {
        filePath = prepareFilePath(filePath);
        char[] readBuffer = new char[1024];

        int readBytesCount = 0;
        if ((readBytesCount = reader.read(readBuffer)) > 0) {
            rewriteFileByString(filePath, new String(readBuffer, 0, readBytesCount));
        }
        while ((readBytesCount = reader.read(readBuffer)) > 0) {
            appendStringToFile(filePath, new String(readBuffer, 0, readBytesCount));
        }
    }

    public void appendFileFromReader(String filePath, Reader reader) throws IOException {
        filePath=prepareFilePath(filePath);
        char[] readBuffer = new char[1024];
        int readBytesCount = 0;

        while ((readBytesCount = reader.read(readBuffer)) > 0) {
            appendStringToFile(filePath, new String(readBuffer, 0, readBytesCount));
        }
    }

   public void levelUp()
   {
       int lastPathDelimiterIndex=currentLocation.getAbsolutePath().lastIndexOf(PATH_SEPARATOR);

       if(lastPathDelimiterIndex>0)
        currentLocation=new File(currentLocation.getAbsolutePath().substring(0,currentLocation.getAbsolutePath().lastIndexOf(PATH_SEPARATOR)));

   }


}
