package vns;

import vns.Individual;
import vns.VND;



public class Main {
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		int c = 0;
		double x = 0;
		
		for (int i = 0; i < Data.picV.length-1; i++) {
			for (int j = Data.picV[i]; j < Data.picV[i+1]-1; j++) {
				//System.out.println(j+","+(j+1));
				//System.out.println(TSPData.disMap[j][(j+1)]);
				x = x + Data.disMap[j][(j+1)];
				
			}
			x = x + Data.disMap[Data.picV[i+1]-1][Data.picV[i]];
			//System.out.println(TSPData.disMap[TSPData.picV[i+1]-1-1][TSPData.picV[i]-1]);
			
		}
		System.out.println(x);
		
		
		while (true) {
			c++;
			
			double time = 0;
			double time1 = 0;
			long start=System.currentTimeMillis(); //获取开始时间
			
			Individual best = new Individual();
			best.createInitial();
			best.calDistance();
			System.out.println("初始解："+(x+best.distance));
/*			for (int string : best.genes) {
				System.out.print(string+",");
			}
			System.out.println();			
			System.out.println("best1:"+best.distance);*/
			
			//VNS
			VND vnd = new VND();
			Shaking shake = new Shaking();	
			int max_iterations = 10000;
	        int count = 0, it = 0;
			do {		
				//System.out.println("VNS第"+(it+1)+"次");
				count++;
				it++;
				Individual better = new Individual();
				better = best.clone();
				best.calDistance();
				//System.out.println("best:"+best.distance);
				
				//扰乱
				shake.shaking(better);
/*				System.out.println("shaking后");
				for (int i : better.genes) {
					System.out.print(i+",");
				}
				System.out.println();
				System.out.println("better1："+better.distance);	*/
		
				//变邻域下调
				int[] bak = new int[Data.PART_NUM];
				vnd.variable_neighborhood_descent(better,bak);
				for (int i = 0; i < bak.length; i++) {
					better.genes[i] = bak[i];
				}
				better.calDistance();
/*				System.out.println("VND后");
				for (int i : better.genes) {
					System.out.print(i+",");
				}
				System.out.println();		
				System.out.println("better1："+better.distance);*/
			
				if (better.distance < best.distance) {
					best = better.clone();
					count = 0;
				}
				best.calDistance();
				//System.out.println("全局best:"+best.distance);
				
				long end1=System.currentTimeMillis(); //获取结束时间
				time1 = (end1-start)*0.001;
				//System.out.println(time1);
				for (int j = 0; j < 37; j++) {
					if ((int)time1 == j*100) {
						System.out.println("进化时间"+time1+",长度"+(x+best.distance));
					}
				}
				
				long end=System.currentTimeMillis(); //获取结束时间
				time = (end-start)*0.001;
											
				if (time > 3600) {
					break;
				}
								
/*				for (int j = 0; j < 51; j++) {
					if (it==j*1000) {
						System.out.println("进化次数"+it+",长度"+(x+best.distance));
					}
				}*/
								
			} while (count <= max_iterations);
			
			best.calDistance();
/*			for (int string : best.genes) {
				System.out.print(string+",");
			}
			System.out.println();			
			System.out.println("best2:"+best.distance);*/
				
			System.out.println("第"+c+"次");
			System.out.println("最优解"+(x+best.distance));
			System.out.println("程序运行时间： "+time+"s");
			System.out.println();
			
			if (c==1) {
				//System.out.println(S/10);
				break;
			}
		}
		
		

		
		
		//int x = 3;
		//int y = 0;
		//best.changePoint(best, x, y);
		//best.reverse(best, x, y);
		//best.insert(best, x, y);
		//best.exchange(best, x, y);
		
		
/*		Individual bak = new Individual();
		bak.genes = best.genes;
		vnd.variable_neighborhood_descent(bak);
		System.out.print("VNS：");
		for (int string : bak.genes) {
			System.out.print(string+",");
		}
		System.out.println();
		bak.calDistance();
		System.out.println(bak.distance);
		
		
		shake.shaking(best);
		System.out.print("Shaking：");
		for (int string : best.genes) {
			System.out.print(string+",");
		}
		System.out.println();
		best.calDistance();
		System.out.println(best.distance);*/
		
		
	
/*		vnd.BychangePoint(best);
		System.out.print("换点：");
		for (int string : best.genes) {
			System.out.print(string+",");
		}
		System.out.println();
		best.calDistance();
		System.out.println(best.distance);
		
		vnd.Byreverse(best);
		System.out.print("反转：");
		for (int string : best.genes) {
			System.out.print(string+",");
		}
		System.out.println();
		best.calDistance();
		System.out.println(best.distance);
		
		vnd.Byinsert(best);
		System.out.print("插入：");
		for (int string : best.genes) {
			System.out.print(string+",");
		}
		System.out.println();
		best.calDistance();
		System.out.println(best.distance);
		
		vnd.Byexchange(best);
		System.out.print("交换：");
		for (int string : best.genes) {
			System.out.print(string+",");
		}
		System.out.println();
		best.calDistance();
		System.out.println(best.distance);*/
		
	}

}
