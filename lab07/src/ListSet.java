import java.util.ArrayList;
import java.util.List;

/**
 * Represents a set of ints. A simple implementation of a set using a list.
 */
public class ListSet implements SimpleSet {

    List<Integer> elems;

    public ListSet() {
        elems = new ArrayList<Integer>();
    }

    /** Adds k to the set. */
    @Override
    public void add(int k) {
        if (!elems.contains(k)) {
            elems.add(k);
        } else {
            System.out.println(k + " already exists !");
        }
    }

    /** Removes k from the set. */
    @Override
    public void remove(int k) {
        Integer toRemove = k;
        if (contains(toRemove)) {
            elems.remove(toRemove);
        } else {
            System.out.println("Elems not exist " + k + " .");
        }
        // The reason is beyond the scope of this lab, but involves
        // method resolution.
    }

    /** Return true if k is in this set, false otherwise. */
    @Override
    public boolean contains(int k) {
        for (int x : elems) {
            if (k == x) {
                return true;
            }
        }
        return false;
    }

    /** Return true if this set is empty, false otherwise. */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /** Returns the number of items in the set. */
    @Override
    public int size() {
        return elems.size();
    }

    /** Returns an array containing all of the elements in this collection. */
    @Override
    public int[] toIntArray() {
        int[] arr = new int[elems.size()];
        int index = 0;
        for (int x : elems) {
            arr[index++] = x;
        }
        return arr;
    }
}
