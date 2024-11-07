import java.util.List;

public class GateRunnable implements Runnable {
    private final Gate gate;
    private List<Thread> CarThreads;
    public GateRunnable(Gate gate) {
        this.gate = gate;
    }
    @Override
    public void run(){
         CarThreads = gate.getCarThreads();
        for (Thread elem : CarThreads) {
            elem.start();
            // try {
            //     elem.join();
            // } catch (InterruptedException ex) {
            // }
        }
    }
}
