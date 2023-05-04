import java.util.Scanner;

public class Fight {
    private Boolean userDead = false;
    private Boolean enemyDead = false;
    private String attackResponse;
    private int userHealth;
    private int enemyHealth;
    private String pickupHammer;
    private Boolean hammerPostFight = false;
    private Boolean breadUsed = false;
    private Boolean wrongChoice = false;
    private Boolean hammerChoice = false;

    public Fight (){
    }
    //starts the fight sequence
    public void startFight(Player user, Enemy enemy, Scanner s, Boolean hasHammer, Weapon hammer, Boolean foodPickedUp) {
        System.out.println(user.getName() + " has started a fight with the " + enemy.getEnemyName());
        System.out.println("");
        user.stats();//displays user stats: health damage
        enemy.stats(); //displays enemy stats: health damage
        while (userDead == false && enemyDead == false) { //runs until user or enemy die
            if (wrongChoice == false) { //doesnt print if they mess up the response
            System.out.println("");
            System.out.println(user.getName() + "'s turn:");//always starts user turn
            playerTurn(user, foodPickedUp); //runs player turn
            attackResponse = s.nextLine().toLowerCase();
            }
            wrongChoice = false;
            if (foodPickedUp == true) { //runs if they have picked up the food and allows them to use it
                switch (attackResponse) {
                    case "hands":
                    case "fists":
                    case "throw hands": //if they choose to use fists 1 damage
                    case "hit him with fists":
                        System.out.println("You attack the " + enemy.getEnemyName() + " with your fists dealing 1 damage.");
                        System.out.println("");
                        enemy.enemyTakeFistDamage();
                        user.stats();
                        enemy.stats();
                        System.out.println("");
                        break;

                    case "hammer": //if they choose to use hammer 3 damage
                        System.out.println("You attack the " + enemy.getEnemyName() + " with the Hammer dealing 3 damage.");
                        System.out.println("");
                        enemy.enemyTakeHammerDamage();
                        user.stats();
                        enemy.stats();
                        System.out.println("");
                        break;

                    case "food":
                    case "eat food":
                    case "bread":
                    case "breadstick": //eat bread healing 3 health
                    case "eat bread":
                    case "eat breadstick":
                        user.heal();
                        user.stats();
                        enemy.stats();
                        System.out.println();
                        break;
                        
                    default: 
                        System.out.println("Thats not a viable option to use. Please enter a weapon or food name.");
                        attackResponse = s.nextLine().toLowerCase();
                        wrongChoice = true;
                        continue;
                }
            } else { //havent picked up food cannot use it
                switch (attackResponse) {
                    case "hands":
                    case "fists": //if they choose to use fists 1 damage
                    case "throw hands":
                    case "hit him with fists":
                        System.out.println("You attack the " + enemy.getEnemyName() + " with your fists dealing 1 damage.");
                        System.out.println("");
                        enemy.enemyTakeFistDamage();
                        user.stats();
                        enemy.stats();
                        System.out.println("");
                        break;

                    case "hammer": //if they choose to use hammer 3 damage
                        System.out.println("You attack the " + enemy.getEnemyName() + " with the Hammer dealing 3 damage.");
                        System.out.println("");
                        enemy.enemyTakeHammerDamage();
                        user.stats();
                        enemy.stats();
                        System.out.println("");
                        break;
                    
                    default:
                        System.out.println("Thats not a viable weapon to use. Please enter a weapon name.");
                        attackResponse = s.nextLine().toLowerCase();
                        wrongChoice = true;
                        continue;
                }
            }
            enemyHealth = enemy.getEnemyHealth();
            if (enemyHealth > 0) { //runs if the enemy is not dead
                if (hasHammer == true) { //if the enemy picks up hammer they do 3 damage
                    System.out.println(enemy.getEnemyName() + "'s turn:");
                    System.out.println("The " + enemy.getEnemyName() + " attacks you with the Hammer dealing 3 damage.");
                    System.out.println("");
                    user.loseHealthHammer();
                    user.stats();
                    System.out.println("");
                    enemy.stats();
                } else { //if the enemy doesnt have the hammer they do 1 damage
                    System.out.println(enemy.getEnemyName() + "'s turn:");
                    System.out.println("The " + enemy.getEnemyName() + " attacks you with their fists dealing 1 damage.");
                    System.out.println("");
                    user.loseHealthHands();
                    user.stats();
                    System.out.println("");
                    enemy.stats();
                }
            }
            userHealth = user.getHealth();
            enemyHealth = enemy.getEnemyHealth();
            if (userHealth <= 0) { //checks if user is dead
                userDead = true;
            }
            if (enemyHealth <= 0) { //checks if enemy is dead
                enemyDead = true;
            }
        }
        if (userDead == true) {
            System.out.println("You have died. GAME OVER."); //result of user death
        }
        if (enemyDead == true) {
            System.out.println("You have defeated the " + enemy.getEnemyName() + "."); //result of enemy death
        }

        if (enemyDead == true && hasHammer == true) {
            while (hammerChoice == false) {
                if (wrongChoice == false) {
                    System.out.println("Would you like to pick up the weapon from the defeated " + enemy.getEnemyName() + ".");
                    pickupHammer = s.nextLine().toLowerCase();
                }
                switch(pickupHammer) {
                    case "hell yes":
                    case "fuck yes":
                    case "hell yeah":
                    case "yeah":
                    case "fuck yeah":
                    case "ill do it":
                    case "lets do it":
                    case "ill help you":
                    case "yes":
                    case "sure":
                    case "i guess":
                    case "if i have to":
                    case "yeah i guess":
                    case "sure why not":
                    case "i can":
                    case "sure i guess i can":
                    case "yes of course":
                    case "of course":
                    case "okay":
                    case "sounds fun":
                    case "sounds boring but alright":
                    case "yeah sure":
                    case "yeah alright":
                    case "ya":
                    case "yea":
                    case "yah": //answer for picking up hammer after the fight
                        user.addWeapon(hammer);
                        hammerPostFight = true;
                        hammerChoice = true;
                        break;
                    case "naw":
                    case "nah":
                    case "i dont want to":
                    case "i dont wanna":
                    case "why would i want to":
                    case "hell naw":
                    case "go fuck yourself":
                    case "hell fucking no":
                    case "fuck no":
                    case "i wont help you":
                    case "go screw yourself":
                    case "hell no":
                    case "fuck off":
                    case "no":
                    case "that sounds lame":
                    case "id rather be boring and alive":
                    case "im not fun":
                    case "i dont like this game":
                    case "fuck you":
                    case "i dont want to do this":
                    case "let me leave": //answer for not picking up hammer after the fight
                        hammerPostFight = false;
                        hammerChoice = true;
                        break;
                    default:
                        wrongChoice = true;
                        pickupHammer = s.nextLine().toLowerCase();
                        break;
                }
            }
        }
    }

    public void playerTurn (Player user, Boolean foodPickedUp) { //displays the options that the user has during the fight
        if (user.numWeapons() < 1) {
            System.out.println("You can attack with your fists.");
        } else if (user.numWeapons() < 2) {
            System.out.println("You can attack with your " + user.getWeapons());
        } else {
            System.out.println("You can attack with your " + user.getWeapons());
        }
        if (foodPickedUp == true && breadUsed == false) {
            System.out.println("You can also use your Breadstick to heal 3 health.");
        }
    }

    public Boolean userWon () {//returns boolean to castle.java if the enemy died
        return enemyDead;
    }

    public Boolean userLost () { //returns boolean to castle.java if the user died
        return userDead;
    }

    public Boolean userGetHammerAfterFight() { //returns boolean of if user picked up hammer after the fight
        return hammerPostFight;
    }
}