package org.edu.consume;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Lxy on 2023/9/18 16:57
 */
public class Processor extends Thread {

    private final Lock lock = new ReentrantLock();
    private CommodityPanel commodityPanel;

    public CommodityPanel getCommodityPanel() {
        return commodityPanel;
    }

    public void setCommodityPanel(CommodityPanel commodityPanel, String name) {

        Font customFont = new Font("Microsoft YaHei", Font.PLAIN, 18); // 字体、样式、字体大小

        TitledBorder titledBorder = BorderFactory.createTitledBorder(name + "进程");
        titledBorder.setTitleFont(customFont);
        commodityPanel.setBorder(titledBorder);
        this.commodityPanel = commodityPanel;
    }

    private boolean isProcessing = false;

    public boolean isProducing() {
        try {
            lock.lock();
            return isProcessing;
        } finally {
            lock.unlock();
        }
    }

    public void setProducing(boolean producing) {
        try {
            lock.lock();
            isProcessing = producing;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(100);
                if (isProcessing) commodityPanel.repaint();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
