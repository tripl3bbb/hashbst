import java.io.*;
import java.util.*;

public class MyBST <K extends Comparable<K>,V> {
    public MyBST() {
        this.root = null;
    }
    private Node root;



    private class Node{
        private K key;
        private V val;
        private Node left,right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
        public Node(K item)
        {
            key = item;
            left = right = null;
        }
    }

    public void put(K key, V val) {
        root = put_recursion(key, root);
    }
    Node put_recursion(K key,Node root) {
        if (root == null) {
            root = new Node(key);
        }

        if (key < root.key)
            root.left = put_recursion(key, root.left);
        else if (key > root.key)
            root.right = put_recursion(key, root.right);
        return root;
    }

    public V get(Node root, K key){
        if (root==null || root.key==key)
            return root;

        if (root.key < key)
            return get(root.right, key);

        return get(root.left, key);
    }

    public void delete(K key) {
        root = delete_recursion(root,key);
    }

    Node delete_recursion(Node root, K key) {
        if (root == null)
            return root;

        if (key < root.key)
            root.left = delete_recursion(root.left, key);
        else if (key > root.key)
            root.right = delete_recursion(root.right, key);

        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.key = minValue(root.right);

            root.right = delete_recursion(root.right, root.key);
        }

        return root;
    }

    int minValue(Node root)
    {
        int minv = root.key;
        while (root.left != null)
        {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }
    void Iterable<K> iterator { inorderRec(root); }
    void inorderRec(Node root)
    {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }
}