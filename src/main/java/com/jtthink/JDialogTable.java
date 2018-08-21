package com.jtthink;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JDialogTable  {

    private JDialog jDialog;

    //表格模型
    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;

    //题目参数
    private String item;
    private String item_num;
    private JLabel label_title;
    private JLabel label_result;

    //面板
    private JPanel panel;
    private JPanel panel_north;
    private JPanel panel_center;


    public JDialogTable(JFrame jframe, String item, String item_num){
        jDialog = new JDialog(jframe);
        this.item = item;
        this.item_num = item_num;
        init();
    }

    private void init(){
        jDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        jDialog.setContentPane(getPanel());
        jDialog.setModal(true);
        jDialog.setTitle("客户端配置");
        jDialog.setSize(800,800);
        jDialog.setResizable(false);
        jDialog.setLocationRelativeTo(null);
        jDialog.setVisible(true);



    }

    private JPanel getPanel(){
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel_north = new JPanel();
        panel_north.setLayout(new FlowLayout());
        label_title = new JLabel(item+": ");
        label_result = new JLabel("进行中");
        panel_north.add(label_title);
        panel_north.add(label_result);
        panel.add(panel_north, BorderLayout.NORTH);

        panel_center = new JPanel();
        panel_center.setLayout(new BorderLayout());
        scrollPane = new JScrollPane();
        panel_center.add(scrollPane, BorderLayout.CENTER);

        //可以在这里给一遍值

        String[] columnNames = { "一级编号", "一级名称", "二级编号", "二级名称", "三级编号", "三级名称", "答题结果", "分数"};
        //String[][] tableValues = { { "A1", "" }, { "A2", "" },};
        String[][] tableValues = null;
        tableModel = new DefaultTableModel(tableValues, columnNames);
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        panel.add(panel_center);

        final JDialogTableFresh tft = new JDialogTableFresh(tableModel, "thread-JDialogTable", item_num, label_result);
        tft.start();

        jDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                int n = JOptionPane.showConfirmDialog(jDialog, "确认答题是否结束？", "提示信息",JOptionPane.YES_NO_OPTION); //返回值为0或1

                if( n == 0 ){
                    tft.stop();
                    jDialog.dispose();
                }
            }
        });

        return panel;
    }







}
