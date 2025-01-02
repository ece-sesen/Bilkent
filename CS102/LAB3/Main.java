/**
 * This program is a console game. 
 * It prints the battle between robots from ecah team, red and blue.
 * Robots differ frommeach other accoring to their name, range of health, attack, speed.
 * Different robots have  different attack strategies.
 * 
 * @author ECE SESEN
 * @version 19.03.2024
 */

import java.util.Scanner;
public class Main 
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner (System.in);
        System.out.print("Team Size: ");
        int size = in.nextInt();
        in.close();

        Simulation game = new Simulation();
        game.initialize(size);
        game.simulate();
    }
}
