/*
	Notice to instructors and DSA team:

	We are unable to perform tests if methods only print to screen. Particular methods must be modified to return data.
	We have included more detailed comments below.
	In particular, we need a way to have the tree returned in the inorder, preorder, and postorder methods (not just printed to screen).
 */

package tester;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tree.AVLTree;
import tree.BinarySearchTree;
import tree.TreeNode;

import static org.junit.jupiter.api.Assertions.*;

public class TreeTest {
	public BinarySearchTree<Integer> bst;
	public AVLTree<Integer> avl;

	@BeforeEach
	public void setup() {
		 bst = new BinarySearchTree<Integer>();
		 avl = new AVLTree<Integer>();
	}

	/*
		BinarySearchTree
		insert()
	 */

	@Test
	public void BSTInsertNotInTree() {
		bst.insert(5);
		bst.insert(2);
		// base state of tree established, root node 5 with left child 2

		bst.insert(6);
		assertEquals(6, bst.root.right.data);
		// we are aware that access to root is protected in the BinaryTree class; such access will be required for effective testing
	}

	@Test
	public void BSTInsertAlreadyInTree() {
		bst.insert(5);
		bst.insert(2);
		bst.insert(6);
		// base state of tree established, root node 5 with left child 2 and right child 6

		String base = bst.inOrder();
		// requires ability to examine binary search tree without the method outputting to System.out.println()

		bst.insert(6);
		assertEquals(base, bst.inOrder());
		// requires ability to examine binary search tree without the method outputting to System.out.println()
	}

	/*
		BinarySearchTree
		find()
	 */

	@Test
	public void BSTFindInTree() {
		bst.insert(5);
		bst.insert(2);
		// base state of tree established, root node 5 with left child 2

		assertTrue(bst.find(5));
	}

	@Test
	public void BSTFindNotInTree() {
		bst.insert(5);
		bst.insert(2);
		// base state of tree established, root node 5 with left child 2

		assertFalse(bst.find(6));
	}

	/*
		BinarySearchTree
		remove()
	 */

	@Test
	// we are aware that access to root is protected in the BinaryTree class; such access will be required for effective testing
	public void BSTRemoveInTree() {
		bst.insert(5);
		bst.insert(2);
		assertEquals(2, bst.root.left.data);
		// base state of tree established, root node 5 with left child 2

		bst.remove(2);
		assertNull(bst.root.left);
	}

	@Test
	public void BSTRemoveNotInTree() {
		bst.insert(5);
		bst.insert(2);
		// base state of tree established, root node 5 with left child 2


		String base = bst.inOrder();
		// requires ability to examine binary search tree without the method outputting to System.out.println()

		bst.remove(3);
		assertEquals(base, bst.inOrder());
		// requires ability to examine binary search tree without the method outputting to System.out.println()
	}

	/*
		AVLTree
		balance()
	 */

	@Test
	// we are aware that access to root is protected in the BinaryTree class; such access will be required for effective testing
	public void AVLBalanceNoRotation() {
		avl.root = new TreeNode<Integer>(5);
		avl.root.left = new TreeNode<Integer>(2);
		avl.root.right = new TreeNode<Integer>(6);
		// base state of tree established

		assertEquals("2 5 6", avl.balance(avl.root).inOrder());
	}

	@Test
	// we are aware that access to root is protected in the BinaryTree class; such access will be required for effective testing
	public void AVLBalanceSingleLeft() {
		avl.root = new TreeNode<Integer>(1);
		avl.root.right = new TreeNode<Integer>(2);
		// base state of tree established

		avl.root.right.right = new TreeNode<Integer>(3);

		assertEquals("1 2 3", avl.balance(avl.root).inOrder());
	}

	@Test
	// we are aware that access to root is protected in the BinaryTree class; such access will be required for effective testing
	public void AVLBalanceSingleRight() {
		avl.root = new TreeNode<Integer>(3);
		avl.root.left = new TreeNode<Integer>(2);
		// base state of tree established

		avl.root.left.left = new TreeNode<Integer>(1);

		assertEquals("1 2 3", avl.balance(avl.root).inOrder());
	}

	@Test
	// we are aware that access to root is protected in the BinaryTree class; such access will be required for effective testing
	public void AVLBalanceLeftRight() {
		avl.root = new TreeNode<Integer>(15);

		avl.root.right = new TreeNode<Integer>(17);
		avl.root.right.left = new TreeNode<Integer>(16);

		avl.root.left = new TreeNode<Integer>(8);
		avl.root.left.right = new TreeNode<Integer>(10);
		avl.root.left.left = new TreeNode<Integer>(4);
		avl.root.left.left.right = new TreeNode<Integer>(6);
		avl.root.left.left.left = new TreeNode<Integer>(3);
		// base state of tree established (see testing doc)

		avl.root.left.left.right.left = new TreeNode<Integer>(5);

		assertEquals("3 4 5 6 8 10 15 16 17", avl.balance(avl.root).inOrder());
	}

	@Test
	// we are aware that access to root is protected in the BinaryTree class; such access will be required for effective testing
	public void AVLBalanceRightLeft() {
		avl.root = new TreeNode<Integer>(5);
		avl.root.left = new TreeNode<Integer>(3);
		// base state of tree established (see testing doc)

		avl.root.left.right = new TreeNode<Integer>(4);

		assertEquals("3 4 5", avl.balance(avl.root).inOrder());
	}

	/*
		AVLTree
		rotateLeft()
	 */

	@Test
	public void rotateLeftAfterInsert() {
		avl.insert(5);
		avl.insert(4);
		avl.insert(6);
		avl.insert(7);
		// base state of tree established (see testing doc)

		avl.insert(8);

		assertEquals("4 5 6 7 8", avl.inOrder())
	}

}
