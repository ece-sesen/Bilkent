/**
 * It is Predator Bot class inherited from Robot class
 * 
 * @author ECE SESEN
 * @version 19.03.2024
 */
public class PredatorBot extends Robot
{
    //Determine the health, attack and speed of the robot instances by random variables within different ranges
    public PredatorBot()
    {
        super("P", 2, 3, 2, 3, 0.5, 1);
        setHealth(round3Decimal(random(getMinHealth(), getMaxHealth())));
        setAttack(round3Decimal(random(getMinAttack(), getMaxAttack())));
        setSpeed(round3Decimal(random(getMinSpeed(), getMaxSpeed())));
    }

    /**
     * Attacks the opposite team's robot with the highest health.
     */
    public void attack(Simulation s)
    {
        Robot target = s.getHighestHealth(!this.getInRedTeam());
        if(target.getHitAndIsDestroyed(this.getAttack()))
        {
            s.removeRobot(target);
        }
        System.out.println(printResult(this, target));
    }
}

