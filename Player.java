import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private String playerName;
    private int health;
    private int defense; 
    private int damage;
    private ArrayList <Weapon> weapons;
    private ArrayList <Food> foods;
    private ArrayList <Item> items;
    private Weapon checker;

    public Player(String playerName) { //creates a new player and their inventory for weapons items and food
        this.playerName = playerName;
        damage = 0;
        health = 10;
        weapons = new ArrayList<Weapon>();
        foods = new ArrayList<Food>();
        items = new ArrayList<Item>();
    }

    public int getHealth () {
        return health;
    }

    public String getName () {
        return this.playerName;
    }

    public void loseHealthHands () { //if enemy attacks with hands
        health -= 1;
    }

    public void loseHealthHammer () { //if enemy attacks with hammer
        health -= 3;
    }

    public void heal() { //if the user uses the food
        health += 3;
    }

    public void stats() { //player stats used for fight and when picking up weapons
        System.out.println("\tHere are your stats:");
        System.out.println("\tHealth: " + health);
        System.out.println("\tAttack Power: " + damage);
    }

    public String userStats () {
        String userStats = "";
        userStats += "\tHealth: " + health;
        userStats += "\n\tAttack Power: " + damage;
        return userStats;
    }

    public void addWeapon (Weapon weapon) { //adds weapon to player inventory and checks weapon for the damage
        checker = new Weapon();
        checker = weapon;
        weapons.add(weapon);
        if (checker.getWeaponName().equalsIgnoreCase("fists")) {
            damage += 1;
        } else {
            damage += 2;
        }
    }

    public int numWeapons () { 
        return weapons.size();
    }

    public String getWeapons () { //returns all of the weapons in user inventory to string
        String weaponNames = "";
        int count = 0;
        for (Weapon weapon: weapons) {
            if (count < weapons.size()- 1) {
                weaponNames += weapon.getWeaponName() + " or ";
            } else {
                weaponNames += weapon.getWeaponName() + ".";
            }
            ++count;
        }
        return weaponNames;
    }

    public void addFood(Food food) { //adds food to player inventory
        foods.add(food);
    }

    public void removeFood(Food food) { //removes food if player eats it
        foods.remove(food);
    }

    public void addItem (Item item) { //adds item to player inventory
        items.add(item);
    }
}