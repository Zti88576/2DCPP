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
	 //�����
	 List<Integer> ResultA = new ArrayList<>();
	 //������
	 List<Integer> MResultA = new ArrayList<>();

	 //ÿ���߶�Ӧ�ĳ���
	 HashMap<Integer, Double> ss = new HashMap<>();
	 //ÿ���������
	 List<List<String>> Vsets = new ArrayList<>();
	 //ÿ���ߵĳ���

	 //ͨ��ÿ��������еı�
	 List<List<Integer>> Asets = new ArrayList<>();
	 List<Integer> picV = new ArrayList<>();
	 //ͨ��ÿ����������
	 List<Integer> ARsets = new ArrayList<>();
	 //����ͼ�Ŀյ���
	 List<List<Integer>> OLsets = new ArrayList<>();
	 /*//���տյ�����
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
	 
