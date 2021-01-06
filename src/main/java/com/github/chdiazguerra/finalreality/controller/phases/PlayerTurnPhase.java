package com.github.chdiazguerra.finalreality.controller.phases;

/**
 * Class that represents the player turn.
 *
 * @author Christian Diaz Guerra
 */
public class PlayerTurnPhase extends Phase{


    @Override
    public void next() {
        controller.playerTurnScene();
    }

    @Override
    public void attack() {
        toSelectTargetPhase();
    }

    @Override
    public void toInventory() {
        toSelectWeaponPhase();
    }

    @Override
    public boolean isPlayerTurn(){
        return true;
    }
}
