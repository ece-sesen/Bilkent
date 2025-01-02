/**
 * This is abstract Robot class
 * Some methods are already impelmented but one of them is abstract which will be implemented by subclasses of robot
 * 
 * @author ECE SESEN
 * @version 19.03.2024
 */

import java.util.ArrayList;
public abstract class Robot
{
    // Robot's health, attack, and speed as double variables, a string name, and a boolean isRedTeam to specify the team of the robot.
    protected static int productionNumber = 0;
    private double minHealth;
    private double maxHealth;
    private double minAttack;
    private double maxAttack;
    private double minSpeed;
    private double maxSpeed;
    private double health;
    private double attack;
    private double speed;
    private String name;
    private boolean isRedTeam;
    private ArrayList<Robot> team;
    private String teamColor;
    
    public Robot( String name, double minHealth, double maxHealth, double minAttack, double maxAttack, double minSpeed, double maxSpeed )
    {
        productionNumber++;
        this.name = name + productionNumber;
        this.minHealth = minHealth;
        this.maxHealth = maxHealth;
        this.minAttack = minAttack;
        this.maxAttack = maxAttack;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
    }

    /**
     * handle the robot’s attack and needs access to the Simulation class to choose a target opponent
     * Different robots prefer different targets
     * subclasses of Robot will override
     * If the removed robot was going to attack, the order continues with the next robot
     * @param s
     */
    abstract void attack(Simulation s); 

    /**
     * handle receiving damage for the robot
     * subtract the given damage from the robot's health; if the health becomes zero or less, the robot is destroyed
     * Return true if the robot is destroyed after receiving the damage and remove from its team; 
     * return false otherwise
     * the attacking robot’s attack value is subtracted from the target’s health value
     * @param damage amount of decrease inn robot's health
     */
    boolean getHitAndIsDestroyed(double damage)
    {
        ArrayList<Robot> team = this.getTeam(); 
        double updatedHealth = round3Decimal(this.getHealth() - damage);
        boolean isDestroyed;
        if(updatedHealth <= 0)
        {
            this.setHealth(0);
            team.remove(this);   //Önden yok oluyosa index'i 1 azaltmak lazım yoksa robotu saymadan pas geçiyor.
            isDestroyed = true;
        }
        else
        {
            this.setHealth(updatedHealth);
            isDestroyed = false;
        }
        return isDestroyed;
    }
    
    

    public double random(double min, double max)
    {
        double random = (Math.random() * (max - min)) + min;
        return random;
    }
    
    public static double round3Decimal(double number)
    {
        int rounded = 0;
        number =(number * 10000) + 5;
        rounded = (int)(number / 10);
        return rounded / 1000.0;
    }

    public String printResult(Robot attacker, Robot victem)
    {
        String result =  attacker.getName() + " attacks " + victem.getName() +
                        "\n" + victem.getName() + " receives " + attacker.getAttack() + " damage -> remaining health: " + victem.getHealth();
        if(victem.getHealth() <= 0)
        {
            result += "\n" + victem.getName() + " destroyed.\n";
        }
        
        return result;
    }
    
    public String printResult(Robot attacker, Robot[] victems)
    {
        String result = "";
        for(int i = 0; i < victems.length; i++)
        {
            result +=  victems[i].getName() + " receives " + attacker.getAttack() + " damage -> remaining health: " + victems[i].getHealth() + "\n";
            if(victems[i].getHealth() <= 0)
            {
                result += victems[i].getName() + " destroyed.";
            }
        }
        return result;
    }


    //Setter methods
    public void setHealth(double x)
    {
        this.health = x;
    }
    public void setAttack(double x)
    {
        this.attack = x;
    }
    public void setSpeed(double x)
    {
        this.speed = x;
    }
    public void setRedTeam(boolean isRed)
    {
        this.isRedTeam = isRed;
    }
    public void setTeam(ArrayList<Robot> team)
    {
        this.team = team;
    }

    //Getter methods
    public double getHealth()
    {
        return health;
    }
    public double getAttack()
    {
        return attack;
    }
    public double getSpeed()
    {
        return this.speed;
    }
    public String getName()
    {
        return name;
    }
    public boolean getInRedTeam()
    {
        return isRedTeam;
    }
    public double getMinHealth()
    {
        return minHealth;
    }
    public double getMaxHealth()
    {
        return maxHealth;
    }
    public double getMinAttack()
    {
        return minAttack;
    }
    public double getMaxAttack()
    {
        return maxAttack;
    }
    public double getMinSpeed()
    {
        return minSpeed;
    }
    public double getMaxSpeed()
    {
        return maxSpeed;
    }
    public ArrayList<Robot> getTeam()
    {
        return this.team;
    }
    public String getTeamColor()
    {
        return this.teamColor;
    }
}
