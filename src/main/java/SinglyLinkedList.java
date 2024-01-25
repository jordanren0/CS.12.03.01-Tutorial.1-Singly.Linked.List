
public class SinglyLinkedList<T> {

    // Inner Node class.
    public class Node<T> {
        // Properties of the Node class.
        // The two properties should be:
        // 1. data (the data stored in the node).
        // 2. next (a reference (also known as a pointer) to the next node.


        // Constructor of the Node class.
        // The constructor should set the data property of the Node to be the value passed as a parameter.
        // The constructor should set the next property of the Node to be null.
        private T data;
        private Node<T> next;

        public Node(T data){
            this.data = data;
            this.next = null;
        }
        public T getData() {
            return data;
        }

        public Node<T> getNext() {
            return next;
        }
        public void setData(T data) {
            this.data = data;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    // Properties of the Singly Linked List class.
    // The three properties should be:
    // 1. size (records the number of nodes in our Singly Linked List)
    // 2. head (a reference to the first (also known as the head) node in our Singly Linked List).
    // 3. tail (a reference to the last (also known as the tail) node in our Singly Linked List.


    // Constructor.
    // Creates a Singly Linked List with a head node.

    private int size;
    private Node<T> head;
    private Node<T> tail;

    public SinglyLinkedList(T value) {
        head = new Node<>(value);
        tail = head;
        size = 1;

    }

    // Methods

    // size
    // returns the size of the Singly Linked List.
    public int size() {
        return size;
    }

    // isEmpty
    // returns whether the Singly Linked List is empty.
    public boolean isEmpty() {
        return size == 0;
    }

    // peekFirst
    // returns the data stored in the head node.
    // throws a run time exception if the Singly Linked List is empty.
    public T peekFirst() {
        return head.getData();
    }

    // peekLast
    // returns the data stored in the tail node.
    // throws a run time exception if the Singly Linked List is empty.
    public T peekLast() {
        return tail.getData();
    }

    // addFirst
    // Adds a node to the front of the Singly Linked List.
    // If the Singly Linked List is empty,
    public void addFirst(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head = newNode;
        }
        size++;
    }

    // addLast
    // Adds a node to the back of the Singly Linked List.
    public void addLast(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    // insertAt
    // Inserts a node at a specific index.
    // If the index is equal to 0, then we can invoke the addFirst method.
    // If the index is equal to size, then we can invoke the addLast method.
    // throws an illegal argument exception if the index is invalid.
    public void insert(T value, int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }
        if (index == 0) {
            addFirst(value);
            return;
        }
        if (index == size) {
            addLast(value);
            return;
        }
        Node<T> current = head;
        for (int i = 1; i < index; i++) {
            current = current.getNext();
        }
        Node<T> newNode = new Node<>(value);
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        size++;
    }

    // removeFirst
    // Removes the first (also known as the head node) from the Singly Linked List.
    // Throws a run time exception if the Singly Linked List is empty.
    // Returns the data stored in the head node.
    // If the size of the Singly Linked List becomes 0, need to set the tail to null.
    public T removeFirst() {
        T data = head.getData();
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
        }
        size--;
        return data;
    }

    // removeLast
    // Removes the last (also known as the tail node) from the Singly Linked List.
    // Throws a run time exception if the Singly Linked List is empty.
    // Returns the data stored in the tail node.
    // If the size of the Singly Linked List becomes 0, need to set the tail to null.
    public T removeLast() {
        if (size == 1) {
            T data = head.getData();
            head = null;
            tail = null;
            size--;
            return data;
        }
        Node<T> current = head;
        for (int i = 1; i < size - 1; i++) {
            current = current.getNext();
        }
        T data = current.getNext().getData();
        tail = current;
        tail.setNext(null);
        size--;
        return data;

    }

    // removeAt
    // Removes a node at a specific index.
    // Returns the data stored in the removed node.
    // If the index is equal to 0, then we can invoke the removeFirst method.
    // If the index is equal to size-1, then we can invoke the removeLast method.
    // throws an illegal argument exception if the index is invalid.

    public T removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }
        Node<T> current = head;
        for (int i = 1; i < index; i++) {
            current = current.getNext();
        }
        T data = current.getNext().getData();
        current.setNext(current.getNext().getNext());
        size--;
        return data;

    }

    // contains
    // Determines whether the Singly Linked List contains a node that holds data equivalent to the value passed.
    // Returns a boolean.
    public boolean contains(T value) {
        Node<T> current = head;
        while (current != null) {
            if (current.getData().equals(value)) {
                return true;
            }
            current = current.getNext();
        }
        return false;

    }

    // valueAt
    // Returns the data held in the node at a specified index.
    // Throws an illegal argument exception if the index is invalid.
    public T valueAt(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    // reverse
    // Reverses the Singly Linked List.
    public void reverse() {
        if (isEmpty() || size == 1) {
            return;
        }
        Node<T> current = head;
        Node<T> previous = null;
        Node<T> next;
        while (current != null) {
            next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }
        head = previous;
    }

    // toString
    // Returns a String representation of the Singly Linked List.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        if(size == 1){
            sb.append(current.getData());
            sb.append(" -> null");
            return sb.toString();
        }
        while (current != null) {
                sb.append(current.getData());
                if (current.getNext() != null) {
                    sb.append(" -> ");
                }
                current = current.getNext();
        }
        sb.append(" -> null");
        return sb.toString();
    }

}
