package testjava;

class LLDNode {
    int data;
    LLDNode next;

    LLDNode(int d) {
        data = d;
        next = null;
    }
}

public class LinkedListDeletion {

    private static LLDNode insert(LLDNode head, int data) {
        LLDNode p = new LLDNode(data);
        if (head == null) {
            head = p;
        } else if (head.next == null) {
            head.next = p;
        }
        else {
            LLDNode start = head;
            while (start.next != null) {
                start = start.next;
            }
            start.next = p;
        }
        return head;
    }

    private static LLDNode removeDuplicates(LLDNode head) {
        if (head.next == null) {
            return head;
        } else if (head.next.data == head.data) {
            if (head.next.next != null) {
                head.next = head.next.next;
                removeDuplicates(head);
            } else {
                head.next = null;
            }
        } else {
            removeDuplicates(head.next);
        }
        return head;
    }

    public static void main() {
        LLDNode head = null;
        int T = 20;
        head = insert(head, 3);
        head = insert(head, 9);
        head = insert(head, 9);
        head = insert(head, 11);
        head = insert(head, 11);
        head = insert(head, 11);
        head = insert(head, 11);
        head = insert(head, 89);
        head = insert(head, 89);
        head = insert(head, 100);
        head = insert(head, 100);
        head = insert(head, 101);
        head = insert(head, 102);
        head = insert(head, 103);
        head = insert(head, 108);
        head = insert(head, 200);
        head = insert(head, 250);
        head = insert(head, 250);
        head = insert(head, 250);
        head = insert(head, 250);
        head = removeDuplicates(head);
    }
}
