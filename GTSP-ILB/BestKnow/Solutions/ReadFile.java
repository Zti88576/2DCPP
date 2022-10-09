package Solutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import vns.Data;
import vns.Main;

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
		}
		
		for(int i = 0; i < 1; i++) {
			line = cin.nextLine();
			Mian.solution = line;
		}
		cin.close();	
		
		//System.out.println(picv);
		
	}
	
	public void Read_data1(String path) throws Exception{
		String line = null;
		Scanner cin = new Scanner(new BufferedReader(new FileReader(path)));
		
		for(int i = 0; i < 1; i++) {
			line = cin.nextLine();
			List<String> substr = Arrays.asList(line.split((" ")));	            
			//System.out.println(substr);
			Mian.v =substr.get(1);
		}
		
		for(int i = 0; i < 1; i++) {
			line = cin.nextLine();
			List<String> substr = Arrays.asList(line.split((" ")));	            
			//System.out.println(substr);
			Mian.p =substr.get(1);
		}
		cin.close();	
		
		//System.out.println(picv);
		
	}
	

	
}
