package com.github.chdiazguerra.finalreality.model.weapon;

import com.github.chdiazguerra.finalreality.model.character.player.BlackMage;
import com.github.chdiazguerra.finalreality.model.character.player.Thief;
import com.github.chdiazguerra.finalreality.model.character.player.WhiteMage;

import java.util.Objects;

/**
 * A class that holds all the information of a Staff type weapon.
 *
 * @author Christian Díaz Guerra
 */
public class Staff extends AbstractWeapon {
    /**
     * Creates a weapon of Staff type with a name, a base damage and it's weight.
     *
     * @param name
     *    the weapon's name
     * @param damage
     *    the weapon's damage
     * @param weight
     *    the weapon's weight
     */
    public Staff(String name, int damage, int weight) {
        super(name, damage, weight);
    }

    @Override
    public void equippedByBlackMage(BlackMage character) {
        this.equipTo(character, character.getIsAlive());
    }

    @Override
    public void equippedByThief(Thief character) {
        this.equipTo(character, character.getIsAlive());
    }

    @Override
    public void equippedByWhiteMage(WhiteMage character) {
        this.equipTo(character, character.getIsAlive());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Staff)) {
            return false;
        }
        final Staff weapon = (Staff) o;
        return getDamage() == weapon.getDamage() &&
                getWeight() == weapon.getWeight() &&
                getName().equals(weapon.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDamage(), getWeight(), Staff.class);
    }
}
