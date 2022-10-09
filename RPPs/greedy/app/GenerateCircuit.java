package app;

import java.util.ArrayList;
import java.util.List;

import graph.*;

public class GenerateCircuit {

	final int MAX_WEIGHT = 0xFFFF;
	public void generate_cycle(ArrayList<City> cities,int[][] matrix,List<Edge> oList) {
	
		
		//得到奇度数点
				List<Integer> SingularPoint = new ArrayList<>();
			    for (City city : cities) {
					int count = 0;
					for(int i = 0; i < cities.size(); i++ ) {
			             if (matrix[city.getIndex()][i] != 0 && matrix[city.getIndex()][i] != MAX_WEIGHT) {
			                 count ++;
			             }
			        }
			        //System.out.println(count);
			        if (count % 2 != 0) {
			        	//System.out.println(count);
						SingularPoint.add(city.getIndex());
					}
				}   
			    //System.out.println(SingularPoint);
			    
		//迭代选择最短空行程
			if (SingularPoint.size()!=0) {
			//空行程边集合	   			 
				 do {
					   double f = pointDistance(cities.get(SingularPoint.get(0)), cities.get(SingularPoint.get(1)));
					   Edge edge = null;
					   List<Integer> list = new ArrayList<>();
					   for (int i = 0; i < SingularPoint.size(); i++) {
							for (int j = i+1; j < SingularPoint.size(); j++) {
								//if (i!=j) {
									double l = pointDistance(cities.get(SingularPoint.get(i)), cities.get(SingularPoint.get(j)));
									if (f >l||f==l) {
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
	
	public double pointDistance(City city1,City city2) {
		return Math.sqrt(Math.pow(city1.getX()
				- city2.getX(), 2)
				+ Math.pow( city1.getY()
				- city2.getY(), 2));
	}
	
}
