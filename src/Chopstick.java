
public class Chopstick {
    private boolean taken = false;
    private int value;

    public Chopstick() {
        value = 0;
    }

    public Chopstick(int v) {
        value = v;
    }

    public synchronized void P() {//opuszczanie
        while (value <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        value--;
        taken = false;

    }
    public synchronized void V(){//podnoszenie
        ++value;
        taken = true;
        notify();
    }

    public int getValue() {
        return value;
    }
}
