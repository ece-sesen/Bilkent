/**
 * It is Spread Bot class inherited from Robot class
 * 
 * @author ECE SESEN
 * @version 19.03.2024
 */
public class SpreadBot extends Robot
{
    //determine the health, attack and speed of the robot instances by random variables within different ranges
    public SpreadBot()
    {
        super("K", 2, 3, 0.5, 1, 0.5, 1.5);
        setHealth(round3Decimal(random(getMinHealth(), getMaxHealth())));
        setAttack(round3Decimal(random(getMinAttack(), getMaxAttack())));
        setSpeed(round3Decimal(random(getMinSpeed(), getMaxSpeed())));
    }

    /**
     * Attacks the slowest three robots in the opposite team. 
     * If the opposite team has less than three robots, it attacks them all.
     */
    public void attack(Simulation s)
    {
        Robot[] robots = s.getLowestSpeed3(!this. getInRedTeam());
        System.out.println(this.getName() + " attacks following targets: ");
        for(int i = 0; i < robots.length; i++)
        {
            System.out.print( robots[i].getName() + " "); 
            if(robots[i].getHitAndIsDestroyed(this.getAttack()))
            {
                s.removeRobot(robots[i]);
            }
        }
        System.out.println();
        System.out.print(printResult(this, robots));
        
    }
}
