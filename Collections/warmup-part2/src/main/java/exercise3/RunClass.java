package exercise3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Gabriel.Tabus on 7/7/2017.
 */
public class RunClass {

    public static void printMap1(Map<Student, Integer> map){
        Iterator it = map.entrySet().iterator();
        System.out.print("[");
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            System.out.print(pair.getKey() + " = " + pair.getValue() + ", ");
        }
        System.out.print("]");
        System.out.println();
    }

    public static void printMap2(Map<StudentBadHashCode, Integer> map){
        Iterator it = map.entrySet().iterator();
        System.out.print("[");
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            System.out.print(pair.getKey() + " = " + pair.getValue() + ", ");
        }
        System.out.print("]");
        System.out.println();
    }

    public static void printMap3(Map<StudentBadEquals, Integer> map){
        Iterator it = map.entrySet().iterator();
        System.out.print("[");
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            System.out.print(pair.getKey() + " = " + pair.getValue() + ", ");
        }
        System.out.print("]");
        System.out.println();
    }

    public static void printMap4(Map<StudentBestMethods, Integer> map){
        Iterator it = map.entrySet().iterator();
        System.out.print("[");
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            System.out.print(pair.getKey() + " = " + pair.getValue() + ", ");
        }
        System.out.print("]");
        System.out.println();
    }



    public static void main(String args[]){
        Map<Student, Integer> map1 = new HashMap<Student, Integer>();
        Map<StudentBadHashCode, Integer> map2 = new HashMap<StudentBadHashCode, Integer>();
        Map<StudentBadEquals, Integer> map3 = new HashMap<StudentBadEquals, Integer>();
        Map<StudentBestMethods, Integer> map4 = new HashMap<StudentBestMethods, Integer>();

        // Case 1
        System.out.println("Case 1:");

        map1.put(new Student("Ion", "Popescu"), 24);
        printMap1(map1);
        map1.put(new Student("Ion", "Vlad"), 56);
        printMap1(map1);

        // Case 2
        System.out.println("Case 2:");

        map2.put(new StudentBadHashCode("Ion", "Popescu"), 12);
        printMap2(map2);
        map2.put(new StudentBadHashCode("Ion", "Vlad"), 56);
        printMap2(map2);

        // Case 3
        System.out.println("Case 3:");

        map3.put(new StudentBadEquals("Ion", "Popescu"), 22);
        printMap3(map3);
        map3.put(new StudentBadEquals("Ion", "Vlad"), 67);
        printMap3(map3);
        System.out.println(map3.get(new StudentBadEquals("Ion", "Vlad")));

        // Case 4
        System.out.println("Case 4:");

        map4.put(new StudentBestMethods("Ion", "Popescu"), 22);
        printMap4(map4);
        map4.put(new StudentBestMethods("Ion", "Vlad"), 67);
        printMap4(map4);
        System.out.println(map4.get(new StudentBestMethods("Ion", "Vlad")));
        System.out.println(map4.get(new StudentBestMethods("Ion", "Popescu")));



    }
}
