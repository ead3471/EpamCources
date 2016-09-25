package com.epam.javase02.t05;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Freemind on 2016-09-20.
 */
public class UniversityTest {
    @Test
    public void generateUniversityTest(){

        University university=University.generateUniversity();

        List<Group> univercityGroups=university.getUniversityGroups();
        assertThat(univercityGroups.size()>0,is(true));

        for (Group group:univercityGroups) {
            assertThat(group.getStudentsInGroup().size()>0,is(true));
        }
    }

    @Test
    public void findStudentsGroupsTest(){

        University university=University.generateUniversity();

        List<Person> studentsInUniversity=university.getUniversityStudents();
        List<Group> groupsInUniversity=university.getUniversityGroups();

        for (Person person :studentsInUniversity) {
            List<Group> studentsGroups = university.findStudentsGroups(person);

            for (Group universityGroup : groupsInUniversity) {
                assertThat(universityGroup.isStudentInGroup(person),is(studentsGroups.contains(universityGroup)));
            }
        }
    }



}