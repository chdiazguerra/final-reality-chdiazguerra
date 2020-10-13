package com.github.chdiazguerra.finalreality.model.weapon;

import java.util.Objects;

/**
 * A class that holds all the information of a weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author Christian Díaz Guerra
 */
public abstract class AbstractWeapon implements IWeapon{

  private final String name;
  private final int damage;
  private final int weight;

  /**
   * Creates a weapon with a name, a base damage and weight.
   */
  protected AbstractWeapon(final String name, final int damage, final int weight) {
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

  @Override
  public int getWeight() {
    return weight;
  }

}
