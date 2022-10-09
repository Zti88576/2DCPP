package main;

import java.util.ArrayList;


import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import main.Data.Edge;


public class DataP {

	static List<List<Integer>> lists = new ArrayList<>();
	void datap(Data data,List<Edge> edges) {
		
		data.vertexnum = data.picV.get(data.picV.size()-1);
		//System.out.println(data.Vsets);
		
		int edgenum = -1;
		
		//List<Edge> edges = new ArrayList<>();
		
		for (int i = 0; i < data.Vsets.size(); i++) {		
			for (int j = i+1; j < data.Vsets.size(); j++) {
				edgenum++;
				Edge edge = new Edge(edgenum, i, j);
				edges.add(edge);
				double l = pointDistance(data.Vsets.get(i), data.Vsets.get(j));
				data.ss.put(edgenum, l);
			}
	
		}
		data.edgenum = edgenum+1;
		//System.out.println(data.ss);
		
		/*for (int i = 0; i < edges.size(); i++) {
			System.out.println(edges.get(i).getIndex()+","+edges.get(i).getX()+","+edges.get(i).getY());
		}*/		
		
		
		//得到Asets
		for (int i = 0; i < data.Vsets.size(); i++) {
			List<Integer> Aset = new ArrayList<>();
			for (Edge edge : edges) {
				if (edge.getX()==i||edge.getY()==i) {
					Aset.add(edge.getIndex());
				}
			}
			data.Asets.add(Aset);
		}
		List<List<Integer>> AAsets = new ArrayList<>(data.Asets);
		//System.out.println("data.Asets1:"+data.Asets);
		
		//System.out.println(data.picV);
		
		//得到ARsets
		for (Edge edge : edges) {
			for (int i = 0; i < data.picV.size()-1; i++) {		
				if (data.picV.get(i)==edge.getX()&&data.picV.get(i+1)-1==edge.getY()) {
					//System.out.println(data.picV.get(i)+","+(data.picV.get(i+1)-1));
					data.ARsets.add(edge.getIndex());
				}
				for (int j =data.picV.get(i) ; j < data.picV.get(i+1)-1; j++) {
					if (j==edge.getX()&&j+1==edge.getY()) {
						//System.out.println(j+","+(j+1));
						data.ARsets.add(edge.getIndex());
					}
					
				}
			}
		}
		System.out.println("data.ARsets1:"+data.ARsets);
		
		//找坐标点相同的共顶点
		List<Integer> outV = new ArrayList<>();
		List<Integer> outAR = new ArrayList<>();
		List<Integer> inList = new ArrayList<>();
		List<List<Integer>> outList = new ArrayList<>();
		List<List<Integer>> identicalVs = new ArrayList<>();

		
		//for (Edge edge : edges) {
/*		for (int i = 0; i < data.Vsets.size(); i++) {
			for (int j = data.Vsets.size()-1; j > i; j--) {
				System.out.println(data.Vsets.get(i));
				System.out.println(data.Vsets.get(j));
			}
		}*/
		
			System.out.println(data.Vsets);
			for (int i = 0; i < data.Vsets.size(); i++) {
				for (int j = i+1; j < data.Vsets.size(); j++) {
					if(check(data.Vsets.get(i), data.Vsets.get(j))){
						System.out.println("相同点"+i+","+j);
						outV.add(j);
						outList.add(data.Asets.get(j));
						//相同点的集合
						List<Integer> identicalV = new ArrayList<>();
						identicalV.add(i);
						identicalV.add(j);
						identicalVs.add(identicalV);
						/*if (edge.getX()==j||edge.getY()==j) {
							System.out.println(edge.getIndex());
						}*/
						
/*						for (int k = 0; k < data.ARsets.size(); k++) {
							if (edges.get(data.ARsets.get(k)).getX()==j) {
								for (Edge edge : edges) {
									if (edge.getX()==i&&edge.getY()==edges.get(data.ARsets.get(k)).getY()) {
										System.out.println(edge.getX()+","+edge.getY());
										inList.add(edge.getIndex());
									}
								}
							}
							if (edges.get(data.ARsets.get(k)).getY()==j) {
								for (Edge edge : edges) {
									if (edge.getX()==i&&edge.getY()==edges.get(data.ARsets.get(k)).getX()) {
										System.out.println(edge.getX()+","+edge.getY());
										inList.add(edge.getIndex());
									}
								}
							}
							
						}*/				
						
						for (Integer integer : data.ARsets) {
							if (edges.get(integer).getX()==j) {
								outAR.add(edges.get(integer).getIndex());
								for (Edge edge : edges) {
									if ((edge.getX()==i&&edge.getY()==edges.get(integer).getY())
										||(edge.getX()==edges.get(integer).getY()&&edge.getY()==i)) {
										inList.add(edge.getIndex());
										//System.out.println(edge.getX()+","+edge.getY());
									}
								}
							}
							if (edges.get(integer).getY()==j) {
								outAR.add(edges.get(integer).getIndex());
								for (Edge edge : edges) {
									if ((edge.getX()==i&&edge.getY()==edges.get(integer).getX())
										||(edge.getX()==edges.get(integer).getX()&&edge.getY()==i)) {
										inList.add(edge.getIndex());
										//System.out.println(edge.getX()+","+edge.getY());
									}
								}
							}
						}
					}
				}
			}
		//}
		System.out.println(outList);
		System.out.println(inList);
		System.out.println(data.ARsets);
		System.out.println(identicalVs);
			
		
		List<Integer> o1 = new ArrayList<>();
		List<Integer> i1 = new ArrayList<>();
		for (List<Integer> list : identicalVs) {
			for (Integer integer : inList) {
				if (edges.get(integer).getX()==list.get(1)) {
					o1.add(integer);
					for (Edge edge : edges) {
						if (edge.getX()==list.get(0)&&edge.getY()==edges.get(integer).getY()) {
							//System.out.println(edge.getX()+","+edge.getY());
							i1.add(edge.getIndex());
						}
					}
				}else if (edges.get(integer).getY()==list.get(1)) {
					o1.add(integer);
					for (Edge edge : edges) {
						if (edge.getX()==edges.get(integer).getX()&&edge.getY()==list.get(0)) {
							//System.out.println(edge.getX()+","+edge.getY());
							i1.add(edge.getIndex());
						}
					}
				}
			}
		}
		inList.removeAll(o1);
		inList.addAll(i1);
		
			
		//更新Asets、ARsets
		
		//System.out.println("data.Asets2:"+data.Asets);		
		
		System.out.println(outAR);
		//data.ARsets.addAll(inList);	
		//data.ARsets.removeAll(outAR);
		data.ARsets.addAll(inList);
		data.Asets.removeAll(outList);
		for (List<Integer> integers : outList) {
			data.ARsets.removeAll(integers);		
				for (List<Integer> list : data.Asets) {
					list.removeAll(integers);
				}
		}	
		
			
		System.out.println("data.ARsets2:"+data.ARsets);
		for (Integer integer : data.ARsets) {
			//System.out.println(edges.get(integer).getX()+","+edges.get(integer).getY());
		}
		
		
		//找相交的点和边
		int intersection;
		do {			
		intersection = 0;
		List<Integer> Inedges = new ArrayList<>();
		List<Integer> Outedges = new ArrayList<>();	
		for (int i = 0; i < data.vertexnum; i++) {
			if (outV.contains(i)==false) {
				for (Integer integer : data.ARsets) {
					if (isOnLine(
							Double.parseDouble(data.Vsets.get(i).get(0)),
							Double.parseDouble(data.Vsets.get(i).get(1)),
							Double.parseDouble(data.Vsets.get(edges.get(integer).getX()).get(0)),
							Double.parseDouble(data.Vsets.get(edges.get(integer).getX()).get(1)),
							Double.parseDouble(data.Vsets.get(edges.get(integer).getY()).get(0)),
							Double.parseDouble(data.Vsets.get(edges.get(integer).getY()).get(1)))
							==true
							) {
						//点和相交边的两点的集合
						List<Integer> identicalV = new ArrayList<>();
						identicalV.add(i);
						identicalV.add(edges.get(integer).getX());
						identicalV.add(edges.get(integer).getY());
						identicalVs.add(identicalV);
						
						intersection++;
						Outedges.add(edges.get(integer).getIndex());
						for (Edge edge : edges) {
							if ((edge.getX()==i&&edge.getY()==edges.get(integer).getX())
								||(edge.getX()==edges.get(integer).getX()&&edge.getY()==i)) {
								Inedges.add(edge.getIndex());
							}
							if ((edge.getX()==i&&edge.getY()==edges.get(integer).getY())
								||(edge.getX()==edges.get(integer).getY()&&edge.getY()==i)) {
								Inedges.add(edge.getIndex());
							}
						}
						//System.out.println(i);
						//System.out.println(edges.get(integer).getX()+","+edges.get(integer).getY());
					}
				}
			}
		}
		//System.out.println(Inedges);
		//System.out.println(Outedges);
		data.ARsets.removeAll(Outedges);
		data.ARsets.addAll(Inedges);
		} while (intersection!=0);
		
		//System.out.println("data.ARsets3:"+data.ARsets);
		removeDuplicate(data.ARsets);
		
		System.out.println("data.ARsets3:"+data.ARsets);
		for (Integer integer : data.ARsets) {
			//System.out.println(edges.get(integer).getX()+","+edges.get(integer).getY());
		}
		
		
		double l = 0;
		for (Integer integer : data.ARsets) {
			l = l + data.ss.get(integer);
		}
		//System.out.println(data.ARsets.size());
		System.out.println("l:"+l);
			
		
		//System.out.println(identicalVs);
		List<List<Integer>> identicalPs = new ArrayList<>();
		for (List<Integer> list : identicalVs) {
			List<Integer> listP = new ArrayList<>();
			VfindP(data, list, listP);
			identicalPs.add(listP);
		}
		//System.out.println(identicalPs);
		List<List<Integer>>  CP = new ArrayList<>();
		//将相交图形划分在同一个集合（划分连通图）
		compotent(identicalPs, CP);
		System.out.println("CP1:"+CP);
		List<Integer> P = new ArrayList<>();
		for (int j = 1; j < data.picV.size(); j++) {
			P.add(j);
		}
		//System.out.println("P1"+P);
		for (List<Integer> list : CP) {
			P.removeAll(list);
		}
		//System.out.println("P2"+P);
		for (Integer integer : P) {
			List<Integer> p = new ArrayList<>();
			p.add(integer);
			//System.out.println("p"+p);
			CP.add(p);
		}
		
		List<List<Integer>> listV = new ArrayList<>();
		PfindV(data, CP, listV);	
		for (List<Integer> list : listV) {
			list.removeAll(outV);
		}
		System.out.println("listV:"+listV);
		System.out.println("CP2:"+CP);
		System.out.println(CP.size());
		
		
		//约束2条件
		//连通图内边的集合
		List<Integer> unol = new ArrayList<>();
		for (int i = 0; i < listV.size(); i++) {
			for (int j = 0; j < listV.get(i).size(); j++) {
					for (int j2 = j+1; j2 < listV.get(i).size(); j2++) {
						//System.out.println(listV.get(i).get(j)+","+listV.get(i).get(j2));	
						for (Edge edge : edges) {
								if (edge.getX()==listV.get(i).get(j)&&edge.getY()==listV.get(i).get(j2)) {
									unol.add(edge.getIndex());
								}
							}
					}
			}		
		}

		//System.out.println(unol);
		//System.out.println(data.Asets);
		//System.out.println(AAsets);
		for (List<Integer> list : listV) {
			List<Integer> OLset = new ArrayList<>();
			for (Integer integer : list) {
				for (Integer integer1 : AAsets.get(integer)) {
					OLset.add(integer1);
				}
			}
			OLset.removeAll(unol);
			OLset.removeAll(data.ARsets);
			data.OLsets.add(OLset);
		}
		System.out.println("OL1"+data.OLsets);
		
		List<Integer> indexs = new ArrayList<>();
		for (int i = 0; i < data.OLsets.size(); i++) {
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
				list2.addAll(data.OLsets.get(integer));
				//System.out.println(list2);					
			}
			fArlicate(list2);
			//System.out.println(list+","+list2);
			data.OLsets.add(list2);
		}
		System.out.println("OL2"+data.OLsets);
		
		
	}
	
	//计算距离
	public double pointDistance(List<String> list,List<String> list2) {
		return Math.sqrt(Math.pow(Double.parseDouble(list.get(0))
				- Double.parseDouble(list2.get(0)), 2)
				+ Math.pow( Double.parseDouble(list.get(1))
				- Double.parseDouble(list2.get(1)), 2));
	}
	
	//集合对比,找相同点	
	public  boolean check(List<String> list, List<String> list1) {       
		if (list.get(0).equals(list1.get(0))&&list.get(1).equals(list1.get(1))) {
				return true;
		}
		return false;
	}
	
	//判断点是否在边上
	public boolean isOnLine(double a,double b,double x1,double y1,double x2,double y2){
		if (a==x1&&a==x2&&Math.min(y1 , y2) < b && b< Math.max(y1 , y2)) {
			return true;
		}else if (b==y1&&b==y2&&Math.min(x1 , x2) < a && a < Math.max(x1 , x2)) {
			return true;
		}else if (Math.abs((y2-b)/(x2-a)-(b-y1)/(a-x1))<=1e-2) {
			if(Math.min(x1 , x2) < a && a < Math.max(x1 , x2)){
				if(Math.min(y1 , y2) <=b && b<= Math.max(y1 , y2)){
				return true;
				}
			}
		}

		return false;
			
	}
	
	//集合去重
	public static void removeDuplicate(List list) {
		HashSet h = new HashSet(list);
		list.clear();
		list.addAll(h);
		}	
	
	//通过点找图
	private void VfindP(Data data,List<Integer> listV,List<Integer> listP) {
		for (Integer integer1 : listV) {
			for (int i = 0; i < data.picV.size()-1; i++) {			
				if (data.picV.get(i)<=integer1&&integer1<data.picV.get(i+1)) {
					listP.add(i+1);
				}
			}	
		}
	}
	
	//通过图找点
	private void PfindV(Data data,List<List<Integer>> listP,List<List<Integer>> listV) {		
		for (int i = 0; i < listP.size(); i++) {
			List<Integer> listv = new ArrayList<>();
			List<Integer> list=new ArrayList<>(listP.get(i));
			for (int j = 0; j < listP.get(i).size(); j++) {			
					for (int i1 = data.picV.get(list.get(j)-1); i1 < data.picV.get(list.get(j)); i1++) {						
						listv.add(i1);					
					}	
			}
			listV.add(listv);
		}
	}
	
	//形成连通图
	public static void compotent(List<List<Integer>> lists,List<List<Integer>> results) {
		List<Integer> mergedValue = new ArrayList<>();
		while (lists.size() > 0) {
            List<Integer> result = lists.get(0);
            results.add(result);
            lists.remove(0);
            boolean merged = true;
            while (merged) {
                merged = false;
                List<List<Integer>> mergingList = new ArrayList<>();
                for (Integer value : result) {
                    if (mergedValue.contains(value)) {
                        continue;
                    }
                    mergedValue.add(value);
                    Iterator<List<Integer>> iter = lists.iterator();
                    while (iter.hasNext()) {
                        List<Integer> values = iter.next();
                        if (values.contains(value)) {
                            mergingList.add(values);
                            iter.remove();
                            merged = true;
                        }
                    }
                }
                mergingList.forEach(result::addAll);
            }
        }
		for (List<Integer> list : results) {
			removeDuplicate(list);
		}
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
	
	//找到重复元素并删除所有重复元素
	public static void fArlicate(List list){  
        List listTemp = new ArrayList(); 
        List listTemp1 = new ArrayList();
        for(int i=0;i<list.size();i++){  
            if(!listTemp.contains(list.get(i))){  
                listTemp.add(list.get(i));  
            }else {
				listTemp1.add(list.get(i));
			}  
        }  
        list.removeAll(listTemp1);
         
    }
	
}
