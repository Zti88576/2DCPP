package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import gC.TSPData;
import graph.City;
import graph.Edge;
import graph.GenerateG;
import graphics.*;
import ilog.concert.IloException;
import ilog.concert.IloIntExpr;
import ilog.concert.IloIntVar;
import ilog.concert.IloLinearIntExpr;
import ilog.concert.IloLinearNumExpr;
import ilog.cplex.IloCplex;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class APP extends Application{
//public class APP {

	static Data d = new Data();
	static ArrayList<City> cities = new ArrayList<>();
	List<List<City>> Kcities = new ArrayList<>();
	
	static List<Integer> test;
	
	double o;
	int in = -1;
	
	DrawLine dl = new DrawLine();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	
	@Override
	public void start(Stage stage) throws Exception {
		//public static void vns(String[] args) throws Exception {
		// TODO Auto-generated method stub
		long start=System.currentTimeMillis(); //��ȡ��ʼʱ��
		
		//String path = "C:/Users/Administrator/Desktop/�и�·������/testInstance/rpp3/THREE.txt";	
		
		String path = "C:/Users/Administrator/Desktop/����ʵ��/blaz2_s.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/dagli.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/dighe2.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/dighe1_s.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/fu.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/dighe2.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/poly2a.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/shapes0.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/shapes - ����.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/shirts.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/swim.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/trousers.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/albano.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/jakobs1.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/mao.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/marques.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/L0003_lingjian(83.514210%).pack";
		
		//String path = "F:/��һ/zt/poly4a.pack";
		//String path = "E:/ZT/shirts.pack";
		//String path = "C:/Users/zhangtai/Desktop/ZT/mao.pack";
			
		
		ReadFile rFile = new ReadFile();
		rFile.Read_data(path, d, cities, Kcities);
		System.out.println("����"+d.vertexnum);
		
		ArrayList<Edge> tour = new ArrayList<Edge>();
		ArrayList<Stack<Edge>> stacks = new ArrayList<Stack<Edge>>();
		ArrayList<List<Integer>> Concitys = new ArrayList<>();
		ArrayList<List<Integer>> Othcitys = new ArrayList<>();
		
		EulerCircuit el = new EulerCircuit();
		GenerateG generateG = new GenerateG();
		
		double[][] distance = generateG.getAdjMatrix(cities);
		
		//System.out.println(cities);
		
		double jieguo = 0;
		
		
		while (true) {
			try {
				IloCplex model = new IloCplex();
				//model.setParam(IloCplex.IntParam.TimeLimit, 100);
				
				// define variables
				IloIntVar[][] x = new IloIntVar[cities.size()][cities.size()];
				for (int i = 0; i < x.length; i++) {
					for (int j = 0; j < x.length; j++) {
						x[i][j] = model.boolVar("X[" + i + ", " + j + "]");
					}
				}

			
				IloIntVar[] w = new IloIntVar[d.vertexnum];
				for(int i = 0; i < d.vertexnum; i++) {
					w[i] = model.boolVar("w" + i);
				}
				
/*				IloIntVar[] w1 = new IloIntVar[d.vertexnum];
				for(int i = 0; i < d.vertexnum; i++) {
					w1[i] = model.intVar(1,1);
				}
				
				int a = 0;				
				for (int i = 0; i < x.length; i++) {
					IloLinearIntExpr r1 = model.linearIntExpr();
					IloLinearIntExpr r2 = model.linearIntExpr();
					for (int j = 0; j < x.length; j++) {
						//System.out.println(x[i][j]);
						
						r1.addTerm(1, x[i][j]);
						System.out.println(r1);
						
						r2.addTerm(6, w[i]);
						
					}
					model.addEq(r1, r2);
				}
				System.out.println(model);*/
				
				//ÿ��������ıߵ��ڳ�ȥ�ı�
				for (int i = 0; i < x.length; i++) {
					IloLinearIntExpr r1 = model.linearIntExpr();
					IloLinearIntExpr r2 = model.linearIntExpr();
					for (int j = 0; j < d.parts.size(); j++) {
						if (d.parts.get(j).contains(i)) {
							for (City city : Kcities.get(j)) {
								r1.addTerm(1, x[i][city.getIndex()]);
								r2.addTerm(1, x[city.getIndex()][i]);
							}
						}
					}
					model.addEq(r1, r2);
				}
				//ÿ����������ı�ֻ����һ��
				for (int i = 0; i < d.parts.size(); i++) {
					IloLinearIntExpr r1 = model.linearIntExpr();
					for (int j = 0; j < d.parts.get(i).size(); j++) {
						for (City city : Kcities.get(i)) {
							r1.addTerm(1, x[d.parts.get(i).get(j)][city.getIndex()]);
							//r1.addTerm(1, x[city.getIndex()][d.parts.get(i).get(j)]);
						}						
					}				
					model.addEq(r1, 1);
				}
				
				
				// one cannot go to the same city as he is
				for (int i = 0; i < x.length; i++) {
					IloLinearIntExpr r = model.linearIntExpr();
					r.addTerm(1, x[i][i]);
					model.addEq(r, 0);
				}
				

/*				//Լ��1��ÿ������ĳ���Ⱦ�Ϊ2
				//ÿ�������ȥһ����
				for (int i = 0; i < d.parts.size(); i++) {
					IloLinearIntExpr r1 = model.linearIntExpr();
					for (int j = 0; j < d.parts.get(i).size(); j++) {
						for (City city : Kcities.get(i)) {
							r1.addTerm(1, x[d.parts.get(i).get(j)][city.getIndex()]);
							r1.addTerm(1, x[city.getIndex()][d.parts.get(i).get(j)]);
						}						
					}				
					model.addEq(r1, 2);
				}
				
				//Լ��2
				for (int i = 0; i < x.length; i++) {
					IloLinearIntExpr r1 = model.linearIntExpr();
					IloLinearIntExpr r4 = model.linearIntExpr();
					for (int j = 0; j < d.parts.size(); j++) {
						if (d.parts.get(j).contains(i)) {
							for (City city : Kcities.get(j)) {
								r1.addTerm(1, x[i][city.getIndex()]);
							}
						}
					}
					//System.out.println(r1);
					r4.addTerm(1, w[i]);
					model.addEq(r1, r4);
				}				
				for (int i = 0; i < x.length; i++) {
					IloLinearIntExpr r1 = model.linearIntExpr();
					IloLinearIntExpr r4 = model.linearIntExpr();
					for (int j = 0; j < d.parts.size(); j++) {
						if (d.parts.get(j).contains(i)) {
							for (City city : Kcities.get(j)) {
								r1.addTerm(1, x[city.getIndex()][i]);
							}
						}
					}
					//System.out.println(r1);
					r4.addTerm(1, w[i]);
					model.addEq(r1, r4);
				}*/
				
				
				//System.out.println(stacks);
				/*for (Stack<Edge> stack : stacks) {					
					//stack.forEach((edge) -> System.out.println(edge.getFrom() + "->" + edge.getTo()));
					constraintFactory.cycleRestrictions(model, x, stack);
				}*/
				
				
				//System.out.println(Concitys);
				//System.out.println(Othcitys);
								
				//Լ��3 �����ӻ���·
				System.out.println(Concitys);
				for (int i = 0; i < Concitys.size(); i++) {
					IloLinearIntExpr r1 = model.linearIntExpr();
					//IloLinearIntExpr r2 = model.linearIntExpr();
					for (int j = 0; j < Concitys.get(i).size(); j++) {
						for (int j2 = j; j2 < Concitys.get(i).size(); j2++) {
							r1.addTerm(1, x[Concitys.get(i).get(j)][Concitys.get(i).get(j2)]);
							r1.addTerm(1, x[Concitys.get(i).get(j2)][Concitys.get(i).get(j)]);
						}
					}
					model.addLe(r1, d.cyclenum.get(i)-1);
					//model.addLe(r2, d.cyclenum.get(i)-1);
				}
				
					
				for (int i = 0; i < Concitys.size(); i++) {
					IloLinearIntExpr r1 = model.linearIntExpr();
					//IloLinearIntExpr r2 = model.linearIntExpr();
					for (Integer integeri : Concitys.get(i)) {
						for (Integer integerj : Othcitys.get(i)) {
							r1.addTerm(1, x[integeri][integerj]);
							r1.addTerm(1, x[integerj][integeri]);
						}
					}
					//r3 = model.sum(r1,r2);
					model.addLe(2, r1);
					//model.addLe(1, r2);
				}
				
				
				// one should complete the tour within the smallest distance possible
				IloLinearNumExpr z = model.linearNumExpr();
				for (int i = 0; i < x.length; i++) {
					for (int j = 0; j < x.length; j++) {
						if (i == j)
							continue;
						z.addTerm(distance[i][j], x[i][j]);
					}
				}
				model.addMinimize(z);
//				System.out.println(model.getModel());

				tour.clear();

				if (model.solve()) {
					// get tour
					for (int i = 0; i < x.length; i++) {
						for (int j = 0; j < x.length; j++) {
							if (model.getValue(x[i][j]) >= 0.5) {
								model.output().println(x[i][j]);
								tour.add(new Edge(i, j));
								
							}
						}
					}

				} else {
					System.err.println("Boi, u sick!");
					System.exit(1);
				}

				System.out.println("Value = " + model.getObjValue());
				jieguo = model.getObjValue();						
				 
				int[][] rra = new int[tour.size()][];
				 for (int i = 0; i < tour.size(); i++) {
					 rra[i] = new int[]{tour.get(i).getFrom(),tour.get(i).getTo()};
				 }
				test = new ArrayList<>(el.EulerCircuitByDFS(rra, cities.size(),tour.get(0).getFrom()));
				// System.out.println("test"+test);
				 				 				 
				 System.out.println("���ʱ��"+model.getCplexTime());
				 
				 if (test.size()-1 == tour.size()) {
					break;
				}else {
					generateG.FA(d, tour, Concitys, Othcitys);
					model.endModel();
				}				
			} catch (IloException e) {						
				System.out.println(e.getMessage());
				e.printStackTrace();
				System.out.println(path);
				System.out.println(jieguo);
				System.exit(1);
				
				// TODO: handle exception
			}
		}
	
		System.out.println("����"+d.vertexnum);
		long end=System.currentTimeMillis(); //��ȡ����ʱ��
		System.out.println("��������ʱ�䣺 "+(end-start)*0.001+"s");
		
		
		BorderPane border = new BorderPane();
		border.setStyle("-fx-background-color:#FFF");
		
		
		border.setCenter(addPane());
		
		d.width = 800/d.width;
		//System.out.println(data.width);
		
		
		Scene scene = new Scene(border,800,600);	
		stage.setScene(scene);
		stage.show();
	}


	public Pane addPane() {
		// TODO Auto-generated method stub
		Pane p = new Pane();
		
		d.height = 600/d.height;
		
		p.setTranslateX(100);
		
		
		System.out.println(test);
		System.out.println(d.picV);
		

		List<Integer> test1 = new ArrayList<>();
		for (int i = 0; i < test.size()-1; i++) {
			for (int i1 = 0; i1 < d.picV.size()-1; i1++) {
				if (test.get(i)>=d.picV.get(i1)&&test.get(i)<d.picV.get(i1+1)) {
					for (int j = test.get(i); j < d.picV.get(i1+1); j++) {
						test1.add(j);
					}
					for (int j = d.picV.get(i1); j <= test.get(i); j++) {
						test1.add(j);
					}			
				}
			}
		}
		test1.add(test.get(0));
		System.out.println(test1);
		
		List<List<Integer>> ruv = new ArrayList<>();
		for (int i = 0; i < test1.size()-1; i++) {
			List<Integer> list = new ArrayList<>();
			list.add(test1.get(i));
			list.add(test1.get(i+1));
			ruv.add(list);
		}
		System.out.println(ruv);
		
		//�ҵ��յ��ߵ�����
		List<Integer> index2 = new ArrayList<>();
		for (int i = 0; i < ruv.size(); i++) {
			for (int j = 0; j < test.size()-1; j++) {
				if (ruv.get(i).get(0)==test.get(j)&&ruv.get(i).get(1)==test.get(j+1)) {
					index2.add(i);
				}
			}
		}
		System.out.println(index2);
		
		o = d.height;
		o = 14;
		
		System.out.println("���"+d.width);
		System.out.println("�峤"+d.height);
	
		for (int in = 0; in < ruv.size(); in++) {
			if (index2.contains(in)) {
				if ((cities.get(ruv.get(in).get(0)).getX()==cities.get(ruv.get(in).get(1)).getX())
						&&(cities.get(ruv.get(in).get(0)).getY()==cities.get(ruv.get(in).get(1)).getY())) {
					System.out.println(ruv.get(in));
				}else {
					dl.drawOL(cities.get(ruv.get(in).get(0)).getX()*o,
							600-cities.get(ruv.get(in).get(0)).getY()*o,
							cities.get(ruv.get(in).get(1)).getX()*o,
							600-cities.get(ruv.get(in).get(1)).getY()*o, p);
				}
		}else {
			dl.drawARL(cities.get(ruv.get(in).get(0)).getX()*o,
					600-cities.get(ruv.get(in).get(0)).getY()*o,
					cities.get(ruv.get(in).get(1)).getX()*o,
					600-cities.get(ruv.get(in).get(1)).getY()*o, p);
		}
		}
		
		
		
/*	// ��ʱ����100����һ��
	Timer timer = new Timer();
	TimerTask task = new TimerTask() {
		//int in = -1;
		@Override
		public void run() {
			// ��JavaFX��UI�̸߳��½���
			Platform.runLater(() -> {
				// ������������Ȼ����ȥ			
					in=in+1;
			if (in<ruv.size()) {
				if (index2.contains(in)) {
						if ((cities.get(ruv.get(in).get(0)).getX()==cities.get(ruv.get(in).get(1)).getX())
								&&(cities.get(ruv.get(in).get(0)).getY()==cities.get(ruv.get(in).get(1)).getY())) {
							System.out.println(ruv.get(in));
						}else {
							dl.drawOL(cities.get(ruv.get(in).get(0)).getX()*o,
									600-cities.get(ruv.get(in).get(0)).getY()*o,
									cities.get(ruv.get(in).get(1)).getX()*o,
									600-cities.get(ruv.get(in).get(1)).getY()*o, p);
						}
				}else {
					dl.drawARL(cities.get(ruv.get(in).get(0)).getX()*o,
							600-cities.get(ruv.get(in).get(0)).getY()*o,
							cities.get(ruv.get(in).get(1)).getX()*o,
							600-cities.get(ruv.get(in).get(1)).getY()*o, p);
				}
			}

			});
		}
	};
	// 100msִ��һ��
	timer.schedule(task, 1000,200);*/
		
		
		return p;
	}

}

