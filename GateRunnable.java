import java.util.List;

public class GateRunnable implements Runnable {
    private final Gate gate;
    private List<Thread> CarThreads;
    public GateRunnable(Gate gate) {
        this.gate = gate;
    }
    @Override
    public void run(){
         CarThreads = gate.CarThreads;
        for (Thread elem : CarThreads) {
            elem.start();
        }
    }
}
