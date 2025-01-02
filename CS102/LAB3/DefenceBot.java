/**
 * It is Defence Bot class inherited from Robot class
 * 
 * @author ECE SESEN
 * @version 19.03.2024
 */

public class DefenceBot extends Robot
{
    //Determine the health, attack and speed of the robot instances by random variables within different ranges
    public DefenceBot()
    {
        super("D", 3, 6, 0.5, 1, 0.5, 1);
        setHealth(round3Decimal(random(getMinHealth(), getMaxHealth())));
        setAttack(round3Decimal(random(getMinAttack(), getMaxAttack())));
        setSpeed(round3Decimal(random(getMinSpeed(), getMaxSpeed())));
    }

    /**
     * Attacks the opposite team's robot with the lowest speed.
     */
    public void attack(Simulation s)
    {
        Robot target = s.getLowestSpeed(!this.getInRedTeam());
        if(target.getHitAndIsDestroyed(this.getAttack()))
        {
            s.removeRobot(target);
        }
        System.out.println(printResult(this, target));

    }
}