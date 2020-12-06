package com.github.cc3002.finalreality.model.character;

import com.github.chdiazguerra.finalreality.model.character.player.classes.Thief;
import com.github.chdiazguerra.finalreality.model.character.player.classes.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * A class containing the tests for WhiteMage class.
 *
 * @author Christian Díaz Guerra
 * @see WhiteMage
 */
class WhiteMageTest extends AbstractPlayerCharacterTest{

    private static final String WHITE_MAGE_NAME = "Eiko";
    private WhiteMage testWhiteMage;
    private static final String OTHER_NAME = "OTHER_NAME";

    @BeforeEach
    void setUp(){
        testWhiteMage = new WhiteMage(WHITE_MAGE_NAME, turns, LIFE, DEFENSE);
        this.weaponsCreation();
        acceptedWeapons.add(staffTest);
        rejectedWeapons.add(swordTest);
        rejectedWeapons.add(axeTest);
        rejectedWeapons.add(knifeTest);
        rejectedWeapons.add(bowTest);
    }

    @Test
    void equipTest(){
        equipWeapons(testWhiteMage, acceptedWeapons, rejectedWeapons);
    }

    @Test
    void equipDeadTest(){
        testWhiteMage.setLife(0);
        this.equipToDead(testWhiteMage, acceptedWeapons, rejectedWeapons);
    }

    @Test
    void constructionTest(){
        checkConstruction(new WhiteMage(WHITE_MAGE_NAME, turns, LIFE, DEFENSE), testWhiteMage,
                new WhiteMage(OTHER_NAME, turns, LIFE, DEFENSE), new Thief(WHITE_MAGE_NAME, turns, LIFE, DEFENSE));
    }
}
