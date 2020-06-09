package data_structures.queue;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

public class Queue<T> implements Iterable<T> {
    private LinkedList<T> linkedList = new LinkedList<T>();

    // Create an empty queue
    public Queue() { }

    // Create a queue with a single element
    public Queue(T elem) {
        Enqueue(elem);
    }

    // Check if list is empty
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    // Get the size of the queue
    public int size() {
        return linkedList.size();
    }

    // Enqueue an element to the list
    public void Enqueue(T elem) {
        linkedList.addLast(elem);
    }

    // Dequeue an element
    public T Dequeue() {
        if (linkedList.isEmpty()) throw new RuntimeException("Empty Queue");
        return linkedList.removeFirst();
    }

    // Peek the first element
    public T Peek() {
        if (linkedList.isEmpty()) throw new RuntimeException("Empty Queue");
        return linkedList.peekFirst();
    }

    // Check if element exists
    public boolean Contains(T elem) {
        if (linkedList.isEmpty()) throw new RuntimeException("Empty Queue");
        return linkedList.contains(elem);
    }

    // Remove a certain element from the queue
    public boolean RemoveElem(T elem) {
        if (linkedList.isEmpty()) throw new RuntimeException("Empty Queue");
        return linkedList.remove(elem);
    }

    @Override
    public Iterator<T> iterator() {
        return linkedList.iterator();
    }
}
