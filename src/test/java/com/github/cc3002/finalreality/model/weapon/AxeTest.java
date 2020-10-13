package com.github.cc3002.finalreality.model.weapon;

import com.github.chdiazguerra.finalreality.model.weapon.Axe;
import com.github.chdiazguerra.finalreality.model.weapon.Knife;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AxeTest extends AbstractWeaponTest {

    @BeforeEach
    void setUp(){
        basicSetUp();
    }

    @Test
    void constructorTest(){
        checkConstruction(new Axe(AXE_NAME, DAMAGE, WEIGHT), testAxe, new Axe(DIFFERENT_NAME, DAMAGE, WEIGHT),
                testKnife);
    }
}
