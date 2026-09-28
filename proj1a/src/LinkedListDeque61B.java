import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private class Node {
        // 内部类复用外部类的泛型
        T val;
        Node prev;
        Node next;

        Node() {

        }
        Node(T val) {
            this.val = val;
        }
    }

    private Node sentinel;
    private int size;

    @Override
    public void addFirst(T x) {
        Node node = new Node(x);
        Node temp = sentinel.next;
        sentinel.next = node;
        node.prev = sentinel;
        temp.prev = node;
        node.next = temp;

        setSize(size + 1);
    }

    @Override
    public void addLast(T x) {
        Node oldLast = sentinel.prev;
        Node node = new Node(x);

        oldLast.next = node;
        node.prev = oldLast;
        node.next = sentinel;
        sentinel.prev = node;
        setSize(size + 1);
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node temp = sentinel.next;
        while (temp != sentinel) {
            returnList.add(temp.val);
            temp = temp.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public int size() {
        return getSize();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node oldFirst = sentinel.next;
        Node newFirst = oldFirst.next;

        sentinel.next = newFirst;
        newFirst.prev = sentinel;

        setSize(getSize() - 1);
        return oldFirst.val;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        /* Find last node.*/
        Node lastNode = sentinel.prev;
        Node newLastNode = lastNode.prev;
        /* newLastNode has been last node.*/
        newLastNode.next = sentinel;
        sentinel.prev = newLastNode;

        setSize(getSize() - 1);
        return newLastNode.val;
    }

    @Override
    public T get(int index) {
        if (index > getSize() - 1) {
            return null;
        }
        Node temp = sentinel.next;
        while (index > 0) {
            temp = temp.next;
            index -= 1;
        }
        return temp.val;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index > getSize() - 1) {
            return null;
        }
        Node temp = sentinel.next;
        return getRecursiveHelper(temp, index);
    }

    private T getRecursiveHelper(Node node, int index) {
        if (index <= 0) {
            return node.val;
        }
        return getRecursiveHelper(node.next, index - 1);
    }
    public LinkedListDeque61B() {
        sentinel = new Node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;

        setSize(0);
    }
}
