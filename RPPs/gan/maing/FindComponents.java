package maing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import graph.Edge;
import graphics.EulerCircuit;
import graphics.EulerCircuit.NotFoundException;

public class FindComponents {

	public void find_components(List<Edge> AR) {
		
		
		List<List<Integer>> edges1 = new ArrayList<>();
		for (Edge edge : AR) {
			List<Integer> e = new ArrayList<>();
			e.add(edge.getFrom());
			e.add(edge.getTo());
			edges1.add(e);
		}
		
		ganM.Components = new ArrayList<>();
		compotent(edges1, ganM.Components);
		//System.out.println(GANM.Components);

	}
	
	//集合去重
	public static void removeDuplicate(List list) {
		HashSet h = new HashSet(list);
		list.clear();
		list.addAll(h);
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
	
}
