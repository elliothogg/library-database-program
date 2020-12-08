/*
 * This class is used to store and retrieve Library Users and Books (duplicates cannot occur).
 * It features all the methods needed to manage a simple library database system.
 * 
 * .txt files containing user and book data can also be parsed and stored (format specific).
 * 
 * This project was written as a University project.
 * 
 * @author	Elliot Hogg
 * @version 1.14  (07 Dec 2020)
 * 
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.PrintWriter;

public class Library {
    
    //SortedArrayLists to store lists of Users & Books (SortedArrayList had special 'insert' method that keeps list ordered)
    private SortedArrayList<User> users = new SortedArrayList<>();
    private SortedArrayList<Book> books = new SortedArrayList<>();

    //Constructor to create empty Library
    Library() {};

    //Constructor to create Library populated with data from .txt file
    Library(String inputFileURI)
    {
        //try to create String of input from .txt file and parse to arrays. If file cannot be found then catch error and throw error message
        try 
        {
            String[] txtInput = txtFileToArray(inputFileURI);
    
            addInputBooksToArrayList(txtInput);
            addInputUsersToArrayList(txtInput);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //Reads a text file and parses each line to a new element in an array
    private String[] txtFileToArray(String inputFileURI) throws IOException
    {
        //Create a reader and pass in the file path parameter
        BufferedReader in = new BufferedReader(new FileReader(inputFileURI));
        
        //parse each line to element in array
        String[] parsedTxtFile = in.lines().toArray(String[]::new);

        //close stream
        in.close();

        return parsedTxtFile;
    }

    //Reads through the text file array, converts the data into new Book Objects, and adds then to arrayList
    private void addInputBooksToArrayList(String[] input)
    {
        //number of books is always on first line of file/first element of array
        int numOfBooks = Integer.parseInt(input[0]);
        //2 lines per book
        int bookRelatedElements = numOfBooks * 2;

        //pointers to iterate through sets of 2 lines for each book
        int titlePointer = 1;
        int authorPointer = 2;
        
        //while loop that iterates through all books and adds to array
        while (titlePointer < bookRelatedElements)
        {
            addBook(new Book(input[titlePointer], input[authorPointer]));
            titlePointer += 2;
            authorPointer +=2;
        }
    }

    //Reads through the text file array, converts the data into new User Objects, and adds then to arrayList
    private void addInputUsersToArrayList(String[] input)
    {
        //number of books is always on first line of file/first element of array
        int numOfBooks = Integer.parseInt(input[0]);
        
        //number of users is at index after all books
        int numOfUsersElementPos = 1 + (numOfBooks * 2);

        //get number of users from correct index
        int numOfUsers = Integer.parseInt(input[numOfUsersElementPos]);

        //create name pointer which starts at index after number of users
        int namePointer = numOfUsersElementPos + 1;
        
        //while loop that iterates through all users and adds them to array
        while (namePointer <= (numOfUsersElementPos + numOfUsers))
        {
            addUser(new User(input[namePointer]));
            namePointer += 1;
        }
    }

    //Adds unique Book to arrayLIst
    public void addBook(Book book)
    {
        //if array has no items then we can add Book without checking for duplicates
        if (books.size() == 0)
            books.insert(book);
        else
        {
            //loop through 'books' array, if a Book Object matches the one being inserted then print error message
            boolean bookExists = false;
            for (Book b : books)
            {
                if (book.equals(b))
                    bookExists = true;   
            }
            if (bookExists)
                System.out.println("Book already exists");

            //if no duplicate found then add Book Object to array
            else books.insert(book);
        }
    }
    
    //Adds unique User to arrayList
    public void addUser(User user)
    {
        //if array has no items then we can add User without checking for duplicates
        if (users.size() == 0)
            users.insert(user);
        else
        {
            //loop through 'users' array, if a User Object matches the one being inserted then print error message
            boolean userExists = false;
            for (User u : users)
            {
                if (user.equals(u))
                    userExists = true;   
            }
            if (userExists)
                System.out.println("User already exists");
            
            //if no duplicate found then add User Object to array
            else users.insert(user);
        }
    }

    public ArrayList<User> getUsers()
    {
        return users;
    }

    public ArrayList<Book> getBooks()
    {
        return books;
    }
    
    public String getNumOfBooksStored()
    {
        return "Number of books owned by Library: " + books.size();
    }
    
    public String getNumOfUsersStored()
    {
        return "Number of users registered at Library: " + users.size();
    }

    //Finds the index of a Book object. If Book is not found then return -1
    private int getBookIndex(Book book)
    {
        //loop through all Book stored in arrayList
        for (int i=0; i<getBooks().size(); i++)
        {
            Book existingBooks = getBooks().get(i);
            if (existingBooks.equals(book))
            {
                return i;
            }
        }
        return -1;
    }

    //Finds the index of a User Object. If User is not found then return -1
    private int getUserIndex(User user)
    {
        //loop through all User stored in arrayList
        for (int i=0; i<this.getUsers().size(); i++)
        {
            User existingUsers = this.getUsers().get(i);
            if (existingUsers.equals(user))
            {
                return i;
            }
        }
        return -1;
    }

    //issues book if it exists in system and is available
    public String issueBook(String bookTitle, String bookAuthor, String name, PrintWriter p)
    {
        //get the index of the book and user being queried. If not found then -1 is returned
        int bookIndex = getBookIndex(new Book(bookTitle, bookAuthor));
        int userIndex = getUserIndex(new User(name));

        //if both book and user are found
        if (bookIndex != -1 && userIndex != -1)
        {   
            //Copy book and user for further operations
            Book bookBeingQueried = getBooks().get(bookIndex);
            User userBeingQueried = getUsers().get(userIndex);

            //if book is already on loan
            if (bookBeingQueried.isOnLoan())
            {
                //if user who is trying to rent book already has book then return error message
                if (bookBeingQueried.getBorrowingUser().equals(userBeingQueried))
                    return "User already has this book!";

                //else tell program that book is already on loan and print message to file for it to be returned
                else
                {
                    printBookReturnNote(p, bookBeingQueried.getBorrowingUser().getFullName(), bookTitle);
                    return "Book is already loaned out. A message has been sent for them to return it.";
                }
            }
            //if book not on loan but user already has maximum allowed books then return error message
            else if (userBeingQueried.getNumberOfBooksHeld() >= userBeingQueried.getMaxNumberOfBooks())
                    return "User is not allowed to loan anymore books! Max = " + userBeingQueried.getMaxNumberOfBooks();
            
            //if book is not on loan and user is not at maximum books then assign User to Book object and increment User number of books
            else 
            {
                bookBeingQueried.setBorrowedBy(userBeingQueried);
                userBeingQueried.addBook();
                return "Book is available and has been successfully loaned out! The system has been updated.";
            }
        }
        else return "Book and/or User not found";
    }

    //returns book if it exists in system and is marked as on loan
    public String returnBook(String bookTitle, String bookAuthor, String name)
    {
        //get the index of the book and user being queried. If not found then -1 is returned
        int bookIndex = getBookIndex(new Book(bookTitle, bookAuthor));
        int userIndex = getUserIndex(new User(name));

        //if both book and user are found
        if (bookIndex != -1 && userIndex != -1)
        {   
            //Copy book and user for further operations
            Book bookBeingQueried = getBooks().get(bookIndex);
            User userBeingQueried = getUsers().get(userIndex);

            //if book is not on loan return error message
            if (!bookBeingQueried.isOnLoan())
                return "Error. Book has not been loaned out.";

            //if user trying to return book is not user who has book, then return error message
            else if (!userBeingQueried.equals(bookBeingQueried.getBorrowingUser()))
            {
                return "Book & User found, however this user has not borrowed this book.";
            }
            else 
            {
                bookBeingQueried.returnBook();
                userBeingQueried.returnBook();
                return "The book was returned successfully";
            }
        }
        //if book or user not found then return error message
        else return "Book and/or User not found";
    }

    public void printBookReturnNote(PrintWriter p, String name, String bookTitle)
    {
        p.println("Dear " + name);
        p.println("You currently have " + bookTitle + " on loan. Please can you return it asap.");
        p.println("All the best,");
        p.println("The Library");
        p.flush();
    }
}