import java.util.List;

public class Report {
    private String name;
    private String difficulty;
    private int time;
    private int space;
    private long executionTime;
    private Result result;

    public Report(){}

    public Report(String name, String difficulty, int time, int space, long executionTime, Result result) {
        this.name = name;
        this.difficulty = difficulty;
        this.time = time;
        this.space = space;
        this.executionTime = executionTime;
        this.result = result;
    }

    public void generateReport(List<Report> reports){
        System.out.println(String.format("%-10s|%-10s|%-10s|%-10s|%-10s|", "Algorithm","Length","Cost", "Time", "Space"));
        for(Report report : reports){
//            System.out.println(difficulty);

            System.out.println(String.format("%-10s|%10d|%10d|%10d|%10d|",
                    report.name,
                    report.result.getSolution().getDepth(),
                    report.result.getSolution().getG(),
                    report.time,
                    report.space));
        }
    }
}
