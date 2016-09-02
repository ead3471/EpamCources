package com.epam.javase01.t06;

import com.epam.javase01.t06.Exceptions.NoteNotFoundException;
import com.epam.javase01.t06.Exceptions.NotebookIsFullException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**Simple class implements Notebook functionality
 */

class Notebook {
    private String ownerName;
    private int noteBookMaxCapacity;
    private Note[] notes;

    private int lastNoteIndex =-1;

    private static final int DEFAULT_NOTEBOOK_CAPACITY=15;
    private static final int DEFAULT_STARTING_CAPACITY =10;


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
        if(noteBookMaxCapacity< DEFAULT_STARTING_CAPACITY)
        {
            notes=new Note[noteBookMaxCapacity];
        }
        else
        {
            notes=new Note[DEFAULT_STARTING_CAPACITY];
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
            notes[++lastNoteIndex]=newNote;
        else
            throw new NotebookIsFullException("Notebook is full");
        resizeNotes();
    }

    /**
     *Remove all Note from notebook with specified title
     * @return true if at least ont note with specified title is found and removed and false otherwise
     */
    public boolean removeNoteByTitle(String noteTitle)
    {
        boolean isOneDeleted=false;
        for(int i = 0; i<=lastNoteIndex; i++) {
            if (notes[i].getNoteTitle().equals(noteTitle)) {
                shiftNotesToLeft(i);
                isOneDeleted=true;
            }
        }
        return isOneDeleted;
    }


    /**Method return List of notes with specified title
     *
     * @return <code>List<Note></code> of Note with specified note title
     */
    public List<Note> getAllNotesByTitle(String noteTitle)
    {
        List<Note> resultList=new ArrayList();
        for(int i=0;i<=lastNoteIndex;i++)
        {
            if(notes[i].getNoteTitle().equals(noteTitle))
            {
                resultList.add(notes[i]);
            }
        }

        return resultList;
    }

    /**Method return first Notebook Note with specified title
     *
     * @return  Note with specified note title
     * @throws NoteNotFoundException in case of Note with specified title is not found
     */
    public Note getNoteByTitle(String noteTitle) throws  NoteNotFoundException
    {
        for(int i=0;i<=lastNoteIndex;i++)
        {
            if(notes[i].getNoteTitle().equals(noteTitle))
            {
                return notes[i];
            }
        }

        throw new NoteNotFoundException("Element with title "+noteTitle+" not found");
    }

    /**Method return Note with specified index
     *
     * @return  Note with specified index
     * @throws IllegalArgumentException in case of specified noteIndex out of bounds
     */
    public Note getNoteByIndex(int noteIndex)
    {
        if(noteIndex>lastNoteIndex|| noteIndex<0)
            throw new IllegalArgumentException("Specified Note Index is out of Notebook size");

        return notes[noteIndex];
    }



    /**Removes Note at the specified position
       */
    public void removeNoteByIndex(int noteIndex)
    {
     shiftNotesToLeft(noteIndex);
    }


    /**
     *Print all notes
     */
    public void printNotes()
    {
        System.out.println("=============Notebook content start===============");
        for(int i=0;i<=lastNoteIndex;i++)
        {
            System.out.println(notes[i]);
        }
        System.out.println("=============Notebook content end===============");
    }

    /**
     *Edit note with specified title
     * @return true if note with specified title is found and edited, false otherwise
     */
    public  boolean editNote(String noteTitle,String newText)
    {
        for (int i = 0; i <=lastNoteIndex ; i++) {
            if(notes[i].getNoteTitle().equals(noteTitle))
            {
                notes[i].setNoteText(newText);
                return true;
            }
        }
        return false;
    }

    /**
     *Return notes loaded into the notebook
     * @return Note[] array of notes
     */
    public Note[] getAllNotes() {
        return Arrays.copyOf(notes,lastNoteIndex+1);
    }

    /**Return number of Notes loaded into this Notebook instance
     */
  public int size()
  {
      return lastNoteIndex+1;
  }

  private void shiftNotesToLeft(int i) {
        for(;i<lastNoteIndex;i++)
        {
            notes[i]=notes[i+1];
        }
        notes[lastNoteIndex--]=null;
    }

    /**Return maximum number of Notes in this Notebook instance
     */
    public int getNoteBookMaxCapacity()
    {
        return noteBookMaxCapacity;
    }
    private void resizeNotes()
    {
        if(lastNoteIndex ==notes.length-1&& lastNoteIndex <noteBookMaxCapacity)
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
