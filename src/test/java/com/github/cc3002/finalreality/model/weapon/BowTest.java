package com.github.cc3002.finalreality.model.weapon;

import com.github.chdiazguerra.finalreality.model.weapon.Bow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class containing the test for Bow type weapon.
 *
 * @author Christian Díaz Guerra
 * @see Bow
 */
class BowTest extends AbstractWeaponTest{
    @BeforeEach
    void setUp(){
        basicSetUp();
    }

    @Test
    void constructorTest(){
        checkConstruction(new Bow(BOW_NAME, DAMAGE, WEIGHT), testBow, new Bow(DIFFERENT_NAME, DAMAGE, WEIGHT),
                testKnife, new Bow(BOW_NAME, DIFFERENT_DAMAGE, WEIGHT), new Bow(BOW_NAME, DAMAGE, DIFFERENT_WEIGHT));
    }
}
