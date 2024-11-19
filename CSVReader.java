import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// class to read in the .csv file accounting for both embedded commas and quotation marks
public class CSVReader {
    public static void parseCSV(String filePath, RBTree tree) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNum = 0; // track line numbers for logging issues with malformed lines

            br.readLine(); // skip first line bc its a header

            // read the .csv file line by line
            while ((line = br.readLine()) != null) {
                lineNum++;
                // parse each line into fields
                String[] fields = parseLine(line);


                // check if the line is missing any fields
                if (fields == null ||fields.length > 4) {
                    System.out.println("Skipping malformed line " + lineNum + ": " + line);
                    continue;
                }

                // extract fields
                String productId = fields[0].trim();
                String name = fields[1].trim();
                String category = fields[2].trim();
                String price = fields[3].trim();

                // put all the fields into a node
            TreeNode node = new TreeNode(productId, name, category, price);

                // insert the node into the tree while checking for any missing info
                if(!(category.isEmpty())) {
                tree.insert(node);}


            }

        } catch (IOException e) {
            // handle file reading exceptions
            e.printStackTrace();
        }
    }

    // parses a line from the .csv file going character by character
    public static String[] parseLine(String line) {
        boolean insideQuotes = false; // tracks if in quotes
        StringBuilder field = new StringBuilder(); // accumulates characters for the current field
        String[] fields = new String[4]; // array to hold the 4 fields
        int fieldIndex = 0; // counts the index of the current field

        for (int i = 0; i < line.length(); i++) {
            char current = line.charAt(i);

            if (current == '"') {
                insideQuotes = !insideQuotes;  // toggle the insideQuotes state
            } else if (current == ',' && !insideQuotes) {
                // end of a field
                if (fieldIndex < fields.length) {
                    fields[fieldIndex++] = field.toString();
                }
                field.setLength(0); // reset the field builder for the next field
            } else {
                field.append(current); // add the current character to the field
            }
        }

        // add the last field after exiting the loop
        if (fieldIndex < fields.length) {
            fields[fieldIndex++] = field.toString();
        }

        // Ensure all 4 fields are filled; return null if fields are missing
        return fieldIndex == 4 ? fields : null;
    }
}