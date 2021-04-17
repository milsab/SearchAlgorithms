public class Node {

    //region Private Attributes
    private int g;  // cost
    private int h;  // Heuristic value
    private int f;  // f = g + h
    private int depth; // the depth(length) of each node from root node (root depth = 0)

    private int[][] rep = new int[][]{}; // representation od the node as a 2d array

    private int row, col;   // the location of the blank tile (represented as 0)
    //endregion

    //region Constructors
    public Node() {
    }

    public Node(int[][] rep, int row, int col) {
        this.rep = rep;
        this.row = row;
        this.col = col;
    }

    public Node(int[][] rep, int row, int col, int f) {
        this.rep = rep;
        this.row = row;
        this.col = col;
        this.f = f;
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
    //endregion

}
