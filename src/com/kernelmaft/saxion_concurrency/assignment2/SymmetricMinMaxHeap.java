package com.kernelmaft.saxion_concurrency.assignment2;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class SymmetricMinMaxHeap<E> implements DoubleEndedPrioQueue<E>
{
	private final ArrayList<PrioritisedElement<E>> array;
	
	public SymmetricMinMaxHeap()
	{
		array = new ArrayList<>();
		// Add empty element at index 0 for easier index calculations
		array.add(null);
		// Add empty root element
		array.add(null);
	}
	
	@Override public boolean isEmpty()
	{
		return size() == 0;
	}
	
	@Override public int size()
	{
		return array.size() - 2;
	}
	
	@Override public E getMin()
	{
		try {
			return array.get(2).element;
		} catch (IndexOutOfBoundsException exception) {
			throw new NoSuchElementException("Cannot get the lowest priority element because the interval heap is empty");
		}
	}
	
	@Override public E getMax()
	{
		if (size() < 2) {
			try {
				return array.get(2).element;
			} catch (IndexOutOfBoundsException exception) {
				throw new NoSuchElementException("Cannot get the highest priority element because the interval heap is empty");
			}
		}
		return array.get(3).element;
	}
	
	@Override public void put(E newElement, int priority)
	{
		array.add(new PrioritisedElement<>(newElement, priority));
		// If this is the first element the heap is already valid
		if (size() > 1)
			bubbleUp(array.size() - 1, true);
	}
	
	private void bubbleUp(int index, boolean bottomLevel)
	{
		// TODO: edge cases
		
		if (bottomLevel && !checkPropertyOne(index)) {
			swap(index, index - 1);
			bubbleUp(index - 1, false);
		}
		else {
			final int indexLeftChildGrandparent = (index / 4) * 2;
			if (array.get(indexLeftChildGrandparent).priority <= array.get(index).priority) {
				// P2 is violated
				
				swap(index, indexLeftChildGrandparent);
				bubbleUp(indexLeftChildGrandparent, false);
			}
			else if (array.get(indexLeftChildGrandparent + 1).priority >= array.get(index).priority) {
				// P3 is violated
				
				swap(index, indexLeftChildGrandparent + 1);
				bubbleUp(indexLeftChildGrandparent + 1, false);
			}
		}
	}
	
	/**
	 * Swaps two elements in the heap.
	 * @param indexFirst The index of the first element.
	 * @param indexSecond The index of the second element.
	 */
	private void swap(int indexFirst, int indexSecond)
	{
		final PrioritisedElement<E> temporary = array.get(indexFirst);
		array.set(indexFirst, array.get(indexSecond));
		array.set(indexSecond, temporary);
	}
	
	private boolean checkPropertyOne(int index)
	{
		return array.get(index).priority >= array.get(index - 1).priority;
	}
	
	private boolean checkPropertyTwo(int index)
	{
		final int indexLeftChildGrandparent = (index / 4) * 2;
		return array.get(indexLeftChildGrandparent).priority <= array.get(index).priority;
	}
	
	private boolean checkPropertyThree(int index)
	{
		final int indexRightChildGrandparent = (index / 4) * 2 + 1;
		return array.get(indexRightChildGrandparent).priority >= array.get(index).priority;
	}
	
	@Override public E removeMin()
	{
		throw new RuntimeException("Not implemented");
	}
	
	@Override public E removeMax()
	{
		throw new RuntimeException("Not implemented");
	}
	
	@Override public void updatePriority(E element, int newPriority)
	{
		throw new RuntimeException("Not implemented");
	}
	
	/**
	 * Gives the DOT-language tree representation of the internal heap. Can be used for visualisation and debugging.
	 * @return A string containing the representation.
	 */
	public String toDotTree()
	{
		final StringBuilder dotOutput = new StringBuilder();
		
		dotOutput.append("graph { ");
		// TODO: add DOT generation
		dotOutput.append("}");
		
		return dotOutput.toString();
	}
}
