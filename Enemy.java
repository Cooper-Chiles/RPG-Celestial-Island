public class Enemy {
    private int health;
    private int damage;
    private String name;

    public Enemy (String name){  //creates enemy object with parameter name
        this.name = name;
    }

    public Enemy (String name, int damage, int health) { //creates enemy objext with parameter name, health ,and damage
        this.name = name;
        this.damage = damage;
        this.health = health;
    }

    public String getEnemyName () { //returns enemy name
        return this.name;
    }

    public int getEnemyHealth () { //returns enemy health
        return this.health;
    }

    public void enemyTakeFistDamage () { //subtracts from health if user used fists
        this.health -= 1;
    }

    public void enemyTakeHammerDamage() { //subtracts from health if user used hammer
        this.health -= 3;
    }

    public void stats() { //prints enemy stats
        System.out.println("\t" + this.name + " stats:");
        if (this.health < 0) {this.health = 0;}
        System.out.println("\tHealth: " + this.health);
        System.out.println("\tDamage: " + this.damage);
    }
}