package com.github.cc3002.finalreality.model.character;

import com.github.chdiazguerra.finalreality.model.character.player.BlackMage;
import com.github.chdiazguerra.finalreality.model.character.player.Engineer;
import com.github.chdiazguerra.finalreality.model.character.player.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BlackMageTest extends AbstractCharacterTest{
    private static final String BLACK_MAGE_NAME = "Vivi";
    private BlackMage testBlackMage;

    private static final String OTHER_NAME = "OTHER_NAME";

    @BeforeEach
    void setUp(){
        testBlackMage = new BlackMage(BLACK_MAGE_NAME, turns);
    }

    @Test
    void equipTest(){
        assertNull(testBlackMage.getEquippedWeapon());
        testBlackMage.equip(testWeapon);
        assertEquals(testWeapon, testBlackMage.getEquippedWeapon());
    }

    @Test
    void constructionTest(){
        checkConstruction(new BlackMage(BLACK_MAGE_NAME, turns), testBlackMage,
                new BlackMage(OTHER_NAME, turns), new Thief(BLACK_MAGE_NAME, turns));
    }
}
