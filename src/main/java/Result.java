import java.util.*;

public class Result {


    private String name;    // name of algorithm
    private DIFFICULTY difficulty;
    private Node solution;
    private List<int[][]> traversePath;
    private int time;
    private int space;
    private long executionTime;
    public Result(){

    }

    public Result(Node solution, List<int[][]> traversePath, int time, int space, long executionTime) {
        this.solution = solution;
        this.traversePath = traversePath;
        this.time = time;
        this.space = space;
        this.executionTime = executionTime;
    }

    public Result(String name, DIFFICULTY difficulty, Node solution, List<int[][]> traversePath, int time, int space, long executionTime) {
        this.name = name;
        this.difficulty = difficulty;
        this.solution = solution;
        this.traversePath = traversePath;
        this.time = time;
        this.space = space;
        this.executionTime = executionTime;
    }

    public void displayResult(){

        for (int[][] node: traversePath) {
            for(int i = 0; i < node.length; i++){
                for(int j = 0; j < node[0].length; j++){
                    System.out.print(node[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("------");
        }
        System.out.println("Solution From Start to Goal: " + solution.getPathFromStart());
        System.out.println("Length: " + solution.getDepth());
        System.out.println("Cost: " + solution.getG());
        System.out.println("Time: " + time);
        System.out.println("Space: " + solution.getDepth());
        System.out.println("Execution Time: " + executionTime + " milliseconds");

    }

    public void generateReport(HashMap<DIFFICULTY, List<Result>> results){
        System.out.println(String.format("%-10s|%10s|%10s|%10s|%10s|%20s|", "Algorithm","Length","Cost", "Time", "Space", "Execution Time"));

        List<Result> additionalReport = new ArrayList<>();

        for (DIFFICULTY difficulty : results.keySet()) {
            System.out.println("==========");
            System.out.println(difficulty);
            System.out.println("==========");
            for(Result result : results.get(difficulty)){
                System.out.println(String.format("%-10s|%10d|%10d|%10d|%10d|%20d|",
                        result.name,
                        result.getSolution().getDepth(),
                        result.getSolution().getG(),
                        result.time,
                        result.space,
                        result.executionTime));

                if(result.name.equals("BFS") || result.name.equals("A*2") || result.name.equals("A*3") )
                    additionalReport.add(result);
            }


        }
        System.out.println("\n\nAdditional Reports:");
        for(Result addRep : additionalReport){
            System.out.println(addRep.name);
            System.out.println(addRep.getSolution().getPathFromStart());

        }
    }


    //region Getters & Setters
    public Node getSolution() {
        return solution;
    }

    public void setSolution(Node solution) {
        this.solution = solution;
    }

    public List<int[][]> getTraversePath() {
        return traversePath;
    }

    public void setTraversePath(List<int[][]> traversePath) {
        this.traversePath = traversePath;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    //endregion
}
