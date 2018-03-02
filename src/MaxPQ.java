public class MaxPQ<Key extends Comparable<Key>> {

	private Key[] pq;
	private int N = 0;

	public MaxPQ(int maxN) {
		pq = (Key[]) new Comparable[maxN];
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public Key del(int k){
		Key max = pq[k];
		exch(k, N--);
		pq[N + 1] = null;
		sink(k);
		return max;
	}
	public int size() {
		return N;
	}
	
	public Key getMax(){
		return pq[1];
	}

	public int insert(Key k) {
		pq[++N] = k;
		swim(N);
		return N;
	}

	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) < 0;
	}

	private void exch(int i, int j) {
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}

	private void swim(int k) {

		while (k > 1 && less(k / 2, k)) {
			exch(k / 2, k);
			k = k / 2;
		}
	}

	private void sink(int k) {

		while (2 * k <= N) {
			int j = 2 * k;
			if (j < N && less(j, j + 1))
				j++;
			if (!less(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}

	public Key delMax() {
		Key max = pq[1];
		exch(1, N--);
		pq[N + 1] = null;
		sink(1);
		return max;
	}

	public static void main(String args[]) {

		MaxPQ<Integer> pq = new MaxPQ<Integer>(10);

		pq.insert(5);

		pq.insert(6);

		pq.insert(10);

		pq.insert(3);

		pq.insert(0);

		while (!pq.isEmpty()) {
			System.out.println(pq.delMax());
		}

	}

}
