package LinkedList;
//Ben Rhee
//bpr210000

 /*
    This sort function is implemented using Insertion Sort
    Iterate over the array and compare the current element (key) to its predecessor.
    If the key element is smaller than its predecessor, compare it to the elements before. 
    Move the greater elements one position up to make space for the swapped element.
    
     */
public class LinkedList<T extends Comparable<T>> {
    private Node<T> head;
    private Node<T> tail;

    // Constructors
    public LinkedList(){
        this.head = null;
        this.tail = null;
    }

    public LinkedList(Node<T> node){
        this.head = node;
        this.tail = node;
    }

    // Accessors
    public Node<T> getHead(){
        return this.head;
    }

    public Node<T> getTail(){
        return this.tail;
    }

    // Mutators
    public void setHead(Node<T> node){
        head = node;
    }

    public void setTail(Node<T> node){
        tail = node;
    }

    // Add Method
    public void add(Node<T> node){
        if (tail != null) {
            tail.setNext(node);
            node.setPrevious(tail);
            tail = node;
        } else {
            head = node;
            tail = node;
        }
    }

    // toString Method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        while (current != null) {
            sb.append(current.getPayload().toString());
            sb.append(System.lineSeparator());
            current = current.getNext();
        }
        return sb.toString();
    }
    

    /*
    This sort function is implemented using Insertion Sort
    Iterate over the array and compare the current element (key) to its predecessor.
    If the key element is smaller than its predecessor, compare it to the elements before. 
    Move the greater elements one position up to make space for the swapped element.


     */
    public void sort() {
        if (head == null || head.getNext() == null) {
            return; // List is already sorted or empty
        }

        boolean swapped;
        do {
            swapped = false;
            Node<T> current = head;
            Node<T> previous = null;

            while (current != null && current.getNext() != null) {
                Node<T> nextNode = current.getNext();
                if (current.getPayload().compareTo(nextNode.getPayload()) > 0) {
                    if (previous != null) {
                        if (previous == this.tail){
                            this.tail = nextNode;
                        }
                        previous.setNext(nextNode);
                    } else {
                        head = nextNode;
                    }

                    current.setNext(nextNode.getNext());
                    nextNode.setNext(current);

                    if (current == this.tail) {
                        this.tail = nextNode;
                    }

                    swapped = true;
                }

                previous = current;
                current = nextNode;
            }
        } while (swapped);

        Node<T> curr = this.head;
        while(curr != null){
            if (curr.getNext() == null){
                this.tail = curr;
            }
            curr = curr.getNext();
        }
    }

}