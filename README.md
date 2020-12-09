Final Reality
=============

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com)
Broadly speaking for the combat the player has a group of characters to control and a group of 
enemies controlled by the computer.

---
## Characters
All the characters have a name, life and defense. The enemies have damage points and weight. The
player's characters have a weapon with a given weight and damage points.  

Because they have certain behaviors in common, there is an interface with these common methods and
an abstract class (*AbstractCharacter*) that implements one of them. Because enemies and player characters have different
way to measure how long they wait in the queue, waitTurn is left as an abstract method.
Enemies are implemented with the *Enemy* class, which inherits from the *AbstractCharacter* class. It has the weight of the enemy and also
implements waitTurn based on this.  

The player's characters have a common interface (*IPlayerCharacter*), which has the common methods *equip* and *getEquippedWeapon*.  

*AbstractPlayerCharacter* extends *AbstractCharacter* and implements the *IPlayerCharacter* interface. Because all the player's characters
have a weapon, it has this field. It also implements *waitTurn* and *getEquippedWeapon*, which are common for all the player's characters,
based on the character's equipped weapon.  

Since different character classes will implement the *equip* method differently, this is left in each class. Also, because there are several classes, it is better to leave them apart. 
This also facilitates the implementation of the method *getEquals*, and the differentiation between different character classes.  

According to the implementation of the *attack* method, there is friendly fire, but the controller makes sure that it 
does not exist by having methods to attack *player character to enemy* and *enemy to player character*.

## Weapons

You have an interface that contains the common methods. AbstractWeapon implements the common methods for all
the types of weapons. Since there are several types, it was decided to leave them in separate classes, in addition to the fact that some have magic damage and others
no (this is not implemented at the moment). This also facilitates the implementation of the method *getEquals* and the differentiation between different types.

The weapons can be equipped depending on the character's type, given by the next table:

|    Type    | Sword | Axe | Knife | Staff | Bow |
|:----------:|:-----:|:---:|:-----:|:-----:|:---:|
|   Knight   |  Yes  | Yes |  Yes  |   No  |  No |
|  Engineer  |   No  | Yes |   No  |   No  | Yes |
|    Thief   |  Yes  |  No |   No  |  Yes  | Yes |
| Black Mage |   No  |  No |  Yes  |  Yes  |  No |
| White Mage |   No  |  No |   No  |  Yes  |  No |

## Controller
The controller has the list of player's characters, enemies, inventory, and the queue. It has
methods for the creation of all the types of characters and weapons, methods for getting their
information, and it performs the equipping and attacking. Also, it can know when a character dies and if
the user loses or wins. This is implemented with an observer. The character notifies the controller
of its death and it also can notify when it attacks, so, the controller knows whose turn it is.

## Turns
For now, the change from one turn to another is not implemented. This makes certain methods exist that do nothing,
but in the future they will implement changes in the game. However, characters have to
notify the controller when they are added to the queue. This is useful because it can happen 
that the queue is empty and the next turn can't happen right after the last one. Also, in this
notification, the controller can know if the turn is of an enemy or a player (since they have
separate handlers). The controller has the methods to attack, so it would know when a character's
turn ends (when this method is called)

