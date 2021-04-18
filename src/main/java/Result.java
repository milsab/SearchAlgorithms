import java.util.*;

public class Result {
    private Node solution;
    private List<int[][]> traversePath;
    private int time;
    private int space;
    private long executionTime;


    public Result(Node solution, List<int[][]> traversePath, int time, int space, long executionTime) {
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
        System.out.println("Length: " + solution.getDepth());
        System.out.println("Cost: " + solution.getG());
        System.out.println("Time: " + time);
        System.out.println("Space: " + solution.getDepth());
        System.out.println("Execution Time: " + executionTime + " milliseconds");
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
