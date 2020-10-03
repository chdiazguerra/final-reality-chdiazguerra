package com.github.chdiazguerra.finalreality.model.weapon;

import java.util.Objects;

/**
 * A class that holds all the information of a weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author Christian Díaz Guerra
 */
public class Weapon {

  private final String name;
  private final int damage;
  private final int weight;

  /**
   * Creates a weapon with a name, a base damage, speed and it's type.
   *
   */
  public Weapon(final String name, final int damage, final int weight) {
    this.name = name;
    this.damage = damage;
    this.weight = weight;
  }

  protected String getName() {
    return name;
  }

  protected int getDamage() {
    return damage;
  }

  public int getWeight() {
    return weight;
  }

}
