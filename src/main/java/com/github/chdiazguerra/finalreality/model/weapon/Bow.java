package com.github.chdiazguerra.finalreality.model.weapon;

import com.github.chdiazguerra.finalreality.model.character.player.Engineer;
import com.github.chdiazguerra.finalreality.model.character.player.Thief;

import java.util.Objects;

/**
 * A class that holds all the information of a Bow type weapon.
 *
 * @author Christian Díaz Guerra
 */
public class Bow extends AbstractWeapon {

    /**
     * Creates a weapon of Bow type with a name, a base damage and it's weight.
     *
     * @param name
     *    the weapon's name
     * @param damage
     *    the weapon's damage
     * @param weight
     *    the weapon's weight
     */
    public Bow(String name, int damage, int weight) {
        super(name, damage, weight);
    }

    @Override
    public void equippedByEngineer(Engineer character) {
        this.equipTo(character, character.getIsAlive());
    }

    @Override
    public void equippedByThief(Thief character) {
        this.equipTo(character, character.getIsAlive());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bow)) {
            return false;
        }
        final Bow weapon = (Bow) o;
        return getDamage() == weapon.getDamage() &&
                getWeight() == weapon.getWeight() &&
                getName().equals(weapon.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDamage(), getWeight(), Bow.class);
    }



}
