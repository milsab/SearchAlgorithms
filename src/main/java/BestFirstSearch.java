import java.util.*;

public class BestFirstSearch extends Search{
    // define a priority queue (min heap) that always returns a node with the smallest heuristic value (h value)
    private PriorityQueue<Node> minHeap = new PriorityQueue<>(new Comparator<Node>() {
        @Override
        public int compare(Node n1, Node n2) {
            return n1.getH() - n2.getH();
        }
    });


    @Override
    public Result search(Node root, int[][] goal, DIFFICULTY difficulty){
        minHeap.add(root);

        // Start Timer
        long startTime = System.currentTimeMillis();
        while(!minHeap.isEmpty()){

            // check the size of the queue and update if it is bigger than the previous max size
            space = Math.max(minHeap.size(), space);

            Node node = minHeap.remove();

            // increase time by one because we popped off one node from the stack
            time++;

            path.add(node.getRep());

            if(node.isGoal(goal)) {
                solution = node;
                break;
            }

            visited.add(node.serialize());

            int[][] grid = node.getRep();   // get the current grid of the node
            int row = node.getRow();        // get the row index of the blank tile
            int col = node.getCol();        // get the column index of the blank tile

            //region EXPAND THE CURRENT NODE
            // NOTE: in this algorithm we do not consider g(n), so f(n) = h(n)
            // move up the blank tile
            if(row - 1 >= 0){
                int[][] newGrid = node.copy();
                int cost = newGrid[row -1][col];
                int depth = node.getDepth();
                int h1 = node.calculateH1(goal);
                newGrid[row][col] = newGrid[row - 1][col];
                newGrid[row - 1][col] = 0;
                Node newNode = new Node(newGrid, row - 1, col, node.getG() + cost, h1,
                        depth + 1, node.getPathFromStart() + "-> Up ");

                // add the new generated node to the queue if it has not visited before
                if(!visited.contains(newNode.serialize()))
                    minHeap.add(newNode);
            }

            // move down the blank tile
            if(row + 1 < grid.length){
                int[][] newGrid = node.copy();
                int cost = newGrid[row + 1][col];
                int h1 = node.calculateH1(goal);
                int depth = node.getDepth();
                newGrid[row][col] = newGrid[row + 1][col];
                newGrid[row + 1][col] = 0;
                Node newNode = new Node(newGrid, row + 1, col, node.getG() + cost, h1,
                        depth + 1, node.getPathFromStart() + "-> Down ");

                // add the new generated node to the queue if it has not visited before
                if(!visited.contains(newNode.serialize()))
                    minHeap.add(newNode);
            }

            // move left the blank tile
            if(col - 1 >= 0){
                int[][] newGrid = node.copy();
                int cost = newGrid[row][col - 1];
                int depth = node.getDepth();
                int h1 = node.calculateH1(goal);
                newGrid[row][col] = newGrid[row][col - 1];
                newGrid[row][col - 1] = 0;
                Node newNode = new Node(newGrid, row, col - 1, node.getG() + cost, h1,
                        depth + 1, node.getPathFromStart() + "-> Left ");

                // add the new generated node to the queue if it has not visited before
                if(!visited.contains(newNode.serialize()))
                    minHeap.add(newNode);
            }

            // move right the blank tile
            if(col + 1 < grid[0].length){
                int[][] newGrid = node.copy();
                int cost = newGrid[row][col +1];
                int h1 = node.calculateH1(goal);
                int depth = node.getDepth();
                newGrid[row][col] = newGrid[row][col + 1];
                newGrid[row][col + 1] = 0;
                Node newNode = new Node(newGrid, row, col + 1, node.getG() + cost, h1,
                        depth + 1, node.getPathFromStart() + "-> Right ");

                // add the new generated node to the queue if it has not visited before
                if(!visited.contains(newNode.serialize()))
                    minHeap.add(newNode);
            }
            //endregion
        }
        // End Timer
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        Result result = new Result("GBF", difficulty, solution, path, time, space, executionTime);
        if(!Main.results.containsKey(difficulty)){
            Main.results.put(difficulty, new ArrayList<>());
        }
        List<Result> resultList = Main.results.get(difficulty);
        resultList.add(result);
        Main.results.put(difficulty, resultList);

        return result;
    }
}
