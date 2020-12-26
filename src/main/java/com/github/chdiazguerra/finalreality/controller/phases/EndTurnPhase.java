package com.github.chdiazguerra.finalreality.controller.phases;

public class EndTurnPhase extends Phase{

    @Override
    public void toBeginTurn(){
        changePhase(new BeginTurnPhase());
    }

    @Override
    public void tryBeginTurn(){
        if(controller.queueIsEmpty()){
            changePhase(new WaitingQueuePhase());
        }
        toBeginTurn();
    }

}
