package test.com.kernelmaft.saxion_concurrency.assignment2;

import com.kernelmaft.saxion_concurrency.assignment2.SymmetricMinMaxHeap;
import org.junit.jupiter.api.*;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests specifically for the SMMH implementation of the DEPQ.
 */
class SymmetricMinMaxHeapTest
{
	private final SymmetricMinMaxHeap<Object> smmh;
	
	SymmetricMinMaxHeapTest()
	{
		smmh = new SymmetricMinMaxHeap<>();
	}
	
	/**
	 * Testing whether the DOT language generator creates a correct representation of the heap.
	 */
	@Test void dotRepresentation()
	{
		smmh.put("ten", 10);
		smmh.put("eleven", 11);
		smmh.put("twelve", 12);
		smmh.put("thirteen", 13);
		smmh.put("fourteen", 14);
		final String expectedDot = "digraph { 1 [label=\"1, root\"]; 2 [label=\"2, 10, ten\"]; 1->2; 3 [label=\"3, 14, fourteen\"]; 1->3; 4 [label=\"4, 11, eleven\"]; 2->4; 5 [label=\"5, 12, twelve\"]; 2->5; 6 [label=\"6, 13, thirteen\"]; 3->6; }";
		assertEquals(expectedDot, smmh.toDotTree(true, true));
	}
	
	/**
	 * Highly unscientific and undeterministic stress test that combines many randomly chosen operations on an SMMH.
	 * Useful for debugging.
	 */
	@Test @Disabled void stressTest()
	{
		for (int i = 0; i < 1000; i++) {
			String prevState = smmh.toDotTree(false, false);
			String lastOperation = "";
			try {
				switch (randomInt(4)) {
					case 0:
					case 1:
						int prio = randomInt(100);
						smmh.put(new Object(), prio);
						lastOperation = "Put elem with prio " + prio;
						break;
					case 2:
						if (!smmh.isEmpty()) {
							smmh.removeMin();
							lastOperation = "Removed min elem";
						}
						break;
					case 3:
						if (!smmh.isEmpty()) {
							smmh.removeMax();
							lastOperation = "Removed max elem";
						}
						break;
				}
			}
			catch (AssertionError ae) {
				System.out.println(lastOperation);
				System.out.println(smmh.toDotTree(false, false));
				System.out.println("Previous state:");
				System.out.println(prevState);
				fail("SMMH invalid");
				return;
			}
		}
		System.out.println(smmh.toDotTree(false, false));
	}
	
	/**
	 * Generates a random integer between 0 and a certain maximum. Helper function for {@link #stressTest}.
	 * @param maxExclusive The exclusive upper limit of the number to be generated.
	 * @return The random number.
	 */
	private static int randomInt(int maxExclusive)
	{
		return ThreadLocalRandom.current().nextInt(maxExclusive);
	}
}
