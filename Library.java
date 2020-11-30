public class Library {
    
    //ArrayLists to store lists of Users & Books
    private SortedArrayList<User> users = new SortedArrayList<>();
    private SortedArrayList<Book> books = new SortedArrayList<>();


    public static void main (String[] args)
    {
        User a = new User("a", "a", 2);
        User b = new User("a", "b", 1);
        User c = new User("a", "c", 2);
        User d = new User("a", "d", 1);
        User e = new User("a", "e", 2);
        User f= new User("a", "f", 1);

        User bookTest = new User("","",1);

        Book aa = new Book("BNW", "pano", "a", bookTest);
        Book bb = new Book("BNW", "pano", "b", bookTest);
        Book cc = new Book("BNW", "pano", "c", bookTest);
        Book dd = new Book("BNW", "pano", "d", bookTest);
        Book ee = new Book("BNW", "pano", "e", bookTest);
        Book ff = new Book("BNW", "pano", "f", bookTest);
        
        Library cpa = new Library();

        cpa.users.insert(f);
        cpa.users.insert(a);
        cpa.users.insert(c);
        cpa.users.insert(d);
        cpa.users.insert(e);
        cpa.users.insert(b);
        
        cpa.books.insert(ff);
        cpa.books.insert(aa);
        cpa.books.insert(cc);
        cpa.books.insert(dd);
        cpa.books.insert(ee);
        cpa.books.insert(bb);

        System.out.println(cpa.users.toString());

        System.out.println(cpa.books.toString());

        
    }
}