package main;

import java.io.BufferedReader;

import java.io.FileReader;
import java.rmi.dgc.Lease;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import main.Data;

public class DataR {
	public void Read_data(String path,Data data) throws Exception{
		String line = null;
		Scanner cin = new Scanner(new BufferedReader(new FileReader(path)));
		
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
	            	System.out.println(substr1);
	            	V.add(substr1);		            	
				}else {
					System.out.println(V);
					for (int j = 0; j < 1; j++) {
						for (int j2 = 0; j2 < V.get(j).size(); j2++) {
							List<String> VV = new ArrayList<>();
							VV.add(V.get(0).get(j2));
							VV.add(V.get(1).get(j2));
							data.Vsets.add(VV);
						}
					}
					V.clear();
					data.picV.add(data.Vsets.size());
				}	           
            
	        }	
		 	//System.out.println(data.picV);
		 	//System.out.println("data.Vsets"+data.Vsets);
	
		cin.close();	
	}
	
	public void Read_data1(String path,Data data) throws Exception{
		String line = null;
		Scanner cin = new Scanner(new BufferedReader(new FileReader(path)));
		
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
		System.out.println(data.width);
		
		
		for(int i = 0; i <3; i++) {
			line = cin.nextLine();
		} 	
	
		data.picV.add(0, 0);

		List<List<String>> V = new ArrayList<>();
		 	for(int i = 0; i < 5*data.picnum; i++) {
	            line = cin.nextLine();	            
	            List<String> substr1 = Arrays.asList(line.split((" ")));	
	            if (substr1.size()==1||substr1.size()==2) {
					continue;
				}
	            if (substr1.get(2).equals("0")==false&&substr1.size()==(Double.parseDouble(substr1.get(2))+3)) {
					System.out.println(substr1);
					data.picV.add((data.picV.get(data.picV.size()-1)+Integer.parseInt(substr1.get(0))));
					for (int j = 3; j < substr1.size(); j++) {
						data.picV.add((data.picV.get(data.picV.size()-1)+Integer.parseInt(substr1.get(j))));
					}
				}else if (substr1.get(2).equals("0")==true&&substr1.size()==3) {
					data.picV.add((data.picV.get(data.picV.size()-1)+Integer.parseInt(substr1.get(0))));
				}else {
					V.add(substr1);	
	            	if (V.size()==2) {
	            		for (int j = 0; j < 1; j++) {
							for (int j2 = 0; j2 < V.get(j).size(); j2++) {
								List<String> VV = new ArrayList<>();
								VV.add(V.get(0).get(j2));
								VV.add(V.get(1).get(j2));
								data.Vsets.add(VV);
							}
						}
	            		V.clear();
					} 
				}        
	        }	
		 	System.out.println(data.picV);
		 	//System.out.println("data.Vsets"+data.Vsets);
	
		cin.close();	
	}
}
