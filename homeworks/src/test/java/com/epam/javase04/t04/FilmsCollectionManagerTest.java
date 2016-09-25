package com.epam.javase04.t04;

import com.epam.javase02.t05.Person;
import com.epam.javase02.t05.PersonsGenerator;
import com.epam.javase02.t05.University;
import org.junit.Test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.assertTrue;


/**
 * Created by Freemind on 2016-09-25.
 */
public class FilmsCollectionManagerTest {


    @Test
    public void serialisationTest() throws IOException, ClassNotFoundException {

        FilmsCollectionManager filmsManager=new FilmsCollectionManager();
        String collectionSavingFilePath="src/test/resources/filmsCollections.films";


        filmsManager.addMovie("The Matrix");
        filmsManager.addMovie("Godzilla");
        filmsManager.addMovie("Tom and Jerry");
        filmsManager.addMovie("Top Gun");
        filmsManager.addMovie("HOUSE m.d.");
        fillMoviesByRandomPersons(filmsManager);

        filmsManager.saveToFile(collectionSavingFilePath);

        FilmsCollectionManager uploadedFilmsCollectionManager=FilmsCollectionManager.createFromFile(collectionSavingFilePath);
        assertTrue(filmsManager.equals(uploadedFilmsCollectionManager));


    }

    private void fillMoviesByRandomPersons(FilmsCollectionManager filmsManager)
    {
        Random rnd=new Random();
        List<Person> actorsList= PersonsGenerator.generatePersons(100);
        Set<String> moviesSet=filmsManager.getMovies();
        for(String movie:moviesSet)
        {
            Collections.shuffle(actorsList);
            int actorsNumber=rnd.nextInt(50);
            for(;actorsNumber>0;actorsNumber--)
            {
                filmsManager.addActor(movie,actorsList.get(actorsNumber));
            }
        }
    }




}