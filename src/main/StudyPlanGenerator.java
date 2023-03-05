import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudyPlanGenerator {

    private int N, M, partial = -1;
    private int[] time, questions;

    private Chapter[] chapters;

    private List<Integer> toBeStudied;

    /**
     * Empty constructor
     */
    public StudyPlanGenerator() { }

    /**
     * Constructor used for testing only.
     *
     * @param n - number of hours to study
     * @param m - number of topics
     * @param time - time it takes to study each chapter
     * @param questions - number of questions per chapter
     */
    public StudyPlanGenerator(int n, int m, int[] time, int[] questions) {
        N = n;
        M = m;
        this.time = time;
        this.questions = questions;
    }

    /**
     * Creates a list of chapters and sorts them descending based on Ki / Ti
     */
    public void createChapters() {
        chapters = new Chapter[M + 1];
        for(int i = 1; i <= M; i++) {
            chapters[i] = new Chapter(i, questions[i], time[i]);
        }
        Arrays.sort(chapters, 1, M+1);
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
        chapters = new Chapter[m+1];
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

    /**
     * Getter for the partial chapter to be studied.
     *
     * @return number of chapter or -1 is no such chapter exists.
     */
    public int getPartial() {
        return partial;
    }

    public int[] getTime() {
        return time;
    }

    public int[] getQuestions() {
        return questions;
    }

    /**
     * Instantiates toBeStudied with a list of the chapter numbers.
     * Instantiates partial with the number of the chapter, if it exists.
     */
    public void calculateOptimalPlan() {
        toBeStudied = new ArrayList<>();
        Chapter current = null;
        int totalStudiedHours = 0;

        for (int i = 1; i <= M && totalStudiedHours < N; i++) {
            current = chapters[i];

            if(totalStudiedHours + current.numberOfHours() <= N) {
                toBeStudied.add(current.numberOfChapter());
                totalStudiedHours += current.numberOfHours();
            }
            else if(partial == -1)
                partial = current.numberOfChapter();
        }

        if(current != null && totalStudiedHours == N)
            partial = -1;
    }

    /**
     * Prints the result of the optimal plan, if it was calculated before.
     */
    public void printOptimalPlan() {
        System.out.println();

        if(toBeStudied == null) {
            System.out.println("Plan was not calculated.");
            return;
        }

        System.out.println("You should study the following chapters: ");
        for(int n : toBeStudied)
            System.out.println("[ ] Chapter " + n);
        if(partial != -1) {
            System.out.println("[ ] Part of chapter " + partial);
        }
    }
}
