package com.epam.javase02.t05;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Freemind on 2016-09-09.
 */
public enum Subject {
    MATH {
        @Override
        Number rateStudent(Student student) {
            return rnd.nextDouble()*5;
        }
    },
    GEOMETRY {
        @Override
        Number rateStudent(Student student) {
            return rnd.nextDouble()*5;
        }
    },
    GEOGRAPHY {
        @Override
        Number rateStudent(Student student) {
            return rnd.nextDouble()*5;
        }
    },
    PHYSICS {
        @Override
        Number rateStudent(Student student) {
            return rnd.nextDouble()*5;
        }
    },
    QUANTUM_PHYSICS {
        @Override
        Number rateStudent(Student student) {
            return rnd.nextDouble()*5;
        }
    },
    TOE {
        @Override
        Number rateStudent(Student student) {
            return rnd.nextDouble()*5;
        }
    },
    PHILOSOPHY {
        @Override
        Number rateStudent(Student student) {
            return rnd.nextDouble()*5;
        }
    };







    protected Random rnd=new Random();
    private Map<Student,Number> studentsInGroup=new HashMap<>();

    public void addStudent(Student student)
    {
        studentsInGroup.put(student, rateStudent(student));
    }

    abstract Number rateStudent(Student student);



}
