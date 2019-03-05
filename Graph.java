package assign07;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

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
	}
	
	public String generateDot()
	{
		StringBuilder dot = new StringBuilder("digraph d {\n "); 
		
		for(Vertex v : vertices.values())
		{
			Iterator<Edge> edges = v.edges(); 
			while(edges.hasNext())
			{
				dot.append("\t\"" + v.getData() + "\" -> \"" + edges.next() + "\"\n" ); 
			}
		}
		return dot.toString() + "}"; 
	}
	
	public String toString()
	{
		StringBuilder result = new StringBuilder(); 
		
		for(Vertex v : vertices.values())
		{
			result.append(v.data + "\n"); 
		}
		
		return result.toString(); 
	}
	
	private class Vertex 
	{
		private Type data; 
		
		private LinkedList<Edge> adj; 
		
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
				s += itr.next() + " "; 
			}
			return s; 
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
		
		public Type data()
		{
			return this.dst.getData();
		}
	}
}
