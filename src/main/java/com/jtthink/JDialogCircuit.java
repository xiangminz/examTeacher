package com.jtthink;

import com.jtthink.entity.ExamDetail;
import com.jtthink.entity.Switch;
import com.jtthink.httpClient.HttpConfig;
import com.jtthink.httpClient.HttpServiceImpl;
import com.jtthink.util.ParamUtil;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;

import static com.jtthink.util.JsonUtil.jsonStrToList;

public class JDialogCircuit extends JDialog {

    public JDialogCircuit(JFrame jframe){
        super(jframe);
        init();
    }

    public void init(){

        //处理逻辑：查询电路图表返回状态，然后设置图片，每个状态一个图片

        //设置背景
        URL urlbg = JDialogCircuit.class.getClassLoader().getResource("dlbj.png");
        Image image = Toolkit.getDefaultToolkit().getImage(urlbg);
        JPanelBackground panel = new JPanelBackground();
        panel.setImage(image);
        this.add(panel);

        //填充按钮
        URL urlRight = JDialogCircuit.class.getClassLoader().getResource("on.png");
        ImageIcon iconRight = new ImageIcon(urlRight);
        URL urlWrong = JDialogCircuit.class.getClassLoader().getResource("off.png");
        ImageIcon iconWrong = new ImageIcon(urlWrong);
        URL urlOpen = JDialogCircuit.class.getClassLoader().getResource("open.png");
        ImageIcon iconOpen = new ImageIcon(urlOpen);
        URL urlClose = JDialogCircuit.class.getClassLoader().getResource("close.png");
        ImageIcon iconClose = new ImageIcon(urlClose);

        //查询按钮状态
        String url = ParamUtil.getConfigValue("url");
        int port = Integer.parseInt(ParamUtil.getConfigValue("port"));
        HttpConfig config = new HttpConfig();
        config.setServerIp(url);
        config.setServerPort(port);
        config.setUrl("circuit");
        String content  = new HttpServiceImpl().get(config);
        List<Switch> list= jsonStrToList(content, Switch.class);


        //按钮1
        JLabel label1 = new JLabel(iconOpen);
        label1.setBounds(50, 50, iconOpen.getIconWidth(), iconOpen.getIconHeight());
        panel.add(label1);
        //jb_set.setPreferredSize(new Dimension(icon.getWidth(), icon.getHeight()));


        //按钮2
        JLabel label2 = new JLabel(iconClose);
        label2.setBounds(300, 50, iconClose.getIconWidth(), iconClose.getIconHeight());
        panel.add(label2);


        //按钮3
        JLabel label3 = new JLabel(iconRight);
        label3.setBounds(50, 250, iconWrong.getIconWidth(), iconWrong.getIconHeight());
        panel.add(label3);


        //对话框属性
        this.setModal(true);
        this.setTitle("电路图状态");
        this.setSize(800,400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}
