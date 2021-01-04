package com.github.chdiazguerra.finalreality.controller.phases;

import com.github.chdiazguerra.finalreality.controller.GameController;
import com.github.chdiazguerra.finalreality.model.character.player.IPlayerCharacter;
import org.jetbrains.annotations.NotNull;

public abstract class Phase {
    protected GameController controller;

    public void setController(final @NotNull GameController controller) {
        this.controller = controller;
    }

    protected void changePhase(Phase phase) {
        controller.setPhase(phase);
    }

    protected void toEndTurnPhase() {
        changePhase(new EndTurnPhase());
        controller.removeFirstInQueue();
        controller.waitTurnActualCharacter();
    }

    protected void toEnemyTurnPhase() {
        changePhase(new EnemyTurnPhase());
    }

    protected void toPlayerTurnPhase() {
        changePhase(new PlayerTurnPhase());
    }

    protected void toSelectTargetPhase() {
        changePhase(new SelectTargetPhase());
    }

    protected void toWaitingQueuePhase() {
        changePhase(new WaitingQueuePhase());
    }

    protected void toSelectWeaponPhase() {
        changePhase(new SelectWeaponPhase());
    }

    protected void toBeginTurnPhase() {
        changePhase(new BeginTurnPhase());
    }


    public void characterAdded() {
        //do nothing
    }


    public void next() throws InvalidMovementException {
        throw new InvalidMovementException("Can't go next on " + this.toString());

    }

    public void attackPlayer(IPlayerCharacter playerCharacter) throws InvalidMovementException {
        throw new InvalidMovementException("Can't attack player on " + this.toString());
    }

    public void back() throws InvalidMovementException {
        throw new InvalidMovementException("Can't go back on " + this.toString());
    }

    public void attack() throws InvalidMovementException {
        throw new InvalidMovementException("Can't attack on " + this.toString());
    }

    public void attackEnemy(int indexEnemy) throws InvalidMovementException {
        throw new InvalidMovementException("Can't attack enemy on " + this.toString());
    }

    public void toInventory() throws InvalidMovementException {
        throw new InvalidMovementException("Can't go to inventory on " + this.toString());
    }

    public void toPlayerTurn() throws InvalidMovementException {
        throw new InvalidMovementException("Can't go to player turn on " + this.toString());
    }

    public void toEnemyTurn() throws InvalidMovementException {
        throw new InvalidMovementException("Can't go to enemy turn on " + this.toString());
    }

    public void equipWeapon(int indexWeapon) throws InvalidMovementException {
        throw new InvalidMovementException("Can't equip weapon on " + this.toString());
    }

    public boolean isPlayerTurn() {
        return false;
    }

    public boolean isEnemyTurn(){
        return false;
    }

    public boolean isSelectingWeapon(){
        return false;
    }

    public boolean isSelectingTarget(){
        return false;
    }

    public boolean isWaiting(){
        return false;
    }

    public boolean isWon(){
        return false;
    }

    public boolean isLost(){
        return false;
    }

    public boolean isEndTurn(){
        return false;
    }
}
