import java.util.Random;
import java.util.function.IntFunction;

public class Benchmark {
    private static final int[] SIZES = {100, 1000, 10000};
    private static int sink;

    public static void main(String[] args) {
        runScenario("random", Benchmark::shuffledKeys);
            runScenario("sorted", Benchmark::sortedKeys);
        }
        private static void runScenario(String name, IntFunction<int[]> generator) {
        System.out.println("== " + name + " keys ==");
        for (int n : SIZES) {
            int[] keys = generator.apply(n);
            BST tree = new BST();
            HashTable table = new HashTable();
            long bstInsert = measure(() -> { for (int k : keys) tree.insert(k); });
            System.out.println("BST insert n=" + n + ": " + bstInsert / 1000 + " us");
            long hashInsert = measure(() -> { for (int k : keys) table.insert(k); });
            System.out.println("Hash insert n=" + n + ": " + hashInsert / 1000 + " us");
            long bstSearch = measure(() -> { for (int k : keys) if (tree.contains(k)) sink++; });
            System.out.println("BST search n=" + n + ": " + bstSearch / 1000 + " us");
            long hashSearch = measure(() -> { for (int k : keys) if (table.contains(k)) sink++; });
            System.out.println("Hash search n=" + n + ": " + hashSearch / 1000 + " us");
            long bstDelete = measure(() -> { for (int k : keys) tree.delete(k); });
            System.out.println("BST delete n=" + n + ": " + bstDelete / 1000 + " us");
            long hashDelete = measure(() -> { for (int k : keys) table.delete(k); });
            System.out.println("Hash delete n=" + n + ": " + hashDelete / 1000 + " us");
            printShape(keys);
        }
    }
    private static void printShape(int[] keys) {
        BST tree = new BST();
        HashTable table = new HashTable();
        for (int k : keys) {
            tree.insert(k);
            table.insert(k);
        }
        System.out.println(" shape: BST height=" + tree.height() +
                ", Hash maxChain=" + table.maxChainLength() +
                ", capacity=" + table.capacity());
    }
    private static long measure(Runnable task) {
        long start = System.nanoTime();
        task.run();
        return System.nanoTime() - start;
    }
    private static int[] sortedKeys(int n) {
        int[] keys = new int[n];
        for (int i = 0; i < n; i++) keys[i] = i;
        return keys;
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