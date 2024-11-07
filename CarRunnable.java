
public class CarRunnable implements Runnable {

    private final ParkingLot parkingLot;
    private final Car car;

    public CarRunnable(ParkingLot parkingLot, Car car) {
        this.parkingLot = parkingLot;
        this.car = car;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(car.getCarArrival() * 100);
            System.out.println("Car " + car.getCarNumber() + " arrived at time " + car.getCarArrival());
            long startWait = System.currentTimeMillis();
            if (parkingLot.tryParkCar()) {
                Thread.sleep(car.getCarArrival() * 100);
                long waited = (System.currentTimeMillis() - startWait) / 1000;
                System.out.println("Car " + car.getCarNumber() + " parked. Waited: " + waited + "s. (Current Spots: " + parkingLot.getCurrentCars() + ")");
                Thread.sleep(car.getCarDuration() * 100);
                parkingLot.leaveSpot();
                System.out.println("Car " + car.getCarNumber() + " left. (Current Spots: " + parkingLot.getCurrentCars() + ")");
            }
            else {
                System.out.println("Car " + car.getCarNumber() + " waiting for a spot.");
            }
        } catch (InterruptedException e) {
            // e.printStackTrace();
        }
    }

}
