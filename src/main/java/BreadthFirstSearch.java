import java.util.*;

public class BreadthFirstSearch extends Search{
    private Queue<Node> q = new LinkedList<>();
//    private HashSet<String> visited = new HashSet<>();
//    private List<int[][]> path = new ArrayList<>();
//    private int time = 0;   // number of nodes popped off the queue
//    private int space = 0;  // maintain the size of the queue at its max
//    private Node solution;

    @Override
    public Result search(Node root, int[][] goal){
        time = 0;
        space = 0;
        q.add(root);

        // Start Timer
        long startTime = System.currentTimeMillis();
        while(!q.isEmpty()){
            // check the size of the queue and update if it is bigger than the previous max size
            space = q.size() > space ? q.size() : space;
            Node node = q.remove();

            // increase time by one because we popped off one node from the queue
            time++;

            path.add(node.getRep());

            // Check to see whether current node is the final goal or not
            if(node.isGoal(goal)){
                solution = node;
                break;
            }

            visited.add(node.serialize());  // add the serialized version of the current node to the visited nodes

            int[][] grid = node.getRep();   // get the current grid of the node
            int row = node.getRow();        // get the row index of the blank tile
            int col = node.getCol();        // get the column index of the blank tile


            //region EXPAND THE CURRENT NODE IN BFS ORDER
            // move up the blank tile
            if(row - 1 >= 0){
                int[][] newGrid = node.copy();
                int cost = newGrid[row - 1][col];
                int depth = node.getDepth();
                newGrid[row][col] = newGrid[row - 1][col];
                newGrid[row - 1][col] = 0;
                Node newNode = new Node(newGrid, row - 1, col, node.getG() + cost, depth + 1);

                // add the new generated node to the queue if it has not visited before
                if(!visited.contains(newNode.serialize()))
                    q.add(newNode);
            }

            // move down the blank tile
            if(row + 1 < grid.length){
                int[][] newGrid = node.copy();
                int cost = newGrid[row + 1][col];
                int depth = node.getDepth();
                newGrid[row][col] = newGrid[row + 1][col];
                newGrid[row + 1][col] = 0;
                Node newNode = new Node(newGrid, row + 1, col, node.getG() + cost, depth + 1);

                // add the new generated node to the queue if it has not visited before
                if(!visited.contains(newNode.serialize()))
                    q.add(newNode);
            }

            // move left the blank tile
            if(col - 1 >= 0){
                int[][] newGrid = node.copy();
                int cost = newGrid[row][col - 1];
                int depth = node.getDepth();
                newGrid[row][col] = newGrid[row][col - 1];
                newGrid[row][col - 1] = 0;
                Node newNode = new Node(newGrid, row, col - 1, node.getG() + cost, depth + 1);

                // add the new generated node to the queue if it has not visited before
                if(!visited.contains(newNode.serialize()))
                    q.add(newNode);
            }

            // move right the blank tile
            if(col + 1 < grid[0].length){
                int[][] newGrid = node.copy();
                int cost = newGrid[row][col + 1];
                int depth = node.getDepth();
                newGrid[row][col] = newGrid[row][col + 1];
                newGrid[row][col + 1] = 0;
                Node newNode = new Node(newGrid, row, col + 1, node.getG() + cost, depth + 1);

                // add the new generated node to the queue if it has not visited before
                if(!visited.contains(newNode.serialize()))
                    q.add(newNode);
            }
            //endregion
        }
        // End Timer
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        Result result = new Result(solution, path, time, space, executionTime);

        return result;
    }
}
