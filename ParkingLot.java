
import java.io.FileNotFoundException;

public class ParkingLot {

    private final semaphore parkingSpots;
    private int currentCars = 0;
    private int totalCarsServed = 0;

    public ParkingLot(int spots) throws FileNotFoundException {
        parkingSpots = new semaphore(spots);

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
