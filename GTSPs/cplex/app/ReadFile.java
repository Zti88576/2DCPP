package app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import graph.City;
import graph.Edge;
import app.Data;

public class ReadFile {

	
	public void Read_data(String path,Data data,ArrayList<City> cities,List<List<City>> Kcities) throws Exception{
		String line = null;
		Scanner cin = new Scanner(new BufferedReader(new FileReader(path)));
		
		int count = 0;
		
		for(int i = 0; i < 2; i++) {
			line = cin.nextLine();
		}
		data.picnum = Integer.parseInt(line);
		//System.out.println(data.picnum);
		
		for(int i = 0; i < 1; i++) {
			line = cin.nextLine();
		}
		data.height = Double.parseDouble(line);
		//System.out.println(data.height);
		
		for(int i = 0; i < 1; i++) {
			line = cin.nextLine();
		}
		data.width = Double.parseDouble(line);
		//System.out.println(data.width);
		
		
		for(int i = 0; i <3; i++) {
			line = cin.nextLine();
		} 	
	
			
		data.picV.add(0, 0);
		
		List<List<String>> V = new ArrayList<>();
		 	for(int i = 0; i < 5*data.picnum; i++) {
	            line = cin.nextLine();	            
	            List<String> substr1 = Arrays.asList(line.split((" ")));	            
	            if (substr1.size()==2) {
					continue;
				}
	            if (substr1.size()!=1) {	            	
	            	V.add(substr1);		            	
				}else {
					//System.out.println(V);
					for (int j = 0; j < 1; j++) {
						for (int j2 = 0; j2 < V.get(j).size(); j2++) {																											
							City city = new City(count, Double.parseDouble(V.get(0).get(j2)), Double.parseDouble(V.get(1).get(j2)));
							count++;
							cities.add(city);							
						}
					}
					V.clear();
					data.picV.add(cities.size());
				}	           
            
	        }	
		 	System.out.println(data.picV);

		cin.close();	
		
		data.vertexnum = data.picV.get(data.picV.size()-1);
		//System.out.println(data.vertexnum);
		//System.out.println(data.picV);
		
/*		for (City city : cities) {
			//System.out.print("{"+city.getX()+","+city.getY()+"}"+",");
			System.out.println(city.getIndex()+","+city.getX()+","+city.getY());
		}
		System.out.println();*/
		
		for (int i = 0; i < data.picV.size()-1; i++){			
			List<Integer> part = new ArrayList<>();
			for (int j =data.picV.get(i) ; j < data.picV.get(i+1); j++) {							
				part.add(j);
			}
			data.parts.add(part);
		}
		//System.out.println(data.parts);
		
		for (List<Integer> list : data.parts) {
			List<City> cList = new ArrayList<>(cities);
				for (Integer i : list) {
					cList.remove(cities.get(i));
				}
			Kcities.add(cList);
		}
				
/*		for (int i = 0; i < Kcities.size(); i++) {
			System.out.println(i);
			for (City city2 : Kcities.get(i)) {
				System.out.println(city2.getIndex()+","+city2.getX()+","+city2.getY());
			}	
		}*/
				
		
	}
	
}
