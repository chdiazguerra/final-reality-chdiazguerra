package com.github.chdiazguerra.finalreality.controller.phases;

/**
 * Class that represents the end turn phase.
 *
 * @author Christian Diaz Guerra
 */
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

    @Override
    public void allPlayerCharactersDead(){
        toLostGamePhase();
    }

    @Override
    public void allEnemiesDead(){
        toWinGamePhase();
    }

    @Override
    public boolean isEndTurn(){
        return true;
    }
}
