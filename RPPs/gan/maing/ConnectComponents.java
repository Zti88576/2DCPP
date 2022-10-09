package maing;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import app.GenerateCircuit;
import graph.Edge;
import ilog.concert.IloException;




public class ConnectComponents {

	GenerateCircuit gCircuit = new GenerateCircuit();
	
	public void connect_CbyT(List<Edge> edges) throws IloException {
		
		System.out.println(ganM.Components);
		List<List<Integer>> components = new ArrayList<>();
		for (List<Integer> list : ganM.Components) {
			components.add(list);
		}
		
		Edge [][] Eside = new Edge[components.size()][components.size()];
		double[][] side = new double[components.size()][components.size()];
		
	
		for (int i = 0; i < components.size(); i++) {
			for (int j = i+1; j < components.size(); j++) {
				//System.out.println(components.get(i)+","+components.get(j));
				double f = Double.MAX_VALUE;				
				for (Integer integer1 : components.get(i)) {
					for (Integer integer2 : components.get(j)) {
						//System.out.println(integer1+","+integer2);
						double l = gCircuit.pointDistance(ganM.cities.get(integer1), ganM.cities.get(integer2));
						if (f>l||f==l) {
							f = l;
							Eside[i][j] = new Edge(integer1, integer2);
							side[i][j] = l;
							Eside[j][i] = new Edge(integer1, integer2);
							side[j][i] = l;
						}
					}
				}
			}
		}
		
		for (int i = 0; i < Eside.length; i++) {
			for (int j = 0; j < Eside.length; j++) {
				if (Eside[i][j]!=null) {
					System.out.print(Eside[i][j].getFrom()+","+Eside[i][j].getTo()+"  ");
				}else {
					System.out.print(Eside[i][j]+"  ");
				}
								
			}
			System.out.println();
		}
		for (int i = 0; i < side.length; i++) {
			for (int j = 0; j < side.length; j++) {
				if (i==j) {
					side[i][j] = Float.MAX_VALUE;
				}	
				System.out.print(side[i][j]+"  ");			
			}
			System.out.println();
		}
		
		List<List<Integer>> indexs = new ArrayList<>();
		PrimAlgorithm primAlgorithm = new PrimAlgorithm();
		primAlgorithm.Prim(side,indexs);
		System.out.println(indexs);
		
		for (List<Integer> list : indexs) {
			Edge edge = new Edge(Eside[list.get(0)][list.get(1)].getFrom(),Eside[list.get(0)][list.get(1)].getTo());
			//Edge edge1 = new Edge(Eside[list.get(0)][list.get(1)].getTo(),Eside[list.get(0)][list.get(1)].getFrom());
			edges.add(edge);
			//edges.add(edge1);
		}
		
		for (Edge edge : edges) {
			System.out.println(edge.getFrom()+","+edge.getTo());
		}
		
		GenerateMiniCircuit generateMiniCircuit =new GenerateMiniCircuit();
	    generateMiniCircuit.connect_Neighbor(edges);
	}
	
	public void connect_CbyST(List<Edge> edges) throws IloException {
		
		List<List<Integer>> components = new ArrayList<>();
		for (List<Integer> list : ganM.Components) {
			components.add(list);
		}

		List<List<Edge>> SG = new ArrayList<>();
			for (int i = 0; i < components.size(); i++) {
				List<Edge> sGe = new ArrayList<>();
				for (Integer integer : components.get(i)) {
					for (Edge edge : ganM.AR) {
					if (integer.equals(edge.getFrom())||integer.equals(edge.getTo())) {
						sGe.add(edge);
					}
				FindTour.removeDuplicate(sGe);
				SG.add(sGe);	
				}
			}
		}
		
		FindTour.removeDuplicate(SG);	
			
		List<int[][]> ComponentsG = new ArrayList<>();
		for (List<Edge> is : SG) {
			int[][] i = new int[ganM.cities.size()][ganM.cities.size()];
			for (Edge js : is) {
				i[js.getFrom()][js.getTo()] = 1;
				i[js.getTo()][js.getFrom()] = 1;
			}
			ComponentsG.add(i);
		}
		
		List<List<Integer>> indexODD = new ArrayList<>();		
		for (int[][] i : ComponentsG) {
			final int MAX_WEIGHT = 0xFFFF;
			List<Integer> SingularPoint = new ArrayList<>();
		    for (int i1 = 0; i1 < ganM.cities.size(); i1++) {
		    	int count = 0;
		    	for (int j = 0; j < ganM.cities.size(); j++) {
		    		if (i[i1][j] != 0 && i[i1][j] != MAX_WEIGHT) {
		                 count ++;
		             }
				}
		    	//System.out.println(count);
		        if (count % 2 != 0) {
		        	//System.out.println(count);
					SingularPoint.add(i1);
				}
			}  
		    if (SingularPoint.size()!=0) {
		    	indexODD.add(SingularPoint);
			}		   
		}
		
/*		for (List<Edge> list : SG) {
			System.out.println();
			for (Edge edge : list) {
				System.out.println(edge.getFrom()+","+edge.getTo());
			}
		}	
		System.out.println(indexODD);*/
		Edge [][] Eside0 = new Edge[indexODD.size()][indexODD.size()];
		double[][] side0 = new double[indexODD.size()][indexODD.size()];
		
	
		for (int i = 0; i < indexODD.size(); i++) {
			for (int j = i+1; j < indexODD.size(); j++) {
				//System.out.println(components.get(i)+","+components.get(j));
				double f = Float.MAX_VALUE;				
				for (Integer integer1 : indexODD.get(i)) {
					for (Integer integer2 : indexODD.get(j)) {
						//System.out.println(integer1+","+integer2);
						double l = gCircuit.pointDistance(ganM.cities.get(integer1), ganM.cities.get(integer2));
						if (f>l||f==l) {
							f = l;
							Eside0[i][j] = new Edge(integer1, integer2);
							side0[i][j] = l;
							Eside0[j][i] = new Edge(integer1, integer2);
							side0[j][i] = l;
						}
					}
				}
			}
		}
		
/*		for (int i = 0; i < Eside0.length; i++) {
			for (int j = 0; j < Eside0.length; j++) {
				if (Eside0[i][j]!=null) {
					System.out.print(Eside0[i][j].getFrom()+","+Eside0[i][j].getTo()+"  ");
				}else {
					System.out.print(Eside0[i][j]+"  ");
				}
								
			}
			System.out.println();
		}
		for (int i = 0; i < side0.length; i++) {
			for (int j = 0; j < side0.length; j++) {
				if (i==j) {
					side0[i][j] = Float.MAX_VALUE;
				}	
				System.out.print(side0[i][j]+"  ");			
			}
			System.out.println();
		}*/
		
		if (side0.length!=0) {
			List<List<Integer>> indexs0 = new ArrayList<>();
			PrimAlgorithm primAlgorithm0 = new PrimAlgorithm();
			primAlgorithm0.Prim(side0,indexs0);
			
			for (List<Integer> list : indexs0) {
				Edge edge = new Edge(Eside0[list.get(0)][list.get(1)].getFrom(),Eside0[list.get(0)][list.get(1)].getTo());
				//Edge edge1 = new Edge(Eside[list.get(0)][list.get(1)].getTo(),Eside[list.get(0)][list.get(1)].getFrom());
				edges.add(edge);
				//edges.add(edge1);
			}
			
			System.out.println(indexODD);
			
			List<List<Integer>> RR = new ArrayList<>();
			List<Integer> AA = new ArrayList<>();

			for (List<Integer> list : indexODD) {
				for (int i = 0; i < components.size(); i++) {
					for (Integer integer : components.get(i)) {
						if (list.get(0)==integer) {
							RR.add(components.get(i));
							AA.addAll(components.get(i));
						}
					}
				}
			}
		
			//System.out.println(RR);
			//System.out.println(AA);
			components.removeAll(RR);
			FindTour.removeDuplicate(AA);
			components.add(AA);
		}
/*		if (indexODD.size()!=0 && indexODD.size()!=1) {
			do {
				Edge edge = null;
				List<List<Integer>> rList = new ArrayList<>();
				List<Integer> aList = new ArrayList<>();
				List<Integer> list = new ArrayList<>();
				double fl = Double.MAX_VALUE;
				for (int i = 0; i < indexODD.size(); i++) {
					for (int j = i+1; j < indexODD.size(); j++) {
						double f = Double.MAX_VALUE;				
						for (Integer integer1 : indexODD.get(i)) {
							for (Integer integer2 : indexODD.get(j)) {
								//System.out.println(integer1+","+integer2);
								double l = gCircuit.pointDistance(ganM.cities.get(integer1), ganM.cities.get(integer2));
								if (f>l||f==l) {
									f = l;							
									if (fl>f||fl==f) {
										fl = f;	
										edge = new Edge(integer1, integer2);
										list.add(i);
										list.add(j);
									}						
								}
							}
						}
					}
				}
					
				
				List<Integer> list1 = new ArrayList<>();		
				list1.add(list.get(list.size()-2));
				list1.add(list.get(list.size()-1));
				
				for (Integer integer : list1) {
					rList.add(indexODD.get(integer));
					for (Integer integer1 : indexODD.get(integer)) {
						aList.add(integer1);
					}
				}
				
				indexODD.removeAll(rList);
				
				FindTour.removeDuplicate(aList);
				
				indexODD.add(aList);
				
				//System.out.println(edge.getFrom()+","+edge.getTo());

				//System.out.println(rList);
				
				edges.add(edge);
				
			} while (indexODD.size()!=1);
		}


		List<List<Integer>> RR = new ArrayList<>();
		List<Integer> AA = new ArrayList<>();
		if (indexODD.size()!=0) {
			for (int i = 0; i < components.size(); i++) {
				for (Integer integer3 : components.get(i)) {
					for (Integer integer : indexODD.get(0)) {
							if (integer3 == integer) {
								RR.add(components.get(i));
								AA.addAll(components.get(i));
						}
					}
				}
			}
		}
		
		components.removeAll(RR);
		FindTour.removeDuplicate(AA);
		components.add(AA);*/
		
		List<Integer> vacant = new ArrayList<>();
		components.remove(vacant);
		
		Edge [][] Eside = new Edge[components.size()][components.size()];
		double[][] side = new double[components.size()][components.size()];
		
	
		for (int i = 0; i < components.size(); i++) {
			for (int j = i+1; j < components.size(); j++) {
				//System.out.println(components.get(i)+","+components.get(j));
				double f = Double.MAX_VALUE;				
				for (Integer integer1 : components.get(i)) {
					for (Integer integer2 : components.get(j)) {
						//System.out.println(integer1+","+integer2);
						double l = gCircuit.pointDistance(ganM.cities.get(integer1), ganM.cities.get(integer2));
						if (f>l||f==l) {
							f = l;
							Eside[i][j] = new Edge(integer1, integer2);
							side[i][j] = l;
							Eside[j][i] = new Edge(integer1, integer2);
							side[j][i] = l;
						}
					}
				}
			}
		}
		
		for (int i = 0; i < Eside.length; i++) {
			for (int j = 0; j < Eside.length; j++) {
				if (Eside[i][j]!=null) {
					System.out.print(Eside[i][j].getFrom()+","+Eside[i][j].getTo()+"  ");
				}else {
					System.out.print(Eside[i][j]+"  ");
				}
								
			}
			System.out.println();
		}
		for (int i = 0; i < side.length; i++) {
			for (int j = 0; j < side.length; j++) {
				if (i==j) {
					side[i][j] = Float.MAX_VALUE;
				}	
				System.out.print(side[i][j]+"  ");			
			}
			System.out.println();
		}
		
		List<List<Integer>> indexs = new ArrayList<>();
		PrimAlgorithm primAlgorithm = new PrimAlgorithm();
		primAlgorithm.Prim(side,indexs);
		System.out.println(indexs);
		
		for (List<Integer> list : indexs) {
			Edge edge = new Edge(Eside[list.get(0)][list.get(1)].getFrom(),Eside[list.get(0)][list.get(1)].getTo());
			//Edge edge1 = new Edge(Eside[list.get(0)][list.get(1)].getTo(),Eside[list.get(0)][list.get(1)].getFrom());
			edges.add(edge);
			//edges.add(edge1);
		}
		
		for (Edge edge : edges) {
			System.out.println(edge.getFrom()+","+edge.getTo());
		}
		
		GenerateMiniCircuit generateMiniCircuit =new GenerateMiniCircuit();
	    generateMiniCircuit.connect_Neighbor(edges);
    
    
	}
	
	public void connect_CbyG(List<Edge> edges) throws IloException{
		

		List<List<Integer>> components = new ArrayList<>();
		for (List<Integer> list : ganM.Components) {
			components.add(list);
		}
		
		if (components.size() != 1&&components.size()!=0) {
			do {
				Edge edge = null;
				List<List<Integer>> rList = new ArrayList<>();
				List<Integer> aList = new ArrayList<>();
				List<Integer> list = new ArrayList<>();
				
				double fl = Double.MAX_VALUE;
				for (int i = 0; i < components.size(); i++) {
					for (int j = i+1; j < components.size(); j++) {
						double f = Double.MAX_VALUE;				
						for (Integer integer1 : components.get(i)) {
							for (Integer integer2 : components.get(j)) {
								//System.out.println(integer1+","+integer2);
								double l = gCircuit.pointDistance(ganM.cities.get(integer1), ganM.cities.get(integer2));
								//the same distance may have a few influences
								if (f>l||f==l) {
									f = l;							
									if (fl>f||fl==f) {
										fl = f;	
										edge = new Edge(integer1, integer2);
										list.add(i);
										list.add(j);
									}						
								}
	/*							if (f>l) {
									f = l;							
									if (fl>f) {
										fl = f;	
										edge = new Edge(integer1, integer2);
										list.add(i);
										list.add(j);
									}						
								}*/
							}
						}
					}
				}
				
				List<Integer> list1 = new ArrayList<>();		
				list1.add(list.get(list.size()-2));
				list1.add(list.get(list.size()-1));
				
				for (Integer integer : list1) {
					rList.add(components.get(integer));
					for (Integer integer1 : components.get(integer)) {
						aList.add(integer1);
					}
				}
				
				components.removeAll(rList);
				
				FindComponents.removeDuplicate(aList);
				
				components.add(aList);
				
				//System.out.println(edge.getFrom()+","+edge.getTo());

				//System.out.println(rList);
				
				edges.add(edge);
			
				//System.out.println(GANM.Components);
			} while (components.size()!= 1);	
		  }
		
		GenerateMiniCircuit generateMiniCircuit =new GenerateMiniCircuit();
	    generateMiniCircuit.connect_Neighbor(edges);
	
	}
	
	public void connect_CbyS(List<Edge> edges) throws IloException {
		
		List<List<Integer>> components = new ArrayList<>();
		for (List<Integer> list : ganM.Components) {
			components.add(list);
		}

		List<List<Edge>> SG = new ArrayList<>();
			for (int i = 0; i < components.size(); i++) {
				List<Edge> sGe = new ArrayList<>();
				for (Integer integer : components.get(i)) {
					for (Edge edge : ganM.AR) {
					if (integer.equals(edge.getFrom())||integer.equals(edge.getTo())) {
						sGe.add(edge);
					}
				FindComponents.removeDuplicate(sGe);
				SG.add(sGe);	
				}
			}
		}
		
		FindComponents.removeDuplicate(SG);	
			
		List<int[][]> ComponentsG = new ArrayList<>();
		for (List<Edge> is : SG) {
			int[][] i = new int[ganM.cities.size()][ganM.cities.size()];
			for (Edge js : is) {
				i[js.getFrom()][js.getTo()] = 1;
				i[js.getTo()][js.getFrom()] = 1;
			}
			ComponentsG.add(i);
		}
		
		List<List<Integer>> indexODD = new ArrayList<>();		
		for (int[][] i : ComponentsG) {
			final int MAX_WEIGHT = 0xFFFF;
			List<Integer> SingularPoint = new ArrayList<>();
		    for (int i1 = 0; i1 < ganM.cities.size(); i1++) {
		    	int count = 0;
		    	for (int j = 0; j < ganM.cities.size(); j++) {
		    		if (i[i1][j] != 0 && i[i1][j] != MAX_WEIGHT) {
		                 count ++;
		             }
				}
		    	//System.out.println(count);
		        if (count % 2 != 0) {
		        	//System.out.println(count);
					SingularPoint.add(i1);
				}
			}  
		    if (SingularPoint.size()!=0) {
		    	indexODD.add(SingularPoint);
			}		   
		}
		
/*		for (List<Edge> list : SG) {
			System.out.println();
			for (Edge edge : list) {
				System.out.println(edge.getFrom()+","+edge.getTo());
			}
		}	
		System.out.println(indexODD);*/
		if (indexODD.size()!=0 && indexODD.size()!=1) {
			do {
				Edge edge = null;
				List<List<Integer>> rList = new ArrayList<>();
				List<Integer> aList = new ArrayList<>();
				List<Integer> list = new ArrayList<>();
				double fl = Double.MAX_VALUE;
				for (int i = 0; i < indexODD.size(); i++) {
					for (int j = i+1; j < indexODD.size(); j++) {
						double f = Double.MAX_VALUE;				
						for (Integer integer1 : indexODD.get(i)) {
							for (Integer integer2 : indexODD.get(j)) {
								//System.out.println(integer1+","+integer2);
								double l = gCircuit.pointDistance(ganM.cities.get(integer1), ganM.cities.get(integer2));
								if (f>l||f==l) {
									f = l;							
									if (fl>f||fl==f) {
										fl = f;	
										edge = new Edge(integer1, integer2);
										list.add(i);
										list.add(j);
									}						
								}
							}
						}
					}
				}
					
				
				List<Integer> list1 = new ArrayList<>();		
				list1.add(list.get(list.size()-2));
				list1.add(list.get(list.size()-1));
				
				for (Integer integer : list1) {
					rList.add(indexODD.get(integer));
					for (Integer integer1 : indexODD.get(integer)) {
						aList.add(integer1);
					}
				}
				
				indexODD.removeAll(rList);
				
				FindComponents.removeDuplicate(aList);
				
				indexODD.add(aList);
				
				//System.out.println(edge.getFrom()+","+edge.getTo());

				//System.out.println(rList);
				
				edges.add(edge);
				
			} while (indexODD.size()!=1);
		}


		List<List<Integer>> RR = new ArrayList<>();
		List<Integer> AA = new ArrayList<>();
		if (indexODD.size()!=0) {
			for (int i = 0; i < components.size(); i++) {
				for (Integer integer3 : components.get(i)) {
					for (Integer integer : indexODD.get(0)) {
							if (integer3 == integer) {
								RR.add(components.get(i));
								AA.addAll(components.get(i));
						}
					}
				}
			}
		}
		
		components.removeAll(RR);
		FindComponents.removeDuplicate(AA);
		components.add(AA);
		
		List<Integer> vacant = new ArrayList<>();
		components.remove(vacant);
		
	if (components.size() != 1) {
		do {
			Edge edge = null;
			List<List<Integer>> rList = new ArrayList<>();
			List<Integer> aList = new ArrayList<>();
			List<Integer> list = new ArrayList<>();
			
			double fl = Double.MAX_VALUE;
			for (int i = 0; i < components.size(); i++) {
				for (int j = i+1; j < components.size(); j++) {
					double f = Double.MAX_VALUE;				
					for (Integer integer1 : components.get(i)) {
						for (Integer integer2 : components.get(j)) {
							//System.out.println(integer1+","+integer2);
							double l = gCircuit.pointDistance(ganM.cities.get(integer1), ganM.cities.get(integer2));
							//the same distance may have a few influences
							if (f>l||f==l) {
								f = l;							
								if (fl>f||fl==f) {
									fl = f;	
									edge = new Edge(integer1, integer2);
									list.add(i);
									list.add(j);
								}						
							}
/*							if (f>l) {
								f = l;							
								if (fl>f) {
									fl = f;	
									edge = new Edge(integer1, integer2);
									list.add(i);
									list.add(j);
								}						
							}*/
						}
					}
				}
			}
			
			List<Integer> list1 = new ArrayList<>();		
			list1.add(list.get(list.size()-2));
			list1.add(list.get(list.size()-1));
			
/*			Edge edge1 = new Edge(0, 0);
			odd_distance(GANM.Components.get(list1.get(0)), GANM.Components.get(list1.get(1)), edge1);
		
			if (TSGM.length[edge1.getFrom()][edge1.getTo()]!=0) {
				if (TSGM.length[edge1.getFrom()][edge1.getTo()] <= TSGM.length[edge.getFrom()][edge.getTo()]) {
					edge.setFrom(edge1.getFrom());
					edge.setTo(edge1.getTo());
				}
			}*/
			
			for (Integer integer : list1) {
				rList.add(components.get(integer));
				for (Integer integer1 : components.get(integer)) {
					aList.add(integer1);
				}
			}
			
			components.removeAll(rList);
			
			FindComponents.removeDuplicate(aList);
			
			components.add(aList);
			
			//System.out.println(edge.getFrom()+","+edge.getTo());

			//System.out.println(rList);
			
			edges.add(edge);
		
			//System.out.println(GANM.Components);
		} while (components.size()!= 1);	
	  }
	GenerateMiniCircuit generateMiniCircuit =new GenerateMiniCircuit();
    generateMiniCircuit.connect_Neighbor(edges);
    
    
	}
	
/*	public void odd_distance(List<Integer> c1, List<Integer> c2, Edge edge1) {
		List<List<Edge>> SG = new ArrayList<>();
		List<Edge> sGe1 = new ArrayList<>();
		for (Integer integer : c1) {
			for (Edge edge : TSGM.AR) {
			if (integer.equals(edge.getFrom())||integer.equals(edge.getTo())) {
				sGe1.add(edge);
			}
		FindTour.removeDuplicate(sGe1);
		SG.add(sGe1);	
		}
	}	
		List<Edge> sGe2 = new ArrayList<>();
		for (Integer integer : c2) {
			for (Edge edge : TSGM.AR) {
			if (integer.equals(edge.getFrom())||integer.equals(edge.getTo())) {
				sGe2.add(edge);
			}
		FindTour.removeDuplicate(sGe2);
		SG.add(sGe2);	
		}
	}
		FindTour.removeDuplicate(SG);
		
		List<int[][]> ComponentsG = new ArrayList<>();
		for (List<Edge> is : SG) {
			int[][] i = new int[TSGM.Vnum][TSGM.Vnum];
			for (Edge js : is) {
				i[js.getFrom()][js.getTo()] = 1;
				i[js.getTo()][js.getFrom()] = 1;
			}
			ComponentsG.add(i);
		}
		
		List<List<Integer>> indexODD = new ArrayList<>();		
		for (int[][] i : ComponentsG) {
			final int MAX_WEIGHT = 0xFFFF;
			List<Integer> SingularPoint = new ArrayList<>();
		    for (int i1 = 0; i1 < TSGM.Vnum; i1++) {
		    	int count = 0;
		    	for (int j = 0; j < TSGM.Vnum; j++) {
		    		if (i[i1][j] != 0 && i[i1][j] != MAX_WEIGHT) {
		                 count ++;
		             }
				}
		    	//System.out.println(count);
		        if (count % 2 != 0) {
		        	//System.out.println(count);
					SingularPoint.add(i1);
				}
			}  
		    if (SingularPoint.size()!=0) {
		    	indexODD.add(SingularPoint);
			}		   
		}
		
		//System.out.println(indexODD);
		
		if (indexODD.size()==2) {
			float f = Float.MAX_VALUE;				
			for (Integer integer1 : indexODD.get(0)) {
				for (Integer integer2 : indexODD.get(1)) {
					//System.out.println(integer1+","+integer2);
					float l = TSGM.length[integer1][integer2];		
						if (f>l||f==l) {
							f = l;	
							//System.out.println(integer1+","+integer2);
							edge1.setFrom(integer1);
							edge1.setTo(integer2);
							//edge1 = new Edge(integer1, integer2);
						}						
				}
			}
		}
	}*/

}
