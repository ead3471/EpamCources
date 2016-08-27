/**
 * Created by Freemind on 2016-08-23.
 */
/**
 * Created by Freemind on 2016-08-21.
 */
import static java.lang.Math.PI;


public class CommandAgrs  {


    static int PI=10;

    public static void main(String[] args) {
        if (args.length==0) System.out.println("No arguments passed!");
        for (String arg: args) {
            System.out.println("ARG "+arg);
        }


        Float f=Float.NaN;
        System.out.println(f.longValue());
        Integer ii=Float.floatToIntBits(f);



        System.out.println(ii);
        System.out.println(Integer.toBinaryString(ii));

        ii=ii>>5;

        Object a;
        System.out.println(Integer.toBinaryString(ii));
        System.out.println(PI);



        int x=5;
        switch (x) {
            case 5:
                System.out.println("5");
            default:
                System.out.println("def");
            case 6:
                System.out.println("6");
            case 7:
                System.out.println("7");
        }

        foo(new int[]{1,2,3});
    }



    static void foo(Object ...args)
    {
        System.out.println("foo");
    }
    static void foo(int ...args)
    {
        System.out.println("foo_1");
    }


    //static void foo(int a)
    //{
      //  System.out.println("foo 1");
    //}
//    static void foo(int a, int b)
//    {
//        System.out.println("foo 2");
//    }
}

abstract class A
{

    int a=10;
}

class B extends A
{


    int a=13;


    public A bar()
    {
        return this;

    }

    public static void main(String[] args) {
        A a=new B().bar();
        System.out.println(a.a);
    }
}
