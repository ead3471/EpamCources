package com.epam.javase05.t01.exceptions;

/**
 * Created by Freemind on 2016-09-25.
 */
public class LocationNotFoundException extends LocationException {
    public LocationNotFoundException(String locationPath) {
       super(locationPath);
    }
}
