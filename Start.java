
import java.io.FileNotFoundException;
import java.util.List;

public class Start {

    private static ParkingLot parkingLot;

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        parkingLot = new ParkingLot(4);
        List<Thread> gateThreads = ReadFile.Read(parkingLot);
        // Car car = new Car(0,0,4);
        // List<Car>cars  = new ArrayList<>();
        // cars.add(car);
        // Gate gate = new Gate(parkingLot, 1, cars);
        // Thread gateRun = new Thread(new GateRunnable(gate));
        for (Thread elem : gateThreads) {
            elem.start();
        }
        // gateRun.start();
    }
}
