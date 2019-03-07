
package assign07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

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

		private Vertex next; 
		
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
			otherVertex.indegree++; 
		}
		
		public Iterator<Edge> edges() 
		{
			return adj.iterator(); 
		}
		public String toString ()
		{
			String s = data + ":"; 
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
		public Vertex getNext() {
			return next; 
		}
		public void setPrevious(Vertex prev) {
			this.prev = prev; 
		}
		public void setNext() {
			this.next = next; 
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
			return indegree; 
		}
	}

	private class Edge
	{
		private boolean visited; 
		
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
		public boolean getVisted() {
			return visited; 
		}
		
		public void setVisited(boolean visited) {
			this.visited = visited; 
		}
	}
	
	public boolean isCyclic()
	{
		boolean a = false; 
		//for all vertices: set distance from start to INF
		for(Vertex v: vertices.values())
		{
			this.reset(v);
			a = depthFirstSearch(v); 
		}
		return a; 
	}
	public void reset (Vertex x) {
		x.setDistanceFromStart(Double.POSITIVE_INFINITY);
		x.setVisited(false);	
	}
	
	public boolean depthFirstSearch(Vertex x)
	{
		x.setVisited(true); 

		//for each unvisited edge e from x do
		for(Edge e: x.getEdges())
		{
			if(e.getDestinationVertex().getVisted() == false) {
				Vertex w = e.getDestinationVertex();
				w.prev = x; 
				e.setVisited(true);
				depthFirstSearch(w); 
			}
			else if (e.getDestinationVertex().getVisted() == true)
				{
					return true; 
				}
		}
		return false; 	
	}
	
	public boolean areConnected(Type srcData, Type dstData)
	{
		Vertex s = vertices.get(srcData);
		
		for(Vertex v : vertices.values())
		{
			v.distanceFromStart = Double.POSITIVE_INFINITY;
		}
			
		Queue<Vertex> myQueue = new LinkedList<Vertex>();
		myQueue.offer(s);
		s.distanceFromStart = 0;
		
		while(!myQueue.isEmpty())
		{
			Vertex x = myQueue.poll();
			
			Iterator<Edge> edges = x.edges();
			while(edges.hasNext())
			{
				Vertex w = edges.next().getDestinationVertex();
				if(w.distanceFromStart == Double.POSITIVE_INFINITY)
				{
					w.distanceFromStart = x.distanceFromStart + 1;
					w.setPrevious(x);
					myQueue.offer(w);
				}
				if(w.getData() == dstData)
					return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public <Type> List<Type> topologicalSort()
	{
		//a new queue of vertices to visit
		Queue<Vertex> pq = new LinkedList<Vertex>(); 
		List<Type> result = new LinkedList<Type>();
		
		//for each vi do
		for (Vertex v : vertices.values())
		{
			if (v.indegree == 0)
			{
				pq.offer(v); 
				result.add((Type) v.getData()); 
			}
		}
		
		//while queue is not empty do
		while (!pq.isEmpty())
		{
			Vertex x = pq.poll(); 
			
			//x is next in sorted order

			//for each edge e from x do
			for (Edge e : x.getEdges())
			{
				Vertex w = e.getDestinationVertex(); 
				w.indegree = w.indegree - 1; 
				if (w.indegree == 0)
				{
					result.add((Type) w.getData()); 
					pq.offer(w); 
				}
			}
		} 
		return result;
	}
}
