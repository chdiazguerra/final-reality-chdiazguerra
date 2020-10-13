package com.github.cc3002.finalreality.model.character;

import com.github.chdiazguerra.finalreality.model.character.player.Thief;
import com.github.chdiazguerra.finalreality.model.character.player.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class WhiteMageTest extends AbstractCharacterTest{

    private static final String WHITE_MAGE_NAME = "Eiko";
    private WhiteMage testWhiteMage;

    private static final String OTHER_NAME = "OTHER_NAME";

    @BeforeEach
    void setUp(){
        testWhiteMage = new WhiteMage(WHITE_MAGE_NAME, turns);
    }

    @Test
    void equipTest(){
        assertNull(testWhiteMage.getEquippedWeapon());
        testWhiteMage.equip(testWeapon);
        assertEquals(testWeapon, testWhiteMage.getEquippedWeapon());
    }

    @Test
    void constructionTest(){
        checkConstruction(new WhiteMage(WHITE_MAGE_NAME, turns), testWhiteMage,
                new WhiteMage(OTHER_NAME, turns), new Thief(WHITE_MAGE_NAME, turns));
    }
}
