/**
 * It is Speed Bot class inherited from Robot class
 * 
 * @author ECE SESEN
 * @version 19.03.2024
 */
public class SpeedBot extends Robot
{
    //Determine the health, attack and speed of the robot instances by random variables within different ranges
    public SpeedBot()
    {
        super("X", 1, 2, 1, 2, 3, 4);
        setHealth(round3Decimal(random(getMinHealth(), getMaxHealth())));
        setAttack(round3Decimal(random(getMinAttack(), getMaxAttack())));
        setSpeed(round3Decimal(random(getMinSpeed(), getMaxSpeed())));
    }

    /**
     * Attacks the opposite team's robot with the lowest attack.
     */
    public void attack(Simulation s)
    {
        Robot target = s.getLowestAttack(!this.getInRedTeam());
        if(target.getHitAndIsDestroyed(this.getAttack()))
        {
            s.removeRobot(target);
        }
        System.out.println(printResult(this, target));
    }
}
