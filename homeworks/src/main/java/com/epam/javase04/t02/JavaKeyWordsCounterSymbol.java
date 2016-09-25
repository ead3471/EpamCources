package com.epam.javase04.t02;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Прочитайте файл, содержащий код на языке Java. Определите, какие ключевые словА
 * языка Java это код содержит. Выведите эти слова и их количество в другой файл. Используйте
 * только символьные потоки ввода-вывода.
 */
public class JavaKeyWordsCounterSymbol {
    private final String javaKeyWordFile;
    //private static final String JAVA_KEY_WORDS_FILE="homeworks/src/main/resoirces/java_keywords.txt";
    private HashSet<String> javaKeyWords=new HashSet<>();

    public JavaKeyWordsCounterSymbol(String javaKeyWordFile)
    {
        this.javaKeyWordFile=javaKeyWordFile;
    }

    public void parseFile(String parsedFileFileName, String outFile ) throws IOException {

        initKeyWordSet();
        BufferedReader inFileReader=new BufferedReader(new FileReader(parsedFileFileName));
        int readedBytesCount=0;
        StringBuilder stringBuilder=new StringBuilder();
        String readedString="";
        while((readedString=inFileReader.readLine())!=null)
        {
            stringBuilder.append(readedString);
        }

        String inputFileString=stringBuilder.toString();

        BufferedWriter outWriter=new BufferedWriter(new FileWriter(outFile));
        for(String javaKeyWord:javaKeyWords)
        {
            Matcher wordMatcher= Pattern.compile("(^|[^\\w$])"+javaKeyWord+"(^|[^\\w$])").matcher(inputFileString);
            int wordIncludesCounter=0;
            while(wordMatcher.find())
                wordIncludesCounter++;
            outWriter.write((javaKeyWord+":"+wordIncludesCounter+"\n"));

        }

        outWriter.close();


    }

    private void initKeyWordSet() throws IOException {
        Files.lines(Paths.get(javaKeyWordFile)).forEach(javaKeyWords::add);
    }


}
