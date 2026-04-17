package QuizApp;

public class TimerThread extends Thread {
    private int time;
    private Runnable onTimeUp;

    public TimerThread(int time, Runnable onTimeUp) {
        this.time = time;
        this.onTimeUp = onTimeUp;
    }

    public void run() {
        try {
            while (time > 0) {
                System.out.println("Time left: " + time);
                Thread.sleep(1000);
                time--;
            }
            onTimeUp.run();
        } catch (InterruptedException e) {
            System.out.println("Timer stopped");
        }
    }
}