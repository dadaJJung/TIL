### [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/)

<br>

### 문제

---

- You are given an array of `k` linked-lists `lists`, each linked-list is sorted in ascending order.
  
  *Merge all the linked-lists into one sorted linked-list and return it.*

<br>

### 풀이 코드

---

```java
//version1
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i<lists.length; i++){
            ListNode node = lists[i];
            while(node!=null){
                pq.offer(node.val);
                node = node.next;
            }
        }
        
        
        ListNode head = null;
        ListNode prev = null;

        while(!pq.isEmpty()){
            
            if(head==null) {
                head = new ListNode(pq.poll());
                prev = head;
            }else{
                ListNode newNode = new ListNode(pq.poll());
                prev.next = newNode;
                prev = newNode;
            }
            
        }
        
        
       return head;
       
        
    }
}
//---------------------------------------------------------------

//version2
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        
        if(lists==null || lists.length==0) return null;
        
        ListNode head = null;
        ListNode cur = null;
        
        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, (a,b)->a.val-b.val);
        for(ListNode node : lists){
            if(node == null) continue;
            pq.offer(node);
        }
        
        while(!pq.isEmpty()){
            ListNode node = pq.poll();
            if(node.next!=null) pq.offer(node.next);
            
            if(head==null){
                head = node;
                cur = node;
            }else{
                cur.next = node;
                cur = node;
            }
            
        }
        
        return head;
        
      
    }
}
```
