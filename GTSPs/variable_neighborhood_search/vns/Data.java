package vns;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vns.ReadFile;
import graph.City;

public class Data {

	static Random random = new Random();
	
	//static String path = "E:/ZT/L0005(83.247886%).pack";
	//static String path = "E:/ZT/L0005(84.021087%).pack";
	//static String path = "E:/ZT/L0003_lingjian(83.514210%).pack";
	//static String path = "C:/Users/Administrator/Desktop/排样实例/L0003_lingjian(83.514210%).pack";
	static String path = "C:/Users/Administrator/Desktop/排样实例/swim.pack";
	//String path = "C:/Users/Administrator/Desktop/排样实例/profiles2 - 副本.pack";
	//static String path = "C:/Users/Administrator/Desktop/排样实例/profiles2_0.pack";
	
	
	static int CITY_NUM; //城市数
	static int PART_NUM;//零件数
	static double height;
	static int[] picV;
	static final double[][] disMap; //地图数据
	static List<City> cities;
	static{
		List<Integer> picv = new ArrayList<>();
		cities = new ArrayList<>();
		ReadFile readFile = new ReadFile();
		try {
			readFile.Read_data(path, picv, cities);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(height);
		
		//System.out.println(picv);
		picV = new int[picv.size()];
		for (int i = 0; i < picv.size(); i++) {
			picV[i] = picv.get(i);
		}
		
		PART_NUM = picV.length-1;
		
		double[][] cityPosition = new double[cities.size()][];
		for (int i = 0; i < cities.size(); i++) {
			cityPosition[i] = new double[]{cities.get(i).getX(),cities.get(i).getY()};
		}
	
		//路径集合
		CITY_NUM=cityPosition.length;
		disMap=new double[CITY_NUM][CITY_NUM];
		for(int i=0;i<CITY_NUM;i++)
		{
			for(int j=i;j<CITY_NUM;j++)
			{
				double dis=(double)Math.sqrt(Math.pow((cityPosition[i][0] - cityPosition[j][0]),2) + Math.pow((cityPosition[i][1] - cityPosition[j][1]),2));
				
				disMap[i][j]=dis;
				disMap[j][i]=disMap[i][j];
			}
		}
	}

}
