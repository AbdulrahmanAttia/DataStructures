import java.util.Arrays;
import java.util.Comparator;


public class Challenge {
	static int prevRow  = 0;
	static int prevCol = 0;
	static long globalSum = 0;
	static long globalMax = 0;
	static int maxI = -1;
	static int maxJ = -1;
	static boolean [][]vis = new boolean[8][8];
	static String[] perm = new String[5];
	static long [][]grid = {
			{8, 7, 1, 2, 1, 3, 2, 1},
			{6, 4, 1, 6, 4, 9, 3, 9},
			{8, 2, 4, 5, 2, 5, 2, 6},
			{9, 4, 2, 9, 4, 2, 2, 3},
			{1, 8, 1, 4, 3, 8, 1, 2},
			{1, 5, 7, 7, 2, 7, 4, 4},
			{5, 6, 2, 9, 3, 4, 6, 5},
			{8, 7, 2, 9, 8, 4, 3, 3}		
	};
	static String []words = {"AMAZON", "WEB", "SERVICES", "ELASTIC", "COMPUTE", "CLOUD", 
			"VIRTUAL", "MACHINE", "IMAGE", "SECURITY","NETWORK", "ACCOUNT", "PASSWORD", "USER",
			"CONSOLE", "XEN", "HYPERVISOR", "GUEST", "SOFTWARE", "MONITOR", "RDS"
	};
	private class Cell{
		int i = 0;
		int j = 0;
		public Cell(int i, int j){
			this.i = i;
			this.j = j;
		}
		public String toString(){
			return (i + " " + j);
		}
	}
	public static Cell firstEmptyCell(String s, int length, int k, int m){
		int count = 0;
		boolean flag = false;
		int pRow = -1;
		int pCol = -1;
		Challenge c = new Challenge();
		long sum = 0;
		int j = m;
		for(int i = k; i < 8; i++){
			for(; j < 8; j++){
				if(count == length)break;
				if(!vis[i][j] && !flag){
					pRow = i;
					pCol = j;
					sum += (grid[i][j] * (s.charAt(count) - 'A' + 1));
					count++;
					flag = true;
					
				}else if(!vis[i][j]){
					sum += (grid[i][j] * (s.charAt(count) - 'A' + 1));
					count++;
				}else{
					flag = false;
					pRow = -1;
					pCol = -1;
					count = 0;
					sum = 0;
				}
				
			}
			j = 0;
		}
		
		if(count != length){
			 pRow = -1;
			 pCol = -1;
			 globalSum = -1;
		}else{
			globalSum  = sum;
		}
		return c.new Cell(pRow, pCol);
	}
	public static long placeWord(String word,int i, int j){
		
		long max = 0;
		Cell c = null;
		
		int startJ = j;
		int startI = i;
		while(true){
			
			if(globalSum > max){
				max = globalSum;
				if(c != null){
				maxI = prevRow;
				maxJ = prevCol;
				}else{
					maxI = i;
					maxJ = j;
				}
			}
		
			if(startJ + 1 >= 8){
				startJ = 0;
				startI = startI + 1;
			}else{
				startJ = startJ + 1;
			}
			
			
			 c = firstEmptyCell(word,word.length(), startI, startJ);
			 
			prevRow = c.i;
			prevCol = c.j;
			
		/*	if(word.equals("VIRTUAL")){
				System.out.println(prevRow + " " + prevCol);
			}*/

			if(prevRow == -1 && prevCol == -1)break;
			
		}
		return max;
	}
	
	public static void setVis(int k, int m, int len){
		int count = 0;
		int j = m;
		for(int i = k; i < 8; i++){
			for(; j < 8; j++){
				if(count == len)break;
				
				/*if(len == 7)
				System.out.println(i + " " + j);*/

				vis[i][j] = true;
				count++;
			}
			j = 0;
		}
		//System.out.println(count);
	}
	
	public static long evalList(){
		long ans = 0l;
		for(int i = perm.length - 1; i >= 0; i--){
			Cell c = firstEmptyCell(perm[i],perm[i].length(), 0, 0);
			prevRow = c.i;
			prevCol = c.j;
			if(prevRow != -1 && prevCol != -1){
			//	System.out.println(perm[i]);
				long cur =  placeWord(perm[i], prevRow, prevCol);
				//System.out.println(cur);
			ans += cur;
			//System.out.println(maxI + " " + maxJ);
			if(maxI != -1 && maxJ != -1){
			/*	if(words[i].equals("VIRTUAL")){
					for(int k = 0; k < 8; k++){
						for(int j = 0; j < 8; j++){
							System.out.print(vis[k][j] == true ? 1 : 0);
						}
						System.out.println();
				}
					System.out.println(maxI + " " + maxJ);
					System.out.println();
				}
				*/
				setVis(maxI, maxJ, perm[i].length());
				/*for(int k = 0; k < 8; k++){
					for(int j = 0; j < 8; j++){
						System.out.print(vis[k][j] == true ? 1 : 0);
					}
					System.out.println();
				}
				System.out.println();*/
				
			}else{
				globalSum = 0;
			}
			}
		}
			return ans;
		
	}
	static int i = 0;
	private static void permutation(int pos) {
	    if (pos == perm.length) {
	    /*	long ans = evalList();
	    	if(ans > globalMax)System.out.println(ans);
	    	globalMax = Math.max(ans, globalMax);
	    	
	    	for(int i = 0; i < 8; i++){
	    		for(int j = 0; j < 8; j++)
	    			vis[i][j] = false;
	    	}*/
	    	System.out.println(i++);
	    } else {
	        for (int i = 0 ; i < words.length ; i++) {
	            perm[pos] = words[i];
	            permutation(pos+1);
	        }
	    }
	}
	
	public static void main(String args[]){
		//System.out.println(firstEmptyCell(65, 0, 0));
		
		/*Arrays.sort(words, new Comparator<String>(){
			public int compare(String a, String b){
				if(b.length() < a.length())return -1;
				else if(b.length() > a.length())return 1;
				return 0;
			}
		});
		for(int i = words.length - 1; i >= 0; i--)
			System.out.print(words[i] + " ");
		*/
		
		permutation(0);
		System.out.println(globalMax);
	}

}
