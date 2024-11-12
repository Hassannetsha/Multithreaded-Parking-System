// import java.io.FileWriter;.
// import java.io.IOException;

public class CarRunnable implements Runnable {

    private final ParkingLot parkingLot;
    private final Car car;
    private final Gate gate;
    private boolean hasPrintedWaitingMessage = false; // flag to track if waiting message is printed

    public CarRunnable(ParkingLot parkingLot, Car car, Gate gate) {
        this.parkingLot = parkingLot;
        this.car = car;
        this.gate = gate;
    }

    @Override
    public void run() {
        FileContentWriter carRunnableWriter = new FileContentWriter("Output.txt");

        try {

            Thread.sleep(car.getCarArrival() * 100);
            carRunnableWriter.write(true,
                    "Car " + car.getCarNumber() + " from Gate " + gate.getGateNumber() + " arrived at time "
                            + car.getCarArrival() + "\n");

            long startWaitTime = 0;
            synchronized (parkingLot) {
                while (!parkingLot.tryParkCar()) {
                    if (!hasPrintedWaitingMessage) {
                        carRunnableWriter.write(true, "Car " + car.getCarNumber() + " from Gate " +
                                gate.getGateNumber()
                                + " waiting for a spot.\n");
                        hasPrintedWaitingMessage = true;
                    }
                    startWaitTime = System.currentTimeMillis();
                    parkingLot.wait();
                }

                if (hasPrintedWaitingMessage) {
                    long waitTime = System.currentTimeMillis() - startWaitTime;

                    carRunnableWriter.write(true, "Car " + car.getCarNumber() + " from Gate " +
                            gate.getGateNumber() + " parked"
                            + " after waiting for " + (waitTime / 100 == 0 ? 1 : waitTime / 100) + " units of time."
                            + " (Parking Status: " + parkingLot.getCurrentCars() + " spots occupied)\n");
                } else {

                    carRunnableWriter.write(true, "Car " + car.getCarNumber() + " from Gate " +
                            gate.getGateNumber() + " parked."
                            + " (Parking Status: " + parkingLot.getCurrentCars()
                            + " spots occupied)\n");

                }
            }
            Thread.sleep(car.getCarDuration() * 100);
            synchronized (parkingLot) {
                parkingLot.leaveSpot();

                carRunnableWriter.write(true, "Car " + car.getCarNumber() + " from Gate " +
                        gate.getGateNumber() + " left after "
                        + car.getCarDuration()
                        + " units of time. (Parking Status: " + parkingLot.getCurrentCars() + " spots occupied)\n");
                parkingLot.notify();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
