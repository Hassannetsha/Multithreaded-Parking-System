import java.io.FileWriter;
import java.io.IOException;

public class CarRunnable implements Runnable {

    private final ParkingLot parkingLot;
    private final Car car;
    private final Gate gate;
    private boolean hasPrintedWaitingMessage = false; // Flag to track if waiting message is printed

    public CarRunnable(ParkingLot parkingLot, Car car, Gate gate) {
        this.parkingLot = parkingLot;
        this.car = car;
        this.gate = gate;
    }

    @Override
    public void run() {
        try {

            Thread.sleep(car.getCarArrival() * 100);
            try (FileWriter writer = new FileWriter("Output.txt",true)) {
                writer.write("Car " + car.getCarNumber() + " from Gate " + gate.getGateNumber() + " arrived at time "
                        + car.getCarArrival() + "\n");
                writer.flush();
            } catch (IOException e) {

            }

            long startWaitTime = 0;
            synchronized (parkingLot) {
                while (!parkingLot.tryParkCar()) {
                    if (!hasPrintedWaitingMessage) {
                        try (FileWriter writer = new FileWriter("Output.txt", true)) {
                            writer.write("Car " + car.getCarNumber() + " from Gate " + gate.getGateNumber()
                                    + " waiting for a spot.\n");
                            writer.flush();
                        } catch (IOException e) {

                        }
                        hasPrintedWaitingMessage = true;
                    }
                    startWaitTime = System.currentTimeMillis();
                    // car.incrementWaitingTime();
                    parkingLot.wait();
                }

                if (hasPrintedWaitingMessage) {
                    long waitTime = System.currentTimeMillis() - startWaitTime;

                    try (FileWriter writer = new FileWriter("Output.txt", true)) {
                        writer.write("Car " + car.getCarNumber() + " from Gate " + gate.getGateNumber() + " parked"
                                + " after waiting for " + (waitTime / 100 == 0 ? 1 : waitTime / 100) + " units of time."
                                + " (Parking Status: " + parkingLot.getCurrentCars() + " spots occupied)\n");
                        writer.flush();
                    } catch (IOException e) {

                    }
                } else {

                    try (FileWriter writer = new FileWriter("Output.txt", true)) {
                        writer.write("Car " + car.getCarNumber() + " from Gate " + gate.getGateNumber() + " parked."
                                + " (Parking Status: " + parkingLot.getCurrentCars()
                                + " spots occupied)\n");
                        writer.flush();
                    } catch (IOException e) {

                    }
                }
            }
            Thread.sleep(car.getCarDuration() * 100);
            synchronized (parkingLot) {
                parkingLot.leaveSpot();

                try (FileWriter writer = new FileWriter("Output.txt", true)) {
                    writer.write("Car " + car.getCarNumber() + " from Gate " + gate.getGateNumber() + " left after "
                            + car.getCarDuration()
                            + " units of time. (Parking Status: " + parkingLot.getCurrentCars() + " spots occupied)\n");
                    writer.flush();
                } catch (IOException e) {

                }
                parkingLot.notify(); // Notify other threads waiting for a spot
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Properly handle interruption
        }
    }
}
