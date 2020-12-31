package com.github.chdiazguerra.finalreality.controller.phases;

public class EndTurnPhase extends Phase{


    @Override
    public void next() {
        if(controller.queueIsEmpty()){
            controller.waitingScene();
            toWaitingQueuePhase();
        }else {
            toBeginTurnPhase();
            controller.beginTurn();
            controller.turnBoxScene();
        }
    }
}
