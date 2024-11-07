public class Car {
    private final int number;
    private final int arrival;
    private final int duration;
    public Car(int number,int arrival,int duration){
        this.number = number;
        this.arrival = arrival;
        this.duration = duration;
    }
    public int getCarNumber(){
        return this.number;
    }
    public int getCarArrival(){
        return this.arrival;
    }
    public int getCarDuration(){
        return this.duration;
    }
}
