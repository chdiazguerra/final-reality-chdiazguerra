package com.github.cc3002.finalreality.model.character;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.github.chdiazguerra.finalreality.model.character.player.IPlayerCharacter;
import com.github.chdiazguerra.finalreality.model.weapon.*;

import java.util.ArrayList;

/**
 * Abstract class containing the common methods and variables for all the types of player characters.
 *
 * @author Christian Díaz Guerra
 */
public class AbstractPlayerCharacterTest extends AbstractCharacterTest{

    protected Axe axeTest;
    protected Bow bowTest;
    protected Knife knifeTest;
    protected Staff staffTest;
    protected Sword swordTest;
    protected final String NAME_WEAPON = "TestWeapon";
    protected final int DAMAGE_WEAPON = 15;
    protected final int WEIGHT_WEAPON = 15;
    protected ArrayList<IWeapon> acceptedWeapons;
    protected ArrayList<IWeapon> rejectedWeapons;


    /**
     * Checks if the equip method is correct, given an alive character
     * @param character
     *      character who performs the equip method
     * @param accepted
     *      list of weapons that can be equipped to character
     * @param rejected
     *      list of weapons that cannot be equipped to character
     */
    protected void equipWeapons(IPlayerCharacter character, ArrayList<IWeapon> accepted, ArrayList<IWeapon> rejected){
        assertNull(character.getEquippedWeapon());
        for(IWeapon weapon: accepted){
            character.equip(weapon);
            assertEquals(weapon, character.getEquippedWeapon());
        }
        for(IWeapon weapon: rejected){
            character.equip(weapon);
            assertEquals(accepted.get(accepted.size()-1), character.getEquippedWeapon());
        }

    }

    /**
     * Checks if the equip method is correct,
     * given a dead character and lists of accepted and rejected weapons
     * @param character
     *      dead character who performs the equip method
     * @param accepted
     *      list of weapons that can be equipped to character
     * @param rejected
     *      list of weapons that cannot be equipped to character
     */
    protected void equipToDead(IPlayerCharacter character, ArrayList<IWeapon> accepted, ArrayList<IWeapon> rejected){
        for(IWeapon weapon: accepted){
            character.equip(weapon);
            assertNull(character.getEquippedWeapon());
        }
        for(IWeapon weapon: rejected){
            character.equip(weapon);
            assertNull(character.getEquippedWeapon());
        }
    }

    /**
     * Initializes the weapons variables
     */
    protected void weaponsCreation(){
        acceptedWeapons = new ArrayList<>();
        rejectedWeapons = new ArrayList<>();
        axeTest = new Axe(NAME_WEAPON, DAMAGE_WEAPON, WEIGHT_WEAPON);
        bowTest = new Bow(NAME_WEAPON, DAMAGE_WEAPON, WEIGHT_WEAPON);
        knifeTest = new Knife(NAME_WEAPON, DAMAGE_WEAPON, WEIGHT_WEAPON);
        staffTest = new Staff(NAME_WEAPON, DAMAGE_WEAPON, WEIGHT_WEAPON);
        swordTest = new Sword(NAME_WEAPON, DAMAGE_WEAPON, WEIGHT_WEAPON);
    }

}
