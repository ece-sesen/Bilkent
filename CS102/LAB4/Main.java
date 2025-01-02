import javax.swing.*;
/**
 * This ia Main class.
 * It is a game. Created ship aims to collect apples to gain points and lose point when is encounter bombs.
 */
public class Main
{
    public static void main(String [] args)
    {
        JFrame menuFrame = new MenuFrame();
        menuFrame.setTitle("Ship Game");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setVisible(true);
    }
}
