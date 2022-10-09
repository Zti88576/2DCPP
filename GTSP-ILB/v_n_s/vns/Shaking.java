package vns;

import java.util.ArrayList;
import java.util.List;

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
				List<String> P = new ArrayList<>();
				for (Point Po : S.genes) {
					P.add(String.valueOf(Po.getX()));
				}
				List<String> tmp = new ArrayList<>(Data.PARTS.get(S.genes[i].getIndex()));
				tmp.removeAll(P);
				if (tmp.size()!=0) {
					int x = Data.random.nextInt(tmp.size());
					S.changePoint(S, i, Integer.parseInt(tmp.get(x)));		
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
