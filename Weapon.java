public class Weapon {
    private String weaponName;
    
    public Weapon () {
    }

    public Weapon (String name, int damage) { //creates weapon object with parameter name
        weaponName = name;
    }

    public String getWeaponName () { //returns weapon name
        return weaponName;
    }
}