package com.kernelmaft.saxion_concurrency.assignment2;

/**
 * A priority queue where elements with both the highest and lowest priority are available.
 */
public interface DoubleEndedPrioQueue<E>
{
	/**
	 * Whether the priority queue currently has no elements in it.
	 * @return A boolean indicating whether the priority queue is empty.
	 */
	public boolean isEmpty();
	
	/**
	 * Get the number of elements currently in the priority queue.
	 * @return The number of elements.
	 */
	public int size();
	
	/**
	 * Get the element in the priority queue with the lowest priority. The element will not be removed from the queue.
	 * @return The aforementioned element.
	 */
	public E getMin();
	
	/**
	 * Get the element in the priority queue with the highest priority. The element will not be removed from the queue.
	 * @return The aforementioned element.
	 */
	public E getMax();
	
	/**
	 * Add a new element with a specified priority to the priority queue. Adding the same element more than one time
	 * will result in undefined behaviour.
	 * @param newElement The new element to add.
	 * @param priority   The priority of the element.
	 */
	public void put(E newElement, int priority);
	
	/**
	 * Get and remove the element in the priority queue with the lowest priority.
	 * @return The element that was removed.
	 */
	public E removeMin();
	
	/**
	 * Get and remove the element in the priority queue with the highest priority.
	 * @return The element that was removed.
	 */
	public E removeMax();
	
	/**
	 * Update the priority of a given element in the priority queue.
	 * @param element     The element to change the priority of.
	 * @param newPriority The new priority the element should have.
	 */
	public void updatePriority(E element, int newPriority);
}
