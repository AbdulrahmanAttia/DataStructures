import java.util.HashMap;

public class MinMaxPQ<Item extends Comparable<Item>> {

	private MaxPQ<Item> maxpq;
	private MinPQ<Item> minpq;
	private int N = 0;
	private HashMap<Item, Integer> mpMin = new HashMap<Item, Integer>();
	private HashMap<Item, Integer> mpMax = new HashMap<Item, Integer>();

	public MinMaxPQ(int N) {
		maxpq = new MaxPQ<Item>(N);
		minpq = new MinPQ<Item>(N);
	}

	public void insert(Item item) {
		int i = maxpq.insert(item);
		int j = minpq.insert(item);
		mpMin.put(item, i);
		mpMax.put(item, j);
		N++;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public Item delMin() {
		N--;
		Item min = minpq.delMin();
		maxpq.del(mpMax.get(min));
		return min;
	}

	public Item delMax() {
		if(isEmpty())return null;
		N--;
		Item max = maxpq.delMax();
		minpq.del(mpMin.get(max));
		return max;

	}

	public Item getMax() {
		return maxpq.getMax();
	}

	public Item getMin() {
		return minpq.getMin();
	}

	public static void main(String[] args) {
		MinMaxPQ<Integer> pq = new MinMaxPQ<Integer>(100);

		pq.insert(100);
		pq.insert(223);
		pq.insert(123);
		pq.insert(1);
		pq.insert(10);

		while (!pq.isEmpty()) {
			System.out.println(pq.delMin());
		}
		// System.out.println(pq.N);


		System.out.println();
	
		 System.out.println(pq.delMax());

		// TODO Auto-generated method stub

	}

}
