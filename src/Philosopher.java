
public class Philosopher extends Thread {

    private int philosopherNumber;
    private Chopstick[] chopstick;
    private Detector detector;

    public Philosopher(int philosopherNumber, Chopstick[] chopstick, Detector detector) {
        this.philosopherNumber = philosopherNumber;
        this.chopstick = chopstick;
        this.detector = detector;
    }


    public void run() {
        int numberOfSecondChopstick = (philosopherNumber + 1) % 5;

        while (true) {

            System.out.println("Philosopher " + (philosopherNumber + 1) + " Is thinking...");
            try {
                Thread.sleep((int) (Math.random() * 300));
            } catch (InterruptedException e) {
            }
            //P - opuszczanie
            //V - podnoszenie
            chopstick[philosopherNumber].P();
            System.out.println("Philosopher " + (philosopherNumber + 1) + " is picking a chopstick " + (philosopherNumber + 1) + " up");
            detector.setPickedUp(philosopherNumber, true);
            if(detector.detectDeadLock()){

                System.out.println("Deadlock detected !");
                System.out.println("Philosopher " + (philosopherNumber + 1) + " is putting a chopstick " + (philosopherNumber + 1) + " down");
                chopstick[philosopherNumber].V();
                detector.setPickedUp(philosopherNumber,false);
            }
            try {
                Thread.sleep((int) (Math.random() + 100));
            } catch (InterruptedException e) {
            }

            chopstick[numberOfSecondChopstick].P();
            System.out.println("Philosopher " + (philosopherNumber + 1) + " is picking up a chopstick " + (numberOfSecondChopstick + 1) + " up");
            System.out.println("Philosopher" + (philosopherNumber + 1) + " is eating...");
            detector.setPickedUp(numberOfSecondChopstick, true);
            detector.setEating(philosopherNumber, true);

            try {
                Thread.sleep((int) (Math.random() * 100));
            } catch (InterruptedException e) {
            }
            System.out.println("Philosopher " + (philosopherNumber + 1) + " is putting a chopstick " + (numberOfSecondChopstick + 1) + " down");
            detector.setPickedUp(numberOfSecondChopstick,false);
            detector.setEating(philosopherNumber, false);
            chopstick[numberOfSecondChopstick].V();
            System.out.println("Philosopher " + (philosopherNumber + 1) + " is putting a chopstick " + (philosopherNumber + 1) + " down");
            chopstick[philosopherNumber].V();
            detector.setPickedUp(philosopherNumber,false);
        }


    }


}
