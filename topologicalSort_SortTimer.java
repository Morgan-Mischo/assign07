package assign07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//Added times as a comment

public class topologicalSort_SortTimer {

	public static <E> void main (String[] args) {

		final int problemSize = 100000;
		final int TIMES_TO_LOOP = 1000;
		
		Random rng = new Random();

		List<String> iList1 = new ArrayList<>(); 
		List<String> iList2 = new ArrayList<>(); 

		for (int i = 10000; i <= problemSize; i += 10000)
		{
			iList1.clear();
			iList2.clear();
			
			for (int j = 0; j < i; j++)
			{
				iList1.add("v" + j);  
				iList2.add("v" + rng.nextInt(i));
			}
			
			String srcData = "v" + rng.nextInt(i); 
			String dstData = "v" + rng.nextInt(i);; 

			long startTime, midpointTime, stopTime = System.nanoTime();

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { 
				// empty block
			}

			// Now, run the test.
			startTime = System.nanoTime();


			for (int j = 0; j <= TIMES_TO_LOOP; j++)
			{
				GraphUtility.isCyclic(iList1, iList2); 
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop and
			// generating a random ISBN.
			for (int j = 0; j <= TIMES_TO_LOOP; j++) {

			}
			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups.
			// Average it over the number of runs.
			double averageTime = (double) ((midpointTime - startTime) - (stopTime - midpointTime))
					/ TIMES_TO_LOOP;
			System.out.println(i + "\t" + averageTime);
		}

	}

}
