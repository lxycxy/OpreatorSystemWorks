package org.edu.consume;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Lxy on 2023/9/19 15:41
 */
public class StorePanel extends JPanel {
    private final Lock lock = new ReentrantLock();
    private Integer count;
    private Font font = new Font("Microsoft YaHei", Font.PLAIN, 16);
    private final JTextArea textArea;

    public StorePanel(Integer count, JTextArea jTextArea) {
        this.setBorder(BorderFactory.createTitledBorder("坤坤仓库"));
        this.count = count;
        textArea = jTextArea;
        textArea.setBorder(BorderFactory.createTitledBorder("生产日志"));
        textArea.setFont(font);
        textArea.setLineWrap(true);
    }

    public void addCount() {
        try {
            lock.lock();
            if (count == 6) textArea.append("坤坤已经满了！\n");
            else {
                this.count ++;
                textArea.append("生产了一个坤坤!\n");
            }
        } finally {
            lock.unlock();
        }
    }

    public void subCount () {
        try {
            lock.lock();
            if (count == 0) textArea.append("坤坤已经没有了！\n");
            else {
                this.count --;
                textArea.append("消费了一个坤坤\n");
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        String fileName = "/kunkun.jpg";
        try {
            lock.lock();
            Image read = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(fileName)));
            for (int i = 0; i < count; i ++) {
                g.drawImage(read, 20, 20 + i * 50, 50, 50, this);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
