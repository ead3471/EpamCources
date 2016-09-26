package com.epam.javase05.t01;

import com.epam.javase05.t01.exceptions.LocationIsNotDirectoryException;
import com.epam.javase05.t01.exceptions.LocationNotFoundException;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Freemind on 2016-09-26.
 */
public class FilesCommanderTest {
    @Test
    public void testNavigation() throws LocationIsNotDirectoryException, LocationNotFoundException, IOException {
        FilesCommander commander=new FilesCommander();
        System.out.println(commander.getCurrentLocation());
        System.out.println( commander.getCurrentLocationFiles());
        commander.goToLocation("/src/test/resources/");
        System.out.println( commander.getCurrentLocationFiles());
        commander.createFile("StupidCommanderTestFolder/StupidCommanderTest.txt");
        commander.goToLocation("/StupidCommanderTestFolder");
        commander.appendStringToFile("StupidCommanderTest.txt","SomeString");
        FileReader reader=new FileReader("src\\test\\resources\\utf8file.txt");
        commander.appendFileFromReader("StupidCommanderTest.txt",reader);

    }

}