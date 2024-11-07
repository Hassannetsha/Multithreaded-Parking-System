
import java.io.FileNotFoundException;
import java.util.concurrent.Semaphore;

public class ParkingLot {

    private final Semaphore parkingSpots;
    private int currentCars = 0;
    private int totalCarsServed = 0;
    // private final Gate[] gates;
    // private List<Car> cars;

    public ParkingLot(int spots) throws FileNotFoundException {
        parkingSpots = new Semaphore(spots, true);
        // gates = new Gate[3];
        // ReadFile.Read();
        // cars.add(new Car(0,0,3));
        // for (int i = 0; i < 4; i++) {
        //     gates[0] = new Gate(this,2,cars);
        // }
    }

    public synchronized boolean tryParkCar() throws InterruptedException {
        if (parkingSpots.tryAcquire()) {
            currentCars++;
            totalCarsServed++;
            return true;
        }
        return false;
    }

    public synchronized void leaveSpot() {
        currentCars--;
        parkingSpots.release();
    }

    public synchronized int getCurrentCars() {
        return currentCars;
    }

    public int getTotalCarsServed() {
        return totalCarsServed;
    }

    // public void run(){
    // }
}
