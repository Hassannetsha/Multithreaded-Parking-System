public class CarGatePair {

    private final Car car;
    private final Gate gate;

    public CarGatePair(Car car, Gate gate) {
        this.car = car;
        this.gate = gate;
    }

    public Car getCar() {
        return car;
    }

    public Gate getGate() {
        return gate;
    }

    @Override
    public String toString() {
        return "CarGatePair{car=" + car + ", gate=" + gate.getGateNumber() + "}";
    }
}

class SortComparator implements java.util.Comparator<CarGatePair> {

    @Override
    public int compare(CarGatePair a, CarGatePair b) {
        if (a.getCar().getCarArrival() == b.getCar().getCarArrival()) {
            return a.getCar().getCarNumber() - b.getCar().getCarNumber();
        }
        return a.getCar().getCarArrival() - b.getCar().getCarArrival();
    }
}
