package com.github.chdiazguerra.finalreality.model.character;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Christian Díaz Guerra
 */
public abstract class AbstractCharacter implements ICharacter {

  protected final BlockingQueue<ICharacter> turnsQueue;
  protected final String name;
  protected boolean isAlive;
  protected int life;
  protected int defense;
  protected ScheduledExecutorService scheduledExecutor;


  protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                              @NotNull String name, int life, int defense) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.isAlive = true;
    this.life = life;
    this.defense = defense;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public abstract void waitTurn();

  /**
   * Adds this character to the turns queue.
   */
  protected void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }

  @Override
  public boolean getIsAlive(){
    return isAlive;
  }

  @Override
  public abstract void attack(ICharacter character);

  @Override
  public void attacked(int attackPoints) {
    int damage;
    if(this.getDefense()<=attackPoints){
      damage = attackPoints - this.getDefense();
    }else{
      damage = 0;
    }
    this.setLife(this.getLife() - damage);
  }

  @Override
  public int getDefense(){
    return defense;
  }

  @Override
  public int getLife(){
    return life;
  }

  @Override
  public void setLife(int newLife){
    if(newLife <= 0) {
      this.isAlive = false;
      newLife = 0;
    }
    this.life = newLife;
  }

}
