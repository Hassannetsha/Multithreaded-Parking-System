
public class semaphore {

    private int capacity;

    public semaphore() {
        this.capacity = 0;
    }

    public semaphore(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        this.capacity = capacity;
    }

    public synchronized boolean tryAcquire() {
        if (capacity > 0) {
            capacity--;
            return true;
        }
        return false;
    }
    public synchronized void release(){
        this.capacity++;
    }
}
