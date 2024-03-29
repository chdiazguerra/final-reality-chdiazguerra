package com.github.cc3002.finalreality.model.weapon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.github.chdiazguerra.finalreality.model.weapon.*;

/**
 * Abstract class containing the common test for all the types of weapons.
 *
 * @author Christian Díaz Guerra
 * @see IWeapon
 */
class AbstractWeaponTest {

  protected static final String AXE_NAME = "Test Axe";
  protected static final String STAFF_NAME = "Test Staff";
  protected static final String SWORD_NAME = "Test Sword";
  protected static final String BOW_NAME = "Test Bow";
  protected static final String KNIFE_NAME = "Test Knife";
  protected static final int DAMAGE = 15;
  protected static final int WEIGHT = 10;

  protected final String DIFFERENT_NAME = "Different Name";
  protected final int DIFFERENT_DAMAGE = 20;
  protected final int DIFFERENT_WEIGHT = 15;

  protected Axe testAxe;
  protected Staff testStaff;
  protected Sword testSword;
  protected Bow testBow;
  protected Knife testKnife;

  /**
   * Initializes the variables
   */
  protected void basicSetUp(){
    testAxe = new Axe(AXE_NAME, DAMAGE, WEIGHT);
    testStaff = new Staff(STAFF_NAME, DAMAGE, WEIGHT);
    testSword = new Sword(SWORD_NAME, DAMAGE, WEIGHT);
    testBow = new Bow(BOW_NAME, DAMAGE, WEIGHT);
    testKnife = new Knife(KNIFE_NAME, DAMAGE, WEIGHT);
  }

  /**
   * Checks if the initialization is correct
   */
  protected void checkConstruction(final IWeapon expectedWeapon, final IWeapon testEqualWeapon,
                         final IWeapon sameTypeDifferentNameWeapon, final IWeapon differentTypeWeapon,
                                   final IWeapon sameTypeDifferentDamageWeapon,
                                   final IWeapon sameTypeDifferentWeightWeapon){
    assertEquals(testEqualWeapon, testEqualWeapon);

    assertEquals(expectedWeapon, testEqualWeapon);
    assertEquals(expectedWeapon.hashCode(), testEqualWeapon.hashCode());

    assertNotEquals(sameTypeDifferentNameWeapon, testEqualWeapon);
    assertNotEquals(sameTypeDifferentNameWeapon.hashCode(), testEqualWeapon.hashCode());

    assertNotEquals(testEqualWeapon, differentTypeWeapon);
    assertNotEquals(testEqualWeapon.hashCode(), differentTypeWeapon.hashCode());

    assertNotEquals(sameTypeDifferentDamageWeapon, testEqualWeapon);
    assertNotEquals(sameTypeDifferentDamageWeapon.hashCode(), testEqualWeapon.hashCode());

    assertNotEquals(sameTypeDifferentWeightWeapon, testEqualWeapon);
    assertNotEquals(sameTypeDifferentWeightWeapon.hashCode(), testEqualWeapon.hashCode());
  }

}