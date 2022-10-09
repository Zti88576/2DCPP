package app;

import java.awt.event.MouseWheelEvent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import graph.City;
import graph.Edge;

public class UndirectedG {

	
	public void Generate_graph(ArrayList<City> cities,ArrayList<Edge> edges,int[][] matrix) {
		
		
		/*for (Edge edge : edges) {
			System.out.print(edge.getFrom()+","+edge.getTo()+" ");
		}
		System.out.println();*/

		
		//重复点替换
		for (int i = 0; i < cities.size(); i++) {
			for (int j = 0; j < cities.size(); j++) {
				if (i!=j&&check(cities.get(i), cities.get(j))) {
					for (Edge edge : edges) {
						if (edge.getFrom()==cities.get(i).getIndex()) {
							edge.setFrom(cities.get(j).getIndex());
						}else if (edge.getTo()==cities.get(i).getIndex()) {
							edge.setTo(cities.get(j).getIndex());
						}
					}
				}
			}
		}
		
		for (Edge edge : edges) {
			//System.out.print(edge.getFrom()+","+edge.getTo()+" ");
		}
		System.out.println();
		
		List<Integer> cityindex = new ArrayList<>();
		for (Edge edge : edges) {
			cityindex.add(edge.getFrom());
			cityindex.add(edge.getTo());
		}
		removeDuplicate(cityindex);
	
		
		int intersection;		
		do {		
		intersection = 0;
		List<Edge> Inedges = new ArrayList<>();
		List<Edge> Outedges = new ArrayList<>();
		
		//相交的点与边
		for (int i = 0; i < edges.size(); i++) {
			for (Integer city : cityindex) {
				if (isOnLine(cities.get(city), cities.get(edges.get(i).getFrom()), cities.get(edges.get(i).getTo()))==true) {
					intersection++;
					//System.out.println(city);
					//System.out.println(edges.get(i).getFrom()+","+edges.get(i).getTo()+" ");
					Outedges.add(edges.get(i));
		
					Edge edge1 = new Edge(city, edges.get(i).getFrom());
					Edge edge2 = new Edge(city, edges.get(i).getTo());
					Inedges.add(edge1);
					Inedges.add(edge2);
							
					
				}
			}
		}
		
		for (Edge edge : edges) {
			//System.out.print(edge.getFrom()+","+edge.getTo()+" ");
		}
		//System.out.println();
		

		edges.removeAll(Outedges);
		//System.out.println(intersection+","+Inedges.size());
		edges.addAll(Inedges);	
		
		/*for (Edge edge : edges) {
			System.out.print(edge.getFrom()+","+edge.getTo()+" ");
		}
		System.out.println();*/
		
	} while (intersection != 0);
		
		
		List<List<Integer>> Integers = new ArrayList<>();
		List<Edge> Inedges = new ArrayList<>();
		List<Edge> Outedges = new ArrayList<>();
		//先删除起始点相同的重复边
		for (int i = 0; i < edges.size(); i++) {
			for (int j = i+1; j < edges.size(); j++) {
				if (i!=j) {
					if (edges.get(i).getFrom()==edges.get(j).getFrom()&&edges.get(i).getTo()==edges.get(j).getTo()) {
						//System.out.println(edges.get(i).getFrom()+","+edges.get(i).getTo()+" ");
						Outedges.add(edges.get(i));
						List<Integer> integer = new ArrayList<>();
						integer.add(edges.get(i).getFrom());
						integer.add(edges.get(i).getTo());
						Integers.add(integer);
					}
				}
			}
		}

		edges.removeAll(Outedges);
		removeDuplicate(Integers);
		
		for (int i = 0; i < Integers.size(); i++) {
			//System.out.println(Integers.get(i));
			Edge edge = new Edge(Integers.get(i).get(0), Integers.get(i).get(1));
			Inedges.add(edge);
		}
		
		edges.addAll(Inedges);
		Outedges.clear();
		
		//再删除起始点相反的重复边
		for (int i = 0; i < edges.size(); i++) {
			for (int j = i+1; j < edges.size(); j++) {
				if (i!=j) {
					if (edges.get(i).getTo()==edges.get(j).getFrom()&&edges.get(i).getFrom()==edges.get(j).getTo()) {
						//System.out.println(edges.get(i).getFrom()+","+edges.get(i).getTo()+" ");
						Outedges.add(edges.get(i));
					}
				}
			}
		}
		edges.removeAll(Outedges);
		
		/*for (Edge edge : edges) {
		System.out.print(edge.getFrom()+","+edge.getTo()+" ");
		}
		System.out.println();*/	
		//形成邻接矩阵
		for (Edge edge : edges) {
			matrix[edge.getFrom()][edge.getTo()] = 1;
			matrix[edge.getTo()][edge.getFrom()] = 1;
			
/*			matrix[edge.getFrom()][edge.getTo()] = Math.sqrt(Math.pow(cities.get(edge.getFrom()).getX()
					- cities.get(edge.getTo()).getX(), 2)
					+ Math.pow( cities.get(edge.getFrom()).getY()
					- cities.get(edge.getTo()).getY(), 2)); 
			matrix[edge.getTo()][edge.getFrom()] = Math.sqrt(Math.pow(cities.get(edge.getFrom()).getX()
					- cities.get(edge.getTo()).getX(), 2)
					+ Math.pow( cities.get(edge.getFrom()).getY()
					- cities.get(edge.getTo()).getY(), 2)); */
		}
		

		
	}
	
	//判断相同点
	public  boolean check(City city1, City city2) { 
		if (city1.getX()==city2.getX()&&city1.getY()==city2.getY()) {
				return true;
		}
		return false;
	}
	
	//判断点是否在边上
	public boolean isOnLine(City city,City city1,City city2){		
		if (city.getX()==city1.getX()&&city.getX()==city2.getX()&&Math.min(city1.getY() , city2.getY()) < city.getY() && city.getY()< Math.max(city1.getY() , city2.getY())) {
			return true;
		}else if (city.getY()==city1.getY()&&city.getY()==city2.getY()&&Math.min(city1.getX() , city2.getX()) < city.getX() && city.getX() < Math.max(city1.getX() , city2.getX())) {
			return true;
		}else if (Math.abs((city2.getY()-city.getY())/(city2.getX()-city.getX())-(city.getY()-city1.getY())/(city.getX()-city1.getX()))<=1e-2) {
			if(Math.min(city1.getX() , city2.getX()) < city.getX() && city.getX() < Math.max(city1.getX() , city2.getX())){
				if(Math.min(city1.getY() , city2.getY()) <=city.getY() && city.getY()<= Math.max(city1.getY() , city2.getY())){
				return true;
				}
			}
		}

		return false;
			
	}
	
	//集合去重
	public static void removeDuplicate(List list) {
		HashSet h = new HashSet(list);
		list.clear();
		list.addAll(h);
		}
	
}
