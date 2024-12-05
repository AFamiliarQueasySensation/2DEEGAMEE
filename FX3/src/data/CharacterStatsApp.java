/**
 * Reads, Writes and Writes to sepcific line in a csv file
 * Needs to have parameter of type List <CharacterStats>, along with file Directory
 * and Optional Line to edit.
 * 
 * @author Grant Von Hagen
 * @email gvonhage@uwo.ca
 * @version 1.0
 */

package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * The Class CharacterStatsApp.
 */
public class CharacterStatsApp {

    /**
     * Write to CSV.
     *
     * @param fileName the file name
     * @param statsList the stats list
     * @throws IOException Signals that an I/O exception has occurred.
     */
    // Method to write data to CSV
    public static void writeToCSV(File fileName, List<CharacterStats> statsList) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        // Write the header line with all value fields
        writer.write("Value1,Value2,Value3,Value4,Value5,Value6,Value7,Value8,Value9,Value10,Value11,Value12,Value13,Value14");
        writer.newLine();

        // Write the data for each CharacterStats object
        for (CharacterStats stats : statsList) {
            writer.write(stats.getValue1() + "," +
                         stats.getValue2() + "," +
                         stats.getValue3() + "," +
                         stats.getValue4() + "," +
                         stats.getValue5() + "," +
                         stats.getValue6() + "," +
                         stats.getValue7() + "," +
                         stats.getValue8() + "," +
                         stats.getValue9() + "," +
                         stats.getValue10() + "," +
                         stats.getValue11() + "," +
                         stats.getValue12() + "," +
                         stats.getValue13() + "," +
                         stats.getValue14());
            writer.newLine();
        }

        writer.close();
    }
    
/**
 * Edits a particular line on the csv file with new data.
 *
 * @param fileName file path
 * @param lineNumber Line to edit
 * @param statsList the stats list
 * @throws IOException Signals that an I/O exception has occurred.
 */
    public static void modifyLine(File fileName, int lineNumber, List<CharacterStats> statsList) throws IOException {
        // Read all lines into memory
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        // Check if the lineNumber is valid
        if (lineNumber > 0 && lineNumber < lines.size()) {
            // Convert the statsList to a CSV string
            StringBuilder newLineData = new StringBuilder();
            for (CharacterStats stats : statsList) {
                newLineData.append(stats.getValue1()).append(",")
                            .append(stats.getValue2()).append(",")
                            .append(stats.getValue3()).append(",")
                            .append(stats.getValue4()).append(",")
                            .append(stats.getValue5()).append(",")
                            .append(stats.getValue6()).append(",")
                            .append(stats.getValue7()).append(",")
                            .append(stats.getValue8()).append(",")
                            .append(stats.getValue9()).append(",")
                            .append(stats.getValue10()).append(",")
                            .append(stats.getValue11()).append(",")
                            .append(stats.getValue12()).append(",")
                            .append(stats.getValue13()).append(",")
                            .append(stats.getValue14());

                // Only add a new line after each CharacterStats except for the last one
                if (stats != statsList.get(statsList.size() - 1)) {
                    newLineData.append("\n");
                }
            }

            // Replace the line in the file with the new data
            lines.set(lineNumber, newLineData.toString());
        } else {
            System.out.println("Invalid line number.");
            return;
        }

        // Write the updated list of lines back to the CSV file using try-with-resources
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String modifiedLine : lines) {
                writer.write(modifiedLine);
                writer.newLine();
            }
        }
    }
   
    /**
     * Read from CSV.
     *
     * @param fileName the file name
     * @return the list
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static List<CharacterStats> readFromCSV(File fileName) throws IOException {
        List<CharacterStats> statsList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        // Skip the header line
        reader.readLine(); 

        // Read each line of data and create a new CharacterStats object
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            
            if (values.length == 14) {
                String value1 = values[0];
                int value2 = Integer.parseInt(values[1]);
                int value3 = Integer.parseInt(values[2]);
                int value4 = Integer.parseInt(values[3]);
                int value5 = Integer.parseInt(values[4]);
                int value6 = Integer.parseInt(values[5]);
                int value7 = Integer.parseInt(values[6]);
                int value8 = Integer.parseInt(values[7]);
                int value9 = Integer.parseInt(values[8]);
                int value10 = Integer.parseInt(values[9]);
                int value11 = Integer.parseInt(values[10]);
                int value12 = Integer.parseInt(values[11]);
                int value13 = Integer.parseInt(values[12]);
                int value14 = Integer.parseInt(values[13]);
                
                // Create a new CharacterStats object and add it to the list
                statsList.add(new CharacterStats(value1, value2, value3, value4, value5, value6, value7, value8, value9, value10, value11, value12, value13, value14));
            }
        }

        reader.close();
        return statsList;
    }
}
