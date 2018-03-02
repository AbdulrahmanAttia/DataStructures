
public class LinkedQueue<Item> {
	private Node first;
	private Node last;
	private int N;
	
	private class Node{
		Item item;
		Node next;
	}
	
	public void enqueue(Item item){//insert at the end.
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if(isEmpty())first = last;
		else oldlast.next = last;
		N++;
	}
	
	public Item dequeue(){// remove from beginning.
		if(isEmpty()){
			last = null;
			return null;
		}
		Item item = first.item;	
		first = first.next;
		N--;
		return item;
	}
	
	public boolean isEmpty(){
		return N == 0;
	}
	
	public int size(){
		return N;
	}
	
	
	

}
