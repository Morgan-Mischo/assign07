package assign07;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphUtilityTest {

	private Graph<String> sGraph;
	
	@BeforeEach
	void setUp() throws Exception
	{
	    sGraph = new Graph<String>();
	    sGraph.addEdge("u0", "u2"); // u0 --> u2
	    sGraph.addEdge("u0", "u4");
	    sGraph.addEdge("u0", "u5");
	    sGraph.addEdge("u1", "u3");
	    sGraph.addEdge("u3", "u2");
	    sGraph.addEdge("u4", "u3");
	    sGraph.addEdge("u5", "u1");
	    sGraph.addEdge("u5", "u2");
	    sGraph.addEdge("u5", "u3");
	    sGraph.addEdge("u5", "u4");
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void makeGraph()
	{
		System.out.println(sGraph);
		System.out.println(sGraph.generateDot());
	}
	
	
	
//	    
//		  public static void generateRandomDotFile(String filename, int vertexCount) {
//			    PrintWriter out = null;
//			    try {
//			      out = new PrintWriter(filename);
//			    } 
//			    catch (IOException e) {
//			      System.out.println(e);
//			    }
//
//			    Random rng = new Random();
//
//			    // randomly construct either a graph or a digraph
//			    String edgeOp = "--";
//			    if (rng.nextBoolean()) {
//			      out.print("di");
//			      edgeOp = "->";
//			    }
//			    out.println("graph G {");
//
//			    // generate a list of vertices
//			    String[] vertex = new String[vertexCount];
//			    for(int i = 0; i < vertexCount; i++)
//			      vertex[i] = "v" + i;
//
//			    // randomly connect the vertices using 2 * |V| edges
//			    for(int i = 0; i < 2 * vertexCount; i++)
//			      out.println("\t" + vertex[rng.nextInt(vertexCount)] + edgeOp
//			          + vertex[rng.nextInt(vertexCount)]);
//
//			    out.println("}");
//			    out.close();
//			  }
//	
}