package com.github.chdiazguerra.finalreality.model.character;

import com.github.chdiazguerra.finalreality.controller.handlers.IHandler;

/**
 * This represents a character from the game.
 * A character can be controlled by the player or by the CPU (an enemy).
 *
 * @author Ignacio Slater Muñoz.
 * @author Christian Díaz Guerra
 */
public interface ICharacter {

  /**
   * Sets a scheduled executor to make this character (thread) wait for {@code speed / 10}
   * seconds before adding the character to the queue.
   */
  void waitTurn();

  /**
   * Returns this character's name.
   */
  String getName();

  /**
   * Returns true if the character is alive. Otherwise, returns false
   */
  boolean getIsAlive();


  /**
   * Attacks the character passed as argument
   * @param character
   *     character attacked
   */
  void attack(ICharacter character);

  /**
   * Performs the calculations to reduce the current life,
   * depending on the attackPoints passed as argument
   */
  void attacked(int attackPoints);

  /**
   * Returns the defense of the character
   */
  int getDefense();

  /**
   * Returns the current life of the character
   */
  int getLife();

  /**
   * Sets the current life to newLife
   */
  void setLife(int newLife);

  /**
   * Adds a listener to notify that the character dies.
   */
  void addDeadListener(IHandler deadHandler);

  /**
   * Adds the listener to notify that the character was added to the queue
   */
  void addAddedToQueueListener(IHandler queueHandler);

  /**
   * Adds the listener to notify the damage received
   */
  void addDamageReceivedListener(IHandler damageReceiveHandler);


}
