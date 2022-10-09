package maing;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import app.Data;
import app.GenerateCircuit;
import app.ReadFile;
import app.UndirectedG;
import graph.City;
import graph.Edge;
import graphics.DrawLine;
import graphics.EulerCircuit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ganM extends Application{

	static Data data = new Data();
	
	double o;
	
	public static ArrayList<City> cities;
	static ArrayList<Edge> AR;
	static int[][] G;
	
	public static List<List<Integer>> Components;
	static List<Edge> oList;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		launch(args);
		
	}
	
	
	
	@Override
	public  void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub

		EulerCircuit el = new EulerCircuit();
				
		//String path = "C:/Users/Administrator/Desktop/排样实例/albano.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/dagli.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/blaz2_s.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/dighe2.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/dighe1_s.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/fu.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/dighe2.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/poly4b.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/shapes.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/shapes - 副本.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/shirts.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/swim.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/trousers.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/albano.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/jakobs2.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/mao.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/marques.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/trousers.pack";
		
		//String path = "C:/Users/Administrator/Desktop/排样实例/profiles2_1.pack";
		
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0003_lingjian(83.514210%).pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0004(84.186735%).pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0004(84.356724%).pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0005(83.225580%).pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0005(83.247886%).pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0005(84.021087%).pack";
		
		String path = "C:/Users/Administrator/Desktop/排样实例/profiles6_3.pack";
		
		
		cities = new ArrayList<>();
		AR = new ArrayList<>();

		double time = 0;			
		long start=System.currentTimeMillis(); //获取开始时间
		
		ReadFile rFile = new ReadFile();
		rFile.Read_data1(path, data, cities, AR);
		//System.out.println(data.picV.get(data.picV.size()-1)-1);
		//System.out.println("1:"+edges.size());
		
		UndirectedG undirectedG = new UndirectedG();
		G = new int[cities.size()][cities.size()];
		undirectedG.Generate_graph(cities, AR, G);
		//System.out.println("2:"+edges.size());
/*		 for (int i = 0; i < edges.size(); i++) {
				System.out.print(edges.get(i).getFrom()+","+edges.get(i).getTo()+",");
			}
		    System.out.println();*/
		
		FindComponents findComponents = new FindComponents();
		findComponents.find_components(AR);
		
		System.out.println("P is"+Components.size());
		
		oList = new ArrayList<>();
		GenerateCircuit gCircuit = new GenerateCircuit();
		
		ConnectComponents connectComponents = new ConnectComponents();
/*		List<Edge> edges1 = new ArrayList<>();
		connectComponents.connect_CbyG(edges1);
		
		List<Edge> edges2 = new ArrayList<>();
		connectComponents.connect_CbyS(edges2);
		
		
		double length1 = 0;
		for (Edge edge : edges1) {
			length1 = length1 + gCircuit.pointDistance(ganM.cities.get(edge.getFrom()), ganM.cities.get(edge.getTo()));
		}
		//System.out.println(length1);
		double length2 = 0;
		for (Edge edge : edges2) {
			length2 = length2 + gCircuit.pointDistance(ganM.cities.get(edge.getFrom()), ganM.cities.get(edge.getTo()));
		}
		//System.out.println(length2);
		
		if (length2<length1) {
			oList.addAll(edges2);
		}else {
			oList.addAll(edges1);
		}*/
		
		List<Edge> edges3 = new ArrayList<>();
		connectComponents.connect_CbyT(edges3);
		oList.addAll(edges3);
		
		List<Edge> ALL = new ArrayList<>();
		ALL.addAll(AR);
		ALL.addAll(oList);
		
/*		double test2 = 0;
		for (Edge edge : ALL) {
			double l = gCircuit.pointDistance(cities.get(edge.getFrom()), cities.get(edge.getTo()));
			test2 = test2 + l;
		}*/
		
		
	    int[][] rra = new int[ALL.size()][];
		 for (int i = 0; i < ALL.size(); i++) {
			 rra[i] = new int[]{ALL.get(i).getFrom(),ALL.get(i).getTo()};
		 }		
		 List<Integer> test = new ArrayList<>(el.EulerCircuitByDFS(rra, cities.size(),ALL.get(0).getFrom()));  
		 double test1 = 0;
		 for (int i = 0; i < test.size()-1; i++) {
		 		//System.out.println(test.get(i));
				//System.out.println(test.get(i+1));
		 		double l = gCircuit.pointDistance(cities.get(test.get(i)), cities.get(test.get(i+1)));
		 		//System.out.println(l);
		 		test1 = test1 + l;
		 		//System.out.println(test1);
			}
		 
		System.out.println("总长度="+test1);
		long end=System.currentTimeMillis(); //获取结束时间
		time = (end-start)*0.001;
		System.out.println("时间="+time);
		
		BorderPane border = new BorderPane();
		border.setStyle("-fx-background-color:#FFF");
		
		Text text = new Text(path);
		Label label = new Label(String.valueOf(test1));
		
		HBox hBox = new HBox();
		hBox.getChildren().addAll(text,label);
		border.setCenter(addPane(test,cities,oList));
		border.setRight(hBox);
		
				
		Scene scene = new Scene(border,800,800);	
		stage.setScene(scene);
		stage.show();
		
		
	}

	private Pane addPane(List<Integer> test,ArrayList<City> cities,List<Edge> oList) {
		// TODO Auto-generated method stub
		
		Pane p = new Pane();
		p.setTranslateX(100);
		DrawLine dl = new DrawLine();
		//System.out.println(test);
		
		//闭环回路的线的点集
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
		
		//找出重复的边的索引			
		List<Integer> index1 = new ArrayList<>();
		for (int i = 0; i < ruv.size()-1; i++) {
			for (int j = ruv.size()-1; j > i; j--) {
				if ((ruv.get(j).getFrom()==ruv.get(i).getFrom()&&ruv.get(j).getTo()==ruv.get(i).getTo())
						||(ruv.get(j).getFrom()==ruv.get(i).getTo()&&ruv.get(j).getTo()==ruv.get(i).getFrom())) {
						index1.add(j);			
				}
			}
		}
		//找到空刀边的索引
		List<Integer> index2 = new ArrayList<>();
		List<Integer> index3 = new ArrayList<>();
		for (int i = 0; i < ruv.size(); i++) {
			for (Edge edge : oList) {
				if ((ruv.get(i).getFrom()==edge.getFrom()&&ruv.get(i).getTo()==edge.getTo())
						||(ruv.get(i).getFrom()==edge.getTo()&&ruv.get(i).getTo()==edge.getFrom())) {
					index2.add(i);
				}
			}
/*			for (Edge edge : two) {
				if ((ruv.get(i).getFrom()==edge.getFrom()&&ruv.get(i).getTo()==edge.getTo())
						||(ruv.get(i).getFrom()==edge.getTo()&&ruv.get(i).getTo()==edge.getFrom())) {
					index3.add(i);
				}
			}*/
		}
		System.out.println(index2);
		//index2.removeAll(index1);
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
		
		// 计时器，100毫秒一次
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			int in = -1;
			@Override
			public void run() {
				// 让JavaFX的UI线程更新界面
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
		// 100ms执行一次
		timer.schedule(task, 100,500);	
		return p;
	}
	
	
	
}
