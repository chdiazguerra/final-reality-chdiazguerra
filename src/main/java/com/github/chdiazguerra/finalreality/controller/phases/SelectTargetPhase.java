package com.github.chdiazguerra.finalreality.controller.phases;

import com.github.chdiazguerra.finalreality.model.character.ICharacter;
import com.github.chdiazguerra.finalreality.model.character.player.IPlayerCharacter;


public class SelectTargetPhase extends Phase{


    @Override
    public void attackPlayer(IPlayerCharacter playerCharacter) {
        toEndTurnPhase();
        ICharacter attacked = (ICharacter) playerCharacter;
        controller.attackToPlayer(controller.getCharacterTurn(), attacked);
        controller.attackInfoScene(attacked);
        controller.refreshInfoColumnScene(attacked);
    }

    @Override
    public void back() {
        toPlayerTurnPhase();
    }

    @Override
    public void attackEnemy(int indexEnemy) {
        toEndTurnPhase();
        controller.attackToEnemy(controller.getCharacterTurn(), indexEnemy);
        controller.attackInfoScene( controller.getEnemy(indexEnemy));
        controller.refreshEnemyColumnsScene(indexEnemy);
    }


}
