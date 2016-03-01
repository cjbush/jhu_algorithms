/** Original code was provided by "Introduction to Java Programming" 
 * 8th Edition by Y. Daniel Liang, pages 858 - 869
 *
 *  This code is used for educational purposes only
 *  in 605.421 Foundations of Algorithms. 
 * 
 */

package cjbush.jhu.algorithms.pa1;

public class BinaryTree<E extends Comparable<E>> extends AbstractTree<E> implements Cloneable {
  protected TreeNode<E> root;
  protected int size = 0;

  /** Create a default binary tree */
  public BinaryTree() {
  }

  /** Create a binary tree from an array of objects */
  public BinaryTree(E[] objects) {
    for (int i = 0; i < objects.length; i++)
      insert(objects[i]);
  }
  
  
  
  
  
  
  
  
  
  /* BEGIN MY CODE */
  
  /**
   * Prints the height of the tree. I wasn't sure whether to follow the
   * signature from the assignment or return an int, so this just calls
   * getHeight below and prints it to the console. Hopefully that satisfies
   * both requirements.
   */
  public void height(){
	  System.out.println("Height of the tree is: " + this.getHeight());
  }
  
  /**
   * Returns the height of the binary tree as an integer
   * @return the height from the tree's root node
   */
  public int getHeight() {
      return getHeight(root);
  }
  
  /**
   * Recursive function to get the height of a subtree beneath any given node.
   * If you've reached a leaf, return -1, otherwise return the max between
   * the height of your left subtree and the height of your right subtree, and
   * add 1 for yourself.
   * @param subroot The "root" node of the subtree to check
   * @return -1 if subroot is a leaf, otherwise the max height between the left subtree
   * 		 and the right subtree + 1 for the root node's level
   */
  protected int getHeight(TreeNode<E> subroot){     
      return subroot == null ? -1 : Integer.max(getHeight(subroot.left), getHeight(subroot.right)) + 1;
  }
  
  /**
   * Finds the total number of leaves in the tree by recursively calling
   * getNumberOfLeaves starting with "root".
   * 
   * @return the number of leaf nodes below the overall root
   */
  public int getNumberOfLeaves(){
      return getNumberOfLeaves(root);
  }
  
  /**
   * Recursively finds leaves in the tree below any given node subroot
   * 
   * @param subroot The root node of the subtree 
   * @return 0 if the subroot is null, 1 if the subroot is a leaf,
   * 		or the sum of leaves on the left subtree and right subtree
   */
  protected int getNumberOfLeaves(TreeNode<E> subroot){
      if(subroot == null) return 0;
      if(subroot.left == null && subroot.right == null) return 1;
      return getNumberOfLeaves(subroot.left) + getNumberOfLeaves(subroot.right);
  }
  
  /**
   * Returns the nonleaves by subtracting the number of leaves from the total
   * number of nodes.
   * 
   * @return The number of non-leaf nodes
   */
  public int getNumberOfNonLeaves(){
      return getSize() - getNumberOfLeaves();
  }
  
  //Note that there's already a postorder implementation in the provided code below.
  //I just decided to make a slightly more interesting one, because I haven't gotten
  //to play with Java lambda expressions yet. Ignore this if you want.
  //(Conclusion: C# still does them better)
  
  /**
   * Defines the "visitor" lambda. i.e. what do we do when we call "visit" on the node
   * 
   */
  @FunctionalInterface
  public interface NodeVisitLambda<E extends Comparable<E>>{
	  public void Visit(TreeNode<E> node);
  }
  
  /**
   * postorder traversal that accepts a lambda expression
   * @param visitLambda Lambda expression for visiting the node
   */
  public void postorder(NodeVisitLambda<E> lambda){
	  postorder(root, lambda);
  }
  
  public void inorder(NodeVisitLambda<E> lambda){
	  inorder(root, lambda);
  }
  
  public void preorder(NodeVisitLambda<E> lambda){
	  preorder(root, lambda);
  }
  
  /**
   * Recursive postorder call that traverses the left subtree, then the right subtree,
   * then visits the subroot node using the given lambda
   * 
   * @param subroot The root of the subtree 
   * @param visitLambda The lambda to execute when the node is visited
   */
  protected void postorder(TreeNode<E> subroot, NodeVisitLambda<E> lambda){
	  if(subroot == null) return;
	  postorder(subroot.left, lambda);
	  postorder(subroot.right, lambda);
	  lambda.Visit(subroot);
  }
  
  protected void inorder(TreeNode<E> subroot, NodeVisitLambda<E> lambda){
	  if(subroot == null) return;
	  inorder(subroot.left, lambda);
	  lambda.Visit(subroot);
	  inorder(subroot.right, lambda);
  }
  
  protected void preorder(TreeNode<E> subroot, NodeVisitLambda<E> lambda){
	  if(subroot == null) return;
	  lambda.Visit(subroot);
	  preorder(subroot.left, lambda);
	  preorder(subroot.right, lambda);
  }
  
  /* END MY CODE */
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  

  /** Returns true if the element is in the tree */
  public boolean search(E e) {
    TreeNode<E> current = root; // Start from the root

    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0) {
        current = current.right;
      }
      else // element matches current.element
        return true; // Element is found
    }

    return false;
  }

  /** Insert element o into the binary tree
   * Return true if the element is inserted successfully */
  public boolean insert(E e) {
    if (root == null)
      root = createNewNode(e); // Create a new root
    else {
      // Locate the parent node
      TreeNode<E> parent = null;
      TreeNode<E> current = root;
      while (current != null)
        if (e.compareTo(current.element) < 0) {
          parent = current;
          current = current.left;
        }
        else if (e.compareTo(current.element) > 0) {
          parent = current;
          current = current.right;
        }
        else
          return false; // Duplicate node not inserted

      // Create the new node and attach it to the parent node
      if (e.compareTo(parent.element) < 0)
        parent.left = createNewNode(e);
      else
        parent.right = createNewNode(e);
    }

    size++;
    return true; // Element inserted
  }

  protected TreeNode<E> createNewNode(E e) {
    return new TreeNode<E>(e);
  }

  /** Inorder traversal from the root*/
  public void inorder() {
    inorder(root);
  }

  /** Inorder traversal from a subtree */
  protected void inorder(TreeNode<E> root) {
    if (root == null) return;
    inorder(root.left);
    System.out.print(root.element + " ");
    inorder(root.right);
  }

  /** Postorder traversal from the root */
  public void postorder() {
    postorder(root);
  }

  /** Postorder traversal from a subtree */
  protected void postorder(TreeNode<E> root) {
    if (root == null) return;
    postorder(root.left);
    postorder(root.right);
    System.out.print(root.element + " ");
  }

  /** Preorder traversal from the root */
  public void preorder() {
    preorder(root);
  }

  /** Preorder traversal from a subtree */
  protected void preorder(TreeNode<E> root) {
    if (root == null) return;
    System.out.print(root.element + " ");
    preorder(root.left);
    preorder(root.right);
  }

  /** Inner class tree node */
  public static class TreeNode<E extends Comparable<E>> {
    E element;
    TreeNode<E> left;
    TreeNode<E> right;

    public TreeNode(E e) {
      element = e;
    }
  }

  /** Get the number of nodes in the tree */
  public int getSize() {
    return size;
  }

  /** Returns the root of the tree */
  public TreeNode<E> getRoot() {
    return root;
  }

  /** Returns a path from the root leading to the specified element */
  public java.util.ArrayList<TreeNode<E>> path(E e) {
    java.util.ArrayList<TreeNode<E>> list =
      new java.util.ArrayList<TreeNode<E>>();
    TreeNode<E> current = root; // Start from the root

    while (current != null) {
      list.add(current); // Add the node to the list
      if (e.compareTo(current.element) < 0) {
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0) {
        current = current.right;
      }
      else
        break;
    }

    return list; // Return an array of nodes
  }

  /** Delete an element from the binary tree.
   * Return true if the element is deleted successfully
   * Return false if the element is not in the tree */
  public boolean delete(E e) {
    // Locate the node to be deleted and also locate its parent node
    TreeNode<E> parent = null;
    TreeNode<E> current = root;
    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        parent = current;
        current = current.left;
      }
      else if (e.compareTo(current.element) > 0) {
        parent = current;
        current = current.right;
      }
      else
        break; // Element is in the tree pointed by current
    }

    if (current == null)
      return false; // Element is not in the tree

    // Case 1: current has no left children
    if (current.left == null) {
      // Connect the parent with the right child of the current node
      if (parent == null) {
        root = current.right;
      }
      else {
        if (e.compareTo(parent.element) < 0)
          parent.left = current.right;
        else
          parent.right = current.right;
      }
    }
    else {
      // Case 2: The current node has a left child
      // Locate the rightmost node in the left subtree of
      // the current node and also its parent
      TreeNode<E> parentOfRightMost = current;
      TreeNode<E> rightMost = current.left;

      while (rightMost.right != null) {
        parentOfRightMost = rightMost;
        rightMost = rightMost.right; // Keep going to the right
      }

      // Replace the element in current by the element in rightMost
      current.element = rightMost.element;

      // Eliminate rightmost node
      if (parentOfRightMost.right == rightMost)
        parentOfRightMost.right = rightMost.left;
      else
        // Special case: parentOfRightMost == current
        parentOfRightMost.left = rightMost.left;     
    }

    size--;
    return true; // Element inserted
  }

  /** Obtain an iterator. Use inorder. */
  public java.util.Iterator iterator() {
    return inorderIterator();
  }

  /** Obtain an inorder iterator */
  public java.util.Iterator inorderIterator() {
    return new InorderIterator();
  }

  // Inner class InorderIterator
  class InorderIterator implements java.util.Iterator {
    // Store the elements in a list
    private java.util.ArrayList<E> list =
      new java.util.ArrayList<E>();
    private int current = 0; // Point to the current element in list

    public InorderIterator() {
      inorder(); // Traverse binary tree and store elements in list
    }

    /** Inorder traversal from the root*/
    private void inorder() {
      inorder(root);
    }

    /** Inorder traversal from a subtree */
    private void inorder(TreeNode<E> root) {
      if (root == null)return;
      inorder(root.left);
      list.add(root.element);
      inorder(root.right);
    }

    /** Next element for traversing? */
    public boolean hasNext() {
      if (current < list.size())
        return true;

      return false;
    }

    /** Get the current element and move cursor to the next */
    public Object next() {
      return list.get(current++);
    }

    /** Remove the current element and refresh the list */
    public void remove() {
      delete(list.get(current)); // Delete the current element
      list.clear(); // Clear the list
      inorder(); // Rebuild the list
    }
  }

  /** Remove all elements from the tree */
  public void clear() {
    root = null;
    size = 0;
  }
  
  public Object clone() {
    BinaryTree<E> tree1 = new BinaryTree<E>();
    
    copy(root, tree1);
    
    return tree1;
  }
  
  private void copy(TreeNode<E> root, BinaryTree<E> tree) {
    if (root != null) {
      tree.insert(root.element);
      copy(root.left, tree);
      copy(root.right, tree);
    }
  }
}