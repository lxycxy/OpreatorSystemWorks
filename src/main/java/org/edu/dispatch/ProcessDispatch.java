package org.edu.dispatch;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by Lxy on 2023/10/14 9:47
 */
public class ProcessDispatch {
    public static void main(String[] args) {

        /* 设定主面板 */
        JFrame jFrame = new JFrame("生成/消费者进程模拟");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JPanel jPanel = new JPanel();
        jFrame.getContentPane().add(jPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize((int) screenSize.getWidth() * 7 / 10, (int) (screenSize.getHeight() * 7 / 10));
        jFrame.setLocationRelativeTo(null);
        jFrame.setLayout(null);
        Container contentPane = jFrame.getContentPane();


        /*  */
        List<ProcessBall> processBalls = new ArrayList<>();

        for (int i = 1; i <= 4; i ++) {
            ProcessBall processBall = new ProcessBall(0, 20);
            processBalls.add(processBall);
            ProcessBallBar processBallBar = new ProcessBallBar(processBall, i);
            processBallBar.setBounds(50, 120 * i, 400, 120);
            processBallBar.setBorder(BorderFactory.createTitledBorder("第" + i + "个进程"));
            processBall.setProcessBallBar(processBallBar);
            contentPane.add(processBallBar);
        }

        for (ProcessBall item : processBalls) {
            item.start();
        }

        ReadyBar readyBar = new ReadyBar();
        readyBar.setBounds(500, 300, 300, 100);
        readyBar.setBorder(BorderFactory.createTitledBorder("准备队列"));

        CPUProcessBar cpuProcessBar = new CPUProcessBar();
        cpuProcessBar.setBounds(1000, 300, 300, 100);
        cpuProcessBar.setBorder(BorderFactory.createTitledBorder("CPU处理"));

        CPUProcess cpuProcess = new CPUProcess(cpuProcessBar);

        Queue<ProcessBall> processBallQueue = new ConcurrentLinkedDeque<>();

        readyBar.setProcessBallQueue(processBallQueue);

        contentPane.add(cpuProcessBar);
        contentPane.add(readyBar);

        jFrame.setVisible(true);



        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                Iterator<ProcessBall> iterator = processBalls.iterator();
                while (iterator.hasNext()) {
                    ProcessBall item = iterator.next();
                    if (item.isFinished()) {
                        processBallQueue.add(item);
                        readyBar.repaint();
                        iterator.remove(); // 使用迭代器的 remove 方法安全地删除元素
                    }
                }

            }
        }).start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(30);
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
        }).start();






    }


}
