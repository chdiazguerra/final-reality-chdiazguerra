package com.github.chdiazguerra.finalreality.controller.handlers;

import com.github.chdiazguerra.finalreality.controller.GameController;
import com.github.chdiazguerra.finalreality.model.character.Enemy;

import java.beans.PropertyChangeEvent;

/**
 * Handler for managing the dead of an enemy.
 * @author Christian Díaz Guerra
 */
public class DeadEnemyHandler implements IHandler{
    private final GameController controller;

    public DeadEnemyHandler(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.deadEnemy((Enemy) evt.getNewValue());
    }
}
