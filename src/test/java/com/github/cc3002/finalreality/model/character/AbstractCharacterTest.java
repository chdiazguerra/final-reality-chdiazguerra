package com.github.cc3002.finalreality.model.character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.github.chdiazguerra.finalreality.model.character.Enemy;
import com.github.chdiazguerra.finalreality.model.character.ICharacter;
import com.github.chdiazguerra.finalreality.model.character.player.Engineer;
import com.github.chdiazguerra.finalreality.model.character.player.IPlayerCharacter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.jupiter.api.Assertions;


/**
 * Abstract class containing the common tests for all the types of characters.
 *
 * @author Ignacio Slater Muñoz.
 * @author Christian Díaz Guerra
 * @see ICharacter
 */
public abstract class AbstractCharacterTest {

  protected BlockingQueue<ICharacter> turns;
  protected int LIFE = 100;
  protected final int DEFENSE = 10;

  protected IPlayerCharacter testPlayerCharacter;
  protected final String TEST_PLAYER_CHARACTER_NAME = "Test Name";
  protected final int TEST_PLAYER_CHARACTER_LIFE = 100;
  protected final int TEST_PLAYER_CHARACTER_DEFENSE = 10;
  protected ICharacter testPlayer;

  protected Enemy testEnemyCharacter;
  protected final String ENEMY_NAME = "Enemy";
  protected final int ENEMY_LIFE = 100;
  protected final int ENEMY_DEFENSE = 10;
  protected final int ENEMY_DAMAGE = 20;
  protected final int ENEMY_WEIGHT = 10;

  /**
   * Checks that the character waits the appropriate amount of time for it's turn.
   */
  protected void waitTurnCheck(ICharacter character) {
    Assertions.assertTrue(turns.isEmpty());
    character.waitTurn();
    try {
      // Thread.sleep is not accurate so this values may be changed to adjust the
      // acceptable error margin.
      // We're testing that the character waits approximately 1 second.
      Thread.sleep(900);
      Assertions.assertEquals(0, turns.size());
      Thread.sleep(200);
      Assertions.assertEquals(1, turns.size());
      Assertions.assertEquals(character, turns.peek());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  /**
   * Checks if the initialization is correct
   */
  protected void checkConstruction(final ICharacter expectedCharacter,
      final ICharacter testEqualCharacter,
      final ICharacter sameClassDifferentCharacter,
      final ICharacter differentClassCharacter) {
    assertEquals(expectedCharacter, testEqualCharacter);
    assertNotEquals(sameClassDifferentCharacter, testEqualCharacter);
    assertNotEquals(testEqualCharacter, differentClassCharacter);
    assertEquals(expectedCharacter.hashCode(), testEqualCharacter.hashCode());
    assertNotEquals(sameClassDifferentCharacter.hashCode(), testEqualCharacter.hashCode());
    assertEquals(testEqualCharacter, testEqualCharacter);
  }

  /**
   * Provides the initialization for some variables
   */
  protected void basicSetUp() {
    turns = new LinkedBlockingQueue<>();
    testPlayerCharacter = new Engineer(TEST_PLAYER_CHARACTER_NAME, turns, TEST_PLAYER_CHARACTER_LIFE, TEST_PLAYER_CHARACTER_DEFENSE);
    testEnemyCharacter = new Enemy(ENEMY_NAME, ENEMY_WEIGHT, turns, ENEMY_LIFE, ENEMY_DEFENSE, ENEMY_DAMAGE);
    testPlayer = (ICharacter) testPlayerCharacter;
  }

  /**
   * Checks if the methods about the life of the character are correct
   */
  protected void lifeMethodsTests(ICharacter character, int initialLife){
    assertEquals(initialLife, character.getLife());
    character.setLife(-10);
    assertEquals(0, character.getLife());
    character.setLife(10);
    assertEquals(10, character.getLife());
    character.setLife(0);
    assertEquals(0, character.getLife());
  }

  /**
   * Checks if the attacking character performs correctly the attack to the attacked character,
   * given the expected damage.
   */
  protected void attackMethod(ICharacter attacking, ICharacter attacked, int expectedDamage){
    int initialLife = attacked.getLife();
    int attackNTimes = initialLife/expectedDamage;
    for(int i=1; i<=attackNTimes; i++){
      attacking.attack(attacked);
      assertEquals(initialLife-i*expectedDamage, attacked.getLife());
    }
    for(int i=0; i<2; i++) {
      attacking.attack(attacked);
      assertEquals(0, attacked.getLife());
    }
  }

  /**
   * Checks if the attack method is correct given a dead attacking character, an attacked character,
   * and the life of this last one.
   */
  protected void attackBeingDead(ICharacter attacking, ICharacter attacked, int life){
    attacking.setLife(0);
    attacking.attack(attacked);
    assertEquals(life, attacked.getLife());
  }

  /**
   * Checks if the attack method is correct given an attacked character,
   * with a defense higher than the damage of the attacking character, given the life of the first one.
   */
  protected void attackWithHighDefense(ICharacter attacking, ICharacter attacked, int life){
    attacking.attack(attacked);
    assertEquals(life, attacked.getLife());
  }
}
