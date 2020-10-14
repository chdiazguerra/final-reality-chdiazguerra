package com.github.cc3002.finalreality.model.character;

import com.github.chdiazguerra.finalreality.model.character.Enemy;
import com.github.chdiazguerra.finalreality.model.character.player.AbstractPlayerCharacter;
import com.github.chdiazguerra.finalreality.model.character.player.IPlayerCharacter;
import com.github.chdiazguerra.finalreality.model.character.player.Thief;
import com.github.chdiazguerra.finalreality.model.weapon.Axe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class containing the tests for Enemy class.
 *
 * @author Christian Díaz Guerra
 * @see Enemy
 */
class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Goblin";
  private Enemy testEnemy;

  @BeforeEach
  void setUp() {
    basicSetUp();
    testEnemy = new Enemy(ENEMY_NAME, 10, turns);
  }

  @Test
  void constructorTest() {
    checkConstruction(new Enemy(ENEMY_NAME, 10, turns),
        testEnemy,
        new Enemy(ENEMY_NAME, 11, turns),
            new Thief(ENEMY_NAME, turns));
  }

  /**
   * Checks that the enemy waits the appropriate amount of time for it's turn.
   */
  @Test
  void waitTurnTest() {
    waitTurnCheck(testEnemy);
  }

}