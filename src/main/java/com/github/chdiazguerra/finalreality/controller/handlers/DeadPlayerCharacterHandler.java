package com.github.chdiazguerra.finalreality.controller.handlers;

import com.github.chdiazguerra.finalreality.controller.GameController;
import com.github.chdiazguerra.finalreality.model.character.player.IPlayerCharacter;

import java.beans.PropertyChangeEvent;

/**
 * Handler for managing the dead of a player character.
 * @author Christian Díaz Guerra
 */
public class DeadPlayerCharacterHandler implements IHandler{

    private final GameController controller;

    public DeadPlayerCharacterHandler(GameController controller) {
        this.controller = controller;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.deadPlayerCharacter((IPlayerCharacter) evt.getNewValue());
    }
}
