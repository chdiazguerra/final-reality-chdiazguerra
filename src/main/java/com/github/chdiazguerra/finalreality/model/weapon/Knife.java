package com.github.chdiazguerra.finalreality.model.weapon;

import com.github.chdiazguerra.finalreality.model.character.player.BlackMage;
import com.github.chdiazguerra.finalreality.model.character.player.Knight;

import java.util.Objects;

/**
 * A class that holds all the information of a Knife type weapon.
 *
 * @author Christian Díaz Guerra
 */
public class Knife extends AbstractWeapon {
    /**
     * Creates a weapon of Knife type with a name, a base damage and it's weight.
     *
     * @param name
     *    the weapon's name
     * @param damage
     *    the weapon's damage
     * @param weight
     *    the weapon's weight
     */
    public Knife(String name, int damage, int weight) {
        super(name, damage, weight);
    }

    @Override
    public void equippedByBlackMage(BlackMage character) {
        this.equipTo(character);
    }

    @Override
    public void equippedByKnight(Knight character) {
        this.equipTo(character);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Knife)) {
            return false;
        }
        final Knife weapon = (Knife) o;
        return getDamage() == weapon.getDamage() &&
                getWeight() == weapon.getWeight() &&
                getName().equals(weapon.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDamage(), getWeight(), Knife.class);
    }
}
