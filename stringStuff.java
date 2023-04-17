import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
* This program reads and outputs to text files.
*
* @author  Jedidiah Alfred
* @version 1.0
* @since   2023-04-08
*/

public final class stringStuff {
    /**
    * This is a private constructor used to satisfy the
    * style checker.
    *
    * @exception IllegalStateException Utility Class
    */
    private stringStuff() {
        throw new IllegalStateException("Utility Class");
    }
    /**
    * This is the main method.
    *
    * @param args Unused
    */

    public static void main(String[] args) {
        try {
            // File to take in the input.
            final File input = new File("input.txt");
            final Scanner inputScanner = new Scanner(input);

            // File to print out the output.
            final FileWriter output = new FileWriter("output.txt");

            // Array variables
            String list = "";
            int counter = 0;
            final ArrayList<String> lists = new ArrayList<>();

            while (inputScanner.hasNextLine()) {

                // Getting next line
                list = inputScanner.nextLine();

                // adding more lines to the Arraylist
                lists.add(list);
            }

            // creating an array of strings
            final String[] listArr = new String[lists.size()];

            // putting lines in the new array
            for (String location : lists) {
                listArr[counter] = location;
                counter++;
            }

            // Function call
            final String[] blowUps = blowUp(listArr);

            // test the max runs
            final String[] maxRuns = maxRun(blowUps);

            // Writing the blowups max runs  to the output file
            for (String test : blowUps) {
                output.write(test + "\n");
            }
            for (String runs : maxRuns) {
                output.write(runs + " \n");
            }
            output.close();
        } catch (IOException err) {
            System.out.println("ERROR: " + err);
        }
    }
    /**
    * This function will read and output to text files.
    *
    * @author  Jedidiah Alfred
    * @version 1.0
    * @param lines Necessary for string manipulation
    * @return The string max runs
    * @since   2023-04-08
    */
    public static String[] maxRun(String[] lists) {

        // Create the array and return it
        final String[] newLists = new String[lists.length];
        newLists[0] = "\n\nOutput max runs:";

        // initialize variables
        int counter1 = 1;
        int listCounter = 0;

        // Loop through the list
        for (String list : lists) {
            if (listCounter != 0) {

                // initialize variables
                int fstRun = 1;
                int lstRun = 0;
                int counter2 = 0;
                char charEnd;

                // Create an array to split up the characters
                final char[] characters = list.toCharArray();
                charEnd = characters[0];

                // looping through the current String
                for (char character : characters) {
                    if (counter2 != characters.length - 1) {
                        if (character == charEnd) {
                            lstRun++;
                        } else {
                            if (lstRun >= fstRun) {
                                fstRun = lstRun;
                            }
                            lstRun = 1;
                        }
                        counter2++;
                        charEnd = character;
                    } else {

                        // seeing if there are any runs
                        if (character == charEnd) {
                            lstRun++;
                            if (lstRun > fstRun) {
                                fstRun = lstRun;
                            }
                        } else {
                            if (lstRun > fstRun) {
                                fstRun = lstRun;
                            }
                        }
                    }
                }

                // Adding to the arraylist
                newLists[counter1] = Integer.toString(fstRun);
                counter1++;
            }
            listCounter++;
        }

        // Returning to main
        return newLists;
    }
    /**
    * This function will read and output to text files.
    *
    * @author  Jedidiah Alfred
    * @version 1.0
    * @param lists Necessary for string manipulation
    * @return The string blow ups
    * @since   2023 04 8
    */

    public static String[] blowUp(String[] lists) {

        // This is the array to be returned to main
        final String[] newLists = new String[lists.length + 1];
        newLists[0] = "Output blow up:";

        // initialize variables
        int counter1 = 1;
        int charNum = 0;
        boolean varNum = false;

        // Loop through the array of lists 
        for (String list : lists) {

            // initialize variables
            int counter2 = 0;
            String newCharacter = "";

            // splitting the string into character arrays
            final char[] characters = list.toCharArray();

            // New arraylist to hold new character list
            final ArrayList<String> newList = new ArrayList<>();

            // Loop through the character array
            for (char character : characters) {
                if (counter2 != characters.length - 1) {

                    // Check if the character is a number.
                    try {

                        // Parsing string to int
                        charNum = Integer.parseInt(String.valueOf(character));

                        // After parsing, the boolean will decide
                        // what to do to it
                        varNum = true;
                    } catch (NumberFormatException error) {

                        // If there is an error, then the boolean
                        // will remain false
                        varNum = false;
                    }
                    if (varNum) {

                        // If the input is a number, then we will loop
                        // through the characters the same amount 
                        // as the input.
                        for (int i = 0; i < charNum; i++) {

                            // Add characters to a newList
                            newList.add(
                                String.valueOf(characters[counter2 + 1])
                            );
                        }
                    } else {

                        // add characters to a newList
                        newList.add(String.valueOf(character));
                    }
                } else {

                    // Add characters to a newList
                    newList.add(String.valueOf(character));
                }
                counter2++;
            }

            // Repeat the same process through the list
            for (String set : newList) {

                // Creating a new String from the places in the List
                newCharacter += set;
            }

            // Adding the new Sentence to the array we are returning to main
            newLists[counter1] = newCharacter;
            counter1++;
        }

        // Returning to main
        return newLists;
    }
}
