/*
 * This class is used to store and retrieve information about library Users.
 * The class features standard getter and setter methods, and tracks how many books a User is holding. 
 * maxNumberOfBooks is a static variable and changing it will affect all instances. All data is encapsulated.
 * 
 * This project was written as a University project.
 * 
 * @author	Elliot Hogg
 * @version 1.14  (07 Dec 2020)
 * 
 */

public class User implements Comparable<User>  
{
    private String firstName, lastName;
    private int numberOfBooksHeld;
    static int maxNumberOfBooks = 3;

    User(String firstName, String lastName) 
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfBooksHeld = 0;
    }

    //Constructor that parses 1 String into first & last name variables
    User(String fullName) 
    {
        //Finds space between 2 names and then parses them into individual first/last name variables (assumed only one 1st/lst name)
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

    private char initial()
    {
        return firstName.charAt(0);
    }

    public String getShortName()
    {
        return initial() + ". " + lastName;
    }

    public void addBook()
    {
        this.numberOfBooksHeld += 1;
    }

    public void returnBook()
    {
        this.numberOfBooksHeld -= 1;
    }
    
    //For potential name changes
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    //For potential name changes - e.g. marriage
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    //Incase of human error
    public void setNumberOfBooksHeld(int numberOfBooksHeld)
    {
        this.numberOfBooksHeld = numberOfBooksHeld;
    }

    @Override
    public String toString()
    {
        return "User [firstName = " + firstName + ",  lastName = " + lastName + ",  numberOfBooksHeld = " + numberOfBooksHeld + "]";
    }

    //Compares first & last names (ignoring cases) to check for equality and prevent duplicates
    public boolean equals(User otherUser)
    {
        return (this.firstName.equalsIgnoreCase(otherUser.firstName) && this.lastName.equalsIgnoreCase(otherUser.lastName));
    }

    //Compares last names to see which is greater, if the same then compares first names. Used to order User objects in array.
    public int compareTo(User u)
    {
        int lnCmp = lastName.compareToIgnoreCase(u.lastName);
        if (lnCmp!=0) return lnCmp;
        else return firstName.compareToIgnoreCase(u.firstName);
    }
}