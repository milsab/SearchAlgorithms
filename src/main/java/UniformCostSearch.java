import java.util.*;

public class UniformCostSearch extends Search{

    // define a priority queue (min heap) that always returns a node with the smallest cost (g value)
    private PriorityQueue<Node> minHeap = new PriorityQueue<>(new Comparator<Node>() {
        @Override
        public int compare(Node n1, Node n2) {
            return n1.getG() - n2.getG();
        }
    });

    // maps the serialized version of a node to the cost of that node which has visited
    private HashMap<String, Integer> visitedMap = new HashMap<>();

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

            // Check to see whether current node is the final goal or not
            if(node.isGoal(goal)) {
                solution = node;
                break;
            }

            visitedMap.put(node.serialize(), node.getG());

            int[][] grid = node.getRep();   // get the current grid of the node
            int row = node.getRow();        // get the row index of the blank tile
            int col = node.getCol();        // get the column index of the blank tile

            //region EXPAND THE CURRENT NODE
            // NOTE: in this algorithm we do not consider h(n), so f(n) = g(n)
            // move up the blank tile
            if(row - 1 >= 0){
                int[][] newGrid = node.copy();
                int cost = newGrid[row -1][col];
                int depth = node.getDepth();
                newGrid[row][col] = newGrid[row - 1][col];
                newGrid[row - 1][col] = 0;
                Node newNode = new Node(newGrid, row - 1, col, node.getG() + cost,
                        depth + 1, node.getPathFromStart() + "-> Up(" + cost + ") ");

                // add the new generated node to the priority queue if it has not visited before or
                // the the its previous cost is larger than its current cost
                if(visitedMap.containsKey(newNode.serialize())) {
                    if(newNode.getG() < visitedMap.get(newNode.serialize())) {
                        visitedMap.put(newNode.serialize(), newNode.getG());
                        minHeap.add(newNode);
                    }
                } else{
                    minHeap.add(newNode);
                }
            }

            // move down the blank tile
            if(row + 1 < grid.length){
                int[][] newGrid = node.copy();
                int cost = newGrid[row + 1][col];
                int depth = node.getDepth();
                newGrid[row][col] = newGrid[row + 1][col];
                newGrid[row + 1][col] = 0;
                Node newNode = new Node(newGrid, row + 1, col, node.getG() + cost,
                        depth + 1, node.getPathFromStart() + "-> Down(" + cost + ") ");

                // add the new generated node to the priority queue if it has not visited before or
                // the the its previous cost is larger than its current cost
                if(visitedMap.containsKey(newNode.serialize())) {
                    if(newNode.getG() < visitedMap.get(newNode.serialize())) {
                        visitedMap.put(newNode.serialize(), newNode.getG());
                        minHeap.add(newNode);
                    }
                } else{
                    minHeap.add(newNode);
                }
            }

            // move left the blank tile
            if(col - 1 >= 0){
                int[][] newGrid = node.copy();
                int cost = newGrid[row][col - 1];
                int depth = node.getDepth();
                newGrid[row][col] = newGrid[row][col - 1];
                newGrid[row][col - 1] = 0;
                Node newNode = new Node(newGrid, row, col - 1, node.getG() + cost,
                        depth + 1, node.getPathFromStart() + "-> Left(" + cost + ") ");

                // add the new generated node to the priority queue if it has not visited before or
                // the the its previous cost is larger than its current cost
                if(visitedMap.containsKey(newNode.serialize())) {
                    if(newNode.getG() < visitedMap.get(newNode.serialize())) {
                        visitedMap.put(newNode.serialize(), newNode.getG());
                        minHeap.add(newNode);
                    }
                } else{
                    minHeap.add(newNode);
                }
            }

            // move right the blank tile
            if(col + 1 < grid[0].length){
                int[][] newGrid = node.copy();
                int cost = newGrid[row][col + 1];
                int depth = node.getDepth();
                newGrid[row][col] = newGrid[row][col + 1];
                newGrid[row][col + 1] = 0;
                Node newNode = new Node(newGrid, row, col + 1, node.getG() + cost,
                        depth + 1, node.getPathFromStart() + "-> Right(" + cost + ") ");

                // add the new generated node to the priority queue if it has not visited before or
                // the the its previous cost is larger than its current cost
                if(visitedMap.containsKey(newNode.serialize())) {
                    if(newNode.getG() < visitedMap.get(newNode.serialize())) {
                        visitedMap.put(newNode.serialize(), newNode.getG());
                        minHeap.add(newNode);
                    }
                } else{
                    minHeap.add(newNode);
                }
            }
            //endregion

        }
        // End Timer
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        Result result = new Result("UCS", difficulty, solution, path, time, space, executionTime);
        if(!Main.results.containsKey(difficulty)){
            Main.results.put(difficulty, new ArrayList<>());
        }
        List<Result> resultList = Main.results.get(difficulty);
        resultList.add(result);
        Main.results.put(difficulty, resultList);

        return result;
    }

}
