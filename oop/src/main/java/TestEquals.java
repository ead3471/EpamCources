/**
 * Created by Freemind on 2016-09-20.
 */
public class TestEquals {
    public static void main(String[] args) {

        A a=new B();
        A a1=new B();
        System.out.println(a.equals(a1));


    }
}
class A
{
    @Override
    public boolean equals(Object o)
    {
        System.out.println("Im in A. getted class:"+getClass());
        if(getClass()!=o.getClass()) return false;
        return true;
    }
}

class B extends A{
    @Override
    public boolean equals(Object o)
    {
        System.out.println("Im in B. getted class:"+getClass());
        return super.equals(o);

    }
}
