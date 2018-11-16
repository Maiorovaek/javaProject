package collections;

import java.util.Iterator;

public class MyLinkedList<E> implements ILinkedList<E>  {
    private int size;
    private Node<E> first;
    private Node<E> last;

    @Override
    public void add(E element) {
        Node<E> Node1 = new Node<>(element);
        if(size==0){
            first = Node1;
            last = Node1;
            size = 1;
        } else{
            if(size ==1){
                last = Node1;
                first.setNextNode(Node1);
                size =2;
            } else {
                last.setNextNode(Node1);
                last = Node1;
                size++;
            }
        }

    }

    @Override
    public void add(int index, E element) {
        if(!(index>=0 && index<size) ){
            throw new IndexOutOfBoundsException();
        }
        if(index == size){
            add(element);

        }else{
            if(index ==0){
                Node<E> Node1 = new Node<E>(element, first);
                first = Node1;
                size++;
            }
            else{
                Node<E> prev = elNode(index-1);
                Node<E> Node1 = new Node<E>(element, prev.getNextNode());
                prev.setNextNode(Node1);
                size++;

            }
        }

    }
    private Node<E> elNode(int index) {
        if(index == 0) return first;
        Node<E> actual = first;
        for (int i = 0; i < index; i++) {
            actual = actual.getNextNode();
        }
        return actual;
    }

    @Override
    public void clear() {
        for(Node<E> x = first; x !=null;){
            Node<E> next = x.getNextNode();
            x.setElement(null);
            x.setNextNode(null);
            x = next;
        }

        size = 0;

    }

    @Override
    public E get(int index) {
        return elNode(index).getElement();
    }

    @Override
    public int indexOf(E element) {
        Node<E> temp = first;
        for(int i =0; i<size;i++){
            if(temp == null){
                return -1;
            }
            if(element == temp.getElement()){
                return i;
            }
            temp = temp.getNextNode();
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        if(!(index>=0 && index<size) ){
            throw new IndexOutOfBoundsException();
        }
        if(index ==0){
            E element = first.getElement();
            first = first.getNextNode();
            size--;
            return element;
        } else {
            Node<E> prev = elNode(index-1);
            E element = prev.getNextNode().getElement();
            prev.setNextNode(prev.getNextNode().getNextNode());
            size--;
            return element;
        }
    }

    @Override
    public E set(int index, E element) {
        if(!(index>=0 && index<size) ){
            throw new IndexOutOfBoundsException();
        }
        Node<E> n = elNode(index);
        E elLast = n.getElement();
        n.setElement(element);
        return elLast;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E[] toArray() {
        Object[] array = new Object[size];
        int i =0;
        for(Node<E> x = first; x != null; x = x.getNextNode()){
            array[i++] = x.getElement();}
            return (E[]) array;
    }

    @Override
    public Iterator iterator() {
        Iterator<E> iterator = new Iterator<E>() {
            private int actualIndex = -1;
            @Override
            public boolean hasNext() {
                actualIndex++;
                return actualIndex < size;
            }
            @Override
            public E next() {
                return elNode(actualIndex).getElement();
            }
        };
        return iterator;
    }

    @Override
    public String toString() {
      String str = "Node(" + first.toString();
        Node<E> tmp = first;
        for (int i = 0; i < size - 1; i++) {
            tmp = tmp.getNextNode();
            str += ", " + tmp.toString();
        }
        str += ")";
        return str;
    }

}