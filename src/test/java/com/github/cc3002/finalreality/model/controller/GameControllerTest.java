package com.github.cc3002.finalreality.model.controller;

import com.github.chdiazguerra.finalreality.controller.GameController;
import com.github.chdiazguerra.finalreality.controller.phases.*;
import com.github.chdiazguerra.finalreality.gui.scenes.IBattleScene;
import com.github.chdiazguerra.finalreality.gui.scenes.NullBattleScene;
import com.github.chdiazguerra.finalreality.model.character.Enemy;
import com.github.chdiazguerra.finalreality.model.character.ICharacter;
import com.github.chdiazguerra.finalreality.model.character.player.classes.*;
import com.github.chdiazguerra.finalreality.model.weapon.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;
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
    private IBattleScene scene;

    private final String TEST_NAME = "TEST_NAME";
    private final int TEST_ATTACK = 10;
    private final int TEST_LIFE = 10;
    private final int TEST_DEFENSE = 5;
    private final int TEST_WEIGHT = 15;


    @BeforeEach
    void setUp(){
        scene = new NullBattleScene();
        testTurnQueue = new LinkedBlockingQueue<>();
        controller = new GameController();
        controller.setScene(scene);
    }

    @Test
    void createPlayerClassesTest() {
        assertEquals(0, controller.getAllPlayerCharacters().size());

        controller.createBlackMage(TEST_NAME, TEST_LIFE, TEST_DEFENSE);
        assertEquals(1, controller.getAllPlayerCharacters().size());
        assertEquals(new BlackMage(TEST_NAME, testTurnQueue, TEST_LIFE, TEST_DEFENSE),
                controller.getPlayerCharacter(0));
        assertNotNull(controller.getEquippedWeapon(0));
        assertFalse(controller.isPlayerDead(0));

        controller.createEngineer(TEST_NAME, TEST_LIFE, TEST_DEFENSE);
        assertEquals(2, controller.getAllPlayerCharacters().size());
        assertEquals(new Engineer(TEST_NAME, testTurnQueue, TEST_LIFE, TEST_DEFENSE),
                controller.getPlayerCharacter(1));
        assertNotNull(controller.getEquippedWeapon(1));
        assertFalse(controller.isPlayerDead(1));

        controller.createKnight(TEST_NAME, TEST_LIFE, TEST_DEFENSE);
        assertEquals(3, controller.getAllPlayerCharacters().size());
        assertEquals(new Knight(TEST_NAME, testTurnQueue, TEST_LIFE, TEST_DEFENSE),
                controller.getPlayerCharacter(2));
        assertNotNull(controller.getEquippedWeapon(2));
        assertFalse(controller.isPlayerDead(2));

        controller.createThief(TEST_NAME, TEST_LIFE, TEST_DEFENSE);
        assertEquals(4, controller.getAllPlayerCharacters().size());
        assertEquals(new Thief(TEST_NAME, testTurnQueue, TEST_LIFE, TEST_DEFENSE),
                controller.getPlayerCharacter(3));
        assertNotNull(controller.getEquippedWeapon(3));
        assertFalse(controller.isPlayerDead(3));

        controller.createWhiteMage(TEST_NAME, TEST_LIFE, TEST_DEFENSE);
        assertEquals(5, controller.getAllPlayerCharacters().size());
        assertEquals(new WhiteMage(TEST_NAME, testTurnQueue, TEST_LIFE, TEST_DEFENSE),
                controller.getPlayerCharacter(4));
        assertNotNull(controller.getEquippedWeapon(4));
        assertFalse(controller.isPlayerDead(4));


    }

    @Test
    void createEnemyTest(){
        assertEquals(0, controller.getAllEnemies().size());
        for(int i=1; i<=5; i++){
            controller.createEnemy(TEST_NAME, TEST_WEIGHT, TEST_LIFE, TEST_DEFENSE, TEST_ATTACK);
            assertEquals(i, controller.getAllEnemies().size());
            assertEquals(new Enemy(TEST_NAME, TEST_WEIGHT, testTurnQueue, TEST_LIFE, TEST_DEFENSE, TEST_ATTACK),
                    controller.getEnemy(i-1));
            assertFalse(controller.isEnemyDead(i-1));
        }
    }


    @Test
    void gettersTest(){
        ICharacter testCharacter = new Knight(TEST_NAME, testTurnQueue, TEST_LIFE, TEST_DEFENSE);
        assertEquals(TEST_LIFE, controller.getCharacterLife(testCharacter));
        assertEquals(TEST_NAME, controller.getCharacterName(testCharacter));
        assertEquals(TEST_DEFENSE, controller.getCharacterDefense(testCharacter));

        controller.createKnight(TEST_NAME, TEST_LIFE, TEST_DEFENSE);
        assertTrue(controller.weaponIsEquipped(5));
        IWeapon weapon = controller.getEquippedWeapon(0);
        controller.equipWeaponFromInventory(5, controller.getPlayerCharacter(0));
        assertEquals(weapon, controller.getEquippedWeapon(0));
    }

    @Test
    void inventoryTest(){
        List<IWeapon> inventory = controller.getInventory();
        assertEquals(5, inventory.size());
        assertEquals("Axe", inventory.get(0).getName());
        assertEquals("Bow", inventory.get(1).getName());
        assertEquals("Knife", inventory.get(2).getName());
        assertEquals("Staff", inventory.get(3).getName());
        assertEquals("Sword", inventory.get(4).getName());
    }

    @RepeatedTest(50)
    void createWeaponsTest(){
        assertTrue(12 <= controller.getInventoryWeapon(0).getDamage()
                && controller.getInventoryWeapon(0).getDamage() < 20);
        assertTrue(12 <= controller.getInventoryWeapon(1).getDamage()
                && controller.getInventoryWeapon(1).getDamage() < 17);
        assertTrue(8 <= controller.getInventoryWeapon(2).getDamage()
                && controller.getInventoryWeapon(2).getDamage() < 15);
        assertTrue(15 <= controller.getInventoryWeapon(3).getDamage()
                && controller.getInventoryWeapon(3).getDamage() < 20);
        assertTrue(10 <= controller.getInventoryWeapon(4).getDamage()
                && controller.getInventoryWeapon(4).getDamage() < 17);

        assertTrue(25 <= controller.getInventoryWeapon(0).getWeight()
                && controller.getInventoryWeapon(0).getDamage() < 40);
        assertTrue(20 <= controller.getInventoryWeapon(1).getWeight()
                && controller.getInventoryWeapon(1).getDamage() < 30);
        assertTrue(15 <= controller.getInventoryWeapon(2).getWeight()
                && controller.getInventoryWeapon(2).getDamage() < 25);
        assertTrue(30 <= controller.getInventoryWeapon(3).getWeight()
                && controller.getInventoryWeapon(3).getDamage() < 35);
        assertTrue(25 <= controller.getInventoryWeapon(4).getWeight()
                && controller.getInventoryWeapon(4).getDamage() < 35);
    }

    @Test
    void winTest() throws InterruptedException {
        Axe weapon = new Axe("Axe", 20, 20);
        controller.createKnight(TEST_NAME, 50, 10);
        controller.getPlayerCharacter(0).equip(weapon);
        controller.createEnemy(TEST_NAME, 10, 20, 0, 1);
        controller.initialize();
        String playerInfo = controller.getPlayerCharacterInfo(0);
        assertEquals(TEST_NAME + "\n HP " + 50 + "\tALIVE", playerInfo);

        assertTrue(controller.isWaiting());
        Thread.sleep(2500);

        assertTrue(controller.isEnemyTurn());
        assertFalse(controller.isWaiting());
        controller.next();

        assertTrue(controller.isSelectingTarget());
        assertFalse(controller.isEnemyTurn());
        controller.tryAttackPlayer();
        assertEquals(playerInfo, controller.getPlayerCharacterInfo(0));

        assertTrue(controller.isEndTurn());
        assertFalse(controller.isSelectingTarget());
        controller.next();

        assertTrue(controller.isPlayerTurn());
        assertFalse(controller.isEndTurn());
        controller.next();
        controller.attack();

        assertTrue(controller.isSelectingTarget());
        assertFalse(controller.isPlayerTurn());
        controller.tryAttackEnemy(0);
        assertTrue(controller.isEnemyDead(0));
        controller.next();

        assertTrue(controller.isWon());
        assertFalse(controller.isLost());
    }

    @Test
    void lostTest() throws InterruptedException {
        controller.createKnight(TEST_NAME, 10, 0);
        controller.createEnemy(TEST_NAME, 1, 20, 10, 30);
        controller.initialize();
        assertEquals(TEST_NAME + "\n HP " + 10 + "\tALIVE", controller.getPlayerCharacterInfo(0));
        Thread.sleep(1000);
        assertTrue(controller.isEnemyTurn());
        controller.next();
        controller.tryAttackPlayer();
        assertTrue(controller.isPlayerDead(0));
        assertEquals(TEST_NAME + "\n HP " + 0 + "\tDEAD", controller.getPlayerCharacterInfo(0));
        controller.next();

        assertTrue(controller.isLost());
        assertFalse(controller.isWon());
    }

    @Test
    void playerOptionsTest() throws InterruptedException {
        controller.createKnight(TEST_NAME, TEST_LIFE, TEST_DEFENSE);
        controller.createEnemy(TEST_NAME, 50, 30, TEST_DEFENSE, TEST_ATTACK);
        controller.initialize();
        String weaponInfo = controller.weaponInfo(controller.getEquippedWeapon(0));
        Thread.sleep(4000);
        assertTrue(controller.isPlayerTurn());
        controller.next();
        controller.toInventory();

        assertTrue(controller.isSelectingWeapon());
        controller.tryToEquipWeapon(0);
        assertNotEquals(weaponInfo, controller.weaponInfo(controller.getEquippedWeapon(0)));
        assertEquals(weaponInfo.replace("EQU", "INV"), controller.weaponInfo(controller.getInventoryWeapon(5)));
        controller.tryToEquipWeapon(5);
        assertEquals(weaponInfo, controller.infoWeaponPlayerTurn().replace("\n", " "));
        controller.back();

        assertTrue(controller.isPlayerTurn());
        assertFalse(controller.isSelectingWeapon());
        controller.attack();

        assertTrue(controller.isSelectingTarget());
        assertFalse(controller.isPlayerTurn());
        controller.back();

        assertTrue(controller.isPlayerTurn());
        assertFalse(controller.isSelectingTarget());
        controller.attack();

        assertTrue(controller.isSelectingTarget());
        assertFalse(controller.isPlayerTurn());
        controller.tryAttackEnemy(0);

        assertTrue(controller.isEndTurn());
        assertFalse(controller.isSelectingTarget());
        controller.next();

        assertTrue(controller.isWaiting());

        Thread.sleep(1000);

        assertTrue(controller.isEnemyTurn());
    }

    @Test
    void noWinNoLostTest() throws InterruptedException {

        controller.createEnemy(TEST_NAME, 5, 5, 0, 10);
        controller.createEnemy(TEST_NAME, 42, 5, 0, 10);

        controller.createKnight("Knight 1", 1, 0);
        controller.createKnight("Knight 2", 1, 0);

        controller.initialize();
        Thread.sleep(4000);

        assertTrue(controller.isEnemyTurn());
        controller.next();
        assertTrue(controller.isSelectingTarget());
        controller.tryAttackPlayer();
        assertTrue(controller.isEndTurn());

        controller.next();
        assertTrue(controller.isPlayerTurn());
        controller.attack();
        assertTrue(controller.isSelectingTarget());
        controller.tryAttackEnemy(0);
        assertTrue(controller.isEndTurn());
        controller.next();
        assertTrue(controller.isEnemyDead(0));

        controller.next();
        assertFalse(controller.isWon());
        assertFalse(controller.isLost());

    }



}
