import java.util.HashMap;

public class Node {

    //region Private Attributes
    private int g;  // cost
    private int h;  // Heuristic value
    private int f;  // f = g + h
    private int depth; // the depth(length) of each node from root node (root depth = 0)

    private int[][] rep = new int[][]{}; // representation of the node as a 2d array
    private int row, col;   // the location of the blank tile (represented as 0)

    private String pathFromStart;   // shows a path (sequence of actions) from start to the current node

    //endregion

    //region Constructors
    public Node() {
    }

    // use for DFS and BFS Algorithms
    public Node(int[][] rep, int row, int col, int cost, int depth, String pathFromStart) {
        this.rep = rep;
        this.row = row;
        this.col = col;
        this.g = cost;
        this.depth = depth;
        this.pathFromStart = pathFromStart;
    }

    // use for GBF (Greedy Best Search First) algorithm
    public Node(int[][] rep, int row, int col, int cost, int heuristic, int depth, String pathFromStart) {
        this.rep = rep;
        this.row = row;
        this.col = col;
        this.g = cost;
        this.h = heuristic;
        this.depth = depth;
        this.pathFromStart = pathFromStart;
    }

    // use for A* algorithm
    public Node(int[][] rep, int row, int col, int cost, int heuristic, int f, int depth, String pathFromStart) {
        this.rep = rep;
        this.row = row;
        this.col = col;
        this.g = cost;
        this.h = heuristic;
        this.f = f;
        this.depth = depth;
        this.pathFromStart = pathFromStart;
    }

    //endregion

    //region Public Methods

    // check if the node is the final goal or not
    public boolean isGoal(int[][] goal){
        for(int i = 0; i < rep.length; i++){
            for(int j = 0; j < rep[0].length; j++){
                if(rep[i][j] != goal[i][j])
                    return false;
            }
        }
        return true;
    }

    // Calculate the heuristic function h1(n) = No. of misplaced tiles relative to the goal
    public int calculateH1(int[][] goal){
        int count = 0; // number of misplaced tiles related to the goal

        for(int i = 0; i < rep.length; i++) {
            for(int j = 0; j < rep[0].length; j++){
                if(rep[i][j] != goal[i][j])
                    count++;
            }
        }
        setH(count);
        return h;
    }

    // Calculate the heuristic function h2(n) = sum of Manhattan distances between all tiles and their correct positions
    public int calculateH2(HashMap<Integer, int[]> goalMap){
        int manhattanDistance = 0;
        for(int i = 0; i < rep.length; i++) {
            for(int j = 0; j < rep[0].length; j++){
                int currentRow = i;
                int currentCol = j;
                int targetRow = goalMap.get(rep[i][j])[0];
                int targetCol = goalMap.get(rep[i][j])[1];

                manhattanDistance += Math.abs(targetRow - currentRow) + Math.abs(targetCol - currentCol);
            }
        }
        return manhattanDistance;
    }

    // Calculate the heuristic function h3(n) = sum of Euclidean distances between all tiles and their correct positions
    public int calculateH3(HashMap<Integer, int[]> goalMap){
        int euclideanDistance = 0;
        for(int i = 0; i < rep.length; i++) {
            for(int j = 0; j < rep[0].length; j++){
                int currentRow = i;
                int currentCol = j;
                int targetRow = goalMap.get(rep[i][j])[0];
                int targetCol = goalMap.get(rep[i][j])[1];

                euclideanDistance += Math.sqrt(Math.pow(targetRow - currentRow, 2) + Math.pow(targetCol - currentCol, 2));
            }
        }
        return euclideanDistance;
    }

    // serialize the 2d-array representation of the node to a simple string
    public String serialize(){
        String result = "";
        for(int i = 0; i < rep.length; i++){
            for(int j = 0; j < rep[0].length; j++){
                result += rep[i][j];
            }
        }
        return result;
    }

    // generate a deep copy of a node representation
    public int[][] copy(){
        int[][] newRep = new int[rep.length][rep[0].length];
        for(int i = 0; i < rep.length; i++){
            newRep[i] = rep[i].clone();
        }
        return newRep;
    }
    //endregion

    //region Getter & Setters
    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int[][] getRep() {
        return rep;
    }

    public void setRep(int[][] rep) {
        this.rep = rep;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getPathFromStart() {
        return pathFromStart;
    }

    //endregion

}
