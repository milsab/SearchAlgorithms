import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


// This is an Abstract Class. All different search algorithm classes have been inherited from this class.
public abstract class Search {
    protected int time = 0;   // number of nodes popped off the queue
    protected int space = 0;  // maintain the size of the queue at its max
    protected HashSet<String> visited = new HashSet<>();
    protected List<int[][]> path = new ArrayList<>();
    protected Node solution;

    public abstract Result search(Node root, int[][] goal);
}
