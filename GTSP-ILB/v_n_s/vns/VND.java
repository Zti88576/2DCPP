package vns;

import java.util.ArrayList;

import java.util.List;


public class VND {

	public void variable_neighborhood_descent(Individual S, Point[] bak){	
		int n = 0;
		int i = 1;
		int[] opt = { 1, 2, 3, 4 };
		  for (int j = 0; j < 4; j++) {
		   int k = Data.random.nextInt(4 - j) + j;
		   int tmp = opt[j];
		   opt[j] = opt[k];
		   opt[k] = tmp;
		  }
/*		  for (int j : opt) {
			System.out.print(j+",");
		}
		 System.out.println();*/
		while (true) {
			S.calDistance();
			//System.out.println("第"+i+"次VND:"+S.distance);
			//System.out.println();
			//System.out.println(opt[n-1]);
			if (n<4) {
				switch (opt[n]) {
				case 1:
					//System.out.println("S1");
					Individual S1 = new Individual();
					S1 = S.clone();
					BychangePoint(S1);
					S1.calDistance();
					if (S1.distance < S.distance) {				
						S = S1.clone();
						n = 0;
					}	
					break;
				case 2:
					//System.out.println("S2");
					Individual S2 = new Individual();
					S2 = S.clone();
					Byreverse(S2);
					S2.calDistance();
					if (S2.distance < S.distance) {
						S = S2.clone();
						n = 0;
					}	
					break;
				case 3:
					//System.out.println("S3");
					Individual S3 = new Individual();
					S3 = S.clone();
					Byinsert(S3);
					S3.calDistance();
					if (S3.distance < S.distance) {
						S = S3.clone();
						n = 0;
					}	
					break;
				case 4:
					//System.out.println("S4");
					Individual S4 = new Individual();
					S4 = S.clone();
					Byexchange(S4);	
					S4.calDistance();
					//System.out.println(S4.distance);
					if (S4.distance < S.distance) {
						S = S4.clone();
						n = 0;
					}	
					break;
				default:
					return;
				}
			}else {		
				break;
			}
			n++;	
			i++;
		}
		//System.out.println(i);
		for (int j = 0; j < S.genes.length; j++) {
			//System.out.print(S.genes[j]+",");
			bak[j] = S.genes[j];
		}
		//System.out.println();
		//System.out.println(S.distance);
	}
	//找换点邻域的最优
	void BychangePoint(Individual individual){
		
		List<String> P = new ArrayList<>();
		for (Point Po : individual.genes) {
			P.add(String.valueOf(Po.getX()));
		}
		//System.out.println(P);
		double dis = Double.MAX_VALUE;
		int in = 0;
		int num = 0;
		for (int left = 0; left < individual.genes.length; left++) {
			List<String> tmp = new ArrayList<>();
			for (String string : Data.PARTS.get(individual.genes[left].getIndex())) {
				tmp.add(string);
			}
			tmp.removeAll(P);
			for (int i = 0; i < tmp.size(); i++) {
				if (left == individual.genes.length-1) {
						double x = Data.Weight[individual.genes[left-1].getX()-1][Integer.parseInt(tmp.get(i))-1]
								+ Data.Weight[Integer.parseInt(tmp.get(i))-1][individual.genes[0].getX()-1];
						if (x < dis) {
							dis = x;
							num = Integer.parseInt(tmp.get(i));
							in = left;
							//System.out.println("num1+"+num+","+dis);
						}
				}
				else if (left == 0) {					
						double x = Data.Weight[individual.genes[individual.genes.length-1].getX()-1][Integer.parseInt(tmp.get(i))-1]
								+ Data.Weight[Integer.parseInt(tmp.get(i))-1][individual.genes[left+1].getX()-1];
						if (x < dis) {
							dis = x;
							num = Integer.parseInt(tmp.get(i));
							in = left;
							//System.out.println("num2+"+num+","+dis);
						}			
				}else {
						double x = Data.Weight[individual.genes[left-1].getX()-1][Integer.parseInt(tmp.get(i))-1]
								+ Data.Weight[Integer.parseInt(tmp.get(i))-1][individual.genes[left+1].getX()-1];
						//System.out.println(j+","+x);											
						if (x < dis) {
							dis = x;
							num = Integer.parseInt(tmp.get(i));
							in = left;
							//System.out.println("num3+"+num+","+dis);
						}
					//System.out.println(dis);
				}
			}	
		}
		individual.changePoint(individual, in, num);
	}
	
	//找反转邻域的最优
	void Byreverse(Individual individual){
		individual.calDistance();
		double dis = individual.distance;
		int in = 0;
		int on = 0;		
		for (int i = 0; i < individual.genes.length; i++) {
			for (int j = i+1; j < individual.genes.length; j++) {
				if (i!=0 && j==individual.genes.length-1) {
					double x = individual.distance 
							- Data.Weight[individual.genes[i-1].getX()-1][individual.genes[i].getX()-1]
							- Data.Weight[individual.genes[j].getX()-1][individual.genes[0].getX()-1]
							+ Data.Weight[individual.genes[i-1].getX()-1][individual.genes[j].getX()-1]
							+ Data.Weight[individual.genes[i].getX()-1][individual.genes[0].getX()-1];
					if (x < dis) {
						dis = x;
						in = i;
						on = j;
					}
				}else if (i==0 && j==individual.genes.length-1) {					
					double x = individual.distance;
					if (x < dis) {
						dis = x;
						in = i;
						on = j;
					}
				}else if (i==0 && j!=individual.genes.length-1) {
					double x = individual.distance 
							- Data.Weight[individual.genes[individual.genes.length-1].getX()-1][individual.genes[i].getX()-1]
							- Data.Weight[individual.genes[j].getX()-1][individual.genes[j+1].getX()-1]
							+ Data.Weight[individual.genes[individual.genes.length-1].getX()-1][individual.genes[j].getX()-1]
							+ Data.Weight[individual.genes[i].getX()-1][individual.genes[j+1].getX()-1];
					if (x < dis) {
						dis = x;
						in = i;
						on = j;
					}
				}else {
					double x = individual.distance 
							- Data.Weight[individual.genes[i-1].getX()-1][individual.genes[i].getX()-1]
							- Data.Weight[individual.genes[j].getX()-1][individual.genes[j+1].getX()-1]
							+ Data.Weight[individual.genes[i-1].getX()-1][individual.genes[j].getX()-1]
							+ Data.Weight[individual.genes[i].getX()-1][individual.genes[j+1].getX()-1];
					if (x < dis) {
						dis = x;
						in = i;
						on = j;
					}
				}	
			}			
		}
		individual.reverse(individual, in, on);	
	}
	
	//找插入邻域的最优
	void Byinsert(Individual individual){
		individual.calDistance();
		double dis = Double.MAX_VALUE;
		int in = 0;
		int on = 0;	
		for (int i = 0; i < individual.genes.length; i++) {
			for (int j = 0; j < individual.genes.length; j++) {
				//System.out.println(i+","+j);
				if (i!=j && i+1!=j) {
					if (i==individual.genes.length-1) {
						if (j!=0) {
							double x = individual.distance 
									- Data.Weight[individual.genes[i-1].getX()-1][individual.genes[i].getX()-1]
									- Data.Weight[individual.genes[i].getX()-1][individual.genes[0].getX()-1]
									+ Data.Weight[individual.genes[i-1].getX()-1][individual.genes[0].getX()-1]
									- Data.Weight[individual.genes[j-1].getX()-1][individual.genes[j].getX()-1]
									+ Data.Weight[individual.genes[j-1].getX()-1][individual.genes[i].getX()-1]
									+ Data.Weight[individual.genes[i].getX()-1][individual.genes[j].getX()-1];
							if (x < dis) {
								dis = x;
								in = i;
								on = j;
								//System.out.println(dis);
							}
						}else {
							double x = individual.distance 
									- Data.Weight[individual.genes[i-1].getX()-1][individual.genes[i].getX()-1]
									- Data.Weight[individual.genes[i].getX()-1][individual.genes[0].getX()-1]
									+ Data.Weight[individual.genes[i-1].getX()-1][individual.genes[0].getX()-1]
									- Data.Weight[individual.genes[individual.genes.length-1].getX()-1][individual.genes[j].getX()-1]
									+ Data.Weight[individual.genes[individual.genes.length-1].getX()-1][individual.genes[i].getX()-1]
									+ Data.Weight[individual.genes[i].getX()-1][individual.genes[j].getX()-1];
							if (x < dis) {
								dis = x;
								in = i;
								on = j;
								//System.out.println(dis);
							}
						}
					}else if (i==0) {
						double x = individual.distance 
								- Data.Weight[individual.genes[individual.genes.length-1].getX()-1][individual.genes[i].getX()-1]
								- Data.Weight[individual.genes[i].getX()-1][individual.genes[i+1].getX()-1]
								+ Data.Weight[individual.genes[individual.genes.length-1].getX()-1][individual.genes[i+1].getX()-1]
								- Data.Weight[individual.genes[j-1].getX()-1][individual.genes[j].getX()-1]
								+ Data.Weight[individual.genes[j-1].getX()-1][individual.genes[i].getX()-1]
								+ Data.Weight[individual.genes[i].getX()-1][individual.genes[j].getX()-1];
						if (x < dis) {
							dis = x;
							in = i;
							on = j;
							//System.out.println(dis);
						}
					}else if (j==0) {
						double x = individual.distance 
								- Data.Weight[individual.genes[i-1].getX()-1][individual.genes[i].getX()-1]
								- Data.Weight[individual.genes[i].getX()-1][individual.genes[i+1].getX()-1]
								+ Data.Weight[individual.genes[i-1].getX()-1][individual.genes[i+1].getX()-1]
								- Data.Weight[individual.genes[individual.genes.length-1].getX()-1][individual.genes[j].getX()-1]
								+ Data.Weight[individual.genes[individual.genes.length-1].getX()-1][individual.genes[i].getX()-1]
								+ Data.Weight[individual.genes[i].getX()-1][individual.genes[j].getX()-1];
						if (x < dis) {
							dis = x;
							in = i;
							on = j;
							//System.out.println(dis);
						}
					}else {
						double x = individual.distance 
								- Data.Weight[individual.genes[i-1].getX()-1][individual.genes[i].getX()-1]
								- Data.Weight[individual.genes[i].getX()-1][individual.genes[i+1].getX()-1]
								+ Data.Weight[individual.genes[i-1].getX()-1][individual.genes[i+1].getX()-1]
								- Data.Weight[individual.genes[j-1].getX()-1][individual.genes[j].getX()-1]
								+ Data.Weight[individual.genes[j-1].getX()-1][individual.genes[i].getX()-1]
								+ Data.Weight[individual.genes[i].getX()-1][individual.genes[j].getX()-1];
						if (x < dis) {
							dis = x;
							in = i;
							on = j;
							//System.out.println(dis);
						}
					}
				}
			}			
		}
		individual.insert(individual, in, on);	
	}
	
	//找交换邻域的最优
	void Byexchange(Individual individual){		
		individual.calDistance();
		double dis = individual.distance;
		Individual bak = new Individual();
		for (int i = 0; i < individual.genes.length; i++) {
			for (int j = i+1; j < individual.genes.length; j++) {
				Individual individual2 = new Individual();
				individual2 = individual.clone();
				//System.out.println("2.1="+individual2.distance);
				individual2.exchange(individual2, i, j);
				individual2.calDistance();
				//System.out.println("2.2="+individual2.distance);}				
				if (individual2.distance < dis) {
					dis = individual2.distance;
					//System.out.println("dis"+dis);
					bak = individual2.clone();				
				}/*else {
					bak = individual.clone();
				}*/
			}
		}
		if (bak.distance != 0) {
			individual.genes = bak.genes;
			individual.distance = bak.distance;
		}
	}
	
	
}
