package maing;

import java.util.ArrayList;
import java.util.List;

public class PrimAlgorithm {

	public void Prim(double[][] side, List<List<Integer>> indexs) {

		char[] element = new char[side.length];
		for (int i = 0; i < side.length; i++) {
			element[i] = (char)(i+48);
		}
		
		System.out.println(element);
		int nodes = element.length;
		
        MGraph mGraph = new MGraph(nodes);
        MinTree minTree = new MinTree();
        minTree.creatTree(mGraph, nodes, element, side);
        minTree.showTree(mGraph);
        minTree.prim(mGraph, 0, indexs);
     
	}
	
	class MinTree {

	    //������
	    public void creatTree(MGraph mGraph, int nodes, char[] element, double[][] side) {
	        for (int i = 0; i < nodes; i++) {
	            mGraph.element[i] = element[i];
	            for (int j = 0; j < nodes; j++) {
	                mGraph.side[i][j] = side[i][j];
	            }
	        }
	    }

	    //����ķ�㷨
	    public void prim(MGraph mGraph, int node,List<List<Integer>> indexs) {
	        int[] nodes = new int[mGraph.nodes]; //���ڱ�Ǳ������Ľڵ�
	         nodes[node] = 1; //����ǰ�ڵ���Ϊ1 ��ʾ�ѷ���
	        //������¼��С·�������ڵ���±�
	        int index1 = -1;
	        int index2 = -1;
	        double minSide = Double.MAX_VALUE; //������֮�Ƚ� �ҵ���С��·��

	        for (int k = 1; k < mGraph.nodes; k++) { //����n���ڵ� ����n - 1 ����
	            for (int i = 0; i < mGraph.nodes; i++) {
	                for (int j = 0; j < mGraph.nodes; j++) {
	                    if (nodes[i] == 1 && nodes[j] == 0 && mGraph.side[i][j] < minSide) {
	                  //      �������Ѿ����ʹ��Ľڵ���(������������д���Ѿ����뵽Vnew�����еĽڵ㣩 ���� δ���ʹ��Ľڵ㣨�����������Ľڵ㣩 �� �ҵ���С��·�� ���䷵��
	                        minSide = mGraph.side[i][j]; //��̱��ϵ�Ȩֵ
	                        //��̱ߵ���������
	                        index1 = i;
	                        index2 = j;
	                    }
	                }
	            }
	            //�ӵ��ڲ�forѭ�������󣬱�ʾ�ҵ�һ����̱�
	            List<Integer> integer = new ArrayList<>();
	            integer.add(mGraph.element[index1]-48);
	            integer.add(mGraph.element[index2]-48);
	            indexs.add(integer);
	            System.out.println("��<" + mGraph.element[index1] + "," + mGraph.element[index2] + ">Ȩֵ��" + minSide);
	            nodes[index2] = 1; //�����ڲ����ѷ��ʽڵ�·����̵Ľڵ�����Ϊ�ѷ��� �����ҳ��Ľڵ���ӵ�Vnew�����У�
	            minSide = 10000; //��minSide�ָ���ʼֵ ��ʼ�´α���
	        }	        
	    }

	    //�����
	    public void showTree(MGraph mGraph) {
	        for (double[] arr : mGraph.side) {
	            for (double element : arr) {
	                System.out.print(element + " ");
	            }
	            System.out.println();
	        }

	    }
	}

	class MGraph {
	    int nodes; //�ڵ�ĸ���
	    char[] element; //�ڵ��Ԫ��
	    double[][] side; //��űߣ��ڽӾ���

	    public MGraph(int nodes) { //������
	        this.nodes = nodes;
	        element = new char[nodes];
	        side = new double[nodes][nodes];
	    }
	}
	
}
