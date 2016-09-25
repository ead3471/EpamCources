package com.epam.javase05.t01.exceptions;

/**
 * Created by Freemind on 2016-09-25.
 */
public class LocationIsNotDirectoryException extends LocationException {
    public LocationIsNotDirectoryException(String locationPath) {
        super(locationPath);
    }
}
