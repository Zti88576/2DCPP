package vns;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;



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
	
	
	public void Read_data(String path) throws Exception{
		String line = null;
		Scanner cin = new Scanner(new BufferedReader(new FileReader(path)));
		
		for(int i = 0; i < 1; i++) {
			line = cin.nextLine();
			List<String> substr = Arrays.asList(line.split((" ")));	            
			//System.out.println(substr);
			Data.CITY_NUM =Integer.parseInt(substr.get(1));
		}
		
		for(int i = 0; i < 1; i++) {
			line = cin.nextLine();
			List<String> substr = Arrays.asList(line.split((" ")));	            
			//System.out.println(substr);
			Data.PART_NUM =Integer.parseInt(substr.get(1));
		}
		//System.out.println(partnum);
		
		for(int i = 0; i < 2; i++) {
			line = cin.nextLine();
		}
		
		for(int i = 0; i < Data.PART_NUM; i++) {
			line = cin.nextLine();
			List<String> part = Arrays.asList(line.split((" ")));
			List<String> P = new ArrayList<>();
			for (int j = 1; j < part.size(); j++) {
				P.add(part.get(j));
			}
			
			//part.remove(0);
			Data.PARTS.add(P);
		}

		Data.Weight = new int [Data.CITY_NUM][Data.CITY_NUM];
		
		for (int i = 0; i < Data.CITY_NUM; i++) {
			line = cin.nextLine();
			List<String> substr = Arrays.asList(line.split((" ")));
			for (int j = 0; j < substr.size(); j++) {
				Data.Weight[i][j]= Integer.parseInt(substr.get(j));
			}	
		}
			
		
		cin.close();	
		
		//System.out.println(picv);
		
	}
	
	
}
