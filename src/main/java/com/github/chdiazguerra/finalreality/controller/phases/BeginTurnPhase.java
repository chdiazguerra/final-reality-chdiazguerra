package com.github.chdiazguerra.finalreality.controller.phases;

/**
 * Class that represents the beginning turn phase.
 *
 * @author Chritian Diaz Guerra
 */
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
