
import java.util.concurrent.PriorityBlockingQueue;

public class GateRunnable implements Runnable {
    
    private final Gate gate;
    // private List<Thread> CarThreads;
    private final PriorityBlockingQueue<CarGatePair> entryQueue;
    public GateRunnable(Gate gate) {
        this.gate = gate;
        this.entryQueue = Singleton.getInstance();
    }

    @Override
    public void run() {
        // CarThreads = gate.getCarThreads();
        for (Car elem : gate.getCars()) {

            entryQueue.put(new CarGatePair(elem,gate));
            // try {
            //     elem.join();
            // } catch (InterruptedException ex) {
            // }
        }
    }
}
