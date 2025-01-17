package timemanager.data.dto.ds;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class DLL<E> implements Iterable<E> {
    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> prev;
        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }
        public E getElement() { return element; }
        public Node<E> getPrev() { return prev; }
        public Node<E> getNext() { return next; }
        public void setPrev(Node<E> p) { prev = p; }
        public void setNext(Node<E> n) { next = n; }
    }

    private Node<E> header;
    private Node<E> tailer;
    private int size;

    public DLL() {
        header = new Node<>(null, null, null);
        tailer = new Node<>(null, header, null);
        header.setNext(tailer);
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void addBetween(E e, Node<E> prevNode, Node<E> nextNode) {
        Node<E> newNode = new Node<>(e, prevNode, nextNode);
        prevNode.setNext(newNode);
        nextNode.setPrev(newNode);
        size++;
    }

    private E remove(Node<E> node) {
        Node<E> prevNode = node.getPrev();
        Node<E> nextNode = node.getNext();

        prevNode.setNext(nextNode);
        nextNode.setPrev(prevNode);
        size--;

        return node.getElement();
    }

    public void addFirst(E e) {
        addBetween(e, header, header.getNext());
    }

    public E removeLast() {
        return remove(tailer.getPrev());
    }

    @Override
    public Iterator<E> iterator() {
        return new DLLIterator();
    }

    private class DLLIterator implements Iterator<E> {
        private Node<E> current;

        public DLLIterator() {
            current = header.getNext();
        }

        @Override
        public boolean hasNext() {
            return current != tailer;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = current.getElement();
            current = current.getNext();
            return element;
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (E e : this) {
            sb.append(e);
            sb.append(", ");
        }
        sb.append(" ]");
        return sb.toString();
    }
}
