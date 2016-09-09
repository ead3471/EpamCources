/**
 * Created by Freemind on 2016-08-23.
 */
/**
 * Created by Freemind on 2016-08-21.
 */
import com.sun.deploy.model.LocalApplicationProperties;
import com.sun.deploy.util.SystemUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.regex.Pattern;

import static java.lang.Math.PI;


public class CommandAgrs  {


    static int PI=10;

    String a;


    public static void main(String[] args) throws IOException {

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
        
    }



}