package graphics;

import java.util.*;


public class EulerCircuit {

	
    private List<Integer> path;

    /**
     * this cost O(|E| + |V|) time
     * @param unDirectedEdges: adjacency matrix,[1,2] represents edge from node1 to node2
     * @param n: the num of nodes
     * @param k: the start node of Euler Circuit
     * @return
     * @throws NotFoundException
     */
    public List<Integer> EulerCircuitByDFS(int[][] unDirectedEdges, int n, int k) throws NotFoundException{
        if (unDirectedEdges==null||unDirectedEdges.length<=1||n<=2) {
            throw new NotFoundException();
        }
        //init undirected graph,use adjacency list
        //{key:1, value:<2, 3>} represents edge from node1 to node2,node3
        Map<Integer, List<Integer>> graph = new HashMap<>();
        //making graph takes O(E)
        //iterate the adjacency matrix
        for(int i = 0; i<unDirectedEdges.length; i++) {
            int[] edge = unDirectedEdges[i];
            //add (edge[0], edge[1])
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new ArrayList<Integer>());
            }
            graph.get(edge[0]).add(edge[1]);
            //add (edge[1], edge[0])
            if (!graph.containsKey(edge[1])) {
                graph.put(edge[1], new ArrayList<Integer>());
            }
            graph.get(edge[1]).add(edge[0]);
        }
        path = new ArrayList<>();
        //ECDFS takes O(V)
        try {
            ECDFS(graph, k, k, path);
        }catch (NotFoundException e){
            throw e;
        }
        return path;
    }

    /**
     * special dfs for Euler Circuit
     * @param graph
     * @param k: start node
     * @param origin: the origin start node
     * @param currentPath
     * @throws NotFoundException
     */
    public void ECDFS(Map<Integer, List<Integer>> graph, int k, int origin, List<Integer> currentPath)
            throws NotFoundException{
        currentPath.add(k);
        for(int i = 0; i<graph.get(k).size(); i++){
            int neighbor = graph.get(k).get(i);
            //and the degree of node w is odd
            if(neighbor!=origin && graph.get(neighbor).size()%2!=0){
                throw new NotFoundException();
            }
            graph.get(k).remove(i);
            graph.get(neighbor).remove(Integer.valueOf(k));

            //when dfs return to the origin start node
            //some edges may not have been visited
            if(neighbor==origin){
                currentPath.add(origin);
                boolean allSeen;
                do{
                    boolean tmp = true;
                    for(int j = 0; j<currentPath.size(); j++) {
                        int entryNode = currentPath.get(j);
                        tmp &= graph.get(entryNode).size() == 0;
                        if(!tmp) {
                            List<Integer> tempPath = new ArrayList<>();
                            ECDFS(graph, entryNode, entryNode , tempPath);
                            //add child circuit path
                            int index = currentPath.indexOf(entryNode);
                            currentPath.remove(index);
                            currentPath.addAll(index, tempPath);
                        }
                    }
                    allSeen = tmp;
                }while (!allSeen);
                return;
            }
            else {
                ECDFS(graph, neighbor, origin, currentPath);
            }

        }
    }

    public static class NotFoundException extends Exception{
        public NotFoundException(){
            super("Euler Circuit Not Found");
        }
    }
    

}

