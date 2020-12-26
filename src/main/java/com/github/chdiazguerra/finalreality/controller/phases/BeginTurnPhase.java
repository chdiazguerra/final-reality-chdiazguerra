package com.github.chdiazguerra.finalreality.controller.phases;

public class BeginTurnPhase extends Phase{

    public BeginTurnPhase(){
        controller.beginTurn();
    }

    @Override
    public void toPlayerTurn(){
        changePhase(new PlayerTurnPhase());
    }

    @Override
    public void toEnemyTurn(){
        changePhase(new EnemyTurnPhase());
    }
}
