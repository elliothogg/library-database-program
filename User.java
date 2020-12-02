import java.io.PrintWriter;

public class User implements Comparable<User>  
{
    
    private String firstName, lastName;
    private int numberOfBooksHeld;
    private final int maxNumberOfBooks = 3;

    User(String firstName, String lastName) 
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfBooksHeld = 0;
    }

    User(String fullName) 
    {
        int nameSeperationPoint = fullName.indexOf(" ");
        this.firstName = fullName.substring(0, nameSeperationPoint);
        this.lastName = fullName.substring(nameSeperationPoint + 1);
        
        this.numberOfBooksHeld = 0;
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

    public void addBook()
    {
        this.numberOfBooksHeld += 1;
    }

    public void returnBook()
    {
        this.numberOfBooksHeld -= 1;
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
        return (this.firstName.equalsIgnoreCase(otherUser.firstName) && this.lastName.equalsIgnoreCase(otherUser.lastName));
    }

    public int compareTo(User u)
    {
        int lnCmp = lastName.compareTo(u.lastName);
        if (lnCmp!=0) return lnCmp;
        else return firstName.compareTo(u.firstName);
    }

    public static void main(String[] args)
    {
        User test = new User("Elliot Hogg");
        User test2 = new User("Elliot Hogg");
        User test3 = new User("Daniel Hogg");


        System.out.println(test.equals(test3));
    }

}