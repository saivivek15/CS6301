package cs6301.g33.shortProject1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author Sai Vivek Kanaparthy
 * @author Chandra Sekhar Guntupalli
 * @author Sushma Eati
 * @author Abhinaya Krishna Mandepudi
 */


public class Diameter {
	public static Graph.Vertex pathLastVertex; // Last Vertex of BFS traversal
	public static LinkedList<Graph.Vertex> path; // Queue maintained in a BFS traversal
    public static int[] distance; // Distance from the start node of the traversal
    public static DVertex[] dVertex; 
    Graph g;
    
    
    // Class to store information about a vertex in this algorithm
    class DVertex {
    	Graph.Vertex element;
    	boolean seen;
    	Graph.Vertex parent;
    	DVertex(Graph.Vertex u) {
    		element = u;
    		seen = false;
    	}
    }
    
    public Diameter(Graph g) {
    	this.g = g;
    	dVertex = new DVertex[g.size()];
    	for(Graph.Vertex u: g) { dVertex[u.name] = new DVertex(u); }
    }
    
    
    /**
	 * @param u : start vertex from where BFS is executed
	 * Procedure for breadth first search algorithm 
	 */
    public  void bfsVisit(Graph.Vertex u) {
    	
    	//Queue for traversing 
    	path  = new LinkedList<>();
    	// distance of node from the start node
    	distance = new int[g.n];
    	//initialized with -1
    	for(int i=0;i<distance.length;i++){
    		distance[i]=-1;
    	}
    	visit(u);
    	path.add(u);
    	pathLastVertex=u;
    	distance[u.getName()]=0;
    	while(path.size()!=0){
    		// remove the visited node 
    		Graph.Vertex start = path.remove();
    		pathLastVertex = start;
    		// traverse across the adjacency list of the node
    		for(Graph.Edge e: start.adj){
        		Graph.Vertex v = e.otherEnd(start);
        		if(distance[v.getName()]==-1){
        			distance[v.getName()] = distance[start.getName()]+1;
        			dVertex[v.getName()].parent = start;
        			}
        		if(!seen(v)) {
        			visit(v);
        			path.add(v);
    		    	}	
    		}
    	}
    }
    
    
    /**
     * 
     * @param g : Graph on which the diameter is to be determined
     * @return LinkedList of path of vertices of diameter
     */
    public static LinkedList<Graph.Vertex> diameter(Graph g){
    	Diameter bfsOne = new Diameter(g);
    	LinkedList<Graph.Vertex> diameterPath = new LinkedList<Graph.Vertex>();
    	// First BFS call with arbitrary start node
    	bfsOne.bfsVisit(g.v[2]);
    	Graph.Vertex lastBFSOne = pathLastVertex;
    	Diameter bfsTwo = new Diameter(g);
    	// Second BFS call with end node of first BFS
    	bfsTwo.bfsVisit(lastBFSOne);
    	Graph.Vertex lastBFSTwo =pathLastVertex;
    	while(lastBFSTwo!=lastBFSOne){
    		diameterPath.add(lastBFSTwo);
    		Graph.Vertex pr1 = dVertex[lastBFSTwo.getName()].parent;
    		lastBFSTwo = pr1;
    	}
    	diameterPath.add(lastBFSTwo);
    	return diameterPath;
    }
    
    public boolean seen(Graph.Vertex u) {
		DVertex ccu = getDVertex(u);
		return ccu.seen;
    }

    // Visit a node by marking it as seen
    void visit(Graph.Vertex u) {
    	DVertex ccu = getDVertex(u);
    	ccu.seen = true;
    }

    // From Vertex to CCVertex 
    DVertex getDVertex(Graph.Vertex u) {
	return dVertex[u.name];
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
	LinkedList<Graph.Vertex> dmtrPath=diameter(g);
	int length = dmtrPath.size() - 1;
	System.out.println("Length of diameter: "+length);
	System.out.print("Path of diameter: ");
	for(Graph.Vertex vertex: dmtrPath){
		System.out.print(vertex+" ");
	}
    }


}
