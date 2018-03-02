import java.util.PriorityQueue;
import java.util.Stack;
public class ExpressionTree {
	
	private class Node{
		Node left;
		Node right;
		char data;
		
		public Node(char data){
			this.data = data;
		}
	}
	
	private boolean isOperator(Character c){
		if(c.equals('+') || c.equals('-') || 
				c.equals('*') || c.equals('/') ){
			return true;
		}
		return false;
	}
	
	public void inorder(Node n){
		if(n == null)return;
		inorder(n.left);
		System.out.print(n.data + " ");
		inorder(n.right);
	}
	public Node constructTree(String exp){
		Stack<Node> s = new Stack<Node>();
		
		for(int i = 0; i < exp.length();i++){
			Character c = exp.charAt(i);
			Node n = new Node(c);
			if(isOperator(c)){
				Node n1 = s.pop();
				Node n2 = s.pop();
				n.right = n1;
				n.left = n2;
				s.push(n);
			}else {
				s.push(n);
			}
		}
		return s.pop();
	}
	
	public static void main(String args[]){
		PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
		queue.offer(10);
		queue.offer(5);
		
		System.out.println(queue.peek());
		ExpressionTree et = new ExpressionTree();
		String exp = "ab+ef*g*-";// postfix
		
		et.inorder(et.constructTree(exp));
		
	}
	

}
