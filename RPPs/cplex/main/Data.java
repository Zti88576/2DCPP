package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Data {
	 int vertexnum;
	 int picnum;
	 int edgenum;
	 double width;
	 double height;
	 double solutionvalue;
	 //结果边
	 List<Integer> ResultA = new ArrayList<>();
	 //多结果边
	 List<Integer> MResultA = new ArrayList<>();

	 //每条边对应的长度
	 HashMap<Integer, Double> ss = new HashMap<>();
	 //每个点的坐标
	 List<List<String>> Vsets = new ArrayList<>();
	 //每条边的长度

	 //通过每个点的所有的边
	 List<List<Integer>> Asets = new ArrayList<>();
	 List<Integer> picV = new ArrayList<>();
	 //通过每个点的所需边
	 List<Integer> ARsets = new ArrayList<>();
	 //各个图的空刀边
	 List<List<Integer>> OLsets = new ArrayList<>();
	 /*//最终空刀集合
	 List<List<Double>> ROLsets = new ArrayList<>();*/	 
	 
	 
	 public static class Edge {

			private int x;
			private int y;
			private int index;

			public Edge(int index, int x, int y) {
				this.index = index;
				this.x = x;
				this.y = y;
			}

			public int getIndex() {
				return index;
			}

			public int getX() {
				return x;
			}

			public int getY() {
				return y;
			}
			
		}
	 
	 
}
	 
