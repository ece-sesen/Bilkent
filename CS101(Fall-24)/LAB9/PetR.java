/**
 * This is Pet Class
 * Pet name, type, owner, health, happiness, current mood are stored in this class.
 * User can feed, groom their pet and play with them.
 * Each interaction with owner makes pet older.
 * Print the properties of pet containing name, type, health...
 * 
 * @author Ece ŞEŞEN
 * @version 10.12.2024
 */
public class PetR 
{
    //Class variables
    private static final int  CAT_OLD_AGE = 10;
    private static final int  DOG_OLD_AGE = 8;
    private static final int  RABBIT_OLD_AGE = 6;
    private static final int  MAX_HEALTH = 100;
    private static final int  MAX_HAPPINESS = 100;

    //Instance variables
    private String name;
    private String type;
    private int health;
    private int happiness;
    private String curMood;
    private UserR owner;
    private double age;
    private boolean isOld;

    //Constructor
    public PetR(String aName, String aType, UserR aOwner)
    {
        this.name = aName;
        this.type = aType;
        this.health = giveRandom(30, 50);
        this.happiness = giveRandom(40, 60);
        adjustMood();
        owner = aOwner;
        this.age = 0;
        this.isOld = findOld(type);
    }

    /**
     * Feed pet, update heath and happiness variable and check to not exceed the max health level
     */
    public void feedPet()
    {
        
        if(health + 10 <= MAX_HEALTH)
        {
            health += 10;
        }
        else
        {
            System.out.println("Health exceeds its max!");
        }

        if(happiness + 5 <= MAX_HAPPINESS)
        {
            happiness += 5;
        }

        
        adjustMood();
        userInteracted();
    }

    /**
     * Play with pet and check to not exceed max happiness level
     */
    public void playWithPet()
    {
        if(happiness + 15 <= MAX_HAPPINESS)
        {
            happiness += 15;
        }
        else
        {
            System.out.println("Happiness exceeds its max!");
        }
        adjustMood();
        userInteracted();

    }

    /**
     * Groom pet and check max health limit.
     */
    public void groomPet()
    {
        if(health + 15 <= MAX_HEALTH)
        {
            health += 15;
        }
        else
        {
            System.out.println("Health exceeds its max!");
        }
        userInteracted();
    }

    /**
     * Decide mood depending on happines level
     */
    private void adjustMood()
    {
        if(happiness > 70)
        {
            curMood = "Happy";
        }
        else if(happiness > 51)
        {
            curMood = "Playful";
        }
        else
        {
            curMood = "Sad";
        }
    }

    /**
     * Detect the variables of name, type, health, happiness and current mood
     * @return variables
     */
    public String getPetStatus()
    {
        return "Name: " + name + ", Type:  " + type + ", Health " + health + ", Happiness: " + happiness + ", Mood: " + curMood;
    }

    /**
     * Print the properties
     */
    public String toString() //Override
    {
        return getPetStatus();
    }

    /**
     * Make pet older when it interacted with its owner
     */
    private void userInteracted()
    {
        interactWithOthers();
        age += 0.25;
    }

    /**
     * Determine whether pet is old or not depending on its age and constraints.
     * @param type pet type
     * @return true if it is old, otherwise false
     */
    private boolean findOld(String type)
    {
        if(type.equals("Cat"))
        {
            return age <= CAT_OLD_AGE;
        }
        else if(type.equals("Dog"))
        {
            
            return age <= DOG_OLD_AGE;
        } 
        else if(type.equals("Rabbit"))
        {
            return age <= RABBIT_OLD_AGE;
        }
        else
        {
            return false;
        }
    }

    /**
     * Assign random number between given interval
     * @param start min amount
     * @param end max amount
     * @return
     */
    private int giveRandom(int start, int end)
    {
        return (int)(Math.random() * (end- start + 1)) + start;
    }

    /**
     * Pet's healt affect other pet's happiness
     */
    private void interactWithOthers()
    {
        int amount = 0;
        if(this.health > 70)
        {
            amount = 5;
        }
        else if(this.health < 30)
        {
            amount = -10;
        }
        for(int i = 0; i < this.owner.getPetList().size(); i++)
        {
            if(!this.owner.getPetList().get(i).getName().equals(this.name))
            {
                if(this.owner.getPetList().get(i).getHappiness() + amount <= MAX_HAPPINESS)
                {
                    this.owner.getPetList().get(i).setHappiness(this.owner.getPetList().get(i).getHappiness() + amount);
                }
            }
        }
    }

    /**
     * Determine whether the two pets haev same name and type
     * @param other other Pet object
     * @return true if both pets have same name and type
     */
    public boolean equals(PetR other)
    {
        return ((this.name).equals(other.getName())) && ((this.name).equals(other.getName()));
    }

    //Setter Methods
    public void setOwner(UserR newOwner)
    {
        this.owner = newOwner;
    }
    private void setHappiness(int amount)
    {
        this.happiness = amount;
    }

    //Getter methods
    public String getName()
    {
        return this.name;
    }
    public String getType()
    {
        return this.type;
    }

    public int getHappiness()
    {
        return this.happiness;
    }

    public int getHealth()
    {
        return this.health;
    }
}


