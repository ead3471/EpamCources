package com.epam.javase01.t06;

import com.epam.javase01.t06.Exceptions.NotebookIsFullException;

import java.util.Arrays;

/**Класс демонстрирует работу спроектированного класса Notebook
 *
 *
 * Created by Freemind on 2016-08-27.
 */
public class NotebookWork {

    public static void main(String[] args) throws NotebookIsFullException {
        Notebook myNotebook=new Notebook("mrTwister");

        myNotebook.addNote(new Note("Title1","Note1 Text"));
        myNotebook.addNote(new Note("Title2","Note2 Text"));
        myNotebook.addNote(new Note("Title3","Note3 Text"));
        myNotebook.addNote(new Note("Title4","Note4 Text"));
        myNotebook.addNote(new Note("Title5","Note5 Text"));
        myNotebook.addNote(new Note("Title6","Note1 Text"));
        myNotebook.addNote(new Note("Title7","Note2 Text"));
        myNotebook.addNote(new Note("Title8","Note3 Text"));
        myNotebook.addNote(new Note("Title9","Note4 Text"));
        myNotebook.addNote(new Note("Title10","Note5 Text"));

        System.out.println("Filled notebook:");
        myNotebook.printNotes();



        myNotebook.removeNoteByTitle("ttt");
        System.out.println("Afetr removing not existing Note:");
        myNotebook.printNotes();


        myNotebook.removeNoteByTitle("Title1");
        System.out.println("After removing Note with title Title1");
        myNotebook.printNotes();

        System.out.println("After removing Note with title Title5");
        myNotebook.printNotes();

        System.out.println("After removing Note with title Title3");
        System.out.println("remove Center");
        myNotebook.printNotes();


        Note[] notes=myNotebook.showAllNotes();
        System.out.println(Arrays.toString(notes));
    }

}


