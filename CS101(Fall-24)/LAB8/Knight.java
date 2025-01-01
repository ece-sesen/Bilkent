public class Knight 
{
    // Instance Variables
    private int y = 0; // y-coordinate of the Knight
    private int health = 0; // current health points of the Knight
    private char symbol; // visual symbol of the Knight

    /*
     * Initializes the Knight with a specified y position, health, and a visual symbol. It sets the 
     * health using the setHealth() method to ensure the value is non-negative.
     */
    public Knight(int y, int health, char symbol) 
    {
        this.y = y;
        setHealth(health);
        this.symbol = symbol;
    }

    /*
     * Returns true if the Knight’s health is greater than zero, meaning the Knight is still alive; 
     * otherwise, returns false.
     */
    public boolean isAlive() 
    {
        return health > 0;
    }

    /*
     * Returns a string representation of the Knight, including its symbol and a visual representation
     * of its health using the ‘*’ symbol. The number of asterisks corresponds to the Knight's health.
     */
    public String toString() 
    {
        String knightInfo = "Knight: symbol(" + symbol + "), health (";
        for(int i = 0; i < health; i++)
        {
            knightInfo += "*";
        }
        knightInfo += ")";
        return knightInfo;
    }

    // Getters & Setters
    public int getHealth() 
    {
        return health;
    }

    public int getY() 
    {
        return y;
    }

    public char getSymbol() 
    {
        return symbol;
    }

    public void setY(int y) 
    {
        this.y = y;
    }

    /*
     * Sets the Knight’s health to the provided value, but ensures the health is never set to a value 
     * below 0 using Math.max(health, 0)
     */
    public void setHealth(int health) 
    {
        this.health = Math.max(health, 0);
    }
}