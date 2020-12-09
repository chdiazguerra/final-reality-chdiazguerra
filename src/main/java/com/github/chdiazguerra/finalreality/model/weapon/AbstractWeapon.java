package com.github.chdiazguerra.finalreality.model.weapon;

import com.github.chdiazguerra.finalreality.model.character.player.*;
import com.github.chdiazguerra.finalreality.model.character.player.classes.*;

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
   * Equips the weapon to character
   */
  protected void equipTo(IPlayerCharacter character) {
    character.setEquippedWeapon(this);
  }

  @Override
  public void equippedByBlackMage(BlackMage character) {
    //do nothing
  }

  @Override
  public void equippedByEngineer(Engineer character) {
    //do nothing
  }

  @Override
  public void equippedByKnight(Knight character) {
    //do nothing
  }

  @Override
  public void equippedByThief(Thief character) {
    //do nothing
  }

  @Override
  public void equippedByWhiteMage(WhiteMage character) {
    //do nothing
  }


}
