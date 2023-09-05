package org.edu.process;

import javax.swing.*;

/**
 * Created by Lxy on 2023/9/4 17:03
 */
public class Ball extends Thread{
    private int x;
    private int y;

    private final static int speed = 10;
    public static int liveBalls = 0;
    private boolean isRunning = true;
    private JPanel jPanel;

    public int getX() {
        return x;
    }
    public Ball(int x, int y, JPanel panel) {
        this.x = x;
        this.y = y;
        this.jPanel = panel;
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

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public JPanel getjPanel() {
        return jPanel;
    }

    public void setJPanel(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    /**
     * 间隔 8ms 模拟移动，由移动的小球数量控制图片移动的平滑度
     */
    @Override
    public void run() {
        if (x < PanelUtils.WidthBorder - 150) {
            x += speed + liveBalls * 5;
            jPanel.repaint();
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            x = PanelUtils.initX;
        }
    }
}
