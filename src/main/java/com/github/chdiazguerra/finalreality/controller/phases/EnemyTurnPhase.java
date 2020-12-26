package com.github.chdiazguerra.finalreality.controller.phases;

public class EnemyTurnPhase extends Phase{

    public EnemyTurnPhase(){
        changePhase(new SelectTargetPhase());
    }
}
