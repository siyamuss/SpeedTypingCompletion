package za.co.typespeed.completition.StopWatch;

public class Timer {
    long start,end;
    double tim;

    public Timer() {}

    public void startTime(){
        start = System.currentTimeMillis();
    }

    public double endTime(){
        end = System.currentTimeMillis();
        tim = (end-start)/1000.0;
        return tim;
    }
}
