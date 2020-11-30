import java.io.PrintWriter;

public class User implements Comparable<User>  
{
    
    private String firstName, lastName;
    private int numberOfBooksHeld;
    private final int maxNumberOfBooks = 3;

    User(String firstName, String lastName, int numberOfBooksHeld) 
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfBooksHeld = numberOfBooksHeld;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getFullName()
    {
        return firstName + " " + lastName;
    }

    public int getNumberOfBooksHeld()
    {
        return numberOfBooksHeld;
    }

    public int getMaxNumberOfBooks()
    {
        return maxNumberOfBooks;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setNumberOfBooksHeld(int numberOfBooksHeld)
    {
        this.numberOfBooksHeld = numberOfBooksHeld;
    }

    @Override
    public String toString() //change
    {
        return "User [firstName=" + firstName + ", lastName=" + lastName + ", numberOfBooksHeld=" + 
                               numberOfBooksHeld + ", maxNumberOfBooks=" + maxNumberOfBooks + "]";
    }

    public void printFullName(PrintWriter p)
    {
        p.println(getFullName());
    }

    public char initial()
    {
        return firstName.charAt(0);
    }

    public void printShortName(PrintWriter p)
    {
        p.print(initial() + ". " + lastName);
    }
        
    public boolean equals(User otherUser)
    {
        return (firstName.equalsIgnoreCase(otherUser.firstName) && lastName.equalsIgnoreCase(otherUser.lastName));
    }

    public int compareTo(User u)
    {
        int lnCmp = lastName.compareTo(u.lastName);
        if (lnCmp!=0) return lnCmp;
        else return firstName.compareTo(u.firstName);
    }

}