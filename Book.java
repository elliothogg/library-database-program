/*
 * This class is used to store and retrieve information about library Books.
 * The class features standard getter and setter methods, and stores a reference to the User who is loaning the Book.
 * 
 * This project was written as a University project.
 * 
 * @author	Elliot Hogg
 * @version 1.14  (07 Dec 2020)
 * 
 */

public class Book implements Comparable<Book>  
{
    private final String title, authorFirstName, authorLastName;
    private boolean onLoan;
    private User borrowedBy;

    Book(String title, String authorfirstName, String authorLastName)
    {
        this.title = title;
        this.authorFirstName = authorfirstName;
        this.authorLastName = authorLastName;
        this.onLoan = false;

        //When set to null we know the book isn't on loan
        this.borrowedBy = null;
    }

    //Constructor that parses 1 String into first & last author name variables
    Book(String title, String authorFullName)
    {
        this.title = title;

        //Finds last space in String and then parses chars after to 'lastName' & chars before to 'firstName'
        int nameSeperationPoint = authorFullName.lastIndexOf(" ");
        this.authorFirstName = authorFullName.substring(0, nameSeperationPoint);
        this.authorLastName = authorFullName.substring(nameSeperationPoint + 1);
        
        this.onLoan = false;

        //When set to null we know the book isn't on loan
        this.borrowedBy = null;
    }

    public String getTitle()
    {
        return title;
    }

    public String getAuthorFullName()
    {
        return authorFirstName + " " + authorLastName;
    }

    public String getAuthorFirstName()
    {
        return authorFirstName;
    }

    public String getAuthorLastName()
    {
        return authorLastName;
    }

    private char initial()
    {
        return authorFirstName.charAt(0);
    }

    public String getAuthorShortName()
    {
        return initial() + ". " + authorLastName;
    }
        
    public boolean isOnLoan()
    {
        return onLoan;
    }

    public User getBorrowingUser()
    {
        return borrowedBy;
    }

    public void returnBook()
    {
        borrowedBy = null;
        onLoan = false;
    }

    public void setBorrowedBy(User borrowedBy)
    {
        this.borrowedBy = borrowedBy;
        onLoan = true;
    }

    @Override
    public String toString()
    {
        return "Book [title = " + title + ",  authorFirstName = " + authorFirstName + ",  authorLastName = " + authorLastName +
               ",  onLoan = " + onLoan + ",  borrowedBy = " + borrowedBy + "]";
    }

    //Compares author full name and title (ignoring cases) to check for equality and prevent duplucates
    public boolean equals(Book otherBook)
    {
        return (getAuthorFullName().equalsIgnoreCase(otherBook.getAuthorFullName()) && title.equalsIgnoreCase(otherBook.getTitle()));
    }

    //Compares last names to see which is greater, if the same then compares first names. Used to order Book objects in array.
    public int compareTo(Book b)
    {
        int lnCmp = authorLastName.compareToIgnoreCase(b.authorLastName);
        if (lnCmp!=0) return lnCmp;
        else return authorFirstName.compareToIgnoreCase(b.authorFirstName);
    }
}