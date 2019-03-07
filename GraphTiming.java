package assign07;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraphTiming {

		public static void main(String[] args) {
			
			final int TIMES_TO_LOOP = 1000;
			
		//	System.out.println("GraphUtility\n" + "" + "\tareConnected\tisCyclic\ttopographicalSort");
			List<String> sList1 = new ArrayList<String>();
			List<String> sList2 = new ArrayList<String>();
			
			for(int i = 1000; i < 10001; i+=1000)
			{
				sList1.add("v" + i);
				sList2.add("v" + i+1);
			}
			
		}
	
}
