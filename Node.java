public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    private T payload;
    private Node<T> next;
    private Node<T> previous;

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
        return next;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public T getPayload() {
        return payload;
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
        if (this.payload == null && other.payload == null) {
            return 0;
        } else if (this.payload == null) {
            return -1;
        } else if (other.payload == null) {
            return 1;
        } else {
            return this.payload.compareTo(other.payload);
        }
    }

    @Override
    public String toString() {
        return (payload != null) ? payload.toString() : "null";
    }
}
