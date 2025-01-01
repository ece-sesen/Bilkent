import java.util.ArrayList;

public class BattleManagerR
{
    // Instance Variables
    private final KnightR knight; // A Knight instance representing the player-controlled Knight
    private final ArrayList<BattleColumnR> columns; // BattleColumn objects for columns in the game grid

    /* 
     * Initializes the columns list with a number of BattleColumn instances based on SkyBattleGame.COLS.
     * The Knight is initialized with y=0 position, initial health (SkyBattleGame.INITIAL_HEALTH), and 
     * a knight symbol (SkyBattleGame.KNIGHT_SYMBOL).
     */
    public BattleManagerR() 
    {
        this.knight = new KnightR(0, SkyBattleGame.INITIAL_HEALTH, SkyBattleGame.KNIGHT_SYMBOL);
        this.columns = new ArrayList<BattleColumnR>();
        for (int i = 0; i < SkyBattleGameR.COLS; i++) 
        {
            columns.add(new BattleColumnR(false));
        }
    }

    /*
     * Takes the player’s input (direction) and adjusts the Knight's y position accordingly.
     * It checks for valid movement directions (up, down, stay still), ensures the knight does not go out of
     * bounds. If the move is valid, the shiftGrid method is called first to shift the grid to the left,
     * followed by the move method, which updates the knight's position and checks for collisions.
     * Shifting the grid before moving ensures accurate collision detection based on the knight's new position.
     * The method returns true if the move is valid and successful, or false if the move is invalid or out of bounds.
     */
    public boolean handleMovement(String direction) 
    {
        if(!direction.equals(SkyBattleGameR.MOVE_UP) && !direction.equals(SkyBattleGameR.MOVE_DOWN ) && !direction.equals(SkyBattleGameR.STAY_STILL))
        {
            System.out.println("Invalid direction");
            return false;
        }
        if(knight.getY() == 0 && direction.equals(SkyBattleGameR.MOVE_UP) ||knight.getY() == SkyBattleGameR.ROWS - 1 && direction.equals(SkyBattleGameR.MOVE_DOWN)) //out of bounds
        {
            System.out.println("The knight cannot go beyond the sky!");
            return false;
        }
        else
        {
            shiftGrid();
            if(direction.equals(SkyBattleGameR.MOVE_DOWN))
            {
                move(SkyBattleGameR.MOVE_DOWN_DIST);
            }
            else if(direction.equals(SkyBattleGameR.MOVE_UP))
            {
                move(SkyBattleGameR.MOVE_UP_DIST);
            }
            else
            {
                move(SkyBattleGameR.STAY_STILL_DIST);
            }
            return true;
        }
    }

    /*
     * Updates the Knight's y position by the provided yDist and checks the position for Dark 
     * Knights. If the Knight collides with a Dark Knight, its health is reduced by 1, and an 
     * attack message is displayed using displayAttackMessage(). Otherwise, the player's score 
     * (SkyBattleGame.score) increases by 1.
     */
    private void move(int yDist) 
    {
        int currentY = knight.getY();
        knight.setY(currentY + yDist);

        if(columns.get(0).getElements()[knight.getY()] == SkyBattleGameR.DARK_KNIGHT_SYMBOL)
        {
            if(knight.getInvisible() > 0)
            {
                knight.setInvisible(knight.getInvisible() - 1);
            }
            else
            {
                int currentHealth = knight.getHealth();
                knight.setHealth(currentHealth - 1);
                displayAttackMessage();
            }
            
        }
        else
        {
            if(columns.get(0).getElements()[knight.getY()] == SkyBattleGameR.SWORD_SYMBOL)
            {
                knight.setInvisible(knight.getInvisible() + SkyBattleGameR.INVISIBLE_SWORD);
            }
            SkyBattleGameR.score ++;
        }
    }

    /*
     * Shifts the grid by moving each column to the left and generating a new column at the 
     * rightmost position.
     */
    private void shiftGrid() 
    {
        columns.remove(0);
        columns.add(SkyBattleGameR.COLS - 1, new BattleColumnR(true));
    }

    /*
     * Displays a message to the player when the Knight is attacked by a Dark Knight.
     */
    private void displayAttackMessage() 
    {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n" + 
                           "You were attacked by a dark knight\n" + 
                           "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    // Getters
    
    /*
     * Returns the current instance of the knight.
     */
    public KnightR getKnight() 
    {
        return knight;
    }

    /*
     * Returns the ArrayList of columns representing the current game grid.
     */
    public ArrayList<BattleColumnR> getColumns() 
    {
        return columns;
    }
}