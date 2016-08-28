package com.epam.javase01.t06.Exceptions;

/**
 * Created by Freemind on 2016-08-28.
 */
public class NoteNotFoundException extends NotebookException
{
    public NoteNotFoundException(String message){
        super(message);
    }
}
