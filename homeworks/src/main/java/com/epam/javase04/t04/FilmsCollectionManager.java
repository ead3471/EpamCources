package com.epam.javase04.t04;

import com.epam.javase02.t05.Person;

import java.io.*;
import java.util.*;

/**
 * Created by Freemind on 2016-09-25.
 * Дана коллекция фильмов, содержащая информацию об актерах, снимавшихся в главных ролях (один актер мог сниматься и в нескольких фильмах).
 * Необходимо написать приложение, позволяющее при запуске восстанавливать коллекцию фильмов, позволять ее модифицировать,
 * а по завершении работы приложения – сохранять (в файл). Для восстановления/сохранения
 * коллекции использовать сериализацию/десериализацию.
 *
 */
public class FilmsCollectionManager implements Serializable {

    private HashMap<String,HashSet<Person>> moviesMap=new HashMap<>();


    public static FilmsCollectionManager createFromFile(String fileName) throws IOException, ClassNotFoundException {
        try(ObjectInputStream inputStream =new ObjectInputStream(new FileInputStream(fileName)))
        {
            return(FilmsCollectionManager)(inputStream.readObject());
        }

    }

    public void addMovie(String movieName,HashSet<Person> actorsList)
    {
        if(moviesMap.containsKey(movieName))
        {
            moviesMap.get(movieName).addAll(actorsList);
        }
        else
            moviesMap.put(movieName,actorsList);

    }
    public void addMovie(String movieName)
    {
        if(!moviesMap.containsKey(movieName))
        {
            moviesMap.put(movieName,new HashSet<Person>());
        }
    }

    public void addActor(String movieName,Person actor)
    {
       addMovie(movieName);
        moviesMap.get(movieName).add(actor);
    }

    public void saveToFile(String fileName) throws IOException {
        try(ObjectOutputStream outputStream=new ObjectOutputStream(new FileOutputStream(fileName)))
        {
            outputStream.writeObject(this);
        }
    }

    public Set<String> getMovies()
    {
        return moviesMap.keySet();
    }

    public Set<Person> getMoviesActors(String movieName)
    {
     Set<Person> actors=moviesMap.get(movieName);
        return actors==null?Collections.emptySet():actors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmsCollectionManager that = (FilmsCollectionManager) o;

        return moviesMap.equals(that.moviesMap);

    }

    @Override
    public int hashCode() {
        return moviesMap.hashCode();
    }
}
