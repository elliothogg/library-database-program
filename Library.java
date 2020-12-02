import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Library {
    
    //ArrayLists to store lists of Users & Books
    private SortedArrayList<User> users = new SortedArrayList<>();
    private SortedArrayList<Book> books = new SortedArrayList<>();

    Library() {};

    Library(String inputFileURI)
    {
        try 
        {
            String[] txtInput = txtFileToArray(inputFileURI);
    
            addInputBooksToArrayList(txtInput);
            addInputUsersToArrayList(txtInput);
        }
        catch (IOException e)
        {
            throw new Error("Cannot find File URI");
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
    

    public String[] txtFileToArray(String inputFileURI) throws IOException
    {
        //Create a reader and pass in the file path parameter
        BufferedReader in = new BufferedReader(new FileReader(inputFileURI));
        
        String[] parsedTxtFile = in.lines().toArray(String[]::new);

        in.close();

        return parsedTxtFile;
    }

    public void addInputBooksToArrayList(String[] input)
    {
        int numOfBooks = Integer.parseInt(input[0]);
        int bookRelatedElements = numOfBooks * 2;

        int titlePointer = 1;
        int authorPointer = 2;
        
        while (titlePointer < bookRelatedElements)
        {
            books.insert(new Book(input[titlePointer], input[authorPointer]));
            titlePointer += 2;
            authorPointer +=2;
        }
    }

    public void addInputUsersToArrayList(String[] input)
    {
        int numOfBooks = Integer.parseInt(input[0]);
        int numOfUsersElementPos = 1 + (numOfBooks * 2);
        int numOfUsers = Integer.parseInt(input[numOfUsersElementPos]);

        int namePointer = numOfUsersElementPos + 1;
        
        while (namePointer <= (numOfUsersElementPos + numOfUsers))
        {
            users.insert(new User(input[namePointer]));
            namePointer += 1;
        }
    }

    public static void main (String[] args) throws IOException
    {

        // ******* Testing that SortedArrayList is functional
        // User a = new User("a", "a", 2);
        // User b = new User("a", "b", 1);
        // User c = new User("a", "c", 2);
        // User d = new User("a", "d", 1);
        // User e = new User("a", "e", 2);
        User f= new User("a", "f");


        // Book aa = new Book("BNW", "pano", "a");
        // Book bb = new Book("BNW", "pano", "b");
        // Book cc = new Book("BNW", "pano", "c");
        // Book dd = new Book("BNW", "pano", "d");
        // Book ee = new Book("BNW", "pano", "e");
        // Book ff = new Book("BNW", "pano", "f");
        
        Library cpa = new Library();

        // cpa.users.insert(f);
        // cpa.users.insert(a);
        // cpa.users.insert(c);
        // cpa.users.insert(d);
        // cpa.users.insert(e);
        // cpa.users.insert(b);
        
        // cpa.books.insert(ff);
        // cpa.books.insert(aa);
        // cpa.books.insert(cc);
        // cpa.books.insert(dd);
        // cpa.books.insert(ee);
        // cpa.books.insert(bb);

        // System.out.println(cpa.users.toString());
        // System.out.println(cpa.books.toString());

        //********** */


        // Testing that book input file parse methods are functional
        // cpa.addInputBooksToArrayList(cpa.txtFileToArray("input.txt"));

        // System.out.println(cpa.books.toString());
        
        // //********** */


        // // Testing that user input file parse methods are functional
        // cpa.addInputUsersToArrayList(cpa.txtFileToArray("input.txt"));

        // System.out.println(cpa.users.toString());
        
        //********** */


        // // Testing input file constructor
        
        // Library oakwood = new Library("input.txt");

        // System.out.println(oakwood.users.toString());
        // System.out.println(oakwood.books.toString());
        
        //********** */

        //testing insert method of SortedArrayList
        Library oakwood = new Library("input.txt");

        System.out.println(oakwood.users.toString());

        User c = new User("John Bastion");
        User d = new User("G.C. Arrow");
        User e = new User("Amter sd Zerton");
        User g = new User("John Williams");
        User j = new User("Ammo", "Smith");

        oakwood.users.insert(c);
        oakwood.users.insert(d);
        oakwood.users.insert(e);
        oakwood.users.insert(g);
        oakwood.users.insert(j);

        System.out.println(oakwood.users.toString());

        
    }
}