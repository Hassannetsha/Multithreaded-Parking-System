
import java.util.concurrent.PriorityBlockingQueue;

public class Singleton {
    private static volatile PriorityBlockingQueue<CarGatePair> entryQueue;

    private Singleton() {}

    public static PriorityBlockingQueue<CarGatePair> getInstance() {
            synchronized (Singleton.class) {
                if (entryQueue == null) {
                    entryQueue = new PriorityBlockingQueue<>(100, new SortComparator());
                }
            }
        return entryQueue;
    }
}