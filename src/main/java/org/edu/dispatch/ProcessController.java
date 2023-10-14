package org.edu.dispatch;

import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Created by Lxy on 2023/10/14 10:54
 */
public class ProcessController extends Thread {
    private Queue<ProcessBall> processBallQueue;
    private CPUProcess cpuProcess;
    private List<ProcessBall> processBalls;
    private ReadyBar readyBar;

    public ProcessController(Queue<ProcessBall> processBallQueue, CPUProcess cpuProcess, List<ProcessBall> processBalls, ReadyBar readyBar) {
        this.processBallQueue = processBallQueue;
        this.cpuProcess = cpuProcess;
        this.processBalls = processBalls;
        this.readyBar = readyBar;
        readyBar.setProcessBallQueue(processBallQueue);
    }

    @Override
    public void run() {
        super.run();

        while (true) {
            try {
                sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (!cpuProcess.isPainting() && !processBallQueue.isEmpty()) {
                ProcessBall poll = processBallQueue.poll();
                readyBar.repaint();
                cpuProcess.setProcessBall(poll);
                cpuProcess.run();
            }

        }

    }
}
