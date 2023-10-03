/*
BST Generic Project
April 2023
Ella Chi

Class: BST<E>
creates binary search tree of type E (VocabEntry is used in this project; tree is used to create a deck of vocabulary words to study)
stores the size and root of the tree to access elements, and balances the tree (creating AVL tree) if wanted
*/
import java.util.*;

public class BST<E extends Comparable<E>> implements BSTInterface<E> {
    private Node<E> root;
    private int size;
    
    //creates an empty tree
   public BST(){
      root = null;
      size = 0;
   }
    
   //add elem to the bst
   public E add( E elem ){
       //increment size
       size++;
       
       if (root == null){
           //if tree is empty (root is null), create new node as root
           root = new Node(elem);
           return elem;
       } else {
           //otherwise, use helper method
          return add(root, elem).getValue();
       }
       
   }//returns the object
   
   //recursive helper method to add elem to bst
   public Node<E> add(Node<E> n, E elem){
       //check if elem is less than the value of the current node. if yes, add to the left side
        if(elem.compareTo(n.getValue()) < 0){
           if(n.getLeft() == null){
               //if left child is null, add elem as the left child
               n.setLeft(new Node<E>(elem));
               return n.getLeft();
           } else 
               //if left child is not null, call method for the left child
                add(n.getLeft(), elem);
       } else {
            //elem is greater than current value
           if(n.getRight() == null){
               //if right child is null, add elem as right child
                n.setRight(new Node<E>(elem));
                return n.getRight();
           }
           else{
               //child is not null --> call method for right child
               add(n.getRight(), elem);
           }
       }
       return n;
   }
   
    //returns if the bst contains an element
   public boolean contains( E obj)
   {
      return contains(root, obj);
   }
   
   //recursive helper to check if an element is in bst
   private boolean contains(Node<E> n, E x) //recursive helper method
   {
       if(n == null){
           return false;
       }
       E cur = n.getValue();
       //return true if current value is target
       if(cur.compareTo(x) == 0){
           return true;
       }
       //if target is greater than current, recur right 
        else if(x.compareTo(cur)> 0){
           return contains(n.getRight(), x);
       } else {
            //otherwise, recur left
           return contains(n.getLeft(), x);
       }

   }
   
   //returns if elem contains element associated with string (is a certain vocab word in the bst)
   public boolean contains(String s){
       return contains(root, s);
   }
   
   //recursive helper to check if string is in bst
   public boolean contains(Node<E> n, String s){
       if(n == null){
           //return false if node is null
           return false;
       }
       //return true if string is equal to node's element's string
       if(n.getValue().toString().equalsIgnoreCase(s)){
           return true;
       }
       
       //return true if either left or right contains s
       return contains(n.getRight(), s) || contains(n.getLeft(), s);

   }

   //returns if tree is empty/root is null
   public boolean isEmpty(){
       return (root == null);
   }
   
   //deletes element from bst
   public E delete(E target)
   {
       //if tree is empty, return
      if(root == null){
          return null;
      }
      //if tree does not contain target, return
      if(!contains(target)){
          return null;
      }
      
      //delete, rebalance, and decrement size
      root = delete(root, target);
      size--;
      balance(root);
      return target;
      
   }
   
   //recursive helper to remove element from bst
   private Node<E> delete(Node<E> current, E target)
   {
       //return if target not found
       if(current == null){
           return null;
       }
       
       //recur left/right until target is found
       if((current.getValue()).compareTo(target) < 0){
           current.setRight(delete(current.getRight(), target));
       } else if (current.getValue().compareTo(target) > 0){
           current.setLeft(delete(current.getLeft(), target));
       } else {
           
           //deletes node from tree
           //delete when node has one or no children
           if(current.getLeft() == null){
               return current.getRight();
           } else if (current.getRight() == null){
               return current.getLeft();
           } else {
               //delete when node has two children
               current.setValue((E)max(current.getLeft()));
               current.setLeft(delete(current.getLeft(), current.getValue()));
           }
       }
       return current;
   }
       
   //returns the size of the bst
   public int size(){
       return size;
   }
   
   //returns the minimum value of bst
   public E min(){
       return min(root);
   }
   
   //recursive helper to find and return minimum value
   public E min (Node<E> n){
       //check if node is null
       if(n == null){
           return null;
       } 
       
       //check if left child is null, if yes, return current node's value --> recur left until leftmost node
       if(n.getLeft() != null){
           //if no, call method for the left child
           return (E)min(n.getLeft());
       }
       return n.getValue();
   }

   //returns maximum value of bst
   public E max(){
        return max(root);
   }
   
   //recursive helper to return maximum value
   public E max(Node<E> n){
      //check if node is null
       if(n == null){
           return null;
       }
       
       //if left child is null, return current value; otherwise, call method for right child --> recur right until rightmost node
       if(n.getRight() != null){
           return (E)max(n.getRight());
       }
       return n.getValue();
   }
   
   //returns string to display bstt
   public String display(){
       return display(root, 0);
   }
   
   //recursive helper to return display string
   public String display(Node<E> n, int level){
      String str = "";
      if(n == null)
         return "";
      str += display(n.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         str += "\t";
      str += n.getValue() + "\n";
      str += display(n.getLeft(), level + 1); //recurse left
      return str;

   }
   
   //return inorder string of bst
   public String toString(){
       if(root == null){
           return "";
       }
       return toString(root);
       
   }
   
   //recursive helper return inorder string to represent bst
   public String toString(Node<E> n){
       if(n == null){
           return " ";
       }

       return toString(n.getLeft()) + n.getValue() + toString(n.getRight());
   }
   
   //returns node of a given element
   public Node<E> findNode(E elem){
       return findNode(root, elem);
   }
   
   //recursive helper to find the node of specific element
   public Node<E> findNode(Node<E> n, E elem){
       if(n == null){
           return null;
       }
       if(elem.compareTo(n.getValue()) < 0){
           //recur left is element is less than current value
            return findNode(n.getLeft(),elem);
        } else if (elem.compareTo(n.getValue())>0){
            //recur right if element is greater than current value
            return findNode(n.getRight(), elem);
        }
       return n;
   }
   
   //replace a value in the tree and return whether successful
   public boolean edit(E oldElem, E newElem){
       if(!contains(oldElem)){
           //return false if element is not in tree
           return false;
       }
       //find node of old element and set its value to new element
       Node<E> n = findNode(root, oldElem);
       n.setValue(newElem);
       
       return true;
   }
   
   //return inorder list of E to represent bst
   public List<E> toList(){
       ArrayList<E> list = new ArrayList<E>();
       toList(list, root);
       return list;
   }
   
   //recursive helper to return inorder list
   public void toList(List l, Node<E> n){
       if(n == null){
           return;
       }
       
       //add values to list in order
       toList(l, n.getLeft());
       l.add(n.getValue());
       toList(l, n.getRight());
   }
   
   //return whether tree is balanced
   public boolean isBalanced(){
       return isBalanced(root);
   }
   
   //recursive helper to return if tree is balanced
   public boolean isBalanced(Node<E> n){
       if(n == null){
           return true;
       }
       
       int leftH = 0, rightH = 0;
       if(!(n.getLeft() == null)){
           leftH = height(n.getLeft());
       }
       if(!(n.getRight() == null)){
          rightH = height(n.getRight());
       }
       
       //tree is balanced if left and right subtrees' height are within 1 from one another, and if both subtrees are balanced
       if(Math.abs(leftH-rightH) <= 1 && isBalanced(n.getLeft()) && isBalanced(n.getRight())){
           return true;
       }
       
       return false;
   }
   
   //return the height of a node
   public int height(Node<E> n){
       if(n == null){
           return 0;
       }
       
       //height is one plus the maximum height of its subtrees
       return 1 + Math.max(height(n.getLeft()), height(n.getRight()));
   }
      
   //return the balance of a tree --> height difference of subtrees
   public int getBalance(Node<E> n){
       if(n == null){
           return 0;
       }
       return height(n.getRight()) - height(n.getLeft());
   }
   
   //add element to tree and maintain balance
    public E addBalanced(E elem){
//       Node<E> n = add(root, elem);
////       if(!isBalanced()){
////           root = balance(root);
////       }
//       return balance(n).getValue();
        root = addBalanced(root, elem);
        size++;

        return elem;
   }
   
    //recursive helper method to add element and maintain balance
   public Node<E> addBalanced(Node<E> n, E elem){
       if(n == null){
           return new Node<E>(elem);
       
       //recur left/right until find the right spot to place element based on bst (performs like a normal add)
       } else if (n.getValue().compareTo(elem) > 0){
           n.setLeft(addBalanced(n.getLeft(), elem));
       } else{
           n.setRight(addBalanced(n.getRight(), elem));
       }
           
       //balance the tree
       return balance(n);
   }

   //balances the tree and returns balanced tree
   public Node<E> balance(Node<E> n){
       if(n == null){
           return null;
       } 
       
       int balance = getBalance(n);
       // if tree is left heavy
       if (balance < -1) {
           if (getBalance(n.getLeft()) <= 0) {  
               // Rotate right
               n = rotateRight(n);
           } else {                                
               // Rotate left-right
               n.setLeft(rotateLeft(n.getLeft()));
               n = rotateRight(n);
           }
       }

       // if tree is right heavy
       if (balance > 1) {
           if (getBalance(n.getRight()) >= 0) {    
               // Rotate left
               n = rotateLeft(n);
           } else {                                 
               // Rotate right-left
               n.setRight(rotateRight(n.getRight()));
               n = rotateLeft(n);
           }
       }
       return n;

   }
   
    //rotates the tree right (to offset left-heavy tree)
   public Node<E> rotateRight(Node<E> n){
       Node<E> left = n.getLeft();
       n.setLeft(left.getRight());
       left.setRight(n);
              
       return left;
       
   }
   
   //rotates the tree left (to offset right-heavy tree)
   public Node<E> rotateLeft(Node<E> n){
       Node<E> right = n.getRight();
       n.setRight(right.getLeft());
       right.setLeft(n);
              
       return right;
       
   }

}
