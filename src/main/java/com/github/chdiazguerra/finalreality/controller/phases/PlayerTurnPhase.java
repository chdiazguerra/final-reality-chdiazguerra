package com.github.chdiazguerra.finalreality.controller.phases;

public class PlayerTurnPhase extends Phase{

    @Override
    public void toSelectWeapon(){
        changePhase(new SelectWeaponPhase());
    }

    @Override
    public void toSelectTarget(){
        changePhase(new SelectTargetPhase());
    }
}
