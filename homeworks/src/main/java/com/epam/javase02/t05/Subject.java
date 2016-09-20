package com.epam.javase02.t05;

import java.util.Random;

/**
 * Created by Freemind on 2016-09-09.
 */
public enum Subject {
    MATH {
    },

    GEOMETRY {
        {
            studentsRater=new IntegerRater(12);
        }
    },

    GEOGRAPHY {
    },

    PHYSICS {
    },

    QUANTUM_PHYSICS {
        {
            studentsRater=new IntegerRater(42);
        }
    },

    TOE {
    },

    PHILOSOPHY {
    };

    protected Random rnd=new Random();

    protected StudentsRater studentsRater=new FloatRater(5.0f);

    public  String getRateFormat()
    {
        return studentsRater.getRateFormat();
    };
    public  Number rateStudent(Student student){
        return studentsRater.getRate(student);
    }

interface StudentsRater
{
    Number getRate(Student student);
    String getRateFormat();
}

class IntegerRater implements StudentsRater
{
    Random rmd=new Random();
    int maxRateValue;
    IntegerRater(int maxRateValue)
    {
        assert(maxRateValue>0);
        this.maxRateValue=maxRateValue;
    }

    @Override
    public Number getRate(Student student) {
        return new Integer(rnd.nextInt(maxRateValue)+1);
    }

    @Override
    public String getRateFormat() {
        return "d";
    }
}

class FloatRater implements StudentsRater
    {
        float maxRateValue;
        FloatRater(float maxRateValue)
        {
            assert(maxRateValue>0);
            this.maxRateValue=maxRateValue;
        }

        @Override
        public Number getRate(Student student) {
            float rate=0;
            while(rate==0)
             rate=rnd.nextFloat()*maxRateValue;

            return rate;
        }

        @Override
        public String getRateFormat() {
            return ".2f";
        }
}


}
