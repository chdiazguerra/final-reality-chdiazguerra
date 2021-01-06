package com.github.chdiazguerra.finalreality.controller.phases;


import com.github.chdiazguerra.finalreality.model.character.player.IPlayerCharacter;


/**
 * Class that represents the selecting weapon state.
 *
 * @author Christian Diaz Guerra
 */
public class SelectWeaponPhase extends Phase{


    @Override
    public void back() {
        toPlayerTurnPhase();
    }

    @Override
    public void equipWeapon(int indexWeapon) {
        IPlayerCharacter character = (IPlayerCharacter)controller.getCharacterTurn();
        controller.equipWeaponFromInventory(indexWeapon, character);
        controller.inventoryScene();
    }

    @Override
    public boolean isSelectingWeapon(){
        return true;
    }


}
