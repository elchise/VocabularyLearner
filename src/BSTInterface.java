/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ellachi
 */
import java.util.*;

public interface BSTInterface<E> {
   public E add( E element );     //returns the object
   public E addBalanced(E element);
   public boolean contains( E element ); // breadth first or depth first search
   public boolean isEmpty();
   public E delete( E element );  //returns the object, not a Node<E>.  
   public int size();
   public E min();
   public E max();
   public String display();
   public String toString();
   public boolean edit(E oldElement, E newElement);
   public List<E> toList();//returns an in-order list of E
   public boolean isBalanced();

}
