package deque;

import java.util.*;

public class ArrayDeque61B<T> implements Deque61B<T> {
    /* T can't be generic array, */
    private Object[] nums;
    /* Current number of elements */
    private int size;
    private int capacity = 8;

    public double getExpansionLimit() {
        return EXPANSION_THRESHOLD;
    }

    public double getReductionLimit() {
        return SHRINK_THRESHOLD;
    }

    public int getCapacity() {
        return capacity;
    }

    private static final double EXPANSION_THRESHOLD = 0.75;
    private static final double SHRINK_THRESHOLD = 0.25;


    /** 缩容后的最小容量。 */
    private static final int MIN_CAPACITY = 16;
    private static final int MIN_SHRINK_CAPACITY = 32;

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }

    private int head = 0;
    private int tail = 0;

    public ArrayDeque61B() {
        nums = new Object[capacity];
        this.size = 0;
    }

    /* Parameters Constructor */
    public ArrayDeque61B(int capacity) {
        nums = new Object[capacity];
        this.capacity = capacity;
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
            if (capacity >= MIN_CAPACITY && size >= EXPANSION_THRESHOLD * capacity) {
                resizeUp();
            }
            if (capacity >= MIN_SHRINK_CAPACITY && size <= capacity * SHRINK_THRESHOLD) {
                resizeDown();
            }
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
            if (capacity >= MIN_CAPACITY && size >= EXPANSION_THRESHOLD * capacity) {
                resizeUp();
            }
            if (capacity >= MIN_SHRINK_CAPACITY && size <= capacity * SHRINK_THRESHOLD) {
                resizeDown();
            }
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
        if (capacity >= MIN_SHRINK_CAPACITY && size <= capacity * SHRINK_THRESHOLD) {
            resizeDown();
        }
        return returnVal;
    }

    @Override
    public T removeLast() {
        if (size() == 0 || nums[getTail()] == null) {
            return null;
        }
        T returnVal = (T) nums[getTail()];
        nums[tail] = null;
        tail = Math.floorMod(tail - 1, nums.length);
        size -= 1;
        if (capacity >= MIN_SHRINK_CAPACITY && size <= capacity * SHRINK_THRESHOLD) {
            resizeDown();
        }
        return returnVal;
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
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }


    public Object[] getNums() {
        return nums;
    }

    public void resizeUp() {
        /* When size great or equal 0.75 * capacity,
        * (require base capacity >= 16)
        * capacity = capacity * 2
        * */
        Object[] newNums = new Object[this.capacity * 2];
        int headTemporary = getHead();
        int tailTemporary = getTail();
        if (getHead() <= getTail()) {
            /* Remove the element to their respective position. */
            /* eg.
             before :0 -> 1 -> ... -> 5 -> null -> null ->.....-> null(15)
             after : 0 -> 1 -> ... -> 5 -> null -> null ->.....-> null(31)
             */
            while (headTemporary <= tailTemporary) {
                newNums[headTemporary] = nums[headTemporary];
                headTemporary += 1;
            }
        } else {
            /* Reset temporary variables */
            headTemporary = getHead();
            tailTemporary = getTail();
            /*
            *  1. Deal with the range from Head to End
            *  same -> ....-> head -> ... -> End(15)
            *  same -> ....-> head -> ... -> End(31)
            *
            *  every range elements the distance from head to End is same.
            *  so lengthBefore - position(head_old) = lengthAfter - position(head_new)
            *  position(head_new) = lengthAfter - lengthBefore + position(head_old)
            * */
            for (int i = headTemporary; i < capacity; i++) {
                int curNewPostion = newNums.length - nums.length + i;
                newNums[curNewPostion] = nums[i];
            }
            head = newNums.length - nums.length + headTemporary;
            /* 2. Deal with the range from Start to Tail */
            /*
            * Range from start to Tail is same
            * Before : 1 -> 2 -> 3 -> .... Tail -> ....
            * After  : 1 -> 2 -> 3 -> .... Tail -> ....
            * */
            for (int i = 0; i <= tailTemporary; i++) {
                newNums[i] = nums[i];
            }
        }
        nums = newNums;
        capacity = capacity * 2;
    }

    public void resizeDown() {
        /* The capacity utilization rate is less than 25%;
         * (require base capacity >= 32， the smallest capacity is 16.)
         * the capacity has been halved. */
        int newCapacity = capacity / 2;
        Object[] newNums = new Object[newCapacity];
        int headTemporary = getHead();
        int tailTemporary = getTail();
        if (getHead() <= getTail()) {
            /* Remove the element to their respective position. */
            /* eg.
             before :0 -> 1 -> ... -> 5 -> null -> null ->.....-> null(15)
             after : 0 -> 1 -> ... -> 5 -> null -> null ->.....-> null(31)
             */
            if (tailTemporary < newCapacity) { // left half of the interval
                for (int i = headTemporary; i <= tailTemporary; i++) {
                    newNums[i] = nums[i];
                }
            } else if (headTemporary >= newCapacity) { // right half of the interval
                for (int i = headTemporary; i <= tailTemporary; i++) {
                    newNums[i - newCapacity] = nums[i];
                }
                head = head - newCapacity;
                tail = tail - newCapacity;
            } else { // [head, mid, tail]
                /*
                   * [head, mid) Don't move
                   * [mid,tail] -> [start, tail]
                   * */
                for (int i = headTemporary; i < newCapacity; i++) {
                    newNums[i] = nums[i];
                }
                for (int i = newCapacity; i <= tailTemporary; i++) {
                    newNums[i - newCapacity] = nums[i];
                }
                // head not change ; tail (tailTempory -> (tailTempory - newCapacity))
                tail = tailTemporary - newCapacity;
            }
        } else {
            /* Reset temporary variables */
            headTemporary = getHead();
            tailTemporary = getTail();
            /*
             *  1. Deal with the range from Head to End
             *  same -> ....-> head -> ... -> End(31)
             * *  same -> ....-> head -> ... -> End(15)
             *
             *  every range elements the distance from head to End is same.
             *  so lengthBefore - position(head_old) = lengthAfter - position(head_new)
             *  position(head_new) = lengthAfter - lengthBefore + position(head_old)
             * */
            for (int i = headTemporary; i < capacity; i++) {
                int curNewPostion = newNums.length - nums.length + i;
                newNums[curNewPostion] = nums[i];
            }
            head = newNums.length - nums.length + headTemporary;
            /* 2. Deal with the range from Start to Tail */
            /*
             * Range from start to Tail is same
             * Before : 1 -> 2 -> 3 -> .... Tail -> ....
             * After  : 1 -> 2 -> 3 -> .... Tail -> ....
             * */
            for (int i = 0; i <= tailTemporary; i++) {
                newNums[i] = nums[i];
            }
        }
        nums = newNums;
        capacity = capacity / 2;


    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int pos = 0; // from 0 to size-1
        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No next element.");
            }
            T val = (T) nums[(head + pos) % nums.length];
            pos += 1;
            return val;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ArrayDeque61B<?> other)) return false;
        if (this.size != other.size) return false;

        Iterator<T> it1 = this.iterator();
        /* Can't assert the it2's type . */
        Iterator<?> it2 = other.iterator();

        while (it1.hasNext() && it2.hasNext()) {
            if (!(Objects.equals(it1.next(), it2.next()))) {
                return false;
            }
        }
        return true;
    }
}
