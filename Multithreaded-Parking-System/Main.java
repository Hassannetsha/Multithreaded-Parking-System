import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {
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

        parkingLot = new ParkingLot(4);
        InnerReadFile innerReadFile = new InnerReadFile();
        List<Thread> gateThreads = ReadFile.Read(parkingLot, innerReadFile);

        for (Thread gateThread : gateThreads) {
            gateThread.start();
        }
        entryQueue = Singleton.getInstance();
        while (innerReadFile.counter-- > 0) {
            try {
                CarGatePair carGate = entryQueue.take();
                Thread thread = new Thread(new CarRunnable(parkingLot, carGate.getCar(), carGate.getGate()));
                thread.start();

                Thread.sleep(10);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted, stopping.");
                break;
            }
        }
        // Thread.sleep(ReadFile.MaxTime * 1000);
        Thread.sleep(1500);
        startWriter.write(true, ("Total Cars Served:  " + String.valueOf(parkingLot.getTotalCarsServed()) + "\n"
                + "Current Cars in Parking: " + String.valueOf(parkingLot.getCurrentCars()) + "\n"
                + "Details: \n"));

        for (Gate gate : ReadFile.gates) {
            startWriter.write(true, "- Gate " + String.valueOf(gate.getGateNumber()) + " served "
                    + String.valueOf(gate.getNumberOfCars()) + " cars.\n");
        }

    }
}
