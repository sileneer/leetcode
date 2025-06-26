# 24. Swap Nodes in Pairs

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        while (prev.next != null && prev.next.next != null) {
            ListNode nextFirst = prev.next;
            ListNode nextSecond = prev.next.next;
            prev.next = nextSecond;
            nextFirst.next = nextSecond.next;
            nextSecond.next = nextFirst;
            prev = nextFirst;
        }
        return dummy.next;
    }
}
```