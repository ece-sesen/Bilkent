import java.awt.*;

/**
 * keep a Rectangle object in your Ship, Bomb, and Apple classes to represent their size and position
 * randomly span on the right edge of the screen and move toward the left
 * cover a square area
 */
public class Apple implements InteractableDrawing
{
    private final int APPLE_WIDTH = 25;
    private final int APPLE_HEIGHT = 25;
    private Rectangle appleTemp;

    private int x;
    private int y ;
    
    public Apple()
    {
        appleTemp = new Rectangle(x,y,APPLE_WIDTH,APPLE_HEIGHT);
        int n = (int)(Math.random() * 1389);         
        x = 1590;
        y = n;
    }

    /**
     * intersection calculations
     * override the method to add score to the ship (increase the player’s score by 1)
     * removed from the ArrayList
     * @param s
     * @return
     */
    public boolean intersects(Ship s)
    {
        return s.getShipTemp().intersects(appleTemp);
    }

    public void interact(Ship s)
    {
       s.incrementScore();
    }

    /**
     * should move the object towards the left edge of the screen with the specified speed amount.
     * You need to pass this speed information from your GameFrame, 
     * so GamePanel needs to keep a reference to the GameFrame
     * @param speed
     * @return
     */
    public boolean moveLeft(int speed)
    {
        boolean isOutRange = false;
        if(this.x - speed < 0)
        {
            isOutRange = true;
        }
        else
        {
            this.x = x - speed;
            appleTemp.setLocation(x,y);

        }

        return isOutRange;
    }

    /**
     * draw their graphics on screen.
     */
    public void draw(Graphics g)
    {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, APPLE_WIDTH, APPLE_HEIGHT);
        g.setColor(Color.RED);
        g.fillOval(x, y, APPLE_WIDTH, APPLE_HEIGHT);
    }
}
