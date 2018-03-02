public class MergeSort {
	private static Comparable aux[];

	public static boolean less(Comparable i, Comparable j) {
		return i.compareTo(j) < 0;
	}

	public static void sort(Comparable[] a) {
		aux = new Comparable[a.length];
		sort(a, 0, a.length - 1);
	}

	public static void sortIter(Comparable[] a) { // Do lg N passes of pairwise
													// merges.
		int N = a.length;
		aux = new Comparable[N];
		for (int sz = 1; sz < N; sz = sz + sz)
			// sz: subarray size
			for (int lo = 0; lo < N - sz; lo += sz + sz)
				// lo: subarray index
				merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
	}

	public static void mergeSortedArrays(Comparable a[], Comparable b[]) {
		int i = 0;// a
		int j = 0;// b
		aux = new Comparable[a.length + b.length];

		for (int k = 0; k < aux.length; k++) {

			if (i >= a.length)
				aux[k] = b[j++];
			else if (j >= b.length)
				aux[k] = a[i++];
			else if (less(a[i], b[j]))
				aux[k] = a[i++];
			else
				aux[k] = b[j++];
		}

		for (int k = 0; k < aux.length; k++) {
			System.out.print(aux[k] + " ");
		}
	}

	public static void merge(Comparable a[], int lo, int mid, int hi) {

		int i = lo;// 0
		int j = mid + 1;// a.length if merging two arrays
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];

		for (int k = lo; k <= hi; k++) {
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(aux[i], aux[j]))
				a[k] = aux[i++];
			else
				a[k] = aux[j++];
		}
	}

	public static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		sort(a, lo, mid);
		sort(a, mid + 1, hi);
		merge(a, lo, mid, hi);
	}

	public static void main(String[] args) {
		Integer a[] = { 0, 1, 2, 40, 3, 5, 101 };
		Integer b[] = { -1, 2, 3, 4, 5, 6, 100 };

		sort(a);

		for (Integer i : a) {
			System.out.print(i + " ");
		}

	}

}
