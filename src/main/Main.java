import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws Exception {
        StudyPlanGenerator studyPlanGenerator = new StudyPlanGenerator();

        read(studyPlanGenerator, "src/resources/input.txt");

        studyPlanGenerator.createChapters();

        studyPlanGenerator.calculateOptimalPlan();

        studyPlanGenerator.printOptimalPlan();
    }

    public static void read(StudyPlanGenerator studyPlanGenerator, String path) throws Exception {
        File data = new File(path);

        BufferedReader br = new BufferedReader(new FileReader(data));

        String line, position = "N";
        int lineNo = 0;

        while((line = br.readLine()) != null) {
            lineNo++;

            // Skip comment and empty lines
            if(line.contains("#") || line.length() == 0)
                continue;

            String[] arr;
            // Read the input file
            switch (position) {
                case "N" -> {
                    studyPlanGenerator.setN(Integer.parseInt(line));
                    position = "M";
                }
                case "M" -> {
                    studyPlanGenerator.setM(Integer.parseInt(line));
                    position = "L";
                }
                case "L" -> {
                    // ignore L. Irrelevant for the current version of the problem
                    position = "T";
                }
                case "T" -> {
                    arr = line.split("\\s*,\\s*");
                    if (arr.length != studyPlanGenerator.getM())
                        throw new ParseException
                                ("Number of hours per topic does not match the number of chapters", lineNo);
                    studyPlanGenerator.setTime(arr);
                    position = "K";
                }
                case "K" -> {
                    // split the array based on
                    arr = line.split("\\s*,\\s*");

                    if (arr.length != studyPlanGenerator.getM())
                        throw new ParseException
                                ("Number of questions per topic does not match the number of chapters", lineNo);

                    studyPlanGenerator.setQuestions(arr);
                    position = "END";
                }
                case "END" -> {
                    return;
                }
                default -> throw new ParseException("Something unexpected happened. Make sure you are using the" +
                        "right template for the input file", lineNo);
            }

        }
        if(!position.equals("END"))
          throw new ParseException("Something unexpected happened. Make sure you are using the" +
                "right template for the input file", lineNo);
    }
}
