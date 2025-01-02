import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * use the return value of moveLeft method in your GamePanel class 
 * to decide which objects to keep and which ones to remove
 * should keep the ship and an ArrayList of InteractableDrawing objects as class members
 */
public class GamePanel extends JComponent
{
    private GameFrame gameFrame;
    private Timer t;
    private TimerListener listener;
    private MouseMotionListener l;
    private Ship ship;
    private ArrayList<InteractableDrawing> appleAndBomb;

    public GamePanel(GameFrame frame)
    {
        gameFrame = frame;
        l = new MyMouse();
        ship = new Ship(gameFrame.getPlayerName(), 100, 400,  gameFrame);
        this.addMouseMotionListener(l);
        appleAndBomb = new ArrayList<InteractableDrawing>();

        listener = new TimerListener();
        t = new Timer(100, listener); 
        t.start();

    }

    class MyMouse implements MouseMotionListener
    {
        /**
         * a ship centered on our mouse location
         * override the mouseMoved method to update the ship’s position
         * repaint the panel  
         * check for intersections when you move the ship
         */
        public void mouseMoved(MouseEvent e)                            
        {
            int x = e.getX() - ship.getWidth() / 2;
            int y = e.getY() - ship.getHeight() / 2;
            ship.setCoordinates(x, y);
            repaint();
        }

        public void mouseDragged(MouseEvent e){}
    }

    class TimerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            moveAll();
            gameFrame.setTitle("Life: " + ship.getLife() + " - Score: " + ship.getScore());
            createAppleOrBomb();
            repaint();
            
            
        }
    }

    

    public void moveAll()
    {
        for(int i = 0; i < appleAndBomb.size(); i++)
        {
            boolean cantMove = appleAndBomb.get(i).moveLeft(gameFrame.getSpeed());
            if(!cantMove)
            {
                boolean isIntersect = appleAndBomb.get(i).intersects(ship);
                if(isIntersect)
                {
                    appleAndBomb.get(i).interact(ship);
                    appleAndBomb.remove(i);
                    //i--;
                }
            }
            else
            {
                appleAndBomb.remove(i);
                //i--;
            }
            
        }
    }

    public void createAppleOrBomb()
    {
        int i = (int)(Math.random() * 2);
        if(i == 0)
        {
            Apple newApple = new Apple();
            appleAndBomb.add(newApple);
        }
        else
        {
            Bomb newBomb = new Bomb();
            appleAndBomb.add(newBomb);
        }
    }

    /**
     * to draw a blue sea background and 
     * draw the ship, bombs, and apples by calling their draw(Graphics g) methods
     */
    public void paintComponent(Graphics g)
    {
        g.setColor(Color.BLUE);
        g.fillRect(0,0,gameFrame.getGameWidth(),gameFrame.getGameHeight());
        ship.draw(g);
        
        for(int i = 0; i < appleAndBomb.size(); i++)
        {
            appleAndBomb.get(i).draw(g);
        }
    }
}
