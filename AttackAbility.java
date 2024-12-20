public class AttackAbility {
	//fields
    private String attacktype;
    private double maxPower;

    /**
     * Constructs an AttackAbility object
     * @param attacktype type of the attack
     * @param maxPower maximum power of the attack
     */
    public AttackAbility(String attacktype, double maxPower) {
        this.attacktype = attacktype;
        this.maxPower = maxPower;
    }
    
    /**
     * Gets the type of the attack
     * @return the type of the attack
     */
    public String getType() {
        return attacktype;
    }
    
    /**
     * Gets the maximum power of the attack
     * @return the maximum power of the attack
     */
    public double getMaxPower() {
        return maxPower;
    }

    /**
     * Returns the attack ability as a string
     * @return the attack type as a string
     */
    @Override
    public String toString() {
        return attacktype; 
    }
}
