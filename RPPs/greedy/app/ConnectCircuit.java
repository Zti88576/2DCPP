package app;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import graph.City;
import graph.Edge;
import graphics.*;
import graphics.EulerCircuit.NotFoundException;

public class ConnectCircuit {

	EulerCircuit el = new EulerCircuit();
	GenerateCircuit gCircuit = new GenerateCircuit();
	
	public void connect_circuit(ArrayList<Edge> edges,ArrayList<City> cities,List<Edge> oList) throws NotFoundException {
		 
	
		List<List<Integer>> tours = new ArrayList<>();
		
		//System.out.println("edges.size()1:"+edges.size());
		List<Edge> edges1 = new ArrayList<>();
		for (int i = 0; i < edges.size(); i++) {
			edges1.add(edges.get(i));
		}
		
		//找到子回路集合
		do {
			 int[][] rra = new int[edges1.size()][];
			 for (int i = 0; i < edges1.size(); i++) {
				 rra[i] = new int[]{edges1.get(i).getFrom(),edges1.get(i).getTo()};
			 }
			 List<Integer> ruvv = new ArrayList<>(el.EulerCircuitByDFS(rra, cities.size(),edges1.get(0).getFrom()));  
			 tours.add(ruvv);
			 //System.out.println(ruvv);   
			 //System.out.println(ruvv.size());
			 
			 List<Edge> edges2 = new ArrayList<>();
			 for (Integer integer : ruvv) {
				 for (Edge edge : edges1) {
					if (integer==edge.getFrom()||integer==edge.getTo()) {
						//System.out.println(integer);
						//System.out.println(edge.getFrom()+","+edge.getTo()+" ");
						edges2.add(edge);
					}
				}
			 } 
			 edges1.removeAll(edges2); 
		} while (edges1.size()!=0);
		//} while (edges.size()!=edges2.size());
		System.out.println("子回路集合"+tours);
		System.out.println("子回路个数"+tours.size());
		
		//连接子回路集合
		List<Edge> tEdges = new ArrayList<>();
		while (tours.size()!=1) {			
		List<Integer> aList = new ArrayList<>();
		//List<Integer> rList1 = new ArrayList<>() ;
		//List<Integer> rList2 = new ArrayList<>() ;
		Edge edge1 = null;
		Edge edge2 = null;
		
		List<List<Integer>> rList = new ArrayList<>() ;
		List<Integer> list = new ArrayList<>();
		double fl = Double.MAX_VALUE;
		for (int i = 0; i < tours.size(); i++) {
			for (int j = i+1; j < tours.size(); j++) {
				double f = Double.MAX_VALUE;
				//double f = gCircuit.pointDistance(cities.get(tours.get(i).get(0)), cities.get(tours.get(j).get(0)));
				//System.out.println("f"+","+f);
				for (Integer integer1 : tours.get(i)) {
					for (Integer integer2 : tours.get(j)) {						
						double l = gCircuit.pointDistance(cities.get(integer1), cities.get(integer2));
						if (f>l) {
							f = l;							
							if (fl>f) {
								fl = f;	
								//System.out.println(integer1+","+integer2);
								edge1 = new Edge(integer1, integer2);
								edge2 = new Edge(integer2, integer1);
								list.add(i);
								list.add(j);
								//rList1 = new ArrayList<>(tours.get(i));
								//rList2 = new ArrayList<>(tours.get(j));
								//aList.addAll(rList1);
								//aList.addAll(rList2);
							}						
						}
					}
				}
			}
		}
		if (edge1!=null) {
			//System.out.println(edge1.getFrom()+","+edge1.getTo());
			//edges.add(edge1);
			//edges.add(edge2);
			tEdges.add(edge1);
			tEdges.add(edge2);
		}
		//System.out.println(list);
		
		List<Integer> list1 = new ArrayList<>();		
		list1.add(list.get(list.size()-2));
		list1.add(list.get(list.size()-1));
		//System.out.println(list1);
		
		for (Integer integer : list1) {
			rList.add(tours.get(integer));
			for (Integer integer1 : tours.get(integer)) {
				aList.add(integer1);
			}
		}
		
		tours.removeAll(rList);
		
		removeDuplicate(aList);
		//tours.remove(rList1);
		//tours.remove(rList2);
		tours.add(aList);
		//System.out.println(tours);
		//System.out.println(tours.size());
		} 
		
/*		for (int i = 0; i < tEdges.size(); i++) {
			System.out.print(tEdges.get(i).getFrom()+","+tEdges.get(i).getTo()+" ");
		}
		System.out.println();*/
		
		//System.out.println("tEdges.size()"+tEdges.size());
		oList.addAll(tEdges);
		edges.addAll(tEdges);
	
/*		for (int i = 0; i < edges.size(); i++) {
			System.out.print(edges.get(i).getFrom()+","+edges.get(i).getTo()+" ");
		}
		System.out.println();*/
		
	}
	
	
	//集合去重
	public static void removeDuplicate(List list) {
		HashSet h = new HashSet(list);
		list.clear();
		list.addAll(h);
		}
	
}
