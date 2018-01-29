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
	
	private static int randomInt(int maxExclusive)
	{
		return ThreadLocalRandom.current().nextInt(maxExclusive);
	}
	
	@BeforeEach void setUp()
	{}
	
	@AfterEach void tearDown()
	{}
}
