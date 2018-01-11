package test.com.kernelmaft.saxion_concurrency.assignment2;

import com.kernelmaft.saxion_concurrency.assignment2.SymmetricMinMaxHeap;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests specifically for the SMMH implementation of the DEPQ.
 */
class SymmetricMinMaxHeapTest
{
	private final SymmetricMinMaxHeap<Integer> smmh;
	
	SymmetricMinMaxHeapTest()
	{
		smmh = new SymmetricMinMaxHeap<>();
	}
	
	@BeforeEach void setUp()
	{}
	
	@AfterEach void tearDown()
	{}
}