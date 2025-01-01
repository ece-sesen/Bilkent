/**
 * This class calculates dry and water areas considering Earth or Turkey.
 * 
 * @author Ece SESEN
 * @version 24.09.2024
 */
public class Lab01_Q3
{
    public static void main(String[] args)
    {
        // Constants
        final long TOTAL_AREA_EARTH = 510072000;
        final double  portionOfEarthToTurkey = 6510.42;
        final double TOTAL_TURKEY = TOTAL_AREA_EARTH / portionOfEarthToTurkey;
       
        // Variables
        double perOfDryEarth = 29.2;
        int portionOfDryTurkey = 76;

        //Calculations
        double dryEarth = TOTAL_AREA_EARTH * perOfDryEarth / 100;
        double waterEarth = TOTAL_AREA_EARTH - dryEarth;
        double waterTurkey = TOTAL_TURKEY / (portionOfDryTurkey + 1);
        double dryTurkey = TOTAL_TURKEY - waterTurkey;
        double dryTurkeyOverEartPercent = dryTurkey / dryEarth * 100;
        double waterOutsideTurkeypercent = (waterEarth - waterTurkey) / waterEarth * 100;

        // Results are printed out
        System.out.println("Turkey has " + (int)dryTurkey + " km2 dry land and " + (int)waterTurkey + " km2 water.");
        System.out.printf("%s%9.0f%s%9.0f%s%n", "Earth has ", dryEarth, " km2 dry land and ",  (TOTAL_AREA_EARTH - dryEarth), " km2 water.");
        System.out.println("Turkey has " + dryTurkeyOverEartPercent + " percent of the Earth's dry land." );
        System.out.printf("%s%2.16f%s%n", "Turkey has ", waterOutsideTurkeypercent, " percent of the Earth's water.");
    }
}