/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.binarysearchtreeex2;

/**
 *
 * @author Ganesh Kumar
 */
import java.util.Iterator;

/**
 *
 * @author Ganesh Kumar
 */
public class BinarySearchTree<T extends Comparable<? super T>>
extends BinaryTree<T> implements SearchTreeInterface<T>
{
    public BinarySearchTree()
    {
        super();
    } // end default constructor

    public BinarySearchTree(T rootEntry)
    {
        super();
        setRootNode(new BinaryNode<>(rootEntry));
    } // end constructor

    public void setTree(T rootData) // Disable setTree (see Segment 25.6)
    {
        throw new UnsupportedOperationException();
    } // end setTree

    public void setTree(T rootData, BinaryTreeInterface<T> leftTree,
            BinaryTreeInterface<T> rightTree)
    {
        throw new UnsupportedOperationException();
    } // end setTree

    @Override
    public boolean contains(T entry)
    {
        return getEntry(entry) != null;
    } // end contains
    
    @Override
    public T getEntry(T entry)
    {
        return findEntry(getRootNode(), entry);
    } // end getEntry
    private T findEntry(BinaryNodeInterface<T> rootNode, T entry)
    {
        T result = null;
        if (rootNode != null)
        {
            T rootEntry = rootNode.getData();
            if (entry.equals(rootEntry))
                result = rootEntry;
            else if (entry.compareTo(rootEntry) < 0)
                result = findEntry(rootNode.getLeftChild(), entry);
            else
                result = findEntry(rootNode.getRightChild(), entry);
        } // end if
        return result;
    } // end findEntry
    
    @Override
    public T add(T newEntry)
    {
        T result = null;
        if (isEmpty()) {
            setRootNode(new BinaryNode<T>(newEntry));
        }else {
            result = addEntry(getRootNode(), newEntry);
            System.err.println("Add entry " + result);

        }
        
        System.out.println("Node " + result);
        
        return result;   
    } // end add
    
    
    public T remove(T entry)
    {
        ReturnObject oldEntry = new ReturnObject(null);
        BinaryNodeInterface<T> newRoot = (BinaryNodeInterface<T>) removeEntry(getRootNode(), entry,
                oldEntry);
        setRootNode(new BinaryNode<T>(entry));
        return oldEntry.get();
    } // end remove
    
    // Removes an entry from the tree rooted at a given node.
    // rootNode is a reference to the root of a tree.
    // entry is the object to be removed.
    // oldEntry is an object whose data field is null.
    // Returns the root node of the resulting tree; if entry matches
    // an entry in the tree, oldEntry's data field is the entry
    // that was removed from the tree; otherwise it is null.
    private BinaryNodeInterface<T> removeEntry(BinaryNodeInterface<T> rootNode,
            T entry, ReturnObject oldEntry)
    {
        if (rootNode != null)
        {
            T rootData = rootNode.getData();
            int comparison = entry.compareTo(rootData);
            if (comparison == 0) // entry == root entry
            {
                oldEntry.set(rootData);
                rootNode = removeFromRoot(rootNode);
            }
            else if (comparison < 0) // entry < root entry
            {
                BinaryNodeInterface<T> leftChild = rootNode.getLeftChild();
                BinaryNodeInterface<T> subtreeRoot = removeEntry(leftChild,
                        entry, oldEntry);
                rootNode.setLeftChild(subtreeRoot);
            }
            else // entry > root entry
            {
                BinaryNodeInterface<T> rightChild = rootNode.getRightChild();
                rootNode.setRightChild(removeEntry(rightChild, entry, oldEntry));
            } // end if
        } // end if
        return rootNode;
    } // end removeEntry
    
    // Removes the entry in a given root node of a subtree.
    // rootNode is the root node of the subtree.
    // Returns the root node of the revised subtree.
    private BinaryNodeInterface<T> removeFromRoot(BinaryNodeInterface<T> rootNode)
    {
        // Case 1: rootNode has two children
        if (rootNode.hasLeftChild() && rootNode.hasRightChild())
        {
            // find node with largest entry in left subtree
            BinaryNodeInterface<T> leftSubtreeRoot = rootNode.getLeftChild();
            BinaryNodeInterface<T> largestNode = findLargest(leftSubtreeRoot);
            // replace entry in root
            rootNode.setData(largestNode.getData());
            // remove node with largest entry in left subtree
            rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
        } // end if
        // Case 2: rootNode has at most one child
        else if (rootNode.hasRightChild())
            rootNode = rootNode.getRightChild();
        else
            rootNode = rootNode.getLeftChild();
        // Assertion: if rootNode was a leaf, it is now null
        return rootNode;
    } // end removeEntry
    
    // Finds the node containing the largest entry in a given tree.
    // rootNode is the root node of the tree.
    // Returns the node containing the largest entry in the tree.
    private BinaryNodeInterface<T> findLargest(BinaryNodeInterface<T> rootNode)
    {
        if (rootNode.hasRightChild())
            rootNode = findLargest(rootNode.getRightChild());
        return rootNode;
    } // end findLargest
    
    // Removes the node containing the largest entry in a given tree.
    // rootNode is the root node of the tree.
    // Returns the root node of the revised tree.
    private BinaryNodeInterface<T> removeLargest(BinaryNodeInterface<T> rootNode)
    {
        if (rootNode.hasRightChild())
        {
            BinaryNodeInterface<T> rightChild = rootNode.getRightChild();
            BinaryNodeInterface<T> root = removeLargest(rightChild);
            rootNode.setRightChild(root);
        }
        else
            rootNode = rootNode.getLeftChild();
        return rootNode;
    } // end removeLargest
    public NodePair getNodeToRemove(BinaryNodeInterface<T> node) {
        NodePair result = new NodePair();
        T entry = node.getData();
        result = findNode(entry);
        return result;
    }
    
        public NodePair findNode(T entry)
        {
            NodePair result = new NodePair();
            boolean found = false;
            BinaryNodeInterface<T> currentNode = (BinaryNodeInterface<T>) getRootNode();
            BinaryNodeInterface<T> parentNode = (BinaryNodeInterface<T>) null;
            while (!found && (currentNode != null) )
            {
                T currentEntry = currentNode.getData();
                int comparison = entry.compareTo(currentEntry);
                if (comparison < 0)
                {
                    parentNode = currentNode;
                    currentNode = currentNode.getLeftChild();
                }
                else if (comparison > 0)
                {
                    parentNode = currentNode;
                    currentNode = currentNode.getRightChild();
                }
                else // comparison == 0
                    found = true;
            } // end while
            if (found)
                result = new NodePair(currentNode, parentNode);
                // found entry is currentNode.getData()
                return result;
        } // end findNode
    
    public T removeEntry(BinaryNode<T> rootNode, T entry, ReturnObject oldEntry)
    {
        T result = null;
        // locate node (and its parent) that contains a match for entry
        NodePair pair = findNode(entry);
        BinaryNodeInterface<T> currentNode = (BinaryNodeInterface<T>) pair.getFirst();
        BinaryNodeInterface<T> parentNode = (BinaryNodeInterface<T>) pair.getSecond();
        if (currentNode != null) // entry is found
        {
            result = currentNode.getData(); // get entry to be removed
            // Case 1: currentNode has two children
            if (currentNode.hasLeftChild() && currentNode.hasRightChild())
            {
                // replace entry in currentNode with the entry in another node
                // that has at most one child; that node can be deleted
                // get node to remove (contains inorder predecessor; has at
                // most one child) and its parent
                pair = getNodeToRemove(currentNode);
                BinaryNodeInterface<T> nodeToRemove = (BinaryNodeInterface<T>) pair.getFirst();
                parentNode = (BinaryNodeInterface<T>) pair.getSecond();
                // copy entry from nodeToRemove to currentNode
                currentNode.setData(nodeToRemove.getData());
                currentNode = nodeToRemove;
                // Assertion: currentNode is the node to be removed; it has at
                // most one child
                // Assertion: Case 1 has been transformed to Case 2
            } // end if
            // Case 2: currentNode has at most one child; delete it
            removeNode(currentNode, parentNode);
        } // end if
        return result;
    } // end remove
    
    
    private void removeNode(BinaryNodeInterface<T> nodeToRemove,
            BinaryNodeInterface<T> parentNode)
    {
        BinaryNode<T> childNode;
        if (nodeToRemove.hasLeftChild())
            childNode = (BinaryNode<T>) nodeToRemove.getLeftChild();
        else
            childNode = (BinaryNode<T>) nodeToRemove.getRightChild();
        // Assertion: if nodeToRemove is a leaf, childNode is null
        assert (nodeToRemove.isLeaf() && childNode == null) ||
                !nodeToRemove.isLeaf();
        if (nodeToRemove == getRootNode())
            setRootNode(childNode);
        else if (parentNode.getLeftChild() == nodeToRemove)
            parentNode.setLeftChild((BinaryNodeInterface<T>) childNode);
        else
            parentNode.setRightChild((BinaryNodeInterface<T>) childNode);
    } // end removeNode
    @Override
    public Iterator<T> getInorderIterator() {
        return super.getInorderIterator(); 
        // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    // NodePair class implementation
    class NodePair<T extends Comparable<T>> implements Comparable<NodePair<T>> {
        private T data;

    private BinaryNodeInterface<T> node;

    private BinaryNodeInterface<T> parent;

    // Default Constructor
    public NodePair() {

        this.node = null;

        this.parent = null;

    }



    // NodePair Constructor
    public NodePair(BinaryNodeInterface<T> node, BinaryNodeInterface<T> parent) {

        this.node = node;

        this.parent = parent;

    }

    // getter functions
    public BinaryNodeInterface<T> getRootNode() {
        return parent;
    }

    
    public BinaryNodeInterface<T> getFirst() { 
        return node;
    }
    
    public BinaryNodeInterface<T> getSecond() {
        return parent;
    }

    public T getData() {
        return data;
    }
    
//    public T getRootData()
//    {
//        BinaryNode<T> myRoot = new BinaryTree<>();
//        T rootData = null;
//        if (root != null)
//            rootData = root.getData();
//        return rootData;
//    } // end getRootData
//    public boolean isEmpty()
//    {
//        return root == null;
//    } // end isEmpty
//    public void clear()
//    {
//        root = null;
//    } // end clear
//    protected void setRootData(T rootData)
//    {
//        root.setData(rootData);
//    } // end setRootData
//    protected void setRootNode(BinaryNodeInterface<T> rootNode)
//    {
//        root = rootNode;
//    } // end setRootNode
//    protected BinaryNodeInterface<T> getRootNode()
//    {
//        return root;
//    } // end getRootNode

    public int compareTo(NodePair<T> other) {
        return this.data.compareTo(other.data);
    }

    private NodePair getNodeToRemove(BinaryNodeInterface<T> currentNode)
    {
        // find node with largest entry in left subtree by
        // moving as far right in the subtree as possible
        BinaryNodeInterface<T> leftSubtreeRoot = currentNode.getLeftChild();
        BinaryNodeInterface<T> rightChild = leftSubtreeRoot;
        BinaryNodeInterface<T> priorNode = currentNode;
        while (rightChild.hasRightChild())
        {
            priorNode = rightChild;
            rightChild = rightChild.getRightChild();
        } // end while

        // rightChild contains the inorder predecessor and is the node to
        // remove; priorNode is its parent

        return new NodePair(rightChild, priorNode);
    } // end getNodeToRemove

}

    private class ReturnObject
    {
        private T item;

        private ReturnObject(T entry)
        {
            item = entry;
        } // end constructor

        public T get()
        {
            return item;
        } // end get

        public void set(T entry)
        {
            item = entry;
        } // end set
    } // end ReturnObject
} // end BinarySearchTree