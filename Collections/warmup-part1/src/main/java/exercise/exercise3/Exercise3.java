package exercise.exercise3;

import java.util.*;

/**
 * Created by Radu.Hoaghe on 04/20/2015.
 * <p>
 * Exercise 3: Fill three Set implementations that you know (Hint: they were described during
 * the earlier presentation) with the List<String> that is given to this class by
 * its constructor.
 * <p>
 * Check out the elements that the list mentioned above contains and then, add them
 * to your three Sets. After this check out the elements of your Sets. What do you
 * remark? What could be the reason?
 * <p>
 * Finally, add to the one of the three Sets some elements
 * that already exist in the Set (e.g add("that") and add("collection"))
 * <p>
 * To run your implementation, run the Exercise3Test class.
 */
public class Exercise3 {

    // List containing some elements that need to be added into the Set
    private List<String> listToAdd;

    public Exercise3(List<String> l) {
        listToAdd = l;
    }

    public void addElementsToSets() {

        System.out.println("The elements that will be added to the Sets: ");
        // TODO Exercise #3 a) Check the content of the elements you will add into the Set
        for (String each : listToAdd) {
            System.out.print(each + " ");
        }
        System.out.println();

        // TODO Exercise #3 b) add the elements from listToAdd to the Sets
        HashSet<String> wordsHS = new HashSet<String>();
        LinkedHashSet<String> wordsLHS = new LinkedHashSet<String>();
        SortedSet<String> wordsTS = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);

        wordsHS.addAll(listToAdd);
        wordsLHS.addAll(listToAdd);
        wordsTS.addAll(listToAdd);

        // TODO Exercise #3 c) Check the content of the Sets
        System.out.println("\nThe elements contained in the first Set: ");
        for (String each : wordsHS) {
            System.out.print(each + " ");
        }
        System.out.println();

        System.out.println("\nThe elements contained in the second Set: ");
        for (String each : wordsLHS) {
            System.out.print(each + " ");
        }
        System.out.println();

        System.out.println("\nThe elements contained in the third Set: ");
        for (String each : wordsTS) {
            System.out.print(each + " ");
        }
        System.out.println();

        System.out.println("\nThe elements contained in the TreeSet after inserting two duplicates: ");
        // TODO Exercise #3 d) Add to the TreeSet two elements that already exist in the Set
        wordsTS.add("that");
        wordsTS.add("no");

        // TODO Exercise #3 d) and print again the TreeSet. What do you see?
        for (String each : wordsTS) {
            System.out.print(each + " ");
        }
        System.out.println();
    }
}
