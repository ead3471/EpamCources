package com.epam.javase03.t01;

import java.io.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 * Необходимо создать класс CrazyLogger, ведущий журнал лога, используя как накопитель

 *объект типа StringBuilder. Логгер должен содержать методы поиска определенной информации

 *в таком логе [с возможностью вывода ее в поток ввода вывода]. Информацию логгер хранит в

 *форматированном виде: dd-mm- YYYY : hh-mm – message;.
 */
public class CrazyLogger {
    private final String TIME_FORMAT="dd-MM-YYYY : HH-mm";
    private final String LOG_MSG_START_PATTERN ="[\\d]{2}-[\\d]{2}-[\\d]{4}\\s:\\s[\\d]{2}-[\\d]{2}-";
    private final String LOG_MSG_DELIMITER =";\n";
    private StringBuilder loggingProvider =new StringBuilder();

    public void logMessage(String message){
        Instant currentTime=Instant.now();
       logMessage(message,currentTime);
    }

    public void logMessage(String message,Instant logTime){
        String formattedInstant= DateTimeFormatter.ofPattern(TIME_FORMAT).withZone(ZoneId.systemDefault()).format(logTime);
        loggingProvider.append(formattedInstant).append("-").append(message).append(LOG_MSG_DELIMITER);
    }

    public void findByMessage(String msgPattern, PrintStream outStream) throws IOException {


        Pattern pattern = Pattern.compile(LOG_MSG_START_PATTERN + msgPattern);
        outStream.println("\n Finded by msg pattern "+msgPattern);
        printMatches(outStream,pattern);
    }

    public void findByLogTime(String datePattern,PrintStream outStream)
    {
        Pattern pattern= Pattern.compile(datePattern+"-.*");
        System.out.println("Full pattern "+datePattern+"-.*");
        outStream.println("\n Finded by date pattern "+datePattern);
        printMatches(outStream,pattern);
    }

    public void flushLogToWriter(Writer out) throws IOException {
        out.write(loggingProvider.toString());
    }

    private void printMatches(PrintStream outStream, Pattern pattern)
    {
        String[] logLines = loggingProvider.toString().split(LOG_MSG_DELIMITER);

        for (String logLine : logLines) {
            if (pattern.matcher(logLine).matches()) {
                outStream.println(logLine);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {

        CrazyLogger logger=new CrazyLogger();
        for(int i=0;i<15;i++)
        {
            logger.logMessage("message "+i);
            Thread.sleep(1000);
        }

        logger.findByMessage(".*\\s[2,4,6,8,0]",System.out);
        logger.findByLogTime(".*[1,3,5,7,9]",System.out);
        System.out.println("\nSRC LOG");
        System.out.println(logger.loggingProvider);

    }
}
