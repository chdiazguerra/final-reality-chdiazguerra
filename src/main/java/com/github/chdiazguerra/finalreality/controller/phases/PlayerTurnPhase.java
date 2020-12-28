package com.github.chdiazguerra.finalreality.controller.phases;

public class PlayerTurnPhase extends Phase{


    @Override
    public void next() {
        controller.getScene().playerTurnBox();
    }

    @Override
    public void attack() {
        toSelectTargetPhase();
    }

    @Override
    public void toInventory() {
        toSelectWeaponPhase();
    }
}
