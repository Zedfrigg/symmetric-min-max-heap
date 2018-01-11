package test.com.kernelmaft.saxion_concurrency.assignment2;

import com.kernelmaft.saxion_concurrency.assignment2.DoubleEndedPrioQueue;
import com.kernelmaft.saxion_concurrency.assignment2.SymmetricMinMaxHeap;
import org.junit.jupiter.api.*;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the DEPQ interface. Independent of implementation.
 */
class DoubleEndedPrioQueueTest
{
	private final DoubleEndedPrioQueue<Integer> depq;
	
	DoubleEndedPrioQueueTest()
	{
		depq = new SymmetricMinMaxHeap<>();
	}
	
	@Test void noElements()
	{
		assertEquals(0, depq.size());
		assertThrows(NoSuchElementException.class, depq::getMin);
		assertThrows(NoSuchElementException.class, depq::getMax);
	}
	
	@Test void oneElement()
	{
		final Integer onlyElement = 0;
		depq.put(onlyElement, 1);
		assertSame(onlyElement, depq.getMin());
		assertSame(onlyElement, depq.getMax());
	}
}
