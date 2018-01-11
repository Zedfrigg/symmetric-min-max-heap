package com.kernelmaft.saxion_concurrency.assignment2;

public class PrioritisedElement<E>
{
	public PrioritisedElement(E element, int priority)
	{
		this.element = element;
		this.priority = priority;
	}
	
	public final E element;
	public int priority;
}
