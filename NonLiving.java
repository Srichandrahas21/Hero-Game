public class NonLiving extends Hero {
    private static int nonLivingCount = 0;

    /**
     * Constructs a NonLiving object
     * @param name name of the non living hero
     * @param health health of the non living hero
     * @param power power of the non living hero
     * @param attackAbility attack ability of the non living hero
     */
    public NonLiving(String name, int health, double power, AttackAbility attackAbility) {
        super("NonLiving", name, health, power, attackAbility); 
        if (health > 0) {
            nonLivingCount++;
        }
    }

    /**
     * Gets the count of non living heroes created
     * @return the count of non living heroes created
     */
    public static int getNonLivingCount() {
        return nonLivingCount;
    }

    /**
     * Calculates the power of the non living hero
     * If health is zero or less the power is zero
     * @return the calculated power of the non-living hero 
     */
    @Override
    public double calculatePower() {
        if (getHealth() <= 0) {
            return 0;
        }
        return getPower();
    }


    /**
     * Returns the non-living entity as a string
     * Uses the superclass toString method to include all of the hero's description
     * @return the non-living entity as a string
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
