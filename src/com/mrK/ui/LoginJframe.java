package com.mrK.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginJframe extends JFrame implements ActionListener {
    //创建登录界面
    public LoginJframe(){
        //初始化界面
        initJFrame();
        //初始化图片
        initImage();

    }
    JLabel Login = new JLabel(new ImageIcon("image/login/登录按钮.png"));

    private void initImage() {
        //初始化数据
        this.getContentPane().removeAll();
        //添加用户名
        JLabel username = new JLabel(new ImageIcon("image/login/用户名.png"));
        username.setBounds(116,135,47,19);
        this.getContentPane().add(username);
        //添加用户名输入框
        JTextField userText = new JTextField();
        userText.setBounds(195,134,200,25);
        this.getContentPane().add(userText);
        //添加密码
        JLabel password = new JLabel(new ImageIcon("image/login/密码.png"));
        password.setBounds(130,195,35,18);
        this.getContentPane().add(password);
        //添加密码输入框
        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(195,195,200,25);
        this.getContentPane().add(passwordText);
        //添加验证码

        //添加验证码输入框

        //添加登录按钮（未完成临时用图片代替返回游戏界面,应改为按钮）
        //Login那行代码移动到成员位置了
        Login.setBounds(133,260,90,40);
        this.getContentPane().add(Login);
        //添加注册按钮

        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("image/login/background.png"));
        background.setBounds(0,0,470,390);
        //把背景图片添加到界面当中
        this.getContentPane().add(background);
        //刷新界面
        this.getContentPane().repaint();
    }

    private void initJFrame() {
        //设置界面宽高
        this.setSize(488,430);
        //设置界面标题
        this.setTitle("拼图游戏V1.0 用户登录(未完成 如进入此页面请关闭重新启动游戏)");
        //设置界面置顶
        this.setAlwaysOnTop(false);//暂时关闭
        //设置界面在屏幕居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(3);
        //取消默认的居中方式,只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);
        //开启可视化界面(默认是不可见的)写在最后
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == Login){
            //临时用于返回游戏
            new GameJframe();
        }
    }
}
