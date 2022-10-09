package maing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import graph.Edge;
import graph.EulerCircuit;
import graph.EulerCircuit.NotFoundException;
import mainR.RPPM;
import mainT.TSGM;


public class FindTour {
	
	static List<List<Integer>> lists = new ArrayList<>();

	public void findT(List<Integer> solve, List<List<Integer>> OLsets) throws NotFoundException{
		
		EulerCircuit el = new EulerCircuit();
		
		//find all Subcircuit
		RPPM.tours = new ArrayList<>();
		List<Edge> edges1 = new ArrayList<>();
		
		for (Integer integer : solve) {
			Edge edge = new Edge(0,0);
			for (int i = 0; i < RPPM.sign.length; i++) {
				for (int j = 0; j < RPPM.sign.length; j++) {
					if (integer == 0) {
						//System.out.println(integer);
						edge.setFrom(0);
						edge.setTo(1);				
					}else {
						if (RPPM.sign[i][j] == integer) {
							edge.setFrom(i);
							edge.setTo(j);
						}
					}

				}
			}
			edges1.add(edge);	
		}
		
/*		for (int i = 0; i < edges1.size(); i++) {
			System.out.print(edges1.get(i).getFrom()+","+edges1.get(i).getTo()+" ");
		}
		System.out.println();*/
			
		do {
			 int[][] rra = new int[edges1.size()][];
			 for (int i = 0; i < edges1.size(); i++) {
				 rra[i] = new int[]{edges1.get(i).getFrom(),edges1.get(i).getTo()};
			 }
			 List<Integer> ruvv = new ArrayList<>(el.EulerCircuitByDFS(rra, TSGM.Vnum,edges1.get(0).getFrom()));  
			 RPPM.tours.add(ruvv);
			 //System.out.println(ruvv);   
			 //System.out.println(ruvv.size());
			 
			 List<Edge> edges2 = new ArrayList<>();
			 for (Integer integer : ruvv) {
				 for (Edge edge : edges1) {
					if (integer==edge.getFrom()||integer==edge.getTo()) {
						//System.out.println(integer);
						//System.out.println(edge.getFrom()+","+edge.getTo()+" ");
						edges2.add(edge);
					}
				}
			 } 
			 edges1.removeAll(edges2); 
		} while (edges1.size()!=0);
		//} while (edges.size()!=edges2.size());
		System.out.println("子回路集合"+RPPM.tours);
		System.out.println("子回路个数"+RPPM.tours.size());
	 
		//find edges connected with other part		
		for (int i = 0; i < RPPM.tours.size(); i++) {
			List<Integer> Oedge = new ArrayList<>();
			for (int j = 0; j < RPPM.tours.size(); j++) {
				if (i != j) {
					for (int j2 = 0; j2 < RPPM.tours.get(i).size()-1; j2++) {
						//System.out.println(tours.get(i).get(j2));
						
						for (int k = 0; k < RPPM.tours.get(j).size()-1; k++) {
							int l = 0;
							if (RPPM.tours.get(i).get(j2)!=RPPM.tours.get(j).get(k)) {
								if (RPPM.tours.get(i).get(j2)>RPPM.tours.get(j).get(k)) {
									l = RPPM.sign[RPPM.tours.get(j).get(k)][RPPM.tours.get(i).get(j2)];
								}else {
									l = RPPM.sign[RPPM.tours.get(i).get(j2)][RPPM.tours.get(j).get(k)];
								}
							}								
							//System.out.println(tours.get(i).get(j2)+","+tours.get(j).get(k)+","+l);
							Oedge.add(l);
						}
						
					}
				}
			}
			removeDuplicate(Oedge);
			OLsets.add(Oedge);
		}
			
/*			System.out.println("OL1"+OLsets);
			
			List<Integer> indexs = new ArrayList<>();
			for (int i = 0; i < OLsets.size(); i++) {
				indexs.add(i);
			}
			//System.out.println(indexs);
			for (int i = 0; i < indexs.size(); i++) {
				if (i>1) {
					combinations(new ArrayList<>(), indexs,i);					
				}
			}
			//System.out.println("lists"+lists);
			for (List<Integer> list : lists) {
				List<Integer> list2 = new ArrayList<>();
				for (Integer integer : list) {
					list2.addAll(OLsets.get(integer));
					//System.out.println(list2);					
				}
				fArlicate(list2);
				//System.out.println(list+","+list2);
				OLsets.add(list2);
			}
			System.out.println("OL2"+OLsets);*/
			
			
			

		
		
	}
	
	//集合去重
	public static void removeDuplicate(List list) {
		HashSet h = new HashSet(list);
		list.clear();
		list.addAll(h);
		}	
	
	//找到重复元素并删除所有重复元素
	public static void fArlicate(List list, List listTemp1){  
        List listTemp = new ArrayList(); 
        for(int i=0;i<list.size();i++){  
            if(!listTemp.contains(list.get(i))){  
                listTemp.add(list.get(i));  
            }else {
				listTemp1.add(list.get(i));
			}  
        }  
        //list.removeAll(listTemp1);        
    }
	
	//排列组合
	public void combinations(List<Integer> selector,List<Integer> data,int n) {
		
		if(n == 0) {
			List<Integer> list = new ArrayList<>();
 			for (Integer i : selector) {
 				list.add(i);
				//System.out.print(i);
		        //System.out.print(" ");
			}
 			lists.add(list);
			//System.out.println();
			return;
		}
		
		if(data.isEmpty()) {
			return;
		}
		
		//选择第一个元素,将元素放入集合
		selector.add(data.get(0));
		combinations(selector,data.subList(1, data.size()),n - 1); //从第二个元素开始选择，再选择两个
		
		//不选择第一个元素
		selector.remove(selector.size() -1 );
		combinations(selector,data.subList(1, data.size()), n); //从第二个元素开始选择，选择两个
		
	}
	
}
