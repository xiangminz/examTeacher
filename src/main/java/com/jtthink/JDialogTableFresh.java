package com.jtthink;

import com.jtthink.entity.ExamDetail;
import com.jtthink.httpClient.HttpConfig;
import com.jtthink.httpClient.HttpServiceImpl;
import com.jtthink.util.ParamUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

import static com.jtthink.util.JsonUtil.jsonStrToList;

public class JDialogTableFresh implements Runnable {

    private DefaultTableModel tableModel;
    private Thread currentThread;
    private String threadName;
    private String item;
    private JLabel label;

    public JDialogTableFresh(DefaultTableModel tableModel, String threadName, String item, JLabel label){
        this.tableModel = tableModel;
        this.threadName = threadName;
        this.item = item;
        this.label = label;
    }

    public void start(){
        currentThread = new Thread(this, threadName);
        currentThread.start();
    }

    public void stop(){
        currentThread.interrupt();
    }

    public void run() {

        while(true){

            //清空表格数据
            tableModel.setRowCount(0);
            //退出标识位置
            boolean flag = false;
            boolean flag_in = false;
            //结束标识位置
            boolean flag_over = false;

            String url = ParamUtil.getConfigValue("url");
            int port = Integer.parseInt(ParamUtil.getConfigValue("port"));

            HttpConfig config = new HttpConfig();
            config.setServerIp(url);
            config.setServerPort(port);
            config.setUrl("search/"+item);

            String content  = new HttpServiceImpl().get(config);
            /*
            [{"detail_id":0,"detail_parent":1,"detail_parent_name":"全站送电操作","detail_son":0,"detail_son_name":null,"detail_grandson":0,"detail_grandson_name":null,"detail_result":0,"detail_score":0,"detail_switch_use":0,"detail_switch_car_id":null,"detail_step":null,"detail_backup1":null,"detail_backup2":null},
            {"detail_id":0,"detail_parent":1,"detail_parent_name":"全站送电操作","detail_son":0,"detail_son_name":null,"detail_grandson":0,"detail_grandson_name":null,"detail_result":0,"detail_score":0,"detail_switch_use":0,"detail_switch_car_id":null,"detail_step":null,"detail_backup1":null,"detail_backup2":null},
            {"detail_id":0,"detail_parent":1,"detail_parent_name":"全站送电操作","detail_son":0,"detail_son_name":null,"detail_grandson":0,"detail_grandson_name":null,"detail_result":0,"detail_score":0,"detail_switch_use":0,"detail_switch_car_id":null,"detail_step":null,"detail_backup1":null,"detail_backup2":null}]
            */
            List<ExamDetail> list= jsonStrToList(content, ExamDetail.class);

            //String[] columnNames = { "一级编号", "一级名称", "二级编号", "二级名称", "三级编号", "三级名称", "答题结果", "分数"};
            //String[][] tableValues = { { "A1", "" }, { "A2", "" },};

            for(int i=0; i<list.size(); i++){
                ExamDetail examDetail = list.get(i);
                String tmp = examDetail.getDetail_backup1();


                if( tmp.equals("0") ){
                    tmp = "未完成";
                }else if( tmp.equals("1")){
                    tmp = "正确";
                    if( i == (list.size()-1) ){
                        flag_over = true;
                    }
                }else if( tmp.equals("2")){
                    tmp = "错误";
                    flag_in = true;
                }

                String[] tmpData = { examDetail.getDetail_parent()+"", examDetail.getDetail_parent_name(),
                        examDetail.getDetail_son()+"", examDetail.getDetail_son_name(),
                        examDetail.getDetail_grandson()+"", examDetail.getDetail_grandson_name(),
                        tmp, examDetail.getDetail_score()+""
                };
                tableModel.addRow(tmpData);

                //错误的处理
                if( flag_in && i==(list.size()-1) ){
                    label.setText("答题错误！结束！");
                    flag = true;
                    break;
                }

                //正确结束的处理
                if( flag_over &&  i==(list.size()-1) ){
                    label.setText("答题全部正确！通过！");
                    flag = true;
                    break;
                }

            }

            if(flag){
                break;
            }

            try{
                Thread.sleep( Long.parseLong(ParamUtil.getConfigValue("sleep")) );
            }catch(Exception ex){
                System.out.println("线程退出");
                break;
            }

        }

    }





}
