public class BST<Key extends Comparable<Key>, Value> {

	private Node root;

	private class Node {
		Key key;
		Value val;
		Node left;
		Node right;
		int N;

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	public void put(Key key, Value val) {
		root = put(root, key, val);
	}

	public int size() {
		return root.N;
	}

	public int size(Node n) {
		if (n == null)
			return 0;
		return n.N;
	}

	public Node put(Node x, Key key, Value val) {
		if (x == null)
			return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if (cmp <= 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	public Value get(Key key) {
		return get(root, key);
	}

	private Node min(Node x) {
		if (x == null)
			return null;
		if (x.left == null)
			return x;
		return min(x.left);
	}

	public Key min() {
		return min(root).key;
	}

	private Node floor(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		else if (cmp < 0)
			return floor(x.left, key);
		Node n = floor(x.right, key);
		if (n != null)
			return n;
		else
			return x;
	}

	public Key floor(Key key) {
		return floor(root, key).key;
	}

	public Key ceil(Key key) {
		return ceil(root, key).key;
	}

	public Node ceil(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		else if (cmp > 0)
			return ceil(x.right, key);
		Node n = ceil(x.right, key);
		if (n != null)
			return n;
		else
			return x;
	}

	public Value get(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return get(x.left, key);
		if (cmp > 0)
			return get(x.right, key);
		return x.val;
	}

	public void print(Node x) {
		if (x == null)
			return;
		print(x.left);
		System.out.println(x.key);
		print(x.right);
	}

	private int rank(Node x, Key key) {
		if (x == null)
			return 0;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return rank(x.left, key);
		else if (cmp > 0)
			return rank(x.right, key) + size(x.left) + 1;
		return size(x.left);
	}

	public int rank(Key key) {
		return rank(root, key);
	}

	private Node select(Node x, int rank) {
		if (x == null)
			return null;
		if (size(x.left) == rank)
			return x;
		else if (size(x.left) > rank)
			return select(x.left, rank);
		return select(x.right, rank - size(x.left) - 1);
	}

	public Key select(int rank) {
		return select(root, rank).key;
	}

	public void print() {
		print(root);
	}
	
	public void delete(Key key){
		delete(root, key);
	}
	
	public Node deleteMin(Node x){
		if(x.left == null)return x.right;
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	public Node delete(Node x, Key key){
		if(x == null)return null;
		int cmp = key.compareTo(x.key);
		if(cmp < 0)x.left = delete(x.left, key);
		else if(cmp > 0)x.right = delete(x.right, key);
		else {
			if(x.left == null)return x.right;
			if(x.right == null)return x.left;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
///////////////////////Range Queries
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BST<Integer, Integer> bst = new BST<>();
		System.out.println(0x7fffffff);
		System.out.println(bst.rank(101));
		bst.put(0, 1);
		bst.put(10, 0);
		bst.put(20, 2);
		bst.put(21, 2);
		bst.put(12, 2);
		bst.put(40, 2);
		bst.put(13, 2);
		bst.put(100, 2);
		bst.put(102, 2);
		
	}

}
