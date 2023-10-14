package org.edu.dispatch;

import java.util.Queue;

/**
 * Created by Lxy on 2023/10/14 10:38
 */
public class CPUProcess extends Thread{
    private CPUProcessBar cpuProcessBar;

    private ProcessBall processBall;

    private boolean isPainting;

    public boolean isPainting() {
        return isPainting;
    }

    public void setPainting(boolean painting) {
        isPainting = painting;
    }

    public ProcessBall getProcessBall() {
        return processBall;
    }

    public void setProcessBall(ProcessBall processBall) {
        this.processBall = processBall;
    }

    public CPUProcess(CPUProcessBar cpuProcessBar) {
        this.cpuProcessBar = cpuProcessBar;
    }

    @Override
    public void run() {
        isPainting = true;
        cpuProcessBar.setOrder(processBall.getProcessBallBar().getOrder());
        for (int i = 0; i < 32; i ++) {
            try {
                sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cpuProcessBar.repaint();
        }
        isPainting = false;
    }
}
