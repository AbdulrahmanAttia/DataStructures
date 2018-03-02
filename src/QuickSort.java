
public class QuickSort {
	
	public static void sort(Comparable a[]){
			sort(a,0,a.length - 1);
	}
	
	public static boolean less(Comparable a, Comparable b){
		return a.compareTo(b) < 0;
	}
	
	public static void exch(Comparable a[],int i ,int j){
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	public static void sort(Comparable a[],int lo , int hi){
		if(lo >= hi)return;
		int i = partition(a,lo,hi);
		sort(a,lo, i - 1);
		sort(a,i + 1,hi);
	}
	
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while (true) {
 
            // find item on lo to swap
            while (less(a[++i], v))
                if (i == hi) break;
 
            // find item on hi to swap
            while (less(v, a[--j]))
                if (j == lo) break;      // redundant since a[lo] acts as sentinel
 
            // check if pointers cross
            if (i >= j) break;
 
            exch(a, i, j);
        }
 
        // put partitioning item v at a[j]
        exch(a, lo, j);
 
        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }
    
    public static Comparable select(Comparable[] a, int k) {
        if (k < 0 || k >= a.length) {
            throw new IndexOutOfBoundsException("Selected element out of bounds");
        }
        int lo = 0, hi = a.length - 1;
        int i = 0;
        while (hi > lo) {
            i = partition(a, lo, hi);
            if      (i > k) hi = i - 1;
            else if (i < k) lo = i + 1;
            else return a[i];
        }
        System.out.println(i + " : " + k);
        return a[lo];
    }
 
	

	private static void qsort(Comparable[] a, int lo, int hi)
	{ // See page 289 for public sort() that calls this method.
	if (hi <= lo) return;
	int lt = lo, i = lo, gt = hi;
	Comparable v = a[lo];
	while (i <= gt)
	{
	int cmp = a[i].compareTo(v);
	if
	(cmp < 0) exch(a, lt++, i++);
	else if (cmp > 0) exch(a, i, gt--);
	else
	i++;
	} // Now a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
	sort(a, lo, lt - 1);
	sort(a, gt + 1, hi);
	}
	
	
	public static void main(String args[]){
		Integer a[] = {1,2,-1,-3,0,7,-100,100};
		qsort(a,0,a.length -1);
		for(Integer i : a){
			System.out.print(i + " ");
		}
	}

}
