package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.Timer;
import java.util.TimerTask;


import graph.*;
import graphics.*;
import ilog.cplex.IloCplex.Param.Tune;
import javafx.application.Application;
import javafx.application.Platform;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import app.Data;

public class APP extends Application{

	
	Data data = new Data();
	
	double o;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		launch(args);
		
	}
	
	@Override
	public  void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		
		long start=System.currentTimeMillis(); //��ȡ��ʼʱ��
		
		stage.setTitle("L");
		
		
		
		EulerCircuit el = new EulerCircuit();
		
		//String path = "C:/Users/Administrator/Desktop/�и�·������/testInstance/rpp3/add2.pack";
		//String path = "C:/Users/Administrator/Desktop/�и�·������/testInstance/rpp3/THREE1.txt";		
		//String path = "C:/Users/Administrator/Desktop/�и�·������/testInstance/rpp3/ll.pack";
		//String path = "C:/Users/Administrator/Desktop/�и�·������/testInstance/rpp3/pdd.pack";
		String path = "C:/Users/Administrator/Desktop/����ʵ��/blaz2_s.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/dagli.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/dighe2.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/dighe1_s.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/fu.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/dighe2.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/poly3a.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/shapes2.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/shapes - ����.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/shirts.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/swim.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/trousers.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/albano.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/jakobs1.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/mao.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/marques.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/trousers.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/albano.pack";
		
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/L0003_lingjian(83.514210%).pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/L0004(84.186735%).pack";
		
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/L0004(84.356724%) - ����.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/L0004(84.356724%) - ���� - ����.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/L0004(84.356724%).pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/L0005(84.021087%) - ���� - ���� - ����.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/L0005(84.021087%) - ���� - ����.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/L0005(84.021087%) - ����.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/L0005(84.021087%) - ���� (2).pack";
		
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/L0005(84.021087%).pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/L0005(83.225580%).pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/L0005(83.247886%).pack";
		
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/profiles2 - ����.pack";
		//String path = "C:/Users/Administrator/Desktop/����ʵ��/profiles9_3.pack";
		

		ArrayList<City> cities = new ArrayList<>();
		ArrayList<Edge> edges = new ArrayList<>();
		int[][] matrix ;
		
		
		ReadFile rFile = new ReadFile();
		UndirectedG undirectedG = new UndirectedG();
		
		rFile.Read_data(path, data, cities, edges);
		System.out.println(data.picV.get(data.picV.size()-1)-1);
		System.out.println("1:"+edges.size());
		matrix = new int[cities.size()][cities.size()];
		undirectedG.Generate_graph(cities, edges, matrix);
		System.out.println("2:"+edges.size());
/*		 for (int i = 0; i < edges.size(); i++) {
				System.out.print(edges.get(i).getFrom()+","+edges.get(i).getTo()+",");
			}

		    System.out.println();*/
		    
		    GenerateCircuit gCircuit = new GenerateCircuit();   
		    
		    double test2 = 0;
			 for (int i = 0; i < edges.size(); i++) {
				double l = gCircuit.pointDistance(cities.get(edges.get(i).getFrom()), cities.get(edges.get(i).getTo()));
				test2 = test2 + l;
			}
			 System.out.println(edges.size());
		    System.out.println("test2:"+test2);  
		
/*		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print((int)matrix[i][j]+"  ");				
			}
			System.out.println();
		}*/
	 
		
		//�����ӻ�·
		List<Edge> oList = new ArrayList<>(); 
		//GenerateCircuit gCircuit = new GenerateCircuit(); 
		gCircuit.generate_cycle(cities, matrix, oList);
	
		
		//��ӵ��߼���
	   edges.addAll(oList);
	    for (int i = 0; i < oList.size(); i++) {
			//System.out.println(oList.get(i).getFrom()+","+oList.get(i).getTo());
		}
	    System.out.println("3:"+edges.size());
	   //�����ӻ�·
	    ConnectCircuit cCircuit = new ConnectCircuit();
	    List<Edge> twog = new ArrayList<>();
	    
	    cCircuit.connect_circuit(edges, cities,twog);	
	    
	    
	    int[][] rra = new int[edges.size()][];
		 for (int i = 0; i < edges.size(); i++) {
			 rra[i] = new int[]{edges.get(i).getFrom(),edges.get(i).getTo()};
		 }
		 
		 /*for (int i = 0; i < rra.length; i++) {
			System.out.println(rra[i][0]+","+rra[i][1]+",");
		}
		 System.out.println();*/
		 
		 int[] a = new int[rra.length*2];
		 int x = 0;
		 for (int[] i : rra) {
			for (int j : i) {
				a[x++] = j; 					
			}
		}
		 
		 /*for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}*/
		 
			Map<Integer, Integer> map = new HashMap<>();
		      
	        for (int str : a) {
	           Integer num = map.get(str);  
	           map.put(str, num == null ? 1 : num + 1);
	        }
	        Set set = map.entrySet();
	        Iterator it = set.iterator();	 
	        Iterator it01 = map.keySet().iterator();
	        int  y = 0;
	        while (it01.hasNext()) {
	            Object key = it01.next();
	            if (map.get(key)%2!=0) {
					System.out.println(key+"+"+map.get(key));
				}else {
					y++;
					//System.out.println("��");
				}
	} 
		 //System.out.println("y:"+y);
		 
		          
	    // euler(rra, s,cities.size(), edges.get(0).getFrom());   
	    // System.out.println(s);   
		// System.out.println("edge.size"+rra.length);
	 List<Integer> test = new ArrayList<>(el.EulerCircuitByDFS(rra, cities.size(),edges.get(0).getFrom()));  
	    	    
	 /*do {
		 int[][] rra = new int[edges.size()][];
		 for (int i = 0; i < edges.size(); i++) {
			 rra[i] = new int[]{edges.get(i).getFrom(),edges.get(i).getTo()};
		 }
		 List<Integer> ruvv = new ArrayList<>(el.EulerCircuitByDFS(rra, cities.size(),edges.get(0).getFrom()));  
		 tours.add(ruvv);
		 //System.out.println(ruvv);   
		 //System.out.println(ruvv.size());
	
		 List<Edge> edges2 = new ArrayList<>();
		 for (Integer integer : ruvv) {
			 for (Edge edge : edges) {
				if (integer==edge.getFrom()||integer==edge.getTo()) {
					//System.out.println(integer);
					//System.out.println(edge.getFrom()+","+edge.getTo()+" ");
					edges2.add(edge);
				}
			}
		 } 
		 edges.removeAll(edges2); 
	} while (edges.size()!=0);*/
	 
	 
	 //List<Integer> test = new ArrayList<>();
	 //System.out.println(test.size());
	 double test1 = 0;
	 	//System.out.println(test.size());
/*	 	for (int i = 0; i < edges.size(); i++) {
	 		//System.out.println(test.get(i));
			//System.out.println(test.get(i+1));
	 		double l = gCircuit.pointDistance(cities.get(edges.get(i).getFrom()), cities.get(edges.get(i).getTo()));
	 		//System.out.println(l);
	 		test1 = test1 + l;
	 		//System.out.println(test1);
		}*/	
	 for (int i = 0; i < test.size()-1; i++) {
	 		//System.out.println(test.get(i));
			//System.out.println(test.get(i+1));
	 		double l = gCircuit.pointDistance(cities.get(test.get(i)), cities.get(test.get(i+1)));
	 		//System.out.println(l);
	 		test1 = test1 + l;
	 		//System.out.println(test1);
		}
	   
	 	System.out.println("test1:"+test1);
	 	
	 	System.out.println(data.picnum);
	 	System.out.println(data.picV.size()-1);
	 	System.out.println(cities.size());
	 	long end=System.currentTimeMillis(); //��ȡ����ʱ��
		System.out.println("��������ʱ�䣺 "+(end-start)*0.001+"s");
		BorderPane border = new BorderPane();
		border.setStyle("-fx-background-color:#FFF");
		
		Text text = new Text(path);
		Label label = new Label(String.valueOf(test1));
		
		HBox hBox = new HBox();
		hBox.getChildren().addAll(text,label);
		border.setCenter(addPane(test,cities,oList,twog));
		border.setRight(hBox);
		
		
		
		Scene scene = new Scene(border,800,800);	
		stage.setScene(scene);
		stage.show();
	}

	
	private Pane addPane(List<Integer> test,ArrayList<City> cities,List<Edge> oList,List<Edge > two) {
		// TODO Auto-generated method stub
		Pane p = new Pane();
		p.setTranslateX(100);
		DrawLine dl = new DrawLine();
		//System.out.println(test);
		
		//�ջ���·���ߵĵ㼯
		//List<List<Integer>> ruv = new ArrayList<>();
 		List<Edge> ruv = new ArrayList<>();
		for (int i = 0; i < test.size()-1; i++) {
			/*List<Integer> list = new ArrayList<>();
			list.add(test.get(i));
			list.add(test.get(i+1));
			ruv.add(list);*/
			//System.out.println(test.get(i)+","+test.get(i+1));
			Edge edge = new Edge(test.get(i), test.get(i+1));
			ruv.add(edge);
		 }
			
		/*for (Edge edge : ruv) {
			System.out.println(edge.getFrom()+","+edge.getTo());
		}*/
		
		//�ҳ��ظ��ıߵ�����
			
		List<Integer> index1 = new ArrayList<>();
		for (int i = 0; i < ruv.size()-1; i++) {
			for (int j = ruv.size()-1; j > i; j--) {
				if ((ruv.get(j).getFrom()==ruv.get(i).getFrom()&&ruv.get(j).getTo()==ruv.get(i).getTo())
						||(ruv.get(j).getFrom()==ruv.get(i).getTo()&&ruv.get(j).getTo()==ruv.get(i).getFrom())) {
						index1.add(j);			
				}
			}
		}
		//�ҵ��յ��ߵ�����
		List<Integer> index2 = new ArrayList<>();
		List<Integer> index3 = new ArrayList<>();
		for (int i = 0; i < ruv.size(); i++) {
			for (Edge edge : oList) {
				if ((ruv.get(i).getFrom()==edge.getFrom()&&ruv.get(i).getTo()==edge.getTo())
						||(ruv.get(i).getFrom()==edge.getTo()&&ruv.get(i).getTo()==edge.getFrom())) {
					index2.add(i);
				}
			}
			for (Edge edge : two) {
				if ((ruv.get(i).getFrom()==edge.getFrom()&&ruv.get(i).getTo()==edge.getTo())
						||(ruv.get(i).getFrom()==edge.getTo()&&ruv.get(i).getTo()==edge.getFrom())) {
					index3.add(i);
				}
			}
		}
		System.out.println(index2);
		index2.removeAll(index1);
	    index2.addAll(index3);

	    data.width = 800/data.width;
		data.height = 800/data.height;
		if (data.width<data.height) {
			data.height = data.width;
		}
		o = data.height;
		//o = 3;
		
		for (int in = 0; in < ruv.size(); in++) {
			if (in<ruv.size()) {
				if (index2.contains(in)) {
					dl.drawOL(cities.get(ruv.get(in).getFrom()).getX()*o, 
							800-cities.get(ruv.get(in).getFrom()).getY()*o, 
							cities.get(ruv.get(in).getTo()).getX()*o, 
							800-cities.get(ruv.get(in).getTo()).getY()*o, p);
				}else {
						dl.drawARL(cities.get(ruv.get(in).getFrom()).getX()*o, 
								800-cities.get(ruv.get(in).getFrom()).getY()*o, 
								cities.get(ruv.get(in).getTo()).getX()*o, 
								800-cities.get(ruv.get(in).getTo()).getY()*o, p);
					}
				
			}
		}
		
		// ��ʱ����100����һ��
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			int in = -1;
			@Override
			public void run() {
				// ��JavaFX��UI�̸߳��½���
				Platform.runLater(() -> {
					in = in +1;
/*					if (in<ruv.size()) {
						if (index2.contains(in)) {
							dl.drawOL(cities.get(ruv.get(in).getFrom()).getX()*o, 
									600-cities.get(ruv.get(in).getFrom()).getY()*o, 
									cities.get(ruv.get(in).getTo()).getX()*o, 
									600-cities.get(ruv.get(in).getTo()).getY()*o, p);
						}else {
								dl.drawARL(cities.get(ruv.get(in).getFrom()).getX()*o, 
										600-cities.get(ruv.get(in).getFrom()).getY()*o, 
										cities.get(ruv.get(in).getTo()).getX()*o, 
										600-cities.get(ruv.get(in).getTo()).getY()*o, p);
							}
						
					}*/
				});
			}
		};
		// 100msִ��һ��
		timer.schedule(task, 100,500);	
		return p;
	}

/*	public void euler(int[][] graph,Stack s,int current,int start){
		int num_edges = graph.length;  //graph�еı���
		boolean flag = false;  //�Ƿ�����x�����ı�
		s.push(current);
		for(int i = start; i < graph.length ; i++){
			//��start��ʼ�����Ƿ��б�
			if(graph[i][current] > 0){ //i��current�б�
				flag = true;
				graph[i][current] = 0; graph[current][i] = 0; //ɾ����
				euler(graph,s,i,0);  //��i��ʼ����
				break;
			}
		}
		
		if(flag){ //���û�б��뵱ǰ�ڵ�current����
			s.pop();
			int m = (int) s.peek();
			graph[m][current] = graph[current][m] = 1; //�ָ���
			int new_start = current + 1;
			if(s.size() == num_edges){ //��ɻ�·
				return;
			}else{
				euler(graph,s,m,new_start);
			}
		}
	}*/
	
}
