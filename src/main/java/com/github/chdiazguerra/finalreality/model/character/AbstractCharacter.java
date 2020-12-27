package com.github.chdiazguerra.finalreality.model.character;


import java.beans.PropertyChangeSupport;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

import com.github.chdiazguerra.finalreality.controller.handlers.IHandler;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Christian Díaz Guerra
 */
public abstract class AbstractCharacter implements ICharacter {

  protected final PropertyChangeSupport characterDeathEvent = new PropertyChangeSupport(this);
  protected final PropertyChangeSupport addedToQueueEvent = new PropertyChangeSupport(this);

  protected final BlockingQueue<ICharacter> turnsQueue;
  protected final String name;
  protected int life;
  protected int defense;
  protected ScheduledExecutorService scheduledExecutor;



  protected AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
                              @NotNull String name, int life, int defense) {
    this.turnsQueue = turnsQueue;
    this.name = name;
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
    addedToQueueEvent.firePropertyChange("CHARACTER_ADDED_TO_QUEUE", null, this);
    scheduledExecutor.shutdown();
  }

  @Override
  public boolean getIsAlive(){
    return life != 0;
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
      newLife = 0;
      characterDeathEvent.firePropertyChange("DEATH_CHARACTER", null, this);
    }
    this.life = newLife;
  }

  @Override
  public void addDeadListener(IHandler deadHandler) {
    characterDeathEvent.addPropertyChangeListener(deadHandler);
  }

  @Override
  public void addAddedToQueueListener(IHandler queueHandler) {
    addedToQueueEvent.addPropertyChangeListener(queueHandler);
  }


}
