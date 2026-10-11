public class HashTable {
    private static class Node {
        int key;
        Node next;
        Node(int key, Node next) { this.key = key; this.next = next; }
    }

    private static final int inital = 16;

    private Node[] table = new Node[inital];
    private int size;

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    private int hash(int key) {
        return Math.abs(key) % table.length;
    }

    public void insert(int key) {
        int i = hash(key);
        table[i] = new Node(key, table[i]);
        size++;
    }

    public boolean contains(int key) {
        for (Node n = table[hash(key)]; n != null; n = n.next) {
            if (n.key == key) return true;
        }
        return false;
    }

}