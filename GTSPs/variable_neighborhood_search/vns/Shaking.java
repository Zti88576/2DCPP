package vns;

public class Shaking {

	
	public void shaking(Individual S){
		int count = 0;	
		while (count<10) {
			int n = Data.random.nextInt(4)+1;
			//System.out.println();
			//n = 1;
			int i = Data.random.nextInt(Data.PART_NUM);
			int j = Data.random.nextInt(Data.PART_NUM);
			while (i == j) {
				j = Data.random.nextInt(Data.PART_NUM);			
			}
			/*if (count==0||count==5||count==6||count==8) {
				n = 1;
			}*/		
			switch (n) {
			case 1:
				for (int j2 = 0; j2 < Data.picV.length; j2++) {
					if (Data.picV[j2]<=S.genes[i] && S.genes[i]<Data.picV[j2+1]) {
						int x= Data.random.nextInt(Data.picV[j2+1]-Data.picV[j2]) + Data.picV[j2];
						//System.out.println(S.genes[i]+","+x);
						S.changePoint(S, i, x);
					}					
				}				
				break;
			case 2:
				S.exchange(S, i, j);				
				break;
			case 3:
				S.reverse(S, i, j);				
				break;
			case 4:
				S.insert(S, i, j);				
				break;
			default:
				break;
			}
			count++;
		}
		S.calDistance();
	}
	
	
	
}
