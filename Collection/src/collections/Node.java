package collections;

public class Node<E> {
    private E element;
    private Node nextNode;

    public Node(E element) {
        this.element = element;
    }

    public Node(E element, Node<E> nextNode) {
        this.element = element;
        this.nextNode = nextNode;
    }

    public E getElement() {
        return element;
    }

    public Node<E> getNextNode() {
        return nextNode;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public void setNextNode(Node<E> nextNode) {
        this.nextNode = nextNode;
    }

    @Override
    public String toString() {
        return " " + element;
    }
}
