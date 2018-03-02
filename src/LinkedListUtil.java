
public class LinkedListUtil {
	
	 public ListNode reverseKGroup(ListNode head, int k) {
	      if(head == null || k == 0)return head;
	      
	      ListNode dummy = new ListNode(0);
	      dummy.next = head;
	      ListNode begin = dummy;
	      int i = 0;
	      while(head != null){
	          i++;
	          if(i % k == 0){
	              begin = reverse(begin, head.next);
	              head = begin.next;
	          }else{
	              head = head.next;
	          }
	      }
	      
	      return dummy.next;
	    }
	 
	public ListNode reverseBetween(ListNode begin/*dummyNode*/, ListNode end){
		//Begin and end aren't included reverse between begin and end only.
		ListNode prev = begin;
		ListNode curr = begin.next;
		ListNode next;
		ListNode first;
		first = curr;
		
		while(curr != end){
			next = curr.next;
			curr.next = prev;
			prev = curr;
			curr = next;
		}
		
		begin.next = prev;
		first.next = curr;
		return first;
	}

}
