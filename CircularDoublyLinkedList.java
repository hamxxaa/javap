import java.util.function.Consumer;

class CircularDoublyLinkedList {
    static Node head;

    // Doubly linked list node definition
    static class Node {
        Player data;
        Node next;
        Node prev;
    };

    // Function to insert node in the list
    public void addNode(Player value) {
        // List is empty so create a single node furst
        if (head == null) {
            Node new_node = new Node();
            new_node.data = value;
            new_node.next = new_node.prev = new_node;
            head = new_node;
            return;
        }

        // find last node in the list if list is not empty
        Node last = (head).prev; // previous of head is the last node

        // create a new node
        Node new_node = new Node();
        new_node.data = value;

        // next of new_node will point to head since list is circular
        new_node.next = head;

        // similarly previous of head will be new_node
        (head).prev = new_node;

        // change new_node=>prev to last
        new_node.prev = last;

        // Make new node next of old last
        last.next = new_node;
    }

    /*
     * Method to aplly a function to every element of the list
     * 
     * @param action The function to apply to each element
     */
    public void forEach(Consumer<Player> action) {
        if (head == null)
            return;

        Node current = head;
        do {
            action.accept(current.data);
            current = current.next;
        } while (current != head);
    }

    /*
     * Method to get the node containing the given value
     * 
     * @param value The value to search for
     * 
     * @return The node containing the value, null if not found
     */
    public Node get(Player value) {
        if (head == null)
            return null;

        Node current = head;
        do {
            if (current.data.equals(value)) {
                return current;
            }
            current = current.next;
        } while (current != head);

        return null; // Value not found
    }

    public Player getRandromPlayer() {
        if (head == null)
            return null;

        Node current = head;
        int rand = (int) (Math.random() * 10) % 4;
        for (int i = 0; i < rand; i++) {
            current = current.next;
        }
        return current.data;
    }

    // Method to reverse the list
    public void reverse() {
        Node temp = null;
        Node current = head;
        do {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        } while (current != head);
    }

    public Node getHead() {
        return head;
    }
}