package com.github.cc3002.finalreality.model.character;

import com.github.chdiazguerra.finalreality.model.character.Enemy;
import com.github.chdiazguerra.finalreality.model.character.player.classes.Engineer;
import com.github.chdiazguerra.finalreality.model.character.player.classes.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class containing the tests for Enemy class.
 *
 * @author Christian Díaz Guerra
 * @see Enemy
 */
class EnemyTest extends AbstractCharacterTest {


  @BeforeEach
  void setUp() {
    basicSetUp();
  }

  @Test
  void constructorTest() {
    checkConstruction(new Enemy(ENEMY_NAME, ENEMY_WEIGHT, turns, ENEMY_LIFE, ENEMY_DEFENSE, ENEMY_DAMAGE),
        testEnemyCharacter,
        new Enemy(ENEMY_NAME, ENEMY_WEIGHT+1, turns, ENEMY_LIFE, ENEMY_DEFENSE, ENEMY_DAMAGE),
            new Thief(ENEMY_NAME, turns, ENEMY_LIFE, ENEMY_DEFENSE));
  }

  /**
   * Checks that the enemy waits the appropriate amount of time for its turn.
   */
  @Test
  void waitTurnTest() {
    waitTurnCheck(testEnemyCharacter);
  }

  @Test
  void lifeTest(){
    lifeMethodsTests(testEnemyCharacter, ENEMY_LIFE);
  }

  @Test
  void attackTest(){
    attackMethod(testEnemyCharacter, testPlayer, ENEMY_DAMAGE-TEST_PLAYER_CHARACTER_DEFENSE);
  }

  @Test
  void attackBeingDeadTest(){
    attackBeingDead(testEnemyCharacter, testPlayer, ENEMY_LIFE);
  }

  @Test
  void attackWithHighDefenseTest(){
    attackWithHighDefense(testEnemyCharacter, new Engineer(TEST_PLAYER_CHARACTER_NAME, turns, LIFE, ENEMY_DAMAGE+10), LIFE);
  }

}