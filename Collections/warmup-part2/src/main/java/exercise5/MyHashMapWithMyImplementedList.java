package exercise5;

import exercise4.MyHashMap;

import java.util.*;

/**
 * Create a HashMap that uses to store the buckets your implementation of MyImplementedList that you
 * created in the Collections I workshop.
 *
 * Created by Radu.Hoaghe on 7/6/2017.
 */


public class MyHashMapWithMyImplementedList {

    // TODO uncomment the following line and add your MyImplementedList implementation to the project
    private MyImplementedList<LinkedList<MyEntry>> buckets;

    private final int BUCKET_ARRAY_SIZE = 10;

    public MyHashMapWithMyImplementedList() {
        // TODO
        buckets = new MyImplementedList<LinkedList<MyEntry>>();
        for (int i = 0; i < 10; i++)
            buckets.add(new LinkedList<MyEntry>());
    }

    public String get(String key) {
        // TODO
        if (key == null) return null;
        for (MyEntry entry : buckets.get(Math.abs(key.hashCode() % BUCKET_ARRAY_SIZE))) {
            if (entry.getKey().equals(key))
                return entry.getValue();
        }
        return null;
    }

    public void put(String key, String value) {
        // TODO
        if (key == null) {
            for (MyEntry entry : buckets.get(0)) {
                if (entry.getKey() == null) {
                    entry.setValue(value);
                    return;
                }
            }
            buckets.get(0).add(new MyEntry(key, value));
            return;
        }
        for (MyEntry entry : buckets.get(Math.abs(key.hashCode() % BUCKET_ARRAY_SIZE))) {
            if (entry.getKey().equals(key)) {
                String retVal = entry.getValue();
                entry.setValue(value);
                return;
            }
        }
        buckets.get(Math.abs(key.hashCode() % BUCKET_ARRAY_SIZE)).add(new MyEntry(key, value));
    }

    public Set<String> keySet() {
        // TODO
        Set<String> mySet = new HashSet<String>();

        Iterator<LinkedList<MyEntry>> it = buckets.iterator();

        while(it.hasNext()){
            LinkedList<MyEntry> bucket = it.next();
            for (MyEntry entry : bucket) {
                mySet.add(entry.getKey());
            }
        }

        return mySet;
    }

    public Collection<String> values() {
        // TODO
        ArrayList<String> myCollection = new ArrayList<String>();
        Iterator<LinkedList<MyEntry>> it = buckets.iterator();

        while(it.hasNext()){
            LinkedList<MyEntry> bucket = it.next();
            for (MyEntry entry : bucket) {
                myCollection.add(entry.getValue());
            }
        }


        return myCollection;
    }

    public String remove(String key) {
        // TODO Returns the value associated with the key removed from the map or null if the key wasn't found
        if (key == null) {
            Iterator<MyEntry> it = buckets.get(0).iterator();
            while (it.hasNext()) {
                MyEntry entry = it.next();
                if(entry.getKey() == null) {
                    String retVal = entry.getValue();
                    it.remove();
                    return retVal;
                }
            }
        } else {
            Iterator<MyEntry> it = buckets.get(Math.abs(key.hashCode()%BUCKET_ARRAY_SIZE)).iterator();
            while(it.hasNext()){
                MyEntry entry = it.next();
                if(entry.getKey().equals(key)){
                    String retVal = entry.getValue();
                    it.remove();
                    return retVal;
                }
            }
        }
        return null;
    }

    public boolean containsKey(String key) {
        // TODO
        for (MyEntry entry : buckets.get(Math.abs(key.hashCode() % BUCKET_ARRAY_SIZE))) {
            if (entry.getKey().equals(key))
                return true;
        }
        return false;
    }

    public boolean containsValue(String value) {
        // TODO
        for (LinkedList<MyEntry> each : buckets) {
            for (MyEntry entry : each) {
                if (entry.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int size() {
        // TODO Return the number of the Entry objects stored in all the buckets
        int count = 0;
        for (LinkedList<MyEntry> each : buckets) {
            for (MyEntry entry : each) {
                count++;
            }
        }
        return count;
    }

    public void clear() {
        // TODO Remove all the Entry objects from the bucket list
        for (LinkedList<MyEntry> each : buckets) {
            each.clear();
        }
    }

    public Set<MyEntry> entrySet() {
        // TODO Return a Set containing all the Entry objects
        Set<MyEntry> mySet = new HashSet<MyEntry>();
        for (LinkedList<MyEntry> each : buckets) {
            for (MyEntry entry : each) {
                mySet.add(entry);
            }
        }
        return mySet;
    }

    public boolean isEmpty() {
        // TODO
        if (size() == 0)
            return true;
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
