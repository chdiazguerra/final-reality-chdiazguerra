package com.github.cc3002.finalreality.model.character;

import com.github.chdiazguerra.finalreality.model.character.player.Knight;
import com.github.chdiazguerra.finalreality.model.character.player.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * A class containing the tests for Knight class.
 *
 * @author Christian Díaz Guerra
 * @see Knight
 */
class KnightTest extends AbstractCharacterTest{

    private static final String KNIGHT_NAME = "Adelbert";
    private Knight testKnight;

    private static final String OTHER_NAME = "OTHER_NAME";

    @BeforeEach
    void setUp(){
        testKnight = new Knight(KNIGHT_NAME, turns);
    }

    @Test
    void equipTest(){
        assertNull(testKnight.getEquippedWeapon());
        testKnight.equip(testWeapon);
        assertEquals(testWeapon, testKnight.getEquippedWeapon());
    }

    @Test
    void constructionTest(){
        checkConstruction(new Knight(KNIGHT_NAME, turns), testKnight,
                new Knight(OTHER_NAME, turns), new Thief(KNIGHT_NAME, turns));
    }
}
