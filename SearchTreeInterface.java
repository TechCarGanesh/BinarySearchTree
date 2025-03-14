/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.binarysearchtreeex2;

/**
 *
 * @author Ganesh Kumar
 */

import java.util.Iterator;
/**
   An interface for a search tree.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 4.0
*/
public interface SearchTreeInterface<T extends Comparable<? super T>> 
       extends TreeInterface<T>
{
   /** Searches for a specific entry in this tree.
       @param entry  An object to be found.
       @return  True if the object was found in the tree. */
   public boolean contains(T entry);

   /** Retrieves a specific entry in this tree.
       @param entry  An object to be found.
       @return  Either the object that was found in the tree or
                null if no such object exists. */
   public T getEntry(T entry);

   /** Adds a new entry to this tree, if it does not match an existing 
       object in the tree. Otherwise, replaces the existing object with
       the new entry.
       @param newEntry  An object to be added to the tree.
       @return  Either null if newEntry was not in the tree already, or
                the existing entry that matched the parameter newEntry
                and has been replaced in the tree. */
   public T add(T newEntry);

   /** Removes a specific entry from this tree.
       @param entry  An object to be removed.
       @return  Either the object that was removed from the tree or
                null if no such object exists. */
   public T remove(T entry);

   /** Creates an iterator that traverses all entries in this tree.
       @return  An iterator that provides sequential and ordered access
                to the entries in the tree. */
   public Iterator<T> getInorderIterator();
} // end SearchTreeInterface