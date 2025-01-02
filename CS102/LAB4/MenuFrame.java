import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This is 
 */
public class MenuFrame extends JFrame
{
    JPanel menu;
    JPanel namePanel;
    JPanel speedPanel;
    JTextField enterName;
    JTextField enterSpeed;
    
    public MenuFrame()
    {
        menu = new JPanel();
        menu.setLayout(new GridLayout(3,1));
        menu.add(createNamePanel());
        menu.add(createSpeedPanel());
        menu.add(createButton());
        add(menu);
        setSize(300,200);

    }

    /**
     * Show user what to enter (name)
     * @return namePanel
     */
    private JPanel createNamePanel()
    {
        namePanel = new JPanel();
        namePanel.setLayout(new GridLayout(1,2));
        JLabel name = new JLabel("            Name:");
        enterName = new JTextField(10);
        namePanel.add(name);
        namePanel.add(enterName);
        return namePanel;

    }

    /**
     * Show user what to enter (speed)
     * @return speedPanel
     */
    private JPanel createSpeedPanel()
    {
        speedPanel = new JPanel();
        speedPanel.setLayout(new GridLayout(1,2));
        JLabel speed = new JLabel("            Speed:");
        enterSpeed = new JTextField(10);
        speedPanel.add(speed);
        speedPanel.add(enterSpeed);
        return speedPanel;
    }

    /**
     * Make button
     * @return start button
     */
    private JButton createButton()
    {
        JButton start = new JButton("Start");
        ActionListener listener = new ClickListener();
        start.addActionListener(listener);
        return start;
    }

    class ClickListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if(enterName.getText().isEmpty() || enterSpeed.getText().isEmpty() || !isNumeric(enterSpeed.getText())) 
            {
                if(enterName.getText().isEmpty() || enterSpeed.getText().isEmpty())
                {JOptionPane.showMessageDialog(null, "Name or speed cannot be empty!");}
                else
                {JOptionPane.showMessageDialog(null, "Speed should be only numbers!");}
            }
            else
            {
                getMenuFrame().dispose();
                JFrame gameFrame = new GameFrame( enterName.getText(), Integer.parseInt(enterSpeed.getText()));
                gameFrame.setTitle("Game Console");
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setVisible(true);

            }
        }
    }

    /**
     * Gives menu frame
     * @return menu frame
     */
    public JFrame getMenuFrame()
    {
        return this;
    }
    
    /**
     * Check whether input is string or number
     * @param input given string
     * @return true is input is number otherwise false
     */
    public boolean isNumeric(String input)
    {
        boolean isNumneric = true;
        boolean found = false;
        for(int i = 0; !found && i < input.length(); i++)
        {
            char ch = input.charAt(i);
            if(!Character.isDigit(ch))
            {
                isNumneric = false;
                found = true;
            }
        }
        return isNumneric;
    }
}