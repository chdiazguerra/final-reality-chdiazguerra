package com.github.cc3002.finalreality.model.character;

import com.github.chdiazguerra.finalreality.model.character.player.Knight;
import com.github.chdiazguerra.finalreality.model.character.player.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * A class containing the tests for Thief class.
 *
 * @author Christian Díaz Guerra
 * @see Thief
 */
class ThiefTest extends AbstractCharacterTest{

    private static final String THIEF_NAME = "Zidane";
    private Thief testThief;


    private static final String OTHER_NAME = "OTHER_NAME";

    @BeforeEach
    void setUp(){
        testThief = new Thief(THIEF_NAME, turns);
    }

    @Test
    void equipTest(){
        assertNull(testThief.getEquippedWeapon());
        testThief.equip(testWeapon);
        assertEquals(testWeapon, testThief.getEquippedWeapon());
    }

    @Test
    void constructionTest(){
        checkConstruction(new Thief(THIEF_NAME, turns), testThief,
                new Thief(OTHER_NAME, turns), new Knight(THIEF_NAME, turns));
    }

}
