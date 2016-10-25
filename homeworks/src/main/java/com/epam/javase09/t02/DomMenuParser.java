package com.epam.javase09.t02;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Freemind on 2016-10-18.
 */
public class DomMenuParser {
    private final static Logger logger= LogManager.getLogger(STaxMenuParser.class);
    public static List<Food> parseMenu(String fileName) throws IOException, SAXException {
        List<Food> resultList=new ArrayList<>();

        DOMParser domParser=new DOMParser();
        domParser.parse(fileName);

        Element rootElement=domParser.getDocument().getDocumentElement();

        NodeList foodList=rootElement.getElementsByTagName("food");

       for(int i=0;i<foodList.getLength();i++){
           Food.FoodBuilder foodBuilder=Food.getFoodBuilder();

           Element foodElement=(Element)(foodList.item(i));
           foodBuilder.setId(Integer.parseInt(foodElement.getAttribute("id")));

           NodeList foodParameters=foodElement.getChildNodes();

           for(int j=0;j<foodParameters.getLength();j++){
               Node currentNode=foodParameters.item(j);
               if(currentNode instanceof Element) {
                   Element parameterElement = (Element) foodParameters.item(j);
                   Food.FOOD_PARAMETER parameter = Food.FOOD_PARAMETER.getFromString(parameterElement.getLocalName());
                   parameter.handle(foodBuilder, parameterElement.getTextContent().replace("\n"," ").trim());
               }
           }
           resultList.add(foodBuilder.build());
       }
    logger.debug(resultList);
    return resultList;
    }
}
