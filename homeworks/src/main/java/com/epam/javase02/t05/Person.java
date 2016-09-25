package com.epam.javase02.t05;

import java.io.Serializable;

/**
 * Created by Freemind on 2016-09-09.
 */
public class Person implements Serializable {

    private final String firstName;
    private final String lastName;
    private final long id;

    public Person(String firstName, String lastName, long id)
    {
        this.firstName=firstName;
        this.lastName=lastName;
        this.id=id;
    }


    @Override
    public String toString() {
        return firstName+" "+lastName+"(id:"+id+")";   }

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
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return (getId() == person.getId());


    }

    @Override
    public int hashCode() {
        int result = getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + (int) (getId() ^ (getId() >>> 32));
        return result;
    }
}
