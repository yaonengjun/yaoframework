package org.oursight.projecteuler.problem82;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem82Others {

	static int[][] grid;
	static int[] sol = new int[80];

	public static void populateArray(String str, int row) {
		// Scanner scan = new Scanner(str);
		int counter = 0;
		String[] data = str.split(",");
		for (int index = 0; index <= data.length - 1; ++index)
			grid[row][counter++] = Integer.parseInt(data[index]);
	}

	public static void assignArray(int no_of_row) {
		grid = new int[no_of_row][no_of_row];
	}

	public static void main(String args[]) throws Exception {
		long start_time = System.currentTimeMillis();
		String thisClassPath = Problem82Answer.class.getResource("").getPath();
		String fileName = thisClassPath + "p082_matrix.txt";

		String lineString = "";
		List listData = new ArrayList();
		BufferedReader data = new BufferedReader(new FileReader(fileName));
		while ((lineString = data.readLine()) != null) {
			listData.add(lineString);
		}

		assignArray(listData.size());
		for (int index = 0, row_counter = 0; index <= listData.size() - 1; ++index, row_counter++)
			populateArray((String) listData.get(index), row_counter);

		int gridSize = grid.length;

		// initialise solution
		for (int i = 0; i < gridSize; i++) {
			sol[i] = grid[i][gridSize - 1];
		}

		for (int i = gridSize - 2; i >= 0; i--) {
			// Traverse down
			sol[0] += grid[0][i];

			for (int j = 1; j < gridSize; j++) {
				sol[j] = Math.min(sol[j - 1] + grid[j][i], sol[j] + grid[j][i]);
			}
//			System.out.println(Arrays.toString(sol));
			// Traverse up
			for (int j = gridSize - 2; j >= 0; j--) {
				sol[j] = Math.min(sol[j], sol[j + 1] + grid[j][i]);
			}
//			System.out.println(Arrays.toString(sol));
//			System.out.println();
		}

		long min_value = sol[0];
		for (int i = 1; i <= gridSize - 1; ++i) {
			if (sol[i] < min_value)
				min_value = sol[i];
		}

		System.out.println(" Matrix[" + grid.length + "][" + grid.length + "] Minimal path sum:" + min_value);
		System.out.println("Total time taken:" + ((System.currentTimeMillis() - start_time)));
		// System.out.println(Arrays.toString(sol));
	}
}
