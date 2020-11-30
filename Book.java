import java.io.PrintWriter;

public class Book implements Comparable<Book>  
{
    
    private final String title, authorFirstName, authorLastName;
    private User borrowedBy;

    Book(String title, String authorFirstName , String authorLastName, User borrowedBy) //Maybe store author name as string[2]??
    {
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.borrowedBy = borrowedBy;
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

    public User getBorrowingUser()
    {
        return borrowedBy;
    }

    public String toString()
    {
        return "Book [title=" + title + ", authorFirstName=" + authorFirstName + ", authorLastName=" + authorLastName +
               ", borrowedBy=" + borrowedBy.toString();
    }

    public void printAuthorName(PrintWriter p)
    {
        p.println(getAuthorFullName());
    }

    public char initial()
    {
        return authorFirstName.charAt(0);
    }

    public void printAuthorShortName(PrintWriter p)
    {
        p.print(initial() + ". " + authorLastName);
    }
        
    public boolean equals(Book otherBook)
    {
        return (authorFirstName.equalsIgnoreCase(otherBook.authorFirstName) && authorLastName.equalsIgnoreCase(otherBook.authorLastName));
    }

    public int compareTo(Book b)
    {
        return authorLastName.compareTo(b.authorLastName);
    }

}