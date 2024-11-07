import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
    void Read() throws FileNotFoundException{
        File input = new File("Input.txt");
        try (Scanner scanner = new Scanner(input)) {
            while (scanner.hasNextLine()) {
                
            }
        }
    }
}

