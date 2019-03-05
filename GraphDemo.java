package assign07;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import assign07.Graph;

public class GraphDemo {
	  public static void main(String[] args) {

		    // build a sample graph
		    Graph<String> g = new Graph<String>();
		    g.addEdge("u0", "u2"); // u0 --> u2
		    g.addEdge("u0", "u4");
		    g.addEdge("u0", "u5");
		    g.addEdge("u1", "u3");
		    g.addEdge("u3", "u2");
		    g.addEdge("u4", "u3");
		    g.addEdge("u5", "u1");
		    g.addEdge("u5", "u2");
		    g.addEdge("u5", "u3");
		    g.addEdge("u5", "u4");
		    
		    System.out.println(g.generateDot());
		    System.out.println(g.toString());

//		    // build another sample graph
//		    g = new Graph();
//		    g.addEdge("s", "v1", 3);
//		    g.addEdge("s", "v2", 6);
//		    g.addEdge("s", "v7", 1);
//		    g.addEdge("v1", "v2", 2);
//		    g.addEdge("v2", "v3", 1);
//		    g.addEdge("v3", "v4", 6);
//		    g.addEdge("v3", "v5", 3);
//		    g.addEdge("v3", "v6", 4);
//		    g.addEdge("v4", "v5", 3);
//		    g.addEdge("v5", "v6", 2);
//		    g.addEdge("v7", "v1", 2);
//		    g.addEdge("v7", "v6", 10);
		  }
		  public static void generateRandomDotFile(String filename, int vertexCount) {
			    PrintWriter out = null;
			    try {
			      out = new PrintWriter(filename);
			    } 
			    catch (IOException e) {
			      System.out.println(e);
			    }

			    Random rng = new Random();

			    // randomly construct either a graph or a digraph
			    String edgeOp = "--";
			    if (rng.nextBoolean()) {
			      out.print("di");
			      edgeOp = "->";
			    }
			    out.println("graph G {");

			    // generate a list of vertices
			    String[] vertex = new String[vertexCount];
			    for(int i = 0; i < vertexCount; i++)
			      vertex[i] = "v" + i;

			    // randomly connect the vertices using 2 * |V| edges
			    for(int i = 0; i < 2 * vertexCount; i++)
			      out.println("\t" + vertex[rng.nextInt(vertexCount)] + edgeOp
			          + vertex[rng.nextInt(vertexCount)]);

			    out.println("}");
			    out.close();
			  }

}
