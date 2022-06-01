package Assignment2;

public class MyLinkedListNode<ElementType> {
    private MyLinkedListNode<ElementType> next;
    private ElementType value;

    public MyLinkedListNode() {
        this.value = null;
        this.next = null;
    }
    public MyLinkedListNode(ElementType value) {
        this.value = value;
    }
    public MyLinkedListNode(ElementType value, MyLinkedListNode<ElementType> next) {
        this.value = value;
        this.next = next;
    }
    public ElementType getValue() {
        return value;
    }
    public void setValue(ElementType value) {
        this.value = value;
    }
    
    public MyLinkedListNode<ElementType> getNext() {
        return next;
    }
    public void setNext(MyLinkedListNode<ElementType> next) {
        this.next = next;
    }

//    @Override
    public boolean contains(ElementType checkValue) {
        MyLinkedListNode<ElementType> curr = this;
        while (curr != null) {
            if (curr.value.equals(checkValue)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }
    
    public String toString() { 
        return this.value.toString();
    }

    
}