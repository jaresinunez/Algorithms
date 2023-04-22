package Assignment2;

public class Triplet<T, U, V> {
    private final T low;
    private final U high;
    private final V sum;

    public Triplet(T low, U high, V sum) {
        this.low = low;
        this.high = high;
        this.sum = sum;
    }

    public Triplet() {
        this.low = null;
        this.high = null;
        this.sum = null;
    }

    public T getLow() { return low; }
    public U getHigh() { return high; }
    public V getSum() { return sum; }

    public void print() {
        System.out.println("low: " + low + "\nhigh: " + high + "\nSum: " + sum);
    }
}
