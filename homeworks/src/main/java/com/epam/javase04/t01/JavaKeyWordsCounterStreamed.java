package com.epam.javase04.t01;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *Прочитайте файл, содержащий код на языке Java. Определите, какие ключевые слова
 * языка Java это код содержит. Выведите эти слова и их количество в другой файл. Используйте
 * только байтовые потоки ввода-вывода.
 */
public class JavaKeyWordsCounterStreamed {
    private  final String javaKeyWordsFile;
    private HashSet<String> javaKeyWords=new HashSet<>();

    public JavaKeyWordsCounterStreamed(String javaKeyWordsFile) {
        this.javaKeyWordsFile = javaKeyWordsFile;
    }

    public void parseFile(String parsedFileName, String outFile ) throws IOException {
        initKeyWordSet(javaKeyWordsFile);
        String inputFileString=readFileToString(parsedFileName);
        BufferedOutputStream outStream=new BufferedOutputStream(new FileOutputStream(outFile));
        for(String javaKeyWord:javaKeyWords)
        {
            Matcher wordMatcher=Pattern.compile("(^|[^\\w$])"+javaKeyWord+"(^|[^\\w$])").matcher(inputFileString);
            int wordIncludesCounter=0;

            while(wordMatcher.find()) {

                wordIncludesCounter++;
            }
            outStream.write((javaKeyWord+":"+wordIncludesCounter+"\n").getBytes());

        }
        outStream.close();
    }


    private String readFileToString(String parsedFileName) throws IOException {
        BufferedInputStream inFileStream=new BufferedInputStream(new FileInputStream(parsedFileName));
        byte[] readBuffer=new byte[1024];
        int readBytesCount=0;
        StringBuilder stringBuilder=new StringBuilder();
        while(( readBytesCount=inFileStream.read(readBuffer))>0)
        {
            stringBuilder.append(new String(readBuffer,0,readBytesCount));
        }

        return stringBuilder.toString();
    }




    private void initKeyWordSet(String fileWithKeyWords) throws IOException {
        Files.lines(Paths.get(fileWithKeyWords)).forEach(javaKeyWords::add);
    }



}
