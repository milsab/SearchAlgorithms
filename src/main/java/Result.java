import java.util.*;

public class Result {
    List<int[][]> traversePath;
    long time;

    public Result(List<int[][]> traversePath, long time) {
        this.traversePath = traversePath;
        this.time = time;
    }

    public List<int[][]> getTraversePath() {
        return traversePath;
    }

    public void setTraversePath(List<int[][]> traversePath) {
        this.traversePath = traversePath;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
