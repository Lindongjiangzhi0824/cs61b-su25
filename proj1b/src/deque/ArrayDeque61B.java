package deque;

import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    /* T can't be generic array, */
    private Object[] nums;
    /* Current number of elements */
    private int size;

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }

    private int head = 0;
    private int tail = 0;

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
        if (size < nums.length) {
            if (nums[head] != null) {
                head = Math.floorMod(head - 1, nums.length);
                nums[head] = x;
            } else {
                nums[head] = x;
            }
            size += 1;
        } else {
            System.out.println("Array is already full.");
        }
    }

    @Override
    public void addLast(T x) {
        if (size < nums.length) {
            if (nums[tail] != null) {
                tail = Math.floorMod(tail + 1, nums.length);
            }
            nums[tail] = x;
            size += 1;
        } else {
            System.out.println("Array is already full.");
        }
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        int cur = head;
        for (int i = 0; i < size; i++) {
            list.add((T) nums[cur]);
            cur = (cur + 1) % nums.length;
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (nums[getHead()] == null) {
            return null;
        }
        T returnVal = (T) nums[getHead()];
        nums[head] = null;
        head = Math.floorMod(head + 1, nums.length);
        size -= 1;
        return returnVal;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        /* Need type check. */
        int length = nums.length;
        if (index > length - 1) {
            return null;
        }
        return (T) nums[index];
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }


    public Object[] getNums() {
        return nums;
    }
}
