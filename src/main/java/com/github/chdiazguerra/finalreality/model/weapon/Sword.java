package com.github.chdiazguerra.finalreality.model.weapon;

import com.github.chdiazguerra.finalreality.model.character.player.classes.Knight;
import com.github.chdiazguerra.finalreality.model.character.player.classes.Thief;

import java.util.Objects;

/**
 * A class that holds all the information of a Sword type weapon.
 *
 * @author Christian Díaz Guerra
 */
public class Sword extends AbstractWeapon {
    /**
     * Creates a weapon of Sword type with a name, a base damage and it's weight.
     *
     * @param name
     *    the weapon's name
     * @param damage
     *    the weapon's damage
     * @param weight
     *    the weapon's weight
     */
    public Sword(String name, int damage, int weight) {
        super(name, damage, weight);
    }

    @Override
    public void equippedByKnight(Knight character) {
        this.equipTo(character);
    }

    @Override
    public void equippedByThief(Thief character) {
        this.equipTo(character);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Sword)) {
            return false;
        }
        final Sword weapon = (Sword) o;
        return getDamage() == weapon.getDamage() &&
                getWeight() == weapon.getWeight() &&
                getName().equals(weapon.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDamage(), getWeight(), Sword.class);
    }
}
