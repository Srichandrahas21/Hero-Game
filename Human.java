public class Human extends Hero {
	/**
	 * Constructs a Human object
	 * @param name name of the human
	 * @param health health of the human
	 * @param power power of the human
	 * @param attackAbility attack ability of the human
	 */
    public Human(String name, int health, double power, AttackAbility attackAbility) {
        super("Human", name, health, power, attackAbility);
    }

    /**
     * Calculates the power of the human
     * The power is calculated by the formula
     * @return the calculated power of the human
     */
    @Override
    public double calculatePower() {
        return (getHealth() / 100.0) * getPower();
    }

    /**
     * Returns the human as a string.
     * Uses the superclass toString method to include all of the hero's description
     * @return the human as a string
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
