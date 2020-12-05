import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is so I don't have to keep copy pasting my File and Scanner
 * method to read each input text file.
 */
public class FileReader {
    private String source;

    public FileReader(String str) {
        source = str;
    }

    public Scanner getScanner() {
        try {
            File file = new File(source);
            return new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
        return null;
    }
}
