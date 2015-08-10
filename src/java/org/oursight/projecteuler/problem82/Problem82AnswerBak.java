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

public class Problem82AnswerBak {

	public static void main(String[] args) {
		String thisClassPath = Problem82AnswerBak.class.getResource("").getPath();
		String fileName = thisClassPath + "p082_matrix.txt";

		BufferedReader file = readFile(fileName);
		List<String> temp = parse(file);
		int matrixSize = temp.size();

		Map<String, Integer> shortestMap = new HashMap<String, Integer>();
		int[][] matrix = initMatrix(temp, shortestMap);

		int[] shortest = new int[matrixSize];

		// 先根据最后一列，初始化一个最短路径
		for (int i = 0; i < matrixSize; i++) {
			shortest[i] = matrix[i][matrixSize - 1];
			shortestMap.put(i + "_" + (matrixSize - 1), shortest[i]);
		}

		// 从倒数第二列开始计算，往前推
		for (int column = matrixSize - 2; column >= 0; column--) {
			// for (int column = matrixSize - 2; column >= 3; column--) {

			// 向下遍历，拿到右、上每个节点比较得到的最小路径

			shortest[0] += matrix[0][column]; // 第0个节点直接向右求和，做为目前它的最短路径
			// shortestMap.put("0_" + column, shortest[0]);
			int first_line_shortest = matrix[0][column] + shortestMap.get(0 + "_" + (column + 1));
			shortestMap.put("0_" + column, first_line_shortest);

			for (int j = 1; j < matrixSize; j++) {
				// shortest[j] = Math.min(shortest[j - 1] + matrix[j][column], shortest[j]
				// + matrix[j][column]); // 向上求和、向右求和，谁大？
				// //
				int up = matrix[j][column] + shortestMap.get((j - 1) + "_" + column);
				int right = matrix[j][column] + shortestMap.get(j + "_" + (column + 1));
				shortest[j] = Math.min(up, right);
				// 向上的最短路径求和、向右求和，谁大？
				// shortest[j] = Math.min(shortestMap.get((j - 1) + "_" + column) +
				// matrix[j][column],
				// shortestMap.get(j + "_" + column) + matrix[j][column]); //
				// 向上的最短路径求和、向右求和，谁大？
				shortestMap.put(j + "_" + column, shortest[j]);
			}

//			System.out.println(shortestMap);

			// 从倒数第2行，向上遍历，拿到右、下每个阶段的最小路径
			for (int j = matrixSize - 2; j >= 0; j--) {
				// 向下求和、和刚才的最最小值相比，哪个小？
				shortest[j] = Math.min(shortest[j + 1] + matrix[j][column], shortestMap.get(j + "_" + column));

				int down = shortestMap.get((j + 1) + "_" + column) + matrix[j][column];

				if (down < shortestMap.get(j + "_" + column)) {
					shortestMap.put(j + "_" + column, down); // 如果更小，则将其放进去
				}

				// 计算出来每个节点的最小路径，然后往前推一行

			}

//			System.out.println(shortestMap.get("0_3"));
//			System.out.println(shortestMap.get("1_3"));
//			System.out.println(shortestMap.get("2_3"));
//			System.out.println(shortestMap.get("3_3"));
//			System.out.println(shortestMap.get("4_3"));
//			System.out.println();
//
//			System.out.println(shortestMap.get("0_0"));
//			System.out.println(shortestMap.get("1_0"));
//			System.out.println(shortestMap.get("2_0"));
//			System.out.println(shortestMap.get("3_0"));
//			System.out.println(shortestMap.get("4_0"));
//			System.out.println();

		}

		int small = 0;
		int flag = 0;
		int[] s = new int[matrixSize];
		for (int k = 0; k < matrixSize; k++) {
			// if(shortestMap.get(k+"_0") > small) {
			// small = shortestMap.get(k+"_0");
			// flag = k;
			// }
			s[k] = shortestMap.get(k + "_0");

		}

		Arrays.sort(s);
		System.out.println();
		System.out.println(Arrays.toString(s));
		// System.out.println(small);
		// System.out.println(flag);

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

	public static int[][] initMatrix(List<String> list, Map shortestMap) {
		int matrixSize = list.size();
		int[][] matrix = new int[matrixSize][matrixSize];
		for (int i = 0; i < list.size(); i++) {
			String line = (String) list.get(i);
			String[] strArray = line.split(",");
			for (int j = 0; j < strArray.length; j++) {
				String str = strArray[j];
				int value = Integer.parseInt(str);
				matrix[i][j] = value;
				// shortestMap.put(value, Integer.toString(i) + "_" +
				// Integer.toString(j));
				shortestMap.put(Integer.toString(i) + "_" + Integer.toString(j), value);
			}
		}

		return matrix;

	}

}
