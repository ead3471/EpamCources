package homeworks.part1;

/**
 * Created by Freemind on 2016-08-27.
 */
public class Ex2v1 {

    public static void main(String[] args) {
        double eps=0.001;

        if(args.length>0)
            eps=CommonUtils.tryGetDoubleFromString(args[0],0.01);

        int sequenceIndex=1;
        double sequenceElement=getSequenceElement(sequenceIndex);

        while(sequenceElement>=eps)
        {
            System.out.println(sequenceIndex+":"+sequenceElement);
            sequenceElement=getSequenceElement(++sequenceIndex);
        }

        System.out.println("First less than eps="+eps+" "+sequenceIndex+":"+sequenceElement);
    }

    private static double getSequenceElement(int sequenceIndex)
    {
        return 1.0/Math.pow(sequenceIndex+1,2);
    }
}
