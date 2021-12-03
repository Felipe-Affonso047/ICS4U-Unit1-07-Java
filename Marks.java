/*
* This is a program generates marks
* after reading in 2 text files.
*
* @author  Felipe Garcia Affonso
* @version 1.0
* @since   2021-12-03
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
* This is the marks program.
*/
final class Marks {

    /**
    * Prevent instantiation
    * Throw an exception IllegalStateException.
    * if this ever is called
    *
    * @throws IllegalStateException
    *
    */
    private Marks() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
    * The generateMarks() function.
    *
    * @param arrayOfStudents the collection of students
    * @param arrayOfAssignments the collection of assignments
    * @return the generated marks
    */
    public static String[][] generateMarks(final String[] arrayOfStudents, 
                                       final String[] arrayOfAssignments) {
        // this is just a place holder!
        String[][] placeholderArray = { { "", "Ass #1", "Ass #2" }, 
                           { "Sue", "76%", "88%" },
                           { "Bob", "46%", "81%" } };

        final int stuNum = arrayOfStudents.length;
        final int assNum = arrayOfAssignments.length;

        String[][] markArray = new String[stuNum + 1][assNum + 1];

        int counter2 = 0;
        for (int counter1 = 1; counter1 < assNum; counter1++) {
            markArray[0][counter1] = arrayOfAssignments[counter2];
            counter2++;
        }
        markArray[0][assNum] = arrayOfAssignments[counter2];

        counter2 = 0;
        for (int counter1 = 1; counter1 < stuNum; counter1++) {
            markArray[counter1][0] = arrayOfStudents[counter2];
            counter2++;
        }
        markArray[stuNum][0] = arrayOfStudents[counter2];

        Random random = new Random();

        for (int counter = 1; counter <= stuNum; counter++) {
            for (int counter1 = 1; counter1 <= assNum; counter1++) {
                int mark = (int)Math.floor(random.nextGaussian()*10+75);
                markArray[counter][counter1] = String.valueOf(mark) + "%";
            }
        }
        for (int counter = 0; counter <= stuNum; counter++) {
            System.out.println(Arrays.toString(markArray[counter]));
        }

        return markArray;
    }

    /**
    * The starting main() function.
    *
    * @param args No args will be used
    */
    public static void main(final String[] args) {
        final ArrayList<String> listOfStudents = new ArrayList<String>();
        final ArrayList<String> listOfAssingments = new ArrayList<String>();
        final Path studentFilePath = Paths.get("./", args[0]);
        final Path assignmentFilePath = Paths.get("./", args[1]);
        final Charset charset = Charset.forName("UTF-8");

        try (BufferedReader readerStudent = Files.newBufferedReader(
                                     studentFilePath, charset)) {
            String lineStudent = null;
            while ((lineStudent = readerStudent.readLine()) != null) {
                listOfStudents.add(lineStudent);
                System.out.println(lineStudent);
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        try (BufferedReader readerAssignment = Files.newBufferedReader(
                                     assignmentFilePath, charset)) {
            String lineAssignment = null;
            while ((lineAssignment = readerAssignment.readLine()) != null) {
                listOfAssingments.add(lineAssignment);
                System.out.println(lineAssignment);
            }
        } catch (IOException errorCode) {
            System.err.println(errorCode);
        }

        String[] studentArray = listOfStudents.toArray(new String[0]);
        String[] assignmentArray = listOfAssingments.toArray(new String[0]);

        String[][] temp = generateMarks(studentArray, assignmentArray);

        // Normal Distribution numbers
        Random random = new Random();

        for (int loopCounter = 0; loopCounter < 5; loopCounter++) {
            int mark = (int)Math.floor(random.nextGaussian()*10+75);
            System.out.println(mark);
        }

        // 

        System.out.println("\nDone.");
    }
}
