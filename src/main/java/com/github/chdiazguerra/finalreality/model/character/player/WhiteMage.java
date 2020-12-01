package com.github.chdiazguerra.finalreality.model.character.player;

import com.github.chdiazguerra.finalreality.model.character.ICharacter;
import com.github.chdiazguerra.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * A class that holds all the information of a White Mage class character.
 *
 * @author Christian Díaz Guerra
 */
public class WhiteMage extends AbstractPlayerCharacter {

    /**
     * Creates a new character of White Mage class, with a name and the queue.
     *
     * @param name
     *     the character's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     */

    public WhiteMage(@NotNull String name,
                  @NotNull BlockingQueue<ICharacter> turnsQueue, int life, int defense) {
        super(turnsQueue, name, life, defense);
    }

    @Override
    public int hashCode() {
        return Objects.hash(WhiteMage.class, getName());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WhiteMage)) {
            return false;
        }
        final WhiteMage that = (WhiteMage) o;
        return getName().equals(that.getName());
    }

    @Override
    public void equip(IWeapon weapon) {
        if(this.getIsAlive()) {
            weapon.equippedByWhiteMage(this);
        }
    }
}
