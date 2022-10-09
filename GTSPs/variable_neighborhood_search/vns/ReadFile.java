package vns;

import java.io.BufferedReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import vns.Data;
import graph.City;

public class ReadFile {
	public void Read_data(String path,List<Integer> picv,List<City> cities) throws Exception{
		String line = null;
		Scanner cin = new Scanner(new BufferedReader(new FileReader(path)));
		
		int count = 0;
		
		int picnum ;
		
		for(int i = 0; i < 2; i++) {
			line = cin.nextLine();
		}
		picnum = Integer.parseInt(line);
		//System.out.println(data.picnum);
		
		for(int i = 0; i < 1; i++) {
			line = cin.nextLine();
		}
		Data.height = Double.parseDouble(line);
		//System.out.println(Data.height);
		
		for(int i = 0; i < 1; i++) {
			line = cin.nextLine();
		}
		//data.width = Double.parseDouble(line);
		//System.out.println(data.width);
		
		
		for(int i = 0; i <3; i++) {
			line = cin.nextLine();
		} 	
	
		picv.add(0, 0);
		
		List<List<String>> V = new ArrayList<>();
		 	for(int i = 0; i < 5*picnum; i++) {
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
					picv.add(cities.size());
				}	           
            
	        }	
		 	//System.out.println(picv);
		cin.close();	
		
		//System.out.println(picv);
		
	}
	
	public void Read_data1(String path,List<Integer> picv,List<City> cities) throws Exception{
		String line = null;
		Scanner cin = new Scanner(new BufferedReader(new FileReader(path)));
		
		int count = 0;
		
		int picnum ;
		
		for(int i = 0; i < 2; i++) {
			line = cin.nextLine();
		}
		picnum = Integer.parseInt(line);
		//System.out.println(data.picnum);
		
		for(int i = 0; i < 1; i++) {
			line = cin.nextLine();
		}
		Data.height = Double.parseDouble(line);
		//System.out.println(Data.height);
		
		for(int i = 0; i < 1; i++) {
			line = cin.nextLine();
		}
		//data.width = Double.parseDouble(line);
		//System.out.println(data.width);
		
		
		for(int i = 0; i <3; i++) {
			line = cin.nextLine();
		} 	
	
		picv.add(0, 0);
		
		List<List<String>> V = new ArrayList<>();
	 	for(int i = 0; i < 5*picnum; i++) {
            line = cin.nextLine();	            
            List<String> substr1 = Arrays.asList(line.split((" ")));	
            if (substr1.size()==1||substr1.size()==2) {
				continue;
			}
            if (substr1.get(2).equals("0")==false&&substr1.size()==(Double.parseDouble(substr1.get(2))+3)) {
				//System.out.println(substr1);
				picv.add((picv.get(picv.size()-1)+Integer.parseInt(substr1.get(0))));
				for (int j = 3; j < substr1.size(); j++) {
					picv.add((picv.get(picv.size()-1)+Integer.parseInt(substr1.get(j))));
				}
			}else if (substr1.get(2).equals("0")==true&&substr1.size()==3) {
				picv.add((picv.get(picv.size()-1)+Integer.parseInt(substr1.get(0))));
			}else {
				V.add(substr1);	
            	if (V.size()==2) {
            		for (int j = 0; j < 1; j++) {
						for (int j2 = 0; j2 < V.get(j).size(); j2++) {
							City city = new City(count, Double.parseDouble(V.get(0).get(j2)), Double.parseDouble(V.get(1).get(j2)));
							count++;
							cities.add(city);
						}
					}
            		V.clear();
				} 
			}          
        }	
		 	//System.out.println(picv);
		cin.close();	
		
		//System.out.println(picv);
		
	}
	
}
