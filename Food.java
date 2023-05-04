public class Food {
    private String name;
    private int health;

    public Food (String name, int health) { //creates food object with name and health it gives back
        this.name = name;
        this.health = health;
    }

    public String getFoodName () {  //returns the name of food object
        return this.name;
    }
}