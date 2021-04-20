import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch extends Search{
    private Stack<Node> stack = new Stack<>();


    @Override
    public Result search(Node root, int[][] goal, DIFFICULTY difficulty) {
        time = 0;
        space = 0;
        solution = new Node();

        stack.push(root);

        // Start Timer
        long startTime = System.currentTimeMillis();
        while(!stack.isEmpty()){
            // check the size of the queue and update if it is bigger than the previous max size
            space = Math.max(stack.size(), space);
            Node node = stack.pop();

            // increase time by one because we popped off one node from the stack
            time++;
            path.add(node.getRep());

            // Check to see whether current node is the final goal or not
            if(node.isGoal(goal)) {
                solution = node;
                break;
            }

            visited.add(node.serialize());

            int[][] grid = node.getRep();   // get the current grid of the node
            int row = node.getRow();        // get the row index of the blank tile
            int col = node.getCol();        // get the column index of the blank tile

            //region EXPAND THE CURRENT NODE IN DFS ORDER
            // move up the blank tile
            if(row - 1 >= 0){
                int[][] newGrid = node.copy();
                int cost = newGrid[row - 1][col];
                int depth = node.getDepth();
                newGrid[row][col] = newGrid[row - 1][col];
                newGrid[row - 1][col] = 0;
                Node newNode = new Node(newGrid, row - 1, col, node.getG() + cost,
                        depth + 1, node.getPathFromStart() + "-> Up ");

                // add the new generated node to the queue if it has not visited before
                if(!visited.contains(newNode.serialize()))
                    stack.push(newNode);
            }

            // move down the blank tile
            if(row + 1 < grid.length){
                int[][] newGrid = node.copy();
                newGrid[row][col] = newGrid[row + 1][col];
                int cost = newGrid[row + 1][col];
                int depth = node.getDepth();
                newGrid[row + 1][col] = 0;
                Node newNode = new Node(newGrid, row + 1, col, node.getG() + cost,
                        depth + 1, node.getPathFromStart() + "-> Down ");

                // add the new generated node to the queue if it has not visited before
                if(!visited.contains(newNode.serialize()))
                    stack.push(newNode);
            }

            // move left the blank tile
            if(col - 1 >= 0){
                int[][] newGrid = node.copy();
                newGrid[row][col] = newGrid[row][col - 1];
                int cost = newGrid[row][col - 1];
                int depth = node.getDepth();
                newGrid[row][col - 1] = 0;
                Node newNode = new Node(newGrid, row, col - 1, node.getG() + cost,
                        depth + 1, node.getPathFromStart() + "-> Left ");

                // add the new generated node to the queue if it has not visited before
                if(!visited.contains(newNode.serialize()))
                    stack.push(newNode);
            }

            // move right the blank tile
            if(col + 1 < grid[0].length){
                int[][] newGrid = node.copy();
                newGrid[row][col] = newGrid[row][col + 1];
                int cost = newGrid[row][col + 1];
                int depth = node.getDepth();
                newGrid[row][col + 1] = 0;
                Node newNode = new Node(newGrid, row, col + 1, node.getG() + cost,
                        depth + 1, node.getPathFromStart() + "-> Right ");

                // add the new generated node to the queue if it has not visited before
                if(!visited.contains(newNode.serialize()))
                    stack.push(newNode);
            }
            //endregion
        }
        // End Timer
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        Result result = new Result("DFS", difficulty, solution, path, time, space, executionTime);
        if(!Main.results.containsKey(difficulty)){
            Main.results.put(difficulty, new ArrayList<>());
        }
        List<Result> resultList = Main.results.get(difficulty);
        resultList.add(result);
        Main.results.put(difficulty, resultList);
        return result;
    }
}
