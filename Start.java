
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Start {
    private static ParkingLot parkingLot;
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        parkingLot = new ParkingLot(4);
        ReadFile.Read();
        Car car = new Car(0,0,4);
        List<Car>cars  = new ArrayList<>();
        cars.add(car);
        Gate gate = new Gate(parkingLot, 1, cars);
        Thread gateRun = new Thread(new GateRunnable(gate));
        gateRun.start();
    }
}
