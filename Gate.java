
import java.util.ArrayList;
import java.util.List;

public class Gate {

    private final int gateNumber;
    private final List<Car> cars;
    private final List<Thread> CarThreads;

    public Gate(ParkingLot parkingLot, int gateNumber, Car car) {
        this.gateNumber = gateNumber;
        CarThreads = new ArrayList<>();
        cars = new ArrayList<>();
        cars.add(car);
    }

    public void increment(Car car) {
        cars.add(car);
    }

    public void addCarThreads(ParkingLot parkingLot) {
        for (Car elem : cars) {
            CarThreads.add(new Thread(new CarRunnable(parkingLot, elem, this)));
        }
    }

    public int getGateNumber() {
        return gateNumber + 1;
    }

    public List<Car> getCars() {
        return this.cars;
    }

    public int getNumberOfCars() {
        return cars.size();
    }
}
