/*
BST Generic Project
April 2023
Ella Chi

Class: Node<E>
creates Node objects to make up the BST
Nodes store generic data type (VocabEntry is used in this project) as its value and holds pointers to two children in order to make the tree
*/
public class Node<E> {
    private E value;
    private Node<E> left;
    private Node<E> right;
    
    public Node(E val){
        value = val;
    }
    
    public Node(E val, Node<E> l, Node<E> r){
        value = val;
        left = l;
        right = r;
    }
    
    
    //returns value of node
    public E getValue()
    { 
        return value; 
    }
   
    //returns left child of node
    public Node getLeft() 
    { 
        return left; 
    }
   
    //returns right child of node
    public Node getRight() 
    { 
       return right; 
    }
   
    //set value of node
    public void setValue(E v) 
    { 
       value = v; 
    }
   
    //set left child of node
     public void setLeft(Node<E> newLeft) 
    { 
       left = newLeft;
    }
   
     //set right child of node
    public void setRight(Node<E> newRight)
    { 
       right = newRight;
    }

    //return string to represent node based on value of node
    public String toString(){
        return value.toString();
    }
}
