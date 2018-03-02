import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class Problems {
	public static void binaryRep(int N) {
		Stack<String> stack = new Stack<String>();
		StringBuilder sb = new StringBuilder();
		while (N > 0) {
			sb.insert(0, N % 3 + " ");
			N = N / 3;
		}

	}

	private class comp implements Comparator<String> {

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return 0;
		}

	}

	public static double median(int[] a) {
		int len = a.length;
		if (len % 2 == 1) {
			return (double) select(a, len / 2);
		} else {
			return ((double) select(a, len / 2 - 1) + (double) select(a,
					len / 2)) * .5;
		}
	}

	private static int partition(int[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		int v = a[lo];
		while (true) {

			// find item on lo to swap
			while (a[++i] < v)
				if (i == hi)
					break;

			// find item on hi to swap
			while (v < a[--j])
				if (j == lo)
					break; // redundant since a[lo] acts as sentinel

			// check if pointers cross
			if (i >= j)
				break;

			exch(a, i, j);
		}

		exch(a, lo, j);

		return j;
	}

	public static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}

	public static void exch(int a[], int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static int select(int[] a, int k) {
		if (k < 0 || k >= a.length) {
			throw new IndexOutOfBoundsException(
					"Selected element out of bounds");
		}
		int lo = 0, hi = a.length - 1;
		int i = 0;
		while (hi > lo) {
			i = partition(a, lo, hi);
			if (i > k)
				hi = i - 1;
			else if (i < k)
				lo = i + 1;
			else
				return a[i];
		}
		System.out.println(i + " : " + k);
		return a[lo];
	}

	public static boolean checkOne(int i, int j, int[][] mat) {
		if (i < 0 || i >= mat.length || j < 0 || j >= mat[0].length
				|| mat[i][j] != 1)
			return false;
		return true;
	}

	static int maxi = -1;
	static int maxj = -1;
	static int maxLen = 0;
	static boolean[][] vis = new boolean[100][100];

	

	public static void findLeafs(int[] arr) {
		if (arr == null || arr.length == 0)
			return;

		Stack<Integer> stack = new Stack<>();
		for (int n = 1, c = 0; n < arr.length; n++, c++) {
			if (arr[c] > arr[n]) {
				stack.push(arr[c]);
			} else {
				boolean found = false;
				while (!stack.isEmpty()) {
					if (arr[n] > stack.peek()) {
						stack.pop();
						found = true;
					} else
						break;
				}
				if (found)
					System.out.println(arr[c]);
			}

		}
		System.out.println(arr[arr.length - 1]);
	}

	public static String minimumRemoval(String unbalancedParan) { // ClosedWithoutOpen
		// = [0,

		// Stack = []
		Stack<Integer> stack = new Stack<>();
		List<Integer> closedWithoutOpen = new ArrayList<Integer>();
		for (int i = 0; i < unbalancedParan.length(); i++) {

			if (unbalancedParan.charAt(i) == '(') {// should add the index of
				// that char to the map.
				stack.push(i);
			} else if (!stack.isEmpty() && unbalancedParan.charAt(i) == ')') {
				stack.pop();
			} else if (unbalancedParan.charAt(i) == ')') {
				closedWithoutOpen.add(i);
			}

		}

		if (closedWithoutOpen.size() == 0 && stack.isEmpty())
			return unbalancedParan;

		StringBuilder sb = new StringBuilder(unbalancedParan);

		while (!stack.isEmpty()) {
			sb.deleteCharAt(stack.pop());
		}

		for (int i = 0; i < closedWithoutOpen.size(); i++) {
			sb.deleteCharAt(closedWithoutOpen.get(i) - i);
		}

		return sb.toString();
	}

	/*
	 * balance("()") -> "()" balance("a(b)c)") -> "a(b)c" balance(")(") -> ""
	 * balance("(((((") -> "" balance("(()()(") -> "()()" balance(")(())(") ->
	 * "(())"
	 */

	public static int subsetSum1D(int[] nums, int s) {
		int[] dp = new int[s + 1];
		dp[0] = 1;
		for (int n : nums)
			for (int i = s; i >= n; i--)
				dp[i] += dp[i - n];
		return dp[s];
	}

	public static int subsetSum2D(int[] nums, int target) {

		int[][] dp = new int[nums.length + 1][target + 1];

		for (int i = 0; i < dp.length; i++)
			dp[i][0] = 1;

		for (int i = 1; i < dp[0].length; i++)
			dp[0][i] = 0;

		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				dp[i][j] = dp[i - 1][j];

				if (j >= nums[i - 1])
					dp[i][j] += dp[i - 1][j - nums[i - 1]];
			}
		}

		// Printing the exact subset.
		/*
		 * if(dp[dp.length - 1][dp[0].length - 1]){ int sum = 0; for(int i =
		 * dp.length - 1, j = dp[0].length - 1; i > 0 && j > 0;){ if(j - nums[i
		 * - 1] >= 0 && dp[i - 1][j - nums[i - 1]]){// If the current number is
		 * included. System.out.print(nums[i - 1] + " ");// Print it and
		 * continue from the remaining sum. j = j - nums[i - 1]; i = i - 1;
		 * }else if(dp[i - 1][j]){//If the current number isn't included i = i -
		 * 1;// don't print it and continue with the same sum. }else break; } }
		 */

		return dp[dp.length - 1][dp[0].length - 1];

	}

	public static Integer[] kClosest(int[] nums, int k, int target) {

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k,
				new Comparator<Integer>() {
					public int compare(Integer a, Integer b) {
						return Math.abs(b - target) - Math.abs(a - target);
					}
				});

		for (int i = 0; i < nums.length; i++) {
			pq.offer(nums[i]);
			if (pq.size() > k) {
				pq.poll();
			}
		}


		return pq.toArray(new Integer[k]);

	}


	public static void main(String args[]) {
		String s = "a";
		System.out.println(s.matches("[bcdfghjklmnpqrstvwxyz]*a[bcdfghjklmnpqrstvwxyz | a]*e[bcdfghjklmnpqrstvwxyz|e]*i[bcdfghjklmnpqrstvwxyz|i]*o[bcdfghjklmnpqrstvwxyz|o]*u[bcdfghjklmnpqrstvwxyz|u]*"));
		System.out.println(s.matches("/\\*[\\d|\\w]*\\*/"));
		System.out.println(s.matches("[^a-z]*[a-z]"));
		System.out.println((Integer.highestOneBit(31)));
		
		
		System.out.println(minimumRemoval("(()()()()(x)abcd)"));
		int[] a = { 10, 5, 2, 0, 1, 3, 7, 6, 8, 15, 12, 11, 14, 20, 100 };
		findLeafs(a);
		System.out.println();
		int[][] board = { { 0, 0, 0, 0, 1, 1, 0 }, { 1, 0, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 1 }, { 0, 0, 1, 1, 1, 1, 0 },
				{ 0, 0, 1, 1, 1, 1, 0 } };
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Scanner x = new Scanner(System.in);
		Problems p = new Problems();

		// System.out.println(wildCardMatching("xaylmdfdfdsgfzz", "x?y*z"))
		;
		Integer[] ans = kClosest(a, 5, 100);
		for (int i = 0; i < ans.length; i++) {
			System.out.print(ans[i] + " ");
		}
		System.out.println();
		System.out.println(median(a));

		List<String> l = new ArrayList<>();
		l.add("doctorgreghouse");
		l.add("hectordoesgorug");
		l.add("hugeegosorry");
		l.add("gregoryhouse");

		int[] nums = { 1, 2, 3, 4, 5, 3 };
		int target = 15;
		// System.out.println(subsetSum(nums, target));

		// System.out.println(anagrams(l));
	}
}
