//@Author Sushma Eati, Sai Vivek Kanaparthy
// Starter code for LP4
// Do not rename this file or move it away from cs6301/g??

// change following line to your group number
package cs6301.g33.longProject4;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cs6301.g33.longProject4.Graph.Edge;
import cs6301.g33.longProject4.Graph.Vertex;

public class LP4 {
	Graph g;
	Vertex s;

	// common constructor for all parts of LP4: g is graph, s is source vertex
	public LP4(Graph g, Vertex s) {
		this.g = g;
		this.s = s;
	}

	// Class for finding the shortest paths in a DAG.
	public static class ShortestPaths {
		public ShortestPaths(Vertex vertex, boolean isVisited, Vertex parent,
				int distance, int tempDistance, int count) {
			this.vertex = vertex;
			this.isVisited = isVisited;
			this.parent = parent;
			this.distance = distance;
			this.tempDistance = tempDistance;
			this.count = count;
		}

		Graph.Vertex vertex;
		Graph.Vertex parent;
		boolean isVisited;
		int distance;
		int tempDistance;
		int count;
	}

	// Class for finding the topological sorts in a DAG.
	public static class TopologicalSort {
		public TopologicalSort(Vertex vertex, int inDegree, boolean isVisited) {
			super();
			this.vertex = vertex;
			this.inDegree = inDegree;
			this.isVisited = isVisited;
		}

		Graph.Vertex vertex;
		int inDegree;
		boolean isVisited;
	}

	TopologicalSort[] topVertex;
	ShortestPaths[] shortestPaths;
	static long shortestPath;
	static long topologicalSorts;
	
	
	
	// Part a. Return number of topological orders of g
		public long countTopologicalOrders() {
			// Basic initialization to set the indegree of the vertices
			initializeGraphForTopologicalSort();
			int[] res = new int[0];
			return findTopologicalSortsUtil(res, 0, true);
		}

		// Part b. Print all topological orders of g, one per line, and
		// return number of topological orders of g
		public long enumerateTopologicalOrders() {
			// Basic initialization to set the indegree of the vertices
			initializeGraphForTopologicalSort();
			int[] res = new int[g.size()];
			return findTopologicalSortsUtil(res, 0, false);
		}
		
		// Part e. Return weight of shortest path from s to t using at most k edges
		public int constrainedShortestPath(Vertex t, int k) {
			// Initialize the graph
			initializeShortestPaths();
			// Set the temp distance of source node to 0
			shortestPaths[s.name].tempDistance = 0;

			// Iterate at-most k times
			for (int j = 1; j <= k; j++) {
				// Iterate through all the vertices
				for (ShortestPaths v : shortestPaths) {
					// Iterate through the adjacency list of the vertices
					for (Edge e : v.vertex.adj) {
						// If the distance of the vertex is not equal to infinity
						// (In this case MAX VALUE and distance of the other end of
						// the vertex is > than the distance of the vertex and edge
						// weight0
						if ((shortestPaths[v.vertex.name].distance != Integer.MAX_VALUE)
								&& shortestPaths[e.to.name].distance > (shortestPaths[v.vertex.name].distance + e.weight))

						{
							// Update tempDistances at intermediate iterations and
							// update the original distance at the end.
							if (shortestPaths[e.to.name].tempDistance > shortestPaths[v.vertex.name].distance
									+ e.weight)
								shortestPaths[e.to.name].tempDistance = shortestPaths[v.vertex.name].distance
										+ e.weight;
							shortestPaths[e.to.name].parent = shortestPaths[v.vertex.name].vertex;
						}
					}

				}
				// Iterate through the vertices and update the distance.
				for (ShortestPaths sp : shortestPaths) {
					sp.distance = sp.tempDistance;
				}
			}
			// Return the distance at the t vertex.
			return shortestPaths[t.getName()].distance;
		}



		// Basic initialize to maintain the count of in-degree vertices
		public void initializeGraphForTopologicalSort() {
			// Initialize the topVertex based on the size of the graph.
			topVertex = new TopologicalSort[g.size()];
			// Iterate through the vertices and set the count of inDegree count and
			// isVisited values
			for (Vertex v : g.v) {
				topVertex[v.getName()] = new TopologicalSort(v, v.revAdj.size(),
						false);
			}
	
		}
	
		 // Initialization method for PART E (Can be used for PART C & PART D also.

		public void initializeShortestPaths() {
			shortestPaths = new ShortestPaths[g.size()];
			for (Vertex v : g.v) {
				shortestPaths[v.getName()] = new ShortestPaths(v, false, null,
						Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
			}
			shortestPaths[s.name].distance = 0;
			shortestPaths[s.name].isVisited = true;
		}

		// Helper function to find the topological sorts of a graph.
		public long findTopologicalSortsUtil(int[] result, int index,
				boolean isCount) {
			// This flag check whether all the topological orders are found or not
			boolean checkAllTopologicalSorts = false;
	
			// Iterate through the number of vertices
			for (int i = 0; i < g.v.length; i++) {
				// Enter only if in-degree is 0 and vertex is not visited
				if (topVertex[i].isVisited == false && topVertex[i].inDegree == 0) {
					// Mark the vertex as not visited.
					topVertex[i].isVisited = true;
					// Iterate through the adjacency list of that vertex, and reduce
					// the in-degree of those vertices
					Iterator<Edge> it = topVertex[i].vertex.adj.iterator();
					while (it.hasNext()) {
						int k = it.next().to.name;
						topVertex[k].inDegree--;
					}
					// Add the vertex to the result list.(Only in case of printing)
					if (!isCount)
						result[index++] = topVertex[i].vertex.name;
	
					// Recursively call the findTopologicalSort on other vertices.
					findTopologicalSortsUtil(result, index, isCount);
	
					// Reset the vertex as not visited and backtrack
					topVertex[i].isVisited = false;
					index--;
					it = topVertex[i].vertex.adj.iterator();
					while (it.hasNext()) {
						topVertex[it.next().to.name].inDegree++;
					}
					checkAllTopologicalSorts = true;
				}
	
			}
	
			if (!checkAllTopologicalSorts) {
				if (!isCount) {
					for (int i = 0; i < index; i++) {
						System.out.print(result[i] + 1 + "\t");
					}
					System.out.println();
				}
				topologicalSorts++;
			}
			return topologicalSorts;
		}
	
	//NOT IMPLEMENTED

	
	// Part f. Reward collection problem
	// Reward for vertices is passed as a parameter in a hash map
	// tour is empty list passed as a parameter, for output tour
	// Return total reward for tour
	public int reward(HashMap<Vertex, Integer> vertexRewardMap,
			List<Vertex> tour) {
		// To do
		return 0;
	}

	// Do not modify this function
	static void printGraph(Graph g, HashMap<Vertex, Integer> map, Vertex s,
			Vertex t, int limit) {
		System.out.println("Input graph:");
		for (Vertex u : g) {
			if (map != null) {
				System.out.print(u + "($" + map.get(u) + ")\t: ");
			} else {
				System.out.print(u + "\t: ");
			}
			for (Edge e : u) {
				System.out.print(e + "[" + e.weight + "] ");
			}
			System.out.println();
		}
		if (s != null) {
			System.out.println("Source: " + s);
		}
		if (t != null) {
			System.out.println("Target: " + t);
		}
		if (limit > 0) {
			System.out.println("Limit: " + limit + " edges");
		}
		System.out.println("___________________________________");
	}

	// Part c. Return the number of shortest paths from s to t
	// Return -1 if the graph has a negative or zero cycle
	public long countShortestPaths(Vertex t) {
		// To do
		return 0;
	}

	// Part d. Print all shortest paths from s to t, one per line, and
	// return number of shortest paths from s to t.
	// Return -1 if the graph has a negative or zero cycle.
	public long enumerateShortestPaths(Vertex t) {
		initializeShortestPaths();
		ShortestPaths temp = new ShortestPaths(t, false, null, 0, 0, 0);
		Queue<ShortestPaths> queue = new LinkedList<ShortestPaths>();
		// Add source to the queue...
		queue.add(shortestPaths[t.name]);
		while (!queue.isEmpty()) {
			temp = queue.remove();
			temp.isVisited = false;
			temp.count = temp.count + 1;

			// Negative edge cycles.
			if (temp.count >= g.size())
				return 0;

			Iterator<Edge> it = temp.vertex.adj.iterator();
			while (it.hasNext()) {
				if (shortestPaths[it.next().to.name].distance > (shortestPaths[it
						.next().from.name].distance + it.next().weight)) {
					shortestPaths[it.next().to.name].distance = (shortestPaths[it
							.next().from.name].distance + it.next().weight);
					shortestPaths[it.next().to.name].parent = shortestPaths[it
							.next().from.name].vertex;
					if (!shortestPaths[it.next().to.name].isVisited) {
						queue.add(shortestPaths[it.next().to.name]);
						shortestPaths[it.next().to.name].isVisited = true;
					}
				}
			}
		}

		return 0;
	}

}
