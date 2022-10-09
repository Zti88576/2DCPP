package vns;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
	
		String path = "C:\\Users\\Administrator\\Desktop\\���\\GTSPILB\\InstancesText";
		//String path = "E:\\ZT\\InstancesText";
		
		
		List<String> File = new ArrayList<>();
		List<Double> S = new ArrayList<>();
		List<Double> Time = new ArrayList<>();
	
		ReadFile readFile = new ReadFile();
		//readFile.Read_data(Data.path);
		ArrayList<File> filelist = readFile.getFiles(path);
		//System.out.println(filelist.size());
		
		for (File file : filelist) {
		
		readFile.Read_data(file.getPath());
		
		System.out.println(file.getName());
		//File.add(file.getName());
		String[] parts = file.getName().split(".t");	
		File.add(parts[0]);
		
		
		System.out.println("City_number"+Data.CITY_NUM);
		System.out.println("Part_number"+Data.PART_NUM);	
		System.out.println("Parts"+Data.PARTS);		
/*		System.out.println("Weight:");
		for (int i = 0; i < Data.Weight.length; i++) {
			for (int j = 0; j < Data.Weight.length; j++) {
				System.out.print(Data.Weight[i][j]+"  ");				
			}
			System.out.println();
		}*/
		
		double time = 0;
		double time1 = 0;
		long start=System.currentTimeMillis(); //��ȡ��ʼʱ��
		
		Individual best = new Individual();
		best.createInitial();
		best.calDistance();
		System.out.println("��ʼ�⣺"+(best.distance));
		
/*		initial.genes[0] = new Point(0,5);
		initial.genes[1] = new Point(1,1);
		initial.genes[2] = new Point(2,13);
        initial.calDistance();*/
		
/*		for (Point point : best.genes) {
			System.out.print("part:"+point.getIndex()+"+vertex:"+point.getX()+",");
			System.out.println();
		}
		System.out.println();*/
		
/*		VND vnd = new VND();
		//vnd.BychangePoint(best);
		
		Shaking shaking = new Shaking();
		shaking.shaking(best);*/
		

		//VNS
		VND vnd = new VND();
		Shaking shake = new Shaking();	
		int max_iterations = 10000;
        int count = 0, it = 0;
		
		do {		
			//System.out.println("VNS��"+(it+1)+"��");
			count++;
			it++;
			Individual better = new Individual();
			better = best.clone();
			best.calDistance();
			//System.out.println("best:"+best.distance);
			
			//����
			shake.shaking(better);
/*				System.out.println("shaking��");
			for (int i : better.genes) {
				System.out.print(i+",");
			}
			System.out.println();
			System.out.println("better1��"+better.distance);	*/
	
			//�������µ�
			Point[] bak = new Point[Data.PART_NUM];
			vnd.variable_neighborhood_descent(better,bak);
			for (int i = 0; i < bak.length; i++) {
				better.genes[i] = bak[i];
			}
			better.calDistance();
/*				System.out.println("VND��");
			for (int i : better.genes) {
				System.out.print(i+",");
			}
			System.out.println();		
			System.out.println("better1��"+better.distance);*/
		
			if (better.distance < best.distance) {
				best = better.clone();
				count = 0;
			}
			best.calDistance();
			//System.out.println("ȫ��best:"+best.distance);
			
			long end1=System.currentTimeMillis(); //��ȡ����ʱ��
			time1 = (end1-start)*0.001;
			//System.out.println(time1);
			for (int j = 0; j < 37; j++) {
				if ((int)time1 == j*100) {
					//System.out.println("����ʱ��"+time1+",����"+(best.distance));
				}
			}
			
			long end=System.currentTimeMillis(); //��ȡ����ʱ��
			time = (end-start)*0.001;
										
			if (time > 3600) {
				break;
			}
							
			for (int j = 0; j < 51; j++) {
				if (it==j*1000) {
					//System.out.println("��������"+it+",����"+(best.distance));
				}
			}
							
		} while (count <= max_iterations);
        
		System.out.println("���Ž�"+(best.distance));
		System.out.println("��������ʱ�䣺 "+time+"s");
		System.out.println();
		
		S.add(best.distance);
		Time.add(time);	
		
		Data.CITY_NUM = 0;
		Data.PART_NUM = 0;
		Data.PARTS = new ArrayList<>();
		Data.Weight = null;
				
	}
		
	 	FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
 
        try {
        	fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\GTSP.csv");
            //fos = new FileOutputStream("E:\\ZT\\GTSP.csv");
 
            //׷��BOM��ʶ
            fos.write(0xef);
            fos.write(0xbb);
            fos.write(0xbf);
 
            osw = new OutputStreamWriter(fos, "UTF-8");
            bw = new BufferedWriter(osw);
 
            for (int i = 0; i < File.size(); i++) {
            	bw.write(File.get(i)+","+S.get(i)+","+Time.get(i));
            	bw.newLine();
			}

            //�ر���
            bw.flush();
            osw.flush();
            fos.flush();
            bw.close();
            osw.close();
            fos.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
		

/*		for (Point point : best.genes) {
			System.out.print("part:"+point.getIndex()+"+vertex:"+point.getX()+",");
			System.out.println();
		}
		System.out.println(best.distance);*/
		
		
/*		Point[] bak = new Point[Data.PART_NUM];
		vnd.variable_neighborhood_descent(initial,bak);
		for (int i = 0; i < bak.length; i++) {
			initial.genes[i] = bak[i];
		}

		//vnd.Byexchange(initial);
		System.out.println();
		initial.calDistance();
		for (Point point : initial.genes) {
			System.out.print("part:"+point.getIndex()+"+vertex:"+point.getX()+",");
			System.out.println();
		}
		System.out.println(initial.distance);*/
	}
	
}
