package com.github.chdiazguerra.finalreality.controller.phases;

/**
 * Class that represents the waiting for characters in the queue.
 *
 * @author Christian Diaz Guerra
 */
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
