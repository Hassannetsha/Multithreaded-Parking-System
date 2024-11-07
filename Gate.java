public class Gate {
    private int gateNumber;
    private static int numberOfCars = 0;

    public Gate() {

    }

    public Gate(int gateNumber) {
        this.gateNumber = gateNumber;
        numberOfCars++;
    }

    public int getGateNumber() {
        return gateNumber;
    }

    public int getNumberOfCars() {
        return numberOfCars;
    }
}