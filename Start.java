import java.io.FileNotFoundException;
import java.io.FileWriter;
// import java.io.FileWriter;
// import java.io.IOException;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

public class Start {
    public static class InnerReadFile {
        public int counter;

        public InnerReadFile() {
            this.counter = 0;
        }
    }

    private static ParkingLot parkingLot;
    private static PriorityBlockingQueue<CarGatePair> entryQueue;

    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        FileContentWriter startWriter = new FileContentWriter("Output.txt");

        startWriter.write(false, "");
        // try (FileWriter writer = new FileWriter("Output.txt")) {
        // writer.write("");
        // writer.flush();
        // } catch (IOException e) {

        // }

        parkingLot = new ParkingLot(4);
        InnerReadFile innerReadFile = new InnerReadFile();
        List<Thread> gateThreads = ReadFile.Read(parkingLot, innerReadFile);
        // Car car = new Car(0,0,4);
        // List<Car>cars = new ArrayList<>();
        // cars.add(car);
        // Gate gate = new Gate(parkingLot, 1, cars);
        // Thread gateRun = new Thread(new GateRunnable(gate));
        // int i = 0;
        // Thread startThread = gateThreads.get(i++);
        // startThread.start();
        // Thread.sleep(100);
        // for (; i < gateThreads.size(); i++) {
        // startThread = gateThreads.get(i);
        // startThread.start();
        // Thread.sleep(100);
        // }
        for (Thread gateThread : gateThreads) {
            gateThread.start();
            gateThread.join();
            // Thread.sleep(100);
        }
        entryQueue = Singleton.getInstance();
        // Manage car entry from the queue
        while (innerReadFile.counter-- > 0) {
            try {
                CarGatePair carGate = entryQueue.take(); // This will block if the queue is empty
                Thread thread = new Thread(new CarRunnable(parkingLot, carGate.getCar(), carGate.getGate()));
                thread.start();

                Thread.sleep(10);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted, stopping.");
                break;
            }
        }
        // while (!entryQueue.isEmpty()) {
        // System.out.println("Entry queue size: " + entryQueue.size());
        // CarGatePair carGate = entryQueue.take(); // Retrieve the next car in arrival
        // order
        // Thread thread =
        // new Thread(new CarRunnable(parkingLot, carGate.getCar(),carGate.getGate()));
        // thread.start(); // Start the car thread
        // // Thread.sleep(1000); // Optional delay to simulate real-time entry
        // intervals
        // }
        // for (Thread elem : gateThreads) {
        // elem.start();
        // }
        // gateRun.start();

        /*
         * Total Cars Served: 15
         * Current Cars in Parking: 0
         * Details:- Gate 1 served 5 cars.- Gate 2 served 5 cars.- Gate 3 serve
         */

        // Thread.sleep(ReadFile.MaxTime * 1000);
        Thread.sleep(1500);
        startWriter.write(true, ("Total Cars Served:  " + String.valueOf(parkingLot.getTotalCarsServed()) + "\n"
                + "Current Cars in Parking: " + String.valueOf(parkingLot.getCurrentCars()) + "\n"
                + "Details: \n"));

        for (Gate gate : ReadFile.gates) {
            startWriter.write(true, "- Gate " + String.valueOf(gate.getGateNumber()) + " served "
                    + String.valueOf(gate.getNumberOfCars()) + " cars.\n");
        }

        // try (FileWriter writer = new FileWriter("Output.txt", true)) {
        // writer.write("Total Cars Served: " +
        // String.valueOf(parkingLot.getTotalCarsServed()) + "\n"
        // + "Current Cars in Parking: " + String.valueOf(parkingLot.getCurrentCars()) +
        // "\n"
        // + "Details: \n" + "- Gate " +
        // String.valueOf(ReadFile.gates[0].getGateNumber()) + " served "
        // + String.valueOf(ReadFile.gates[0].getNumberOfCars()) + " cars.\n" + "- Gate
        // "
        // + String.valueOf(ReadFile.gates[1].getGateNumber()) + " served "
        // + String.valueOf(ReadFile.gates[1].getNumberOfCars()) + " cars.\n" + "- Gate
        // "
        // + String.valueOf(ReadFile.gates[2].getGateNumber()) + " served "
        // + String.valueOf(ReadFile.gates[2].getNumberOfCars()) + " cars.\n");
        // writer.flush();
        // } catch (IOException e) {

        // }
    }
}
