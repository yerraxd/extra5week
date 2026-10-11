import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BST {
    private static class Node {
        int key;
        Node left, right;
        Node(int key) { this.key = key; }
    }

    private Node root;
    private int size;

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public boolean insert(int key) {
        if (contains(key)) return false;
        root = insert(root, key);
        size++;
        return true;
    }


    private Node insert(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insert(node.left, key);
        else node.right = insert(node.right, key);
        return node;
    }
    public boolean contains(int key) {
        return contains(root, key);
    }

    private boolean contains(Node node, int key) {
        if (node == null) return false;
        if (key == node.key) return true;
        return key < node.key ? contains(node.left, key) : contains(node.right, key);
    }
    public List<Integer> inorder() {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(Node node, List<Integer> result) {
        if (node == null) return;
        inorder(node.left, result);
        result.add(node.key);
        inorder(node.right, result);
    }
    public boolean delete(int key) {
        if (!contains(key)) return false;
        root = delete(root, key);
        size--;
        return true;
    }

    private Node delete(Node node, int key) {
        if (node == null) return null;
        if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.key = successor.key;
            node.right = delete(node.right, successor.key);
        }
        return node;
    }
    public int min() {
        if (root == null) throw new NoSuchElementException("tree is empty");
        Node cur = root;
        while (cur.left != null) cur = cur.left;
        return cur.key;
    }

    public int max() {
        if (root == null) throw new NoSuchElementException("tree is empty");
        Node cur = root;
        while (cur.right != null) cur = cur.right;
        return cur.key;
    }



}