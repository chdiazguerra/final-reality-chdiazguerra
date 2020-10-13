package com.github.cc3002.finalreality.model.character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.github.chdiazguerra.finalreality.model.character.player.Engineer;
import com.github.chdiazguerra.finalreality.model.character.player.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EngineerTest extends AbstractCharacterTest {
    private static final String ENGINEER_NAME = "Cid";
    private Engineer testEngineer;

    private static final String OTHER_NAME = "OTHER_NAME";

    @BeforeEach
    void setUp(){
        testEngineer = new Engineer(ENGINEER_NAME, turns);
    }

    @Test
    void equipTest(){
        assertNull(testEngineer.getEquippedWeapon());
        testEngineer.equip(testWeapon);
        assertEquals(testWeapon, testEngineer.getEquippedWeapon());
    }

    @Test
    void constructionTest(){
        checkConstruction(new Engineer(ENGINEER_NAME, turns), testEngineer,
                new Engineer(OTHER_NAME, turns), new Thief(ENGINEER_NAME, turns));
    }
}
