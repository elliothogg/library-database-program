import java.util.ArrayList;

public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E> {

    public void insert(E o)
    {
        if (this.size() == 0)
        {
            this.add(o);
            return;
        }
    
        for (int i=0; i<this.size(); i++)
        {
            if (this.get(i).compareTo(o) >= 0)
            {
                this.add(i, o);
                return;
            }
        }
        
        this.add(this.size(), o);
    }    

   

    public static void main (String[] args) {
        System.out.println("hello");
        SortedArrayList<String> help = new SortedArrayList<>();
        
        System.out.println(help.toString());
        
    }
}   