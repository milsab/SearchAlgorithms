import java.util.*;

public class Main {

    static HashMap<DIFFICULTY, List<Result>> results = new HashMap<>();

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

        Node easyNode = new Node(easyRoot, 2, 1, 0, 0, "Start ");
        Node mediumNode = new Node(mediumRoot, 1, 0, 0, 0, "Start ");
        Node hardNode = new Node(hardRoot, 1, 1, 0, 0, "Start ");

        Scanner scan = new Scanner(System.in);
        int choice = -1;
        printMenu();
        choice = scan.nextInt();
        while(choice != 9){
            switch (choice){
                case 1:
                    BreadthFirstSearch bfs = new BreadthFirstSearch();
                    Result bfsEasyResult = bfs.search(easyNode, goal, DIFFICULTY.EASY);
                    bfsEasyResult.displayResult(DIFFICULTY.EASY);

                    System.out.println("\n Enter 0 to Search from the next Start node...");
                    scan.next();

                    bfs = new BreadthFirstSearch();
                    Result bfsMediumResult = bfs.search(mediumNode, goal, DIFFICULTY.MEDIUM);
                    bfsMediumResult.displayResult(DIFFICULTY.MEDIUM);

                    System.out.println("\n Enter 0 to Search from the next Start node...");
                    scan.next();

                    bfs = new BreadthFirstSearch();
                    Result bfsHardResult = bfs.search(hardNode, goal, DIFFICULTY.HARD);
                    bfsHardResult.displayResult(DIFFICULTY.HARD);
                    break;
                case 2:
                    DepthFirstSearch dfs = new DepthFirstSearch();
                    Result dfsEasyResult = dfs.search(easyNode, goal, DIFFICULTY.EASY);
                    dfsEasyResult.displayResult(DIFFICULTY.EASY);

                    System.out.println("\n Press any key to Search from the next Start node...");
                    scan.next();

                    dfs = new DepthFirstSearch();
                    Result dfsMediumResult = dfs.search(mediumNode, goal, DIFFICULTY.MEDIUM);
                    dfsMediumResult.displayResult(DIFFICULTY.MEDIUM);

                    System.out.println("\n Press any key to Search from the next Start node...");
                    scan.next();

                    dfs = new DepthFirstSearch();
                    Result dfsHardResult = dfs.search(hardNode, goal, DIFFICULTY.HARD);
                    dfsHardResult.displayResult(DIFFICULTY.HARD);
                    break;
                case 3:
                    UniformCostSearch usc = new UniformCostSearch();
                    Result uscEasyResult = usc.search(easyNode, goal, DIFFICULTY.EASY);
                    uscEasyResult.displayResult(DIFFICULTY.EASY);

                    System.out.println("\n Press any key to Search from the next Start node...");
                    scan.next();

                    usc = new UniformCostSearch();
                    Result uscMediumResult = usc.search(mediumNode, goal, DIFFICULTY.MEDIUM);
                    uscMediumResult.displayResult(DIFFICULTY.MEDIUM);

                    System.out.println("\n Press any key to Search from the next Start node...");
                    scan.next();

                    usc = new UniformCostSearch();
                    Result uscHardResult = usc.search(hardNode, goal, DIFFICULTY.HARD);
                    uscHardResult.displayResult(DIFFICULTY.HARD);
                    break;
                case 4:
                    BestFirstSearch gbf = new BestFirstSearch();
                    Result gbfEasyResult = gbf.search(easyNode, goal, DIFFICULTY.EASY);
                    gbfEasyResult.displayResult(DIFFICULTY.EASY);

                    System.out.println("\n Press any key to Search from the next Start node...");
                    scan.next();

                    gbf = new BestFirstSearch();
                    Result gbfMediumResult = gbf.search(mediumNode, goal, DIFFICULTY.MEDIUM);
                    gbfMediumResult.displayResult(DIFFICULTY.MEDIUM);

                    System.out.println("\n Press any key to Search from the next Start node...");
                    scan.next();

                    gbf = new BestFirstSearch();
                    Result gbfHardResult = gbf.search(hardNode, goal, DIFFICULTY.HARD);
                    gbfHardResult.displayResult(DIFFICULTY.HARD);
                    break;
                case 5:
                    A_StarSearch as1 = new A_StarSearch();
                    Result as1EasyResult = as1.search(easyNode, goal, (byte) 1, DIFFICULTY.EASY);
                    as1EasyResult.displayResult(DIFFICULTY.EASY);

                    System.out.println("\n Press any key to Search from the next Start node...");
                    scan.next();

                    as1 = new A_StarSearch();
                    Result as1MediumResult = as1.search(mediumNode, goal, (byte) 1, DIFFICULTY.MEDIUM);
                    as1MediumResult.displayResult(DIFFICULTY.MEDIUM);

                    System.out.println("\n Press any key to Search from the next Start node...");
                    scan.next();

                    as1 = new A_StarSearch();
                    Result as1HardResult = as1.search(hardNode, goal, (byte) 1, DIFFICULTY.HARD);
                    as1HardResult.displayResult(DIFFICULTY.HARD);
                    break;
                case 6:
                    A_StarSearch as2 = new A_StarSearch();
                    Result as2EasyResult = as2.search(easyNode, goal, (byte) 2, DIFFICULTY.EASY);
                    as2EasyResult.displayResult(DIFFICULTY.EASY);

                    System.out.println("\n Press any key to Search from the next Start node...");
                    scan.next();

                    as2 = new A_StarSearch();
                    Result as2MediumResult = as2.search(mediumNode, goal, (byte) 2, DIFFICULTY.MEDIUM);
                    as2MediumResult.displayResult(DIFFICULTY.MEDIUM);

                    System.out.println("\n Press any key to Search from the next Start node...");
                    scan.next();

                    as2 = new A_StarSearch();
                    Result as2HardResult = as2.search(hardNode, goal, (byte) 2, DIFFICULTY.HARD);
                    as2HardResult.displayResult(DIFFICULTY.HARD);
                    break;
                case 7:
                    A_StarSearch as3 = new A_StarSearch();
                    Result as3EasyResult = as3.search(easyNode, goal, (byte) 3, DIFFICULTY.EASY);
                    as3EasyResult.displayResult(DIFFICULTY.EASY);

                    System.out.println("\n Press any key to Search from the next Start node...");
                    scan.next();

                    as3 = new A_StarSearch();
                    Result as3MediumResult = as3.search(mediumNode, goal, (byte) 3, DIFFICULTY.MEDIUM);
                    as3MediumResult.displayResult(DIFFICULTY.MEDIUM);

                    System.out.println("\n Press any key to Search from the next Start node...");
                    scan.next();

                    as3 = new A_StarSearch();
                    Result as3HardResult = as3.search(hardNode, goal, (byte) 3, DIFFICULTY.HARD);
                    as3HardResult.displayResult(DIFFICULTY.HARD);
                    break;
                case 8:
                    Result allResult = new Result();
                    allResult.generateReport(results);
                    break;
                default:
                    System.out.println("Invalid Choice! Please choose a number between 1 to 9.");
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
                "2) Depth First Search\n" +
                "3) Uniform Cost Search\n" +
                "4) Best First Search\n" +
                "5) A* (h1)\n" +
                "6) A* (h2)\n" +
                "7) A* (h3)\n" +
                "8) Report\n" +
                "9) Exit\n");
    }
}
