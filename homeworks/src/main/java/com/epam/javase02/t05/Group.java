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

    private Map<Person,Number> students=new HashMap<>();

    public Group(String groupId, Subject subject)
    {
        this.groupId=groupId;
        groupSubject=subject;
    }

    public void addStudent(Person newPerson)
    {
        students.put(newPerson,0);// TODO: 2016-09-09  default RATE???
    }


    public void rateStudents()
    {
        for(Person person :students.keySet())
        {
            students.put(person,groupSubject.rateStudent(person));
        }
    }

    public Number getStudentRate(Person person)
    {
        return students.get(person);
    }

    public boolean isStudentInGroup(Person person)
    {
        return students.containsKey(person);
    }

    public Set<Person> getStudentsInGroup()
    {
        return students.keySet();
    }

    public void printGroup()
    {
        System.out.println("Group:"+groupId+" Subject:"+groupSubject);
        for (Person person :students.keySet()) {


            System.out.printf("%-25s : %"+ groupSubject.getRateFormat()+"\n", person,students.get(person));
        }
    }

    public String getRateFormat()
    {
        return groupSubject.getRateFormat();
    }

    public String getGroupId() {
        return groupId;
    }
}
