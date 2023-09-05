package org.edu.process;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Lxy on 2023/9/4 17:15
 */
public class BallPanel extends JPanel{

    private final JButton button;
    private final Ball ball;

    public Ball getBall() {
        return ball;
    }

    private final int order;

    public BallPanel(int order) {
        this.order = order;
        this.setLayout(null);

        Font customFont = new Font("Microsoft YaHei", Font.PLAIN, 18); // 字体、样式、字体大小

        TitledBorder titledBorder = BorderFactory.createTitledBorder("模拟进程：" + order);
        titledBorder.setTitleFont(customFont);
        setBorder(titledBorder);

        ball = new Ball(PanelUtils.initX, PanelUtils.initY , this);
        Ball.liveBalls += 1;

        button = new JButton("暂停");
        button.setBounds( PanelUtils.WidthBorder - 50, ball.getY(), 100, 50);
        button.setFont(customFont);
        this.add(button);
        Ball finalBall = ball;

        /*
          添加点击监听事件
         */
        button.addActionListener(e -> {
            if (finalBall.isRunning()) {
                finalBall.setRunning(false);
                button.setText("执行");
                Ball.liveBalls -= 1;
            } else {
                finalBall.setRunning(true);
                button.setText("暂停");
                Ball.liveBalls += 1;
            }
        });

    }

    /**
     * 读取资源文件中的图片并绘制
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        String fileName =  "/" + order + ".jpg";
        InputStream resourceAsStream = getClass().getResourceAsStream(fileName);
        Image image;
        try {
            if (resourceAsStream != null) {
                image = ImageIO.read(resourceAsStream);
                g.drawImage(image,ball.getX(), ball.getY(), 70, 70, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
