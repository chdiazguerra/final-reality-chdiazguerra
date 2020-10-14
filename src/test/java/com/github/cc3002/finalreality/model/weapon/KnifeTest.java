package com.github.cc3002.finalreality.model.weapon;

import com.github.chdiazguerra.finalreality.model.weapon.Knife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class containing the test for Knife type weapon.
 *
 * @author Christian Díaz Guerra
 * @see Knife
 */
class KnifeTest extends AbstractWeaponTest{

    @BeforeEach
    void setUp(){
        basicSetUp();
    }

    @Test
    void constructorTest(){
        checkConstruction(new Knife(KNIFE_NAME, DAMAGE, WEIGHT), testKnife, new Knife(DIFFERENT_NAME, DAMAGE, WEIGHT),
                testAxe, new Knife(KNIFE_NAME, DIFFERENT_DAMAGE, WEIGHT), new Knife(KNIFE_NAME, DAMAGE, DIFFERENT_WEIGHT));
    }
}
