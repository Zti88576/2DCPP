package maing;

import java.util.List;

import app.GenerateCircuit;
import graph.Edge;
import ilog.concert.IloException;
import ilog.concert.IloIntVar;
import ilog.concert.IloLinearIntExpr;
import ilog.concert.IloLinearNumExpr;
import ilog.cplex.IloCplex;


public class CpelxForMinCF {
	GenerateCircuit gCircuit = new GenerateCircuit();
	
	public void CFmodle(List<Integer> SingularPoint, List<Edge> edges) throws IloException {
		
		
		IloCplex model = new IloCplex();
		IloIntVar[][] x = new IloIntVar[SingularPoint.size()][SingularPoint.size()];
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x.length; j++) {
				x[i][j] = model.boolVar("X[" + i + ", " + j + "]");
			}
		}
		
		//objective function
		IloLinearNumExpr z = model.linearNumExpr();
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x.length; j++) {
				if (i == j)
					continue;
				z.addTerm(gCircuit.pointDistance(ganM.cities.get(SingularPoint.get(i)), ganM.cities.get(SingularPoint.get(j))), x[i][j]);
			}
		}
		model.addMinimize(z);
		
		// one cannot go to the same city as he is
		for (int i = 0; i < x.length; i++) {
			IloLinearIntExpr r = model.linearIntExpr();
			r.addTerm(1, x[i][i]);
			model.addEq(r, 0);
		}
		
		//constrain1: the number of total edges is half of point
		IloLinearIntExpr r = model.linearIntExpr();
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x.length; j++) {
				r.addTerm(1, x[i][j]);
			}
		}
		model.addEq(r, SingularPoint.size()/2);
			
		//constrain2: assure each point only have one out-degree or in-degree
		for (int i = 0; i < x.length; i++) {
			IloLinearIntExpr r1 = model.linearIntExpr();
			for (int j = 0; j < x.length; j++) {
				r1.addTerm(1, x[i][j]);
				r1.addTerm(1, x[j][i]);
			}
			model.addEq(r1, 1);		
		}
		
		if (model.solve()) {
			// get tour
			for (int i = 0; i < x.length; i++) {
				for (int j = 0; j < x.length; j++) {
					if (model.getValue(x[i][j]) >= 0.5) {
						//model.output().println(x[i][j]);
						edges.add(new Edge(SingularPoint.get(i), SingularPoint.get(j)));			
					}
				}
			}

		} else {
			System.err.println("Boi, u sick!");
			System.exit(1);
		}
		
	
	}
	
	
}
