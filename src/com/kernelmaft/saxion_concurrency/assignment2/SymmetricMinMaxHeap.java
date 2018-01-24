package com.kernelmaft.saxion_concurrency.assignment2;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class SymmetricMinMaxHeap<E> implements DoubleEndedPrioQueue<E>
{
	private final ArrayList<PrioritisedElement<E>> array;
	
	public SymmetricMinMaxHeap()
	{
		array = new ArrayList<>();
		// Add empty element at index 0 for easier index arithmetic
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
		// TODO: move bottom-level checks from bubbleUp into this method
	}
	
	private void bubbleUp(int index, boolean bottomLevel)
	{
		// TODO: preserve insertion order for elems with same prio, maybe w/ a comb of bottomLevel checks and > vs >=
		
		final int leftSibling = index - 1;
		if (bottomLevel && !isLeftChild(index) && biggerThanRightSibling(leftSibling)) {
			swap(index, leftSibling);
			bubbleUp(leftSibling, false);
		}
		else {
			final int leftChildGrandparent = (index / 4) * 2;
			final int rightChildGrandparent = leftChildGrandparent + 1;
			// If the element has no grandparent it doesn't need to be checked, we're at the top of the tree
			if (leftChildGrandparent != 0) {
				if (array.get(index).priority < array.get(leftChildGrandparent).priority) {
					// P2 is violated
					
					swap(index, leftChildGrandparent);
					bubbleUp(leftChildGrandparent, false);
				}
				else if (array.get(index).priority > array.get(rightChildGrandparent).priority) {
					// P3 is violated
					
					swap(index, rightChildGrandparent);
					bubbleUp(rightChildGrandparent, false);
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
	
	/**
	 * Checks whether P1 is violated for an element with a certain index and its left sibling.
	 * @param index The index of the element to check.
	 * @return True when the P1 is satisfied, false when it doesn't.
	 */
	private boolean biggerThanRightSibling(int index)
	{
		return array.get(index).priority > array.get(index + 1).priority;
	}
	
	@Override public E removeMin()
	{
		final PrioritisedElement<E> removedElement = array.get(2);
		final PrioritisedElement<E> lastElement = array.remove(array.size() - 1);
		if (array.size() > 3) {
			array.set(2, lastElement);
			bubbleDown(2);
		}
		return removedElement.element;
	}
	
	@Override public E removeMax()
	{
		int elementToRemove = 3;
		if (array.size() < 4) {
			elementToRemove = 2;
		}
		final PrioritisedElement<E> removedElement = array.get(elementToRemove);
		final PrioritisedElement<E> lastElement = array.remove(array.size() - 1);
		if (array.size() > 3) {
			array.set(elementToRemove, lastElement);
			bubbleDown(elementToRemove);
		}
		return removedElement.element;
	}
	
	private void bubbleDown(int index)
	{
		final int elementPrio = array.get(index).priority;
		final int leftChild = index * 2;
		if (isLeftChild(index)) {
			final int rightSibling = index + 1;
			final boolean hasRightSibling = rightSibling < array.size();
			if (hasRightSibling && biggerThanRightSibling(index)) {
				swap(index, rightSibling);
				bubbleDown(rightSibling);
			}
			else if (leftChild < array.size()) {
				// Element has a left child
				
				final int leftChildPrio = array.get(leftChild).priority;
				final boolean leftChildSmallerThanElem = leftChildPrio < elementPrio;
				final int rightNephew = leftChild + 2;
				final boolean rightNephewExists = rightNephew < array.size();
				
				if (leftChildSmallerThanElem) {
					// Element is bigger than left child, violation of P2
					
					if (rightNephewExists && array.get(rightNephew).priority < leftChildPrio) {
						// Right nephew is even smaller than left child
						swap(index, rightNephew);
						bubbleDown(rightNephew);
					}
					else {
						// Left child is the smallest
						swap(index, leftChild);
						bubbleDown(leftChild);
					}
				}
				else if (rightNephewExists && array.get(rightNephew).priority < elementPrio) {
					// Right nephew is the smallest
					swap(index, rightNephew);
					bubbleDown(rightNephew);
				}
			}
		}
		else {
			final int leftSibling = index - 1;
			final int leftNephew = leftChild - 1;
			if (biggerThanRightSibling(leftSibling)) {
				swap(index, leftSibling);
				bubbleDown(leftSibling);
			}
			else if (leftNephew < array.size()) {
				// Element has a left nephew
				
				final int leftNephewPrio = array.get(leftNephew).priority;
				final boolean leftNephewBiggerThanElem = leftNephewPrio > elementPrio;
				final int rightChild = leftChild + 1;
				final boolean rightChildExists = rightChild < array.size();
				
				if (leftNephewBiggerThanElem) {
					// Element is smaller than right nephew, violation of P3
					
					if (rightChildExists && array.get(rightChild).priority > leftNephewPrio) {
						// Right child is even bigger than left nephew
						swap(index, rightChild);
						bubbleDown(rightChild);
					}
					else {
						// Left nephew is the smallest
						swap(index, leftNephew);
						bubbleDown(leftNephew);
					}
				}
				else if (rightChildExists && array.get(rightChild).priority > elementPrio) {
					// Right child is the biggest
					swap(index, rightChild);
					bubbleDown(rightChild);
				}
			}
		}
	}
	
	@Override public void updatePriority(E element, int newPriority)
	{
		throw new RuntimeException("Not implemented");
		// Use bubbleDown w/ L>R checks everywhere
	}
	
	private static boolean isLeftChild(int index)
	{
		return index % 2 == 0;
	}
	
	/**
	 * Gives the DOT-language tree representation of the internal heap. Can be used for visualisation and debugging.
	 * @return A string containing the representation.
	 */
	public String toDotTree(boolean includeElements)
	{
		final StringBuilder dotOutput = new StringBuilder();
		dotOutput.append("digraph { ");
		
		dotOutput.append("1 [label=\"1, root\"]; ");
		for (int i = 2; i < array.size(); i++) {
			final int elemPrio = array.get(i).priority;
			dotOutput.append(i).append(" [label=\"").append(i).append(", ").append(elemPrio);
			if (includeElements)
				// Include the toString representation of the elements in the nodes
				dotOutput.append(", ").append(array.get(i).element);
			dotOutput.append("\"]; ");
			
			final int parentIndex = i / 2;
			dotOutput.append(parentIndex).append("->").append(i).append("; ");
		}
		
		dotOutput.append("}");
		return dotOutput.toString();
	}
}
