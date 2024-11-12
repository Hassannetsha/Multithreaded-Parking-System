
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile {
    public static Gate[] gates = new Gate[3];
    public static int MaxTime = 0;

    public static List<Thread> Read(ParkingLot parkingLot, Main.InnerReadFile innerReadFile)
            throws FileNotFoundException {
        File input = new File("Input.txt");
        try (Scanner scanner = new Scanner(input)) {
            String line;
            while (scanner.hasNextLine()) {
                String gateNumber = "", carNumber = "", ArriveTime = "", duration = "";
                line = scanner.nextLine();
                int ctn = 0;
                for (int i = 0; i < line.length(); i++) {
                    if (Character.isDigit(line.charAt(i))) {
                        ctn++;
                        switch (ctn) {
                            case 1 -> {
                                gateNumber = parseDigits(line, i);
                                i += gateNumber.length();
                                break;
                            }
                            case 2 -> {
                                carNumber = parseDigits(line, i);
                                i += carNumber.length();
                                break;
                            }
                            case 3 -> {
                                ArriveTime = parseDigits(line, i);
                                i += ArriveTime.length();
                                break;
                            }
                            case 4 -> {
                                duration = parseDigits(line, i);
                                i += duration.length();
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

    private static String parseDigits(String line, int startIndex) {
        String result = "";
        int j = startIndex;
        while (j < line.length() && Character.isDigit(line.charAt(j))) {
            result += line.charAt(j);
            j++;
        }
        return result;
    }
}
