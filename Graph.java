
package assign07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import lab08.Vertex;

public class Graph <Type>{
	
	HashMap<Type, Vertex> vertices; 
	
	public Graph() 
	{
		vertices = new HashMap<Type, Vertex>();
	}
	
	public void addEdge (Type type1, Type type2)
	{
		Vertex vertex1; 
		
		if(vertices.containsKey(type1))
		{
			vertex1 = vertices.get(type1); 
		}
		
		else 
		{
			vertex1 = new Vertex(type1); 
			vertices.put(type1, vertex1); 
		}
		
		Vertex vertex2; 
		 if(vertices.containsKey(type2)) 
		 {
			 vertex2 = vertices.get(type2); 
		 }
		 else 
		 {
			 vertex2 = new Vertex(type2); 
			 vertices.put(type2, vertex2); 
		 }
		 
		 vertex1.addEdge(vertex2);
	}
	
	public String generateDot()
	{
		StringBuilder dot = new StringBuilder("digraph d {\n "); 
		
		for(Vertex v : vertices.values())
		{
			Iterator<Edge> edges = v.edges(); 
			while(edges.hasNext())
			{
				dot.append("\t\"" + v.getData() + "\" -> \"" + edges.next().getData() + "\"\n" ); 
			}
		}
		return dot.toString() + "}"; 
	}
	
	public String toString()
	{
		StringBuilder result = new StringBuilder(); 
		
		for(Vertex v : vertices.values())
		{
			result.append(v + "\n"); 
		}
		
		return result.toString(); 
	}
	
	private class Vertex 
	{
		private Type data; 
		
		private LinkedList<Edge> adj; 
		public double distanceFromStart;
		//distance
		private double dist; 
		
		private Vertex prev; 
		
		private boolean visited; 
		
		private double indegree; 
		
		public Vertex (Type data)
		{
			this.data = data; 
			this.adj = new LinkedList<Edge>(); 
		}
		
		public Type getData ()
		{
			return data; 
		}
		
		public void addEdge (Vertex otherVertex)
		{
			adj.add(new Edge(otherVertex)); 
		}
		
		public Iterator<Edge> edges() 
		{
			return adj.iterator(); 
		}
		public String toString ()
		{
			String s = "Vertex " + data + " adjacent to vertices "; 
			Iterator<Edge> itr = this.edges(); 
			while (itr.hasNext())
			{
				s += itr.next().getData() + " "; 
			}
			return s; 
		}
		public double getDistanceFromStart() {
			return dist;
		}

		public void setDistanceFromStart(double dist) {
			this.dist = dist;
		}
		
		public Vertex getPrevious() {
			return prev; 
		}
		public void setPrevious(Vertex prev) {
			this.prev = prev; 
		}
		
		public boolean getVisted() {
			return visited; 
		}
		
		public void setVisited(boolean visited) {
			this.visited = visited; 
		}
		
		public LinkedList<Edge> getEdges() {
			return adj; 
		}
		public double getIndegree () {
			return adj.size(); 
		}
	}

	private class Edge
	{
		private Vertex dst; 
		
		public Edge(Vertex dst)
		{
			this.dst = dst; 
		}
		
		public Vertex getDestinationVertex()
		{
			return this.dst;
		}
		
		public Type getData()
		{
			return this.dst.getData();
		}
		
		public Vertex getOtherVertex() {
			return this.dst; 
		}
	}
	
	public boolean isCyclic (Vertex source)
	{
		//get starting vertex
		Vertex start = vertices.get(source);
		
		
		//set up priority queue of vertices prioritized by smallest distance
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(); 
		
		//for all vertices: set distance from start to INF
		for(Vertex v: vertices.values())
		{
			v.setDistanceFromStart(Double.POSITIVE_INFINITY);
		}
		
		start.setDistanceFromStart(0);
		pq.offer(start); 
		
		//call depthFirstSearch 
		return depthFirstSearch(start); 
		
		
	}
	
	public boolean depthFirstSearch(Vertex x)
	{
		if (x.getVisted() == true)
		{
			return true; 
		}
		//mark x as visited
		x.setVisited(true);
		
		//for each unvisited edge e from x do
		for(Edge e: x.getEdges())
		{
			Vertex w = e.getDestinationVertex(); 
			w.distanceFromStart = x.distanceFromStart + e.weight; 
			w.prev = x; 
			depthFirstSearch(w); 
		}
		return false; 
	}
	
	public <Type> List<Type> topologicalSort(Graph myGraph)
	{
		//a new queue of vertices to visit
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(); 
		
		//for each vi do
		for (Vertex v : vertices.values())
		{
			if (v.indegree == 0)
			{
				pq.add(v);  
			}
		}
		
		//while queue is not empty do
		while (!pq.isEmpty())
		{
			Vertex x = pq.remove(); 
			
			//x is next in sorted order
			
			//for each edge e from x do
			for (Edge e : x.getEdges())
			{
				Vertex w = e.getDestinationVertex(); 
				w.indegree = w.indegree - 1; 
				if (w.indegree == 0)
				{
					pq.add(w); 
				}
			}
		} 
		List <Type> result = new ArrayList<>(); 
		
		result.addAll(pq); 
		
		return result; 
		
	}
}
