import java.util.Scanner;

public class Introduction {
    private String pName;
    private Character playerLetter;
    private String playerCapitalized = "";
    private int requestCount = 0;
    private Boolean castleEntered = false;

    public Introduction () {
    }

    public void gameIntro () { //beginning of the game plays when game is started or restarted 
        System.out.println("\tYou awaken dazed and confused in a lush field populated with roses, hydrangeas, orchids, tulips and sunflowers as far as the eye can see,\n\tlocated on a mystical Island floating far above the gleaming blue sea and fuffy clouds.");
        System.out.println("\tAs you begin to regain consciousness an old man appears.");
        System.out.println("Old Man: Welcome Traveler to the Utopia in the sky, the Celestial Island.");
        System.out.println("Old Man: My name is Cyrus, I have been seeking a courageous Traveler to assist me in a pressing mission.");
        System.out.println("Cyrus: What is your name?");
        System.out.print("\t");

        
    }

    public void answerYes (String playerName) { //if the user helps cyrus
        System.out.println("Cyrus: Thank you I knew I was right about you " + playerName + ". Now before we head off I want to give you something to show my appreciation, please take this armor.");
        System.out.println("\tLeather Armor acquired: +5 health");
    }

    public void answerNo () { //if the user doesnt help cyrus
        System.out.println("\tAh that's a shame but it's understandable thank you anyway " + pName + ". Hopefully the next Traveler will be more willing to help me.");
    }
    
    public void takeToCastle (Scanner s, String enterCastle, String playerName) { //leads user to castle and asks if they want to enter only yes allowed
        System.out.println("Cyrus: Now please follow me I'll lead you to the place where this catastrophe happened only a few short months ago.\n\tThe place where this nation fell from a Utopia to a Fascist state.");
        System.out.println("Cyrus: I used to be part of our government senate before we were crushed by King Augustus of Caesar Island.\n\tNow all of the citizens deal with oppression by a ruler that fought his way to the throne.\n\tHe needs to be defeated so they can be freed and I can regain my po... Ah, we have reached the castle.\n\tNow it is time for me to tell you my request.\n");
        System.out.println("Cyrus: " + playerName + ", I would like you to free our government from the tyranny of Augustus and help return our country to the utopia we created.");
        System.out.println("Cyrus: Behind me you will find an underground entrance to the castle, leading you into it's dungeon.\n\tGood Luck I believe in you!");
        System.out.println("");
        System.out.println("\tWould you like to enter the castle?");
        System.out.print("\t");
        enterCastle = s.nextLine();
        enterCastle = enterCastle.toLowerCase();
        while (castleEntered == false) {
            ++requestCount;
            switch (enterCastle) { //switch for entering castle 
                case "naw":
                case "nah":
                case "hell naw":
                case "hell fucking no":
                case "fuck no":
                case "i wont help you":
                case "go screw yourself":
                case "hell no":
                case "fuck off":
                case "no":
                case "fuck you":
                case "go fuck yourself":
                case "i dont want to do this":
                case "i dont wanna":
                case "i dont want to":
                case "let me leave":
                case "youre annoying":
                case "that sounds lame":
                case "id rather be boring and alive":
                case "im not fun":
                case "i dont like this game": //all cases for not entering castle
                    if (requestCount > 9) { //only displays on the 10th time cyrus asks
                        System.out.println("Cyrus: IF I DONT HEAR YES ILL KILL YOU.");
                        System.out.print("\t");
                        enterCastle = s.nextLine();
                        if (enterCastle.equalsIgnoreCase("no")) {
                            System.exit(0);
                        }
                    } else if (requestCount > 4) { //displays the 5th - 9th times cyrus asks
                        System.out.println("Cyrus: The castle's entrance is just behind me HEAD ON IN.");
                        System.out.print("\t");
                        enterCastle = s.nextLine();
                    }else { 
                        System.out.println("Cyrus: I don't mean to be rude but you did agree to help, so please enter the castle.");
                        System.out.print("\t");
                        enterCastle = s.nextLine();
                    }
                    castleEntered = false;
                    continue;
                     
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
                case "yeah i guess": //all cases for entering castle
                    castleEntered = true;
                    break;

                default: //if they dont enter some form of yes or no
                    System.out.println("Cyrus: I dont understand what " + enterCastle + " means. Can you give me a clear Yes?");
                    System.out.print("\t");
                    enterCastle = s.nextLine();
                    castleEntered = false;
                    continue;
            }
        }
        System.out.println("Entering castle dungeon now.");
    }
}