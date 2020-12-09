package com.github.cc3002.finalreality.model.character;


import com.github.chdiazguerra.finalreality.model.character.Enemy;
import com.github.chdiazguerra.finalreality.model.weapon.Axe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class containing the common test for all type of player characters.
 * @author Christian Díaz Guerra
 */
public class PlayerCharacterTest extends AbstractPlayerCharacterTest{


    @BeforeEach
    void setUp(){
        basicSetUp();
        axeTest = new Axe(NAME_WEAPON, DAMAGE_WEAPON, WEIGHT_WEAPON);
        testPlayerCharacter.equip(axeTest);
    }

    @Test
    void waitTurnPlayerTest(){
        waitTurnCheck(testPlayer);
    }

    @Test
    void lifeTest(){
        lifeMethodsTests(testPlayer, TEST_PLAYER_CHARACTER_LIFE);
    }

    @Test
    void attackTest(){
        this.attackMethod(testPlayer, testEnemyCharacter, DAMAGE_WEAPON-ENEMY_DEFENSE);
    }

    @Test
    void attackBeingDeadTest(){
        this.attackBeingDead(testPlayer, testEnemyCharacter, ENEMY_LIFE);
    }

    @Test
    void attackWithHighDefenseTest(){
        this.attackWithHighDefense(testPlayer, new Enemy(ENEMY_NAME, ENEMY_WEIGHT, turns, ENEMY_LIFE, DAMAGE_WEAPON+10, ENEMY_DAMAGE), ENEMY_LIFE);
    }

}
