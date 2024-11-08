
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

public class Start {

    private static ParkingLot parkingLot;
    private static PriorityBlockingQueue<CarGatePair> entryQueue;
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {

        parkingLot = new ParkingLot(4);
        List<Thread> gateThreads = ReadFile.Read(parkingLot);
        // Car car = new Car(0,0,4);
        // List<Car>cars  = new ArrayList<>();
        // cars.add(car);
        // Gate gate = new Gate(parkingLot, 1, cars);
        // Thread gateRun = new Thread(new GateRunnable(gate));
        // int i = 0;
        // Thread startThread = gateThreads.get(i++);
        // startThread.start();
        // Thread.sleep(100);
        // for (; i < gateThreads.size(); i++) {
        //     startThread = gateThreads.get(i);
        //     startThread.start();
        //     Thread.sleep(100);
        // }
        for (Thread gateThread : gateThreads) {
            gateThread.start();
        }
        entryQueue = Singleton.getInstance();
        // Manage car entry from the queue
        while (!entryQueue.isEmpty()) {
            CarGatePair carGate = entryQueue.take(); // Retrieve the next car in arrival order
            Thread thread = 
            new Thread(new CarRunnable(parkingLot, carGate.getCar(),carGate.getGate()));
            thread.start(); // Start the car thread
            // Thread.sleep(100); // Optional delay to simulate real-time entry intervals
        }
        // for (Thread elem : gateThreads) {
        //     elem.start();
        // }
        // gateRun.start();
    }
}
