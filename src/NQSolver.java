public class NQSolver {
	//Complexity O(N!) , N = 29 Max.
	private int queens;
	private int[][] grid;
	int sol = 0;
	public NQSolver(int queens) {
		this.queens = queens;
		grid = new int[queens][queens];
	}

	private void printGrid() {
		for (int i = 0; i < queens; i++) {
			for (int j = 0; j < queens; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	private int dx[] = { 2, 2, -2, -2, 1, 1, -1, -1 };
	private int dy[] = { 1, -1, 1, -1, 2, -2, 2, -2 };

	private boolean checkBoundries(int row, int col) {
		if (row >= 0 && row < queens && col >= 0 && col < queens)
			return true;
		return false;
	}

	private boolean check(int row, int col) { // you can adjust it to check for
												// Knight also.
		// check if the row contains any queens.
		for (int i = 0; i < grid[0].length; i++) {
			if (grid[row][i] == 1)
				return false;
		}
		// check for upper left diagonal
		for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
			if (grid[i][j] == 1)
				return false;
		}
		// check lower left diagonal
		for (int i = row, j = col; i < grid.length && j >= 0; i++, j--) {
			if (grid[i][j] == 1)
				return false;
		}
		// No point of checking upper right and lower right because they
		// consider columns not yet assigned.
		/*for (int i = 0; i < 8; i++) {
			int newRow = row + dy[i];
			int newCol = col + dx[i];
			if (checkBoundries(newRow, newCol) && grid[newRow][newCol] == 1)
				return false;

		}*/
		return true;
	}

	public boolean nQSolver(int col) {
		if (col == queens) {
			// printBoard [a valid solution has been found].
			printGrid();
			sol++;
			return true;
		}

		for (int i = 0; i < grid.length; i++) {
			if (check(i, col)) {
				grid[i][col] = 1;
				 nQSolver(col + 1);// generate all solutions.
				//if (nQSolver(col + 1))
					//return true; // generate only one solution
				grid[i][col] = 0;
			}
		}

		return false;// No solution has been found.[So you must backtrack and
						// remove previously assigned queens].
	}

	public static void main(String args[]) {
		
		NQSolver s = new NQSolver(8);
		s.nQSolver(0);
		System.out.println(s.sol);
	}

}