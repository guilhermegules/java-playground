package playground.model;

public class MultiDimensionalArray {
	
	private int[][] grid = {
				{3, 6, 999},
				{2, 2},
				{1, 2, 3, 4}	
	};
	
	public void showMultiDimensional() {
		for(int row = 0; row < grid.length; row++) {
			for(int col = 0; col < grid[row].length; col++) {
				System.out.print(grid[row][col] + "\t");
			}
			System.out.println();
		}
	}
}
