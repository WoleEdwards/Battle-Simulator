public class Move {

    private String moveName = "";
    private int damage = 0;
    private double healFactor = 0;
    private double attackBoost = 1;
    private double defenceBoost = 1;
    private double speedBoost = 1;
    private boolean shielded = false;
    private boolean poison = false;
    private boolean burn = false;
    private boolean userSleep = false;
    private boolean alwaysFirst = false;
    private String type = "";
    private String typeImage = "";

    /**
     * Constructor for instantiating a new move object
     * 
     * @param moveName     - Name of the move
     * @param type         - Move type
     * @param damage       - Base power of the move
     * @param healFactor   - Heal factor of the move (for recoil damage and healing
     *                     moves)
     * @param attackBoost  - Attack stat factor (for increasing/decreasing attack
     *                     stat)
     * @param defenceBoost - Defence stat factor (for increasing/decreasing defence
     *                     stat)
     * @param speedBoost   - Speed stat factor (for increasing/decreasing speed
     *                     stat)
     * @param shielded     - boolean that classifies a move as a protection move
     * @param alwaysFirst  - boolean that classifies a move as a priority move
     * @param typeImage    - String representing the file name of the image of the
     *                     move type
     */
    public Move(String moveName, String type, int damage, double healFactor,
            double attackBoost, double defenceBoost, double speedBoost, boolean shielded, boolean alwaysFirst,
            String typeImage) {
        this.moveName = moveName;
        this.damage = damage;
        this.healFactor = healFactor;
        this.attackBoost = attackBoost;
        this.defenceBoost = defenceBoost;
        this.speedBoost = speedBoost;
        this.shielded = shielded;
        this.alwaysFirst = alwaysFirst;
        this.type = type;
        this.typeImage = typeImage;
    }

    /**
     * Constructor for instantiating a new Move object. This constructor is
     * primarily used for instantiating moves that affect the opponent's status conditions.
     * 
     * @param moveName   - Name of the move
     * @param type       - Move type
     * @param healFactor - Heal factor of the move (for healing moves)
     * @param poison     - boolean variable that classifies a move as one that
     *                   poisons the opponent
     * @param burn       - boolean variable that classifies a move as one that burns
     *                   the opponent
     * @param userSleep  - boolean variable that classifies a move as one that will
     *                   put the user of the move to sleep
     * @param typeImage  - String representing the file name of the image of the
     *                   move type.
     */
    public Move(String moveName, String type, double healFactor, boolean poison, boolean burn, boolean userSleep,
            String typeImage) {
        this.moveName = moveName;
        this.type = type;
        this.poison = poison;
        this.burn = burn;
        this.healFactor = healFactor;
        this.userSleep = userSleep;
        this.typeImage = typeImage;
    }

    /**
     * 
     * @return - String representing the name of the move
     */
    public String getMoveName() {
        return moveName;
    }

    /**
     * 
     * @return - int value representing the base power of a move
     */
    public int getDamage() {
        return damage;
    }

    /**
     * 
     * @return - double value representing the health increase/decrease factor
     *         of a move
     */
    public double getHealFactor() {
        return healFactor;
    }

    /**
     * 
     * @return - double value representing the attack stat increase/decrease factor
     *         of a move
     */
    public double getAttackBoost() {
        return attackBoost;
    }

    /**
     * 
     * @return - double value representing the defence stat increase/decrease factor
     *         of a move
     */
    public double getDefenceBoost() {
        return defenceBoost;
    }

    /**
     * 
     * @return - double value representing the speed stat increase/decrease factor of a
     *         move
     */
    public double getSpeedBoost() {
        return speedBoost;
    }

    /**
     * 
     * @return - boolean value that determines whether this move is or is not a
     *         protection move.
     *         If the method returns true, the move is a protection move and the 
     *         user of the move can be shielded from incoming attacks from the 
     *         opponent.
     *         If the method returns false, the move is not a protection move.
     */
    public boolean isShielded() {
        return shielded;
    }

    /**
     * 
     * @return - boolean value that determines whether this move is or is not a
     *         poisoning move
     *         If the method returns true, the move is one that can poison the
     *         opponent
     *         If the method returns false, the move cannot poison the opponent
     */
    public boolean poisonMove() {
        return poison;
    }

    /**
     * 
     * @return - boolean value that determines whether this move is or is not a
     *         burning move
     *         If the method returns true, the move is one that can burn the
     *         opponent
     *         If the method returns false, the move cannot burn the opponent
     */
    public boolean burnMove() {
        return burn;
    }

    /**
     * 
     * @return - boolean value that determines whether this move is or is not able
     *         to put the user of the move to sleep.
     *         If the method returns true, the move is able to put the user of the
     *         move to sleep
     *         If the method returns false, the move cannot put the user of the move
     *         to sleep
     */
    public boolean sleepMove() {
        return userSleep;
    }

    /**
     * 
     * @return - boolean variable that determines whether this move is or is not 
     *          a priority move.
     *          If the method returns true, the move is a priority move and should
     *          be performed before non-priority moves.
     *          If the method returns false, the move is not a priority move 
     */
    public boolean attacksFirst() {
        return alwaysFirst;
    }

    /**
     * 
     * @return - String representing the type of the move 
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @return - String representing the file name of the image of the move's type
     */
    public String getTypeImage() {
        return typeImage;
    }
}
