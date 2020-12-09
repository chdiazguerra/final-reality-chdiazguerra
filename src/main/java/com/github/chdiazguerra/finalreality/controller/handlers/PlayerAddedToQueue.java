package com.github.chdiazguerra.finalreality.controller.handlers;

import com.github.chdiazguerra.finalreality.controller.GameController;

import java.beans.PropertyChangeEvent;

/**
 * The handler for the event when a player character is added to the turns queue.
 * @author Christian Díaz Guerra
 */
public class PlayerAddedToQueue implements IHandler{

    private final GameController controller;

    public PlayerAddedToQueue(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.playerAdded();
    }
}
