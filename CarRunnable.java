
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
            if (parkingLot.tryParkCar()) {
                System.out.println("Entered car" + car.getCarNumber());
                synchronized (this) {
                    wait(car.getCarDuration() * 10);
                    parkingLot.leaveSpot();
                    System.out.println("Car " + car.getCarNumber() + " left");
                }
            }
        } catch (InterruptedException e) {
            // e.printStackTrace();
        }
    }

}
