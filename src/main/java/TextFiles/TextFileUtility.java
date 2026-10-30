package TextFiles;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class TextFileUtility {

    // Method to read a text file line by line
    public static void readFile(String filePath) throws IOException {

        // Opens the file for reading using BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;

            // Reads each line until the end of the file
            while ((line = br.readLine()) != null) {

                // Displays the current line
                System.out.println(line);
            }
        }
    }

    // Method to write new content into a text file
    public static void writeFile(String filePath, String content)
            throws IOException {

        // Opens the file for writing 
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {

            // Writes the given content into the file
            bw.write(content);

            // Moves the cursor to the next line
            bw.newLine();
        }
    }

    // Method to append new content without deleting existing content
    public static void appendFile(String filePath, String content)
            throws IOException {

        // Opens the file in append mode (true)
        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(filePath, true))) {

            // Adds new content at the end of the file
            bw.write(content);

            // Moves the cursor to the next line
            bw.newLine();
        }
    }
}