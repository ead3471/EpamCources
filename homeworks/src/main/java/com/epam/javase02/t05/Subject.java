package com.epam.javase02.t05;

import java.util.Random;

/**
 * Created by Freemind on 2016-09-09.
 */
public enum Subject {
    MATH {
        @Override
        public Number rateStudent(Student student) {
            return rnd.nextDouble()*5;
        }
    },
    GEOMETRY {
        @Override
        public String getRateFormat()
        {
            return "d";
        }
        @Override
        public Number rateStudent(Student student) {
            return rnd.nextInt(5);
        }
    },
    GEOGRAPHY {
        @Override
        public Number rateStudent(Student student) {
            return rnd.nextDouble()*5;
        }
    },
    PHYSICS {
        @Override
        public Number rateStudent(Student student) {
            return rnd.nextDouble()*5;
        }
    },
    QUANTUM_PHYSICS {
        @Override
        public Number rateStudent(Student student) {
            return rnd.nextInt(12);
        }

        @Override
        public String getRateFormat()
        {
            return "d";
        }
    },
    TOE {
        @Override
        public Number rateStudent(Student student) {
            return rnd.nextDouble()*5;
        }
    },
    PHILOSOPHY {
        @Override
       public Number rateStudent(Student student) {
            return rnd.nextDouble()*5;
        }
    };

    protected Random rnd=new Random();
    public abstract Number rateStudent(Student student);
    private final static String DEFAULT_RATE_FORMAT =".2f";

    public  String getRateFormat()
    {
        return DEFAULT_RATE_FORMAT;
    };



}
