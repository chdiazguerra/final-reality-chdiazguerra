package com.github.chdiazguerra.finalreality.controller.phases;

import com.github.chdiazguerra.finalreality.controller.GameController;
import com.github.chdiazguerra.finalreality.model.character.player.IPlayerCharacter;
import org.jetbrains.annotations.NotNull;

public abstract class Phase {
    protected GameController controller;

    public void setController(final @NotNull GameController controller){
        this.controller = controller;
    }

    protected void changePhase(Phase phase){
        controller.setPhase(phase);
    }

    protected void toEndTurnPhase(){
        changePhase(new EndTurnPhase());
        controller.waitTurnActualCharacter();
    }

    protected void toEnemyTurnPhase(){
        changePhase(new EnemyTurnPhase());
    }

    protected void toPlayerTurnPhase(){
        changePhase(new PlayerTurnPhase());
    }

    protected void toSelectTargetPhase(){
        changePhase(new SelectTargetPhase());
    }

    protected void toWaitingQueuePhase(){
        changePhase(new WaitingQueuePhase());
    }

    protected void toSelectWeaponPhase(){
        changePhase(new SelectWeaponPhase());
    }


    public void characterAdded(){

    }


    public void next() {

    }

    public void attackPlayer(IPlayerCharacter playerCharacter) {

    }

    public void back() {

    }

    public void attack(){

    }

    public void attackEnemy(int indexEnemy) {
    }

    public void toInventory() {

    }

    public void toPlayerTurn() {
    }

    public void toEnemyTurn() {
    }

    public void equipWeapon(int indexWeapon) {

    }
}
