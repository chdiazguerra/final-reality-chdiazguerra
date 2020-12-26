package com.github.chdiazguerra.finalreality.controller.phases;

import com.github.chdiazguerra.finalreality.controller.GameController;
import org.jetbrains.annotations.NotNull;

public abstract class Phase {
    protected GameController controller;

    public void setController(final @NotNull GameController controller){
        this.controller = controller;
    }

    protected void changePhase(Phase phase){
        controller.setPhase(phase);
    }

    public void toEnemyTurn() {

    }

    public void toPlayerTurn() {
    }

    public void back() {

    }

    public void toSelectWeapon(){

    }

    public void equipWeapon(int indexWeapon){

    }

    public void toSelectTarget(){

    }

    public void attackEnemy(int indexEnemy){

    }

    public void attackPlayer(int indexPlayer){

    }

    public void toEndTurn(){

    }

    public void toBeginTurn(){

    }

    public void tryBeginTurn(){

    }

    public void characterAdded(){

    }
}
