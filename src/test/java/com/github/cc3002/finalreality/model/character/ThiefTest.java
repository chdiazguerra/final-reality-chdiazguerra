package com.github.cc3002.finalreality.model.character;

import com.github.chdiazguerra.finalreality.model.character.player.classes.Knight;
import com.github.chdiazguerra.finalreality.model.character.player.classes.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class containing the tests for Thief class.
 *
 * @author Christian Díaz Guerra
 * @see Thief
 */
class ThiefTest extends AbstractPlayerCharacterTest{

    private static final String THIEF_NAME = "Zidane";
    private Thief testThief;


    private static final String OTHER_NAME = "OTHER_NAME";

    @BeforeEach
    void setUp(){
        testThief = new Thief(THIEF_NAME, turns, LIFE, DEFENSE);
        this.weaponsCreation();
        acceptedWeapons.add(swordTest);
        acceptedWeapons.add(staffTest);
        acceptedWeapons.add(bowTest);
        rejectedWeapons.add(axeTest);
        rejectedWeapons.add(knifeTest);
    }

    @Test
    void equipTest(){
        equipWeapons(testThief, acceptedWeapons, rejectedWeapons);
    }

    @Test
    void equipDeadTest(){
        testThief.setLife(0);
        this.equipToDead(testThief, acceptedWeapons, rejectedWeapons);
    }

    @Test
    void constructionTest(){
        checkConstruction(new Thief(THIEF_NAME, turns, LIFE, DEFENSE), testThief,
                new Thief(OTHER_NAME, turns, LIFE, DEFENSE), new Knight(THIEF_NAME, turns, LIFE, DEFENSE));
    }

}
