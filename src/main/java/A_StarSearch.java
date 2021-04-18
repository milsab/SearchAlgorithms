import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class A_StarSearch extends Search{
    // define a priority queue (min heap) that always returns a node with the smallest f value (f = g + h)
    private PriorityQueue<Node> minHeap = new PriorityQueue<>(new Comparator<Node>() {
        @Override
        public int compare(Node n1, Node n2) {
            return n1.getF() - n2.getF();
        }
    });

    // maps the serialized version of a node to the f value of that node which has visited
    private HashMap<String, Integer> visitedMap = new HashMap<>();

    // a hashmap that map the value of each tile in the goal to its positions
    private HashMap<Integer, int[]> goalMap;


    // map the value of each tile in the goal to its positions
    public void mapGoal(int[][] goal){

        HashMap<Integer, int[]> map = new HashMap<>();
        for(int i = 0; i < goal.length; i++) {
            for(int j = 0; j < goal[0].length; j++){
                map.put(goal[i][j], new int[]{i, j});
            }
        }
        goalMap = map;
    }

    // if the type = 1 then the A* will search with h1 function
    // if the type = 2 then the A8 will search with h2 function
    public Result search(Node root, int[][] goal, byte type) {
        // check the edge case
        if(type != 1 && type != 2)
            return null;

        // map the value of each tile in the goal to its positions
        mapGoal(goal);

        minHeap.add(root);

        // Start Timer
        long startTime = System.currentTimeMillis();
        while(!minHeap.isEmpty()){
            // check the size of the queue and update if it is bigger than the previous max size
            space = minHeap.size() > space ? minHeap.size() : space;

            Node node = minHeap.remove();

            // increase time by one because we popped off one node from the stack
            time++;

            path.add(node.getRep());

            // Check to see whether current node is the final goal or not
            if(node.isGoal(goal)) {
                solution = node;
                break;
            }

            visitedMap.put(node.serialize(), node.getF());

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
                int h = 0;
                if (type == 1) {
                    int h1 = node.calculateH1(goal);
                    h = h1;
                } else {
                    int h2 = node.calculateH2(goalMap);
                    h = h2;
                }
                int f = cost + h;
                newGrid[row][col] = newGrid[row - 1][col];
                newGrid[row - 1][col] = 0;
                Node newNode = new Node(newGrid, row - 1, col, node.getG() + cost, h, node.getF() + f, depth + 1);

                // add the new generated node to the priority queue if it has not visited before or
                // its previous f value is larger than its current f value
                if(visitedMap.containsKey(newNode.serialize())) {
                    if(newNode.getF() < visitedMap.get(newNode.serialize())) {
                        visitedMap.put(newNode.serialize(), newNode.getF());
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
                int h = 0;
                if (type == 1) {
                    int h1 = node.calculateH1(goal);
                    h = h1;
                } else {
                    int h2 = node.calculateH2(goalMap);
                    h = h2;
                }
                int f = cost + h;
                newGrid[row][col] = newGrid[row + 1][col];
                newGrid[row + 1][col] = 0;
                Node newNode = new Node(newGrid, row + 1, col, node.getG() + cost, h, node.getF() + f, depth + 1);

                // add the new generated node to the priority queue if it has not visited before or
                // its previous f value is larger than its current f value
                if(visitedMap.containsKey(newNode.serialize())) {
                    if(newNode.getF() < visitedMap.get(newNode.serialize())) {
                        visitedMap.put(newNode.serialize(), newNode.getF());
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
                int h = 0;
                if (type == 1) {
                    int h1 = node.calculateH1(goal);
                    h = h1;
                } else {
                    int h2 = node.calculateH2(goalMap);
                    h = h2;
                }
                int f = cost + h;
                newGrid[row][col] = newGrid[row][col - 1];
                newGrid[row][col - 1] = 0;
                Node newNode = new Node(newGrid, row, col - 1, node.getG() + cost, h, node.getF() + f, depth + 1);

                // add the new generated node to the priority queue if it has not visited before or
                // its previous f value is larger than its current f value
                if(visitedMap.containsKey(newNode.serialize())) {
                    if(newNode.getF() < visitedMap.get(newNode.serialize())) {
                        visitedMap.put(newNode.serialize(), newNode.getF());
                        minHeap.add(newNode);
                    }
                } else{
                    minHeap.add(newNode);
                }
            }

            // move right the blank tile
            if(col + 1 < grid[0].length){
                int[][] newGrid = node.copy();
                int cost = newGrid[row][col +1];
                int depth = node.getDepth();
                int h = 0;
                if (type == 1) {
                    int h1 = node.calculateH1(goal);
                    h = h1;
                } else {
                    int h2 = node.calculateH2(goalMap);
                    h = h2;
                }
                int f = cost + h;
                newGrid[row][col] = newGrid[row][col + 1];
                newGrid[row][col + 1] = 0;
                Node newNode = new Node(newGrid, row, col + 1, node.getG() + cost, h, node.getF() + f, depth + 1);

                // add the new generated node to the priority queue if it has not visited before or
                // its previous f value is larger than its current f value
                if(visitedMap.containsKey(newNode.serialize())) {
                    if(newNode.getF() < visitedMap.get(newNode.serialize())) {
                        visitedMap.put(newNode.serialize(), newNode.getF());
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

        Result result = new Result(solution, path, time, space, executionTime);

        return result;
    }

    @Override
    public Result search(Node root, int[][] goal) {
        return null;
    }
}
