import java.util.ArrayList;

public class CastleRoom {
    private String name;
    private String description;
    private ArrayList<String> exits;
    private ArrayList<Food> roomFood;
    private ArrayList<Weapon> roomWeapons;
    private ArrayList<Enemy> roomEnemies;
    private ArrayList<Item> roomItems;

    public CastleRoom (String name, String description) { //creates a castleroom object with name and description parameters and creates arraylists for items, exits, weapons, food, and enemies
        this.name = name;
        this.description = description;
        roomItems = new ArrayList <Item> ();
        exits = new ArrayList<String> ();
        roomFood = new ArrayList<Food> ();
        roomWeapons = new ArrayList<Weapon> ();
        roomEnemies = new ArrayList<Enemy> ();
    }

    public String returnRoomName () {
        return this.name;
    }

    public String returnDescription () {
        return this.description;
    }

    public void addExit (String exit) { //adds an exit for a room
        exits.add(exit);
    }

    public String listExits () { //list all exits of the current room
        String roomExits = "";
        for (String exit: exits) {
            roomExits += exit + "\n";
        }
        return roomExits;
    }

    public void addWeapon (Weapon Weapon) { //adds weapon to the room
        roomWeapons.add(Weapon);
    }

    public void removeWeapon (Weapon weapon) { //removes weapon from the room
        roomWeapons.remove(weapon);
    }

    public String listWeapons () { //list all weapons in room
        String weaponsInRoom = "";
        for (Weapon weapon: roomWeapons) {
            weaponsInRoom += weapon.getWeaponName() + "\n";
        }
        return weaponsInRoom;
    }

    public void addEnemy (Enemy enemy) { //add enemy to room
        roomEnemies.add(enemy);
    }

    public String listEnemies () { //list all enemies in room
        String enemyInRoom = "";
        for (Enemy enemy: roomEnemies) {
            enemyInRoom += enemy.getEnemyName() + "\n";
        }
        return enemyInRoom;
    }

    public void addFood (Food food) { //add food to room
        roomFood.add(food);
    }

    public String listFood () { //list all food in the room
        String foodInRoom = "";
        for (Food food: roomFood) {
            foodInRoom += food.getFoodName() + "\n";
        }
        return foodInRoom;
    }

    public void addItem (Item item) { //adds item to room
        roomItems.add(item);
    }

    public void removeItem(Item item) { //removes item from room
        roomItems.remove(item);
    }

    public String listItems(){ //lists all room items
        String itemsInRoom = "";
        for (Item item: roomItems) {
            itemsInRoom += item.getItemName() + "\n";
        }
        return itemsInRoom;
    }

    public String toString() { //outputs the room information as a string
        String output = this.name + ": " + this.description;
        return output;
    }
}