package mainT1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import app.ConnectCircuit;
import app.Data;
import app.GenerateCircuit;
import app.ReadFile;
import app.UndirectedG;
import graph.City;
import graph.Edge;
import graphics.EulerCircuit;
import maing.ConnectComponents;
import maing.FindComponents;
import maing.GenerateMiniCircuit;
import maing.ganM;


public class TSGM1 {

	static Data data = new Data();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		long start=System.currentTimeMillis(); //获取开始时间

		
		EulerCircuit el = new EulerCircuit();
		
		//String path = "C:/Users/Administrator/Desktop/切割路径问题/testInstance/rpp3/add2.pack";
		//String path = "C:/Users/Administrator/Desktop/切割路径问题/testInstance/rpp3/THREE1.txt";		
		//String path = "C:/Users/Administrator/Desktop/切割路径问题/testInstance/rpp3/ll.pack";
		//String path = "C:/Users/Administrator/Desktop/切割路径问题/testInstance/rpp3/pdd.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/blaz2_s.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/dagli.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/dighe1.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/dighe1_s.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/fu.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/dighe2.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/poly2b.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/shapes.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/shapes - 副本.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/shirts.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/swim.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/trousers.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/albano.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/jakobs2.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/mao.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/marques.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/trousers.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/albano.pack";
		
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0003_lingjian(83.514210%).pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0004(84.186735%).pack";
		
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0004(84.356724%) - 副本.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0004(84.356724%) - 副本 - 副本.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0004(84.356724%).pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0005(84.021087%) - 副本 - 副本 - 副本.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0005(84.021087%) - 副本 - 副本.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0005(84.021087%) - 副本.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0005(84.021087%) - 副本 (2).pack";
		
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0005(84.021087%).pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0005(83.225580%).pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0005(83.247886%).pack";
		
		//String path = "C:/Users/Administrator/Desktop/排样实例/profiles2 - 副本.pack";
		String path = "C:/Users/Administrator/Desktop/排样实例/profiles2_2.pack";
		
		ArrayList<City> cities = new ArrayList<>();
		ArrayList<Edge> edges = new ArrayList<>();
		int[][] matrix ;
		
		
		ReadFile rFile = new ReadFile();
		UndirectedG undirectedG = new UndirectedG();
		
		rFile.Read_data1(path, data, cities, edges);
		
		ganM.cities = new ArrayList<>(cities);
		
		System.out.println(data.picV.get(data.picV.size()-1)-1);
		System.out.println("1:"+edges.size());
		matrix = new int[cities.size()][cities.size()];
		undirectedG.Generate_graph(cities, edges, matrix);
		System.out.println("2:"+edges.size());
/*		 for (int i = 0; i < edges.size(); i++) {
				System.out.print(edges.get(i).getFrom()+","+edges.get(i).getTo()+",");
			}

		    System.out.println();*/
		    
		    GenerateCircuit gCircuit = new GenerateCircuit();   
		    
		    double test2 = 0;
			 for (int i = 0; i < edges.size(); i++) {
				double l = gCircuit.pointDistance(cities.get(edges.get(i).getFrom()), cities.get(edges.get(i).getTo()));
				test2 = test2 + l;
			}
			 System.out.println(edges.size());
		    System.out.println("test2:"+test2);  
		
/*		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print((int)matrix[i][j]+"  ");				
			}
			System.out.println();
		}*/
	 
		List<Edge> oList = new ArrayList<>(); 
		//connect the odd-point
		GenerateMiniCircuit generateMiniCircuit = new GenerateMiniCircuit();
		generateMiniCircuit.connect_Cplex(cities.size(), matrix, oList);
		
		System.out.println(oList);
		edges.addAll(oList);
		
		FindComponents findComponents = new FindComponents();
		findComponents.find_components(edges);
		
		List<Edge> edges1 = new ArrayList<>();
		ConnectComponents components = new ConnectComponents();
		components.connect_CbyT(edges1);
		
		oList.addAll(edges1);
		
		edges.addAll(edges1);
	    
	    int[][] rra = new int[edges.size()][];
		 for (int i = 0; i < edges.size(); i++) {
			 rra[i] = new int[]{edges.get(i).getFrom(),edges.get(i).getTo()};
		 }
		 
		 /*for (int i = 0; i < rra.length; i++) {
			System.out.println(rra[i][0]+","+rra[i][1]+",");
		}
		 System.out.println();*/
		 
		 int[] a = new int[rra.length*2];
		 int x = 0;
		 for (int[] i : rra) {
			for (int j : i) {
				a[x++] = j; 					
			}
		}
		 
		 /*for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}*/
		 
			Map<Integer, Integer> map = new HashMap<>();
		      
	        for (int str : a) {
	           Integer num = map.get(str);  
	           map.put(str, num == null ? 1 : num + 1);
	        }
	        Set set = map.entrySet();
	        Iterator it = set.iterator();	 
	        Iterator it01 = map.keySet().iterator();
	        int  y = 0;
	        while (it01.hasNext()) {
	            Object key = it01.next();
	            if (map.get(key)%2!=0) {
					System.out.println(key+"+"+map.get(key));
				}else {
					y++;
					//System.out.println("无");
				}
	} 
		 //System.out.println("y:"+y);
		 
		          
	    // euler(rra, s,cities.size(), edges.get(0).getFrom());   
	    // System.out.println(s);   
		// System.out.println("edge.size"+rra.length);
	 List<Integer> test = new ArrayList<>(el.EulerCircuitByDFS(rra, cities.size(),edges.get(0).getFrom()));  
	    	    
	 /*do {
		 int[][] rra = new int[edges.size()][];
		 for (int i = 0; i < edges.size(); i++) {
			 rra[i] = new int[]{edges.get(i).getFrom(),edges.get(i).getTo()};
		 }
		 List<Integer> ruvv = new ArrayList<>(el.EulerCircuitByDFS(rra, cities.size(),edges.get(0).getFrom()));  
		 tours.add(ruvv);
		 //System.out.println(ruvv);   
		 //System.out.println(ruvv.size());
	
		 List<Edge> edges2 = new ArrayList<>();
		 for (Integer integer : ruvv) {
			 for (Edge edge : edges) {
				if (integer==edge.getFrom()||integer==edge.getTo()) {
					//System.out.println(integer);
					//System.out.println(edge.getFrom()+","+edge.getTo()+" ");
					edges2.add(edge);
				}
			}
		 } 
		 edges.removeAll(edges2); 
	} while (edges.size()!=0);*/
	 
	 
	 //List<Integer> test = new ArrayList<>();
	 //System.out.println(test.size());
	 double test1 = 0;
	 	//System.out.println(test.size());
/*	 	for (int i = 0; i < edges.size(); i++) {
	 		//System.out.println(test.get(i));
			//System.out.println(test.get(i+1));
	 		double l = gCircuit.pointDistance(cities.get(edges.get(i).getFrom()), cities.get(edges.get(i).getTo()));
	 		//System.out.println(l);
	 		test1 = test1 + l;
	 		//System.out.println(test1);
		}*/	
	 for (int i = 0; i < test.size()-1; i++) {
	 		//System.out.println(test.get(i));
			//System.out.println(test.get(i+1));
	 		double l = gCircuit.pointDistance(cities.get(test.get(i)), cities.get(test.get(i+1)));
	 		//System.out.println(l);
	 		test1 = test1 + l;
	 		//System.out.println(test1);
		}
	   
	 	System.out.println("test1:"+test1);
	 	
	 	//System.out.println(data.picnum);
	 	//System.out.println(data.picV.size()-1);
	 	System.out.println(cities.size());
	 	long end=System.currentTimeMillis(); //获取结束时间
		System.out.println("程序运行时间： "+(end-start)*0.001+"s");
	}

}
