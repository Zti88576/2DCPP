package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import graph.*;
import app.Data;

public class ReadFile {

	public ArrayList<File> getFiles(String path) {
	    ArrayList<File> fileList = new ArrayList<File>();
	    File file = new File(path);
	    if (file.isDirectory()){
	        File files[] = file.listFiles();
	        for (File fileIndex : files){
	            //如果这个文件是目录，则进行递归搜索
	            if (fileIndex.isDirectory()){
	                getFiles((fileIndex.getPath()));
	            }else {
	                //如果是普通文件，则将文件放入集合
	                fileList.add(fileIndex);
	            }
	        }
	    }
	    return fileList;
	}
	
	public void Read_data(String path,Data data,ArrayList<City> cities,ArrayList<Edge> edges) throws Exception{
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
		 	//System.out.println(data.picV);
		cin.close();	
		
		//System.out.println(data.picV);
		for (int i = 0; i < data.picV.size()-1; i++) {
			Edge edge = new Edge(data.picV.get(i), data.picV.get(i+1)-1);
			edges.add(edge);
			//data.ARsets.add(data.Psets.get(data.picV.get(i)).get(data.picV.get(i+1)-1));
			for (int j =data.picV.get(i) ; j < data.picV.get(i+1)-1; j++) {							
				Edge edge1 = new Edge(j, j+1);
				edges.add(edge1);
				//data.ARsets.add(data.Psets.get(j).get(j+1));
			}
		}
		
	}
	
	public void Read_data1(String path,Data data,ArrayList<City> cities,ArrayList<Edge> edges) throws Exception{
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
	            if (substr1.size()==1||substr1.size()==2) {
					continue;
				}
	            if (substr1.get(2).equals("0")==false&&substr1.size()==(Double.parseDouble(substr1.get(2))+3)) {
					//System.out.println(substr1);
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
								City city = new City(count, Double.parseDouble(V.get(0).get(j2)), Double.parseDouble(V.get(1).get(j2)));
								count++;
								cities.add(city);
							}
						}
	            		V.clear();
					} 
				}          
	        }	
		 	//System.out.println(data.picV);
		cin.close();	
		
		//System.out.println(data.picV);
		for (int i = 0; i < data.picV.size()-1; i++) {
			Edge edge = new Edge(data.picV.get(i), data.picV.get(i+1)-1);
			edges.add(edge);
			//data.ARsets.add(data.Psets.get(data.picV.get(i)).get(data.picV.get(i+1)-1));
			for (int j =data.picV.get(i) ; j < data.picV.get(i+1)-1; j++) {							
				Edge edge1 = new Edge(j, j+1);
				edges.add(edge1);
				//data.ARsets.add(data.Psets.get(j).get(j+1));
			}
		}
		
	}
	
	
	
}
