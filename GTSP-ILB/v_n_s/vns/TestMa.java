package vns;


public class TestMa {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//readFile.Read_data(Data.path);

				//System.out.println(filelist.size());
		
		String path = "C:/Users/Administrator/Desktop/审稿/GTSP-ILB/InstancesText/115u574.txt";
		ReadFile readFile = new ReadFile();
		
				readFile.Read_data(path);
				
				//File.add(file.getName());
				
				
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
				long start=System.currentTimeMillis(); //获取开始时间
				
				Individual best = new Individual();
				best.createInitial();
				best.calDistance();
				System.out.println("初始解："+(best.distance));
				
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
					Point[] bak = new Point[Data.PART_NUM];
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
							//System.out.println("进化时间"+time1+",长度"+(best.distance));
						}
					}
					
					long end=System.currentTimeMillis(); //获取结束时间
					time = (end-start)*0.001;
												
					if (time > 3600) {
						break;
					}
									
		/*			for (int j = 0; j < 51; j++) {
						if (it==j*1000) {
							System.out.println("进化次数"+it+",长度"+(x+best.distance));
						}
					}*/
					System.out.println("进化次数"+it+",长度"+(best.distance));		
				} while (count <= max_iterations);
		        
				System.out.println("最优解"+(best.distance));
				System.out.println("程序运行时间： "+time+"s");
				System.out.println();
				
	}

}
