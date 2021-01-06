package com.github.chdiazguerra.finalreality.controller.handlers;

import com.github.chdiazguerra.finalreality.controller.GameController;

import java.beans.PropertyChangeEvent;

/**
 * Handler for managing the damage received for an attacked character.
 *
 * @author Christian Diaz Guerra
 */
public class DamageReceivedHandler implements IHandler{
    private final GameController controller;


    public DamageReceivedHandler(GameController controller) {
        this.controller = controller;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.setDamageReceived((Integer) evt.getOldValue()- (Integer) evt.getNewValue());
    }
}
