package main;

import ilog.concert.IloException;

import ilog.concert.IloIntVar;
import ilog.concert.IloLinearNumExpr;
import ilog.cplex.IloCplex;
import main.Data;

public class Cplex {
	Data data;
	IloCplex model;
	public IloIntVar[] x;
	

	public void solve() throws IloException{
		 int count = 0;
		if(model.solve() == false) {
			System.out.println("problem should not solve false!!!");
		}else {
			model.output().println("Solution status = " + model.getStatus());
            model.output().println("Solution value = " + model.getObjValue());
            data.solutionvalue = model.getObjValue();
            double[] val =  model.getValues(x);              
            for (int j = 0; j < val.length; j++)
            	if(val[j] > 0.5){
            	//model.output().println("x" + (j) + "  = " + (val[j]));
            		count++;
            	data.ResultA.add(j);
            	if (val[j]>1.999999) {
					data.ResultA.add(j);
					data.MResultA.add(j);
				}
            	
            }
		}
	}	
	
	
	public Cplex(Data data) {
		this.data = data;
	}
	
	public void build_model() throws IloException{
		model = new IloCplex();
		
		x = new IloIntVar[data.edgenum];
		
		for(int e = 0; e < data.edgenum; e++) {
			x[e] = model.intVar(0, 10);
		}
			

		IloIntVar[] w = new IloIntVar[data.vertexnum];
		
		
		//System.out.println(data.ss);
		//目标函数
			IloLinearNumExpr obj = model.linearNumExpr();
				for(int e = 0;e<data.edgenum;e++){
					obj.addTerm(data.ss.get(e), x[e]);
				}
				model.addMinimize(obj);								
				
		//约束1
		for(int i = 0;i<data.Asets.size();i++){
			IloLinearNumExpr r1 = model.linearNumExpr();
			w[i] = model.intVar(1, 10);
			IloLinearNumExpr r3 = model.linearNumExpr();		
			
			for (int j = 0; j < data.ARsets.size(); j++) {
				x[data.ARsets.get(j)].setMin(1);				
			}			
			for (int j = 0; j < data.Asets.get(i).size(); j++) {				
			r1.addTerm(1, x[(data.Asets.get(i).get(j))]);			
			}
			
			r3.addTerm(2, w[i]);				
			model.addEq(r1, r3);
			//System.out.println(model);
		}	
		
		
/*		//约束2	
		for (int j = 0; j < data.OLsets.size(); j++) {
			IloLinearNumExpr r1 = model.linearNumExpr();
			for (int i = 0; i < data.OLsets.get(j).size(); i++) {		
				r1.addTerm(1, x[(data.OLsets.get(j).get(i))]);				
			}
			if (data.OLsets.size()==1&&data.OLsets.get(j).size()==0) {
				model.addEq(0, r1);
			}else {
				model.addLe(2, r1);	
			}			
		}*/
		

	}
}
