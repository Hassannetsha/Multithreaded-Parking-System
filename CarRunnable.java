
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
                Thread.sleep(car.getCarDuration()*1000);
            }
        } catch (InterruptedException e) {
            // e.printStackTrace();
        }
    }

}
