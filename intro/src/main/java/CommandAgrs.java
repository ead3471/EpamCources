/**
 * Created by Freemind on 2016-08-23.
 */
/**
 * Created by Freemind on 2016-08-21.
 */
import org.xml.sax.SAXException;

import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;
import java.util.regex.Pattern;


public class CommandAgrs  {


    static int PI=10;

    String a;


    public static void main(String[] args) throws IOException, SAXException {

        System.out.println(Locale.getDefault().getDisplayCountry(Locale.CHINA));
        System.out.println(Locale.getDefault().getDisplayCountry(Locale.JAPAN));
        System.out.println(Locale.getDefault().getDisplayLanguage());
        System.out.println(Locale.getDefault().getDisplayLanguage(Locale.KOREAN));
        System.out.println(NumberFormat.getCurrencyInstance(Locale.UK).format(10.0));
        System.out.println(NumberFormat.getInstance(Locale.KOREAN).format(100000000.0));


        //System.out.println(DateFormat.getDateTimeInstance(DateFormat.DAY_OF_YEAR_FIELD,DateFormat.AM_PM_FIELD).format(new Date()));
        Pattern p= Pattern.compile("<+");
        String str="<body><h1>a<<b</h1></body>";
        System.out.println(Arrays.toString(p.split(str)));

        ByteArrayOutputStream b=new ByteArrayOutputStream();
        Double dd=new Double(15);

        System.out.println(System.in.read());


        XMLReader reader= XMLReaderFactory.createXMLReader();

        DocumentBuilderFactory.newInstance();



    }



}