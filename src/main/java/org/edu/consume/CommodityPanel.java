package org.edu.consume;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Created by Lxy on 2023/9/18 17:05
 */
public class CommodityPanel extends JPanel {
    private final int speed = 15;
    private final int initY;
    private final int initX;
    private int times = 0;
    private StorePanel store;

    private boolean isConsumer;
    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }



    public CommodityPanel(int initY, int initX, StorePanel store, boolean isConsumer) {
        this.store = store;
        this.initY = initY;
        this.initX = initX;
        this.isConsumer = isConsumer;
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        String fileName = "/kunkun.jpg";
        try {
            Image read = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(fileName)));
            g.drawImage(read, initX + times ++ * speed, initY,70, 70, this);
            if (times == 40) {
                times = 0;
                if (isConsumer) store.subCount();
                else store.addCount();
                store.repaint();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
