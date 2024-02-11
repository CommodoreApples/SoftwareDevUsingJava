package PA4;

public interface MyList<E> {
    E get(int index);
    
    E getFirst();
    E getLast();
    void addFirst(E e);
    void addLast(E e);
    void add(int index, E e);
    E removeFirst();
    E removeLast();
    E remove(int index);
    E set(int index, E e);
    int size();
    int indexOf(Object e);
    int lastIndexOf(E e);
}
