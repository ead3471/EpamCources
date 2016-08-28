package com.epam.javase01;

import java.util.Arrays;

/**Класс демонстрирует работу спроектированного класса Notebook
 *
 *
 * Created by Freemind on 2016-08-27.
 */
public class Ex6 {

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

        myNotebook.printNotes();



        myNotebook.removeNoteByTitle("ttt");
        System.out.println("remove not exist:");
        myNotebook.printNotes();


        myNotebook.removeNoteByTitle("Title1");
        System.out.println("remove First");
        myNotebook.printNotes();

        myNotebook.removeNoteByTitle("Title5");
        System.out.println("remove Last");
        myNotebook.printNotes();

        myNotebook.removeNoteByTitle("Title3");
        System.out.println("remove Center");
        myNotebook.printNotes();


        Note[] notes=myNotebook.showAllNotes();

        System.out.println(Arrays.toString(notes));
    }

}


/**Simple class implements Notebook functionality
 *
 *
 *
 *
 */

class Notebook {
    private String ownerName;
    private int noteBookMaxCapacity;
    private Note[] notes;

    private int lastNoteIndex =0;

    private static final int DEFAULT_NOTEBOOK_CAPACITY=15;
    private static final int DEFAULT_STATRING_CAPACITY=10;


    /**
     * Constructs an empty notebook with the notebook owner data and maximum notebook capacity.
     *
     * @param  ownerName  name of the notebook owner
     * @param  noteBookMaxCapacity maximum notebook capacity
     */
    public Notebook(String ownerName, int noteBookMaxCapacity)
    {
        this.ownerName=ownerName;
        this.noteBookMaxCapacity=noteBookMaxCapacity;
        if(noteBookMaxCapacity<DEFAULT_STATRING_CAPACITY)
        {
            notes=new Note[noteBookMaxCapacity];
        }
        else
        {
            notes=new Note[DEFAULT_STATRING_CAPACITY];
        }

    }

    /**
     * Constructs an empty notebook with the notebook owner name
     *
     * @param  ownerName  name of the notebook owner
     */
    public Notebook(String ownerName)
    {
        this(ownerName,DEFAULT_NOTEBOOK_CAPACITY);
    }

    /**
     * Returns the name of this notebook owner
     */
    public String getOwnerName() {
        return ownerName;
    }


    /**
     *Add new Note at the ent of notebook
     * @throws NotebookIsFullException if notebook notes count has riched maximum notebook notes capacity
     */
    public void addNote(Note newNote) throws NotebookIsFullException
    {
        if (lastNoteIndex <notes.length)
            notes[lastNoteIndex++]=newNote;
        else
            throw new NotebookIsFullException("Notebook is full");
        resizeNotes();
    }

    /**
     *Remove all Note from notebook with specified title
     * @return true if note with specified title is found and removed and false otherwise
     */
    public boolean removeNoteByTitle(String noteTitle)
    {
        boolean isOnedeleted=false;
        for(int i = 0; i<lastNoteIndex; i++) {
            if (notes[i].getNoteTitle().equals(noteTitle)) {
                shiftNotesToLeft(i);
                isOnedeleted=true;
            }
        }
        return isOnedeleted;
    }


    /**
     *Print all notes
     */
    public void printNotes()
    {
        System.out.println("=============Notebook start===============");
        for(int i=0;i<lastNoteIndex;i++)
        {
            System.out.println(notes[i]);
        }
        System.out.println("=============Notebook end===============");
    }


    /**
     *Edit note with specified title
     * @return true if note with specified title is found and edited, false otherwise
     */
    public  boolean editNote(String noteTitle,String newText)
    {
        for (int i = 0; i <lastNoteIndex ; i++) {
            if(notes[i].getNoteTitle().equals(noteTitle))
            {
                notes[i].setNoteText(newText);
                return true;
            }
        }
        return false;
    }

    /**
     *Return array of notes in the notebook
     */
    public Note[] showAllNotes()
    {
        return Arrays.copyOf(notes,lastNoteIndex);

    }
    private void shiftNotesToLeft(int i)
    {
        for(;i<lastNoteIndex-1;i++)
        {
            notes[i]=notes[i+1];
        }
        notes[--lastNoteIndex]=null;
    }

    private void resizeNotes()
    {
        if(lastNoteIndex ==notes.length&& lastNoteIndex <noteBookMaxCapacity)
        {
            int newNotesLength=notes.length+notes.length/2;
            if(newNotesLength>noteBookMaxCapacity){
                newNotesLength=noteBookMaxCapacity;
            }
            Note[] resizedNotes=new Note[newNotesLength];
            System.arraycopy(notes,0,resizedNotes,0,notes.length);
        }
    }
}


/**Simple class that represents single note in the notebook
 *
 * Note consist from note title and note text
 */
class Note{
    private String noteTitle;
    private String noteText;

    /**
     * Constructs a note with specified title and text
     */
    Note(String noteTitle,String noteText)
    {
        this.noteTitle=noteTitle;
        this.noteText=noteText;
    }


    /**
     * Constructs a note with specified title without text
     */
    Note(String noteTitle)
    {
        this(noteTitle,"Empty");
    }

    /**
     * Return note text
     */
    public String getNoteText(){
        return  noteText;
    }

    /**
     * Return note title
     */
    public String getNoteTitle() {
        return noteTitle;
    }



    /**
     * Set note text
     */
    public void setNoteText(String newText)
    {
        noteText=newText;
    }


    /**
     * return String representation of note
     */
    public String toString()
    {
        return noteTitle+"     Note Text:"+noteText;
    }
}

class NotebookException extends  Exception
{
    public NotebookException(String message){
        super(message);
    }

}

class NotebookIsFullException extends  NotebookException
{
    public NotebookIsFullException(String message){
        super(message);
    }

}
class NotebookIsEmptyException extends NotebookException
{
    public NotebookIsEmptyException(String message){
        super(message);
    }

}

class NoteNotFoundException extends NotebookException
{
    public NoteNotFoundException(String message){
        super(message);
    }
}