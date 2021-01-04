package com.github.chdiazguerra.finalreality.controller;

import com.github.chdiazguerra.finalreality.controller.handlers.*;
import com.github.chdiazguerra.finalreality.controller.phases.*;
import com.github.chdiazguerra.finalreality.gui.scenes.BattleScene;
import com.github.chdiazguerra.finalreality.gui.scenes.IBattleScene;
import com.github.chdiazguerra.finalreality.model.character.Enemy;
import com.github.chdiazguerra.finalreality.model.character.ICharacter;
import com.github.chdiazguerra.finalreality.model.character.player.IPlayerCharacter;
import com.github.chdiazguerra.finalreality.model.character.player.classes.*;
import com.github.chdiazguerra.finalreality.model.weapon.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The controller for the game. It has all the methods that the user can use for the interaction with the model.
 * @author Christian Díaz Guerra
 */
public class GameController {

    private final IHandler deadPlayerCharacterHandler = new DeadPlayerCharacterHandler(this);
    private final IHandler deadEnemyHandler = new DeadEnemyHandler(this);
    private final IHandler characterAddedToQueue = new CharacterAddedToQueue(this);
    private final IHandler damageReceivedHandler = new DamageReceivedHandler(this);

    private Phase phase;

    private BlockingQueue<ICharacter> turnsQueue = new LinkedBlockingQueue<>();

    private final List<IPlayerCharacter> playerCharacters = new ArrayList<>();
    private final List<Enemy> enemies = new ArrayList<>();
    private final List<IWeapon> inventory = new ArrayList<>();
    private final HashMap<IWeapon, IPlayerCharacter> weaponToPlayer = new HashMap<>();

    private List<IPlayerCharacter> alivePlayerCharacters = new ArrayList<>();
    private int enemiesAlive;

    private ICharacter characterTurn;

    private Random rng;

    public IBattleScene scene;

    private int damageReceived;


    public GameController(){
        rng = new Random();
        createInventory();
    }

    /**
     * Returns the list of player's characters
     */
    public List<IPlayerCharacter> getAllPlayerCharacters() {
        return playerCharacters;
    }

    /**
     * Creates a new Black Mage given the parameters, with and equipped weapon and adding
     * it to the list of player's characters.
     * Also, adds the listener for dead and the queue to the character.
     */
    public void createBlackMage(String name, int life, int defense) {
        BlackMage newPlayerCharacter = new BlackMage(name, turnsQueue, life, defense);
        newPlayerCharacter.addDeadListener(deadPlayerCharacterHandler);
        newPlayerCharacter.addAddedToQueueListener(characterAddedToQueue);
        newPlayerCharacter.addDamageReceivedListener(damageReceivedHandler);
        createStaff("Init Staff");
        equipWeaponFromInventory(inventory.size()-1, newPlayerCharacter);
        playerCharacters.add(newPlayerCharacter);
    }

    /**
     * Creates a new Engineer given the parameters, adding it to the list of player's characters.
     * Also, adds the listener for dead and the queue to the character.
     */
    public void createEngineer(String name, int life, int defense) {
        Engineer newPlayerCharacter = new Engineer(name, turnsQueue, life, defense);
        newPlayerCharacter.addDeadListener(deadPlayerCharacterHandler);
        newPlayerCharacter.addAddedToQueueListener(characterAddedToQueue);
        newPlayerCharacter.addDamageReceivedListener(damageReceivedHandler);
        createAxe("Init Axe");
        equipWeaponFromInventory(inventory.size()-1, newPlayerCharacter);
        playerCharacters.add(newPlayerCharacter);
    }

    /**
     * Creates a new Knight given the parameters, adding it to the list of player's characters.
     * Also, adds the listener for dead and the queue to the character.
     */
    public void createKnight(String name, int life, int defense){
        Knight newPlayerCharacter = new Knight(name, turnsQueue, life, defense);
        newPlayerCharacter.addDeadListener(deadPlayerCharacterHandler);
        newPlayerCharacter.addAddedToQueueListener(characterAddedToQueue);
        newPlayerCharacter.addDamageReceivedListener(damageReceivedHandler);
        createSword("Init Sword");
        equipWeaponFromInventory(inventory.size()-1, newPlayerCharacter);
        playerCharacters.add(newPlayerCharacter);
    }

    /**
     * Creates a new Thief given the parameters, adding it to the list of player's characters.
     * Also, adds the listener for dead and the queue to the character.
     */
    public void createThief(String name, int life, int defense) {
        Thief newPlayerCharacter = new Thief(name, turnsQueue, life, defense);
        newPlayerCharacter.addDeadListener(deadPlayerCharacterHandler);
        newPlayerCharacter.addAddedToQueueListener(characterAddedToQueue);
        newPlayerCharacter.addDamageReceivedListener(damageReceivedHandler);
        createBow("Init Bow");
        equipWeaponFromInventory(inventory.size()-1, newPlayerCharacter);
        playerCharacters.add(newPlayerCharacter);
    }

    /**
     * Creates a new White Mage given the parameters, adding it to the list of player's characters.
     * Also, adds the listener for dead and the queue to the character.
     */
    public void createWhiteMage(String name, int life, int defense) {
        WhiteMage newPlayerCharacter = new WhiteMage(name, turnsQueue, life, defense);
        newPlayerCharacter.addDeadListener(deadPlayerCharacterHandler);
        newPlayerCharacter.addAddedToQueueListener(characterAddedToQueue);
        newPlayerCharacter.addDamageReceivedListener(damageReceivedHandler);
        createStaff("Init Staff");
        equipWeaponFromInventory(inventory.size()-1, newPlayerCharacter);
        playerCharacters.add(newPlayerCharacter);
    }

    /**
     * Returns the player's character in the position index of the player's characters list
     */
    public IPlayerCharacter getPlayerCharacter(int index) {
        return playerCharacters.get(index);
    }

    /**
     * Returns the enemies list.
     */
    public List<Enemy> getAllEnemies() {
        return enemies;
    }

    /**
     * Creates a new enemy and adds it to the enemies list
     */
    public void createEnemy(String name, int weight, int life, int defense, int attack) {
        Enemy newEnemy = new Enemy(name, weight, turnsQueue, life, defense, attack);
        newEnemy.addDeadListener(deadEnemyHandler);
        newEnemy.addAddedToQueueListener(characterAddedToQueue);
        newEnemy.addDamageReceivedListener(damageReceivedHandler);
        enemies.add(newEnemy);
    }

    /**
     * Return the enemy in the position index of the enemies list
     */
    public Enemy getEnemy(int index) {
        return enemies.get(index);
    }

    /**
     * Returns the inventory of the player
     */
    public List<IWeapon> getInventory() {
        return inventory;
    }

    /**
     * Adds the given weapon to the inventory
     */
    private void putWeapon(IWeapon weapon){
        inventory.add(weapon);
        weaponToPlayer.put(weapon, null);
    }

    /**
     * Creates a new Axe, with damage between 12-20, weight between 15-30, and adds it to the inventory
     */
    public void createAxe(String name){
        putWeapon(new Axe(name, rng.nextInt(8)+12, rng.nextInt(15)+25));
    }

    /**
     * Creates a new Bow, with damage between 12-17, weight between 10-20, and adds it to the inventory
     */
    public void createBow(String name){
        putWeapon(new Bow(name, rng.nextInt(5)+12, rng.nextInt(10)+20));
    }

    /**
     * Creates a new Knife, with damage between 8-15, weight between 5-15, and adds it to the inventory
     */
    public void createKnife(String name){
        putWeapon(new Knife(name, rng.nextInt(7)+8, rng.nextInt(10)+15));
    }

    /**
     * Creates a new Staff, with damage between 15-20, weight between 20-25, and adds it to the inventory
     */
    public void createStaff(String name){
        putWeapon(new Staff(name, rng.nextInt(5)+15, rng.nextInt(5)+30));
    }

    /**
     * Creates a new Sword, with damage between 10-17, weight between 15-25, and adds it to the inventory
     */
    public void createSword(String name){
        putWeapon(new Sword(name, rng.nextInt(7)+10, rng.nextInt(10)+25));
    }

    /**
     * Returns the weapon of the inventory in the position index
     */
    public IWeapon getInventoryWeapon(int index) {
        return inventory.get(index);
    }

    /**
     * Returns the equipped weapon of the player's character in the position index of the list of players characters
     */
    public IWeapon getEquippedWeapon(int index) {
        return getPlayerCharacter(index).getEquippedWeapon();
    }

    /**
     * Equips the weapon in the position indexWeapon of the inventory to the character given.
     */
    public void equipWeaponFromInventory(int indexWeapon, IPlayerCharacter character) {
        if(!weaponIsEquipped(indexWeapon)) {
            weaponToPlayer.put(character.getEquippedWeapon(), null);
            character.equip(getInventoryWeapon(indexWeapon));
            weaponToPlayer.put(character.getEquippedWeapon(), character);
        }
    }

    /**
     * Returns true if the weapon in position index of the inventory is equipped to some player character.
     * Else, returns false.
     */
    public boolean weaponIsEquipped(int index){
        return weaponToPlayer.get(getInventoryWeapon(index)) != null;
    }

    /**
     * Returns the character's life
     */
    public int getCharacterLife(ICharacter character) {
        return character.getLife();
    }

    /**
     * Returns the character's name
     */
    public String getCharacterName(ICharacter character) {
        return character.getName();
    }

    /**
     * Returns the character's defense
     */
    public int getCharacterDefense(ICharacter character) {
        return character.getDefense();
    }

    /**
     * Performs the attack from a player character to the enemy in position indexEnemy of the list of enemies
     */
    public void attackToEnemy(ICharacter playerCharacter, int indexEnemy) {
        playerCharacter.attack(getEnemy(indexEnemy));
    }

    /**
     * Performs the attack from an enemy to the player's character in position indexPlayer
     * of the list of player's characters
     */
    public void attackToPlayer(ICharacter enemy, ICharacter playerCharacter) {
        enemy.attack(playerCharacter);
    }

    /**
     * Removes the dead playerCharacter from the list of player's characters
     */
    public void deadPlayerCharacter(IPlayerCharacter playerCharacter) {
        alivePlayerCharacters.remove(playerCharacter);
        turnsQueue.remove(playerCharacter);
        if(alivePlayerCharacters.isEmpty()){
            gameOverLost();
        }
    }

    /**
     * Performs the actions for the lost game
     */
    public void gameOverLost(){
        setPhase(new LostGamePhase());
    }

    /**
     * Removes the enemy from the list of enemies
     */
    public void deadEnemy(Enemy enemy) {
        enemiesAlive--;
        turnsQueue.remove(enemy);
        if(enemiesAlive==0){
            gameOverWin();
        }
    }

    /**
     * Performs the actions for the won game
     */
    public void gameOverWin() {
        setPhase(new WinGamePhase());
    }


    /**
     * Returns the character that is playing
     */
    public ICharacter getCharacterTurn(){
        return characterTurn;
    }



    /**
     * Begins the turn of the first character in the queue
     */
    public void beginTurn(){
        characterTurn = turnsQueue.peek();
        if(playerCharacters.contains(characterTurn)){
            try {
                phase.toPlayerTurn();
            } catch (InvalidMovementException e) {
                e.printStackTrace();
            }
        }else{
            try {
                phase.toEnemyTurn();
            } catch (InvalidMovementException e) {
                e.printStackTrace();
            }
        }
        scene.refreshWaitingNext();
    }


    public void setPhase(Phase newPhase) {
        this.phase = newPhase;
        newPhase.setController(this);
    }


    public boolean queueIsEmpty(){
        return turnsQueue.isEmpty();
    }

    private void createInventory(){
        createAxe("Axe");
        createBow("Bow");
        createKnife("Knife");
        createStaff("Staff");
        createSword("Sword");
    }


    public boolean isEnemyDead(int indexEnemy){
        return !enemies.get(indexEnemy).getIsAlive();
    }

    public boolean isPlayerDead(int indexPlayer){
        return !((ICharacter) playerCharacters.get(indexPlayer)).getIsAlive();
    }

    public String getPlayerCharacterInfo(int indexPlayerCharacter){
        String name = getCharacterName((ICharacter) playerCharacters.get(indexPlayerCharacter));
        int life = getCharacterLife((ICharacter) playerCharacters.get(indexPlayerCharacter));
        String isAlive = isPlayerDead(indexPlayerCharacter) ? "DEAD" : "ALIVE";
        return name + "\n HP " + life + "\t" + isAlive;
    }


    private String[] getWeaponInfo(IWeapon weapon){
        String name = weapon.getName();
        String damage = "ATK " + weapon.getDamage();
        String weight = "WGT " + weapon.getWeight();
        String equipped = weaponIsEquipped(inventory.indexOf(weapon)) ? "EQU" : "INV";

        return new String[]{name, damage, weight, equipped};
    }

    public String infoWeaponPlayerTurn(){
        return String.join("\n", getWeaponInfo(((IPlayerCharacter) characterTurn).getEquippedWeapon()));
    }

    public String weaponInfo(IWeapon weapon){
        return String.join(" ", getWeaponInfo(weapon));
    }

    public void setScene(IBattleScene scene){
        this.scene = scene;
    }

    public void initialize(){
        setPhase(new WaitingQueuePhase());
        alivePlayerCharacters.addAll(playerCharacters);
        for(int i = 0; i<playerCharacters.size(); i++){
            ((ICharacter) playerCharacters.get(i)).waitTurn();
        }
        for(Enemy enemy: enemies){
            enemy.waitTurn();
        }
        enemiesAlive = enemies.size();
    }

    /**
     * Method called when and character was added to the queue
     */
    public void characterAdded() {
        phase.characterAdded();
    }


    public void tryAttackEnemy(int indexEnemy) throws InvalidMovementException {
        phase.attackEnemy(indexEnemy);
    }

    public void next() throws InvalidMovementException {
        phase.next();
    }

    public void tryAttackPlayer() throws InvalidMovementException {
        phase.attackPlayer(alivePlayerCharacters.get(rng.nextInt(alivePlayerCharacters.size())));
    }

    public void back() throws InvalidMovementException {
        phase.back();
    }

    public void attack() throws InvalidMovementException {
        phase.attack();
    }

    public void toInventory() throws InvalidMovementException {
        phase.toInventory();
    }


    public void tryToEquipWeapon(int indexWeapon) throws InvalidMovementException {
        phase.equipWeapon(indexWeapon);
    }

    public void removeFirstInQueue(){
        turnsQueue.poll();
    }

    public void waitTurnActualCharacter(){
        characterTurn.waitTurn();
    }

    public void turnBoxScene(){
        scene.turnBox();
    }

    public void attackInfoScene(ICharacter attacked){
        scene.attackInfo(getCharacterName(characterTurn), damageReceived, getCharacterName(attacked));
    }

    public void refreshInfoColumnScene(ICharacter attacked){
        scene.refreshInfoColumn(playerCharacters.indexOf(attacked));
    }

    public void refreshEnemyColumnsScene(int indexEnemy){
        scene.refreshEnemyColumns(indexEnemy);
    }

    public void waitingScene(){
        scene.waitingText();
    }

    public void enemyChoosingScene(){
        scene.enemyChoosing();
    }

    public void lostScene(){
        scene.lostGame();
    }

    public void winScene(){
        scene.winGame();
    }

    public void playerTurnScene(){
        scene.playerTurnBox();
    }

    public void inventoryScene(){
        scene.inventoryBox();
    }

    public void setDamageReceived(int damageReceived){
        this.damageReceived = damageReceived;
    }

    public boolean isPlayerTurn() {
        return phase.isPlayerTurn();
    }

    public boolean isEnemyTurn(){
        return phase.isEnemyTurn();
    }

    public boolean isSelectingWeapon(){
        return phase.isSelectingWeapon();
    }

    public boolean isSelectingTarget(){
        return phase.isSelectingTarget();
    }

    public boolean isWaiting(){
        return phase.isWaiting();
    }

    public boolean isWon(){
        return phase.isWon();
    }

    public boolean isLost(){
        return phase.isLost();
    }

    public boolean isEndTurn(){
        return phase.isEndTurn();
    }


}
