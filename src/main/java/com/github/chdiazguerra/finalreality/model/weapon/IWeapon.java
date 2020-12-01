package com.github.chdiazguerra.finalreality.model.weapon;

import com.github.chdiazguerra.finalreality.model.character.ICharacter;
import com.github.chdiazguerra.finalreality.model.character.player.*;

/**
 * This represents a weapon from the game.
 * A weapon can be equipped by player's characters.
 *
 * @author Christian Díaz Guerra
 */

public interface IWeapon {

    /**
     * Returns the weapon weight
     */
    int getWeight();

    /**
     * Returns the weapon's damage
     */
    int getDamage();

    /**
     * Equips the weapon to character, if it applies
     */
    void equippedByBlackMage(BlackMage character);

    /**
     * Equips the weapon to character, if it applies
     */
    void equippedByEngineer(Engineer character);

    /**
     * Equips the weapon to character, if it applies
     */
    void equippedByKnight(Knight character);

    /**
     * Equips the weapon to character, if it applies
     */
    void equippedByThief(Thief character);

    /**
     * Equips the weapon to character, if it applies
     */
    void equippedByWhiteMage(WhiteMage character);

}
