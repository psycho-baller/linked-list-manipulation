package Assignment2;


public class MyLinkedList<ElementType> implements MyLinkedListInterface<ElementType> {
    
    private MyLinkedListNode<ElementType> head;

    public MyLinkedList(){
         head = null;
    }

    /**
     * returns the first node of the linked list
     * Precondition: this is a linked list
     * Postcondition: The head node is returned, if the list is empty null is returned
     * instead
     * @return the first node of the linked list
     */
    public MyLinkedListNode<ElementType> getHeadNode() {
        return this.head;
//        return null;
    }

    /**
     * Appends a node to the end of this linked list given the value
     * Precondition: this is a linked list, and value is a variable with appropriate type for
     * this list, which is not already present in the list
     * Postcondition: The tail node of this list is a new node with node.value = value, the
     * length of the list is the previous length + 1, no other nodes have been changed.
     * If a node with the given value already exists, an exception is generated.
     * @param value the element to be appended to this list
     */
    public void appendToTail(ElementType value) throws LinkedListValueExistsException {
        if (this.getHeadNode() == null) { // if the linked list is empty
            this.head = new MyLinkedListNode<ElementType>(value); // create a new node with value
        } else if(this.getHeadNode().contains(value)) { // if the value is already a value for one of the nodes
            throw new LinkedListValueExistsException("Value already exists in the linked list");
        } else {
            MyLinkedListNode<ElementType> current = getHeadNode();
            while (current.getNext() != null) {
                current = current.getNext(); // traverse to the end of the linked list
            }
            current.setNext(new MyLinkedListNode<ElementType>(value)); // set the next node to the new node
        }
    }

    /**
     * Appends the specified node to the end of this linked list given a node
     * Precondition: this is a linked list, a node with appropriate type for this list such
     * that node.value is not already present in the list.
     * Postcondition: The tail node of this list is node, the length of the list is the previous
     * length + 1, no other nodes have been changed.
     * @param node the element to be appended to this list
     */
    public void appendToTail(MyLinkedListNode<ElementType> node) throws LinkedListValueExistsException {
        if (this.getHeadNode() == null) { // if the linked list is empty
            this.head= node; // set the head to the node(parameter)
        } else if(this.getHeadNode().contains(node.getValue())) { // if the node's(parameter) value already exists in the list
            throw new LinkedListValueExistsException("Value already exists in the linked list");
        } else { // if the linked list is not empty and the node's value is not already in the list
            MyLinkedListNode<ElementType> current = getHeadNode(); // head node
            while (current.getNext() != null) { // traverse to the end of the linked list
                current = current.getNext();
            }
            current.setNext(node); // set the next node to the node(parameter)
        }
    }

    /**
     * Precondition: this is a linked list
     * Postcondition: The number of nodes in this list is returned, 0 is returned if the list
     * is empty
     * @return the length of this linked list
     */
    public int length() {
        int length = 0;
        MyLinkedListNode<ElementType> current = getHeadNode();
        while (current != null) { // goes through the linked list until it reaches the end starting from the getHeadNode()
            length++; // adds one to the length for each node it goes through
            current = current.getNext();
        }
        return length;
    }

    /**
     * Precondition: this is a linked list and a value is a variable of appropriate type for
     * the list.
     * Postcondition: If a node with node.value = value is present in the list it is removed, the order of elements in the list is otherwise unchanged. If no node with
     * node.value = value is present in the list, the list is unchanged. If the list is
     * empty an exception is generated.
     * @param value the value of the node to be removed
     * @throws LinkedListListEmptyException
     */
    public void deleteByValue(ElementType value) throws LinkedListListEmptyException {
        if (this.getHeadNode() == null) {
            throw new LinkedListListEmptyException("Linked List is empty");
        } else if (this.getHeadNode().getValue().equals(value)) { // check if head is the node to be deleted
            this.head = this.getHeadNode().getNext(); //if so, set the head to the next node
        } else { // checks the rest of the nodes for the node to be deleted
            MyLinkedListNode<ElementType> current = this.getHeadNode();
            while (current.getNext() != null) {
                if (current.getNext().getValue().equals(value)) { // if the next node is the node to be deleted
                    current.setNext(current.getNext().getNext()); // set the next node to the next next node (to delete that node)
                    return; // end the method if the node is found
                }
                current = current.getNext(); // goes to the next node
            }
            System.out.println("Value not found in the list");
        }
    }

    /**
     * Precondition: this is a linked list and a value is a variable of appropriate type for
     * the list.
     * Postcondition: If a node with node.value = value is present in the list, it is returned.
     * If no node with node.value = value is present in the list, null is returned. If the
     * list is empty an exception is generated
     * @param value the value of the node to be returned
     * @return the node with node.value = value
     * @throws LinkedListListEmptyException
     */
    public MyLinkedListNode<ElementType> searchByValue(ElementType value) throws LinkedListListEmptyException {
        if (this.getHeadNode() == null) {
            throw new LinkedListListEmptyException("Linked List is empty");
        } else { // if the head is not null (list is not empty)
            MyLinkedListNode<ElementType> current = this.getHeadNode();
            while (current != null) { // while the current node is not null
                if (current.getValue().equals(value)) { // if the current node's value is equal to the value(parameter)
                    return current; //returns the node with the value
                }
                current = current.getNext(); // goes to next node
            }
            return null; // value not found
        }
    }   


    public String toString() {
        StringBuilder result = new StringBuilder();
        MyLinkedListNode<ElementType> current = this.getHeadNode();
        while (current != null) {
            result.append(current.getValue());
            current = current.getNext();
            if(current != null) { // so that it won't add an arrow after the last node
                result.append(" -> ");
            }
        }
        return result.toString();
    }
}