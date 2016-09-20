package com.epam.javase03.t01;

import java.io.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Необходимо создать класс CrazyLogger, ведущий журнал лога, используя как накопитель

 *объект типа StringBuilder. Логгер должен содержать методы поиска определенной информации

 *в таком логе [с возможностью вывода ее в поток ввода вывода]. Информацию логгер хранит в

 *форматированном виде: dd-mm- YYYY : hh-mm – message;.
 */
public class CrazyLogger {
    private final String TIME_FORMAT="dd-MM-YYYY : HH-mm-ss";
    private StringBuilder loggerPlace=new StringBuilder();
    private final String LOG_MSG_START_PATTERN ="[\\d]{2}-[\\d]{2}-[\\d]{4}\\s:\\s[\\d]{2}-[\\d]{2}-[\\d]{2}-";
    private final String LOG_MSG_DELIMETER=";";

    public void logMessage(String message){
        Instant currentTime=Instant.now();
        String formattedInstant= DateTimeFormatter.ofPattern(TIME_FORMAT).withZone(ZoneId.systemDefault()).format(currentTime);
        loggerPlace.append(formattedInstant).append("-").append(message).append(LOG_MSG_DELIMETER);
    }

    public void findByRegex(String stringPattern, Writer writer) throws IOException {
        Pattern pattern= Pattern.compile(stringPattern);
        Matcher matcher=pattern.matcher(loggerPlace);
        while(matcher.find()) {
            System.out.println(loggerPlace.substring(matcher.start(),matcher.end()));
            writer.write(loggerPlace.substring(matcher.start(),matcher.end()));
        }

    }
//[\d]{2}-[\d]{2}-[\d]{4}\s:\s[\d]{2}-[\d]{2}-message\s.{1}[\n$]
    public void findByMessage(String msgPattern, PrintStream outStream) throws IOException {
        Pattern pattern= Pattern.compile(LOG_MSG_START_PATTERN +msgPattern);
     //   System.out.println("Pattern:"+LOG_MSG_START_PATTERN +stringPattern);
        Matcher matcher=pattern.matcher(loggerPlace);
        outStream.println("Finded by msg pattern "+msgPattern);
        printFinded(matcher,outStream);
    }

    public void findByLogTime(String datePattern,PrintStream outStream)
    {
        Pattern pattern= Pattern.compile(datePattern+"-[^;.]+");
        //   System.out.println("Pattern:"+LOG_MSG_START_PATTERN +stringPattern);
        Matcher matcher=pattern.matcher(loggerPlace);
        outStream.println("Finded by date pattern "+datePattern);
        printFinded(matcher,outStream);

    }

    private void printFinded(Matcher matcher,PrintStream outStream)
    {
        if (!matcher.find()) {
            outStream.println("Nothing finded!");
            return;
        }

        do{
            outStream.println(loggerPlace.substring(matcher.start(),matcher.end()));
        }while(matcher.find());

    }

    public static void main(String[] args) throws InterruptedException, IOException {

        CrazyLogger logger=new CrazyLogger();
        for(int i=0;i<15;i++)
        {
            logger.logMessage("message "+i);
            Thread.sleep(1000);
        }

    //   PrintStream fw=new PrintStream(new File("test.txt"));


        logger.findByMessage(".*",System.out);

       logger.findByLogTime("14-09-2016\\s:\\s\\d{2}-\\d{2}-\\d[2,4,6,8,0]",System.out);

      //  fw.close();

        System.out.println("\nSRC LOG");
        System.out.println(logger.loggerPlace);

    }



}
