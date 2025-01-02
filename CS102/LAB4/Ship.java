import java.awt.*;

import javax.swing.JOptionPane;

/**
 * keep a Rectangle object in your Ship, Bomb, and Apple classes to represent their size and position
 * add methods to support this functionality (slife of the ship)
 */

public class Ship 
{
    private final int SHIP_WIDTH = 50;
    private final int SHIP_HEIGHT = 30;
    private Rectangle shipTemp;
    private int x;
    private int y;
    private String shipName;
    private int score;
    private int currentLife;
    private GameFrame game;

    public Ship( String name, int x, int y, GameFrame game)
    {
        shipTemp = new Rectangle(x, y, SHIP_WIDTH, SHIP_HEIGHT);
        shipName = name;
        this.game = game;
        currentLife = 3;
        score = 0;
    }    

    /**
     * draw their graphics on screen.
     * @param g
     */
    public void draw(Graphics g)
    {
        g.setColor((Color.YELLOW));
        g.fillRect(this.getX(), this.getY(), SHIP_WIDTH, SHIP_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawString(shipName, this.getX() + 5 , this.getY() + SHIP_HEIGHT - 10);
    }

    public int getLife()
    {
        return currentLife;
    }
    public void decreaseLife()
    {
        currentLife--;
        if(currentLife == 0)
            {  
                int reply = JOptionPane.showConfirmDialog(null, "Score: " + score + ", do you want to play again?", "Select an option", JOptionPane.YES_NO_CANCEL_OPTION);
                if (reply == JOptionPane.YES_OPTION) 
                {
                    new GameFrame(shipName, game.getSpeed());
                    game.dispose();
                } 

                else if(reply == JOptionPane.NO_OPTION)
                {
                    System.exit(0);
                }    
            }
    }
    
    public int getScore()
    {
        return score;
    }
    public void incrementScore()
    {
        score++;
    }

    public void setCoordinates(int newX, int newY)
    {
        x = newX;
        y = newY;
        shipTemp.setLocation(x - (SHIP_WIDTH / 2),y - (SHIP_HEIGHT / 2));
    }

    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getHeight()
    {
        return SHIP_HEIGHT;
    }
    public int getWidth()
    {
        return SHIP_WIDTH;
    }
    
    public Rectangle getShipTemp()
    {
        return shipTemp;
    }
}
