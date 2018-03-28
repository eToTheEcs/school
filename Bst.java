/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bst;

/**
 *
 * @author Nicolas Benatti
 */
public class Bst <E extends Comparable<E>> {
    
    private class Node {
        
        public E val;
        
        public Node sx;
        public Node dx;
        
        public Node() {}
        
        public Node(E _val, Node _sx, Node _dx) {
            
            val = _val;
            sx = _sx;
            dx = _dx;
        }
        
        @Override
        public String toString() {
            
            return ""+val;
        }
    }
    
    private Node root;
    
    public Bst() {
        
        root = null;
    }
    
    private Node insert(Node curRoot, E el) {
        
        if(curRoot == null) {
            curRoot = new Node(el, null, null);
            return curRoot;
        }
        
        if( el.compareTo(curRoot.val) < 0 )
            curRoot.sx = insert(curRoot.sx, el);
        else if( el.compareTo(curRoot.val) > 0 )
            curRoot.dx = insert(curRoot.dx, el);
        else if( el.compareTo(curRoot.val) == 0 )
            return null;
        
        return curRoot;
    }
    
    public void insert(E el) {
        root = insert(root, el);
    }
    
    private boolean find(Node curRoot, E el) {
        
        if(curRoot == null)
            return false;
        
        if(curRoot.val.compareTo(el) == 0)
            return true;
        else if(el.compareTo(curRoot.val) > 0)
            return find(curRoot.dx, el);
        else if(el.compareTo(curRoot.val) < 0)
            return find(curRoot.sx, el);
        
        return false;
    }
    
    public boolean find(E el) {
        return find(root, el);
    }
    
    private int contaNodi(Node curRoot) {
        
        if(curRoot == null)
            return 0;

        return 1 + contaNodi(curRoot.sx) + contaNodi(curRoot.dx);
    }
    
    public int contaNodi() {
        return contaNodi(root);
    }
    
    private void preorder(Node curRoot) {
        
        if(curRoot == null)
            return;
        
        System.out.println(curRoot.val);
        preorder(curRoot.sx);
        preorder(curRoot.dx);
    }
    
    private void inorder(Node curRoot) {
        
        if(curRoot == null)
            return;
        
        preorder(curRoot.sx);
        System.out.println(curRoot.val);
        preorder(curRoot.dx);
    }
    
    private void stampaIndentato(Node curRoot, int depth) {
        
        if(curRoot == null)
            return;
        
        //System.out.println(curRoot.val + ", " + curRoot.sx + ", " + curRoot.dx);
        
        for(int i = 0; i < depth; ++i)
            System.out.print("\t"); // indento pari al livello del nood
        
        System.out.println(curRoot.val);
        
        stampaIndentato(curRoot.sx, depth+1);
        stampaIndentato(curRoot.dx, depth+1);
    }
    
    public void stampaIndentato() {
        
        stampaIndentato(root, 0);
    }
}
