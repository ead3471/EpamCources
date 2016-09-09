package com.epam.javase02.t05;

/**
 * Created by Freemind on 2016-09-09.
 */
public class Student {

    private final String firstName;
    private final String lastName;
    private final long id;

    public Student(String firstName, String lastName,long id)
    {
        this.firstName=firstName;
        this.lastName=lastName;
        this.id=id;
    }


    @Override
    public String toString() {
        return firstName+" "+lastName+" id:"+id;   }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        return getId() == student.getId();

    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }
}
