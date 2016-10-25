package com.epam.javase09.t02;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxMenuParser {
    private final static Logger logger= LogManager.getLogger(SaxMenuParser.class);

    public static List<Food> parseMenu(String filePath) throws SAXException, IOException {
        XMLReader reader= XMLReaderFactory.createXMLReader();
        MenuHandler menuHandler=new MenuHandler();
        reader.setContentHandler(menuHandler);
        reader.parse(new InputSource(filePath));
        //reader.setFeature("http://xml.org/sax/features/string-interning",true);
        //reader.setFeature("http://xml.org/sax/features/string-interning",true);
        logger.debug(menuHandler.loadedFood);
        return menuHandler.loadedFood;
    }

    static class MenuHandler extends DefaultHandler{
        private List<Food> loadedFood=new ArrayList<>();
        private Food.FoodBuilder foodBuilder=Food.getFoodBuilder();
        private StringBuilder textBuilder=new StringBuilder();

        @Override
        public void  startElement(String uri, String localName, String qName, Attributes attributes){
            if(qName.equals("food")){
              //  logger.debug("start food id="+attributes.getValue("id"));
                foodBuilder=Food.getFoodBuilder();
                foodBuilder.setId(Integer.parseInt(attributes.getValue("id")));
            }
            textBuilder=new StringBuilder();
        }

        @Override
        public void characters(char[] buffer,int start, int length){
            textBuilder.append(buffer,start,length);
        }

        @Override
        public void endElement(String uri, String localName, String qName){
            //logger.debug("end element "+qName);
            if(qName.equals("food")){
                Food newFood=foodBuilder.build();
                //logger.debug("new Food created "+newFood);
                loadedFood.add(newFood);
                return;
            }
            //logger.debug(qName+"="+textBuilder.toString());
            Food.FOOD_PARAMETER parameter=Food.FOOD_PARAMETER.getFromString(qName);
            parameter.handle(foodBuilder,textBuilder.toString().replace("\n"," ").trim());

        }
    }


}



