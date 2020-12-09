package com.github.chdiazguerra.finalreality.controller.handlers;

import com.github.chdiazguerra.finalreality.controller.GameController;

import java.beans.PropertyChangeEvent;

/**
 * The handler for the event when an enemy is added to the turns queue.
 * @author Christian Díaz Guerra
 */
public class EnemyAddedToQueue implements IHandler {

    private final GameController controller;

    public EnemyAddedToQueue(GameController controller) {
        this.controller = controller;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.enemyAdded();
    }
}
