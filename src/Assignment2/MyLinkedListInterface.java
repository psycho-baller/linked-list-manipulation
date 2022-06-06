package Assignment2;

/**
 * class containing a static method/algorithm to calculate the nth fibonacci number using a memoized recursive method
 *
 * @author Rami Maalouf
 * @version 1.0
 * @date 2022-05-19
 * @TUT 05
 * @course CPSC 331
 */
interface MyLinkedListInterface <ElementType> {

    public class LinkedListValueExistsException extends Exception {
        public LinkedListValueExistsException(String errorMessage) {
            super(errorMessage);
        }
    }

    public class LinkedListListEmptyException extends Exception {
        public LinkedListListEmptyException(String errorMessage) {
            super(errorMessage);
        }
    }

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

        public String toString() { 
            return this.value.toString();
        }

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
    }
    
    public MyLinkedListNode<ElementType> getHeadNode();
    
    public void appendToTail(ElementType value) throws LinkedListValueExistsException;
    
    public void appendToTail(MyLinkedListNode<ElementType> node) throws LinkedListValueExistsException;

    public int length();

    public void deleteByValue(ElementType value) throws LinkedListListEmptyException;

    public MyLinkedListNode<ElementType> searchByValue(ElementType value) throws LinkedListListEmptyException;

    public String toString();
}
