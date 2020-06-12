package data_structures.unionfind;

/**
 * eg. use case would be: Kruskal's minimum spanning tree
 */

/**
 * UnionFind/Disjoined Set data structure implementation
 */
public class UnionFind {

    // The number of elements in the union find
    private int size;

    // Used to track the sizes of each of the components
    private int[] sz;

    // id[i] points to the parent of i, if id[i] = i the i is the root node
    private int[] id;

    // Tracks the number of components in the union find
    private int numComponents;

    public UnionFind(int size) {
        if (size <= 0) throw new IllegalArgumentException("Size <= 0 is not allowed");

        this.size = numComponents = size;
        sz = new int[size];
        id = new int[size];

        for(int i=0; i<size; i++) {
            id[i] = i; // Link to itself (self root) initially
            sz[i] = i; // Each component is originally of size one
        }
    }

    // Find which component/set 'p' belongs to, takes amortized constant time.
    public int find(int p) {
        // Find the root of the component/set
        int root = p;
        while(root != id[root])
            root = id[root];

        // Compress the path leading back to the root.
        // Doing this operation is called "path compressions"
        // and is what gives us amortized constant time complexity
        while (p!= root) {
            int next = id[p];
            id[p] = root;
            p = next;
        }
        return root;
    }

    // Return whether or not the elements 'p' and
    // 'q' are in the same component/set.
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // Return the size of the components/set 'p' belongs to
    public int componentSize(int p) {
        return sz[find(p)];
    }

    // Return the number of elements in this UnionFind/Disjoint set
    public int size() {
        return size;
    }

    // Returns the number of remaining components/sets
    public int components() {
        return numComponents;
    }

    // Unify the components/sets containing elements 'p' and 'q'
    public void unify(int p, int q) {

        int root1 = find(p);
        int root2 = find(q);

        // These elements are already in the same group then return
        if (root1 == root2) return;

        // Merge two components/set together.
        // Merge smaller components/set into the larger one.
        if(sz[root1] < sz[root2]) {
            sz[root2] += sz[root1];
            id[root1] = root2;
        } else {
            sz[root1] += sz[root2];
            id[root2] = root1;
        }

        // Since the roots found are different we know that the
        // number of components/sets has decreased by one
        numComponents--;
    }


}
