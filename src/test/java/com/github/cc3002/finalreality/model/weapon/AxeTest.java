package com.github.cc3002.finalreality.model.weapon;

import com.github.chdiazguerra.finalreality.model.weapon.Axe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A class containing the test for Axe type weapon.
 *
 * @author Christian Díaz Guerra
 * @see Axe
 */
class AxeTest extends AbstractWeaponTest {

    @BeforeEach
    void setUp(){
        basicSetUp();
    }

    @Test
    void constructorTest(){
        checkConstruction(new Axe(AXE_NAME, DAMAGE, WEIGHT), testAxe, new Axe(DIFFERENT_NAME, DAMAGE, WEIGHT),
                testKnife, new Axe(AXE_NAME, DIFFERENT_DAMAGE, WEIGHT), new Axe(AXE_NAME, DAMAGE, DIFFERENT_WEIGHT));
    }
}
