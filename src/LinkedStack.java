
public class LinkedStack<Item> {
	
	private Node first;
	private int N;
	
	private class Node{
		Item item;
		Node next;
	}
	
	public void push(Item item){// insert at beginning
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
		N++;
	}
	
	public Item pop(){// remove from beginning
		if(first == null)return null;
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
	
	public static void main(String args[]){
		LinkedStack<String> s = new LinkedStack<String>();
		s.push("A");
		s.push("B");
		System.out.println(s.pop() + "  " + s.pop());
	}
	
}
