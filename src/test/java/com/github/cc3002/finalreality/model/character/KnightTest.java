package com.github.cc3002.finalreality.model.character;

import com.github.chdiazguerra.finalreality.model.character.player.Knight;
import com.github.chdiazguerra.finalreality.model.character.player.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * A class containing the tests for Knight class.
 *
 * @author Christian Díaz Guerra
 * @see Knight
 */
class KnightTest extends AbstractPlayerCharacterTest{

    private static final String KNIGHT_NAME = "Adelbert";
    private Knight testKnight;

    private static final String OTHER_NAME = "OTHER_NAME";

    @BeforeEach
    void setUp(){
        testKnight = new Knight(KNIGHT_NAME, turns, LIFE, DEFENSE);
        this.weaponsCreation();
        acceptedWeapons.add(swordTest);
        acceptedWeapons.add(axeTest);
        acceptedWeapons.add(knifeTest);
        rejectedWeapons.add(staffTest);
        rejectedWeapons.add(bowTest);
    }

    @Test
    void equipTest(){
        equipWeapons(testKnight, acceptedWeapons, rejectedWeapons);
    }

    @Test
    void equipDeadTest(){
        testKnight.setLife(0);
        this.equipToDead(testKnight, acceptedWeapons, rejectedWeapons);
    }
    @Test
    void constructionTest(){
        checkConstruction(new Knight(KNIGHT_NAME, turns, LIFE, DEFENSE), testKnight,
                new Knight(OTHER_NAME, turns, LIFE, DEFENSE), new Thief(KNIGHT_NAME, turns, LIFE, DEFENSE));
    }
}
