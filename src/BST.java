import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayDeque;

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
        if (root == null) {
            root = new Node(key);
            size++;
            return true;
        }
        Node cur = root;
        while (true) {
            if (key < cur.key) {
                if (cur.left == null) { cur.left = new Node(key); break; }
                cur = cur.left;
            } else if (key > cur.key) {
                if (cur.right == null) { cur.right = new Node(key); break; }
                cur = cur.right;
            } else {
                return false;
            }
        }
        size++;
        return true;
    }
    public boolean contains(int key) {
        Node cur = root;
        while (cur != null) {
            if (key == cur.key) return true;
            cur = key < cur.key ? cur.left : cur.right;
        }
        return false;
    }
    public List<Integer> inorder() {
        List<Integer> result = new ArrayList<>();
        ArrayDeque<Node> stack = new ArrayDeque<>();
        Node cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            result.add(cur.key);
            cur = cur.right;
        }
        return result;
    }
    public boolean delete(int key) {
        Node parent = null;
        Node cur = root;
        while (cur != null && cur.key != key) {
            parent = cur;
            cur = key < cur.key ? cur.left : cur.right;
        }
        if (cur == null) return false;
        if (cur.left != null && cur.right != null) {
            Node successorParent = cur;
            Node successor = cur.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            cur.key = successor.key;
            parent = successorParent;
            cur = successor;
        }

        Node child = cur.left != null ? cur.left : cur.right;
        if (parent == null) root = child;
        else if (parent.left == cur) parent.left = child;
        else parent.right = child;
        size--;
        return true;
    }
    public int min() {
        if (root == null) throw new NoSuchElementException("tree is empty");
        return findMin(root).key;
    }

    public int max() {
        return findMax(root).key;
    }
    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private Node findMax(Node node) {
        while (node.right != null) node = node.right;
        return node;
    }
    public int height() {
        if (root == null) return -1;
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        int height = -1;
        while (!queue.isEmpty()) {
            height++;
            for (int i = queue.size(); i > 0; i--) {
                Node node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return height;
    }
}