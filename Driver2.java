/* 	
 *
 * Project 3
 * <Brief project description: 
 *  This Java project is a program for a game that reads a list of heroes from a file and provides players
 *  with several options through a menu. These options include displaying all heroes, showing the number
 *  of non-living characters, adding players to the game, building teams by selecting heroes, and starting the game. 
 *  Players take turns selecting heroes for their teams, and after the teams are built, the game calculates each hero's 
 *  power based on their health. The total power of each team is then compared to determine the winner.>
 * 
 */
 
import java.io.*;
import java.util.*;

public class Driver2 {
	//fields 
	
	//max number of heroes a team can have
    private static final int TEAM_SIZE = 100;
    private static ArrayList<Hero> heroes;
    private static Team team1;
    private static Team team2;

    public static void main(String[] args) throws IOException {
    	//load hero info with loadHeroesFromFile method
        heroes = loadHeroesFromFile("heroInfo.txt");

        Scanner scanner = new Scanner(System.in);
        
        //set program running = true so quit option can work
        boolean running = true;

        while (running) {
        	// main menu of game
            System.out.println("Main Menu:");
            System.out.println(" 1. Show all characters     ");
            System.out.println(" 2. How many non-living?    ");
            System.out.println(" 3. Add players to the game ");
            System.out.println(" 4. Build the teams         ");
            System.out.println(" 5. Start the game          ");
            System.out.println(" 6. Quit                    ");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            
            //switch statement for display menu options to be chosen
            switch (choice) {
                case 1:
                    showAllCharacters();
                    break;
                case 2:
                    showNonLivingCount();
                    break;
                case 3:
                    addteam();
                    break;
                case 4:
                    buildTeams();
                    break;
                case 5:
                    startGame();
                    break;
                case 6:
                	System.out.println("Quitting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }

    }
    
    /**
     * Loads heroes from a file
     * Parses hero data and creates hero objects
     * @param fileName the name of the file with hero data
     * @return list of heroes
     * @throws IOException if an error occurs while reading the file
     */
    private static ArrayList<Hero> loadHeroesFromFile(String fileName) throws IOException {
        //ArrayList to store the heroes
        ArrayList<Hero> heroes = new ArrayList<>();
        
        //BufferedReader to read the file
        BufferedReader inputFile = new BufferedReader(new FileReader(fileName));
        
        //String to hold each line read from the file
        String line;
        
        //Reads file until the end of the file
        while ((line = inputFile.readLine()) != null) {
            
            //Split the line into an array of strings using a comma as a delimiter
            String[] data = line.split(",");
            String type = data[0];
            
            //Check if the type is "Human" and the length of data is 6
            if (type.equals("Human") && data.length == 6) {
                //Extract the name, health, and maxPower from the data
                String name = data[1];
                int health = Integer.parseInt(data[2]);
                double maxPower = Double.parseDouble(data[3]);
                AttackAbility attackAbility = new AttackAbility(data[4], maxPower);
                //Add new Human hero to the list
                heroes.add(new Human(name, health, maxPower, attackAbility));
                
            //Check if the type is "Monster" and the length of data is 7
            } else if (type.equals("Monster") && data.length == 7) {
                // Extract the name, health, and maxPower from the data
                String name = data[1];
                int health = Integer.parseInt(data[2]);
                double maxPower = Double.parseDouble(data[3]);
                AttackAbility attackAbility = new AttackAbility(data[4], Double.parseDouble(data[6]));
                // Add new Monster hero to the list
                heroes.add(new Monster(name, health, maxPower, attackAbility));
                
            //Check if the type is "NonLiving" and the length of data is 6
            } else if (type.equals("NonLiving") && data.length == 6) {
                // Extract the name, health, and maxPower from the data
                String name = data[1];
                int health = Integer.parseInt(data[2]);
                double maxPower = Double.parseDouble(data[3]);
                AttackAbility attackAbility = new AttackAbility(data[4], maxPower); 
                // Add new NonLiving hero to the list
                heroes.add(new NonLiving(name, health, maxPower, attackAbility));
            }
        }
        
        inputFile.close();
        return heroes;
    }

    
    /**
     * Displays all of the heroes
     * Prints the list of heroes and their total count
     */
    private static void showAllCharacters() {
        System.out.println("All Heroes:");
        
        //Loop through the list of heroes and print each hero
        for (int i = 0; i < heroes.size(); i++) {
            System.out.println(heroes.get(i));
        }
        System.out.println("Total Number of Heroes: " + Hero.getTotalNumber());
    }

    /**
     * passes through the list of heros looks for matching string and adds to non living count and then displays it
     */
    private static void showNonLivingCount() {
    	//Initialize non living heroes counter
        int nonLivingCount = 0;
        //Loop through the list of heroes and adds to the count if NonLiving type is there
        for (int i = 0; i < heroes.size(); i++) {
            if (heroes.get(i).getType().equals("NonLiving")) {
                nonLivingCount++;
            }
        }
        System.out.println("Number of non living heroes: " + nonLivingCount);
    }
    
    /**
     * Adds teams to the game
     * tells the user to enter the names for Team 1 and Team 2
     */
    private static void addteam() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name for Team 1: ");
        String team1Name = scanner.nextLine();
        team1 = new Team(team1Name);

        System.out.print("Enter the name for Team 2: ");
        String team2Name = scanner.nextLine();
        team2 = new Team(team2Name);
    }

    /**
     * Builds the teams by selecting heroes from the list of heroes displayed
     * Allows users to pick heroes alternately for both teams
     */
    private static void buildTeams() {
    	//make sure that there is team names before team building happens to avoid the big errors
        if (team1 == null) {
            System.out.println("Please add team before building teams.");
            //exit the method
            return;
        }else if(team2 == null) {
        	System.out.println("Please add team before building teams.");
        	//exit the method
            return;
        }

        Scanner scanner = new Scanner(System.in);
        //Loop through the number of heroes to add to the teams
        for (int i = 0; i < TEAM_SIZE; i++) {
        	
        	//If no more heroes are left stop loop
            if (heroes.isEmpty()) {
                System.out.println("No more heroes available.");
                break;
            }

            //Show available heroes
            System.out.println("Available Heroes:");
            for (int j = 0; j < heroes.size(); j++) {
                System.out.println((j + 1) + ": " + heroes.get(j));
            }

            //Team 1's hero selection
            System.out.print(team1.getName() + ", choose a hero 1-" + heroes.size() + ": ");
            int choice1 = scanner.nextInt() - 1;
            if (choice1 >= 0 && choice1 < heroes.size()) {
            	//Add hero to Team 1
                team1.addHero(heroes.get(choice1));
                //Remove the selected hero from the list
                heroes.remove(choice1);
            }

            if (heroes.isEmpty()) {
                System.out.println("No more heroes available.");
                break;
            }

            // Team 2's hero selection
            System.out.print(team2.getName() + ", choose a hero 1-" + heroes.size() + ": ");
            int choice2 = scanner.nextInt() - 1;
            if (choice2 >= 0 && choice2 < heroes.size()) {
            	//Add hero to Team 1
                team2.addHero(heroes.get(choice2));
                //Remove the selected hero from the list
                heroes.remove(choice2);
            }
        }
    }

    /**
     * Starts the game
     * Randomizes hero health and determines the winning team
     */
    private static void startGame() {
    	//make sure that there are teams built before starting games to avoid errors
        if (team1 == null) {
            System.out.println("Teams not built yet. Please build the teams first.");
            return;
        }else if(team2 == null) {
        	System.out.println("Teams not built yet. Please build the teams first.");
            return;
        }

        Random random = new Random();
        //Randomizes health for heroes in Team 1 and Team 2
        randomizeHealth(team1.getTeam(), random);
        randomizeHealth(team2.getTeam(), random);

        displayTeams(team1, team2);
        determineWinner(team1, team2);
    }

    /**
     * Randomizes the health of heroes
     * Sets a random health between 0 and 100 for each hero
     * @param heroes the list of heroes
     * @param random the random number generator
     */
    private static void randomizeHealth(ArrayList<Hero> heroes, Random random) {
        for (int i = 0; i < heroes.size(); i++) {
        	//Random health between 0-100
            heroes.get(i).setHealth(random.nextInt(101)); 
        }
    }

    
    /**
     * Displays the teams and their heroes in the team
     * @param team1 the first team
     * @param team2 the second team
     */
    private static void displayTeams(Team team1, Team team2) {
    	//Displays Team 1 name and heroes with their power
        System.out.println("\n" + team1.getName() + "'s Team:");
        for (int i = 0; i < team1.getTeam().size(); i++) {
            Hero hero = team1.getTeam().get(i);
            System.out.println(hero + ", Power: " + hero.calculatePower());
        }
        
        //Displays Team 2 name and heroes with their power
        System.out.println("\n" + team2.getName() + "'s Team:");
        for (int i = 0; i < team2.getTeam().size(); i++) {
            Hero hero = team2.getTeam().get(i);
            System.out.println(hero + ", Power: " + hero.calculatePower());
        }
    }


    /**
     * Determines the winner based on total team power
     * @param team1 the first team
     * @param team2 the second team
     */
    private static void determineWinner(Team team1, Team team2) {
    	//Calculate the total power of both teams
        double power1 = team1.calculateTotalPower();
        double power2 = team2.calculateTotalPower();

        //Display the total power of both teams
        System.out.println("\n" + team1.getName() + "'s Total Power: " + power1);
        System.out.println(team2.getName() + "'s Total Power: " + power2);
        
        //Compare the total power and determine the winner
        if (power1 > power2) {
            System.out.println("Congratulations " + team1.getName() + " You win!");
        } else if (power2 > power1) {
            System.out.println("Congratulations " + team2.getName() + " You win!");
        } else {
            System.out.println("It's a tie!");
        }
    }
}
