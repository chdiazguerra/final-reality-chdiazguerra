package com.github.chdiazguerra.finalreality.controller.phases;

/**
 * Class that holds the exception when the movement is invalid for the given phase.
 *
 * @author Christian Diaz Guerra
 */
public class InvalidMovementException extends Exception{

    /**
     * Creates the exception
     * @param message
     *      Message of the exception.
     */
    public InvalidMovementException(final String message) {
        super(message);
    }
}
