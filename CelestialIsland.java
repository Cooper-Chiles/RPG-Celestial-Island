/*
Name: Cooper Chiles
Class: CPSC1060
Lab section: 005
Github Link: https://github.com/Cooper-Chiles/RPG-Celestial-Island
*/
import java.util.Scanner;

public class CelestialIsland {
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        Introduction intro;
        String playerResponse = null;
        String enterCastle = null;
        char acknowledgeStats;
        char restartGame;
        Boolean helpCyrus = false;
        Boolean endGame = false;
        Boolean castleEntered = false;
        Boolean insideCastle = true;
        Boolean playerResponds = false;
        Boolean stillInCastle;
        Boolean wonGame;

        while (endGame == false) { //loops unless the user ends game or gets killed
            intro = new Introduction();
            String playerName = "";
            playerResponds = false;
            helpCyrus = false;
            endGame = false;
            castleEntered = false;
            insideCastle = true;
            intro.gameIntro();
            playerName = s.nextLine();
            Player user = new Player(playerName); //creates new player
            Weapon hands = new Weapon ("Fists", 1); 
            user.addWeapon(hands); //adds weapon fists to player
            System.out.println("");
            System.out.println("Cyrus: " + playerName + ", you seem like a strong and valiant hero willing to assist an old man with a dangerous quest, however I won't force you to help me.\n\tYou can say no and keep living a peaceful and boring existence or you can join me to help free this Island from tyranny.");
            System.out.println("Cyrus: I know this is rather sudden, but would you be willing to assist me in my journey?");
            System.out.print("\t");
            playerResponse = s.nextLine();
            playerResponse = playerResponse.toLowerCase();
            while (helpCyrus == false) { //loops until they responds in some form of yes or no
                switch (playerResponse) {
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
                    case "yah":
                    case "fine": //all possible yes answers
                        System.out.println("");
                        intro.answerYes(playerName);
                        System.out.println("");
                        user.stats();
                        System.out.println("");
                        System.out.println("\tAcknowledge Stats(press a):");
                        System.out.print("\t");
                        acknowledgeStats = s.next().charAt(0);
                        while (!(acknowledgeStats == 'a')) {
                            System.out.print("\tPlease acknowledge your stats.");
                            System.out.print("\t");
                            acknowledgeStats = s.next().charAt(0);
                        }
                        s.nextLine();
                        System.out.println("");
                        intro.takeToCastle(s, enterCastle, playerName); //takes player to castle
                        castleEntered = true;
                        helpCyrus = true;
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
                    case "let me leave": //all possible no options
                        System.out.println("");
                        intro.answerNo();
                        System.out.println("");
                        System.out.println("\tWould you like to play again?(y/n)");
                        System.out.print("\t");
                        restartGame = s.nextLine().charAt(0);
                        if (restartGame == 'n') {
                            helpCyrus = true;
                            endGame = true;
                        } else if (restartGame == 'y') {
                            helpCyrus = true;
                            System.out.println("");
                        }
                        break;
                    default: 
                        System.out.println("Cyrus: I dont understand what " + playerResponse + " means.");
                        System.out.print("\t");
                        playerResponse = s.nextLine();
                        break;
                }
            }

            if (castleEntered == true)  { //if player entered castle this runs
                CastleMap castleMap = new CastleMap();
                Castle castleRooms = new Castle();
                stillInCastle = castleRooms.createCastle(castleMap, insideCastle, s, user); //returns boolean for inside castle and creates the castle map
                wonGame = castleRooms.wonGame(); //returns boolean for if augustus was beaten
                if (stillInCastle == false && wonGame == true) { //runs if they left after beating augustus
                    System.out.println("Well done my valiant hero! I'm grateful for your assistance with freeing this island from tyranny. You will be looked at as a savior of our people.\nEspecially after YOU'RE DEAD! Cyrus attacks and defeats you in gladitorial combat.\nThank you for your help, Now its my turn to take rule of this country! muhahahahahahahahahaha");
                    System.out.println("Game Over.");
                    System.out.println("\tWould you like to play again?");
                    System.out.print("\t");
                    playerResponse = s.nextLine();
                    while (playerResponds == false) {
                        if (playerResponse.equalsIgnoreCase("no")) {
                            System.exit(0);
                        } else if (playerResponse.equalsIgnoreCase("yes")) {
                            playerResponds = true;
                        } else {
                            System.out.println("\tPlease input yes or no.");
                            System.out.print("\t");
                            playerResponse = s.nextLine();
                            playerResponds = false;
                        }
                    }
                }
                if (stillInCastle == false && wonGame == false) { //runs if they just left the castle before beating augustus
                    System.out.println("Cyrus: I'm disappointed in you I thought you were stronger than this. I guess i'll have to look for someone else to help.");
                    System.out.println("");
                    System.out.println("\tWould you like to play again?");
                    System.out.print("\t");
                    playerResponse = s.nextLine();
                    while (playerResponds == false) { //runs until the player makes a yes or no response
                        if (playerResponse.equalsIgnoreCase("no")) {
                            System.exit(0);
                        } else if (playerResponse.equalsIgnoreCase("yes")) {
                            playerResponds = true;
                        } else {
                            System.out.println("Please input yes or no.");
                            System.out.print("\t");
                            playerResponse = s.nextLine();
                            playerResponds = false;
                        }
                    }
                }
            }
            insideCastle = false;
            castleEntered = false;
            playerResponds = false; 
        }
    }
}