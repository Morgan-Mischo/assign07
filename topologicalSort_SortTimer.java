package assign07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class topologicalSort_SortTimer {

	public static <E> void main (String[] args) {

		final int timesToLoop = 10000; 

		List<Double> iList1 = new ArrayList<>(); 
		List<Double> iList2 = new ArrayList<>(); 

		for (int n = 1000; n <= timesToLoop; n += 1000)
		{
			iList1.add((double) n / 2);  
			iList2.add((double) (n));
			
			Integer srcData = n; 
			Integer dstData = n; 

			long startTime, midpointTime, stopTime;


			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { 
				// empty block
			}

			// Now, run the test.
			startTime = System.nanoTime();


			for (int i = 1000; i <= timesToLoop; i += 10)
			{
				GraphUtility.isCyclic(iList1, iList2); 
			}

			midpointTime = System.nanoTime();

			// Run a loop to capture the cost of running the "timesToLoop" loop and
			// generating a random ISBN.
			for (int i = 0; i < timesToLoop; i++) {

			}
			stopTime = System.nanoTime();

			// Compute the time, subtract the cost of running the loop
			// from the cost of running the loop and doing the lookups.
			// Average it over the number of runs.
			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
					/ (double) timesToLoop;
			System.out.println(n + "\t" + averageTime);
		}

	}

}
