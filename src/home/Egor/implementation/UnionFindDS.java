package home.Egor.implementation;

public abstract class UnionFindDS {

    protected int[] id;
    private int[] componentSize;

    public enum Status {
        SUCCESS,
        OUT_OF_RANGE,
        ALREADY_CONNECTED,
        NO_SUCCESSOR, // no successor when all successors were removed
        ALREADY_REMOVED //for successor
    }

    public UnionFindDS(int N) {
        if (N <= 0) // this is new code, taken from successorWithRemove project
            throw new IllegalArgumentException("Input argument must be greater than zero");
        id = new int[N];
        componentSize = new int[N];

        for (int i = 0; i < N; i++) {
            id[i] = i;
            componentSize[i] = 1;
        }
    }

    protected abstract void callback(int firstRoot, int secondRoot);

    protected Status union(int first, int second) {
        if ((first < 0) || (first >= id.length)
                || (second < 0) || (second >= id.length))
            return Status.OUT_OF_RANGE;

        int firstRoot = getRoot(first);
        int secondRoot = getRoot(second);
        if (firstRoot == secondRoot)
            return Status.ALREADY_CONNECTED;

        callback(firstRoot, secondRoot);

        int sizeOfFirst = componentSize[firstRoot];//[first]
        int sizeOfSecond = componentSize[secondRoot];//[second]
        connect(firstRoot, secondRoot);
        int newRoot = getRoot(first);
        assert newRoot == getRoot(second);
        assert componentSize[newRoot] == sizeOfFirst + sizeOfSecond;
        return Status.SUCCESS;
    }

    protected int getRoot(int i) {
        assert (i >= 0) && (i < id.length);
        while (i != id[i])
            i = id[i];
        assert (i >= 0) && (i < id.length);
        return i;
    }

    private void connect(int first, int second) {
        assert first != second;
        if (componentSize[first] < componentSize[second])
            mergeFirstIntoSecond(first, second);
        else
            mergeFirstIntoSecond(second, first);
    }

    private void mergeFirstIntoSecond(int first, int second) {
        id[first] = second;
        componentSize[second] += componentSize[first];
    }
}
