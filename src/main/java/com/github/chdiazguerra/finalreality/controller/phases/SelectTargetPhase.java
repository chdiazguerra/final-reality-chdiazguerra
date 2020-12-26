package com.github.chdiazguerra.finalreality.controller.phases;

public class SelectTargetPhase extends Phase{

    @Override
    public void attackEnemy(int indexEnemy){
        controller.attackToEnemy(controller.getCharacterTurn(), indexEnemy);
    }

    @Override
    public void attackPlayer(int indexPlayer){
        controller.attackToPlayer(controller.getCharacterTurn(), indexPlayer);
    }

    @Override
    public void back(){
        changePhase(new PlayerTurnPhase());
    }

    @Override
    public void toEndTurn(){
        changePhase(new EndTurnPhase());
    }
}
