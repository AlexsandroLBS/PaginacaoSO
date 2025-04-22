package Components.LinkedList;

public class LinkedListNode{

    private LinkedListNode next;

    private int value;


    public LinkedListNode(int value){
        this.value = value;
    }
    public LinkedListNode getNext() {
        return next;
    }

    public void setNext(LinkedListNode next) {
        this.next = next;
    }

    public int getValue() {
        return value;
    }

}
