default: CelestialIsland.java Player.java Introduction.java CastleMap.java CastleRoom.java Fight.java Food.java Castle.java Weapon.java Enemy.java Item.java
	javac CelestialIsland.java Player.java Introduction.java CastleMap.java CastleRoom.java Fight.java Food.java Castle.java Weapon.java Enemy.java Item.java

run: CelestialIsland.java Player.java Introduction.java CastleMap.java CastleRoom.java Fight.java Food.java Castle.java Weapon.java Enemy.java Item.java
	java CelestialIsland

clean: 
	rm -f *.class