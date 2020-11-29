package bst;

import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearchTree<E> {
	BinaryNode<E> root; // Används också i BSTVisaulizer
	int size; // Används också i BSTVisaulizer
	private Comparator<E> comparator;

	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {

	}

	/**
	 * Constructs an empty binary search tree, sorted according to the specified
	 * comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x
	 *            element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		// tomt träd
		if (root == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		} else {
			// BinaryNode<E> node = new BinaryNode<E>(x);
			return add(root, x);
		}
	}

	private boolean add(BinaryNode<E> node, E x) {
		int compare;
		if (comparator == null) {
			compare = ((Comparable<E>) x).compareTo(node.element);
		} else
			compare = comparator.compare(node.element, x);

		// elementet finns redan
		if (compare == 0) {
			return false;
		}
		// gå till vänster o fortsätt kolla
		else if (compare < 0) {
			if (node.left == null) {
				node.left = new BinaryNode<E>(x);
				size++;
				return true;
			}
			return add(node.left, x);
		} else if (compare > 0) {
			if (node.right == null) {
				node.right = new BinaryNode<E>(x);
				size++;
				return true;
			} else
				return add(node.right, x);
		} else
			return false;
	}

	public BinaryNode<E> temp;

	public E find(E x) {
		temp = new BinaryNode<E>(null);
		if (root == null) {
			return null;
		} else
			find(root, x);
		return temp.element;
	}

	private void find(BinaryNode<E> node, E x) {
		if (node != null) {
			if (node.element.equals(x)) {
				temp = new BinaryNode<E>(node.element);
				return;
			}
			find(node.left, x);
			find(node.right, x);
		}
	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}

	private int height(BinaryNode<E> node) {
		if (node == null)
			return 0;
		else
			return 1 + Math.max(height(node.left), height(node.right));
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		size = 0;
		root = null;
	}

	/**
	 * Print tree contents in inorder.left root right
	 */
	public void printTree() {
		printTree(root);
	}

	private void printTree(BinaryNode<E> node) {
		// basfall size = 0 el node=null printa inget
		if (node == null) {
			return;
		} else {
			printTree(node.left);
			System.out.print(node.element);
			printTree(node.right);
		}

		//
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> sorted = new ArrayList<E>();
		toArray(root, sorted);
		// System.out.println(sorted);
		root = buildTree(sorted, 0, sorted.size() - 1);
		// for (E e : sorted)
		// System.out.println(e);
	}

	/*
	 * Adds all elements from the tree rooted at n in inorder (left root right)
	 * to the list sorted.
	 */
	private void toArray(BinaryNode<E> node, ArrayList<E> sorted) {
		if (node == null) {
			return;
		}
		toArray(node.left, sorted);
		sorted.add(node.element);
		toArray(node.right, sorted);
	}

	public void printBiggerThan(E x) {
		if (x == null || root == null) {
			return;
		} else
			printBiggerThan(root, x);
	}

	private void printBiggerThan(BinaryNode<E> node, E x) {
		if (node == null)
			return;

		else if (((Comparable<E>) x).compareTo(node.element) >= 0) {
			printBiggerThan(node.right, x);
		} 
		
		else if (((Comparable<E>) x).compareTo(node.element) < 0) {
			printBiggerThan(node.left, x);
			System.out.println(node.element);
			printBiggerThan(node.right, x);

		}

	}

	/*
	 * Builds a complete tree from the elements from position first to last in
	 * the list sorted. Elements in the list a are assumed to be in ascending
	 * order. Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if (first > last)
			return null;

		int mid = (first + last) / 2;
		BinaryNode<E> node = new BinaryNode<E>(sorted.get(mid));
		node.left = buildTree(sorted, first, mid - 1);
		node.right = buildTree(sorted, mid + 1, last);

		return node;
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}

	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		BinarySearchTree<Integer> tree1 = new BinarySearchTree<Integer>();
		BSTVisualizer bVis = new BSTVisualizer("blandat träd", 800, 800);

		tree1.add(1);
		tree1.add(3);
		tree1.add(5);
		tree1.add(7);
		tree1.add(9);
		tree1.add(11);
		tree1.add(13);
		tree1.rebuild();
		bVis.drawTree(tree1);

		tree1.printBiggerThan(5);
	}
}
