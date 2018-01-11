package com.kernelmaft.saxion_concurrency.assignment2;

import java.util.ArrayList;

public class IntervalHeap<E> implements DoubleEndedPrioQueue<E>
{
	private final ArrayList<PrioritisedElement<E>> array;
	
	public IntervalHeap()
	{
		array = new ArrayList<>();
		// Add empty root element
		array.add(null);
	}
	
	@Override public boolean isEmpty()
	{
		return size() == 0;
	}
	
	@Override public int size()
	{
		return array.size() - 1;
	}
	
	@Override public E getMin()
	{
		// TODO: edge case handling
		return array.get(1).element;
	}
	
	@Override public E getMax()
	{
		// TODO: edge case handling
		return array.get(2).element;
	}
	
	@Override public void put(E newElement, int priority)
	{
	
	}
	
	@Override public E removeMin()
	{
	
	}
	
	@Override public E removeMax()
	{
	
	}
	
	@Override public void updatePriority(E element, int newPriority)
	{
	
	}
}
