package com.jtthink;

import com.jtthink.httpClient.HttpConfig;
import com.jtthink.httpClient.HttpServiceImpl;
import com.jtthink.util.ParamUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame  {

    //界面布局
    private JFrame frame;
    private JPanel panel ;

    private JButton button_item1; //题目按钮1
    private JButton button_item2; //题目按钮2

    private JButton button_showPic; //电路显示按钮


    public MainFrame(){
        init();
    }

    public void init(){

        frame = new JFrame();
        frame.setTitle("教师出题界面");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setResizable(false);

        frame.setContentPane(getPenel());

        int windowWidth = frame.getWidth();
        int windowHeight = frame.getHeight();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);

        frame.setVisible(true);

    }

    private JPanel getPenel(){
        panel = new JPanel();
        panel.setLayout(null);

        button_item1 = new JButton("第一题");
        button_item1.setBounds(50, 50, 120, 30);
        button_item1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //传送题目的id
                String url = ParamUtil.getConfigValue("url");
                int port = Integer.parseInt(ParamUtil.getConfigValue("port"));

                HttpConfig config = new HttpConfig();
                config.setServerIp(url);
                config.setServerPort(port);
                config.setUrl("num/1");

                String content  = new HttpServiceImpl().get(config);

                if( content != null && !content.equals("") ){
                    new JDialogTable(frame, "第一题答题结果", "1");
                }else{
                    JOptionPane.showMessageDialog(frame, "网络通信配置不正确！", "标题",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        panel.add(button_item1);

        button_item2 = new JButton("第二题");
        button_item2.setBounds(50, 90, 120, 30);
        button_item2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //传送题目的id
                String url = ParamUtil.getConfigValue("url");
                int port = Integer.parseInt(ParamUtil.getConfigValue("port"));

                HttpConfig config = new HttpConfig();
                config.setServerIp(url);
                config.setServerPort(port);
                config.setUrl("num/2");

                String content  = new HttpServiceImpl().get(config);

                if( content != null && !content.equals("") ){
                    new JDialogTable(frame, "第二题答题结果", "2");
                }else{
                    JOptionPane.showMessageDialog(frame, "网络通信配置不正确！", "标题",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        panel.add(button_item2);

        //电路部分的逻辑设置2个答题 每个大题有两个小题共4题目
        //每个题目对应一个开关
        button_showPic = new JButton("显示电路图");
        button_showPic.setBounds(50, 130, 120, 30);
        button_showPic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new JDialogCircuit(frame);
            }
        });


        panel.add(button_showPic);

        return panel;
    }



    public static void main(String[] args){
        new MainFrame();
    }


}
