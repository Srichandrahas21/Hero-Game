public abstract class Hero {
	//fields
    private static int totalNumber = 0;
    private String type;
    private String name;
    private int health;
    private double power;
    private AttackAbility attackAbility;

    /**
     * Constructs a Hero object
     * @param type type of the hero
     * @param name name of the hero
     * @param health health of the hero
     * @param power power of the hero
     * @param attackAbility attack ability of the hero
     */
    public Hero(String type, String name, int health, double power, AttackAbility attackAbility) {
        this.type = type;
        this.name = name;
        this.health = health;
        this.power = power;
        this.attackAbility = attackAbility;
        totalNumber++;
    }

    /**
     * Gets the total number of heroes
     * @return the total number of heroes
     */
    public static int getTotalNumber() {
        return totalNumber;
    }

    /**
     * Gets the type of the hero
     * @return the type of the hero
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the name of the hero
     * @return the name of the hero
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the health of the hero
     * @return the health of the hero
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health of the hero
     * @param health the health set for the hero
     */
    public void setHealth(int health) {
        this.health = health; 
    }
    
    /**
     * Gets the power of the hero
     * @return the power of the hero
     */
    public double getPower() {
        return power;
    }

    /**
     * Gets the attack ability of the hero
     * @return the attack ability of the hero
     */
    public AttackAbility getAttackAbility() {
        return attackAbility;
    }

    /**
     * Calculates the power of the hero
     * @return the calculated power of the hero
     */
    public abstract double calculatePower();

    /**
     * Returns the hero as a string
     * Includes type, name, health, power, and attack ability
     * @return the hero as a string
     */
    @Override
    public String toString() {
        return "Type: " + type + ", Name: " + name + ", Health: " + health + ", Power: " + power + ", Attack Ability: " + attackAbility;
    }
}
