import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Driver {

    static Scanner sc = new Scanner(System.in);

    static Library cpa = new Library("input.txt");
    
    static PrintWriter p;

    static
    {
        try
        {
            p = new PrintWriter("output.txt");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    

    public static void main(String[] args) throws FileNotFoundException
    {
        runMainMenu();

        //testing methods
        // System.out.println(getUserIndex("zoe brown"));
        // System.out.println(getBookIndex("concurrent programming", "c. r. snow"));

       
        
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
        boolean programRunning= true;
        String menuChoice;
        
        printMenuOptions();
        
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
                    printArrayList(cpa.getBooks());
                    break;
                
                //Passes control to filterMenu
                case "u":
                    System.out.println("\n*********** LIBRARY USERS *********\n");
                    printArrayList(cpa.getUsers());
                    break;
                         
                case "i":
                    System.out.println("\n*********** ISSUE BOOK *********\n");
                    issueBook();
                  
                    break;
                
                case "r":
                    System.out.println("\n*********** RETURN BOOK *********\n");
                    returnBook();
                    
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
            System.out.println(); 
        }
    }

    public static int getBookIndex(String title, String author)
    {
        for (int i=0; i<cpa.getBooks().size(); i++)
        {
            Book book = cpa.getBooks().get(i);
            if (book.getAuthorFullName().equalsIgnoreCase(author) && book.getTitle().equalsIgnoreCase(title))
            {
                return i;
            }
        }
        return -1;
    }

    public static int getUserIndex(String name)
    {
        for (int i=0; i<cpa.getUsers().size(); i++)
        {
            User user = cpa.getUsers().get(i);
            if (user.getFullName().equalsIgnoreCase(name))
            {
                return i;
            }
        }
        return -1;
    }

    public static User whoHasBook(Book book)
    {
        if (book.getBorrowingUser() == null)
            return null;
        else return book.getBorrowingUser();
    }

    public static void printBookReturnNote(PrintWriter p, String name, String bookTitle)
    {
        p.println("Dear " + name);
        p.println("You currently have " + bookTitle + " on loan. Please can you return it asap.");
        p.println("All the best,");
        p.println("The Library");
        p.flush();
    }

    public static void issueBook()
    {
        System.out.println("Book Title: ");
        String bookTitle = sc.nextLine();
        System.out.println("Book Author: ");
        String bookAuthor = sc.nextLine();
        System.out.println("Users Name: ");
        String name = sc.nextLine();

        int bookIndex = getBookIndex(bookTitle, bookAuthor);
        int userIndex = getUserIndex(name);

        if (bookIndex != -1 && userIndex != -1)
        {   
            Book bookBeingQueried = cpa.getBooks().get(bookIndex);
            User userBeingQueried = cpa.getUsers().get(userIndex);
            if (whoHasBook(bookBeingQueried) != null)
            {
                printBookReturnNote(p, whoHasBook(bookBeingQueried).getFullName(), bookTitle);
                System.out.println("Book is already loaned out. A message has been sent for them to return it.");
            }
            else 
            {
                bookBeingQueried.setBorrowedBy(userBeingQueried);
                userBeingQueried.addBook();
                System.out.println("Book is available and has been sucessfully loaned out! The system has been updated.");
            }
        }
        else System.out.println("Book and/or User not found");
    }

    public static void returnBook()
    {
        System.out.println("Book Title: ");
        String bookTitle = sc.nextLine();
        System.out.println("Book Author: ");
        String bookAuthor = sc.nextLine();
        System.out.println("Users Name: ");
        String name = sc.nextLine();

        int bookIndex = getBookIndex(bookTitle, bookAuthor);
        int userIndex = getUserIndex(name);

        if (bookIndex != -1 && userIndex != -1)
        {   
            Book bookBeingQueried = cpa.getBooks().get(bookIndex);
            User userBeingQueried = cpa.getUsers().get(userIndex);

            if (bookBeingQueried.getBorrowingUser() == null)
                System.out.println("Error. Book has not been loaned out.");

            else if (!userBeingQueried.equals(bookBeingQueried.getBorrowingUser()))
            {
                System.out.println("Book & User found, however this user has not borrowed this book.");
            }
            else 
            {
                bookBeingQueried.returnBook();
                userBeingQueried.returnBook();
                System.out.println("The book was returned successfully");
            }
        }
        else System.out.println("Book and/or User not found");
    }
    
   
}