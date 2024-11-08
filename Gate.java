
import java.util.ArrayList;
import java.util.List;

public class Gate {

    private final int gateNumber;
    private final List<Car> cars;
    private final List<Thread> CarThreads;
    public Gate(ParkingLot parkingLot, int gateNumber, Car car) {
        this.gateNumber = gateNumber;
        CarThreads = new ArrayList<>();
        cars = new ArrayList<>();
        cars.add(car);
        // this.cars = cars;
        // for (Car elem : cars) {
        // CarThreads.add(new Thread(new CarRunnable(parkingLot,car)));
        // }
    }

    public void increment(Car car) {
        cars.add(car);
        // numberOfCars++;
        // CarThreads.add(new Thread(new CarRunnable(parkingLot,car)));
    }

    public void addCarThreads(ParkingLot parkingLot) {
        // sort();
        for (Car elem : cars) {
            CarThreads.add(new Thread(new CarRunnable(parkingLot, elem, this)));
        }
    }

    public int getGateNumber() {
        return gateNumber + 1;
    }

    // public void sort() {
    //     // int x = CarThreads
    //     Collections.sort(cars, new SortComparator());
    // }

    // public List<Thread> getCarThreads() {
    //     return this.CarThreads;
    // }
    public List<Car> getCars() {
        return this.cars;
    }

    public int getNumberOfCars() {
        // return numberOfCars;
        return cars.size();
    }
}

// class SortComparator implements java.util.Comparator<Car> {

//     @Override
//     public int compare(Car a, Car b) {
//         if (a.getCarArrival() == b.getCarArrival()) {
//             return a.getCarNumber() - b.getCarNumber();
//         }
//         return a.getCarArrival() - b.getCarArrival();
//     }
// }
