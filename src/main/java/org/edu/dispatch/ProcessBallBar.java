package org.edu.dispatch;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Created by Lxy on 2023/10/14 9:50
 */
public class ProcessBallBar extends JPanel {

    private ProcessBall processBall;

    private int order;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }


    public ProcessBallBar( ProcessBall ball, int order) {
        this.processBall = ball;
        this.order = order;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        String fileName = "/" + order + ".jpg";
        try {
            Image read = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(fileName)));
            g.drawImage(read, processBall.getX(), processBall.getY(), 50, 50, this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
