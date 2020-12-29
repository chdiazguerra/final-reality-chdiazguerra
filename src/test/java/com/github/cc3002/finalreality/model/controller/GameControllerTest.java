package com.github.cc3002.finalreality.model.controller;

import com.github.chdiazguerra.finalreality.controller.GameController;
import com.github.chdiazguerra.finalreality.model.character.Enemy;
import com.github.chdiazguerra.finalreality.model.character.ICharacter;
import com.github.chdiazguerra.finalreality.model.character.player.classes.*;
import com.github.chdiazguerra.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class has all the tests for the game controller.
 * @author Christian Díaz Guerra
 */
public class GameControllerTest {

    private GameController controller;
    private BlockingQueue<ICharacter> testTurnQueue;

    private final String TEST_NAME = "TEST_NAME";
    private final int TEST_ATTACK = 10;
    private final int TEST_LIFE = 10;
    private final int TEST_DEFENSE = 5;
    private final int TEST_WEIGHT = 15;
    private final int TEST_WEIGHT_2 = 20;


    @BeforeEach
    void setUp(){
        testTurnQueue = new LinkedBlockingQueue<>();
        controller = new GameController();
    }

    @Test
    void createPlayerClassesTest() {
        assertEquals(0, controller.getAllPlayerCharacters().size());

        controller.createBlackMage(TEST_NAME, TEST_LIFE, TEST_DEFENSE);
        assertEquals(1, controller.getAllPlayerCharacters().size());
        assertEquals(new BlackMage(TEST_NAME, testTurnQueue, TEST_LIFE, TEST_DEFENSE),
                controller.getPlayerCharacter(0));

        controller.createEngineer(TEST_NAME, TEST_LIFE, TEST_DEFENSE);
        assertEquals(2, controller.getAllPlayerCharacters().size());
        assertEquals(new Engineer(TEST_NAME, testTurnQueue, TEST_LIFE, TEST_DEFENSE),
                controller.getPlayerCharacter(1));

        controller.createKnight(TEST_NAME, TEST_LIFE, TEST_DEFENSE);
        assertEquals(3, controller.getAllPlayerCharacters().size());
        assertEquals(new Knight(TEST_NAME, testTurnQueue, TEST_LIFE, TEST_DEFENSE),
                controller.getPlayerCharacter(2));

        controller.createThief(TEST_NAME, TEST_LIFE, TEST_DEFENSE);
        assertEquals(4, controller.getAllPlayerCharacters().size());
        assertEquals(new Thief(TEST_NAME, testTurnQueue, TEST_LIFE, TEST_DEFENSE),
                controller.getPlayerCharacter(3));

        controller.createWhiteMage(TEST_NAME, TEST_LIFE, TEST_DEFENSE);
        assertEquals(5, controller.getAllPlayerCharacters().size());
        assertEquals(new WhiteMage(TEST_NAME, testTurnQueue, TEST_LIFE, TEST_DEFENSE),
                controller.getPlayerCharacter(4));


    }

    @Test
    void createEnemyTest(){
        assertEquals(0, controller.getAllEnemies().size());
        for(int i=1; i<=5; i++){
            controller.createEnemy(TEST_NAME, TEST_WEIGHT, TEST_LIFE, TEST_DEFENSE, TEST_ATTACK);
            assertEquals(i, controller.getAllEnemies().size());
            assertEquals(new Enemy(TEST_NAME, TEST_WEIGHT, testTurnQueue, TEST_LIFE, TEST_DEFENSE, TEST_ATTACK),
                    controller.getEnemy(i-1));
        }
    }

    @Test
    void createWeaponsTest(){
        assertEquals(0, controller.getInventory().size());

        controller.createAxe(TEST_NAME, TEST_ATTACK, TEST_WEIGHT);
        assertEquals(1, controller.getInventory().size());
        assertEquals(new Axe(TEST_NAME, TEST_ATTACK, TEST_WEIGHT),
                controller.getInventoryWeapon(0));

        controller.createBow(TEST_NAME, TEST_ATTACK, TEST_WEIGHT);
        assertEquals(2, controller.getInventory().size());
        assertEquals(new Bow(TEST_NAME, TEST_ATTACK, TEST_WEIGHT),
                controller.getInventoryWeapon(1));

        controller.createKnife(TEST_NAME, TEST_ATTACK, TEST_WEIGHT);
        assertEquals(3, controller.getInventory().size());
        assertEquals(new Knife(TEST_NAME, TEST_ATTACK, TEST_WEIGHT),
                controller.getInventoryWeapon(2));

        controller.createStaff(TEST_NAME, TEST_ATTACK, TEST_WEIGHT);
        assertEquals(4, controller.getInventory().size());
        assertEquals(new Staff(TEST_NAME, TEST_ATTACK, TEST_WEIGHT),
                controller.getInventoryWeapon(3));

        controller.createSword(TEST_NAME, TEST_ATTACK, TEST_WEIGHT);
        assertEquals(5, controller.getInventory().size());
        assertEquals(new Sword(TEST_NAME, TEST_ATTACK, TEST_WEIGHT),
                controller.getInventoryWeapon(4));

    }

    @Test
    void equipTest(){
        Axe axeTest = new Axe(TEST_NAME, TEST_ATTACK, TEST_WEIGHT);
        Sword swordTest = new Sword(TEST_NAME, TEST_ATTACK, TEST_WEIGHT);
        controller.createAxe(TEST_NAME, TEST_ATTACK, TEST_WEIGHT);
        controller.createSword(TEST_NAME, TEST_ATTACK, TEST_WEIGHT);
        controller.createStaff(TEST_NAME, TEST_ATTACK, TEST_WEIGHT);
        controller.createKnight(TEST_NAME, TEST_LIFE, TEST_DEFENSE);

        assertNull(controller.getEquippedWeapon(0));
        assertFalse(controller.weaponIsEquipped(0));
        assertFalse(controller.weaponIsEquipped(1));
        assertFalse(controller.weaponIsEquipped(2));

        equipMethod(0, 0, axeTest);

        equipMethod(1, 1, swordTest);

        equipMethod(1, 1, swordTest);

        equipMethod(2, 1, swordTest);
    }

    private void equipMethod(int indexWeapon, int indexEquipped, IWeapon weaponTest){
        controller.equipWeaponFromInventory(indexWeapon, controller.getPlayerCharacter(0));
        assertEquals(weaponTest, controller.getEquippedWeapon(0));
        assertTrue(controller.weaponIsEquipped(indexEquipped));
        for(int i=0; i<controller.getInventory().size(); i++){
            if(i!=indexEquipped){
                assertFalse(controller.weaponIsEquipped(i));
                assertFalse(controller.weaponIsEquipped(i));
            }
        }
    }

    @Test
    void gettersTest(){
        ICharacter testCharacter = new Knight(TEST_NAME, testTurnQueue, TEST_LIFE, TEST_DEFENSE);
        assertEquals(TEST_LIFE, controller.getCharacterLife(testCharacter));
        assertEquals(TEST_NAME, controller.getCharacterName(testCharacter));
        assertEquals(TEST_DEFENSE, controller.getCharacterDefense(testCharacter));
    }


    @Test
    void attackTest(){
        createInstance();

        controller.attackToEnemy((ICharacter) controller.getPlayerCharacter(0), 0);
        assertEquals(TEST_LIFE-(TEST_ATTACK-TEST_DEFENSE),
                controller.getCharacterLife(controller.getEnemy(0)));

        //controller.attackToPlayer(controller.getEnemy(0), 0);
        assertEquals(TEST_LIFE-(TEST_ATTACK-TEST_DEFENSE),
                controller.getCharacterLife((ICharacter) controller.getPlayerCharacter(0)));
    }

    @Test
    void winTest(){
        createInstance();

        controller.attackToEnemy((ICharacter)controller.getPlayerCharacter(0), 0);
        controller.attackToEnemy((ICharacter)controller.getPlayerCharacter(0), 0);

        controller.attackToEnemy((ICharacter)controller.getPlayerCharacter(0), 0);
        controller.attackToEnemy((ICharacter)controller.getPlayerCharacter(0), 0);


        assertTrue(controller.win);
        assertFalse(controller.lose);
    }

    @Test
    void loseTest(){
        createInstance();

        //controller.attackToPlayer(controller.getEnemy(0), 0);
        //controller.attackToPlayer(controller.getEnemy(0), 0);

        //controller.attackToPlayer(controller.getEnemy(0), 0);
        //controller.attackToPlayer(controller.getEnemy(0), 0);

        assertTrue(controller.lose);
        assertFalse(controller.win);
    }

    private void createInstance(){
        controller.createEnemy(TEST_NAME, TEST_WEIGHT, TEST_LIFE, TEST_DEFENSE, TEST_ATTACK);
        controller.createEnemy(TEST_NAME, TEST_WEIGHT, TEST_LIFE, TEST_DEFENSE, TEST_ATTACK);

        controller.createSword(TEST_NAME, TEST_ATTACK, TEST_WEIGHT_2);
        controller.createKnight(TEST_NAME, TEST_LIFE, TEST_DEFENSE);

        controller.createSword(TEST_NAME, TEST_ATTACK, TEST_WEIGHT_2);
        controller.createKnight(TEST_NAME, TEST_LIFE, TEST_DEFENSE);

        controller.equipWeaponFromInventory(0, controller.getPlayerCharacter(0));
        controller.equipWeaponFromInventory(1, controller.getPlayerCharacter(1));
    }

    @Test
    void turnsTest() throws InterruptedException {
        createInstance();

        controller.getEnemy(0).waitTurn();
        ((ICharacter)controller.getPlayerCharacter(0)).waitTurn();

        Thread.sleep(2000);

        controller.beginTurn();
        assertNotNull(controller.getCharacterTurn());
        assertEquals(new Enemy(TEST_NAME, TEST_WEIGHT, testTurnQueue, TEST_LIFE, TEST_DEFENSE, TEST_ATTACK),
                controller.getCharacterTurn());
        //controller.attackToPlayer(controller.getCharacterTurn(), 0);
        //controller.attackToPlayer(controller.getCharacterTurn(), 0);

        controller.beginTurn();
        assertNull(controller.getCharacterTurn());


    }







}
