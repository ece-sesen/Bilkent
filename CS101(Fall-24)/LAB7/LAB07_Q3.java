/**
 * This is a movie rating rewiew program.
 * User can enter movie name and add reviews or ratings.
 * User can remove entered movie adn terminate program whenever user wants.
 * 
 * @author ECE ŞEŞEN
 * @version 26.11.2024
 * 
 */

import java.util.ArrayList;
import java.util.Scanner;

public class LAB07_Q3
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        boolean terminate = false;
        int current = 0; //In order to add nee movies at the end of the listi store current number of movies.
        ArrayList<String> movies = new ArrayList<String>();
        ArrayList<ArrayList<String>> reviews = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<Double>> ratings = new ArrayList<ArrayList<Double>>();

        int choice;

        do
        {
            //Print the menu
            System.out.print("Movie Review System\n" +
                           "1- Add a new movie\n" + 
                           "2- Remove a movie\n" +
                           "3- Submit a rewiev and rating for a movie\n" +
                           "4- View all movies, reviews, and average ratings\n" +
                           "5- Exit\n" +
                           "Enter your choice: ");
            choice = in.nextInt();
            System.out.println();

            if(choice == 1) //Add movie
            {
                System.out.print("Enter the movie title: ");
                in.nextLine();
                String title = in.nextLine();
                movies.add(current, title);
                reviews.add(current, new ArrayList<String>()); //Start ArrayList of reviews which belongs to each movie
                ratings.add(current, new ArrayList<Double>());
                System.out.println("Movie added!\n");
                current++;
            }

            else if(choice == 2) //Remove movie
            {
                System.out.println("Select a movie to remove: ");
                for(int i = 0; i < movies.size(); i++)
                {
                    System.out.println((i + 1) + ". " +  movies.get(i));
                }

                System.out.print("Enter movie number: ");
                int select = in.nextInt();
                if(select > movies.size()) //Warn user about invalid input
                {
                    System.out.println("The entered number is wrong.");
                }
                else
                {
                    int index = select - 1;
                    String name = movies.get(index);
                    movies.remove(index);
                    reviews.remove(index);
                    ratings.remove(index);
                    System.out.println(name + " has been removed.\n");
                }
                
            }

            else if(choice == 3) //Add rewiew and rating to specific movie
            {
                System.out.println("Select a movie to rewiew: ");
                for(int i = 0; i < movies.size(); i++)
                {
                    System.out.println((i + 1) + " ." + movies.get(i));
                }

                System.out.print("Enter movie number: "); //Select specific movie

                int select = in.nextInt();
                int index = select - 1;

                System.out.print("Enter yout review: ");
                in.nextLine(); //In order to make computer read input
                String review = in.nextLine();
                reviews.get(index).add(review);

                System.out.print("Enter your rating (1-5): ");
                double rate = in.nextDouble();
                ratings.get(index).add(rate);

                System.out.println("Review and rating submitted!\n");
            }

            else if(choice == 4) //List all information about movies on list
            {
                System.out.println("Movie List: ");
                if(movies.size() == 0)
                {
                    System.out.println("-> Empty");
                }
                else
                {
                    for(int i = 0; i < movies.size(); i++)
                    {
                        System.out.println((i + 1) + ". " + movies.get(i));
                        if(ratings.get(i).size() == 0)
                        {
                            System.out.println("   Rating: N/A");
                        }
                        else
                        {
                            ArrayList <Double> movieRatings = ratings.get(i);
                            System.out.println("   Average Rating: " + calculateAveRate(movieRatings));
                        }
                        
                        if(reviews.get(i).size() == 0)
                        {
                            System.out.println("   No reviews yet!");
                        }
                        else
                        {
                            ArrayList<String> movieReview = reviews.get(i);
                            System.out.println("   Reviews: ");
                            for(int k= 0; k < movieReview.size(); k++ )
                            {
                                System.out.println("   - " + movieReview.get(k));
                            }
                        }
                        System.out.println();
    
                    }
                }  
            }

            else if(choice == 5) //Terminate the program
            {
                System.out.println("Program terminated. Good bye!\n");
                terminate = true;
            }
        }
        while(!terminate);
        in.close();
    }

    /**
     * Find the average rating of movie
     * @param list rating list of movie
     * @return average rating
     */
    public static double calculateAveRate(ArrayList<Double> list)
    {
        double sum = 0;
        int size = list.size();
        for(int i = 0; i < size; i++)
        {
            sum += list.get(i);
        }
        return sum / size;
    }
}
