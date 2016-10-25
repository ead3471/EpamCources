package com.epam.javase09.t02;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Freemind on 2016-10-18.
 */
public class STaxMenuParser {

    private final static Logger logger= LogManager.getLogger(STaxMenuParser.class);
    public static List<Food> parseMenu(String fileName) throws IOException, XMLStreamException {
        ArrayList<Food> loadedList=new ArrayList<>();
        Food.FoodBuilder foodBuilder=Food.getFoodBuilder();
        String parameterText="";

        try(InputStream inStream=new FileInputStream(fileName)){
            XMLInputFactory factory=XMLInputFactory.newInstance();
            XMLStreamReader xmlReader=factory.createXMLStreamReader(inStream);

            while(xmlReader.hasNext()){
                int elementTypeInt=xmlReader.next();
                switch(elementTypeInt){
                    case XMLStreamConstants.START_ELEMENT:{
                        if(xmlReader.getLocalName().equals("food")){
                            foodBuilder.setId(Integer.parseInt(xmlReader.getAttributeValue("","id")));
                            parameterText="";
                        }
                        break;
                    }
                    case XMLStreamConstants.CHARACTERS:{
                        String readText=xmlReader.getText();
                        if(readText.isEmpty())
                            break;
                        parameterText=readText.trim();

                        break;
                    }
                    case XMLStreamConstants.END_ELEMENT:{
                        if(xmlReader.getLocalName().equals("food")){
                            loadedList.add(foodBuilder.build());
                            break;
                        }
                        Food.FOOD_PARAMETER foodParameter= Food.FOOD_PARAMETER.getFromString(xmlReader.getLocalName());
                        foodParameter.handle(foodBuilder,parameterText);
                    }
                }
            }
        };

        logger.debug(loadedList);
        return loadedList;
    }
}
