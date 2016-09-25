package com.epam.javase02.t05;

import java.util.*;

/**
 * Created by Freemind on 2016-09-09.
 */
public class University {

    private List<Person> educatedPersons =new ArrayList<>();

    private List<Group> educationGroups=new ArrayList<>();

    public University(List<Person> enteredPersons, List<Group> educationGroups)
    {
        educatedPersons.addAll(enteredPersons);
        this.educationGroups.addAll(educationGroups);
        distributeStudentsByGroups();
    }

    public static University generateUniversity()
    {
        return new University(PersonsGenerator.generatePersons(15), PersonsGenerator.generateGroups());
    }

    public void printStudentRates(Person person, List<Group> studentGroups)
    {
        for(Group group:studentGroups)
        {
            System.out.printf("%-20s : %"+group.getRateFormat()+"\n",group.getGroupId(),group.getStudentRate(person));
        }
    }

    public List<Group> getUniversityGroups()
    {
        ArrayList resultList=new ArrayList<Group>();
        resultList.addAll(educationGroups);
        return resultList;
    }

    public List<Person> getUniversityStudents()
    {
        ArrayList resultList=new ArrayList<Person>();
        resultList.addAll(educatedPersons);
        return resultList;
    }


    private void distributeStudentsByGroups()
    {
        for (Group group:educationGroups)
        {
            PersonsGenerator.loadStudentsToGroup(group, educatedPersons);
        }
    }

    public List<Group> findStudentsGroups(Person person)
    {
        ArrayList<Group> resultList=new ArrayList<>();
        for(Group group:educationGroups)
        {
            if(group.isStudentInGroup(person))
            {
                resultList.add(group);
            }
        }
        return resultList;
    }


    public static void main(String[] args) {

        University newUniversity = generateUniversity();

        for(Group group: newUniversity.educationGroups)
        {
            group.rateStudents();
            System.out.println("=======================================");
            group.printGroup();
            System.out.println();
        }

        Person randomPerson =newUniversity.educatedPersons.get(new Random().nextInt(newUniversity.educatedPersons.size()));

        System.out.println(" Information about student "+ randomPerson +" rates:");

        newUniversity.printStudentRates(randomPerson,newUniversity.findStudentsGroups(randomPerson));
    }



}

