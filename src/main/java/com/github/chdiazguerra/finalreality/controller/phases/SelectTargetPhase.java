package com.github.chdiazguerra.finalreality.controller.phases;

import com.github.chdiazguerra.finalreality.model.character.ICharacter;
import com.github.chdiazguerra.finalreality.model.character.player.IPlayerCharacter;


public class SelectTargetPhase extends Phase{


    @Override
    public void attackPlayer(IPlayerCharacter playerCharacter) {
        toEndTurnPhase();
        ICharacter attacked = (ICharacter) playerCharacter;
        int initialLife = controller.getCharacterLife(attacked);
        controller.attackToPlayer(controller.getCharacterTurn(), attacked);
        int newLife = controller.getCharacterLife(attacked);
        int lifeDifference = initialLife - newLife;
        controller.getScene().attackInfo(controller.getCharacterName(controller.getCharacterTurn()),
                lifeDifference,
                controller.getCharacterName(attacked));
        controller.getScene().refreshInfoColumn(controller.getAllPlayerCharacters().indexOf(attacked));
    }

    @Override
    public void back() {
        toPlayerTurnPhase();
    }

    @Override
    public void attackEnemy(int indexEnemy) {
        toEndTurnPhase();
        int initialLife = controller.getCharacterLife(controller.getEnemy(indexEnemy));
        controller.attackToEnemy(controller.getCharacterTurn(), indexEnemy);
        int newLife = controller.getCharacterLife(controller.getEnemy(indexEnemy));
        controller.getScene().attackInfo(controller.getCharacterName(controller.getCharacterTurn()),
                initialLife-newLife,
                controller.getCharacterName(controller.getEnemy(indexEnemy)));
        controller.getScene().refreshEnemyColumns(indexEnemy);
    }


}
