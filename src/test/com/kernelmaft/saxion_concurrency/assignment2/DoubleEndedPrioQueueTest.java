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
	private final DoubleEndedPrioQueue<Object> depq;
	
	DoubleEndedPrioQueueTest()
	{
		depq = new SymmetricMinMaxHeap<>();
	}
	
	/**
	 * Testing the behaviour when empty.
	 */
	@Test void noElements()
	{
		assertEquals(0, depq.size());
		assertTrue(depq.isEmpty());
		assertThrows(NoSuchElementException.class, depq::getMin);
		assertThrows(NoSuchElementException.class, depq::getMax);
		assertThrows(NoSuchElementException.class, depq::removeMin);
		assertThrows(NoSuchElementException.class, depq::removeMax);
	}
	
	/**
	 * Testing the behaviour when one element is present.
	 */
	@Test void oneElement()
	{
		final Object onlyElement = new Object();
		depq.put(onlyElement, 1);
		assertEquals(1, depq.size());
		assertFalse(depq.isEmpty());
		assertSame(onlyElement, depq.getMin());
		assertSame(onlyElement, depq.getMax());
	}
	
	/**
	 * Testing the behaviour when two elements are present.
	 */
	@Test void getTwoElements()
	{
		final Object smallerElement = new Object();
		final Object biggerElement = new Object();
		depq.put(smallerElement, 1);
		depq.put(biggerElement, 2);
		assertSame(smallerElement, depq.getMin());
		assertSame(biggerElement, depq.getMax());
	}
	
	/**
	 * Testing whether the DEPQ can handle new elements becoming the minimum and maximum priority elements.
	 */
	@Test void newMinAndMax()
	{
		final Object biggestElement = new Object();
		final Object smallestElement = new Object();
		depq.put(new Object(), 3);
		depq.put(new Object(), 2);
		depq.put(smallestElement, 1);
		depq.put(biggestElement, 4);
		assertSame(smallestElement, depq.getMin());
		assertSame(biggestElement, depq.getMax());
	}
	
	/**
	 * Testing whether the DEPQ can handle the two lowest priority elements being removed correctly.
	 */
	@Test void removeSmallest()
	{
		final Object smallestElement = new Object();
		final Object secondSmallestElement = new Object();
		depq.put(smallestElement, 1);
		depq.put(secondSmallestElement, 2);
		depq.put(new Object(), 3);
		depq.put(new Object(), 4);
		depq.put(new Object(), 5);
		assertSame(smallestElement, depq.removeMin());
		assertSame(secondSmallestElement, depq.removeMin());
	}
	
	/**
	 * Testing whether the DEPQ can handle the two highest priority elements being removed correctly.
	 */
	@Test void removeBiggest()
	{
		final Object biggestElement = new Object();
		final Object secondBiggestElement = new Object();
		depq.put(biggestElement, 5);
		depq.put(secondBiggestElement, 4);
		depq.put(new Object(), 1);
		depq.put(new Object(), 2);
		depq.put(new Object(), 3);
		assertSame(biggestElement, depq.removeMax());
		assertSame(secondBiggestElement, depq.removeMax());
	}
}
