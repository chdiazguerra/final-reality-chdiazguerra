package com.github.chdiazguerra.finalreality.controller.phases;

import com.github.chdiazguerra.finalreality.model.character.player.IPlayerCharacter;

public class SelectWeaponPhase extends Phase{

    @Override
    public void equipWeapon(int indexWeapon){
        controller.equipWeaponFromInventory(indexWeapon, (IPlayerCharacter) controller.getCharacterTurn());
    }

    @Override
    public void back(){
        changePhase(new PlayerTurnPhase());
    }
}
