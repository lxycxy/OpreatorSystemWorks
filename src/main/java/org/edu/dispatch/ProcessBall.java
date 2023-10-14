package org.edu.dispatch;

/**
 * Created by Lxy on 2023/10/14 9:53
 */
public class ProcessBall extends Thread {
    private int x;
    private int y;
    private ProcessBallBar processBallBar;
    private boolean isFinished = false;

    public int getX() {
        return x;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ProcessBallBar getProcessBallBar() {
        return processBallBar;
    }

    public void setProcessBallBar(ProcessBallBar processBallBar) {
        this.processBallBar = processBallBar;
    }

    public ProcessBall(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() {

        int speed = (int) (Math.random() * 15);
        for (int i = 1; i <= 100; i ++) {
            x += 5 + speed;
            if (x > 400) break;
            try {
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            processBallBar.repaint();
        }

        isFinished = true;

    }
}
