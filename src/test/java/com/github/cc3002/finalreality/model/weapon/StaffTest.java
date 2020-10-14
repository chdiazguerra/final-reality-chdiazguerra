package com.github.cc3002.finalreality.model.weapon;

import com.github.chdiazguerra.finalreality.model.weapon.Axe;
import com.github.chdiazguerra.finalreality.model.weapon.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StaffTest extends AbstractWeaponTest{
    @BeforeEach
    void setUp(){
        basicSetUp();
    }

    @Test
    void constructorTest(){
        checkConstruction(new Staff(STAFF_NAME, DAMAGE, WEIGHT), testStaff, new Staff(DIFFERENT_NAME, DAMAGE, WEIGHT),
                testKnife, new Staff(STAFF_NAME, DIFFERENT_DAMAGE, WEIGHT), new Staff(STAFF_NAME, DAMAGE, DIFFERENT_WEIGHT));
    }
}
