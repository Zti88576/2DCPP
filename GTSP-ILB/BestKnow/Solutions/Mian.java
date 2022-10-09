package Solutions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Mian {
	
	static String v;
	static String p;
	
	static String solution;
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		String path = "C:\\Users\\Administrator\\Desktop\\…Û∏Â\\GTSP-ILB\\InstancesText";
		//String path = "C:\\Users\\Administrator\\Desktop\\…Û∏Â\\GTSP-ILB\\SolutionsText";
		
		//String path = "C:\\Users\\Administrator\\Desktop\\…Û∏Â\\GTSP-ILB\\SolutionsText\\3burma14.txt";

		ReadFile readFile = new ReadFile();
		
		ArrayList<File> filelist = readFile.getFiles(path);
		List<String> P = new ArrayList<>();
		List<String> V = new ArrayList<>();
		List<String> S = new ArrayList<>();
		
		
		for (File file : filelist) {
			
			readFile.Read_data1(file.getPath());
			//System.out.println(solution);
			//S.add(solution);	
			P.add(p);
			V.add(v);
		}
		
	 	FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
 
        try {
            fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\GTSP.csv");
 
            //◊∑º”BOM±Í ∂
            fos.write(0xef);
            fos.write(0xbb);
            fos.write(0xbf);
 
            osw = new OutputStreamWriter(fos, "UTF-8");
            bw = new BufferedWriter(osw);
 
            for (int i = 0; i < P.size(); i++) {
            	bw.write(P.get(i)+","+V.get(i));
            	bw.newLine();
			}

            //πÿ±’¡˜
            bw.flush();
            osw.flush();
            fos.flush();
            bw.close();
            osw.close();
            fos.close();
        }catch(Exception e) {
            e.printStackTrace();
        }	
		
	}

}
