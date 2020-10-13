package com.github.cc3002.finalreality.model.weapon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.github.chdiazguerra.finalreality.model.weapon.*;

class AbstractWeaponTest {

  protected static final String AXE_NAME = "Test Axe";
  protected static final String STAFF_NAME = "Test Staff";
  protected static final String SWORD_NAME = "Test Sword";
  protected static final String BOW_NAME = "Test Bow";
  protected static final String KNIFE_NAME = "Test Knife";
  protected static final int DAMAGE = 15;
  protected static final int WEIGHT = 10;

  protected final String DIFFERENT_NAME = "Different Name";

  protected Axe testAxe;
  protected Staff testStaff;
  protected Sword testSword;
  protected Bow testBow;
  protected Knife testKnife;

  protected void basicSetUp(){
    testAxe = new Axe(AXE_NAME, DAMAGE, WEIGHT);
    testStaff = new Staff(STAFF_NAME, DAMAGE, WEIGHT);
    testSword = new Sword(SWORD_NAME, DAMAGE, WEIGHT);
    testBow = new Bow(BOW_NAME, DAMAGE, WEIGHT);
    testKnife = new Knife(KNIFE_NAME, DAMAGE, WEIGHT);
  }


  protected void checkConstruction(final IWeapon expectedWeapon, final IWeapon testEqualWeapon,
                         final IWeapon sameTypeDifferentWeapon, final IWeapon differentTypeWeapon){
    assertEquals(testEqualWeapon, testEqualWeapon);
    assertEquals(expectedWeapon, testEqualWeapon);
    assertEquals(expectedWeapon.hashCode(), testEqualWeapon.hashCode());
    assertNotEquals(sameTypeDifferentWeapon, testEqualWeapon);
    assertNotEquals(sameTypeDifferentWeapon.hashCode(), testEqualWeapon.hashCode());
    assertNotEquals(testEqualWeapon, differentTypeWeapon);
    assertNotEquals(testEqualWeapon, differentTypeWeapon);
  }

}