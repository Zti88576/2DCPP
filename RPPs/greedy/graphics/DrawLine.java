package graphics;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class DrawLine {

	public void drawARL(double sx, double sy, double ex, double ey,Pane p){
	
	       double  H  =   6 ;  // 箭头高度    
	        double  L  =   6 ; // 底边的一半   
	        double  x3  =   0 ;
	        double  y3  =   0 ;
	        double  x4  =   0 ;
	        double  y4  =   0 ;
	        double  awrad  =  Math.atan(L  /  H);  // 箭头角度    
	        double  arraow_len  =  Math.sqrt(L  *  L  +  H  *  H); // 箭头的长度    
	        double [] arrXY_1  =  rotateVec(ex  -  sx, ey  -  sy, awrad,  true , arraow_len);
	        double [] arrXY_2  =  rotateVec(ex  -  sx, ey  -  sy,  - awrad,  true , arraow_len);
	        double  x_3  =  ex  -  arrXY_1[ 0 ];  // (x3,y3)是第一端点    
	        double  y_3  =  ey  -  arrXY_1[ 1 ];
	        double  x_4  =  ex  -  arrXY_2[ 0 ]; // (x4,y4)是第二端点    
	        double  y_4  =  ey  -  arrXY_2[ 1 ];

	      Double X3  =   new  Double(x_3);
	      x3  =  X3.intValue();
	      Double Y3  =   new  Double(y_3);
	      y3  =  Y3.intValue();
	      Double X4  =   new  Double(x_4);
	      x4  =  X4.intValue();
	      Double Y4  =   new  Double(y_4);
	      y4  =  Y4.intValue();
	       // g.setColor(SWT.COLOR_WHITE);
	       // 画线    
	       //g2.drawLine(sx, sy, ex, ey);
	      Line line = new Line(sx, sy, ex, ey);
	      line.setStroke(Color.RED);
	      line.setStrokeWidth(0.75);
	       // 画箭头的一半 
	       //g2.drawLine(ex, ey, x3, y3);
	      Line line1 = new Line(ex, ey, x3, y3);
	      line1.setStroke(Color.RED);
	      line1.setStrokeWidth(0.5);
	       // 画箭头的另一半 
	       //g2.drawLine(ex, ey, x4, y4);
	      Line line2 = new Line(ex, ey, x4, y4);
	      line2.setStroke(Color.RED);
	      line2.setStrokeWidth(0.5);
	      p.getChildren().addAll(line,line1,line2);

	    }
	
	
	public void drawOL(double sx, double sy, double ex, double ey,Pane p){
		
	       double  H  =   6 ;  // 箭头高度    
	        double  L  =   6 ; // 底边的一半   
	        double  x3  =   0 ;
	        double  y3  =   0 ;
	        double  x4  =   0 ;
	        double  y4  =   0 ;
	        double  awrad  =  Math.atan(L  /  H);  // 箭头角度    
	        double  arraow_len  =  Math.sqrt(L  *  L  +  H  *  H); // 箭头的长度    
	        double [] arrXY_1  =  rotateVec(ex  -  sx, ey  -  sy, awrad,  true , arraow_len);
	        double [] arrXY_2  =  rotateVec(ex  -  sx, ey  -  sy,  - awrad,  true , arraow_len);
	        double  x_3  =  ex  -  arrXY_1[ 0 ];  // (x3,y3)是第一端点    
	        double  y_3  =  ey  -  arrXY_1[ 1 ];
	        double  x_4  =  ex  -  arrXY_2[ 0 ]; // (x4,y4)是第二端点    
	        double  y_4  =  ey  -  arrXY_2[ 1 ];

	      Double X3  =   new  Double(x_3);
	      x3  =  X3.intValue();
	      Double Y3  =   new  Double(y_3);
	      y3  =  Y3.intValue();
	      Double X4  =   new  Double(x_4);
	      x4  =  X4.intValue();
	      Double Y4  =   new  Double(y_4);
	      y4  =  Y4.intValue();
	       // g.setColor(SWT.COLOR_WHITE);
	       // 画线    
	       //g2.drawLine(sx, sy, ex, ey);
	      Line line = new Line(sx, sy, ex, ey);
	      line.getStrokeDashArray().addAll(3.0, 5.0);
	      line.setStroke(Color.BLUE);
	      line.setStrokeWidth(1);
	       // 画箭头的一半 
	       //g2.drawLine(ex, ey, x3, y3);
	      Line line1 = new Line(ex, ey, x3, y3);
	      line1.setStroke(Color.BLUE);
	      line1.setStrokeWidth(0.5);
	       // 画箭头的另一半 
	       //g2.drawLine(ex, ey, x4, y4);
	      Line line2 = new Line(ex, ey, x4, y4);
	      line2.setStroke(Color.BLUE);
	      line2.setStrokeWidth(0.5);
	      p.getChildren().addAll(line,line1,line2);

	    }

	       //计算
	   public  double [] rotateVec( double  px,  double  py,  double  ang,  boolean  isChLen,
	           double  newLen)   {
	       double  mathstr[]  =   new   double [ 2 ];
	       // 矢量旋转函数，参数含义分别是x分量、y分量、旋转角、是否改变长度、新长度    
	        double  vx  =  px  *  Math.cos(ang)  -  py  *  Math.sin(ang);
	       double  vy  =  px  *  Math.sin(ang)  +  py  *  Math.cos(ang);
	       if  (isChLen)   {
	           double  d  =  Math.sqrt(vx  *  vx  +  vy  *  vy);
	          vx  =  vx  /  d  *  newLen;
	          vy  =  vy  /  d  *  newLen;
	          mathstr[ 0 ]  =  vx;
	          mathstr[ 1 ]  =  vy;
	      } 
	       return  mathstr;
	  }
	
	
}
