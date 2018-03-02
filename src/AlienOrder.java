import java.util.*;

public class AlienOrder {
	static List<Character> order = new ArrayList<Character>();

	public static void alienOrder(String[] words) {

		HashMap<Character, Set<Character>> map = new HashMap<Character, Set<Character>>();

		for (int i = 0; i < words.length - 1; i++) {
			String curr = words[i];
			String next = words[i + 1];
			int len = Math.min(curr.length(), next.length());
			for (int j = 0; j < len; j++) {
				char c1 = curr.charAt(j);
				char c2 = next.charAt(j);
				if (c1 != c2) {
					if (map.containsKey(c1)) {
						Set<Character> s = map.get(c1);
						s.add(c2);
						map.put(c1, s);
					} else {
						map.put((Character) c1, new HashSet<Character>());
						Set<Character> s = map.get(c1);
						s.add(c2);
						map.put(c1, s);;
					}
					break;
				}
			}
		}

		HashSet<Character> onStack = new HashSet<>();
		HashSet<Character> vis = new HashSet<>();
		dfs(words[0].charAt(0), onStack, vis, map);
	}

	public static void dfs(Character c, HashSet<Character> onStack,
			HashSet<Character> vis, HashMap<Character, Set<Character>> map) {
		vis.add(c);
		onStack.add(c);
		
		 if(map.containsKey(c)){// Not each character 
		for (Character ch : map.get(c)) {
			if (!vis.contains(ch)) {
				dfs(ch, onStack, vis, map);
			} else if (onStack.contains(ch)) {
				// dependency // return error
			}
		}
		 }
		order.add(0, c);
		onStack.remove(c);
	}
	public static void main(String []args){
		String []words = {
		                  "wrf",
		                  "wrt",
		                  "er",
		                  "eft",
		                  "rttt"
	};
		alienOrder(words);
		System.out.println(order);
	}
}
