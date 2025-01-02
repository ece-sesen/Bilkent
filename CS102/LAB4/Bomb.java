import java.awt.*;

/**
 * keep a Rectangle object in your Ship, Bomb, and Apple classes to represent their size and position
 * randomly span on the right edge of the screen and move toward the left
 * cover a square area
 */
public class Bomb implements InteractableDrawing
{
    private final int BOMB_WIDTH = 25;
    private final int BOMB_HEIGHT = 25;

    private int x;
    private int y ;
    private Rectangle bombTemp;


    public Bomb()
    {
        bombTemp = new Rectangle(x, y, BOMB_WIDTH, BOMB_HEIGHT);
        int n = (int)(Math.random() * 1389);
        x = 1590;
        y = n;
    }

    /**
     * intersection calculations
     * override the method decrease the life of the ship (reduce the player’s life by 1)
     * removed from the ArrayList
     * 
     * @param s
     * @return
     */
    public boolean intersects(Ship s)
    {
        return s.getShipTemp().intersects(bombTemp);
    }

    public void interact(Ship s)                
    {
       s.decreaseLife();
    }

    /**
     * should move the object towards the left edge of the screen with the specified speed amount.
     * @param speed
     * @return true if the object is off limits; 
     * in other words, if its right side coordinate is less than zero. Otherwise, it will return false.
     */
    public boolean moveLeft(int speed)          
    {
        boolean isOut = false;
        if(this.x - speed < 0)
        {
            isOut = true;
        }
        else
        {
            this.x = x - speed;
            bombTemp.setLocation(x,y);
        }
        return isOut;
    }

    /**
     * draw their graphics on screen.
     * @param g
     */
    public void draw(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(x, y, BOMB_WIDTH, BOMB_HEIGHT);
        g.setColor(Color.BLACK);
        g.fillOval(x, y, BOMB_WIDTH, BOMB_HEIGHT);
    }
}