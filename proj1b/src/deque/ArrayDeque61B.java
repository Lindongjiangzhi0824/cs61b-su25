package deque;

import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T>{
    /* T can't be generic array, */
    private Object[] nums;
    /* Current number of elements */
    private int size;

    public ArrayDeque61B() {
        nums = new Object[8];
        this.size = 0;
    }

    /* Parameters Constructor */
    public ArrayDeque61B(int capacity) {
        nums = new Object[capacity];
        this.size = 0;
    }
    @Override
    public void addFirst(T x) {

    }

    @Override
    public void addLast(T x) {

    }

    @Override
    public List<T> toList() {
        return List.of();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }

    public int getSize() {
        return size;
    }

    public Object[] getNums() {
        return nums;
    }
}
