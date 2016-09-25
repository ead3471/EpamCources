package com.epam.javase03.t01;

import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;
import java.util.Random;

/**
 * Created by Freemind on 2016-09-21.
 */
public class CrazyLoggerTest {
    @Test
    public void logMessageTest(){
        CrazyLogger logger=new CrazyLogger();
        fillLogByRandomMessages(logger);
        try {
            PrintWriter fileOut=new PrintWriter("randomLog.txt");
            logger.flushLogToWriter(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillLogByRandomMessages(CrazyLogger logger)
    {
        Random rnd=new Random();
        int logCount=rnd.nextInt(50);
        Instant currentTime=Instant.now();

        while(logCount-->0)
        {
            Instant logTime= currentTime.plusSeconds(rnd.nextInt(1200)-2400);
            logger.logMessage(generateRandomMessage(),logTime);
        }
    }

    private String generateRandomMessage()
    {
        Random rnd=new Random();
        int messageLength=rnd.nextInt(30);
        StringBuilder builder=new StringBuilder();
        while(messageLength-->0)
        {
            builder.append((char)rnd.nextInt(255));
        }
        return builder.toString();

    }

    @Test
    public void findByMessageTest(){

    }

    @Test
    public void findByLogTimeTest(){

    }

}