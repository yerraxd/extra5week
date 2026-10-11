import java.util.Random;

public class Benchmark {
    private static final int[] SIZES = {100, 1000, 10000};

    public static void main(String[] args) {
        for (int n : SIZES) {
            int[] keys = randomKeys(n);
            BST tree = new BST();
            long start = System.nanoTime();
            for (int k : keys) tree.insert(k);
            long elapsed = System.nanoTime() - start;
            System.out.println("BST insert n=" + n + ": " + elapsed / 1000 + " us");
        }
    }

    private static int[] randomKeys(int n) {
        Random rnd = new Random(42);
        int[] keys = new int[n];
        for (int i = 0; i < n; i++) keys[i] = rnd.nextInt();
        return keys;
    }
}