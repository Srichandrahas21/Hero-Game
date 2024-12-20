public class Monster extends Hero {
	/**
     * Constructs a Monster object
     * @param name name of the monster
     * @param health health of the monster
     * @param power power of the monster
     * @param attackAbility attack ability of the monster
     */
    public Monster(String name, int health, double power, AttackAbility attackAbility) {
        super("Monster", name, health, power, attackAbility);
    }
    
    /**
     * Calculates the power of the monster
     * power is calculated based on the health of the monster
     * @return the calculated power of the monster
     */
    @Override
    public double calculatePower() {
        int health = getHealth();
        // Get AttackAbility
        AttackAbility attackAbility = getAttackAbility();
        // Retrieve maxPower from AttackAbility
        double maxPower = attackAbility.getMaxPower(); 

        if (health >= 75) {
            return getPower();
        } else if (health >= 50) {
            return getPower() * 0.5;
        } else if (health >= 25) {
            return getPower() * 0.25;
        } else if (health > 0) {
            return maxPower;
        }
        //When health is 0 or less power is 0
        return 0;  
    }

    /**
     * Returns the monster as a string
     * Uses the superclass toString method to include all of the hero's description
     * @return the monster as a string
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
