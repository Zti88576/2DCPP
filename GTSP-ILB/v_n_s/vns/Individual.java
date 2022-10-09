package vns;

import java.util.ArrayList;
import java.util.List;


public class Individual {
	
	public Point[] genes;//基因序列
	public double distance;//路程
	
	public Individual()
	{
		//初始化
		this.genes=new Point[Data.PART_NUM];
		this.distance=0.0f;
	}
	
	//计算长度
	public void calDistance(){
		double totalDis=0.0f;
		//System.out.println();
		
/*		System.out.println(this.distance);
		for (Point point : this.genes) {
			System.out.print("part:"+point.getIndex()+"+vertex:"+point.getX()+",");
			System.out.println();
		}*/
		for(int i = 0;i < Data.PART_NUM;i++)
		{
			int curCity=this.genes[i].getX()-1;
			int nextCity=this.genes[(i+1) % Data.PART_NUM].getX()-1;
			//System.out.println(curCity+"+"+nextCity+"="+Data.Weight[curCity][nextCity]);
			
			totalDis += Data.Weight[curCity][nextCity];
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
	
	//Initial solution —— random
	void createInitial(){
			
		List<List<String>> parts = new ArrayList<>(Data.PARTS);	
		List<String> g = new ArrayList<>();		
		g.add(parts.get(0).get(0));
		Point p0 = new Point(0, Integer.parseInt(parts.get(0).get(0)));
		genes[0] = p0;
		
		int i = 1;
		
		
		do {
			int j = 0;
			//System.out.println(parts.get(i));
			//if (j > parts.get(i).size()) {
				while (g.contains(parts.get(i).get(j))) {
					j++;
					/*if (j == parts.get(i).size()) {
						j = 0;					
						break;
					}*/
				}
			//}
			//repeat.add(i);
			//repeat.add(Integer.parseInt(parts.get(i).get(0)));
			Point p = new Point(i, Integer.parseInt(parts.get(i).get(j)));
			genes[i] = p;
			g.add(Integer.toString(genes[i].getX()));
			//System.out.println(g);
			i++;			
		} while (i < Data.PART_NUM);
		
		
/*		int r1 = Data.random.nextInt(parts.get(0).size());
		g.add(parts.get(0).get(r1));
		Point p0 = new Point(0, Integer.parseInt(parts.get(0).get(r1)));
		genes[0] = p0;
		
		
		int i = 1;
		do {
			int r = Data.random.nextInt(parts.get(i).size());
			do {
				r = Data.random.nextInt(parts.get(i).size());
				Point p = new Point(i, Integer.parseInt(parts.get(i).get(r1)));
				genes[i] = p;
			} while (g.contains(parts.get(i).get(r)));			
			g.add(Integer.toString(genes[i].getX()));
			//System.out.println(g);
			i++;			
		} while (i < Data.PART_NUM);*/
						
	}

	//换点
	void changePoint(Individual individual, int i, int x){
		individual.genes[i] = new Point(genes[i].getIndex(),x);
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
			Point tmp = new Point(0, 0);
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
		List<Point> front = new ArrayList<>();
		List<Point> behind = new ArrayList<>();
		List<Point> list = new ArrayList<>();
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
		for (int k = 0; k < list.size(); k++) {
			individual.genes[k] = list.get(k);
		}
	}
	
	//交换
	void exchange(Individual individual, int i, int j){
		Point tmp = new Point(0, 0);
		tmp = individual.genes[i];
		individual.genes[i] = individual.genes[j];
		individual.genes[j] = tmp;
	}


}
