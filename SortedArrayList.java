/*
 * This class extends the ArrayList Java class. 
 * There is an insert method implemented which adds items to ArrayList whilst preserving an accending order.
 * 
 * This project was written as a University project.
 * 
 * @author	Elliot Hogg
 * @version 1.14  (07 Dec 2020)
 * 
 */

import java.util.ArrayList;

public class SortedArrayList<E extends Comparable<E>> extends ArrayList<E> {

    //Inserts objects into arrayList whilst preserving accending order
    public void insert(E o)
    {
        //if array is empty then insert at index 0
        if (size() == 0)
        {
            add(o);
            return;
        }
        //iterate through arrayList
        for (int i=0; i<size(); i++)
        {
            //if Object being inserted is smaller than current element, add Object at this position
            if (o.compareTo(get(i)) < 0)
            {
                add(i, o);
                return;
            }
        }
        //if Object is not smaller than any of existing elements, then add Object at end of array
        add(o);
    }
}   