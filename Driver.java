/*
 * This is the Driver class for the program, and contains the main method.
 * 
 * The terminal program and data retrieval is managed by this class.
 * 
 * To run - $ javac Driver.java
 *          $ java Driver
 * 
 * This project was written as a University project.
 * 
 * @author	Elliot Hogg
 * @version 1.14  (07 Dec 2020)
 * 
 */

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.File;

public class Driver {

    //Create Scanner Object & PrintWriter reference
    static Scanner sc = new Scanner(System.in);
    static PrintWriter p;

    //Create library object to be used in program using constructor that allows to parse info from .txt file
    static Library cpa = new Library("input.txt");
    
    //assign PrintWriter Object using static block to catch FileNotFound exception
    static
    {
        try
        {
            File output = new File("output.txt");
            p = new PrintWriter(output);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args)
    {
        runMainMenu();
    }

    public static void printMenuOptions()
    {
        System.out.println("\n" + "*********** MENU *********" + "\n\n" +
        "f - Finish Running Program" + "\n" +
        "b - Display Books" + "\n" +
        "u - Display Users" + "\n" +
        "i - Issue a Book" + "\n" +
        "r - Return a Book" + "\n");
    }

    public static void runMainMenu()
    {
        boolean programRunning = true;
        String menuChoice;
        
        printMenuOptions();
        
        //Do-while loop that runs until programRunning is set to false
        do {
            System.out.print("Please select a menu option (to see the options again press m): ");
            menuChoice = sc.nextLine().toLowerCase();
            
            switch(menuChoice)
            {                 
                case "f":
                    System.out.println("\n************ THANKS FOR USING ************\n");
                    programRunning = false;
                    break;

                case "b":
                    System.out.println("\n*********** LIBRARY BOOKS *********\n");
                    System.out.println(cpa.getNumOfBooksStored());
                    System.out.println();
                    printArrayList(cpa.getBooks());
                    break;
                    
                case "u":
                    System.out.println("\n*********** LIBRARY USERS *********\n");
                    System.out.println(cpa.getNumOfUsersStored());
                    System.out.println();
                    printArrayList(cpa.getUsers());
                    break;
                    
                    case "i":
                    System.out.println("\n*********** ISSUE BOOK *********\n");
                    //issue book method is called, which also returns result as a String
                    System.out.println(cpa.issueBook(getBookTitle(), getAuthorName(), getUserName(), p));
                    System.out.println();
                    break;
                    
                    case "r":
                    System.out.println("\n*********** RETURN BOOK *********\n");
                    //return book method is called, which also returns result as String
                    System.out.println(cpa.returnBook(getBookTitle(), getAuthorName(), getUserName()));
                    System.out.println();
                    break;
         
                case "m":
                    printMenuOptions();
                    break;
                
                default:
                    System.err.print("Invalid input! ");
                    break;
                }
            } while (programRunning);        
    }

    //Loops through all items in passed ArrayList and neatly prints them
    public static <T> void printArrayList(ArrayList<T> arrayList)
    {
        for (int i = 0; i < arrayList.size(); i++)
        {
            System.out.println(arrayList.get(i));
            System.out.println();
        }
    }

    public static String getUserName()
    {
        System.out.print("User Full Name: ");
        String userName = sc.nextLine();
        if (userName.length() < 1 || !userName.contains(" "))
        {
            System.out.print("Error - must contain at least 2 words. ");   
            return getUserName();
        }
        else return userName;
    }

    public static String getAuthorName()
    {
        System.out.print("Author Full Name: ");
        String authorName = sc.nextLine();
        if (authorName.length() < 1 || !authorName.contains(" "))
        {
            System.out.print("Error - must contain at least 2 words. ");   
            return getAuthorName();
        }
        else return authorName;
    }

    public static String getBookTitle()
    {
        System.out.print("Book Title: ");
        String title = sc.nextLine();
        if (title.length() < 1)
        {
            System.out.print("Error - no input. ");
            return getBookTitle();
        }
        else return title;
    }
}