package PA4;

public class TwoWayLinkedList<E> implements MyList<E> {
    private Node<E> head, tail;
    private int size = 0;

    public TwoWayLinkedList() {
    }

    public TwoWayLinkedList(E[] objects) {
        for (E object : objects)
            add(size, object);
    }

    @Override
    public E getFirst() {
        if (size == 0)
            return null;
        else
            return head.element;
    }

    @Override
    public E getLast() {
        if (size == 0)
            return null;
        else
            return tail.element;
    }

    @Override
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        if (head != null)
            head.previous = newNode;
        head = newNode;
        if (tail == null)
            tail = newNode;
        size++;
    }

    @Override
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);
        newNode.previous = tail;
        if (tail != null)
            tail.next = newNode;
        tail = newNode;
        if (head == null)
            head = newNode;
        size++;
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        } else if (index == 0) {
            addFirst(e);
        } else if (index == size) {
            addLast(e);
        } else {
            Node<E> current = getNode(index);
            Node<E> newNode = new Node<>(e);
            newNode.next = current;
            newNode.previous = current.previous;
            current.previous.next = newNode;
            current.previous = newNode;
            size++;
        }
    }

    @Override
    public E get(int index) {
    if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    Node<E> current = getNode(index);
    return current.element;
    }

    @Override
    public int indexOf(Object e) {
        int index = 0;
        if (e == null) {
            for (Node<E> x = head; x != null; x = x.next) {
                if (x.element == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = head; x != null; x = x.next) {
                if (e.equals(x.element))
                    return index;
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        int index = size;
        if (e == null) {
            for (Node<E> x = tail; x != null; x = x.previous) {
                index--;
                if (x.element == null)
                    return index;
            }
        } else {
            for (Node<E> x = tail; x != null; x = x.previous) {
                index--;
                if (e.equals(x.element))
                    return index;
            }
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> current = getNode(index);
            E element = current.element;
            current.previous.next = current.next;
            current.next.previous = current.previous;
            size--;
            return element;
        }
    }

    @Override
    public E set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<E> current = getNode(index);
        E oldValue = current.element;
        current.element = e;
        return oldValue;
    }

    @Override
    public int size() {
        return size;
    }

    // Implementations of other methods in your TwoWayLinkedList class

    // Other helper methods

    private Node<E> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        public Node(E element) {
            this.element = element;
        }
    }

    @Override
    public E removeFirst() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFirst'");
    }

    @Override
    public E removeLast() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeLast'");
    }
}