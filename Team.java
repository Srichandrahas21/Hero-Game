import java.util.ArrayList;

public class Team {
	//fields
    private String name;
    private ArrayList<Hero> team;
    
    /**
     * Constructs Team object with name and creates an empty array list of heroes
     * @param name name of the team
     */
    //constructor
    public Team(String name) {
        this.name = name;
        this.team = new ArrayList<>();
    }
    
    /**
     * gets the name of the team
     * @return the team name
     */
    public String getName() {
        return name;
    }
    
    /**
     * gets list of heroes in team
     * @return the list
     */
    public ArrayList<Hero> getTeam() {
        return team;
    }
    
    /**
     * adds hero to team
     * @param hero the hero to add into the team
     */
    public void addHero(Hero hero) {
        team.add(hero);
    }

    /**
     * adds up the power of each hero in the team
     * @return the total power
     */
    public double calculateTotalPower() {
        double totalPower = 0;
        for (int i = 0; i < team.size(); i++) {
            totalPower += team.get(i).calculatePower();
        }
        return totalPower;
    }
}
