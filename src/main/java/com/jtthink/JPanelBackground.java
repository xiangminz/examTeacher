package com.jtthink;

import javax.swing.*;
import java.awt.*;

public class JPanelBackground extends JPanel {

    private Image image;

    public JPanelBackground(){
        super();
        setOpaque(false);
        setLayout(null);
    }

    public void setImage(Image image){
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {// 重写绘制组件外观
        if (image != null) {
            int width = getWidth();// 获取组件大小
            int height = getHeight();
            g.drawImage(image, 0, 0, width, height, this);// 绘制图片与组件大小相同
        }
        super.paintComponent(g);// 执行超类方法
    }

}
