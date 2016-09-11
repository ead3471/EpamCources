package com.epam.javase02.t05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Freemind on 2016-09-09.
 */
public class University {

    private List<Student> educatedStudents=new ArrayList<>();

    private List<Group> educationGroups=new ArrayList<>();

    public University(List<Student> enteredStudents, List<Group> educationGroups)
    {
        educatedStudents.addAll(enteredStudents);
        this.educationGroups.addAll(educationGroups);
        distributeStudentsByGroups();
    }

    public static University generateUniversity()
    {
        return new University(StudentsGenerator.generateStudents(15),StudentsGenerator.generateGroups());
    }

    private void distributeStudentsByGroups()
    {
        for (Group group:educationGroups)
        {
            StudentsGenerator.loadStudentsToGroup(group,educatedStudents);
        }
    }

    public void printStudentRates(Student student, List<Group> studentGroups)
    {
        for(Group group:studentGroups)
        {
            System.out.printf("%-20s : %"+group.getRateFormat()+"\n",group.getGroupId(),group.getStudentRate(student));
        }
    }

    public List<Group> findStudentsGroups(Student student)
    {
        ArrayList<Group> resultList=new ArrayList<>();
        for(Group group:educationGroups)
        {
            if(group.isStudentInGroup(student))
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

        Student randomStudent=newUniversity.educatedStudents.get(new Random().nextInt(newUniversity.educatedStudents.size()));

        System.out.println(" Information about student "+randomStudent+" rates:");

        newUniversity.printStudentRates(randomStudent,newUniversity.findStudentsGroups(randomStudent));
    }



}

class StudentsGenerator
{
    private static final String[] names={"Jhon","Bill","Jerry","Dave","Mary","Bob","Nick","Homer","Barth","Lisa","Maggy","Marge","Montghomery","Erik","Stan","Kaile","Kenny","Fox",};
    private static final String[] lastNames={"Gray","Brown","Red","Malder","Scally","Skinner","Simpson","Berns","Smitters","Brohlovsky","Marsh","Kartman","Mackormik","Smith"};

    private final static Random rnd=new Random();

    public static List<Student> generateStudents(int studentsNumber)
    {
        ArrayList<Student>resultList=new ArrayList<>();
        while(studentsNumber>0)
        {
            resultList.add(new Student(names[rnd.nextInt(names.length)],lastNames[rnd.nextInt(lastNames.length)],studentsNumber));
            studentsNumber--;
        }
        return resultList;
    }

    public static void loadStudentsToGroup(Group group,List<Student> students)
    {
        int studentsInGroup=rnd.nextInt(students.size());
        while(studentsInGroup>0)
        {
            Student student=students.get(rnd.nextInt(students.size()));
            if(!group.isStudentInGroup(student)) {
                group.addStudent(student);
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
            resultList.add(new Group(groupSubject+"__"+groupsNumber--,groupSubject));
        }
        return resultList;

    }

    public static void main(String[] args) {
        List<Student> newGeneration=generateStudents(10);
        System.out.println(newGeneration);
        
        Group mathGroup=new Group("ELT-111",Subject.MATH);
        Group geometryGroup=new Group("ELT-112",Subject.GEOMETRY);

        loadStudentsToGroup(mathGroup,newGeneration);
        loadStudentsToGroup(geometryGroup,newGeneration);

        System.out.println(mathGroup.getStudentsInGroup());
        System.out.println(geometryGroup.getStudentsInGroup());

        System.out.println(Arrays.toString(Subject.values()));
    }
 }
