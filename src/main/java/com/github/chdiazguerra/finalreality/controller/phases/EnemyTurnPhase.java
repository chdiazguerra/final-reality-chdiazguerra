package com.github.chdiazguerra.finalreality.controller.phases;

public class EnemyTurnPhase extends Phase{


    @Override
    public void next() {
        toSelectTargetPhase();
        controller.getScene().enemyChoosing();
    }
}