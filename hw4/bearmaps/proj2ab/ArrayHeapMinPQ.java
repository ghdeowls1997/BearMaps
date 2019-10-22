package bearmaps.proj2ab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    ArrayList<Node<T>> minHeap;
    HashMap<T, Integer> storage;

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentException if item is already present. */
    public ArrayHeapMinPQ() {
        minHeap = new ArrayList<>();
        Node sentinel = new Node(63, 0.0);
        minHeap.add(sentinel);
        storage = new HashMap<>();
    }

    public static void main(String[] args) {
        ArrayHeapMinPQ<String> ex = new ArrayHeapMinPQ<>();
        ex.add("cat", 25.0);
        ex.add("rat", 1.0);
        ex.removeSmallest();
        ex.removeSmallest();
        ex.add("deer", 100.0);
        assertFalse(ex.contains("cat"));
        assertEquals(ex.getSmallest(), "deer");
        //ex.removeSmallest();
        /** Array cleared up to default */

        /** Test for exceptions derived from add operations */
        ex.add("cat", 45.0);
        ex.add("elephant", 500.0);
        ex.add("torquiose", 105.0);
        ex.add("rabbit", 30.0);
        ex.add("rat", 5.0);
        ex.add("squirrel", 15.0);
        ex.add("t-rex", 100000.0);
        //ex.add("bacteria", 1.0);
        //adding an item with the same priority
        ex.add("turtle", 105.0);
        ex.changePriority("turtle", 100.0);
        System.out.println(ex.minHeap.get(9).item);
        System.out.println(ex.minHeap.get(9).priority);

    }

    public void add(T item, double priority) {
        if (minHeap.size() == 1) {
            Node firstNode = new Node(item, priority);
            storage.put(item, 1);
            minHeap.add(firstNode);
        } else {
            if (contains(item)) {
                throw new IllegalArgumentException();
            }
            storage.put(item, minHeap.size());
            minHeap.add(new Node(item, priority));
            addHelper(minHeap.size() - 1);
        }
    }

    private void addHelper(int i) {
        while (i >= 2 && minHeap.get(i).priority < minHeap.get(i / 2).priority) {
            Collections.swap(minHeap, i / 2, i);
            storage.put(minHeap.get(i / 2).item, i / 2);
            storage.put(minHeap.get(i).item, i);
            i /= 2;
        }
    }

    /* Returns true if the PQ contains the given item. */
    public boolean contains(T item) {
        return storage.containsKey(item);
    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest() {
        if (minHeap.size() == 1) {
            throw new NoSuchElementException();
        }
        return minHeap.get(1).item;
    }

    private int smallerChild(int k) {
        if (k * 2 + 1 >= minHeap.size()
                || minHeap.get(k * 2).priority < minHeap.get(k * 2 + 1).priority) {
            return k * 2;
        }
        return k * 2 + 1;
    }

    private void removeHelper(int i) {
        Node<T> thingToSink = minHeap.get(i);
        while (2 * storage.get(thingToSink.item) < minHeap.size()
                && (minHeap.get(storage.get(thingToSink.item)).priority
                > minHeap.get(smallerChild(storage.get(thingToSink.item))).priority)) {
            int smallerIndex = smallerChild(storage.get(thingToSink.item));
            Collections.swap(minHeap, smallerIndex, storage.get(thingToSink.item));
            storage.put(minHeap.get(smallerIndex).item, smallerIndex);
            storage.put(minHeap.get(storage.get(thingToSink.item) / 2).item,
                    storage.get(thingToSink.item) / 2);
        }
    }

    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest() {
        if (minHeap.size() == 1) {
            throw new NoSuchElementException();
        }
        if (minHeap.size() == 2) {
            T returnItem = minHeap.get(1).item;
            storage.clear();
            minHeap.remove(minHeap.size() - 1);
            return returnItem;
        }
        T returnItem = minHeap.get(1).item;
        Collections.swap(minHeap, 1, minHeap.size() - 1);
        storage.remove(minHeap.get(minHeap.size() - 1).item);
        minHeap.remove(minHeap.size() - 1);
        storage.put(minHeap.get(1).item, 1);
        removeHelper(1);
        return returnItem;
    }

    /* Returns the number of items in the PQ. */
    public int size() {
        return minHeap.size() - 1;
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority) {
        if (!storage.containsKey(item)) {
            throw new NoSuchElementException();
        }
        Node<T> current = minHeap.get(storage.get(item));
        current.priority = priority;
        Node<T> parent = minHeap.get(storage.get(item) / 2);
        if (parent.priority > current.priority) {
            addHelper(storage.get(item));
        } else {
            removeHelper(storage.get(item));
        }
    }

    private class Node<T> {
        T item;
        double priority;

        Node(T k, double num) {
            item = k;
            priority = num;
        }
    }
}
