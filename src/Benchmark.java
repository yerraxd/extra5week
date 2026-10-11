import java.util.Random;

public class Benchmark {
    private static final int[] SIZES = {100, 1000, 10000};

    public static void main(String[] args) {
        for (int n : SIZES) {
            int[] keys = shuffledKeys(n);
            BST tree = new BST();
            long start = System.nanoTime();
            for (int k : keys) tree.insert(k);
            long elapsed = System.nanoTime() - start;
            System.out.println("BST insert n=" + n + ": " + elapsed / 1000 + " us");
            HashTable table = new HashTable();
            start = System.nanoTime();
            for (int k : keys) table.insert(k);
            elapsed = System.nanoTime() - start;
            System.out.println("Hash insert n=" + n + ": " + elapsed / 1000 + " us");
        }
    }

    private static int[] shuffledKeys(int n) {
        int[] keys = new int[n];
        for (int i = 0; i < n; i++) keys[i] = i;
        Random rnd = new Random(42);
        for (int i = n - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);
            int tmp = keys[i];
            keys[i] = keys[j];
            keys[j] = tmp;
        }
        return keys;
    }
}