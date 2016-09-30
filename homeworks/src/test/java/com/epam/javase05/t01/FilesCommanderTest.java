package com.epam.javase05.t01;

import com.epam.javase05.t01.exceptions.LocationIsNotDirectoryException;
import com.epam.javase05.t01.exceptions.LocationNotFoundException;
import org.jcp.xml.dsig.internal.SignerOutputStream;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by Freemind on 2016-09-26.
 */
public class FilesCommanderTest {
    private final static File WORK_DIRECTORY=new File(System.getProperty("user.dir"));


    //@Test
    public void testNavigation() throws LocationIsNotDirectoryException, LocationNotFoundException, IOException {
        FilesCommander commander=new FilesCommander();
        commander.goToLocation("/src/test/resources/");

        System.out.println( commander.getCurrentLocationContentFiles());
        commander.createFile("FilesCommander/test1/test1.txt");
        commander.createFile("FilesCommander/test2/test2.txt");
        commander.goToLocation("FilesCommander/test1/");
        commander.appendStringToFile("/FilesCommanderTest.txt","SomeString");
        FileReader reader=new FileReader("src\\test\\resources\\utf8file.txt");
        commander.appendFileFromReader("utf8file.txt",reader);
        commander.levelUp();
        commander.appendStringToFile("test3.txt","teststring");
        System.out.println(commander.removeFile("test3.txt"));
    }

    @Test
    public void defaultLocationTest() {
        FilesCommander commander=new FilesCommander();
        assertTrue(commander.getCurrentLocationAbsolutePath().equals(System.getProperty("user.dir")));
    }
    
    @Test
    public void getCurrentLocationContentFilesTest() {
      FilesCommander commander=new FilesCommander();
      List<File> currentDirectoryContent=Arrays.asList(WORK_DIRECTORY.listFiles());
      assertTrue(commander.getCurrentLocationContentFiles().equals(currentDirectoryContent));

    }

    @Test
    public void goToLocationTestAndLevelUp() throws LocationIsNotDirectoryException, LocationNotFoundException {
        FilesCommander commander=new FilesCommander();

        FileFilter folderFilter=new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        };


        List<File> currentDirectoryContent=Arrays.asList(WORK_DIRECTORY.listFiles(folderFilter));
        File currentLocation=new File(WORK_DIRECTORY.getAbsolutePath());

        directoryWalker(commander);
    }

    private void directoryWalker(FilesCommander commander) throws LocationIsNotDirectoryException, LocationNotFoundException {
        List<File> directoriesInLocation = commander.getCurrentLocationContentFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });

        String rootPath = commander.getCurrentLocationAbsolutePath();
        for (File currentDirectory : directoriesInLocation) {

            //test absolute path walk
            commander.goToLocation(currentDirectory.getPath());
            assertThat(commander.getCurrentLocationAbsolutePath(), is(currentDirectory.getPath()));

            commander.levelUp();
            assertThat(commander.getCurrentLocationAbsolutePath(), is(rootPath));

            //test relative path
            int indexOfLastPathStart = currentDirectory.getAbsolutePath().lastIndexOf(File.separator);

            if (indexOfLastPathStart > 1) {
                String subDirectoryWithSeparator = currentDirectory.getAbsolutePath().substring(indexOfLastPathStart, currentDirectory.getAbsolutePath().length());
                String subDirectoryWithoutSeparator = subDirectoryWithSeparator.replace(File.separator, "");

                commander.goToLocation(subDirectoryWithSeparator);
                assertThat(commander.getCurrentLocationAbsolutePath(), is(currentDirectory.getPath()));

                commander.levelUp();
                assertThat(commander.getCurrentLocationAbsolutePath(), is(rootPath));

                commander.goToLocation(subDirectoryWithoutSeparator);
                assertThat(commander.getCurrentLocationAbsolutePath(), is(currentDirectory.getPath()));

                commander.levelUp();
                assertThat(commander.getCurrentLocationAbsolutePath(), is(rootPath));
            }

            commander.goToLocation(currentDirectory.getAbsolutePath());
            directoryWalker(commander);
            commander.levelUp();
        }
    }

    @Test
    public void creationTest() throws LocationIsNotDirectoryException, LocationNotFoundException, IOException {
        FilesCommander commander=new FilesCommander();
        commander.goToLocation("src/test/resources/");
        assertTrue(commander.createDir("testDirectory"));
        commander.goToLocation("testDirectory");
        assertTrue(commander.createFile("testFile"));

        File createdFile=new File(commander.getCurrentLocationAbsolutePath()+File.separator+"testFile");
        assertTrue(createdFile.exists());

        commander.removeFile("testFile");
        assertFalse(createdFile.exists());

        commander.levelUp();

        assertTrue(commander.removeFile("testDirectory"));
        assertFalse(new File(commander.getCurrentLocationAbsolutePath()+File.separator+"testDirectory").exists());

    }
}