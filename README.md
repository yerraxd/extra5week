# BST vs Hash Table

Java implementation and performance comparison of a Binary Search Tree and a Hash Table (separate chaining) with int keys.

## Structure

```
src/
  BST.java
  HashTable.java
  Benchmark.java
```

Run:
```
javac -d out src/*.java
java -cp out Benchmark
```

## Implementations

- **BST:** unbalanced, no duplicates. Methods: `insert`, `contains`, `delete` (inorder successor for two children), `min`, `max`, `height`, `inorder`.
- **HashTable:** array of linked-list buckets, initial capacity 16, resize (x2) when the load factor exceeds 0.75. Methods: `insert`, `contains`, `delete`.

## Complexity

| Operation | BST best | BST average | BST worst | Hash best | Hash average | Hash worst |
|---|---|---|---|---|---|---|
| insert | O(1) | O(log n) | O(n) | O(1) | O(1) | O(n) |
| search | O(1) | O(log n) | O(n) | O(1) | O(1) | O(n) |
| delete | O(1) | O(log n) | O(n) | O(1) | O(1) | O(n) |

- BST worst case: degenerate tree (sorted input), height equals n-1.
- Hash worst case: all keys in one bucket. A resize costs O(n), but amortized insert is O(1).

## Results

Median of 3 runs, in microseconds, format `BST / Hash`. Runs are single measurements without warm-up, so n = 100 is noisy.

**Random keys** (BST height 13 / 26 / 29)

| Operation | n = 100 | n = 1000 | n = 10000 |
|---|---|---|---|
| insert | 3933 / 322 | 1341 / 1465 | 6956 / 9960 |
| search | 92 / 54 | 1327 / 161 | 12499 / 1979 |
| delete | 107 / 65 | 1689 / 979 | 10510 / 2009 |

**Sorted keys** (BST height 99 / 999 / 9999)

| Operation | n = 100 | n = 1000 | n = 10000 |
|---|---|---|---|
| insert | 52 / 97 | 5825 / 205 | 330114 / 9342 |
| search | 42 / 16 | 2813 / 139 | 723808 / 2206 |
| delete | 11 / 13 | 116 / 156 | 1524 / 1392 |

## Conclusions

- On sorted input the BST degenerates into a list and becomes much slower than the hash table.
- On random input both are close, and hash search is faster.
- BST delete on sorted input is fast only because keys are deleted in ascending order.
- Hash maxChain is 1 because the keys are 0..n-1, so the hash worst case is not tested here.
