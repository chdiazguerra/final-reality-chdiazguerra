package com.github.chdiazguerra.finalreality.controller.phases;

public class EndTurnPhase extends Phase{


    @Override
    public void next() {
        if(controller.queueIsEmpty()){
            controller.getScene().waitingText();
            toWaitingQueuePhase();
        }else {
            controller.beginTurn();
            controller.toTurnBox();
        }
    }
}
