package com.mrK.ui;

import javax.swing.*;

public class RegisterJframe extends JFrame {
    //创建注册界面
    public RegisterJframe(){
        //设置界面宽高
        this.setSize(488,500);
        //设置界面标题
        this.setTitle("用户注册");
        //设置界面置顶
        this.setAlwaysOnTop(false);//暂时关闭
        //设置界面在屏幕居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(3);
        //开启可视化界面(默认是不可见的)写在最后
        this.setVisible(true);
    }

}
