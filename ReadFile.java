
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
    public static Gate[] gates = new Gate[3];
    public static int MaxTime = 0;

    public static List<Thread> Read(ParkingLot parkingLot, Start.InnerReadFile innerReadFile)
            throws FileNotFoundException {

        // for (Gate elem : gates) {
        // elem = null;
        // }
        File input = new File("Input.txt");
        try (Scanner scanner = new Scanner(input)) {
            String line;
            // String[] parts,wordParts;
            while (scanner.hasNextLine()) {
                String gateNumber = "", carNumber = "", ArriveTime = "", duration = "";
                line = scanner.nextLine();
                int ctn = 0;
                for (int i = 0; i < line.length(); i++) {
                    if (Character.isDigit(line.charAt(i))) {
                        ctn++;
                        switch (ctn) {
                            case 1 -> {
                                int j = i;
                                while (j < line.length() && Character.isDigit(line.charAt(j))) {
                                    gateNumber += line.charAt(j);
                                    j++;
                                }
                                i = j;
                                break;
                            }
                            case 2 -> {
                                int j = i;
                                while (j < line.length() && Character.isDigit(line.charAt(j))) {
                                    carNumber += line.charAt(j);
                                    j++;
                                }
                                i = j;
                                break;
                            }
                            case 3 -> {
                                int j = i;
                                while (j < line.length() && Character.isDigit(line.charAt(j))) {
                                    ArriveTime += line.charAt(j);
                                    j++;
                                }
                                i = j;
                                break;
                            }
                            case 4 -> {
                                int j = i;
                                while (j < line.length() && Character.isDigit(line.charAt(j))) {
                                    duration += line.charAt(j);
                                    j++;
                                }
                                i = j;
                                break;
                            }
                            default ->
                                throw new AssertionError();
                        }
                    }
                }
                int gateNumberConvert = Integer.parseInt(gateNumber) - 1,
                        carNumberConvert = Integer.parseInt(carNumber),
                        ArriveTimeConvert = Integer.parseInt(ArriveTime), durationConvert = Integer.parseInt(duration);
                MaxTime = Math.max(MaxTime, durationConvert);
                if (gates[gateNumberConvert] == null) {
                    gates[gateNumberConvert] = new Gate(parkingLot, gateNumberConvert,
                            new Car(carNumberConvert, ArriveTimeConvert, durationConvert));
                } else {
                    gates[gateNumberConvert].increment(new Car(carNumberConvert, ArriveTimeConvert, durationConvert));
                }
                innerReadFile.counter++;
                // for (String elem : parts) {
                // for (int i = 0; i < parts.length; i++) {
                // wordParts = parts[0].split(" ");
                // switch (i) {
                // case 0:
                // gateNumber = ;
                // break;
                // case 1:
                // break;
                // default:
                // throw new AssertionError();
                // }
                // }
                // wordParts = elem.split(" ");
            }
            List<Thread> gateRunnable = new ArrayList<>();
            for (Gate elem : gates) {
                if (elem != null) {
                    elem.addCarThreads(parkingLot);
                    gateRunnable.add(new Thread(new GateRunnable(elem)));
                }
            }
            return gateRunnable;
        }
    }
}
