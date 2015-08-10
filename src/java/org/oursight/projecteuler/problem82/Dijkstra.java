package org.oursight.projecteuler.problem82;

public class Dijkstra {

	public static void main(String[] args) {
		int[][] w2 = { //
		{ 0, 1, 4, 9999, 9999, 9999 }, { 1, 0, 2, 7, 5, 9999 }, { 4, 2, 0, 9999, 1, 9999 }, { 9999, 7, 9999, 0, 3, 2 },
				{ 9999, 5, 1, 3, 0, 6 }, { 9999, 9999, 9999, 2, 6, 0 } };
		int[][] w1 = { //
		{ 0, 6, 3, 9999, 9999, 9999 }, { 6, 0, 2, 5, 9999, 9999 }, { 3, 2, 0, 3, 4, 9999 }, { 9999, 5, 3, 0, 2, 3 },
				{ 9999, 9999, 4, 2, 0, 5 }, { 9999, 9999, 9999, 3, 5, 0 }, };
		/* 用数据进行测试 */
		dijkstra1(w2);

	}

	public static void dijkstra1(int a[][]) {
		boolean isUsed[] = new boolean[a[0].length];// 记录该点是否访问过
		int[] distance = new int[a[0].length];// 记录权值
		int[] prev = new int[a[0].length];// 记录该点的前驱节点

		for (int v = 0; v < (a.length); v++) {// 该循环用来初始化各个变量
			isUsed[v] = false;

			distance[v] = a[0][v];
			if (distance[v] < 0) {
				prev[v] = 9999;
			} else
				prev[v] = 0;
		}

		isUsed[0] = true;// 将初始点标记为访问过
		distance[0] = 0;//
		/* 开始循环 */
		for (int i = 1; i < a[0].length; i++) {
			int mindist = 9999;
			int u = 0;
			/* 该循环用来找到在未访问的节点中的权值最小的点 */
			for (int j = 0; j < (a[0].length); j++) {
				if (!isUsed[j] && (distance[j] != 9999)) {
					if (distance[j] < mindist) {
						u = j;
						mindist = distance[j];
					}
				}
			}
			isUsed[u] = true;
			/* 对于各个未访问的点的权值进行更新，并标记其前驱节点 */
			for (int j = 0; j < a[0].length; j++) {
				if (!isUsed[j] && (a[u][j] + distance[u] < distance[j])) {
					distance[j] = distance[u] + a[u][j];
					prev[j] = u;
				}
			}
		}
		// 打印相关信息
		for (int i = 0; i < a[0].length; i++) {
			System.out.print(distance[i] + "  ");
			// System.out.println();
		}
		System.out.println();
		for (int i = 0; i < a[0].length; i++) {
			System.out.print(prev[i] + "  ");
		}
	}
}
