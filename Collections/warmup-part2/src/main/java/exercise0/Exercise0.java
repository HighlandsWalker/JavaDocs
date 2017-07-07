package exercise0;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Radu.Hoaghe on 4/20/2015.
 *
 * Exercise 0: Iterate over the keys of a Map using keySet method (this method returns a Set of all the map keys) and
 *          print all its elements.
 */
public class Exercise0 {

    public Exercise0(){

    }

    public void iterateThroughMap(){

        // Exercise #0 a) Create a Map (HashMap) and add elements to it (using put() method)
        // Exercise #0 a) Hint: Don't forget to specify the types of the key and value when creating the Map

        Map<Integer, String> map = new HashMap<Integer, String>();

        map.put(1,"Ana");
        map.put(2, "Marius");
        map.put(3, "Mihai");
        map.put(4, "Casandra");

        Set<Integer> keySet = map.keySet();

        // Exercise #0 b) Iterate over the Map using keySet() method and print all its elements
        // Exercise #0 b) The elements could be printed like this: [key1=value1, key2=value2, ...]

        System.out.println("The set keys are: ");

        Iterator it = keySet.iterator();

        System.out.print("[");
        while(it.hasNext()){
            Object current = it.next();
            System.out.print((Integer)current + "=" + map.get((Integer)current) + ", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Exercise0 exercise0 = new Exercise0();
        exercise0.iterateThroughMap();
    }
}
