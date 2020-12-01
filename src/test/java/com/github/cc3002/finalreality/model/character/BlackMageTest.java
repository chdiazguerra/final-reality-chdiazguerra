package com.github.cc3002.finalreality.model.character;

import com.github.chdiazguerra.finalreality.model.character.player.BlackMage;
import com.github.chdiazguerra.finalreality.model.character.player.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class containing the tests for BlackMage class.
 *
 * @author Christian Díaz Guerra
 * @see BlackMage
 */
class BlackMageTest extends AbstractPlayerCharacterTest{
    private static final String BLACK_MAGE_NAME = "Vivi";
    private BlackMage testBlackMage;
    private static final String OTHER_NAME = "OTHER_NAME";

    @BeforeEach
    void setUp(){
        testBlackMage = new BlackMage(BLACK_MAGE_NAME, turns, LIFE, DEFENSE);
        this.weaponsCreation();
        acceptedWeapons.add(knifeTest);
        acceptedWeapons.add(staffTest);
        rejectedWeapons.add(axeTest);
        rejectedWeapons.add(bowTest);
        rejectedWeapons.add(swordTest);
    }

    @Test
    void equipTest(){
        equipWeapons(testBlackMage, acceptedWeapons, rejectedWeapons);
    }

    @Test
    void equipDeadTest(){
        testBlackMage.setLife(0);
        this.equipToDead(testBlackMage, acceptedWeapons, rejectedWeapons);
    }

    @Test
    void constructionTest(){
        checkConstruction(new BlackMage(BLACK_MAGE_NAME, turns, LIFE, DEFENSE), testBlackMage,
                new BlackMage(OTHER_NAME, turns, LIFE, DEFENSE), new Thief(BLACK_MAGE_NAME, turns, LIFE, DEFENSE));
    }
}
