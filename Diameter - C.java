/**
 * 
 */
package cs6301.g33;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author chandu
 *
 */
class Diameter {

	// Conducts a Breadth first search and return the path to the farthest node from the given root
	public static LinkedList<Graph.Vertex> bfsSearch(Graph g, Graph.Vertex root) {
		// distance array that maintains distance of each vertex from the root
		int dist[] = new int[g.size()];
		
		//Maintains the predecessor of each vertex
		int pre[] = new int[g.size()];
		// initializing to -1
		for (int i = 0; i< g.size(); i++)
			dist[i] = -1;
		
		Queue<Graph.Vertex> q = new LinkedList<>();
		q.add(root); // adding a random vertex to the queue 
		
		dist[root.name] = 0; // distance of the root from it self is 0
		while (!q.isEmpty()) {
			Graph.Vertex t = q.poll();    //vertex at the head of the queue
			
			for (Graph.Edge adjEdge: t.adj) {
				Graph.Vertex v = adjEdge.otherEnd(t); // other end of the edge
				if (dist[v.name] == -1) {
					q.add(v);
					pre[v.name] = t.name;
					//update the distance of each vertex from the root
					dist[v.name] = dist[t.name] + 1; // ?????????? adjEdge.weight ??????????????
				}
			}
		}
		
		int maxDistance = 0;
		int farthestNode = -1; // node farthest from root
		
		for (int i = 0; i < g.size(); i++) {
			if (dist[i] > maxDistance) {
				maxDistance = dist[i];
				farthestNode = i;
			}
		}
		
		LinkedList<Graph.Vertex> l = new LinkedList<>();
		int i = farthestNode;
		while(i != root.name) {
			l.add(g.v[i]);
			i = pre[i];
		}
		l.add(g.v[i]);
		return l;
	}
	
	//calculates the diameter of the graph
	public static LinkedList<Graph.Vertex> diameter(Graph g) {
		LinkedList<Graph.Vertex> l = bfsSearch(g, g.v[0]);
		Graph.Vertex u = l.removeFirst(); // node farthest from the randomly selected root
		return bfsSearch(g, u);
	}
	
	/**
	 * Driver program to test the functionality
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Graph g = Graph.readGraph(in);
		LinkedList<Graph.Vertex> l = diameter(g);
		for (Graph.Vertex v: l)
			System.out.print(v.toString() + " ---- ");

	}

}
