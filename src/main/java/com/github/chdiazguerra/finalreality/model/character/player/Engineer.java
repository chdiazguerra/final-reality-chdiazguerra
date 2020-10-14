package com.github.chdiazguerra.finalreality.model.character.player;

import com.github.chdiazguerra.finalreality.model.character.AbstractCharacter;
import com.github.chdiazguerra.finalreality.model.character.ICharacter;
import com.github.chdiazguerra.finalreality.model.weapon.Weapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class Engineer extends AbstractCharacter{

    /**
     *
     * @param name
     * @param turnsQueue
     */

    public Engineer(@NotNull String name,
                  @NotNull BlockingQueue<ICharacter> turnsQueue) {
        super(turnsQueue, name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Engineer.class);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Engineer)) {
            return false;
        }
        final Engineer that = (Engineer) o;
        return getName().equals(that.getName());
    }

    @Override
    public void equip(Weapon weapon) {
        this.equippedWeapon = weapon;
    }
}