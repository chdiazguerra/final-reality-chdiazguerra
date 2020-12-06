package com.github.chdiazguerra.finalreality.model.character.player;

import com.github.chdiazguerra.finalreality.model.weapon.IWeapon;

/**
 * This represents a player character from the game, controlled by the user.
 *
 * @author Christian Díaz Guerra
 */
public interface IPlayerCharacter {

    /**
     * Equip a weapon for this character if it is alive,
     * depending on the type of the weapon and class of the character
     */
    void equip(IWeapon weapon);


    /**
     * Return this character's equipped weapon.
     */
    IWeapon getEquippedWeapon();

    /**
     * Set the character's weapon
     */
    void setEquippedWeapon(IWeapon weapon);


}
