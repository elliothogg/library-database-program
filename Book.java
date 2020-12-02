import java.io.PrintWriter;

public class Book implements Comparable<Book>  
{
    
    private final String title, authorFirstName, authorLastName;
    private User borrowedBy;

    Book(String title, String authorfirstName, String authorLastName)
    {
        this.title = title;
        this.authorFirstName = authorfirstName;
        this.authorLastName = authorLastName;
        this.borrowedBy = null;
    }

    Book(String title, String authorFullName)
    {
        this.title = title;

        int nameSeperationPoint = authorFullName.lastIndexOf(" ");
        this.authorFirstName = authorFullName.substring(0, nameSeperationPoint);
        this.authorLastName = authorFullName.substring(nameSeperationPoint + 1);

        this.borrowedBy = null;
    }

    public void setBorrowedBy(User borrowedBy)
    {
        this.borrowedBy = borrowedBy;
    }

    public void returnBook()
    {
        borrowedBy = null;
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
               ", borrowedBy=" + borrowedBy + "]";
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