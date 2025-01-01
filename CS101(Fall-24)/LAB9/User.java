/**
 * This is User class.
 * Stores usernames and their password and also list of their pets.
 * Users can adopt unowned or new pets as long has they have enough budget.
 * Also find pets which at least specific health or happiness or average values of those variables.
 * Prints the prrperties of User object.
 * 
 * @author Ece ŞEŞEN
 * @version 04.12.2024
 */
import java.util.ArrayList;

public class User 
{
    //Class Varibles
    private static final int  CAT_BUDGET = 30;
    private static final int  DOG_BUDGET = 40;
    private static final int  RABBIT_BUDGET = 20;

    //Instance variables
    private String username;
    private String password;
    private ArrayList<Pet> pets;
    private int budget;

    //Constructor
    public User(String aUsername, String aPassword)
    {
        this.username = aUsername;
        this.password = aPassword;
        this.budget = 100;
        pets = new ArrayList<Pet>();
    }

    /**
     * Check whether the password is the same or not
     * @param testPassword given password
     * @return true if given passwprd is same witht he instance variable, otherwise false.
     */
    public boolean verifyPassword(String testPassword)
    {
        return (this.password).equals(testPassword);
    }

    /**
     * Add new pet to the user
     * @param name pet name
     * @param type pet type
     */
    public void adoptPet(String name, String type)
    {
        if(enoughBudget(type))
        {
            Pet newPet = new Pet(name, type, this);
            pets.add(newPet);
            updateBudget(type);
            System.out.println("You have adopted a new " + type  + " named " + name);
        }  
    }

    /**
     * Add unowned pet to the user's pet list
     * @param newPet pet object
     */
    public void adoptPet(Pet newPet) //Override
    {
        String type = newPet.getType();
        if(enoughBudget(type));
        {
            pets.add(newPet);
            updateBudget(type);
            newPet.setOwner(this);
            System.out.println("You have adopted a new " + newPet.getType()  + " named " + newPet.getName());   
        }
    }
       
    /**
     * Reduce the relevant point when user adopt a pet
     * @param type pet type
     */
    private void updateBudget(String type)
    {
        if(type.equals("Cat"))
        {
            this.budget -= CAT_BUDGET;
        }
        else if(type.equals("Dog"))
        {
            this.budget -= DOG_BUDGET;
        } 
        else if(type.equals("Rabbit"))
        {
            this.budget -= RABBIT_BUDGET;
        }
    }

    /**
     * Check whether the user has enough point to adopt a pet
     * @param type pet type
     * @return true if user has enough point to adopt a pet otherwise return false
     */
    private boolean enoughBudget(String type)
    {
        if(type.equals("Cat"))
        {
            return this.budget - CAT_BUDGET >= 0;
        }
        else if(type.equals("Dog"))
        {
            return this.budget - DOG_BUDGET >= 0;
        } 
        else if(type.equals("Rabbit"))
        {
            return this.budget - RABBIT_BUDGET >= 0;
        }
        else
        {
            return false;
        }
    }

    /**
     * Remove pet from the user's list
     * @param removedPet pet object to be released
     * @return true if the user own specific pet, otherwise return false
     */
    public Pet releasePet(Pet removedPet)
    {
        if(pets.remove(removedPet))
        {
            removedPet.setOwner(null);
            System.out.println("You have released " + removedPet.getName() + " the " + removedPet.getType());
            return removedPet;
        }
        else
        {
            System.out.println("You do not own to the " + removedPet.getType() + " " + removedPet.getName());
            return null;
        } 
    }

    /**
     * Gives the pets which are happy more than minimum limit
     * @param minHappy minimum happiness level
     * @return pet list
     */
    public ArrayList<Pet> searchPetsByHappiness(int minHappy)
    {
        ArrayList<Pet> happyPets = new ArrayList<Pet>();

        for(Pet pet: pets)
        {
            if(pet.getHappiness() >= minHappy)
            {
                happyPets.add(pet);
            }
        }
        return happyPets;
    }

    /**
     * Gives the pets which are healthy more than minimum limit
     * @param minHealth minimum health level
     * @return pet list
     */
    public ArrayList<Pet> searchPetsByHealth(int minHealth)
    {
        ArrayList<Pet> healthyPets = new ArrayList<Pet>();

        for(Pet pet: pets)
        {
            if(pet.getHealth() >= minHealth)
            {
                healthyPets.add(pet);
            }
        }
        return healthyPets;
    }

    /**
     * Find the average health and happiness values
     */
    public void displayAverageHealthAndHappiness()
    {
        double sumHappiness = 0;
        double sumHealth = 0;
        int petNo = this.pets.size();

        for(Pet pet: this.pets)
        {
            sumHappiness += pet.getHappiness();
            sumHealth += pet.getHealth();
        }

        System.out.println("Average Health is: " + (sumHealth / petNo) + ", " + "Average Happiness is: " + (sumHappiness / petNo) );
    }

    /**
     * Print the list of user's pets
     */
    public void displayPets()
    {
        String result = "Your Pets: \n";
        for(Pet pet: this.pets)
        {
            result += pet.toString() + "\n";
        }
        System.out.println(result);
    }

    /**
     * Find the pet with given name
     * @param name pet name
     * @return pet object
     */
    public Pet getPetByName(String name)
    {
        for(Pet pet: pets)
        {
            if(pet.getName().equals(name))
            {
                return pet;
            }
        }
        return null;
    }

    //Getter methdos
    public int getBudget()
    {
        return this.budget;
    }
    public String getName()
    {
        return this.username;
    }
}
