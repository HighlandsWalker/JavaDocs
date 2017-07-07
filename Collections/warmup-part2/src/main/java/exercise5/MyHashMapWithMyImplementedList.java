package exercise5;

import exercise4.MyHashMap;

import java.util.Collection;
import java.util.Set;

/**
 * Create a HashMap that uses to store the buckets your implementation of MyImplementedList that you
 * created in the Collections I workshop.
 *
 * Created by Radu.Hoaghe on 7/6/2017.
 */
public class MyHashMapWithMyImplementedList {

    // TODO uncomment the following line and add your MyImplementedList implementation to the project
//    private MyImplementedList<LinkedList<MyEntry>> buckets;

    private final int BUCKET_ARRAY_SIZE = 16;

    public MyHashMapWithMyImplementedList() {
        // TODO
    }

    public String get(String key) {
        // TODO
        return null;
    }

    public void put(String key, String value) {
        // TODO
    }

    public Set<String> keySet() {
        // TODO
        return null;
    }

    public Collection<String> values() {
        // TODO
        return null;
    }

    public String remove(String key) {
        // TODO Returns the value associated with the key removed from the map or null if the key wasn't found
        return null;
    }

    public boolean containsKey(String key) {
        // TODO
        return false;
    }

    public boolean containsValue(String value) {
        // TODO
        return false;
    }

    public int size() {
        // TODO Return the number of the Entry objects stored in all the buckets
        return 0;
    }

    public void clear() {
        // TODO Remove all the Entry objects from the bucket list
    }

    public Set<MyHashMap.MyEntry> entrySet() {
        // TODO Return a Set containing all the Entry objects
        return null;
    }

    public boolean isEmpty() {
        // TODO
        return false;
    }

    public static class MyEntry {
        private String key;
        private String value;

        public MyEntry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
