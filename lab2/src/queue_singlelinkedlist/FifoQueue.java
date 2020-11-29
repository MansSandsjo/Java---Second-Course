package queue_singlelinkedlist;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**
	 * Inserts the specified element into this queue, if possible post: The
	 * specified element is added to the rear of this queue
	 * 
	 * @param e
	 *            the element to insert
	 * @return true if it was possible to add the element to this queue, else
	 *         false
	 */
	public boolean offer(E e) {
		QueueNode<E> n = new QueueNode<E>(e);
		size++;
		if (last == null) {
			n.next = n;
			last = n;
			return true;
		} else
			n.next = last.next;
		last.next = n;
		last = n;
		return true;
	}

	/**
	 * Returns the number of elements in this queue
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue, returning null if
	 * this queue is empty
	 * 
	 * @return the head element of this queue, or null if this queue is empty
	 */
	public E peek() {
		if (last == null) {
			return null;
		}
		return last.next.element;
	}

	/**
	 * Retrieves and removes the head of this queue, or null if this queue is
	 * empty. post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	public E poll() {
		if (size > 1) {
			QueueNode<E> temp = last.next;
			size--;
			last.next = last.next.next;
			return temp.element;
		} else if (size == 1) {
			QueueNode<E> temp = last;
			size--;
			last = null;
			return temp.element;

		} else
			return null;
	}

	/**
	 * Returns an iterator over the elements in this queue
	 * 
	 * @return an iterator over the elements in this queue
	 */
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;
		int elementleft;

		/* Konstruktor */
		private QueueIterator() {
			elementleft = size;
			if (size != 0)
				pos = last.next;
		}

		public boolean hasNext() {
			if (elementleft > 0) {
				return true;
			} else
				return false;
		}

		public E next() {
			if (hasNext()) {
				QueueNode<E> temp = pos;
				pos = pos.next;
				elementleft--;
				return temp.element;
			} else if (!hasNext()) {
				throw new NoSuchElementException();
			} else
				return null;
		}

	}

	/**
	 * Appends the specified queue to this queue post: all elements from the
	 * specified queue are appended to this queue. The specified queue (q) is
	 * empty after the call.
	 * 
	 * @param q
	 *            the queue to append
	 * @throws IllegalArgumentException
	 *             if this queue and q are identical
	 */
	public void append(FifoQueue<E> q2) {
		if (this == q2) { //gick ej med bara (this != q2) vrf?
			throw new IllegalArgumentException();
		}
		 else if (this.size == 0 && q2.size!=0) {
			last = q2.last;
			last.next = q2.last.next;
		} else if (q2.size == 0 && this.size!=0) {
			// do nothing
		
		} else if (q2.size != 0) {
			last.next = q2.last.next;
		} else if (this.size != 0 && q2.size != 0) {
			last = q2.last.next;
			q2.last = last.next;
	}
	}
	
	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

}
