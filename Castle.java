import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class Castle {
    private String workShopResponse = "";
    private String dininghall;
    private String servantCham;
    private String central;
    private String fightAnswer;
    private Boolean beatElf = false;
    private Boolean pickedHammerUp = false;
    private Boolean elfGetHammer = false;
    private Boolean hasKey = false;
    private Boolean foodPickedUp = false;
    private String interactResponse;
    private String defaultRespone;
    private Boolean validOption = false;
    private Boolean choseWrong = false;
    private Boolean exitCastle = false;
    private String exit;
    private Boolean defeatedAugustus = false; 
    private Boolean runOrFight = false;

    public Castle () {
    }

    public Boolean wonGame () { //returns if you have defeated augustus and therefore won the game
        return defeatedAugustus;
    }

    public boolean createCastle (CastleMap castleMap, Boolean insideCastle, Scanner s, Player user) { //returns insideCastle and creates the castle map and file steam
        String fileName = user.getName() + ".txt"; //creates file name of user name .txt
        FileOutputStream gameStats = null;
        try { //trys creating a output file stream
            gameStats = new FileOutputStream(fileName); //adds file as new ouput stream
        } catch (FileNotFoundException e) { //catches if the file isnt created
            System.out.println("Cannot find " + fileName); //prints if their is a filenotfound exception 
        }
        PrintWriter userStats = new PrintWriter(gameStats); //creates print writer
        userStats.println(user.getName()+ "'s stats throughout the game: \nBeginning:");
        userStats.println(user.userStats());
        CastleRoom dungeonEntrance = new CastleRoom ("Dungeon Entrance", "Dark and damp, the walls are lined with tapestries dipicting dragons and other folklore.\n\tTo the east is the door to Servant Chambers.\n\tTo the west is the door to the Dining Hall.\n\tTo the northeast is the door to the workshop.\n\tTo the northwest is the locked door to the Central Hall of the dungeon requires a key to open.");
        //creates the castle dungeon entrance
        CastleRoom servantChambers = new CastleRoom ("Servant Chambers", "A cramped room filled with four freshly made beds. Sitting atop of the bed closest to you is a key with the words central hall printed on it.");
        CastleRoom servantChambersNoKey = new CastleRoom ("Servant Chambers", "A cramped room filled with four freshly made beds.");
        //creates the variations of servant chambers with different options
        CastleRoom diningHall = new CastleRoom ("Dining Hall", "A grand ballroom filled with cafeteria tables with plates of half eaten meat and bread, silverware, and bottles of wine strewn about.\nIn the back half of the room there is a kitchen with a wall for serving plates. In the left corner of the back wall there is an entranceway to the kitchen.");
        //creates dining hall room
        CastleRoom workShop = new CastleRoom ("Work Shop", "A small musty room with a dusty work bench with a stool beside it in the back left corner.\n\tThe desk is covered with design sketches for swords and tools and on top of the nearby stool lies a Hammer. \n\tAlso in the back of the room, but in the right corner there is a bed with an elf asleep in it.");
        CastleRoom workShopNoHammer = new CastleRoom ("Work Shop", "A small musty room with a dusty work bench with a stool beside it in the back left corner.\n\tThe desk is covered with design sketches for swords and tools and has a stool nearby. \n\tAlso in the back of the room, but in the right corner there is a bed with an elf asleep in it.");
        CastleRoom workShopNoElf = new CastleRoom ("Work Shop", "A small musty room with a dusty work bench with a stool beside it in the back left corner.\n\tThe desk is covered with design sketches for swords and tools and on top of the stool nearby lies a Hammer. \n\tAlso in the back of the room, but in the right corner there is an empty bed.");
        CastleRoom workShopNoHammerElf = new CastleRoom ("Work Shop", "A small musty room with a dusty work bench with a stool beside it in the back left corner.\n\tThe desk is covered with design sketches for swords and tools and has a stool nearby. \n\tAlso in the back of the room, but in the right corner there is an empty bed.");
        //creates all variations of the workshop with different options
        CastleRoom centralHall = new CastleRoom ("Central Hall", "The final room of the dungeon. The room has four lit braziers in each of the corners throwing ominous lighting across the room.\nDirectly in front of you and in the center of the room resides a throne with the evil dictator Augustus sitting in it.");
        CastleRoom centralHallNoAugustus = new CastleRoom ("Central Hall", "The final room of the dungeon. The room has four lit braziers in each of the corners throwing ominous lighting across the room.\nDirectly in front of you and in the center of the room resides an throne.");
        //creates the variations of the central hall with different options
        CastleRoom kitchen = new CastleRoom ("Kitchen", "A medieval kitchen which has a right wall of wood burning stoves and a left wall of shelves holding plates and cups of varying sizes.\nDirectly in front of you is an open bread box with a single breadstick left inside.");
        CastleRoom kitchenNoBread = new CastleRoom ("Kitchen", "A medieval kitchen which has a right wall of wood burning stoves and a left wall of shelves holding plates and cups of varying sizes.\nDirectly in front of you is an open bread box.");
        
        Fight fightStarts = new Fight();
        Weapon hammer = new Weapon ("Hammer", 3);
        Enemy elf = new Enemy ("Elf", 1, 2);
        Enemy elfH = new Enemy ("Elf with Hammer", 3, 2);
        Enemy augustus = new Enemy ("Augustus");
        Food breadStick = new Food ("BreadStick", 4);
        Item key = new Item ("Central Room Key");

        castleMap.addRoom(dungeonEntrance);
        castleMap.addRoom(diningHall);
        castleMap.addRoom(servantChambers);
        castleMap.addRoom(workShop);
        castleMap.addRoom(centralHall);

        dungeonEntrance.addExit("Servant Chambers");
        dungeonEntrance.addExit("Dining Hall");
        dungeonEntrance.addExit("Work Shop");
        dungeonEntrance.addExit("Central Hall");
        dungeonEntrance.addExit("Castle Exit");

        diningHall.addExit("Dungeon Entrance");
        diningHall.addExit("Kitchen");

        kitchen.addExit("Dining Hall");
        kitchen.addFood(breadStick);
        kitchenNoBread.addExit("Dining Hall");

        workShop.addExit("Dungeon Entrance");
        workShop.addWeapon(hammer);
        workShop.addEnemy(elf);
        workShopNoHammer.addExit("Dungeon Entrance");
        workShopNoHammer.addEnemy(elf);
        workShopNoElf.addExit("Dungeon Entrance");
        workShopNoElf.addWeapon(hammer);
        workShopNoHammerElf.addExit("Dungeon Entrance");

        servantChambers.addExit("Dungeon Entrance");
        servantChambersNoKey.addExit("Dungeon Entrance");
        servantChambers.addItem(key);

        centralHall.addExit("Dungeon Entrance");
        centralHall.addEnemy(augustus);
        centralHallNoAugustus.addExit("Dungeon Entrance");
        centralHallNoAugustus.addExit("Final Exit");

        CastleRoom currentRoom = dungeonEntrance;
        String exitChoice = "";
        while (insideCastle == true) { //ensures they havent left the castle 
            if (currentRoom == dungeonEntrance) { //only prints if the room is the dungeon entrance
                System.out.println("");
                System.out.println(currentRoom);
                System.out.println("");
                System.out.println("Interactable options:");
                System.out.println("Exits:");
                System.out.println(currentRoom.listExits()); //displays the room exits
                System.out.println("\tPlease choose a door.");
                System.out.print("\t"); 
                exitChoice = s.nextLine().toLowerCase();
            }
                    switch(exitChoice) { //switch based on exitchoice with all rooms as options
                        case "workshop":// switch for room workshop
                        case "work":
                        case "shop":
                        case "work shop":
                       
                            if (pickedHammerUp == false && beatElf == false) { //shows workshop if there is still a hammer and elf there
                                if (choseWrong == false) { //only prints if they havent messed up the first response
                                    currentRoom = workShop;
                                    System.out.println();
                                    System.out.println(currentRoom);
                                    System.out.println("\nInteractable options:");
                                    System.out.println("Weapons:");
                                    System.out.println(currentRoom.listWeapons());
                                    System.out.println("Enemies:");
                                    System.out.println(currentRoom.listEnemies());                                
                                    System.out.println("Exits:");
                                    System.out.println(currentRoom.listExits());
                                    System.out.println("\tWhat would you like to interact with?");
                                    System.out.print("\t");
                                    interactResponse = s.nextLine().toLowerCase();
                                }
                                choseWrong = false;
                                switch (interactResponse) { //switch with options of the room weapons enemy or exit
                                    case "hammer":
                                    case "weapon":
                                        System.out.println("\tWould you like to try and SNEAK past the Elf to get the hammer, RUN past him, or EXIT the room?");
                                        System.out.print("\t");
                                        workShopResponse = s.nextLine().toLowerCase(); 
                                        
                                        switch (workShopResponse) { //switch with options if they chose hammer
                                            case "sneak": //if they want to sneakily pick up hammer and not have to fight
                                                System.out.println("");
                                                System.out.println("\tYou were able to sneak by the Elf undetected.");
                                                System.out.println("\tHammer picked up.");
                                                user.addWeapon(hammer);
                                                workShop.removeWeapon(hammer);
                                                pickedHammerUp = true;
                                                user.stats();
                                                System.out.println();
                                                currentRoom = workShopNoHammer;
                                                break;

                                            case "run": //if they want to pick up the hammer fast at risk of a fight
                                                System.out.println("");
                                                System.out.println("\tYou manage to pick up the Hammer.\n\tHoever the Elf has awoken and has challenged you to a battle.");
                                                user.addWeapon(hammer);
                                                workShop.removeWeapon(hammer);
                                                pickedHammerUp = true;
                                                System.out.println("\tRUN away or FIGHT bravely?");
                                                System.out.print("\t");
                                                fightAnswer = s.nextLine().toLowerCase();
                                                switch(fightAnswer){
                                                    case "fight":
                                                        userStats.println("Before Fight: \n" + user.userStats());
                                                        fightStarts.startFight(user, elf, s, elfGetHammer, hammer, foodPickedUp);
                                                        if (fightStarts.userWon() == true) {
                                                            beatElf = true;
                                                            userStats.println("After Fight: \n" + user.userStats());
                                                        } else if (fightStarts.userLost() == true){
                                                            beatElf = false;
                                                            System.exit(0);
                                                        }
                                                        break;
                                                    case "run":
                                                        exitChoice = "dungeon entrance";
                                                        currentRoom = dungeonEntrance;
                                                        break;

                                                    default:
                                                        while (runOrFight == false) {
                                                            System.out.println("\tPlease enter run or fight.");
                                                            System.out.print("\t");
                                                            fightAnswer = s.nextLine().toLowerCase();
                                                            switch(fightAnswer) {
                                                                case "fight":
                                                                    userStats.println("Before Fight: \n" + user.userStats());
                                                                    fightStarts.startFight(user, elf, s, elfGetHammer, hammer, foodPickedUp);
                                                                    if (fightStarts.userWon() == true) {
                                                                        beatElf = true;
                                                                        userStats.println("After Fight: \n" + user.userStats());
                                                                    } else if (fightStarts.userLost() == true){
                                                                        beatElf = false;
                                                                        System.exit(0);
                                                                    }
                                                                    runOrFight = true;
                                                                    break;

                                                                case "run":
                                                                    exitChoice = "dungeon entrance";
                                                                    currentRoom = dungeonEntrance;
                                                                    runOrFight = true;
                                                                    break;
                                                                default:
                                                                    runOrFight = false;
                                                            }
                                                        }
                                                }
                                                runOrFight = false;
                                                break;

                                            case "exit":
                                                exitChoice = "dungeon entrance";
                                                currentRoom = dungeonEntrance;
                                                currentRoom = castleMap.getName(interactResponse);
                                            break;

                                            default: //catches if they dont enter a valid option and loops until they enter a valid choice
                                                while (validOption == false) {
                                                    System.out.println();
                                                    System.out.println("\tInvalid option.");
                                                    System.out.println("\tPlease choose an valid choice.");
                                                    System.out.print("\t");
                                                    defaultRespone = s.nextLine().toLowerCase();
                                                    switch(defaultRespone) {
                                                        case "sneak": //if they want to sneakily pick up hammer and not have to fight
                                                            System.out.println("");
                                                            System.out.println("\tYou were able to sneak by the Elf undetected.");
                                                            System.out.println("\tHammer picked up.");
                                                            user.addWeapon(hammer);
                                                            workShop.removeWeapon(hammer);
                                                            pickedHammerUp = true;
                                                            user.stats();
                                                            System.out.println();
                                                            currentRoom = workShopNoHammer;
                                                            validOption = true;
                                                            break;

                                                        case "run": //if they want to pick up the hammer fast at risk of a fight
                                                            System.out.println("");
                                                            System.out.println("\tYou manage to pick up the Hammer.\n\tHoever the Elf has awoken and has challenged you to a battle.");
                                                            user.addWeapon(hammer);
                                                            workShop.removeWeapon(hammer);
                                                            pickedHammerUp = true;
                                                            System.out.println("\tRUN away or FIGHT bravely?");
                                                            System.out.print("\t");
                                                            fightAnswer = s.nextLine().toLowerCase();

                                                            switch(fightAnswer){
                                                                case "fight":
                                                                    userStats.println("Before Fight: \n" + user.userStats());
                                                                    fightStarts.startFight(user, elf, s, elfGetHammer, hammer, foodPickedUp);
                                                                    if (fightStarts.userWon() == true) {
                                                                        beatElf = true;
                                                                        userStats.println("After Fight: \n" + user.userStats());
                                                                    } else if (fightStarts.userLost() == true){
                                                                        beatElf = false;
                                                                        System.exit(0);
                                                                    }
                                                                    break;
                                                                case "run":
                                                                    exitChoice = "dungeon entrance";
                                                                    currentRoom = dungeonEntrance;
                                                                    break;

                                                                default:
                                                                    while (runOrFight == false) {
                                                                        System.out.println("\tPlease enter run or fight.");
                                                                        System.out.print("\t");
                                                                        fightAnswer = s.nextLine().toLowerCase();
                                                                        switch(fightAnswer) {
                                                                            case "fight":
                                                                                userStats.println("Before Fight: \n" + user.userStats());
                                                                                fightStarts.startFight(user, elf, s, elfGetHammer, hammer, foodPickedUp);
                                                                                if (fightStarts.userWon() == true) {
                                                                                    beatElf = true;
                                                                                    userStats.println("After Fight: \n" + user.userStats());
                                                                                } else if (fightStarts.userLost() == true){
                                                                                    beatElf = false;
                                                                                    System.exit(0);
                                                                                }
                                                                                runOrFight = true;
                                                                                break;

                                                                            case "run":
                                                                                exitChoice = "dungeon entrance";
                                                                                currentRoom = dungeonEntrance;
                                                                                runOrFight = true;
                                                                                break;

                                                                            default:
                                                                                runOrFight = false;
                                                                        }
                                                                    }
                                                            }
                                                            validOption = true;
                                                            runOrFight = false;
                                                            break;
                                                        case "exit":
                                                            exitChoice = "dungeon entrance";
                                                            currentRoom = dungeonEntrance;
                                                            validOption = true;
                                                            break;

                                                        default:
                                                            validOption = false;
                                                            break;
                                                    }
                                                }
                                        }
                                        break;

                                    case "elf":// if they chose elf instead of hammer
                                    case "enemy":
                                        System.out.println("\tThe Elf woke up and has picked up the Hammer to use in battle.");
                                        elfGetHammer = true;
                                        userStats.println("Before Fight: \n" + user.userStats());
                                        fightStarts.startFight(user, elfH, s, elfGetHammer, hammer, foodPickedUp); //starts a fight between user and enemy
                                        if (fightStarts.userWon() == true) {
                                            beatElf = true;
                                            userStats.println("After Fight: \n" + user.userStats());
                                        }
                                        if (fightStarts.userGetHammerAfterFight() == true) { //checks if they wanted to pick weapon up after fight
                                            pickedHammerUp = true;
                                            currentRoom = workShopNoHammerElf; //if they beat enemy and picked hammer up goes to this room
                                        } else {
                                            currentRoom = workShopNoElf; //else goes to the room with the hammer still in it
                                        }
                                        break;
    
                                    case "dungeon entrance" :
                                    case "dungeon":
                                    case "back to dungeon":
                                    case "leave room":
                                    case "return to entrance":
                                    case "leave": //returns to entrance
                                        exitChoice = "dungeon entrance";
                                        currentRoom = dungeonEntrance;
                                        break;

                                    default: //catches if they dont enter a valid option and loops until they enter a valid choice
                                        while (validOption == false) {
                                        System.out.println();
                                        System.out.println("\tInvalid option.");
                                        System.out.println("\tPlease choose an interactable object.");
                                        System.out.print("\t");
                                        defaultRespone = s.nextLine().toLowerCase();
                                        switch(defaultRespone) {
                                            case "hammer":
                                            case "weapon":
                                                interactResponse = "hammer";
                                                validOption = true;
                                                choseWrong = true;
                                                break;

                                            case "enemy":
                                            case "elf":
                                                interactResponse = "elf";
                                                validOption = true;
                                                choseWrong = true;
                                                break;

                                            case "dungeon entrance" :
                                            case "dungeon":
                                            case "back to dungeon":
                                            case "leave room":
                                            case "return to entrance":
                                            case "leave":
                                                exitChoice = "dungeon entrance";
                                                currentRoom = dungeonEntrance;
                                                validOption = true;
                                                break;

                                            default:
                                                validOption = false;
                                                break;
                                        }
                                    }
                                validOption = false;

                                    }
                            } else if (pickedHammerUp == true && beatElf == false) { //if the user picked up the hammer and then left without fighting the elf
                                if (choseWrong == false) { //doesnt display if they put in the wrong input
                                    currentRoom = workShopNoHammer; //room is now workshop with no hammer
                                    System.out.println(currentRoom);
                                    System.out.println("\nInteractable options:");
                                    System.out.println("Enemies:");
                                    System.out.println(currentRoom.listEnemies());
                                    System.out.println("Exits:");
                                    System.out.println(currentRoom.listExits());
                                    System.out.println("\tWhat would you like to interact with?");
                                    System.out.print("\t");
                                    interactResponse = s.nextLine();
                                    interactResponse = interactResponse.toLowerCase();
                                }
                                choseWrong = false;
                                switch (interactResponse) { //switch that displays option exit or enemy
                                    case "enemy": //fight enemy
                                    case "elf":
                                        userStats.println("Before Fight: \n" + user.userStats());
                                        fightStarts.startFight(user, elf, s, elfGetHammer, hammer, foodPickedUp);
                                        if (fightStarts.userWon() == true) {
                                            beatElf = true;
                                            currentRoom = workShopNoHammerElf;
                                            userStats.println("After Fight: \n" + user.userStats());
                                        }
                                        break;
    
                                    case "dungeon entrance" :
                                    case "dungeon":
                                    case "back to dungeon"://return to entrance
                                    case "leave room":
                                    case "return to entrance": //return to entrance
                                    case "leave":
                                        interactResponse = "dungeon entrance";
                                        currentRoom = dungeonEntrance;
                                       
                                        break;
                                    default: //catches if they dont enter a valid option and loops until they enter a valid choice
                                        while (validOption == false) {
                                            System.out.println();
                                            System.out.println("\tInvalid option.");
                                            System.out.println("\tPlease choose a door.");
                                            System.out.print("\t");
                                            defaultRespone = s.nextLine().toLowerCase();
                                            switch(defaultRespone) {
                                                case "enemy":
                                                case "elf":
                                                    interactResponse = "elf";
                                                    validOption = true;
                                                    choseWrong = true;
                                                    break;

                                                case "dungeon entrance" :
                                                case "dungeon":
                                                case "back to dungeon":
                                                case "leave room":
                                                case "return to entrance":
                                                case "leave":
                                                    exitChoice = "dungeon entrance";
                                                    currentRoom = dungeonEntrance;
                                                   
                                                    validOption = true;
                                                    break;

                                                default:
                                                    validOption = false;
                                                    break;
                                            }
                                        }
                                    validOption = false;
                                }
                            } else if (pickedHammerUp == false && beatElf == true) { //displays if you beat elf but didnt get the hammer
                                if (choseWrong == false) { 
                                    currentRoom = workShopNoElf; //room is workshop no elf 
                                    System.out.println(currentRoom);
                                    System.out.println("\nInteractable options:");
                                    System.out.println("Weapons:");
                                    System.out.println(currentRoom.listWeapons());
                                    System.out.println("Exits:");
                                    System.out.println(currentRoom.listExits());
                                    System.out.println("\tWhat would you like to interact with?");
                                    System.out.print("");
                                    interactResponse = s.nextLine();
                                    interactResponse = interactResponse.toLowerCase();
                                }
                                choseWrong = false;
                                switch(interactResponse) { //switch with remaining options exit or weapon
                                    case "hammer":
                                    case "weapon": //grab weapon
                                        System.out.println("You walk over and grab the Hammer from the stool.\nHammer picked up.");
                                        user.addWeapon(hammer);
                                        pickedHammerUp = true;
                                        choseWrong = true;
                                        break;

                                    case "dungeon entrance":
                                    case "dungeon":
                                    case "back to dungeon":
                                    case "leave room": //return to entrance
                                    case "return to entrance":
                                    case "leave":
                                        interactResponse = "dungeon entrance";
                                        currentRoom = dungeonEntrance;
                                        break;

                                    default: //catches if they dont enter a valid option and loops until they enter a valid choice
                                        while (validOption == false) {
                                            System.out.println();
                                            System.out.println("\tInvalid option.");
                                            System.out.println("\tPlease choose an interactable object.");
                                            System.out.print("\t");
                                            defaultRespone = s.nextLine().toLowerCase();
                                            switch(defaultRespone) {
                                                case "hammer":
                                                case "weapon":
                                                    interactResponse = "hammer";
                                                    validOption = true;
                                                    choseWrong = true;
                                                    break;

                                                case "dungeon entrance" :
                                                case "dungeon":
                                                case "back to dungeon":
                                                case "leave room":
                                                case "return to entrance":
                                                case "leave":
                                                    exitChoice = "dungeon entrance";
                                                    currentRoom = dungeonEntrance;
                                                   
                                                    validOption = true;
                                                    break;


                                                default:
                                                    validOption = false;
                                                    break;
                                            }
                                        }
                                    validOption = false;
                                }
                            }else if(pickedHammerUp == true && beatElf == true) { // displays if they beat the elf and grabbed the hammer
                                currentRoom = workShopNoHammerElf; //room is workshop with no hammer or elf
                                System.out.println(currentRoom);
                                System.out.println("\nInteractable options:");
                                System.out.println("Exits:"); //only exits left
                                System.out.println(currentRoom.listExits());
                                System.out.println("\tWhat would you like to interact with?");
                                System.out.print("\t");
                                interactResponse = s.nextLine();
                                interactResponse = interactResponse.toLowerCase();
                                switch(interactResponse) {
                                    case "dungeon entrance":
                                    case "dungeon":
                                    case "back to dungeon":
                                    case "leave room":
                                    case "return to entrance":
                                    case "leave": //return to entrance
                                        interactResponse = "dungeon entrance";
                                        currentRoom = dungeonEntrance;
                                        break;
                                    default: //catches if they dont enter a valid option and loops until they enter a valid choice
                                        while (validOption == false) {
                                            System.out.println();
                                            System.out.println("\tInvalid option.");
                                            System.out.println("\tPlease choose a door.");
                                            System.out.print("\t");
                                            defaultRespone = s.nextLine().toLowerCase();
                                            switch(defaultRespone) {
                                                case "dungeon entrance" :
                                                case "dungeon":
                                                case "back to dungeon":
                                                case "leave room":
                                                case "return to entrance":
                                                case "leave":
                                                    exitChoice = "dungeon entrance";
                                                    currentRoom = dungeonEntrance;
                                                    validOption = true;
                                                    break;

                                                default:
                                                    validOption = false;
                                                    break;
                                            }
                                        }
                                    validOption = false;
                                }
                            }

                            break;

                        case "servantschamber":
                        case "servants chamber":
                        case "servants chambers":
                        case "servant chambers":
                        case "servantchambers": //switch for the room servant chambers
                        case "servant chamber":
                        case "servant":
                        case "chambers":
                            if (hasKey == false) { //uses this if they havent picked up the key from the room
                                currentRoom = servantChambers;
                                System.out.println();
                                System.out.println(currentRoom);
                                System.out.println("\nInteractable options:");
                                System.out.println("Items:");
                                System.out.println(currentRoom.listItems());
                                System.out.println("Exits:");
                                System.out.println(currentRoom.listExits());
                                System.out.println("\tWhat would you like to interact with?");
                                System.out.print("\t");
                                servantCham = s.nextLine().toLowerCase();
                                switch(servantCham){ //switch for grab key or leave
                                    case "get key":
                                    case "grab key":
                                    case "getkey":
                                    case "grabkey":
                                    case "key":
                                    case "room key":  //get key
                                    case "roomkey":
                                    case "central room key":
                                    case "centralroomkey":
                                        user.addItem(key);
                                        System.out.println("Key picked up.");
                                        servantChambers.removeItem(key);
                                        hasKey = true;
                                        exitChoice = "servant chambers";
                                        break;
                                    
                                    case "dungeon entrance":
                                    case "dungeon":
                                    case "back to dungeon": //return to entrance
                                    case "leave room":
                                    case "return to entrance":
                                    case "leave":
                                        currentRoom = dungeonEntrance;
                                        break;

                                    default: //catches if they dont enter a valid option and loops until they enter a valid choice
                                        while (validOption == false) {
                                            System.out.println();
                                            System.out.println("\tInvalid option.");
                                            System.out.println("\tPlease choose an interactable object.");
                                            System.out.print("\t");
                                            defaultRespone = s.nextLine().toLowerCase();
                                            switch(defaultRespone) {
                                                    case "get key":
                                                    case "grab key":
                                                    case "getkey":
                                                    case "grabkey":
                                                    case "key":
                                                        user.addItem(key);
                                                        System.out.println("Key picked up.");
                                                        servantChambers.removeItem(key);
                                                        hasKey = true;
                                                        validOption = true;
                                                        break;
                                                    
                                                    case "dungeon entrance":
                                                    case "dungeon":
                                                    case "back to dungeon":
                                                    case "leave room":
                                                    case "return to entrance":
                                                    case "leave":
                                                        currentRoom = dungeonEntrance;
                                                        validOption = true;
                                                        break;
                        
                                                default:
                                                    validOption = false;
                                                    break;
                                            }
                                        }
                                    validOption = false;
                                }

                            } else { //uses this if they have picked up the key
                                currentRoom = servantChambersNoKey;
                                System.out.println();
                                System.out.println(currentRoom);
                                System.out.println(currentRoom.listItems());
                                System.out.println("Exits:");
                                System.out.println(currentRoom.listExits());
                                System.out.println("\tWhat would you like to interact with?");
                                System.out.print("\t");
                                servantCham = s.nextLine().toLowerCase();
                                switch(servantCham){
                                    case "dungeon entrance":
                                    case "dungeon":
                                    case "back to dungeon":
                                    case "leave room": //return to entrance
                                    case "return to entrance":
                                    case "leave":
                                        currentRoom = dungeonEntrance;
                                        break;

                                    default: //catches if they dont enter a valid option and loops until they enter a valid choice
                                        while (validOption == false) {
                                        System.out.println();
                                        System.out.println("\tInvalid option.");
                                        System.out.println("\tPlease choose a door.");
                                        System.out.print("\t");
                                        defaultRespone = s.nextLine().toLowerCase();
                                        switch(defaultRespone) {
                                            case "dungeon entrance":
                                            case "dungeon":
                                            case "back to dungeon":
                                            case "leave room":
                                            case "return to entrance":
                                            case "leave":
                                                currentRoom = dungeonEntrance;
                                                validOption = true;
                                                break;

                                            default:
                                                validOption = false;
                                                break;
                                        }
                                    }
                                validOption = false;
                                }

                            }
                            break;

                        case "dining hall":
                        case "dininghall": //switch for room dining hall
                        case "dining":
                        case "dinner":
                            if (choseWrong == false) { //prints if the dont mess up their response
                                currentRoom = diningHall;
                                System.out.println();
                                System.out.println(currentRoom);
                                System.out.println("\nInteractable options:");
                                System.out.println("Exits:");
                                System.out.println(currentRoom.listExits());
                                System.out.println("\tWhat would you like to interact with?");
                                System.out.print("\t");
                                dininghall = s.nextLine();
                                dininghall = dininghall.toLowerCase();
                            }
                            choseWrong = false;
                                switch(dininghall){ //switch for dining hall exit options
                                    case "kitchen":
                                    case "food": //switch for the room kitchen
                                    case "enter kitchen":  
                                        if (foodPickedUp == true){currentRoom = kitchenNoBread;exitChoice = "kitchen";} else {currentRoom = kitchen;} //checks if they have picked up the bread and sends them to kitchen no bread if they have
                                        while (currentRoom == kitchen) {
                                            dininghall = "";
                                            System.out.println();
                                            System.out.println(currentRoom);
                                            System.out.println("\nInteractable options:");
                                            System.out.println("Food:");
                                            System.out.println(currentRoom.listFood());
                                            System.out.println("Exits:");
                                            System.out.println(currentRoom.listExits());
                                            System.out.println("\tWhat would you like to interact with?");
                                            System.out.print("\t");
                                            dininghall = s.nextLine();
                                            switch (dininghall) {
                                                case "dining hall":
                                                case "dining room":
                                                case "dinner room": //return to dining hall
                                                case "dininghall":
                                                case "diningroom":
                                                case "dinnerroom":
                                                    currentRoom = diningHall;
                                                    break;
                                                case "bread":
                                                case "breadstick":
                                                case "grab bread": //grabs food 
                                                case "get bread":
                                                case "food":
                                                    System.out.println("Breadstick picked up.");
                                                    System.out.println();
                                                    user.addFood(breadStick);
                                                    foodPickedUp = true;
                                                    exitChoice = "kitchen";
                                                    currentRoom = kitchenNoBread;
                                                    break;
                                                default: //catches if they dont enter a valid option and loops until they enter a valid choice
                                                    while (validOption == false) {
                                                        System.out.println();
                                                        System.out.println("\tInvalid option.");
                                                        System.out.println("\tPlease choose an interactable object.");
                                                        System.out.print("\t");
                                                        defaultRespone = s.nextLine().toLowerCase();
                                                        switch(defaultRespone) {
                                                            case "dining hall":
                                                            case "dining room":
                                                            case "dinner room":
                                                            case "dininghall":
                                                            case "diningroom":
                                                            case "dinnerroom":
                                                                exitChoice = "dining hall";
                                                                validOption = true;
                                                                break;
                                                            
                                                            case "bread":
                                                            case "breadstick":
                                                            case "grab bread":
                                                            case "get bread":
                                                            case "food":
                                                                System.out.println("Breadstick picked up.");
                                                                System.out.println();
                                                                user.addFood(breadStick);
                                                                foodPickedUp = true;
                                                                validOption = true;
                                                                exitChoice = "kitchen";
                                                                currentRoom = kitchenNoBread;
                                                                break;

                                                            default:
                                                                validOption = false;
                                                                break;
                                                        }
                                                    }
                                                validOption = false;
                                            }
                                        }
                                        break;
                                    case "dungeon entrance":
                                    case "dungeon":
                                    case "back to dungeon": //returns to dungeon entrance
                                    case "leave room":
                                    case "return to entrance":
                                    case "leave":
                                        currentRoom = dungeonEntrance;
                                        break;

                                    default: //catches if they dont enter a valid option and loops until they enter a valid choice
                                        while (validOption == false) {
                                            System.out.println();
                                            System.out.println("\tInvalid option.");
                                            System.out.println("\tPlease choose a door.");
                                            System.out.print("\t");
                                            defaultRespone = s.nextLine().toLowerCase();
                                            switch(defaultRespone) {
                                                case "kitchen":
                                                case "food":
                                                case "enter kitchen":
                                                    exitChoice = "dining hall";
                                                    currentRoom = kitchen;
                                                    validOption = true;
                                                    choseWrong = true;
                                                    dininghall = "kitchen";
                                                    break;

                                                case "dungeon entrance":
                                                case "dungeon":
                                                case "back to dungeon":
                                                case "leave room":
                                                case "return to entrance":
                                                case "leave":
                                                    exitChoice = "dungeon entrance";
                                                    currentRoom = dungeonEntrance;
                                                    validOption = true;
                                                    break;

                                                default:
                                                    validOption = false;
                                                    break;
                                            }
                                        }
                                        validOption = false;
                                       break;
                                }
                            break;
                        case "kitchen": //case for kitchen with no bread
                            currentRoom = kitchenNoBread;
                            System.out.println();
                            System.out.println(currentRoom);
                            System.out.println("\nInteractable options:");
                            System.out.println("Exits:");
                            System.out.println(currentRoom.listExits());
                            System.out.println("\tWhat would you like to interact with?");
                            System.out.print("\t");
                            exitChoice = s.nextLine();
                            exitChoice = exitChoice.toLowerCase();
                            switch (exitChoice) { //switch for kitchen exit choices
                                case "dining hall":
                                case "dining room":
                                case "dinner room":
                                case "dininghall":
                                case "diningroom"://returns to dining hall
                                case "dinnerroom":
                                    currentRoom = diningHall;
                                    break;

                                default: //catches if they dont enter a valid option and loops until they enter a valid choice
                                    while (validOption == false) {
                                        System.out.println();
                                        System.out.println("\tInvalid option.");
                                        System.out.println("\tPlease choose a door.");
                                        System.out.print("\t");
                                        defaultRespone = s.nextLine().toLowerCase();
                                        switch(defaultRespone) {
                                            case "dining hall":
                                            case "dining room":
                                            case "dinner room":
                                            case "dininghall":
                                            case "diningroom":
                                            case "dinnerroom":
                                                exitChoice = "dining hall";
                                                validOption = true;
                                                break;

                                            default:
                                                validOption = false;
                                                break;
                                        }
                                    }
                                validOption = false;
                            }
                            break;

                        case "central hall":
                        case "central room": //switch for the central hall
                        case "centralhall":
                        case "centralroom":
                            if (hasKey == false) {  //returns to dungeon if they dont have the key 
                                System.out.println("Door is locked find the key first.");
                                exitChoice = "dungeon entrance";
                            } else { //only can be entered if user has the key
                                if (defeatedAugustus == false) { //prints central hall if you havent defeated augustus
                                    currentRoom = centralHall;
                                    System.out.println();
                                    System.out.println(currentRoom);
                                    System.out.println("\nInteractable options:");
                                    System.out.println("Enemies:");
                                    System.out.println(currentRoom.listEnemies());
                                    System.out.println("Exits:");
                                    System.out.println(currentRoom.listExits());
                                    System.out.println("\tWhat would you like to interact with?");
                                    System.out.print("\t");
                                    central = s.nextLine().toLowerCase();
                                    switch(central) { //switch for central hall options
                                        case "augustus":
                                        case "enemy":  //switch for attack augustus
                                        case "attack augustus":
                                            System.out.println("You snuck up behind augustus and killed him while he was unaware.");
                                            System.out.println("Mission complete you can now exit the castle to meet up with Cyrus:");
                                            defeatedAugustus = true;
                                            break;

                                        case "dungeon entrance":
                                        case "dungeon":
                                        case "back to dungeon": //switch for leave to dungeon
                                        case "leave room":
                                        case "return to entrance":
                                        case "leave":
                                            exitChoice = "dungeon entrance";
                                            currentRoom = dungeonEntrance;
                                            break;
                                        
                                        default: //catches if they dont enter a valid option and loops until they enter a valid choice
                                             while (validOption == false) {
                                                System.out.println();
                                                System.out.println("\tInvalid option.");
                                                System.out.println("\tPlease choose an interactable object.");
                                                System.out.print("\t");
                                                defaultRespone = s.nextLine().toLowerCase();
                                                switch(defaultRespone) {
                                                    case "augustus":
                                                    case "enemy":
                                                    case "attack augustus":
                                                        System.out.println("You snuck up behind augustus and killed him while he was unaware.");
                                                        System.out.println("Mission complete you can now exit the castle to meet up with Cyrus:");
                                                        defeatedAugustus = true;
                                                        break;

                                                    case "dungeon entrance":
                                                    case "dungeon":
                                                    case "back to dungeon":
                                                    case "leave room":
                                                    case "return to entrance":
                                                    case "leave":
                                                        currentRoom = dungeonEntrance;
                                                        validOption = true;
                                                        break;

                                                    default:
                                                        validOption = false;
                                                        break;
                                                }
                                            }
                                        validOption = false;   
                                    }
                                } else { //prints central hall if you have defeated augustus
                                    currentRoom = centralHallNoAugustus;
                                    System.out.println();
                                    System.out.println(currentRoom);
                                    System.out.println("\nInteractable options:");
                                    System.out.println("Exits:");
                                    System.out.println(currentRoom.listExits());
                                    System.out.println("\tWhat would you like to interact with?");
                                    System.out.print("\t");
                                    central = s.nextLine().toLowerCase();
                                    switch(central) { //switch for central hall no augustus options
                                        case "dungeon entrance":
                                        case "dungeon":
                                        case "back to dungeon": //return to dungeon 
                                        case "leave room":
                                        case "return to entrance":
                                        case "leave":
                                            exitChoice = "dungeon entrance";
                                            currentRoom = dungeonEntrance;
                                            break;

                                        case "final exit":
                                        case "castle exit": //exit the castle but game won so prints the game over text
                                        case "castleexit":
                                        case "finalexit":
                                            insideCastle = false;
                                            userStats.println("End: \n" + user.userStats());
                                            break;

                                        default: //catches if they dont enter a valid option and loops until they enter a valid choice
                                            while (validOption == false) {
                                                System.out.println();
                                                System.out.println("\tInvalid option.");
                                                System.out.println("\tPlease choose a door.");
                                                System.out.print("\t");
                                                defaultRespone = s.nextLine().toLowerCase();
                                                switch(defaultRespone) {
                                                    case "dungeon entrance":
                                                    case "dungeon":
                                                    case "back to dungeon":
                                                    case "leave room":
                                                    case "return to entrance":
                                                    case "leave":
                                                        currentRoom = dungeonEntrance;
                                                        validOption = true;
                                                        break;

                                                    case "final exit":
                                                    case "castle exit":
                                                    case "castleexit":
                                                    case "finalexit":
                                                        insideCastle = false;
                                                        break;

                                                    default:
                                                        validOption = false;
                                                        break;
                                                }
                                            }
                                        validOption = false;
                                    }
                                }
                            }
                            break;

                        case "castle exit":
                        case "exit castle":
                        case "castleexit": //exit for castle from dungeon 
                        case "exitcastle":
                            while (exitCastle == false) { //keeps running until they exit
                                System.out.println("\tAre you sure you want to leave the castle after you've come so far?");
                                System.out.print("\t");
                                exit = s.nextLine().toLowerCase();
                                switch(exit) {
                                    case "naw":
                                    case "nah":
                                    case "i dont want to":
                                    case "i dont wanna":
                                    case "why would i want to":
                                    case "hell naw":
                                    case "hell fucking no":
                                    case "fuck no":
                                    case "hell no":
                                    case "fuck off":
                                    case "no":
                                    case "i dont want to do this": //all of the not to leave options
                                        insideCastle = true;
                                        exitCastle = true;
                                        System.out.println("Returning to Dungeon Entrance.");
                                        exitChoice = "dungeon entrance";
                                        break;
                                    
                                    case "hell yes":
                                    case "fuck yes":
                                    case "hell yeah":
                                    case "yeah":
                                    case "fuck yeah":
                                    case "yes":
                                    case "sure":
                                    case "i guess":
                                    case "yeah i guess":
                                    case "sure why not":
                                    case "yes of course":
                                    case "of course":
                                    case "ya":
                                    case "yea":
                                    case "yah":
                                    case "let me leave": //all of the to leave options
                                        insideCastle = false;
                                        userStats.println("End: \n" + user.userStats());
                                        exitCastle = true;
                                        break;
                                    default: //catches if they dont enter a valid option and loops until they enter a valid choice
                                        while (validOption == false) {
                                            System.out.println("\tPlease input yes or no.");
                                            System.out.print("\t");
                                            exit = s.nextLine().toLowerCase();
                                            switch(exit) {
                                                case "naw":
                                                case "nah":
                                                case "i dont want to":
                                                case "i dont wanna":
                                                case "why would i want to":
                                                case "hell naw":
                                                case "hell fucking no":
                                                case "fuck no":
                                                case "hell no":
                                                case "fuck off":
                                                case "no":
                                                case "i dont want to do this":
                                                    insideCastle = true;
                                                    validOption = true;
                                                    exitCastle = true;
                                                    exitChoice = "dungeon entrance";
                                                    break;
                                                case "hell yes":
                                                case "fuck yes":
                                                case "hell yeah":
                                                case "yeah":
                                                case "fuck yeah":
                                                case "yes":
                                                case "sure":
                                                case "i guess":
                                                case "yeah i guess":
                                                case "sure why not":
                                                case "yes of course":
                                                case "of course":
                                                case "ya":
                                                case "yea":
                                                case "yah":
                                                case "let me leave":
                                                    insideCastle = false;
                                                    exitCastle = true;
                                                    validOption = true;
                                                    break;

                                                default:
                                                    validOption = false;
                                            }
                                        }
                                        validOption = false;
                                }
                            }
                            exitCastle = false;
                            break;

                        default: //catches if they dont enter a valid option and loops until they enter a valid choice
                        //this is default for the original switch
                            while (validOption == false) {
                                System.out.println();
                                System.out.println("\tInvalid option.");
                                System.out.println("\tPlease choose a door.");
                                System.out.print("\t");
                                defaultRespone = s.nextLine().toLowerCase();
                                switch(defaultRespone) {
                                    case "workshop":
                                    case "work":
                                    case "shop":
                                    case "work shop":
                                        exitChoice = "work shop";
                                        currentRoom = workShop;
                                        validOption = true;
                                        break;

                                    case "servant chambers":
                                    case "servantchambers":
                                    case "servant chamber":
                                    case "servant":
                                    case "chambers":
                                        exitChoice = "servant chambers";
                                        currentRoom = servantChambers;
                                        validOption = true;
                                        break;

                                    case "dining hall":
                                    case "dininghall":
                                    case "dining":
                                    case "dinner":
                                        exitChoice = "dining hall";
                                        currentRoom = diningHall;
                                        validOption = true;
                                        break;

                                    case "castle exit":
                                    case "castleexit":
                                    case "exit castle":
                                    case "exitcastle":
                                        insideCastle = false;
                                        validOption = true;
                                        break;

                                    case "central hall":
                                    case "central room":
                                    case "centralhall":
                                    case "centralroom":
                                        if (hasKey == false) {
                                            System.out.println("Door is locked find the key first.");
                                            exitChoice = "dungeon entrance";
                                            validOption = true;
                                        } else {
                                            exitChoice = "central hall";
                                            validOption = true;
                                        }
                                        break;

                                    default:
                                        validOption = false;
                                        break;
                                }
                            }
                            validOption = false;
                        }
            }
            beatElf = false; //resets booleans to false
            pickedHammerUp = false; //resets booleans to false
            elfGetHammer = false; //resets booleans to false
            foodPickedUp = false; //resets booleans to false
            hasKey = false; //resets booleans to false
            validOption = false; //resets booleans to false
            userStats.close(); //closes printWriter
            return insideCastle; //returns inside castle boolean
        }
}