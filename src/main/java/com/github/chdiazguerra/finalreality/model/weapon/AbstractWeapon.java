package com.github.chdiazguerra.finalreality.model.weapon;

import com.github.chdiazguerra.finalreality.model.character.player.*;

/**
 * A class that holds the common behaviour of all the weapons in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Christian Díaz Guerra
 */
public abstract class AbstractWeapon implements IWeapon{

  private final String name;
  private final int damage;
  private final int weight;

  protected AbstractWeapon(final String name, final int damage, final int weight) {
    this.name = name;
    this.damage = damage;
    this.weight = weight;
  }

  /**
   * Returns the weapon's name
   */
  protected String getName() {
    return name;
  }

  @Override
  public int getDamage() {
    return damage;
  }

  @Override
  public int getWeight() {
    return weight;
  }


  /**
   * Equips the weapon to character, if this one is alive
   */
  protected void equipTo(IPlayerCharacter character, boolean isAlive) {
    if(isAlive){
      character.setEquippedWeapon(this);
    }
  }

  @Override
  public void equippedByBlackMage(BlackMage character) {
    return;
  }

  @Override
  public void equippedByEngineer(Engineer character) {
    return;
  }

  @Override
  public void equippedByKnight(Knight character) {
    return;
  }

  @Override
  public void equippedByThief(Thief character) {
    return;
  }

  @Override
  public void equippedByWhiteMage(WhiteMage character) {
    return;
  }


}
