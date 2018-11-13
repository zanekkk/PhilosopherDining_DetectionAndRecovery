public class Detector {
    private boolean pickedUp[] = new boolean[5];
    private boolean eating[] = new boolean[5];
    private Philosopher philosophers[] = new Philosopher[5];


    public Detector(){
     eating[0] = false;
     eating[1] = false;
     eating[2] = false;
     eating[3] = false;
     eating[4] = false;

    }


    public void setPickedUp(int number, boolean pickedUp) {
        this.pickedUp[number] = pickedUp;
    }

    public boolean detectDeadLock() {
        boolean step1 = false;
        int i =0;

        for(boolean picked : pickedUp){
            if(picked){
                i++;
            }
        }
        for(boolean eat : eating){
            if(!eat){
                i++;
            }
        }

        if(i == 10){
            return true;
        }

        return false;
    }

    public void setEating(int number, boolean eating) {
        this.eating[number] = eating;
    }
}
