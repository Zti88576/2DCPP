package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.plaf.basic.BasicTabbedPaneUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Data.Edge;
import graphics.DrawLine;
import graphics.EulerCircuit;
import graphics.EulerCircuit.NotFoundException;

public class Main extends Application{

	
	List<Edge> edges = new ArrayList<>();
	Data data = new Data();
	DataR dR = new DataR();
	DataP dP = new DataP();
	EulerCircuit el = new EulerCircuit();
	DrawLine dl = new DrawLine();
	
	double o;

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		launch(args);
		
	}
		
	@Override
	public void start(Stage stage) throws Exception{
		
		long start=System.currentTimeMillis(); //获取开始时间
		
		stage.setTitle("L");
		// TODO Auto-generated method stub
		
		
		//String path = "C:/Users/Administrator/Desktop/切割路径问题/testInstance/rpp3/add2.pack";
		//String path = "C:/Users/Administrator/Desktop/切割路径问题/testInstance/rpp3/THREE1.txt";		
		//String path = "C:/Users/Administrator/Desktop/切割路径问题/testInstance/rpp3/ll.pack";
		//String path = "C:/Users/Administrator/Desktop/切割路径问题/testInstance/rpp3/pdd.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/blaz2_s.pack";
		String path = "C:/Users/Administrator/Desktop/排样实例/dagli.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/dighe2.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/dighe1_s.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/fu.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/dighe2.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/poly5b.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/shapes1.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/shapes - 副本 (2).pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/shirts.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/swim.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/trousers.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/albano.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/jakobs1.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/mao.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/marques.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/trousers.pack";
		
		
		//String path = "E:/ZT/L0003_lingjian(83.514210%).pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0003_lingjian(83.514210%).pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0004(84.186735%).pack";
		
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0004(84.356724%) - 副本.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0004(84.356724%) - 副本 - 副本.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0004(84.356724%).pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0005(84.021087%) - 副本 - 副本 - 副本.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0005(84.021087%) - 副本 - 副本.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0005(84.021087%) - 副本.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/L0005(84.021087%).pack";
		
		//String path = "C:/Users/Administrator/Desktop/排样实例/profiles2 - 副本.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/profiles9_3.pack";
		//String path = "C:/Users/Administrator/Desktop/排样实例/profiles6_2.pack";
		
		//String path = "E:/ZT/profiles9_2.pack";
		
		dR.Read_data(path, data);
		dP.datap(data,edges);
		
		Cplex cplex = new Cplex(data);	
		//cplex.build_model();		
		//cplex.solve();
	

		long end=System.currentTimeMillis(); //获取结束时间
		System.out.println("程序运行时间： "+(end-start)*0.001+"s");	
		
		
		BorderPane border = new BorderPane();
		border.setStyle("-fx-background-color:#FFF");
		
		Text text = new Text(path);
		Label label = new Label(String.valueOf(data.solutionvalue));
		
		HBox hBox = new HBox();
		hBox.getChildren().addAll(text,label);
		border.setCenter(addPane());
		border.setRight(hBox);
		

		//System.out.println(data.width);
		
		
		Scene scene = new Scene(border,800,800);	
		stage.setScene(scene);
		stage.show();
		
		
	}

	private Pane addPane() throws NotFoundException {
		// TODO Auto-generated method stub	
			
		Pane p = new Pane();
		
		p.setTranslateX(100);		
		
		
		int[][] rra = new int[data.ResultA.size()][];		
		for (int i = 0; i < data.ResultA.size(); i++) {
			rra[i] = new int[]{edges.get(data.ResultA.get(i)).getX(),edges.get(data.ResultA.get(i)).getY()};
		}
		
		List<Integer> ruvv = new ArrayList<>(el.EulerCircuitByDFS(rra, data.vertexnum,0));
		
		//闭环回路的线的点集
		List<List<Integer>> ruv = new ArrayList<>();
		for (int i = 0; i < ruvv.size()-1; i++) {
			List<Integer> list = new ArrayList<>();
			list.add(ruvv.get(i));
			list.add(ruvv.get(i+1));
			ruv.add(list);
		}
		
	    data.width = 800/data.width;
		data.height = 800/data.height;
		if (data.width<data.height) {
			data.height = data.width;
		}
		o = data.height;
		//o = 3;
		
		System.out.println(ruv.size());
		
		//找出重复的边的索引
		List<Integer> index1 = new ArrayList<>();
		for (int i = 0; i < ruv.size()-1; i++) {
			for (int j = ruv.size()-1; j > i; j--) {
				if ((ruv.get(j).get(0).equals(ruv.get(i).get(0))&&ruv.get(j).get(1).equals(ruv.get(i).get(1)))
					||(ruv.get(j).get(0).equals(ruv.get(i).get(1))&&ruv.get(j).get(1).equals(ruv.get(i).get(0)))) {
					//System.out.println(ruv.get(i)+","+ruv.get(j));
					index1.add(j);
				}
			}			
		}
		System.out.println(index1);
		
		//找出切割边的索引
		List<Integer> index = new ArrayList<>();
		for (int i = 0; i < ruv.size(); i++) {
			for (Integer integer : data.ARsets) {
				if ((ruv.get(i).get(0).equals(edges.get(integer).getX())&&
						ruv.get(i).get(1).equals(edges.get(integer).getY()))||
						(ruv.get(i).get(0).equals(edges.get(integer).getY())&&
								ruv.get(i).get(1).equals(edges.get(integer).getX()))) {
					index.add(i);
				}
			}
		}
		System.out.println(index);
		
		
		for (int in = 0; in < ruv.size(); in++) {
			//System.out.println(ruv.get(in)+","+data.Vsets.get(ruv.get(in).get(0))+","+data.Vsets.get(ruv.get(in).get(1)));			
				if (index.contains(in)&&index1.contains(in)==false) {
					dl.drawARL(Double.parseDouble(data.Vsets.get(ruv.get(in).get(0)).get(0))*o,
							800-Double.parseDouble(data.Vsets.get(ruv.get(in).get(0)).get(1))*o,
							Double.parseDouble(data.Vsets.get(ruv.get(in).get(1)).get(0))*o,
							800-Double.parseDouble(data.Vsets.get(ruv.get(in).get(1)).get(1))*o, p);
				}else {
					dl.drawOL(Double.parseDouble(data.Vsets.get(ruv.get(in).get(0)).get(0))*o,
							800-Double.parseDouble(data.Vsets.get(ruv.get(in).get(0)).get(1))*o,
							Double.parseDouble(data.Vsets.get(ruv.get(in).get(1)).get(0))*o,
							800-Double.parseDouble(data.Vsets.get(ruv.get(in).get(1)).get(1))*o, p);
				}
			
			
			
		}
	
		
		
/*	// 计时器，100毫秒一次
	Timer timer = new Timer();
	TimerTask task = new TimerTask() {
		int in = -1;
		@Override
		public void run() {
			// 让JavaFX的UI线程更新界面
			Platform.runLater(() -> {
				// 计算你的随机数然后画上去			
					in=in+1;
			if (in<ruv.size()) {
				if (index.contains(in)&&index1.contains(in)==false) {
					dl.drawARL(Double.parseDouble(data.Vsets.get(ruv.get(in).get(0)).get(0))*o,
							600-Double.parseDouble(data.Vsets.get(ruv.get(in).get(0)).get(1))*o,
							Double.parseDouble(data.Vsets.get(ruv.get(in).get(1)).get(0))*o,
							600-Double.parseDouble(data.Vsets.get(ruv.get(in).get(1)).get(1))*o, p);
				}else {
					dl.drawOL(Double.parseDouble(data.Vsets.get(ruv.get(in).get(0)).get(0))*o,
							600-Double.parseDouble(data.Vsets.get(ruv.get(in).get(0)).get(1))*o,
							Double.parseDouble(data.Vsets.get(ruv.get(in).get(1)).get(0))*o,
							600-Double.parseDouble(data.Vsets.get(ruv.get(in).get(1)).get(1))*o, p);
				}	
			}

			});
		}
	};
	// 100ms执行一次
	timer.schedule(task, 2000,200);*/
		
		return p;
	}

}
