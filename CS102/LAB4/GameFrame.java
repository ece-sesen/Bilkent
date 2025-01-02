import javax.swing.*;

/**
 * This is a GameFrame class
 * Game will be executed here
 */
public class GameFrame extends JFrame
{
    private final int GAME_FRAME_WIDTH = 1300;
    private final int GAME_FRAME_HEIGHT = 1000;
    private String name;
    private int speed;

    private JComponent gamePanel;

    public GameFrame(String name, int speed)
    {
        this.name = name;
        this.speed = speed;
        gamePanel = createGamePanel();
        add(gamePanel);
        setSize(GAME_FRAME_WIDTH,GAME_FRAME_HEIGHT);
        setTitle("Game Console");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * 
     * @return gamePanel
     */
    public JComponent createGamePanel()
    {
        JComponent game = new GamePanel(this);
        return game;
    }

    public String getPlayerName()
    {
        return name;
    }

    public int getGameWidth()
    {
        return GAME_FRAME_WIDTH;
    }

    public int getGameHeight()
    {
        return GAME_FRAME_HEIGHT;
    }
    
    public int getSpeed()
    {
        return speed;
    }
    

}