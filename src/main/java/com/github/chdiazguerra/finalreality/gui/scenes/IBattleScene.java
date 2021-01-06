package com.github.chdiazguerra.finalreality.gui.scenes;

/**
 * Interface for the battle scene (Null pattern). It has the public methods that can be called by the controller.
 * @author Christian Diaz Guerra
 */
public interface IBattleScene {

    /**
     * Enables the button of the waiting box.
     */
    void refreshWaitingNext();

    /**
     * Refresh the button of the enemy in indexEnemy position. If the enemy is dead, it disables the button.
     */
    void refreshEnemyColumns(int indexEnemy);


    /**
     * Refresh the info of the player character in indexPlayer position, doing a fade transition and updating the info.
     * If the character is dead, it changes the color of the label to red.
     */
    void refreshInfoColumn(int indexPlayer);

    /**
     * Puts a waiting label in the bottom box.
     */
    void waitingText();

    /**
     * Puts the player turn box, with a label indicating the name of the player and buttons for attack
     * and go to the inventory.
     */
    void playerTurnBox();

    /**
     * Puts the select target text and a button to go back to the player turn box.
     */
    void selectTargetText();


    /**
     * Puts the label indicating the info of the attacking. It says the attacking character, the attacked and the
     * damage received by the last one.
     */
    void attackInfo(String attackingName, int damage, String attackedName);

    /**
     * Puts the inventory box, with all the buttons for selecting a weapon from the inventory for equipping it to the
     * player character who is in turn.
     */
    void inventoryBox();


    /**
     * Puts the info indicating the character who is in turn.
     */
    void turnBox();

    /**
     * Puts the info for indicating that the enemy is choosing an action.
     */
    void enemyChoosing();


    /**
     * Puts the info for the winning game.
     */
    void winGame();

    /**
     * Puts the info for the lost game.
     */
    void lostGame();
}
