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
            System.out.println("Car " + car.getCarNumber() + " arrived at time " + car.getCarArrival() + " from Gate "
                    + gate.getGateNumber());

            synchronized (parkingLot) {
                while (!parkingLot.tryParkCar()) {
                    if (!hasPrintedWaitingMessage) {
                        System.out.println("Car " + car.getCarNumber() + " waiting for a spot.");
                        hasPrintedWaitingMessage = true;
                    }
                    parkingLot.wait();
                }
                double waited = (System.currentTimeMillis() - car.getCarArrival() * 100) / 1000.0;
                System.out.println("Car " + car.getCarNumber() + " parked," + " from Gate " + gate.getGateNumber()
                        + " . Waited: " + waited + "s. (Current Spots: "
                        + parkingLot.getCurrentCars() + ")");
            }

            Thread.sleep(car.getCarDuration() * 100);

            synchronized (parkingLot) {
                parkingLot.leaveSpot();
                System.out.println("Car " + car.getCarNumber() + " left. " + " from Gate " + gate.getGateNumber()
                        + " (Current Spots: " + parkingLot.getCurrentCars() + ")");
                parkingLot.notify();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Properly handle interruption
        }
    }
}
