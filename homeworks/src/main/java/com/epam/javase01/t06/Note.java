package com.epam.javase01.t06;

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
     * Constructs a note with specified title.
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
    @SuppressWarnings("WeakerAccess")
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
     * @return String representation of note
     */
    public String toString()
    {
        return noteTitle+"     Note Text:"+noteText;
    }
}
