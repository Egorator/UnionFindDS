package home.Egor.implementation;

public class SuccessorWithRemove extends UnionFindDS {

    private int[] min;
    private boolean[] removed;
    final int noSuccessor = -1;

    public class Result {
        public Status status;
        public int ReturnedValue;
    }

    public SuccessorWithRemove(int N) {
        super(N);
        min = new int[N];
        removed = new boolean[N];
        for (int i = 0; i < N; i++) {
            min[i] = i;
            removed[i] = false;
        }
    }

    @Override
    protected void callback(int firstRoot, int secondRoot) {
    }

    public Result getSuccessor(int index) {
        Result result = new Result();
        if ((index < 0) || (index >= id.length)) {
            result.status = Status.OUT_OF_RANGE;
            return result;
        }
        result.ReturnedValue = min[getRoot(index)];
        if(result.ReturnedValue == noSuccessor)
            result.status = Status.NO_SUCCESSOR;
        else
            result.status = Status.SUCCESS;
        return result;
    }

    public Status remove(int numToRemove) {
/*        if ((numToRemove < 0) || (numToRemove >= id.length))
            return Status.OUT_OF_RANGE; // repeats in super class, may be removed*/
        if (removed[numToRemove])
            return Status.ALREADY_REMOVED;
        if (numToRemove == id.length - 1)
            min[getRoot(numToRemove)] = noSuccessor;
        else {
            int newMin = min[getRoot(numToRemove + 1)];
            //connectByID(numToRemove, numToRemove + 1);
            // use union() instead with empty callback.
            union(numToRemove, numToRemove + 1);
            callback(0, 0); // can we not to pass senseless arguments?
            min[getRoot(numToRemove)] = newMin;
        }
        removed[numToRemove] = true;
        return Status.SUCCESS; // union also returns status success, isn't it a repeat?
    }
}
