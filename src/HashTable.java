public class HashTable {
    private static class Node {
        int key;
        Node next;
        Node(int key, Node next) { this.key = key; this.next = next; }
    }

    private static final int inital = 16;
    private static final double loadFactor = 0.75;

    private Node[] table = new Node[inital];
    private int size;

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    private int hash(int key) {
        int h = key ^ (key >>> 16);
        return (h & 0x7FFFFFFF) % table.length;
    }

    public boolean insert(int key) {
        if (contains(key)) return false;
        if (size >= table.length * loadFactor) resize();
        int i = hash(key);
        table[i] = new Node(key, table[i]);
        size++;
        return true;
    }

    public boolean delete(int key) {
        int i = hash(key);
        Node prev = null;
        for (Node cur = table[i]; cur != null; prev = cur, cur = cur.next) {
            if (cur.key == key) {
                if (prev == null) table[i] = cur.next;
                else prev.next = cur.next;
                size--;
                return true;
            }
        }
        return false;
    }

    private void resize() {
        Node[] old = table;
        table = new Node[old.length * 2];
        for (Node head : old) {
            Node n = head;
            while (n != null) {
                Node next = n.next;
                int i = hash(n.key);
                n.next = table[i];
                table[i] = n;
                n = next;
            }
        }
    }


    public boolean contains(int key) {
        for (Node n = table[hash(key)]; n != null; n = n.next) {
            if (n.key == key) return true;
        }
        return false;
    }

}