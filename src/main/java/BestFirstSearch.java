import java.util.*;

public class BestFirstSearch {
    // define a priority queue (min heap) that always returns a node with the smallest h value
    private PriorityQueue<Node> minHeap = new PriorityQueue<>(new Comparator<Node>() {
        @Override
        public int compare(Node n1, Node n2) {
            return n1.getF() - n2.getF();
        }
    });
    private HashSet<String> visited = new HashSet<>();
    private List<int[][]> path = new ArrayList<>();

    public Result search(Node root, int[][] goal){
        minHeap.add(root);

        // Start Timer
        long startTime = System.currentTimeMillis();
        while(!minHeap.isEmpty()){
            Node node = minHeap.remove();
            path.add(node.getRep());

            if(node.isGoal(goal))
                break;

            visited.add(node.serialize());

            int[][] grid = node.getRep();   // get the current grid of the node
            int row = node.getRow();        // get the row index of the blank tile
            int col = node.getCol();        // get the column index of the blank tile

            //region EXPAND THE CURRENT NODE
            // NOTE: in this algorithm we do not consider g(n), so f(n) = h(n)
            // move up the blank tile
            if(row - 1 >= 0){
                int[][] newGrid = node.copy();
                newGrid[row][col] = newGrid[row - 1][col];
                int h1 = node.calculateH1(goal);
                newGrid[row - 1][col] = 0;
                Node newNode = new Node(newGrid, row - 1, col, h1);

                // add the new generated node to the queue if it has not visited before
                if(!visited.contains(newNode.serialize()))
                    minHeap.add(newNode);
            }

            // move down the blank tile
            if(row + 1 < grid.length){
                int[][] newGrid = node.copy();
                newGrid[row][col] = newGrid[row + 1][col];
                int h1 = node.calculateH1(goal);
                newGrid[row + 1][col] = 0;
                Node newNode = new Node(newGrid, row + 1, col, h1);

                // add the new generated node to the queue if it has not visited before
                if(!visited.contains(newNode.serialize()))
                    minHeap.add(newNode);
            }

            // move left the blank tile
            if(col - 1 >= 0){
                int[][] newGrid = node.copy();
                newGrid[row][col] = newGrid[row][col - 1];
                int h1 = node.calculateH1(goal);
                newGrid[row][col - 1] = 0;
                Node newNode = new Node(newGrid, row, col - 1, h1);

                // add the new generated node to the queue if it has not visited before
                if(!visited.contains(newNode.serialize()))
                    minHeap.add(newNode);
            }

            // move right the blank tile
            if(col + 1 < grid[0].length){
                int[][] newGrid = node.copy();
                newGrid[row][col] = newGrid[row][col + 1];
                int h1 = node.calculateH1(goal);
                newGrid[row][col + 1] = 0;
                Node newNode = new Node(newGrid, row, col + 1, h1);

                // add the new generated node to the queue if it has not visited before
                if(!visited.contains(newNode.serialize()))
                    minHeap.add(newNode);
            }
            //endregion
        }
        // End Timer
        long endTime = System.currentTimeMillis();

        Result result = new Result(path, endTime - startTime);

        return result;
    }
}
