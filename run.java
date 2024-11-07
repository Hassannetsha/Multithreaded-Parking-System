public class run {
    public static void main(String[] args) throws InterruptedException {
        ParkingLot parkingLot = new ParkingLot(4);
        Thread[] threads = new Thread[4];
        if (parkingLot.tryParkCar()) {
            threads[0].sleep(3000);
            parkingLot.leaveSpot();
        }
    }
}
