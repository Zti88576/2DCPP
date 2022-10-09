package vns;

import java.util.ArrayList;


import java.util.List;


import javax.xml.transform.Templates;

import gC.TSPData;
import graph.City;
import graph.Edge;


public class Individual {
	
	public int[] genes;//基因序列
	public double distance;//路程
	
	public Individual()
	{
		//初始化
		this.genes=new int[Data.PART_NUM];
		this.distance=0.0f;
	}
	
	//计算长度
	public void calDistance(){
		double totalDis=0.0f;
		for(int i = 0;i < Data.PART_NUM;i++)
		{
			int curCity=this.genes[i];
			int nextCity=this.genes[(i+1) % Data.PART_NUM];
			//System.out.println(curCity+"+"+nextCity+"="+Data.disMap[curCity][nextCity]);
			
			totalDis += Data.disMap[curCity][nextCity];
		}
		
		this.distance=totalDis;
	}
	
	public Individual clone()
	{	
		Individual species= new Individual();
		
		//复制值
		for(int i=0;i<this.genes.length;i++)
			species.genes[i]=this.genes[i];
		species.distance=this.distance;
	
		return species;	
	}
	
	//得到初始解
	void createInitial() {
		
/*		List<List<Integer>> id = new ArrayList<>();
		for (int i = 0; i < Data.cities.size(); i++) {
			for (int j = i+1; j < Data.cities.size(); j++) {
				if (i!=j&&check(Data.cities.get(i), Data.cities.get(j))) {
					List<Integer> x1 = new ArrayList<>();
					x1.add(i);
					x1.add(j);
					id.add(x1);
				}
			}
		}
		System.out.println(id);*/

		int i = Data.random.nextInt(Data.CITY_NUM);
		//i = 46;
		genes[0] = i;
		int j;//终点
		int partNum = 0;
		List<Integer> cities = new ArrayList<>();
		for (int k = 0; k < Data.CITY_NUM; k++) {
			cities.add(k);
		}
		
		do{
			partNum++;
			
			List<Integer> Rcities = new ArrayList<>();
			for (int k = 0; k < Data.picV.length; k++) {
				if (Data.picV[k]<=i&&i<Data.picV[k+1]) {
					for (int k2 = Data.picV[k]; k2 < Data.picV[k+1]; k2++) {
						//System.out.println("去掉你"+k2);
						Rcities.add(k2);
					}
				}
			}
			cities.removeAll(Rcities);
			Rcities.clear();
				
			//选出单源最短城市
			double minDis = Float.MAX_VALUE;
			int minCity = 0;
			for(j=0;j<cities.size();j++)
			{
				if(cities.get(j) != i)
				{
					//判是否和已有重复
					boolean repeat=false;
					for(int n=0;n<partNum;n++)
					{
						if(genes[n] == cities.get(j))
						{
							repeat=true;//重了
							break;
						}
					}
					if(repeat == false)//没重
					{
						//判长度
						if(Data.disMap[i][cities.get(j)] < minDis)
						{
							minDis=Data.disMap[i][cities.get(j)];
							minCity=cities.get(j);							
						}
					}
				}
			}
			
			//加入到染色体
			genes[partNum]=minCity;
			i=minCity;			
		}while(partNum<Data.PART_NUM-1);	
		
		
/*		for(int j1=0;j1<genes.length;j1++)
		{
			int num= Data.random.nextInt(Data.picV[j1+1]-Data.picV[j1]) + Data.picV[j1];
			//交换
			int tmp;
			tmp=num;
			genes[j1] = tmp;
		}*/
		
	}
	
	//换点
	void changePoint(Individual individual, int i, int x){
		individual.genes[i] = x;
	}
	
	//反转
	void reverse(Individual individual, int left, int right){	
		if(left > right)
        {
            int tmp;
            tmp=left;
            left=right;
            right=tmp;
        }
		while (left < right) {
			int tmp;
			tmp = individual.genes[left];
			individual.genes[left]=individual.genes[right];
			individual.genes[right]=tmp;
			left++;
			right--;
			
		}
	}
	
	//插入
	void insert(Individual individual, int i, int j){
		//System.out.println(i+","+j);
		List<Integer> front = new ArrayList<>();
		List<Integer> behind = new ArrayList<>();
		List<Integer> list = new ArrayList<>();
		for (int k = 0; k < individual.genes.length; k++) {
			if (k<j) {
				if (k!=i) {
					front.add(individual.genes[k]);
				}				
			}else {
				if (k!=i) {
					behind.add(individual.genes[k]);
				}
			}
		}
		//System.out.println(individual.genes[i]);
		//System.out.println(front+"+"+behind);
		if (j == 0) {
			list.add(individual.genes[i]);
			list.addAll(behind);
		}else {
			list.addAll(front);
			list.add(individual.genes[i]);
			if (behind.size()!=0) {
				list.addAll(behind);
			}
		}
		//System.out.println(list);
		for (int k = 0; k < list.size(); k++) {
			individual.genes[k] = list.get(k);
		}
	}
	
	//交换
	void exchange(Individual individual, int i, int j){
		int tmp = individual.genes[i];
		individual.genes[i] = individual.genes[j];
		individual.genes[j] = tmp;
	}
	
	public  boolean check(City city1, City city2) { 
		if (city1.getX()==city2.getX()&&city1.getY()==city2.getY()) {
				return true;
		}
		return false;
	}


}
