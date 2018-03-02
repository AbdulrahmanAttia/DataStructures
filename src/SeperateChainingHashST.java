import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class SeperateChainingHashST<Key, Value> {
	private int N;

	private List<Pair>[] st;
	private int M;

	public SeperateChainingHashST(int M) {
		st = (List<Pair>[]) new List[M];
		this.M = M;
		for (int i = 0; i < M; i++) {
			st[i] = new ArrayList<Pair>();
		}
	}
	
	public class Apple{
		private String name;
		private List<Apple> ne;
		public Apple(String name){
			this.name = name;
		}
	}
	
	public HashMap<Apple, List<Apple>> getAdj(List<Pair<Apple>> edges){
		HashMap<Apple, List<Apple>> adj = new HashMap<>();
		for(Pair p : edges){
			Apple start = p.first;
			Apple second = p.second;
			edges
		}
	}
	
	public void findAllPaths(Apple src, Apple dst, HashMap<Apple, Boolean> visited, List<Apple> currentPath){
		visited.put(src, true);
		if(src.equals(dst)){
			// add current path
		}
		
		for(Apple apple : adj(src)){
			if(!visited.containsKey(apple)){
				currentPath.add(apple);
				findAllPaths()
				currentPath.remove(apple);
			}
		}
		
		visited.put(src, false);
			
	}

	private class Pair {
		Key key;
		Value val;

		public Pair(Key key, Value val) {
			this.key = key;
			this.val = val;
		}
	}

	public int hash(Key key) {
		return (key.hashCode() & 0x07fffffff) % M;
	}

	public Value get(Key key) {
		int slot = hash(key);
		for (int i = 0; i < st[slot].size(); i++) {
			if (st[slot].get(i).key.equals(key)) {
				return st[slot].get(i).val;
			}
		}
		return null;
	}

	public boolean update(Key key, Value val) {
		List<Pair> chain = st[hash(key)];
		for (int i = 0; i < chain.size(); i++) {
			if (chain.get(i).key.equals(key)) {
				chain.get(i).val = val;
				return true;
			}
		}

		return false;
	}

	public void resize(int M) {
		SeperateChainingHashST<Key, Value> newSt = new SeperateChainingHashST<>(
				M);
		for (int i = 0; i < st.length; i++) {
			for (int j = 0; j < st[i].size(); j++) {
				Key key = st[i].get(j).key;
				Value val = st[i].get(j).val;
				newSt.put(key, val);
			}
		}
		this.M = newSt.M;
		this.st = newSt.st;
		this.N = newSt.N;
		System.out.println("Resized");
	}

	public void put(Key key, Value val) {
		if (N / M >= 2)
			resize(2 * M);

		if (update(key, val))
			return;
		st[hash(key)].add(new Pair(key, val));
		N++;
	}

	public static List<Integer> leavesFromPre(int[] arr) {

		if (arr == null || arr.length == 0)
			return new ArrayList<>();
		List<Integer> leaves = new ArrayList<Integer>();
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0, j = 1; j < arr.length; i++, j++) {
			if (arr[i] > arr[j]) {
				stack.push(arr[i]);
			} else {
				boolean found = false;
				while (!stack.isEmpty()) {
					if (arr[j] > stack.peek()) {
						stack.pop();
						found = true;
					} else
						break;
				}
				if (found) {
					leaves.add(arr[i]);
				}
			}
		}
		leaves.add(arr[arr.length - 1]);

		return leaves;
	}

	static List<String> de = new ArrayList<>();
	static boolean[] vis;
	static char pos[];

	// ABCDE
	public static void setPos(String s) {
		for (int i = 0; i < s.length(); i++) {
			pos[i] = s.charAt(i);
		}
	}

	static HashSet<String> set = new HashSet<>();

	public static void Dearrg(String s, int j, StringBuilder sb) {
		if (j == s.length()) {
				de.add(sb.toString());
			return;
		}
		for (int i = 0; i < s.length(); i++) {
			if (!vis[i] && pos[j] != s.charAt(i)&& (i == 0 || (s.charAt(i - 1) != s.charAt(i)) || vis[i - 1])) {
				sb.append(s.charAt(i));
				vis[i] = true;
				Dearrg(s, j + 1, sb);
				sb.deleteCharAt(j);
				vis[i] = false;
			}
		}
	}

	public static String reverseWords(String s) {
		if (s == null)
			return null;
		if (s.equals(""))
			return "";
		String[] words = s.split("\\s+");
		int[] spaces = new int[words.length];
		int spaceNum = 0;
		for (int i = 0; i < s.length(); i++) {
			while(i  < s.length() && s.charAt(i) == ' ') {
				spaces[spaceNum]++;
				i++;
			}
			if(spaces[spaceNum] != 0)
				spaceNum++;
		}

		//String[] words = s.split("\\s+");

		for (int i = 0; i < words.length / 2; i++) {
			String temp = words[i];
			words[i] = words[words.length - i - 1];
			words[words.length - i - 1] = temp;
		}

		StringBuilder sb = new StringBuilder("");

		int i = 0;
		spaceNum = 0;
		if (s.charAt(0) == ' ') {
			while (i < spaces[0]) {
				
				sb.append(" ");
				i++;
			}
			spaceNum = 1;
		}

		for (String word : words) {
			sb.append(word);
			i = 0;
			while (spaceNum < spaces.length && i < spaces[spaceNum]) {
				sb.append(" ");
				i++;
			}
			spaceNum++;
		}
		return sb.toString();
	}

	public static class DNode {
		DNode next;
		DNode prev;
		int val;

		public DNode(int val) {
			this.val = val;
		}
	}

	public static DNode reverse(DNode head) {
		DNode cur = head;
		DNode prev = null;
		while (cur != null) {
			prev = cur.prev;
			cur.prev = cur.next;
			cur.next = prev;
			cur = cur.prev;
		}
		return prev.prev;
	}

	public static void main(String[] args) {
		pos = new char[100];
		vis = new boolean[100];
		String s = "AABC";
		setPos(s);
		char[] chars = s.toCharArray();
		Arrays.sort(chars);
		s = new String(chars);
		Dearrg(s, 0, new StringBuilder());
		System.out.println(de);

		int[] arr = { 7, 3, 1, -1, 0, 2, 4, 5, 12, 9, 13 };
		System.out.println(reverseWords("   Hello fuck      you"));

		Node root = new OperatorNode("+");
		Node l1 = new OperatorNode("*");
		Node l2 = new OperandNode("3");
		Node l3 = new OperatorNode("+");
		Node l4 = new OperandNode("2");
		Node l5 = new OperandNode("5");
		Node l6 = new OperandNode("9");

		root.left = l2;
		root.right = l1;
		l1.left = l3;
		l1.right = l4;
		l3.left = l5;
		l3.right = l6;
		System.out.println(root.evaluate());

		DNode head = new DNode(0);
		DNode n1 = new DNode(1);
		DNode n2 = new DNode(2);
		DNode n3 = new DNode(3);

		head.next = n1;
		head.prev = null;
		n1.prev = head;
		n1.next = n2;
		n2.prev = n1;
		n2.next = n3;
		n3.prev = n2;
		n3.next = null;

		DNode ret = reverse(head);

		while (ret != null) {
			System.out.println(ret.val);
			ret = ret.next;
		}

		SeperateChainingHashST<Integer, String> st = new SeperateChainingHashST<>(
				5);
		st.put(10, "Hello");
		st.put(5, "HI");
		st.put(0, "THird entry");
		st.put(-1, "String");
		st.put(-1, "Stringgg");
		st.put(0, "Replaced");
		st.put(10, "Hello");
		st.put(1, "HI");
		st.put(2, "THird entry");
		st.put(31, "String");
		st.put(3, "Stringgg");
		st.put(4, "Replaced");
		st.put(50, "Hello");
		st.put(52, "HI");
		st.put(22, "THird entry");
		st.put(21, "String");
		st.put(51, "Stringgg");
		st.put(2, "Replaced");
		st.put(503, "Hello");
		st.put(51, "HI");
		st.put(34, "THird entry");
		st.put(53, "String");
		st.put(83, "Stringgg");
		st.put(67, "Replaced");
		System.out.println(st.get(67));
	}

}
