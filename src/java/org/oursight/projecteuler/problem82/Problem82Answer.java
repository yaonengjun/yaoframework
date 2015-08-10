package org.oursight.projecteuler.problem82;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The answer for https://projecteuler.net/index.php?section=problems&id=82
 * in the default matrix in p082_matrix.txt, the result is 260324
 * @author yaonengjun,Apr 19, 2015 9:30:17 AM
 *
 */
public class Problem82Answer {

	public static void main(String[] args) {
		String thisClassPath = Problem82Answer.class.getResource("").getPath();
		String fileName = thisClassPath + "p082_matrix.txt";

		BufferedReader file = readFile(fileName);
		List<String> temp = parse(file);
		int matrixSize = temp.size();
		long start = System.currentTimeMillis();

		Map<String, Integer> shortestMap = new HashMap<String, Integer>();
		int[][] matrix = initMatrix(temp, shortestMap);

		// 从倒数第二列开始计算，往前倒推
		for (int column = matrixSize - 2; column >= 0; column--) {

			// 向下遍历，拿到右、上每个节点比较得到的最小路径
			// 第0个节点直接向右求和，做为目前它的最短路径
			int first_line_shortest = matrix[0][column] + shortestMap.get(0 + "_" + (column + 1));
			shortestMap.put("0_" + column, first_line_shortest);

			for (int j = 1; j < matrixSize; j++) {
				// 向上的最短路径求和、向右求和，谁小？
				int up = matrix[j][column] + shortestMap.get((j - 1) + "_" + column);
				int right = matrix[j][column] + shortestMap.get(j + "_" + (column + 1));
				// 把二者中较小的存起来，得到最短路径
				shortestMap.put(j + "_" + column, Math.min(up, right));
			}

			// System.out.println(shortestMap);

			// 从倒数第2行，向上遍历，作为向下便利时漏掉的路径的补充
			for (int j = matrixSize - 2; j >= 0; j--) {
				// 向下求和、和刚才的最小值相比，哪个小？
				int down = matrix[j][column] + shortestMap.get((j + 1) + "_" + column);
				if (down < shortestMap.get(j + "_" + column)) {
					shortestMap.put(j + "_" + column, down); // 如果更小，则将其放进去
				}

			}
		}

		int[] s = new int[matrixSize];
		for (int k = 0; k < matrixSize; k++) {
			s[k] = shortestMap.get(k + "_0");
		}

		Arrays.sort(s);
		System.out.println("Final result is: " + s[0]);

		long end = System.currentTimeMillis();
		System.out.println("Time cost: " + (end - start));

	}

	public static BufferedReader readFile(String fileName) {
		BufferedReader file = null;
		try {
			file = new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.err.println("Load file " + fileName + " error");
			e.printStackTrace();
		}

		return file;
	}

	public static List<String> parse(BufferedReader file) {
		List<String> temp = new ArrayList<String>();
		String line = null;
		try {
			while ((line = file.readLine()) != null) {
				temp.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public static int[][] initMatrix(List<String> list, Map<String, Integer> shortestMap) {
		int matrixSize = list.size();
		int[][] matrix = new int[matrixSize][matrixSize];
		for (int i = 0; i < list.size(); i++) {
			String line = (String) list.get(i);
			String[] strArray = line.split(",");
			for (int j = 0; j < strArray.length; j++) {
				String str = strArray[j];
				int value = Integer.parseInt(str);
				matrix[i][j] = value;
				// 同时初始化shortestMap
				shortestMap.put(Integer.toString(i) + "_" + Integer.toString(j), value);
			}
		}

		return matrix;

	}

}
