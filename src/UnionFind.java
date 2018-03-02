
public class UnionFind {
	
	private int[]id;
	private int[]sz;
	private int count;
	public UnionFind(int n){
		id = new int [n];
		sz = new int[n];
		count = n;
		for(int i = 0; i < n; i++){
			id[i] = i;
			sz[i] = 1;
		}
			
	}
	
	public boolean connected(int i, int j){
		int iRoot = findRoot(i);
		int jRoot = findRoot(j);
		return iRoot == jRoot;
	}
	
	public int findRoot(int i){
		while(i != id[i]){
			id[i] = id[id[i]]; // make every path on the way to the root point to it's grand parent.[path compression]
			i = id[i];
		}
		return i;
	}
	
	public void union(int i, int j){
		int iRoot = findRoot(i);
		int jRoot = findRoot(j);
		
		if(iRoot == jRoot)return;
		
		if(sz[iRoot] <= sz[jRoot]){
			id[iRoot] = jRoot;
			sz[jRoot] += sz[iRoot];
		}
		else {
			id[jRoot] = iRoot;
			sz[iRoot] += sz[jRoot];
		}
		count--;
		
	}
	
	public static void main(String args[]){
		UnionFind uf = new UnionFind(10);
		System.out.println(uf.count);
		uf.union(0,1);
		for(int i = 0; i < 9; i++){
			System.out.println(uf.connected(i, i + 1));
		}
	
	}
	
}
