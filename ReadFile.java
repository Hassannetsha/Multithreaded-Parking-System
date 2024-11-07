import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
    public static void Read() throws FileNotFoundException{
        Gate[] gates = new Gate[3];
        File input = new File("Input.txt");
        try (Scanner scanner = new Scanner(input)) {
            String line;
            String[] parts,wordParts;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                parts = line.split(", ");
                for (String elem : parts) {
                    wordParts = elem.split(" ");
                    
                }
            }
        }
    }
}

