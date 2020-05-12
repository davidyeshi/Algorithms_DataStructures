package data_structures;

import java.util.Iterator;

public class DoublyLinkedList<T> implements Iterable<T> {

    // == fields ==
    private int size = 0;
    private Node <T> head = null;
    private Node <T> tail = null;

    // getter methods
    public int size() {
        return size;
    }

    public Node<T> head() {
        return head;
    }

    public Node<T> tail() {
        return tail;
    }

    // Internal node class to represent data
    private class Node <T> {
        // == Node fields ==
        T data;
        Node <T> prev, next;

        // == Node constructor ==
        public Node(T data, Node <T> prev, Node <T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    // Empty this linked list, O(n)
    public void clear() {
        Node <T> traverse = head;
        while(traverse != null) {
            Node<T> next = traverse.next;
            traverse.prev = traverse.next = null;
            traverse.data = null;
            traverse = next;
        }
        head = tail = traverse = null;
        size = 0;
    }

    // Is this linked list empty
    public boolean isEmpty() {
        return size() == 0;
    }

    // Add an element to the tail of the linked list, O(1)
    public void add(T elem) {
        addLast(elem);
    }

    public void addLast(T elem) {

        // The linked list is empty
        if (isEmpty()) {
            head = tail = new Node<T> (elem, null, null);
        } else {
            tail.next = new Node<T>(elem, tail, null);
            tail = tail.next;
        }

        size++;
    }

    // And an element to the beginning of the linked list
    public void addFirst(T elem) {

        // The linked list is empty
        if (isEmpty()) {
            head = tail = new Node<T> (elem, null, null);
        } else {
            head.prev = new Node<T>(elem, null, head);
            head = head.prev;
        }

        size++;
    }

    // Check the value of the first node if it exists
    public T peekFirst() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        return head.data;
    }

    // Check the value of the last node if it exists
    public T peekLast() {
        if (isEmpty()) throw new RuntimeException("Empty List");
        return tail.data;
    }

    // Remove the first value at the head of the linked list
    public T removeFirst() {

        // Can't remove data from an empty list so throw exception
        if (isEmpty()) throw new RuntimeException("Empty List");

        T data = head.data;
        head = head.next;
        -- size;

        // If the list is empty set the tail to null as well
        if (isEmpty()) tail = null;

        // Do a memory clean of the previous node
        else head.prev = null;

        // Return the data that was at the first node we just removed
        return data;
    }

    // Remove the last value at the tail of the linked list
    public T removeLast() {

        // Can't remove data from an empty list so throw exception
        if (isEmpty()) throw new RuntimeException("Empty List");

        T data = tail.data;
        tail = tail.prev;
        --size;

        // If the list is empty set the head to null as well
        if (isEmpty()) head = null;

        // Do a memory clean of the next node
        else tail.next = null;

        // Return the data that was at the last node we just removed
        return data;
    }

    // Remove an arbitrary node from the linked list
    public T remove(Node<T> node) {

        // If the node to remove is somewhere either at the
        // head or the tail handle those independently
        if (node.prev == null) return removeFirst();
        if (node.next == null) return removeLast();

        // Make the pointers of the adjacent node skip over the node to be removed
        node.next.prev = node.prev;
        node.prev.next = node.next;

        // Temporary store the data we want to return
        T data = node.data;

        // Memory cleanup
        node.data = null;
        node = node.prev = node.next = null;

        --size;

        // Return the data at the node we just removed
        return data;
    }

    // Remove a node at a particular index
    public T removeAt(int index) {

        // Make sure the index provided is valid
        if (index <0 || index >= size) throw new IllegalArgumentException();

        int i;
        Node<T> traverse;

        // Select from the front of the list
        if (index < size/2) {
            for (i = 0, traverse = head; i != index; i++) {
                traverse = traverse.next;
            }
        } else {
            // Search from the back of the list
            for (i = size-1, traverse = tail; i != index; i--) {
                traverse = traverse.prev;
            }
        }

        return remove(traverse);
    }

    // Remove a particular value in the linked list
    public boolean remove(Object object) {

        Node <T> traverse = head;

        // Support searching for null
        if (object == null) {
            for (traverse = head; traverse != null; traverse = traverse.next) {
                if (traverse.data == null) {
                    remove(traverse);
                    return true;
                }
            }
        }
        // Search for non null object
        else {
            for (traverse = head; traverse != null; traverse = traverse.next) {
                if (object.equals(traverse.data)) {
                    remove(traverse);
                    return true;
                }
            }
        }

        // Value was not found
        return false;
    }

    // Find the index of a particular value in the linked list
    public int indexOf(Object object) {

        int index = 0;
        Node<T> traverse = head;

        // Support searching for null
        if (object == null) {
            for (traverse = head; traverse != null; traverse = traverse.next, index++) {
                if (traverse.data == null)
                    return index;
            }
        }
        // Search for non null object
        else {
            for (traverse = head; traverse != null; traverse = traverse.next, index++) {
                if (object.equals(traverse.data))
                    return index;
            }
        }

        // Index not found
        return -1;
    }

    // Check if the value is contained within the linked list
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    // Implement iterator
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> traverse = head;
            public boolean hasNext() {
                return head.next != null;
            }

            public T next() {
                T data = traverse.data;
                traverse = traverse.next;
                return data;
            }

            public void remove() {
                Node<T> toRemove = traverse;
                traverse = traverse.next;
                DoublyLinkedList.this.remove(toRemove);
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node <T> traverse = head;
        while (traverse != null) {
            sb.append(traverse.data + ", ");
            traverse = traverse.next;
        }
        sb.append(" ]");
        return sb.toString();
    }
}
