package com.epam.javase02.t05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Freemind on 2016-09-25.
 */
public class PersonsGenerator
{
    private static final String[] names={"Jhon","Bill","Jerry","Dave","Mary","Bob","Nick","Homer","Barth","Lisa","Maggy","Marge","Montghomery","Erik","Stan","Kaile","Kenny","Fox",};
    private static final String[] lastNames={"Gray","Brown","Red","Malder","Scally","Skinner","Simpson","Berns","Smitters","Brohlovsky","Marsh","Kartman","Mackormik","Smith"};

    private final static Random rnd=new Random();

    public static List<Person> generatePersons(int studentsNumber)
    {
        ArrayList<Person> resultList=new ArrayList<>();
        while(studentsNumber>0)
        {
            resultList.add(new Person(names[rnd.nextInt(names.length)],lastNames[rnd.nextInt(lastNames.length)],studentsNumber));
            studentsNumber--;
        }
        return resultList;
    }

    public static void loadStudentsToGroup(Group group,List<Person> persons)
    {
        int studentsInGroup=rnd.nextInt(persons.size()/2)+1;
        while(studentsInGroup>0)
        {
            Person person = persons.get(rnd.nextInt(persons.size()));
            if(!group.isStudentInGroup(person)) {
                group.addStudent(person);
                studentsInGroup--;
            }
        }
    }



    public static List<Group> generateGroups()
    {
        ArrayList<Group> resultList=new ArrayList<>();

        int groupsNumber=rnd.nextInt(11)+1;

        while (groupsNumber>0)
        {
            Subject groupSubject=Subject.values()[rnd.nextInt(Subject.values().length)];
            resultList.add(new Group(groupSubject+"_"+groupsNumber--,groupSubject));
        }
        return resultList;

    }


 }
