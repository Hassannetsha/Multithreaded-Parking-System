
import java.io.FileNotFoundException;
import java.util.concurrent.Semaphore;

public class ParkingLot {

    private final Semaphore parkingSpots;
    private int currentCars = 0;
    private int totalCarsServed = 0;

    public ParkingLot(int spots) throws FileNotFoundException {
        parkingSpots = new Semaphore(spots, true);

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

}
