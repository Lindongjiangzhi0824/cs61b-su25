import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>{
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private class Node{
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

        setSize(size+1);
    }

    @Override
    public void addLast(T x) {
        Node temp = sentinel;
        while (temp.next != sentinel) {
            temp = temp.next;
        }
        Node node = new Node(x);
        temp.next = node;
        node.prev = temp;
        node.next = sentinel;
        setSize(size+1);
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node temp = sentinel.next;
        while(temp != sentinel) {
            returnList.add(temp.val);
            temp = temp.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return getSize();
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

    public LinkedListDeque61B() {
        sentinel = new Node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;

        setSize(0);
    }
}
