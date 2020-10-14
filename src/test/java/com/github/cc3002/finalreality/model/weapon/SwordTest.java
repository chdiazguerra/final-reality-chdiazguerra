package com.github.cc3002.finalreality.model.weapon;


import com.github.chdiazguerra.finalreality.model.weapon.Axe;
import com.github.chdiazguerra.finalreality.model.weapon.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SwordTest extends AbstractWeaponTest{
    @BeforeEach
    void setUp(){
        basicSetUp();
    }

    @Test
    void constructorTest(){
        checkConstruction(new Sword(SWORD_NAME, DAMAGE, WEIGHT), testSword, new Sword(DIFFERENT_NAME, DAMAGE, WEIGHT),
                testKnife, new Sword(SWORD_NAME, DIFFERENT_DAMAGE, WEIGHT), new Sword(SWORD_NAME, DAMAGE, DIFFERENT_WEIGHT));
    }
}
