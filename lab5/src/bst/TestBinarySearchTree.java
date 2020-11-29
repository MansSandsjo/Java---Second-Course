package bst;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestBinarySearchTree {
	Comparator<Integer> comp;
	BinarySearchTree<Integer> tree;
	BinarySearchTree<Integer> treeWcomp;

	@Before
	public void setUp() {
		
		tree = new BinarySearchTree<Integer>();
		treeWcomp = new BinarySearchTree<Integer>(((e1, e2)->e1-e2));
	}

	@Test
	public void testHeight() {
		assertEquals("trädet ska vara tomt", 0, tree.height());
		assertEquals("trädet ska vara tomt", 0, treeWcomp.height());

		tree.add(5);
		tree.add(7);
		treeWcomp.add(5);
		treeWcomp.add(7);
		assertEquals("ej rätt höjd", 2, tree.height());
		assertEquals("ej rätt höjd", 2, treeWcomp.height());

	}
	@Test
	public void testAdd() {
		assertTrue(tree.add(3));
		assertTrue(treeWcomp.add(3));

		tree.add(1);
		treeWcomp.add(1);
		
		assertFalse("fel när samma tal läggs till", tree.add(1));
		assertEquals("fel när samma tal läggs till", 2,tree.size);
		assertFalse("fel när samma tal läggs till", treeWcomp.add(1));
		assertEquals("fel när samma tal läggs till", 2,treeWcomp.size);
	}
	@Test
	public void testSize() {
		assertEquals("trädet ska vara tomt", 0, tree.size);
		assertEquals("trädet ska vara tomt", 0, treeWcomp.size);
		
		tree.add(4);
		tree.add(6);
		tree.add(8);
		tree.add(12);
		treeWcomp.add(4);
		treeWcomp.add(6);
		treeWcomp.add(8);
		treeWcomp.add(12);
		
		assertEquals("fel stlk på trädet", 4, tree.size);
		assertEquals("fel stlk på trädet", 4, treeWcomp.size);

	}
	@Test
	public void testClear() {
		tree.add(2);
		tree.clear();
		
		treeWcomp.add(2);
		treeWcomp.clear();
		
		assertEquals("går inte att tömma trädet", tree.root, null);
		assertEquals("går inte att tömma trädet", treeWcomp.root, null);

	}

	@After
	public void tearDown() {
	}
}
