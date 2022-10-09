package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import app.Data;
import graphics.EulerCircuit;
import graphics.EulerCircuit.NotFoundException;

public class GenerateG {

	EulerCircuit el = new EulerCircuit();
	
	public void FA(Data data,ArrayList<Edge> tour, ArrayList<List<Integer>> Concitys, ArrayList<List<Integer>> Othcitys ) throws NotFoundException {
		
		/*List<Integer> tmp = new ArrayList<>();
		for (int i = 0; i < tour.size(); i++) {
			 tmp.add(tour.get(i).getFrom());
			 tmp.add(tour.get(i).getTo());
		 }
		removeDuplicate(tmp);*/
		do {
			 int[][] rra = new int[tour.size()][];
			 
			 for (int i = 0; i < tour.size(); i++) {
				 rra[i] = new int[]{tour.get(i).getFrom(),tour.get(i).getTo()};
			 }
	
			 List<Integer> test = new ArrayList<>(el.EulerCircuitByDFS(rra, data.vertexnum,tour.get(0).getFrom()));
			//System.out.println(test);
			//临时零件库
			List<List<Integer>> Tmpparts = new ArrayList<>(data.parts);
			//连通的零件库
			List<List<Integer>> Conparts = new ArrayList<>();
			for (int i = 0; i < test.size()-1; i++) {
				for (int j = 0; j < Tmpparts.size(); j++) {
					if (Tmpparts.get(j).contains(test.get(i))) {
						Conparts.add(Tmpparts.get(j));
					}
				}
			}
			data.cyclenum.add(Conparts.size());
			//System.out.println(data.cyclenum);
			//连通点
			List<Integer> Concity = new ArrayList<>();
			for (List<Integer> list : Conparts) {
				Concity.addAll(list);
			}
			Concitys.add(Concity);
			//removeDuplicate(Concity);
			//System.out.println(Concitys);
			//其他点
			List<Integer> Othcity = new ArrayList<>();
			Tmpparts.removeAll(Conparts);
			for (List<Integer> list : Tmpparts) {
				Othcity.addAll(list);
			}
			Othcitys.add(Othcity);
			//removeDuplicate(Othcity);
			//System.out.println(Othcitys);
			
			List<Edge> rEdges = new ArrayList<>();
			for (Edge edge : tour) {
					 if (test.contains(edge.getFrom())||test.contains(edge.getTo())) {
							rEdges.add(edge);
						}
			}
			 tour.removeAll(rEdges);
						
		} while (tour.size()!=0);
	
	}
	
	
	//集合去重
	public static void removeDuplicate(List list) {
		HashSet h = new HashSet(list);
		list.clear();
		list.addAll(h);
		}
	
	private double pointDistance(double p1x, double p1y, double p2x, double p2y) {
		return Math.sqrt(Math.pow(p1x - p2x, 2) + Math.pow(p1y - p2y, 2));
	}

	public double[][] getAdjMatrix(ArrayList<City> data) {
		double[][] matrix = new double[data.size()][data.size()];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				City from = data.get(i);
				City to = data.get(j);

				matrix[i][j] = pointDistance(from.getX(), from.getY(), to.getX(), to.getY());
			}
		}

		return matrix;
	}
	
	
}
