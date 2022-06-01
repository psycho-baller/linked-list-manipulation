package Assignment2;

import Assignment2.MyLinkedListInterface.MyLinkedListNode;

class TangledList {

    /**
     * Precondition: list0 and list1 are linked lists, there is at least one non-shared node in each
     * list.
     * Postcondition: list0 and list1 do not share any nodes. If they did have shared nodes to
     * begin with, those nodes have been removed from whichever list was shorter to begin
     * with.
     * @param list0 the longer linked list which won't be modified
     * @param list1 the shorter linked list which will be modified to not share any nodes with list0
     */
    public static void removeSharedLinkedListNodes(MyLinkedList<String> list0, MyLinkedList<String> list1) {

        int list0Length = list0.length();
        int list1Length = list1.length();
        // 1. Ensure list0 is the longer list, if it is not swap them
        if (list0Length < list1Length) {
            MyLinkedList<String> temp = list0; // swap list0 and list1
            list0 = list1;
            list1 = temp;
            int tempLength = list0Length; // swap their lengths
            list0Length = list1Length;
            list1Length = tempLength;
        }

        // 2. Compute the length difference but avoid calling length() more than necessary,
        int diff = list0Length - list1Length;

        // 3. Initialize two nodes and set them to the heads of each list.
        MyLinkedListNode<String> node0 = list0.getHeadNode();
        MyLinkedListNode<String> node1 = list1.getHeadNode();
        MyLinkedListNode<String> originalNode1 = list1.getHeadNode();

        // 4. Advance the node from the longer list (list0) by a number of nodes equal to the difference in lengths of the two lists.
        for (int i = 0; i < diff; i++) {
            node0 = node0.getNext();
        }
        // 5. At this point both nodes are the same number of steps from the end of both lists.

        // 6. Advance both nodes along the lists checking equality as you go. The nodes will be equal at the first shared node between the lists.
        int steps = 0; // 8. keep track of the number of steps it takes to reach the shared node

        while (node0 != null && node1 != null && !(node0.getValue().equals(node1.getValue()))) {
            steps++;
            node0 = node0.getNext();
            node1 = node1.getNext();
        }
        // 7. If there are no shared nodes, then the nodes will both eventually be null.
        // 9. If a shared node is found then reset the list1 iterating node back to the head of list1
        if (node0 != null && node1 != null) {
            node1 = originalNode1;
            // 10. Iterate through the list for one less steps than the steps it took to reach the shared node. This is the last node in list1 that is not shared.
            for (int i = 0; i < steps - 1; i++) {
                node1 = node1.getNext();
            }
            // 11. Set its next value to null to remove the subsequent nodes from this list.
            node1.setNext(null);
        }
    }

    /**
     * Precondition: list0 is a linked list
     * Postcondition: If list0 contains a cycle, the period is returned. If list0 does not contain
     * a cycle, 0 is returned
     * @param list0 the linked list to check for a cycle
     * @return
     */
    public static int detectCycleAndPeriod(MyLinkedList<String> list0) {
        // 1. Initialize the tortoise and hare to the head node
        MyLinkedListNode<String> tortoise = list0.getHeadNode();
        MyLinkedListNode<String> hare = list0.getHeadNode();

        while (tortoise != null && hare != null) {
            // 2. Advance the tortoise 1 node, and the hare two nodes
            // 3. repeat this process until the end of the list is reached (no loop) or the two nodes are equal
            tortoise = tortoise.getNext();
            hare = hare.getNext();
            if (hare != null) {
                hare = hare.getNext();
            }
            if (tortoise == hare) { // 2. and check if they are equal (occupying the same node)
                // 4. If the hare and tortoise meet then that means there is a cycle, and we should identify the period.

                // 6. To identify the period:
                hare = hare.getNext(); // advance the hare one node
                int period = 1; // set the period to 1 since we have already advanced the hare once
                while(hare != tortoise) {
                    //(a) Let the hare continue traversing the list (one node at a time this time) until
                    //it returns to the tortoise’s position.
                    hare = hare.getNext();
                    period++; //b) Count the number of steps that this takes.
                }
                //(c) The count is the period
                //(d) Return it.
                return period;
            }
        }
        // 5. If there is no cycle then return –1 for the period.
        return -1;
    }

    /**
     * Precondition: list0 is a linked list with a cycle, period is the number of nodes in the cycle
     * Postcondition: list0 does not have a cycle, the order of the nodes in the list is unchanged
     * (except for the removal of repeated nodes in the order), the number of the nodes in
     * the list is unchanged
     * @param list0 the linked list to remove the cycle
     * @param period the number of nodes in the cycle (from the previous method)
     */
    public static void removeCycle(MyLinkedList<String> list0, int period) {
        // 1. Initialize the tortoise and hare to the head node
        MyLinkedListNode<String> tortoise = list0.getHeadNode();
        MyLinkedListNode<String> hare = list0.getHeadNode();

        // 2. Advance the hare “period” nodes into the list.
        for (int i = 0; i < period; i++) {
            hare = hare.getNext();
        }
        // 3. Advance the hare and the tortoise at the same rate, checking for equality as you go
        while (tortoise != null && hare != null) {
            if (tortoise == hare) {
                // 4. The tortoise and hare meet at the first node in the cycle.
                // 5. Advance the hare period-1 nodes, this is the last node in the cycle.
                for(int i = 0; i < period - 1; i++) {
                    hare = hare.getNext();
                }
                // 6. Set the last node in the cycle’s next value to be null.
                hare.setNext(null);
                return;
            } else {
                tortoise = tortoise.getNext();
                hare = hare.getNext();
            }
        }
    }
    public static void removeLinkedListCycles(MyLinkedList<String> list0) {
        int period = detectCycleAndPeriod(list0);
        if (period != -1) {
            removeCycle(list0, period);
        }
    }

    // Code to setup one test case for eliminating shared nodes from two linked lists
    public static void createAndTestSharedNode() {
        // Your assignment submission should have more specific error handling
        try{
            MyLinkedList<String> stage0 = new MyLinkedList<String>();
            stage0.appendToTail("Arkells");
            stage0.appendToTail("Bruno Mars");
            stage0.appendToTail("Coldplay");
            stage0.appendToTail("David Bowie");
            stage0.appendToTail("Earth, Wind & Fire");

            MyLinkedList<String> stage1 = new MyLinkedList<String>();
            stage1.appendToTail("Foo Fighters");
            stage0.appendToTail("Gorillaz");

            MyLinkedListNode<String> node;
            node = stage0.searchByValue("Coldplay");
            stage1.appendToTail(node);

            System.out.println("Shared Nodes: Stage 0 Lineup");
            System.out.println(stage0.toString());
            System.out.println("Shared Nodes: Stage 1 Lineup");
            System.out.println(stage1.toString());
            System.out.println("\n");

            removeSharedLinkedListNodes(stage0, stage1);

            System.out.println("Shared Nodes Fixed: Stage 0 Lineup");
            System.out.println(stage0.toString());
            System.out.println("Shared Nodes Fixed: Stage 1 Lineup");
            System.out.println(stage1.toString());
            System.out.println("\n");

        } catch (Exception e) {}
    }

    // Code to setup one test case for eliminating cycles from a linked list
    public static void createAndTestCycle() {
        // Your assignment submission should have more specific error handling
        try {
            MyLinkedList<String> stage0 = new MyLinkedList<String>();
            stage0.appendToTail("Arkells");
            stage0.appendToTail("Bruno Mars");
            stage0.appendToTail("Coldplay");
            stage0.appendToTail("David Bowie");
            stage0.appendToTail("Earth, Wind & Fire");
            stage0.appendToTail("Foo Fighters");
            stage0.appendToTail("Gorillaz");

            MyLinkedListNode<String> loopToNode;
            loopToNode = stage0.searchByValue("Coldplay");

            MyLinkedListNode<String> tail;
            tail = stage0.searchByValue("Gorillaz");
            if(tail != null)
                tail.setNext(loopToNode);


            System.out.println("Cyclic Nodes: Stage 0 Lineup");
            MyLinkedListNode<String> currentNode;
            currentNode = stage0.searchByValue("Arkells");

            // If we use the toString method it will never terminate.
            // Most linked list operations on this list will not work, so be careful!
            for(int i = 0; i<10 & currentNode != null; i++) {
                System.out.println("Element: " + currentNode.toString());
                currentNode = currentNode.getNext();
            }
            System.out.println("\n");

            removeLinkedListCycles(stage0);

        } catch (Exception e) {}
    }

    public static void main(String[] args) {
        //Feel free to set up different tests, your code should be general, and will be tested against other cases
        createAndTestSharedNode();
        createAndTestCycle();
    }
}