package com.github.cc3002.finalreality.model.character;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.chdiazguerra.finalreality.model.character.player.Engineer;
import com.github.chdiazguerra.finalreality.model.character.player.IPlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AbstractPlayerCharacterTest extends AbstractCharacterTest{

    private IPlayerCharacter testPlayerCharacter;
    private final String TEST_PLAYER_CHARACTER_NAME = "Test Name";

    @BeforeEach
    void setUp(){
        basicSetUp();
        testPlayerCharacter = new Engineer(TEST_PLAYER_CHARACTER_NAME, turns);
        testPlayerCharacter.equip(testWeapon);
    }

    @Test
    void waitTurnPlayerTest(){
        waitTurnCheck(testPlayerCharacter);
    }

    @Test
    void getEquippedWeaponTest(){
        assertEquals(testWeapon, testPlayerCharacter.getEquippedWeapon());
    }
}
