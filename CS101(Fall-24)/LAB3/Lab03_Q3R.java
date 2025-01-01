/**
* This is simple inventory management system
* Username and password is needed to enter the system
* By selecting options, user can add/remove custumor or add/delete item.
* 
* @author Ece ŞEŞEN
* @version 16.10.2024
*/

import java.util.Scanner;
public class Lab03_Q3R 
{
    public static void main(String[] args)
    {
        //Take username and password from user
        Scanner in = new Scanner(System.in);
        String customers = "PrimeTech, Peak, EcoGoods, ";
        String items = "Item104:Laptop Item125:Monitor ";
        System.out.print("Enter your username: ");
        String username = in.next();
 
        if(!username.equals("manager"))
        {
            System.out.println("Username not found! Goodbye!");
        }
        else
        {
            System.out.print("Enter your password: ");
            String password = in.next();
 
            if(!password.equals("inventory"))
            {
                System.out.println("Incorrect password! Goodbye!");
            }
            else
            {
                System.out.print("1-Add customer\n" + 
                                 "2-Delete customer\n" +
                                 "3-Add item\n" +
                                 "4-Delete item\n" +
                                 "5-Change Credential\n" +
                                 "6-Logout\n" +
                                 "Select an operation: ");
                int choice = in.nextInt();
 
                //Add customer if it is not on the list and print customers.
                if(choice == 1)
                {
                    System.out.println("--Add Customer--");
                    System.out.print("Enter customer name: ");
                    String newCostumer = in.next();
 
                    if(customers.contains(" " + newCostumer + ", "))
                    {
                        System.out.println("This customer is already in your list!");
                    }
                    else
                    {
                        customers += newCostumer + ", ";
                        System.out.println("New customer " + newCostumer + " is added!");
                    }
 
                    System.out.println("Your customers: (" + customers + ")");
                }
 
                //Delete customer with given name otherwise, warn user that it is not exist and print customers.
                else if (choice == 2)
                {
                    System.out.println("-- Delete Customer --");
                    System.out.print("Enter customer name which you want to delete: ");
                    String delete = in.next();
 
                    if(customers.contains(delete))
                    {
                        System.out.println(delete + " is deleted successfully from customers!");
                        customers = customers.replace(delete + ", ", "");
                    }
                    else
                    {
                        System.out.println("You don't have any customer whose name is " + delete + "!");
                    }
 
                    System.out.println("Your customers: (" + customers + ")");
                }
 
                //Add item with unique id and print items.
                else if(choice == 3)
                {
                    System.out.println("-- Add Item --");
                    System.out.print("Enter item name: ");
                    String item = in.next();
                    int newID = (int)(Math.random() * 900 + 100); //What is the range of random numbers?
 
                    if(items.contains("Item" + newID + ":"))
                    {
                        System.out.println("There is an item with id " + newID + " , you canot add a new item with the same id!");
                    }
                    else
                    {
                        System.out.println("New item with id " + newID + " is added!");
                        items += "Item" + newID + ":" + item + " ";
                    }
 
                    System.out.println("Your items: " + items);
                }
 
                //Delete item with given id and print items.
                else if(choice == 4)
                {
                    if(items.length() == 0)
                    {
                        System.out.println("You don't have any items!");
                    }
                    else
                    {
                        System.out.println("-- Delete Item --");
                        System.out.print("Enter itemId which you want to delete: ");
                        String deleteID = in.next();

                        if(items.contains("Item" + deleteID + ":"))
                        {
                            System.out.println("The item with the id " + deleteID + " is deleted successfully!");
                            int start = items.indexOf("Item" + deleteID + ":");
                            int finish = items.indexOf(" ", start);
                            items = items.substring(0, start) + items.substring(finish + 1);
                        }
                        else
                        {
                            System.out.println("You don't have any item with the id " + deleteID + "!");
                        }
                    }

                    System.out.println("Your items: " + items + " ");
                }
                else if(choice == 5)
                {
                    System.out.print("-- Change Credential --\n" +
                                     "1- Change username\n" +
                                     "2- Change password\n" +
                                     "Select an option: ");
                    int input = in.nextInt();
                    if(input == 1)
                    {
                        System.out.print("Enter  new username: ");
                        username = in.next();
                        System.out.println("Your username is " + username);
                    }
                    else
                    {
                        System.out.print("Enter new password: ");
                        password = in.next();
                        System.out.println("Your password is " + password);
                    }
                 }
                 
                //Log out, terminate the program.
                else 
                {
                    System.out.println("Logged out successfully!");
                }
            }
        }
        in.close();
    }
}
