package org.edu.process;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lxy on 2023/9/4 17:03
 */
public class ProcessConcurrency {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // 设置主框架
        JFrame frame = new JFrame("进程并发模拟");
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 计算居中位置
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int programWidth = (int) (screenSize.getWidth() * 0.7);
        PanelUtils.WidthBorder = programWidth - 150;
        int programHeight = (int) (screenSize.getHeight() * 0.7);

        frame.setSize(programWidth, programHeight);
        int centerX = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);

        // 设置窗体位置
        frame.setLocation(centerX, centerY);
        Container contentPane = frame.getContentPane();
        // 设置布局
        GridLayout groupLayout = new GridLayout(5, 1);
        contentPane.setLayout(groupLayout);

        // 第一行字体
        Font customFont = new Font("Microsoft YaHei", Font.PLAIN, 25); // 字体、样式、字体大小
        JLabel jLabel = new JLabel();
        jLabel.setFont(customFont);
        jLabel.setText("模拟进程程序          作者：lxy");
        jLabel.setPreferredSize(new Dimension(programWidth, 50));
        // 设置文本居中对齐
        jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel.setSize(programWidth, 50);
        contentPane.add(jLabel);

        // 添加三个小球通道
        List<BallPanel> panels = new ArrayList<>();
        for (int i = 1; i <= 3 ; i ++) {
            BallPanel ballPanel = new BallPanel(i);
            panels.add(ballPanel);
            contentPane.add(ballPanel);
        }

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        frame.setVisible(true);

        while (true) {
            int r = (int) (Math.random() * 10 % 3);
            BallPanel ballPanel = panels.get(r);
            if (ballPanel.getBall().isRunning()) {
                ballPanel.getBall().run();
            }
        }

    }
}
