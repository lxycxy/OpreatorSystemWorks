package org.edu.dispatch;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * Created by Lxy on 2023/10/14 10:56
 */
public class ReadyBar extends JPanel {

    private Queue<ProcessBall> processBallQueue;

    public void setProcessBallQueue(Queue<ProcessBall> processBallQueue) {
        this.processBallQueue = processBallQueue;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int i = 0;
        processBallQueue.forEach(System.out::println);
        for (ProcessBall item : processBallQueue) {
            String fileName =  "/" + item.getProcessBallBar().getOrder()+ ".jpg";
            try {
                Image image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(fileName)));
                g.drawImage(image,20 + 50 * i ++, 20, 50, 50, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
