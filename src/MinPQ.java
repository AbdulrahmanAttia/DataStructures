public class MinPQ<Item extends Comparable<Item>> {

	private Item[] pq;
	private int N = 0;

	public MinPQ(int N) {
		pq = (Item[]) new Comparable[N + 1];
	}

	public int insert(Item item) {
		pq[++N] = item;
		swim(N);
		return N;
	}
	
	public void swim(int k) {

		while (k > 1 && less(k, k / 2)) {
			exch(k / 2, k);
			k = k / 2;
		}
	}

	public Item delMin() {
		Item min = pq[1];
		exch(1, N);
		pq[N--] = null;
		sink(1);
		return min;
	}

	public Item del(int k) {
		Item min = pq[k];
		exch(k, N--);
		pq[N + 1] = null;
		sink(k);
		return min;
	}

	public Item getMin() {
		return pq[1];
	}

	public void sink(int k) {
		while (k * 2 < N) {
			int j = k * 2;
			if (j < N && less(j + 1, j))
				j++;
			if (!less(j, k))
				break;
			exch(k,j);
			k = j;
		}
	}

	public boolean isEmpty() {
		return N == 0;
	}

	

	public void exch(int i, int j) {
		Item t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}

	public boolean less(int i, int j) {
		return (pq[i].compareTo(pq[j])) < 0;
	}
	public int size() {
		return N;
	}


	public static void main(String args[]) {
		MinPQ<Integer> pq = new MinPQ<Integer>(100);
		
		pq.insert(100);
		pq.insert(223);
		pq.insert(123);
		pq.insert(1);
		pq.insert(10);

		while (!pq.isEmpty()) {
			System.out.println(pq.delMin());
		}

	}

}
