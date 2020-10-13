package com.github.chdiazguerra.finalreality.model.character.player;

import com.github.chdiazguerra.finalreality.model.character.ICharacter;
import com.github.chdiazguerra.finalreality.model.weapon.AbstractWeapon;
import com.github.chdiazguerra.finalreality.model.weapon.IWeapon;

public interface IPlayerCharacter extends ICharacter {

    /**
     * Equip a weapon for this character, depending on the type of the weapon and class of the character
     * @param weapon
     * @return
     */
    void equip(IWeapon weapon);


    /**
     * Return this character's equipped weapon.
     */
    IWeapon getEquippedWeapon();
}
