package com.epam.javase09.t02;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Freemind on 2016-10-18.
 */
public class StaxMenuParser {

    public static List<Food> parseMenu(String fileName) throws IOException, XMLStreamException {
        ArrayList<Food> loadedList=new ArrayList<>();

        try(InputStream inStream=new FileInputStream(fileName)){
            XMLInputFactory factory=XMLInputFactory.newInstance();
            XMLStreamReader xmlReader=factory.createXMLStreamReader(inStream);

            while(xmlReader.hasNext()){
                int elementTypeInt=xmlReader.next();
                //switch()


            }


        };


        return loadedList;
    }
}
