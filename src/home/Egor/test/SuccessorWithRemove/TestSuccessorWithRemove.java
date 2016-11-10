package home.Egor.test.SuccessorWithRemove;

import home.Egor.implementation.SuccessorWithRemove;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestSuccessorWithRemove {

    @Test
    public void generalTest() {
        SuccessorWithRemove successorWithRemove = new SuccessorWithRemove(5);
        successorWithRemove.remove(4);

        //assertEquals(-1, successorWithRemove.getSuccessor(4));
    }
}
