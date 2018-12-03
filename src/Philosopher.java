
public class Philosopher extends Thread {

    private int philosopherNumber;
    private Chopstick[] knives;
    private Chopstick[] forks;
    private Detector detector;

    public Philosopher(int philosopherNumber, Chopstick[] forks, Chopstick[] knives,  Detector detector) {
        this.philosopherNumber = philosopherNumber;
        this.philosopherNumber = philosopherNumber;
        this.knives = knives;
        this.forks = forks;
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

            int forkNumber = detector.getNumberOfFreeFork();
            int kniveNumber = detector.getNumberOfFreeKnives();

            knives[kniveNumber].P();
            System.out.println("Philosopher " + (philosopherNumber + 1) + " is picking a knife " + (kniveNumber + 1) + " up");
            detector.setKnivesPickedUp(kniveNumber, true);
            forks[forkNumber].P();
            System.out.println("Philosopher " + (philosopherNumber + 1) + " is picking up a fork " + (forkNumber + 1) + " up");
            detector.setForksPickedUp(forkNumber, true);

            try {
                Thread.sleep((int) (Math.random() + 100));
            } catch (InterruptedException e) {
            }
            if(!detector.detectDeadLock()){
                System.out.println("Deadlock detected !");
                System.out.println("Philosopher " + (philosopherNumber + 1) + " is putting a knife " + (philosopherNumber + 1) + " down");
                knives[kniveNumber].V();
                System.out.println("Philosopher " + (philosopherNumber + 1) + " is putting a fork " + (philosopherNumber + 1) + " down");
                forks[forkNumber].V();

                detector.setKnivesPickedUp(kniveNumber, false);
                detector.setForksPickedUp(forkNumber, false);
            }else{
            try {
                Thread.sleep((int) (Math.random() + 100));
            } catch (InterruptedException e) {
            }

                detector.setEating(philosopherNumber, true);
                System.out.println("Philosopher " + (philosopherNumber + 1) + " is eating...");

                try {
                    Thread.sleep((int) (Math.random() + 100));
                } catch (InterruptedException e) {
                }

                System.out.println("Philosopher " + (philosopherNumber + 1) + " is putting a knive " + (kniveNumber + 1) + " down");
                detector.setKnivesPickedUp(kniveNumber, false);
                detector.setEating(philosopherNumber, false);
                knives[kniveNumber].V();

                System.out.println("Philosopher " + (philosopherNumber + 1) + " is putting a fork " + (forkNumber + 1) + " down");
                forks[forkNumber].V();
                detector.setForksPickedUp(forkNumber, false);
            }
        }


    }


}
