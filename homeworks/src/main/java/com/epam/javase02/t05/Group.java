package com.epam.javase02.t05;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Freemind on 2016-09-09.
 */
public class Group {
    private String groupId="";
    private Subject groupSubject=Subject.GEOMETRY;

    private Map<Student,Number> students=new HashMap<>();

    public Group(String groupId, Subject subject)
    {
        this.groupId=groupId;
        groupSubject=subject;
    }

    public void addStudent(Student newStudent)
    {
        students.put(newStudent,0);// TODO: 2016-09-09  default RATE???
    }


    public void rateStudents()
    {
        for(Student student:students.keySet())
        {
            students.put(student,groupSubject.rateStudent(student));
        }
    }

    public Number getStudentRate(Student student)
    {
        return students.get(student);
    }

    public boolean isStudentInGroup(Student student)
    {
        return students.containsKey(student);
    }

    public Set<Student> getStudentsInGroup()
    {
        return students.keySet();
    }

    public void printGroup()
    {
        System.out.println("Group:"+groupId+" Subject:"+groupSubject);
        for (Student student:students.keySet()) {
            System.out.println(student+" : "+students.get(student));
        }
    }

}
