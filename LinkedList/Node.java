package LinkedList;
//Ben Rhee
//bpr210000
public class Node<T extends Comparable<T>>implements Comparable<Node<T>>  {
    public T payload;
    public Node<T> next;
    public Node<T> previous;

    // Default constructor
    public Node(){
        this.payload = null;
        this.next = null;
        this.previous = null;
    }

    // Overloaded constructor
    public Node(T payload){
        this.payload = payload;
        this.next = null;
        this.previous = null;
    }

    // Accessors
    public Node<T> getNext() {
        return this.next;
    }

    public Node<T> getPrevious() {
        return this.previous;
    }

    public T getPayload() {
        return this.payload;
    }

    // Mutators
    public void setNext(Node<T> next) {
        this.next = next;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    // Implement compareTo method for Comparable
    @Override
    public int compareTo(Node<T> other) {
        return this.payload.compareTo(other.getPayload());
    }

    @Override
    public String toString() {
        return (payload != null) ? payload.toString() : "null";
    }
}
