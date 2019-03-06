package assign07;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GraphUtilityTest {

	private Graph<String> sGraph;
	private List<String> sList1, sList2;
	
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
	     
	    sList1 = new ArrayList<String>();
	    sList1.add("u0");
	    sList1.add("u0");
	    sList1.add("u0");
	    sList1.add("u1");
	    sList1.add("u3");
	    sList1.add("u4");
	    sList1.add("u5");
	    sList1.add("u5");
	    sList1.add("u5");
	    sList1.add("u5");
	    
	    sList2 = new ArrayList<String>();
	    sList2.add("u2");
	    sList2.add("u4");
	    sList2.add("u5");
	    sList2.add("u3");
	    sList2.add("u2");
	    sList2.add("u3");
	    sList2.add("u1");
	    sList2.add("u2");
	    sList2.add("u3");
	    sList2.add("u4");
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void printGraph()
	{
//		System.out.println(sGraph);
//		System.out.println(sGraph.generateDot());
	}
	
	@Test
	void listToGraph()
	{
		Graph<String> myGraph = new Graph<String>();
		for(int i = 0; i < sList1.size(); i ++)
		{
			myGraph.addEdge(sList1.get(i), sList2.get(i));
		}
		assertEquals(sGraph.toString(), myGraph.toString());
		assertEquals(sGraph.generateDot(), myGraph.generateDot());
	}
	
	@Test
	void areConnected()
	{
		assertTrue(sGraph.areConnected("u0", "u4"));
		assertFalse(sGraph.areConnected("u1", "u5"));
	}
	
	@Test
	void areConnectedGraphUtility()
	{
		assertTrue(GraphUtility.areConnected(sList1, sList2, "u0", "u4"));
		assertFalse(GraphUtility.areConnected(sList1, sList2, "u1", "u5"));
	}
	
	@Test
	void areConnectedThrows()
	{
		assertThrows(IllegalArgumentException.class, () -> { GraphUtility.areConnected(sList1, sList2, "u3", "u6"); });
	}
}
//	@Test
//	void test1()
//	{
//		ArrayList<String> a1, a2;
//		GraphUtility.buildListsFromDot(filename, a1, a2);
//		GraphUtility.areConnected(a1, a2, srcData, dstData)
//	}
	
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
