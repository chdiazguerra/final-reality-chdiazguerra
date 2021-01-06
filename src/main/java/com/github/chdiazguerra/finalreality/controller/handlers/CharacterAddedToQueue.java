package com.github.chdiazguerra.finalreality.controller.handlers;

import com.github.chdiazguerra.finalreality.controller.GameController;

import java.beans.PropertyChangeEvent;

/**
 * The handler for the event when a character is added to the turns queue.
 * @author Christian Díaz Guerra
 */
public class CharacterAddedToQueue implements IHandler {

    private final GameController controller;

    public CharacterAddedToQueue(GameController controller) {
        this.controller = controller;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.characterAdded();
    }
}
