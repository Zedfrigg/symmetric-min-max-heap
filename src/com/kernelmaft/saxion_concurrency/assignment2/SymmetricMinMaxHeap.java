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
		try {
			return array.get(3).element;
		} catch (IndexOutOfBoundsException exception) {
			throw new NoSuchElementException("Cannot get the highest priority element because the interval heap is empty");
		}
	}
	
	@Override public void put(E newElement, int priority)
	{
		array.add(new PrioritisedElement<>(newElement, priority));
		bubbleUp(array.size() - 1, true);
	}
	
	private void bubbleUp(int index, boolean bottomLevel)
	{
		// TODO: edge cases
		
		if (index % 2 == 0) {
			// The element is a left child
			
			if (!checkPropertyTwo(index)) {
				//
			}
			else if (!checkPropertyThree(index)) {
				//
			}
			else {
				//
			}
		}
		else {
			// The element is a right child
			
			if (bottomLevel) {
				// We're at the bottom level of the tree, which means we have to check P1
				
				if (!checkPropertyOne(index)) {
					swap(index, index - 1);
					bubbleUp(index - 1, false);
				}
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
