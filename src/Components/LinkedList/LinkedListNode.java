package Components.LinkedList;

import Components.Page;

public class LinkedListNode{

    private LinkedListNode next;

    private Page page;


    public LinkedListNode(int value){
        this.page = new Page(value);
    }
    public LinkedListNode getNext() {
        return next;
    }

    public void setNext(LinkedListNode next) {
        this.next = next;
    }

    public int getValue() {
        return page.getValue();
    }

}
