package Objects;

import Main.GamePanel;

public class SuperPortion extends SuperObject{
    public boolean isWorking;
    public long startTime;
    public long duration;

    public SuperPortion(long durationInMillis){
        duration = (durationInMillis == -1) ? 0 : durationInMillis;
        startTime = 0;
        isWorking = false;
    }

    public void startWorking() {
        isWorking = true;
        startTime = System.currentTimeMillis();
    }
    public boolean isWorking() {
        if (isWorking) {
             return duration == 0 || (System.currentTimeMillis() - startTime) <= duration;
        }
        return false;
    }
}
