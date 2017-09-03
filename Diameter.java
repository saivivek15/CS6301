package cs6301.g33;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/*
 * @author Sai Vivek Kanaparthy
 */


public class Diameter {
	
	public static LinkedList<Graph.Vertex> path, tmp;
    public static int[] previous, distance;
    CCVertex[] ccVertex;
    Graph g;
    
    
    // Class to store information about a vertex in this algorithm
    class CCVertex {
    	Graph.Vertex element;
    	boolean seen;
    	int cno;
    	CCVertex(Graph.Vertex u) {
    		element = u;
    		seen = false;
    		cno = -1;
    	}
    }
    
    public Diameter(Graph g) {
    	this.g = g;
    	ccVertex = new CCVertex[g.size()];
    	for(Graph.Vertex u: g) { ccVertex[u.name] = new CCVertex(u); }
    }
    
    
    /**
	 * @param u : vertex from where BFS is executed
	 * Procedure for breadth first search algorithm 
	 */
    public  void bfsVisit(Graph.Vertex u) {
    	// path is used to store the order of traversal and can be accesed anywhere as it static variable
    	path = new LinkedList<>();
    	//Queue for traversing 
    	tmp  = new LinkedList<>();
    	// parent node of the node
    	previous = new int[g.n];
    	// distance of node from the start node
    	distance = new int[g.n];
    	for(int i=0;i<distance.length;i++){
    		distance[i]=-1;
    		previous[i]=u.getName();
    	}
    	visit(u);
    	path.add(u);
    	tmp.add(u);
    	distance[u.getName()]=0;
    	while(tmp.size()!=0){
    		// remove the visited node 
    		Graph.Vertex start = tmp.remove();
    		// traverse across the adjacency list of the node
    		Iterator<Graph.Edge> it = start.adj.iterator();
    		while(it.hasNext()){
    		Graph.Edge e = it.next();
    		Graph.Vertex v = e.otherEnd(start);
    		if(distance[v.getName()]==-1){
    			distance[v.getName()] = distance[start.getName()]+1;
    			previous[v.getName()] = start.getName();
    			}
    		if(!seen(v)) {
    			visit(v);
    			path.add(v);
    			tmp.add(v);
		    	}	
    		}
    	}
    }
    
    
    /**
     * 
     * @param g : Graph on which the diameter is to be determined
     * @return LinkedList of path of vertices of diameter
     */
    public LinkedList<Graph.Vertex> diameter(Graph g){
    	Diameter bfsOne = new Diameter(g);
    	LinkedList<Graph.Vertex> diameterPath = new LinkedList<Graph.Vertex>();
    	// First BFS call with arbitrary start node
    	bfsOne.bfsVisit(g.getVertex(2));
    	Graph.Vertex lastBFSOne = path.getLast();
    	Diameter bfsTwo = new Diameter(g);
    	// Second BFS call with end node of first BFS
    	bfsTwo.bfsVisit(lastBFSOne);
    	Graph.Vertex lastBFSTwo =path.getLast();
    	while(lastBFSTwo!=lastBFSOne){
    		diameterPath.add(lastBFSTwo);
    		int pr = previous[lastBFSTwo.getName()]+1;
    		lastBFSTwo = g.getVertex(pr);
    	}
    	diameterPath.add(lastBFSTwo);
    	return diameterPath;
    }
    
    public boolean seen(Graph.Vertex u) {
		CCVertex ccu = getCCVertex(u);
		return ccu.seen;
    }

    // Visit a node by marking it as seen
    void visit(Graph.Vertex u) {
    	CCVertex ccu = getCCVertex(u);
    	ccu.seen = true;
    }

    // From Vertex to CCVertex 
    CCVertex getCCVertex(Graph.Vertex u) {
	return ccVertex[u.name];
    }
    
    
    /**
     * Driver Class
     * 
     */
    public static void main(String[] args) throws FileNotFoundException {
	Scanner in;
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }
	Graph g = Graph.readGraph(in);
	Diameter d = new Diameter(g);
	LinkedList<Graph.Vertex> dmtrPath=d.diameter(g);
	System.out.println("Length of diameter: "+dmtrPath.size());
	System.out.print("Path of diameter: ");
	for(Graph.Vertex vertex: dmtrPath){
		System.out.print(vertex+" ");
	}
    }


}
