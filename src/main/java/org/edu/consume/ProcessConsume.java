package org.edu.consume;

/**
 * Created by Lxy on 2023/9/18 16:53
 */

import javax.swing.*;
import java.awt.*;

/**
 * 程序主体
 */
public class ProcessConsume {
    public static void main(String[] args) {

        /* 设定主面板 */
        JFrame jFrame = new JFrame("生成/消费者进程模拟");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        JPanel jPanel = new JPanel();
        jFrame.getContentPane().add(jPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize((int) screenSize.getWidth() * 7 / 10, (int) (screenSize.getHeight() * 7 / 10));
        jFrame.setLocationRelativeTo(null);

        Processor consumer = new Processor();
        Processor producer = new Processor();

        JTextArea textArea = new JTextArea();

        StorePanel theStatic = new StorePanel(0, textArea);

        CommodityPanel consumerPanel = new CommodityPanel(50, 50, theStatic, true);
        CommodityPanel producerPanel = new CommodityPanel(50, 50, theStatic, false);

        consumer.setCommodityPanel(consumerPanel, "消费者");
        producer.setCommodityPanel(producerPanel, "生产者");

        Container contentPane = jFrame.getContentPane();
        Font customFont = new Font("Microsoft YaHei", Font.PLAIN, 16); // 字体、样式、字体大小
        JButton consumerButton = new JButton("开始消费");
        JButton producerButton = new JButton("开始生产");

        // 设置布局
        jFrame.setLayout(null);



        consumerButton.addActionListener( e -> {
            consumer.setProducing(! consumer.isProducing());
            if (consumerButton.getText().equals("开始消费")) consumerButton.setText("停止消费");
            else consumerButton.setText("开始消费");
        });

        consumerButton.setFocusPainted(false);
        consumerButton.setFont(customFont);
        consumerButton.setBounds(900, 120, 100, 50);
        contentPane.add(consumerButton);

        consumerPanel.setBounds(50, 50, 800, 150);
        contentPane.add(consumerPanel);

        producerButton.addActionListener( e -> {
            producer.setProducing(! producer.isProducing());
            if (producerButton.getText().equals("开始生产")) producerButton.setText("停止生产");
            else producerButton.setText("开始生产");
        });

        producerButton.setFocusPainted(false);
        producerButton.setFont(customFont);
        producerButton.setBounds(350, 550, 100, 50);
        contentPane.add(producerButton);

        producerPanel.setBounds(500, 500, 800, 150);
        contentPane.add(producerPanel);

        theStatic.setBounds(200, 250, 100, 400);
        contentPane.add(theStatic);

        textArea.setBounds(800, 250, 300, 250);
        contentPane.add(textArea);

        producer.start();
        consumer.start();

        jFrame.setVisible(true);

    }
}
