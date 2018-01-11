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
		throw new RuntimeException("Not implemented");
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
		
		dotOutput.append("graph{");
		// TODO: add DOT generation
		dotOutput.append("}");
		
		return dotOutput.toString();
	}
}
