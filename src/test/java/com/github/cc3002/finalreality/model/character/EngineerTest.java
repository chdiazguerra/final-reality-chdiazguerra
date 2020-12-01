package com.github.cc3002.finalreality.model.character;


import com.github.chdiazguerra.finalreality.model.character.player.Engineer;
import com.github.chdiazguerra.finalreality.model.character.player.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class containing the tests for Engineer class.
 *
 * @author Christian Díaz Guerra
 * @see Engineer
 */
class EngineerTest extends AbstractPlayerCharacterTest {
    private static final String ENGINEER_NAME = "Cid";
    private Engineer testEngineer;

    private static final String OTHER_NAME = "OTHER_NAME";

    @BeforeEach
    void setUp(){
        testEngineer = new Engineer(ENGINEER_NAME, turns, LIFE, DEFENSE);
        this.weaponsCreation();
        acceptedWeapons.add(axeTest);
        acceptedWeapons.add(bowTest);
        rejectedWeapons.add(staffTest);
        rejectedWeapons.add(swordTest);
        rejectedWeapons.add(knifeTest);
    }

    @Test
    void equipTest(){
        equipWeapons(testEngineer, acceptedWeapons, rejectedWeapons);
    }

    @Test
    void equipDeadTest(){
        testEngineer.setLife(0);
        this.equipToDead(testEngineer, acceptedWeapons, rejectedWeapons);
    }

    @Test
    void constructionTest(){
        checkConstruction(new Engineer(ENGINEER_NAME, turns, LIFE, DEFENSE), testEngineer,
                new Engineer(OTHER_NAME, turns, LIFE, DEFENSE), new Thief(ENGINEER_NAME, turns, LIFE, DEFENSE));
    }
}
