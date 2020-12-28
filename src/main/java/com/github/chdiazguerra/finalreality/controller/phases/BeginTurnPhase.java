package com.github.chdiazguerra.finalreality.controller.phases;

public class BeginTurnPhase extends Phase{


    @Override
    public void toEnemyTurn() {
        toEnemyTurnPhase();
    }

    @Override
    public void toPlayerTurn() {
       toPlayerTurnPhase();
    }
}
