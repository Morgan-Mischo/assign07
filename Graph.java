package assign07;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
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
					w.setPrev(x);
					myQueue.offer(w);
				}
				if(w.getData() == dstData)
					return true;
			}
		}
		return false;
	}
	
	private class Vertex 
	{
		private Vertex prev;

		private Type data; 
		
		private LinkedList<Edge> adj; 
		public double distanceFromStart;
		
		public Vertex (Type data)
		{
			this.data = data; 
			this.adj = new LinkedList<Edge>(); 
		}
		
		public Type getData ()
		{
			return data; 
		}
		
		public Vertex getPrev() 
		{
			return prev;
		}

		public void setPrev(Vertex prev) 
		{
			this.prev = prev;
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
	}
}