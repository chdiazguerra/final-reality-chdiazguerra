package com.github.chdiazguerra.finalreality.controller.phases;

public class WaitingQueuePhase extends Phase{


    @Override
    public void characterAdded() {
        toBeginTurnPhase();
        controller.beginTurn();
    }

    @Override
    public boolean isWaiting(){
        return true;
    }
}
