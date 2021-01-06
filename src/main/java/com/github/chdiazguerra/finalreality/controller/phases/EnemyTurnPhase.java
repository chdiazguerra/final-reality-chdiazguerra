package com.github.chdiazguerra.finalreality.controller.phases;

/**
 * Class that represents the enemy turn.
 *
 * @author Christian Diaz Guerra
 */
public class EnemyTurnPhase extends Phase{


    @Override
    public void next() {
        toSelectTargetPhase();
        controller.enemyChoosingScene();
    }

    @Override
    public boolean isEnemyTurn(){
        return true;
    }
}
