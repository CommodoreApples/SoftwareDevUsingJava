package PA4;

public class test2 {
    public static void main(String[] args) {

        MyList<Integer> myList = new TwoWayLinkedList<>();

        myList.add(0, 10);
        myList.add(1, 20); 
        myList.add(2, 30); 

        System.out.println("First element: " + myList.getFirst());

        System.out.println("Last element: " + myList.getLast());

        myList.remove(1);

        System.out.println("Size of list: " + myList.size());
    }
}

