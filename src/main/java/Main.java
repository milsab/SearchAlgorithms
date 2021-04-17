import java.util.*;

public class Main {

    String solution = "";


    public static void main(String[] args) {
        int[][] easyRoot = new int[][]{
                {1, 3, 4},
                {8, 6, 2},
                {7, 0, 5}
        };

        int[][] mediumRoot = new int[][]{
                {2, 8, 1},
                {0, 4, 3},
                {7, 6, 5}
        };

        int[][] hardRoot = new int[][]{
                {5, 6, 7},
                {4, 0, 8},
                {3, 2, 1}
        };

        int[][] goal = new int[][]{
                {1, 2, 3},
                {8, 0, 4},
                {7, 6, 5}
        };

        Node root = new Node(mediumRoot, 1, 1);

//        List<List<int[][]>> bfsPath = bfs(root, goal);
//        printBFSPath(bfsPath);

//        Result dfsPath = dfs(root, goal);
//        printPath(dfsPath);

//        UniformCostSearch usc = new UniformCostSearch();
//        Result uscPath = usc.search(root, goal);
//        printPath(uscPath);

//        BestFirstSearch bfs = new BestFirstSearch();
//        Result bfsPath = bfs.search(root, goal);
//        printPath(bfsPath);

        Scanner scan = new Scanner(System.in);
        int choice = -1;
        printMenu();
        choice = scan.nextInt();
        while(choice != 8){
            switch (choice){
                case 1:
                    List<List<int[][]>> brfsPath = brfs(root, goal);
                    printBFSPath(brfsPath);
                    break;
                case 2:
                    Result dfsPath = dfs(root, goal);
                    printPath(dfsPath);
                    break;
                case 3:
                    UniformCostSearch usc = new UniformCostSearch();
                    Result uscPath = usc.search(root, goal);
                    printPath(uscPath);
                    break;
                case 4:
                    BestFirstSearch bfs = new BestFirstSearch();
                    Result bfsPath = bfs.search(root, goal);
                    printPath(bfsPath);
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Invalid Choice! Please choose a number between 1 to 8.");
            }
            printMenu();
            choice = scan.nextInt();

        }
        System.out.println("Thank you for using this program!");
    }

    // Print the main menu
    public static void printMenu(){
        System.out.println("\nPlease choose one of the following search algorithms:\n" +
                "1) Breath First Search\n" +
                "2) Depth First Serarch\n" +
                "3) Uniform Cost Search\n" +
                "4) Best First Search\n" +
                "5) A* (h1)\n" +
                "6) A* (h2)\n" +
                "7) Report\n" +
                "8) Exit\n");
    }

    // Breath First Search
    public static List<List<int[][]>> brfs(Node root, int[][] goal){
        Queue<Node> q = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        List<List<int[][]>> path = new ArrayList<>();

        q.add(root);

        // Start Timer
        long startTime = System.currentTimeMillis();
        while(!q.isEmpty()){

            int size = q.size();
            path.add(new ArrayList<>());
            for(int i = 0; i < size; i++){

                Node node = q.remove();
                path.get(path.size() - 1).add(node.getRep());        // log the current node in our traversed path

                // Check to see whether current node is the final goal or not
                if(isGoal(node, goal)){
                    return path;
                }

                visited.add(node.serialize());  // add the serialized version of the current node to the visited nodes

                int[][] grid = node.getRep();   // get the current grid of the node
                int row = node.getRow();        // get the row index of the blank tile
                int col = node.getCol();        // get the column index of the blank tile


                //region EXPAND THE CURRENT NODE IN BFS ORDER
                // move up the blank tile
                if(row - 1 >= 0){
                    int[][] newGrid = node.copy();
                    newGrid[row][col] = newGrid[row - 1][col];
                    newGrid[row - 1][col] = 0;
                    Node newNode = new Node(newGrid, row - 1, col);

                    // add the new generated node to the queue if it has not visited before
                    if(!visited.contains(newNode.serialize()))
                        q.add(newNode);
                }

                // move down the blank tile
                if(row + 1 < grid.length){
                    int[][] newGrid = node.copy();
                    newGrid[row][col] = newGrid[row + 1][col];
                    newGrid[row + 1][col] = 0;
                    Node newNode = new Node(newGrid, row + 1, col);

                    // add the new generated node to the queue if it has not visited before
                    if(!visited.contains(newNode.serialize()))
                        q.add(newNode);
                }

                // move left the blank tile
                if(col - 1 >= 0){
                    int[][] newGrid = node.copy();
                    newGrid[row][col] = newGrid[row][col - 1];
                    newGrid[row][col - 1] = 0;
                    Node newNode = new Node(newGrid, row, col - 1);

                    // add the new generated node to the queue if it has not visited before
                    if(!visited.contains(newNode.serialize()))
                        q.add(newNode);
                }

                // move right the blank tile
                if(col + 1 < grid[0].length){
                    int[][] newGrid = node.copy();
                    newGrid[row][col] = newGrid[row][col + 1];
                    newGrid[row][col + 1] = 0;
                    Node newNode = new Node(newGrid, row, col + 1);

                    // add the new generated node to the queue if it has not visited before
                    if(!visited.contains(newNode.serialize()))
                        q.add(newNode);
                }
                //endregion
            }
        }
        // End Timer
        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");
        return path;
    }

    // Depth First Search
    public static Result dfs (Node root, int[][] goal){
        Stack<Node> stack = new Stack<>();
        HashSet<String> visited = new HashSet<>();
        List<int[][]> path = new ArrayList<>();

        stack.push(root);

        // Start Timer
        long startTime = System.currentTimeMillis();
        while(!stack.isEmpty()){
            Node node = stack.pop();
            path.add(node.getRep());

            if(isGoal(node, goal))
                break;

            visited.add(node.serialize());

            int[][] grid = node.getRep();   // get the current grid of the node
            int row = node.getRow();        // get the row index of the blank tile
            int col = node.getCol();        // get the column index of the blank tile

            //region EXPAND THE CURRENT NODE IN DFS ORDER
            // move up the blank tile
            if(row - 1 >= 0){
                int[][] newGrid = node.copy();
                newGrid[row][col] = newGrid[row - 1][col];
                newGrid[row - 1][col] = 0;
                Node newNode = new Node(newGrid, row - 1, col);

                // add the new generated node to the queue if it has not visited before
                if(!visited.contains(newNode.serialize()))
                    stack.push(newNode);
            }

            // move down the blank tile
            if(row + 1 < grid.length){
                int[][] newGrid = node.copy();
                newGrid[row][col] = newGrid[row + 1][col];
                newGrid[row + 1][col] = 0;
                Node newNode = new Node(newGrid, row + 1, col);

                // add the new generated node to the queue if it has not visited before
                if(!visited.contains(newNode.serialize()))
                    stack.push(newNode);
            }

            // move left the blank tile
            if(col - 1 >= 0){
                int[][] newGrid = node.copy();
                newGrid[row][col] = newGrid[row][col - 1];
                newGrid[row][col - 1] = 0;
                Node newNode = new Node(newGrid, row, col - 1);

                // add the new generated node to the queue if it has not visited before
                if(!visited.contains(newNode.serialize()))
                    stack.push(newNode);
            }

            // move right the blank tile
            if(col + 1 < grid[0].length){
                int[][] newGrid = node.copy();
                newGrid[row][col] = newGrid[row][col + 1];
                newGrid[row][col + 1] = 0;
                Node newNode = new Node(newGrid, row, col + 1);

                // add the new generated node to the queue if it has not visited before
                if(!visited.contains(newNode.serialize()))
                    stack.push(newNode);
            }
            //endregion

        }
        // End Timer
        long endTime = System.currentTimeMillis();

        Result result = new Result(path, endTime - startTime);

        return result;
    }

    public static boolean isGoal(Node node, int[][] goal){
        for(int i = 0; i < node.getRep().length; i++){
            for(int j = 0; j < node.getRep()[0].length; j++){
                if(node.getRep()[i][j] != goal[i][j])
                    return false;
            }
        }
        return true;
    }

    public static void printBFSPath(List<List<int[][]>> path){
        int count = 0;
        for(int m = 0; m < path.size(); m++){
            System.out.println(String.format("*** Depth=%s ***", m));
            for (int n = 0; n < path.get(m).size(); n++) {
                int[][] node = path.get(m).get(n);
                for(int i = 0; i < node.length; i++){
                    for(int j = 0; j < node[0].length; j++){
                        System.out.print(node[i][j] + " ");
                    }
                    System.out.println();
                    count++;
                }
                System.out.println("------");
            }
        }
        System.out.println(String.format("Found the Goal after traversing of %s node", count));
    }

    public static void printPath(Result result){
        int count = 0;
        List<int[][]> traversePath = result.getTraversePath();
        long time = result.time;
        for (int[][] node: traversePath) {
            for(int i = 0; i < node.length; i++){
                for(int j = 0; j < node[0].length; j++){
                    System.out.print(node[i][j] + " ");
                }
                System.out.println();
                count++;
            }
            System.out.println("------");
        }
        System.out.println(String.format("Traversing Nodes: %s\nTotal Time: %s milliseconds\n======", count, time));
    }
}
