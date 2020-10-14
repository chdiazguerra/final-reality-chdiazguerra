package com.github.chdiazguerra.finalreality.model.character;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.NotNull;

/**
 * A class that holds all the information of a single enemy of the game, controlled by the CPU.
 *
 * @author Ignacio Slater Muñoz
 * @author Christian Díaz Guerra
 */
public class Enemy extends AbstractCharacter{

  private final int weight;
  protected ScheduledExecutorService scheduledExecutor;

  /**
   * Creates a new enemy with a name, a weight and the queue with the characters ready to
   * play.
   * @param name
   *     the name of the enemy
   * @param weight
   *     the weight of the enemy
   * @param turnsQueue
   *     the queue with the characters waiting for their turn
   */
  public Enemy(@NotNull final String name, final int weight,
      @NotNull final BlockingQueue<ICharacter> turnsQueue) {
    super(turnsQueue, name);
    this.weight = weight;
  }


  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Enemy)) {
      return false;
    }
    final Enemy enemy = (Enemy) o;
    return getWeight() == enemy.getWeight();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getWeight());
  }

  @Override
  public void waitTurn() {
    scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
    scheduledExecutor
            .schedule(this::addToQueue, this.getWeight() / 10, TimeUnit.SECONDS);
    }

  /**
   * Returns the weight of this enemy.
   */
  public int getWeight() {
    return weight;
  }

}
