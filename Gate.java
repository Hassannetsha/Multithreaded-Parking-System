
import java.util.List;

public class Gate {
    private final int gateNumber;
    // private static int numberOfCars = 0;
    List<Car> cars;
    List<Thread> CarThreads;
    public Gate(ParkingLot parkingLot,int gateNumber,List<Car> cars) {
        this.gateNumber = gateNumber;
        this.cars = cars;
        for (Car elem : cars) {
            CarThreads.add(new Thread(new CarRunnable(parkingLot,elem)));
        }
    }
    public void increment(Car car){
        cars.add(car);
    }
    public int getGateNumber() {
        return gateNumber;
    }

    public int getNumberOfCars() {
        // return numberOfCars;
        return cars.size();
    }
}