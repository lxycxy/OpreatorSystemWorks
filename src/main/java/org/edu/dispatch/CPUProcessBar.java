package org.edu.dispatch;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Created by Lxy on 2023/10/14 10:32
 */
public class CPUProcessBar extends JPanel {
    private int order;
    private int times;


    public void setOrder(int order) {
        this.order = order;
        this.times = 0;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (order == 0) return;
        String fileName = "/" + order + ".jpg";
        try {
            Image read = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(fileName)));
            g.drawImage(read,  10 * times ++, 20, 50, 50, this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
