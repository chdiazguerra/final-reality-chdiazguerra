package com.github.chdiazguerra.finalreality.controller.phases;

import com.github.chdiazguerra.finalreality.controller.GameController;
import com.github.chdiazguerra.finalreality.model.character.player.IPlayerCharacter;
import org.jetbrains.annotations.NotNull;

/**
 * Class that holds all the methods for the different phases of the game.
 *
 * @author Christian Diaz Guerra
 */
public abstract class Phase {
    protected GameController controller;

    /**
     * Sets the game controller.
     */
    public void setController(final @NotNull GameController controller) {
        this.controller = controller;
    }

    /**
     * Performs the change of phase
     */
    protected void changePhase(Phase phase) {
        controller.setPhase(phase);
    }

    /**
     * Changes the phase to EndTurnPhase and performs the actions due to this change.
     */
    protected void toEndTurnPhase() {
        changePhase(new EndTurnPhase());
        controller.removeFirstInQueue();
        controller.waitTurnActualCharacter();
    }

    /**
     * Changes the phase to EnemyTurnPhase
     */
    protected void toEnemyTurnPhase() {
        changePhase(new EnemyTurnPhase());
    }

    /**
     * Changes the phase to PlayerTurnPhase
     */
    protected void toPlayerTurnPhase() {
        changePhase(new PlayerTurnPhase());
    }

    /**
     * Changes the phase to SelectTargetPhase
     */
    protected void toSelectTargetPhase() {
        changePhase(new SelectTargetPhase());
    }

    /**
     * Changes the phase to WaitingQueuePhase
     */
    protected void toWaitingQueuePhase() {
        changePhase(new WaitingQueuePhase());
    }

    /**
     * Changes the phase to SelectWeaponPhase
     */
    protected void toSelectWeaponPhase() {
        changePhase(new SelectWeaponPhase());
    }

    /**
     * Changes the phase to BeginTurnPhase
     */
    protected void toBeginTurnPhase() {
        changePhase(new BeginTurnPhase());
    }

    /**
     * Changes the phase to WinGamePhase
     */
    protected void toWinGamePhase(){
        changePhase(new WinGamePhase());
    }

    /**
     * Changes the phase to LostGamePhase
     */
    protected void toLostGamePhase(){
        changePhase(new LostGamePhase());
    }

    /**
     * Called when the game all enemies are defeated
     */
    public void allEnemiesDead(){
        //Do nothing
    }

    /**
     * Called when all the player characters are defeated
     */
    public void allPlayerCharactersDead(){
        //Do nothing
    }


    /**
     * Method called when a character is added to Queue. It performs the changes when the phase corresponds.
     */
    public void characterAdded() {
        //do nothing
    }


    /**
     * Goes next
     * @throws InvalidMovementException
     */
    public void next() throws InvalidMovementException {
        throw new InvalidMovementException("Can't go next on " + this.toString());

    }

    /**
     * Attacks a given playerCharacter
     * @throws InvalidMovementException
     */
    public void attackPlayer(IPlayerCharacter playerCharacter) throws InvalidMovementException {
        throw new InvalidMovementException("Can't attack player on " + this.toString());
    }

    /**
     * Goes back
     * @throws InvalidMovementException
     */
    public void back() throws InvalidMovementException {
        throw new InvalidMovementException("Can't go back on " + this.toString());
    }

    /**
     * Performs the changes when a character attacks.
     * @throws InvalidMovementException
     */
    public void attack() throws InvalidMovementException {
        throw new InvalidMovementException("Can't attack on " + this.toString());
    }

    /**
     * Attacks an enemy given its index in the enemy list.
     * @throws InvalidMovementException
     */
    public void attackEnemy(int indexEnemy) throws InvalidMovementException {
        throw new InvalidMovementException("Can't attack enemy on " + this.toString());
    }

    /**
     * Goes to inventory.
     * @throws InvalidMovementException
     */
    public void toInventory() throws InvalidMovementException {
        throw new InvalidMovementException("Can't go to inventory on " + this.toString());
    }

    /**
     * Goes to player turn
     * @throws InvalidMovementException
     */
    public void toPlayerTurn() throws InvalidMovementException {
        throw new InvalidMovementException("Can't go to player turn on " + this.toString());
    }

    /**
     * Goes to enemy Turn
     * @throws InvalidMovementException
     */
    public void toEnemyTurn() throws InvalidMovementException {
        throw new InvalidMovementException("Can't go to enemy turn on " + this.toString());
    }

    /**
     * Equip a weapon to the player in turn, given its index in the inventory.
     * @throws InvalidMovementException
     */
    public void equipWeapon(int indexWeapon) throws InvalidMovementException {
        throw new InvalidMovementException("Can't equip weapon on " + this.toString());
    }

    /**
     * Returns true if it is player's turn. False otherwise.
     */
    public boolean isPlayerTurn() {
        return false;
    }

    /**
     * Returns true if it is enemy's turn. False otherwise.
     */
    public boolean isEnemyTurn(){
        return false;
    }

    /**
     * Returns true if the character is selecting weapon. False otherwise.
     */
    public boolean isSelectingWeapon(){
        return false;
    }

    /**
     * Returns true if the character is selecting target. False otherwise.
     */
    public boolean isSelectingTarget(){
        return false;
    }

    /**
     * Returns true if it is waiting for characters in the queue. False otherwise.
     */
    public boolean isWaiting(){
        return false;
    }

    /**
     * Returns true if the game ends in a victory. False otherwise.
     */
    public boolean isWon(){
        return false;
    }

    /**
     * Returns true if the game ends in a defeat. False otherwise.
     */
    public boolean isLost(){
        return false;
    }

    /**
     * Returns true if it is in EndTurnPhase. False otherwise.
     */
    public boolean isEndTurn(){
        return false;
    }
}
