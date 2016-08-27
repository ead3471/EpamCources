package homeworks.part1;

/**
 * Created by Freemind on 2016-08-27.
 */
public class CommonUtils {

    public static double tryGetDoubleFromString(String doubleString, double defaultValue)
    {
        try {
            return Double.parseDouble(doubleString);
        }
        catch(NumberFormatException ex)
        {
            System.out.println("Bad String to double conversion:"+doubleString);
            return defaultValue;
        }
    }

    public static int tryGetIntFromString(String intString, int defaultValue)
    {
        try {
            return Integer.parseInt(intString);
        }
        catch(NumberFormatException ex)
        {
            System.out.println("Bad String to double conversion:"+intString);
            return defaultValue;
        }
    }
}
