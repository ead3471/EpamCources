package com.epam.javase03.t03;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Freemind on 2016-09-22.
 * Необходимо определить в тексте статьи * (html-файл), ссылается ли автор на рисунки
 * последовательно или нет, а также выделить все предложения, в которых встречаются ссылки на
 * рисунки. Для разбора текста использовать регулярные выражения.
 */
public class HtmlParser {

    private final static String PARSED_FILE_NAME="homeworks/src/main/resources/Article.html";
    private final static String REFERENCE_TO_PICTURE_PATTERN_STRING="(\\(Рис\\.|рисун[а-я]+?)\\s(\\d+)";
    private final static String PICTURE_INSERTION_PATTERN_STRING="[^\\(](Рис.\\s{0,1}(\\d+))";
    private final static String SENTENCE_PATTERN_STRING ="[А-Я](((([а-я]\\.){2,}.? ?|[^.!?])*?)(([Рр]ис\\.|рисун[а-я]+) ?\\d{1,2})((([а-я]\\.){2,}|[^.!?])*?))+[.?!]";



    public static void main(String[] args) throws IOException {
//\(Рис.\s(\d+)\)|(рисун[а-я]+\s(\d+))
        Pattern referenceToPicturePattern=Pattern.compile(REFERENCE_TO_PICTURE_PATTERN_STRING);
        Pattern  pictureInsertPattern=Pattern.compile(PICTURE_INSERTION_PATTERN_STRING);
        Pattern sentencePattern=Pattern.compile(SENTENCE_PATTERN_STRING);

        BufferedReader fileReader=new BufferedReader(new InputStreamReader(new FileInputStream(PARSED_FILE_NAME), "windows-1251"));

        String readedLine="";
        String maximumPresentedPictureNumber="0";
        int linesCounter=0;
        ArrayList<String> findedSentences=new ArrayList();
        while((readedLine=fileReader.readLine())!=null)
        {
            linesCounter++;
            Matcher referenceMatcher=referenceToPicturePattern.matcher(readedLine);
            Matcher pictureInsertionMatcher=pictureInsertPattern.matcher(readedLine);
            Matcher sentenceMatcher=sentencePattern.matcher(readedLine);

            while(pictureInsertionMatcher.find())
            {
                maximumPresentedPictureNumber=pictureInsertionMatcher.group(2);
                System.out.println("Finded picture insertion at line "+linesCounter+" "+pictureInsertionMatcher.group(1));
            }
            while(referenceMatcher.find())
            {
                System.out.print("            Finded picture reference at line "+linesCounter+" ref picture: "+referenceMatcher.group(2));
                System.out.println(Integer.parseInt(maximumPresentedPictureNumber)>=Integer.parseInt(referenceMatcher.group(2))?" OK":" BAD REF");
            }

            while(sentenceMatcher.find())
            {
                findedSentences.add(sentenceMatcher.group(0));
            }
        }

        System.out.println("\nFinded sentences:("+findedSentences.size()+")");
        BufferedReader inFile=new BufferedReader(new FileReader(new File("Sentences.txt")));


        int readedLinesCount=1;
        boolean allEqual=true;
        for (String sentence:findedSentences) {
            try {

                String inFileString=inFile.readLine();
                System.out.print(sentence.equals(inFileString)?"OK:":" FAILED:");
                System.out.println(sentence);
                if(!sentence.equals(inFileString))
                {
                    allEqual=false;
                }
                readedLinesCount++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(allEqual?"Test PASS":"TestFailed");



        }


    }
//[А-Я](([^\.^!^?|[а-я]\.+]*?)([Рр]ис\. {0,1}\d{1,2}|рисун)((^\.^\!^\?|([а-я]\.+))*?))+?[\.\?\!]
//[А-Я](([^..]*?)([Рр]ис\. {0,1}\d{1,2}|рисун)([^..]*?))+\.

