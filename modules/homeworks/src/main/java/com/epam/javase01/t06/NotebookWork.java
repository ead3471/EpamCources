package com.epam.javase01.t06;

import com.epam.javase01.t06.Exceptions.NotebookIsFullException;

import java.util.Arrays;

/**Класс демонстрирует работу спроектированного класса Notebook
 *
 * Спроектировать и разработать классы Запись в блокноте и Блокнот (записи блокнота хранятся в массиве).
 * Реализовать методы:
 *                  Добавить запись,
 *                  Удалить запись,
 *                  Редактировать запись,
 *                  Посмотреть все записи.
 * Написать для данного кода javadoc-документацию.
 */
public class NotebookWork {

    public static void main(String[] args) throws NotebookIsFullException {
        Notebook myNotebook=new Notebook("mrTwister");
        loadNotesIntoNotebook(myNotebook,10);
        System.out.println("Notebook capacity:"+myNotebook.getNoteBookMaxCapacity());

        myNotebook.printNotes();
        System.out.println("Notebook size:"+myNotebook.size());

        myNotebook.removeNoteByIndex(0);
        myNotebook.removeNoteByIndex(myNotebook.size()-1);
        System.out.println("\nAfter last and first Note deletion dy Index size is "+myNotebook.size());
        myNotebook.printNotes();

        myNotebook.removeNoteByTitle("Title 5");
        System.out.println("\nAfter  Note by Title deletion size is "+myNotebook.size());
        myNotebook.printNotes();

        removeAllNotesFromNotebook(myNotebook);
        System.out.println("\nAfter All Note deletion size is "+myNotebook.size());
        myNotebook.printNotes();

        myNotebook.addNote(new Note("New Title_1"));
        myNotebook.addNote(new Note("New Title_2"));
        myNotebook.addNote(new Note("New Title_3"));
        System.out.println("\nAfter three new notes adding size is "+myNotebook.size());
        myNotebook.printNotes();


        myNotebook.editNote("New Title_2","New Text after editing");
        System.out.println("\nAfter Note editing size is "+myNotebook.size());
        myNotebook.printNotes();

        Note[] notes=myNotebook.getAllNotes();
        System.out.println(Arrays.toString(notes));
    }

    private  static void loadNotesIntoNotebook(Notebook targetNotebook,int notesQuantity) throws NotebookIsFullException {
        for(int i=0;i<notesQuantity;i++)
        {
            targetNotebook.addNote(new Note("Title "+i,"Text_"+i));
        }
    }

    private static void removeAllNotesFromNotebook(Notebook targetNotebook)
    {
        while(targetNotebook.size()>0)
        {
            targetNotebook.removeNoteByIndex(targetNotebook.size()-1);
        }
    }



}




