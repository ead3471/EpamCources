package com.epam.javase04.t01;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public void parseFile(String parsedFileFileName, String outFile ) throws IOException {

    initKeyWordSet(javaKeyWordsFile);
        BufferedInputStream inFileStream=new BufferedInputStream(new FileInputStream(parsedFileFileName));
        byte[] readBuffer=new byte[1024];
                int readedBytesCount=0;
        StringBuilder stringBuilder=new StringBuilder();
        while(( readedBytesCount=inFileStream.read(readBuffer))>0)
        {
            stringBuilder.append(new String(readBuffer,0,readedBytesCount));
        }

        String inputFileString=stringBuilder.toString();


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

    private void initKeyWordSet(String fileWithKeyWords) throws IOException {
        Files.lines(Paths.get(fileWithKeyWords)).forEach(javaKeyWords::add);
    }



}
