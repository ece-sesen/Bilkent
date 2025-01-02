/**
 * It is Simple Bot class inherited from Robot class
 * 
 * @author ECE SESEN
 * @version 19.03.2024
 */
public class SimpleBot extends Robot
{
    //Determine the health, attack and speed of the robot instances by random variables within different ranges
    public SimpleBot()
    {
        super("S", 2, 3, 1, 2, 1, 2);
        setHealth(round3Decimal(random(getMinHealth(), getMaxHealth())));
        setAttack(round3Decimal(random(getMinAttack(), getMaxAttack())));
        setSpeed(round3Decimal(random(getMinSpeed(), getMaxSpeed())));

    }

    /**
     * Attacks a random robot on the opposite team.
     */
    public void attack(Simulation s)
    {
        Robot target = s.getRandomTarget(!this.getInRedTeam());
        if(target.getHitAndIsDestroyed(this.getAttack()))
        {
            s.removeRobot(target);
        }
        System.out.println(printResult(this, target));
    }
}