/**
 * It is a simulation class.
 * It creates the dyanmic of the game.
 * First it builds two teams and then match them according to their types and battle starts.
 * 
 * @author ECE SESEN
 * @version 19.03.2024
 */

import java.util.ArrayList;
public class Simulation
{
    //Instance variables
    private boolean redStart;
    private String winnerColor;
    private ArrayList<Robot> redTeam;
    private ArrayList<Robot> blueTeam;
    public static int redIndex;
    private static  int blueIndex;
    
    //Constructor
    public Simulation()
    {
        winnerColor = "";
        redTeam = null;
        blueTeam = null;
        redIndex = 0;
        blueIndex = 0;
        redStart = true;
    }
    
    /**
     * Create two teams of robots of the given size. Robot types will be determined randomly.
     * Robots are sorted according to increasing speed.
     * @param teamSize size of team
     */
    void initialize(int teamSize) 
    {
        final int ROBOT_TYPE = 6;
        redTeam = new ArrayList<Robot>();
        blueTeam = new ArrayList<Robot>();
        for(int i = 0; i < teamSize * 2; i++)
        {
            int random = (int)(Math.random() * ROBOT_TYPE);
            Robot robot = null;

            if( random == 0) {robot = new SimpleBot();}
            else if(random == 1){robot = new PredatorBot();}
            else if(random == 2){robot = new DefenceBot();}
            else if(random == 3){robot = new SpeedBot();}
            else if(random == 4){robot = new SpreadBot();}
            else if(random == 5){robot = new OneBot();}

            if(i < teamSize)
            {   
                redTeam.add(robot);
                robot.setTeam(redTeam);
                robot.setRedTeam(true);
            }
            else
            {
                blueTeam.add(robot);
                robot.setTeam(blueTeam);
                robot.setRedTeam(false);
            }
        }
        sortSpeed(redTeam);
        sortSpeed(blueTeam);

        //Print teams' content
        System.out.println("\nRed Team: ");
        for(int i = 0; i < redTeam.size(); i++)
        {
            Robot each = redTeam.get(i);
            System.out.println(robotInfo(each));
        }

        System.out.println("\nBlue Team: ");
        for(int i = 0; i < blueTeam.size(); i++)
        {
            Robot each = blueTeam.get(i);
            System.out.println(each.getName() + " Heath: " + each.getHealth() + " Attack: " + each.getAttack() + " Speed: " + each.getSpeed());
        }

        System.out.println("\nSpeed sum of Red: " + speedSum(redTeam));
        System.out.println("Speed sum of Blue: " + speedSum(blueTeam));
        
    }

    /**
     * Gives health attack and speed of specific robot
     * @param robot robot instance
     * @return all information
     */
    public String robotInfo(Robot robot)
    {
        String info = robot.getName() + " Heath: " + robot.getHealth() + " Attack: " + robot.getAttack() + " Speed: " + robot.getSpeed();
        return info;
    }

    /**
     * Sort given team's robots according to increasing speed
     * @param team red or blue
     */
    private void sortSpeed(ArrayList<Robot> team)
    {
        int size = team.size();

        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size - i - 1; j++)
            {
                if(team.get(j).getSpeed() < team.get(j + 1).getSpeed()) 
                {
                    Robot slow = team.get(j);
                    Robot fast = team.get(j + 1);
                    team.remove(j);
                    team.add(j, fast);
                    team.remove(j + 1);
                    team.add(j + 1, slow);

                }
            }        
        }
    }

    /**
     * Simulate the battle between two teams which robots take turn to perform attack
     * First,it determines starting team, which has more sum of speed. If sum is equal, red team starts.
     * If robot is destroyed, remove it from the team.
     * Prints meaningful message about "what is going on" during game.
     * Game continues as long as both teams have at least one robot.
     */
    void simulate()
    {
        boolean isFirstRound = true;
        boolean firstRed = isRedStart();
        while(redTeam.size() != 0 && blueTeam.size() != 0)
        {
            blueIndex %= blueTeam.size();
            redIndex %= redTeam.size();

            if(isFirstRound)
            {
                if(firstRed)
                {
                    System.out.println("\nRed starts first.\n");
                    redTeam.get(redIndex).attack(this);
                    redIndex++;
                }
                else
                {
                    System.out.println("\nBlue starts first.\n");
                }
                isFirstRound = false;
            }

            if(isRobot(blueTeam)){blueTeam.get(blueIndex).attack(this);}
            if(isRobot(redTeam)){redTeam.get(redIndex).attack(this);}

            redIndex++;
            blueIndex++;
        }
        
        ArrayList<Robot> winner = findWinner();
        System.out.println("\n" + winnerColor + " team wins, remaining robots: ");
        for(int i = 0; i < winner.size(); i++)
        {
            Robot robot = winner.get(i);
            System.out.println(robotInfo(robot));
        }
    }

    /**
     * Check whether the team has robots in it or not
     * @param team red or blue
     * @return true if team has at least one player otherwise false
     */
    public boolean isRobot(ArrayList<Robot> team)
    {
        return team.size() != 0;
    }

    /**
     * Find the team which won the game
     * @return winner team
     */
    public ArrayList<Robot> findWinner()
    {
        ArrayList<Robot> winner = null;
        if(redTeam.size() != 0)
        {
            winner = redTeam;
            winnerColor = "Red";

        }
        else
        {
            winner = blueTeam;
            winnerColor = "Blue";
        }
        return winner;
    }

    /**
     * Compare sum of speeds of each team and detemine who will start.
     * @return true is red team starts
     */
    private boolean isRedStart()
    {
        if(speedSum(redTeam) < speedSum(blueTeam))
        {
            redStart = false;
        }
        return redStart;
    }

    /**
     * If the isRedTeam parameter is true the method should choose the random robot from the red team, 
     * otherwise from the blue team.
     * @param isRedTeam the team of the target robot
     * @return a random Robot instance from the red or blue team.
     */
    public Robot getRandomTarget(boolean isRedTeam)
    {
        ArrayList<Robot> team = findTeam(isRedTeam);
        int random = (int)Math.random() * team.size();
        return team.get(random);
    }

    /**
     * According to boolean parameter, team will be found.
     * @param isRedTeam the team of the target robot
     * @return the team
     */
    public ArrayList<Robot> findTeam (boolean isRedTeam)
    {
        ArrayList<Robot> team = null;
        if(isRedTeam)
        {
            team = redTeam;
        }
        else
        {
            team = blueTeam;
        }
        return team;
    }

    /**
     * Returns the robot with the highest health from the desired team.
     * @param isRedTeam the team of the target robot
     * @return healtiest robot
     */
    public Robot getHighestHealth(boolean isRedTeam)
    {
        ArrayList<Robot> team = findTeam(isRedTeam);
        double maxHealth = team.get(0).getHealth();
        int index = 0;
        for(int i = 1; i < team.size(); i++)
        {
            if(team.get(i).getHealth() > maxHealth)
            {
                maxHealth = team.get(i).getHealth();
                index = i;
            }
        }
        return team.get(index);
    }

    /**
     * Returns the robot with the lowest health from the desired team.
     * @param isRedTeam the team of the target robot
     * @return most unhealthy robot
     */
    public Robot getLowestHealth(boolean isRedTeam)
    {
        ArrayList<Robot> team = findTeam(isRedTeam);
        double minHealth = team.get(0).getHealth();
        int index = 0;
        for(int i = 1; i < team.size(); i++)
        {
            if(team.get(i).getHealth() < minHealth)
            {
                minHealth = team.get(i).getHealth();
                index = i;
            }
        }
        return team.get(index);
    }

    /**
     * Returns the robot with the lowest speed from the desired team.
     * @param isRedTeam the team of the target robot
     * @return slowest robot
     */
    public Robot getLowestSpeed(boolean isRedTeam)
    {
        ArrayList<Robot> team = findTeam(isRedTeam);
        int size = team.size();
        return team.get(size - 1);

    }

    /**
     * Returns the robot with the lowest attack from the desired team.
     * @param isRedTeam the team of the target robot
     * @return robot with lowest attack
     */
    public Robot getLowestAttack(boolean isRedTeam)
    {
        ArrayList<Robot> team = findTeam(isRedTeam);
        int index = 0;
        double minAttack = team.get(0).getAttack();
        for(int i = 0; i < team.size(); i++)
        {
            if(team.get(i).getAttack() < minAttack)
            {
                index = i;
                minAttack = team.get(i).getAttack();
            }
        }
        return team.get(index);
    }

    /**
     * Returns the Robot array containing the desired team's three robots with the lowest speed values. 
     * This method can return 1, 2, or 3 Robot instances based on the remaining robots of the desired team.
     * @param isRedTeam robot's team
     * @return desired robot with lowest speed
     */
    public Robot[] getLowestSpeed3 (boolean isRedTeam)
    {
        ArrayList<Robot> team = findTeam(isRedTeam);
        int size = team.size();
        Robot[] robotsSlowest = null;
        if(size >= 3)
        {
            robotsSlowest = new Robot[3];
            robotsSlowest[0] = team.get(size- 1);
            robotsSlowest[1] = team.get(size- 2);
            robotsSlowest[2] = team.get(size- 3);

        }
        else
        {
            robotsSlowest = new Robot[team.size()];
            for(int i = 0; i < team.size(); i++)
            {
                robotsSlowest[i] = team.get(i);
            }
        }
        return robotsSlowest;
    }

    /**
     * Calcualte the sum of speed of each robot
     * @param team red or blue
     * @return sum
     */
    public double speedSum(ArrayList<Robot> team)
    {
        double sum = 0;
        for(int i = 0; i< team.size(); i++)
        {
            sum += team.get(i).getSpeed();
        }
        return Robot.round3Decimal(sum);
    }

    /**
     * Remove the destroyed robots 
     * @param r destroyed robot
     */
    public  void removeRobot(Robot r)                                                 
    {
        ArrayList<Robot> team = findTeam(r.getInRedTeam());
        if(team.equals(redTeam) && findIndexOfRobot(r) < redIndex) 
        { 
            redIndex--;
        }
        else if (!team.equals(redTeam) && findIndexOfRobot(r) < blueIndex)
        { 
            blueIndex--;
        }
        team.remove(r);
    }

    /**
     * Fnf the index of robot in its team
     * @param robot specific robot
     * @return index of given robot in team
     */
    public int findIndexOfRobot(Robot robot)
    {
        boolean found = false;
        int index = 0;
        for(int i = 0; !found && i < robot.getTeam().size(); i++)
        {
            if(robot.getTeam().get(i).equals(robot))
            {
                found = true;
                index = i;
            }
        }
        return index;
    }
}

