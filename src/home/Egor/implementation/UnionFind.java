package home.Egor.implementation;

public class UnionFind extends UnionFindDS {

    private int[] max;

    public UnionFind(int N) {
        super(N);
        max = new int[N];
        for (int i = 0; i < N; i++)
            max[i] = i;
    }

    @Override
    protected void callback(int firstRoot, int secondRoot) {
        int maxAll;
        if(max[firstRoot] > max[secondRoot])
            maxAll = max[firstRoot];
        else
            maxAll = max[secondRoot];
        max[firstRoot] = maxAll;
        max[secondRoot] = maxAll;
    }

    public boolean connected(int p, int q) {
        return getRoot(p) == getRoot(q);
    }

    public int find(int i) {
        return max[getRoot(i)];
    }

    public Status union(int first, int second) {
        return super.union(first, second);
    }
}
