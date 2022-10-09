package maing;

import java.util.ArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import app.GenerateCircuit;
import graph.Edge;
import ilog.concert.IloException;


public class GenerateMiniCircuit {

	GenerateCircuit gCircuit = new GenerateCircuit();
	
	public void connect_Neighbor(List<Edge> edges) throws IloException {
		

		int[][] g = new int[ganM.cities.size()][ganM.cities.size()];
		for (int i = 0; i < ganM.G.length; i++) {
			for (int j = 0; j < ganM.G[i].length; j++) {
				g[i][j] = ganM.G[i][j];
			}
		}
		
		
				
		for (Edge edge : edges) {
			g[edge.getFrom()][edge.getTo()] = 1;
			g[edge.getTo()][edge.getFrom()] = 1;
		}
		
		
		//connect odd_edge
		List<Edge> oddE = new ArrayList<>();
		//connect_Greedy(ganM.cities.size(), g, oddE);
		connect_Cplex(ganM.cities.size(), g, oddE);
		
/*		for (Edge edge : oddE) {
			System.out.println(edge.getFrom()+","+edge.getTo());
		}*/
		
		//System.out.println("beforeN"+oddE.size());
		
/*		for (int count = 0; count < 10000; count++) {
	
			Collections.shuffle(oddE);
			List<Edge> rE = new ArrayList<>();
			List<Edge> aE = new ArrayList<>();
			look:
			for (int i = 0; i < oddE.size(); i++) {
				for (int j = i+1; j < oddE.size(); j++) {					
					double original_Length = gCircuit.pointDistance(ganM.cities.get(oddE.get(i).getFrom()), ganM.cities.get(oddE.get(i).getTo()))
							+ gCircuit.pointDistance(ganM.cities.get(oddE.get(j).getFrom()), ganM.cities.get(oddE.get(j).getTo()));
					double cross_Length = gCircuit.pointDistance(ganM.cities.get(oddE.get(i).getFrom()), ganM.cities.get(oddE.get(j).getTo()))
							+ gCircuit.pointDistance(ganM.cities.get(oddE.get(j).getFrom()), ganM.cities.get(oddE.get(i).getTo()));
					double oppose_Length = gCircuit.pointDistance(ganM.cities.get(oddE.get(i).getFrom()), ganM.cities.get(oddE.get(j).getFrom()))
							+ gCircuit.pointDistance(ganM.cities.get(oddE.get(i).getTo()), ganM.cities.get(oddE.get(j).getTo()));
					//System.out.println(i+","+j);
					
					//System.out.println(oddE.get(i).getFrom()+","+oddE.get(i).getTo());
					//System.out.println(oddE.get(j).getFrom()+","+oddE.get(j).getTo());
					if (cross_Length < oppose_Length) {
						if (cross_Length < original_Length) {
							rE.add(oddE.get(i));
							rE.add(oddE.get(j));						
							Edge e1 = new Edge(oddE.get(i).getFrom(), oddE.get(j).getTo());
							Edge e2 = new Edge(oddE.get(j).getFrom(), oddE.get(i).getTo());
							aE.add(e1);
							aE.add(e2);
							break look;
						}
					}else {
						if (oppose_Length < original_Length) {
							rE.add(oddE.get(i));
							rE.add(oddE.get(j));						
							Edge e1 = new Edge(oddE.get(i).getFrom(), oddE.get(j).getFrom());
							Edge e2 = new Edge(oddE.get(i).getTo(), oddE.get(j).getTo());
							aE.add(e1);
							aE.add(e2);
							break look;
						}
					}
				}
			}
			
			if (rE.size() == 0) {
				break;
			}
			
			for (Edge edge : rE) {
				System.out.print(edge.getFrom()+"+"+edge.getTo()+",");
			}
			System.out.println();
			for (Edge edge : aE) {
				System.out.print(edge.getFrom()+"+"+edge.getTo()+",");
			}
			float rate=(float)Math.random();
			if (rate < 0.8) {
				oddE.removeAll(rE);
				oddE.addAll(aE);
			}
	
			//System.out.println("afterN"+oddE.size());
		}*/


		
/*		for (int i = 0; i < oddE.size()-1; i++) {
			chang(oddE.get(i), oddE.get(oddE.size()-1));
		}*/
		
		//chang(oddE.get(2), oddE.get(3));
			
		edges.addAll(oddE);
	}


	public void connect_Greedy(int Vnum, int[][] matrix,List<Edge> oList) {
		final int MAX_WEIGHT = 0xFFFF;
		//得到奇度数点
				List<Integer> SingularPoint = new ArrayList<>();
			    for (int i = 0; i < Vnum; i++) {
			    	int count = 0;
			    	for (int j = 0; j < Vnum; j++) {
			    		if (matrix[i][j] != 0 && matrix[i][j] != MAX_WEIGHT) {
			                 count ++;
			             }
					}
			    	//System.out.println(count);
			        if (count % 2 != 0) {
			        	//System.out.println(count);
						SingularPoint.add(i);
					}
				}
				  
			   // System.out.println(SingularPoint.size());
			    
		//迭代选择最短空行程
			if (SingularPoint.size()!=0) {
			//空行程边集合	   			 
				 do {
					   double f = Double.MAX_VALUE;
					   Edge edge = null;
					   List<Integer> list = new ArrayList<>();
					   for (int i = 0; i < SingularPoint.size(); i++) {
							for (int j = i+1; j < SingularPoint.size(); j++) {
								//if (i!=j) {
								double l = gCircuit.pointDistance(ganM.cities.get(SingularPoint.get(i)), ganM.cities.get(SingularPoint.get(j)));
									if (f >l) {
										f = l;
										edge = new Edge(SingularPoint.get(i), SingularPoint.get(j));					
									}
								//}
							}
						}
					    //System.out.println("f"+f);
					    if (edge!=null) {
					    	oList.add(edge);
					    	//System.out.println(edge.getFrom()+","+edge.getTo()+" "); 
					    	list.add(edge.getFrom());
						    list.add(edge.getTo());	
						}
					    	     
					    SingularPoint.removeAll(list);
					    //System.out.println(SingularPoint);
				} while (SingularPoint.size()!=0);
				  //System.out.println("oList"+oList);				  
			 }
	
	}
	
	
	public void connect_Cplex(int Vnum, int[][] matrix,List<Edge> oList) throws IloException {
		
		final int MAX_WEIGHT = 0xFFFF;
		//得到奇度数点
				List<Integer> SingularPoint = new ArrayList<>();
			    for (int i = 0; i < Vnum; i++) {
			    	int count = 0;
			    	for (int j = 0; j < Vnum; j++) {
			    		if (matrix[i][j] != 0 && matrix[i][j] != MAX_WEIGHT) {
			                 count ++;
			             }
					}
			    	//System.out.println(count);
			        if (count % 2 != 0) {
			        	//System.out.println(count);
						SingularPoint.add(i);
					}
				}
				  
			   System.out.println(SingularPoint);
			    CpelxForMinCF cpelxForMinCF = new CpelxForMinCF();
			    cpelxForMinCF.CFmodle(SingularPoint, oList);
	}
}
