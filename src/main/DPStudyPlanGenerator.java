import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DPStudyPlanGenerator {

    private int N, M;
    private int[] time, questions;

    private int[][] memo;

    private List<Integer> toBeStudied;

    /**
     * Empty constructor
     */
    public DPStudyPlanGenerator() { }

    /**
     * Constructor used for testing only.
     *
     * @param n - number of hours to study
     * @param m - number of topics
     * @param time - time it takes to study each chapter
     * @param questions - number of questions per chapter
     */
    public DPStudyPlanGenerator(int n, int m, int[] time, int[] questions) {
        N = n;
        M = m;
        this.time = time;
        this.questions = questions;
        memo = new int[N+1][M+1];
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public int getM() {
        return M;
    }

    /**
     * Setter for M
     * @param m - the value to be set
     * @throws RuntimeException if M <= 0
     */
    public void setM(int m) throws RuntimeException{
        if(m <= 0)
            throw new RuntimeException("M must be positive");
        M = m;
    }

    /**
     * Converts array of String in array of Integer
     *
     * @param time - String[] contains the values to be set
     */
    public void setTime(String[] time) {
        this.time = new int[time.length + 1];
        int pos = 1;
        for (String num: time) {
            this.time[pos++] = Integer.parseInt(num);
        }
    }

    /**
     * Converts array of String in array of Integer
     *
     * @param questions - String[] contains the values to be set
     */
    public void setQuestions(String[] questions) {
        this.questions = new int[questions.length + 1];
        int pos = 1;
        for (String num: questions) {
            this.questions[pos++] = Integer.parseInt(num);
        }
    }

    /**
     * Getter for the chapters to be studied.
     *
     * @return list of integer
     */
    public List<Integer> getToBeStudied() {
        return toBeStudied;
    }

    public int[] getTime() {
        return time;
    }

    public int[] getQuestions() {
        return questions;
    }

    public void calculateOptimalPlan() {
        this.memo = new int[N+1][M+1];
        for(int j = 1; j <= M; j++) {
            for(int i = 1; i<= N; i++) {
                if(i >= time[j])
                  memo[i][j] = Math.max(memo[i][j-1], memo[Math.max(0, i-time[j])][j-1] + questions[j]);
                else
                    memo[i][j] = memo[i][j-1];
            }
        }

        //backtrack to find the optimal solution
        int currentHour = N;
        toBeStudied = new ArrayList<>();
        for(int j = M; j>=1 ; j--) {
            if(memo[currentHour][j] == memo[Math.max(0, currentHour-time[j])][j-1] + questions[j]) {
                toBeStudied.add(j);
                currentHour = Math.max(0, currentHour - time[j]);
            }
        }
        toBeStudied.sort(Comparator.naturalOrder());
    }

    public void printOptimalPlan() {
        System.out.println();

        if(toBeStudied == null) {
            System.out.println("Plan was not calculated.");
            return;
        }

        System.out.println("You should study the following chapters: ");
        for(int n : toBeStudied)
            System.out.println("[ ] Chapter " + n);
    }
}
