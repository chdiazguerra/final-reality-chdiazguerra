package com.github.chdiazguerra.finalreality.model.weapon;

import java.util.Objects;

/**
 * A class that holds all the information of a Axe type weapon.
 *
 * @author Christian Díaz Guerra
 */
public class Axe extends AbstractWeapon {
    /**
     * Creates a weapon of Axe type with a name, a base damage and it's weight.
     *
     * @param name
     *    the weapon's name
     * @param damage
     *    the weapon's damage
     * @param weight
     *    the weapon's weight
     */
    public Axe(String name, int damage, int weight) {
        super(name, damage, weight);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Axe)) {
            return false;
        }
        final Axe weapon = (Axe) o;
        return getDamage() == weapon.getDamage() &&
                getWeight() == weapon.getWeight() &&
                getName().equals(weapon.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDamage(), getWeight(), Axe.class);
    }

}
