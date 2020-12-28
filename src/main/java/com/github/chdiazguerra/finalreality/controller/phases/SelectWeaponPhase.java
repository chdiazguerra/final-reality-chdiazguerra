package com.github.chdiazguerra.finalreality.controller.phases;


import com.github.chdiazguerra.finalreality.model.character.player.IPlayerCharacter;
import com.github.chdiazguerra.finalreality.model.weapon.IWeapon;

public class SelectWeaponPhase extends Phase{


    @Override
    public void back() {
        toPlayerTurnPhase();
    }

    @Override
    public void equipWeapon(int indexWeapon) {
        IPlayerCharacter character = (IPlayerCharacter)controller.getCharacterTurn();
        controller.equipWeaponFromInventory(indexWeapon, character);
        controller.getScene().inventoryBox();
    }


}
