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

	    //创建树
	    public void creatTree(MGraph mGraph, int nodes, char[] element, double[][] side) {
	        for (int i = 0; i < nodes; i++) {
	            mGraph.element[i] = element[i];
	            for (int j = 0; j < nodes; j++) {
	                mGraph.side[i][j] = side[i][j];
	            }
	        }
	    }

	    //普利姆算法
	    public void prim(MGraph mGraph, int node,List<List<Integer>> indexs) {
	        int[] nodes = new int[mGraph.nodes]; //用于标记遍历过的节点
	         nodes[node] = 1; //将当前节点标记为1 表示已访问
	        //用来记录最小路径两个节点的下标
	        int index1 = -1;
	        int index2 = -1;
	        double minSide = Double.MAX_VALUE; //遍历与之比较 找到最小的路径

	        for (int k = 1; k < mGraph.nodes; k++) { //若有n个节点 则有n - 1 条边
	            for (int i = 0; i < mGraph.nodes; i++) {
	                for (int j = 0; j < mGraph.nodes; j++) {
	                    if (nodes[i] == 1 && nodes[j] == 0 && mGraph.side[i][j] < minSide) {
	                  //      代表在已经访问过的节点中(即本博客上文写到已经加入到Vnew集合中的节点） 遍历 未访问过的节点（即满足条件的节点） 并 找到最小的路径 将其返回
	                        minSide = mGraph.side[i][j]; //最短边上的权值
	                        //最短边的两个顶点
	                        index1 = i;
	                        index2 = j;
	                    }
	                }
	            }
	            //从第内层for循环出来后，表示找到一条最短边
	            List<Integer> integer = new ArrayList<>();
	            integer.add(mGraph.element[index1]-48);
	            integer.add(mGraph.element[index2]-48);
	            indexs.add(integer);
	            System.out.println("边<" + mGraph.element[index1] + "," + mGraph.element[index2] + ">权值：" + minSide);
	            nodes[index2] = 1; //将最内层与已访问节点路径最短的节点设置为已访问 （将找出的节点添加到Vnew集合中）
	            minSide = 10000; //将minSide恢复初始值 开始下次遍历
	        }	        
	    }

	    //输出树
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
	    int nodes; //节点的个数
	    char[] element; //节点的元素
	    double[][] side; //存放边，邻接矩阵

	    public MGraph(int nodes) { //构造器
	        this.nodes = nodes;
	        element = new char[nodes];
	        side = new double[nodes][nodes];
	    }
	}
	
}
