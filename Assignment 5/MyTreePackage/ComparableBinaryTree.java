package MyTreePackage;

public class ComparableBinaryTree<T extends Comparable<? super T>>
extends BinaryTree<T> implements ComparableTreeInterface<T> {
	public ComparableBinaryTree() {
		super();
	}
	
	public ComparableBinaryTree(T root) {
		super();
	}
	
	public ComparableBinaryTree(T root, T leftChild, T rightChild) {
		super();
	}
	
	// If the tree is not empty, return the maximum
	// value in the tree; otherwise return null
	public T getMax() {
		if (getRootData() == null)
			return null;
		else 
			return getMax(this.getRootNode());
	}
	
	private T getMax(BinaryNode<T> node) {
		T maximum = node.getData();
		if (node.hasLeftChild()) {
			BinaryNode<T> left = node.getLeftChild();
			T LChild = getMax(left);
			if (maximum.compareTo(LChild) < 0)
				maximum = LChild;
		}
		
		if (node.hasRightChild()) {
			BinaryNode<T> right = node.getRightChild();
			T RChild = getMax(right);
			if (maximum.compareTo(RChild) < 0)
				maximum = RChild;
		}
			
		return maximum;
	}
	

	// If the tree is not empty, return the minimum
	// value in the tree; otherwise return null
	public T getMin() {
		if (getRootData() == null)
			return null;
		else 
			return getMin(this.getRootNode());
	}
	
	public T getMin(BinaryNode<T> node) {
		T minimum = node.getData();
		if (node.hasLeftChild()) {
			BinaryNode<T> left = node.getLeftChild();
			T LChild = getMin(left);
			if (minimum.compareTo(LChild) > 0)
				minimum = LChild;
		}
		
		if (node.hasRightChild()) {
			BinaryNode<T> right = node.getRightChild();
			T RChild = getMin(right);
			if (minimum.compareTo(RChild) > 0)
				minimum = RChild;
		}
			
		return minimum;
	}
	
	//Return true if the the tree meets the
	//recursive definition of a BST; else
	// return false 
	public boolean isBST() {
		BinaryNode<T> node = this.getRootNode();
		//Get the max of the left subtree
		T leftMax = getMax(node);
		T rightMin = getMin(node);
		
		if ( (leftMax.compareTo(node.getData()) > 0) || (rightMin.compareTo(node.getData()) < 0) )
			return false;
		else
			return true;
	}
		
}
