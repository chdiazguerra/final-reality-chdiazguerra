package com.github.chdiazguerra.finalreality.model.weapon;

public class Sword extends Weapon{
    /**
     * Creates a weapon with a name, a base damage, speed and it's type.
     *
     * @param name
     * @param damage
     * @param weight
     * @see WeaponType
     */
    public Sword(String name, int damage, int weight) {
        super(name, damage, weight, WeaponType.SWORD);
    }
}
