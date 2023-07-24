package com.mrK.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class GameJframe extends JFrame implements KeyListener, ActionListener {
    //创建存放图片位置的二维数组
    int[][] Arr = new int[4][4];
    //定义图片序号
    Random random = new Random();
    int index = random.nextInt(13)+1;
    //图片路径
    String path ="image/girl/girl"+index+"/";
    //定义拼图正确的存放顺序
    int[][] win = {
        {1,5,9,13},
        {2,6,10,14},
        {3,7,11,15},
        {4,8,12,0}
    };
    //定义变量用来统计步数
    int step = 0;
    //创建选项下面的条目对象
    //功能
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem girlImg = new JMenuItem("美女");
    JMenuItem sportImg = new JMenuItem("运动");
    JMenuItem animalImg = new JMenuItem("动物");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    //关于我们
    JMenuItem accountItem = new JMenuItem("微信号");
    JMenuItem moneyItem = new JMenuItem("赞助我们");
    //创建游戏主界面
    public GameJframe(){
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据(打乱数组)
        initDate();

        //初始化图片
        initImage();


        //开启可视化界面(默认是不可见的)写在最后
        this.setVisible(true);
    }
    int x , y;
    private void initDate() {
        //初始化数组
        int[] tempArr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        //获取随机数
        //并打乱数组
        Random random = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            //用于临时存放交换位置
            int temp;
            int r = random.nextInt(tempArr.length);
            //数据交换
            temp = tempArr[i];
            tempArr[i] = tempArr[r];
            tempArr[r] = temp;
        }
        //建立存放打乱后的数据的二维数组
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Arr[i][j] = tempArr[count];
                System.out.println(Arr[i][j]);
                count++;
                //记录空白图片的位置
                if (Arr[i][j] == 0){
                    x = i;
                    y = j;
                }
            }
        }
    }
    

    private void initImage() {
        //初始化数据
        this.getContentPane().removeAll();
        //判断游戏是否胜利
        if(Victory()){
            JLabel winJLabel = new JLabel(new ImageIcon("image/win.png"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);
        }
        //显示当前步数
        JLabel stepCount = new JLabel("步数："+step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        //循环添加拼图
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //获取图片的存放位置
                int number = Arr[i][j];
                //创建一个图片对象ImageIcon的对象
                //创建一个Jlabeld的对象(管理容器)
                JLabel jLabel = new JLabel(new ImageIcon(path+number+".jpg"));
                //指定图片位置
                jLabel.setBounds(105*i+83,105*j+134,105,105);
                //给拼图添加边框
                jLabel.setBorder(new BevelBorder(1));
                //获取窗体的隐藏容器,把管理容器添加到界面中
                this.getContentPane().add(jLabel);
            }
        }
        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("image/background.png"));
        background.setBounds(40,40,508,560);
        //把背景图片添加到界面当中
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();



    }

    private void initJMenuBar() {
        //创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();
        //创建菜单上面的选项
        JMenu functionJmenu = new JMenu("功能");
        JMenu aboutJmenu = new JMenu("关于我们");
        JMenu ChangeImg = new JMenu("更换图片");
        //将每一个选项下面的条目添加到选项当中
        functionJmenu.add(replayItem);
        functionJmenu.add(ChangeImg);
        functionJmenu.add(reLoginItem);
        functionJmenu.add(closeItem);

        aboutJmenu.add(accountItem);
        aboutJmenu.add(moneyItem);

        ChangeImg.add(girlImg);
        ChangeImg.add(animalImg);
        ChangeImg.add(sportImg);
        //给条目添加事件
        replayItem.addActionListener(this);
        girlImg.addActionListener(this);
        animalImg.addActionListener(this);
        sportImg.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        moneyItem.addActionListener(this);

        //将选项添加到菜单中
        jMenuBar.add(functionJmenu);
        jMenuBar.add(aboutJmenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //设置界面宽高
        this.setSize(603,680);
        //设置界面标题
        this.setTitle("拼图游戏单机版 V1.0");
        //设置界面置顶
        this.setAlwaysOnTop(false);//暂时关闭
        //设置界面在屏幕居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(3);
        //取消默认的居中方式,只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //判断游戏是否获胜
        if (Victory()){
            return;
        }
        int code = e.getKeyCode();
        if (code == 65){
            //查看拼图最终效果
            Viewall();
        }

    }

    private void Viewall() {
        //初始化图片
        this.getContentPane().removeAll();
        //添加完整图片
        JLabel jLabel = new JLabel(new ImageIcon(path+"all.jpg"));
        //指定图片位置
        jLabel.setBounds(83,134,105*4,105*4);
        //获取窗体的隐藏容器,把管理容器添加到界面中
        this.getContentPane().add(jLabel);
        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("image/background.png"));
        background.setBounds(40,40,508,560);
        //把背景图片添加到界面当中
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //读取键盘输入
        int code = e.getKeyCode();
        //按R重置游戏
        if (code == 82){
            replay();
        }
        //判断游戏是否胜利，如果胜利不进行下边的代码
        if (Victory()){
            return;
        }
        //左移
        if (code == 37){
            //越界判断
            if (x==3){
                return;
            }
            //图片位置交换
            Arr[x][y]=Arr[x+1][y];
            Arr[x+1][y]=0;
            x++;
            //每次移动步数增加
            step++;
            //加载并刷新图片
            initImage();
        //上移
        } else if (code == 38) {
            //越界判断
            if (y==3){
                return;
            }
            //图片位置交换
            Arr[x][y]=Arr[x][y+1];
            Arr[x][y+1]=0;
            y++;
            //每次移动步数增加
            step++;
            //加载并刷新图片
            initImage();
        //右移动
        } else if (code == 39) {
            //越界判断
            if (x==0){
                return;
            }
            //图片位置交换
            Arr[x][y]=Arr[x-1][y];
            Arr[x-1][y]=0;
            x--;
            //每次移动步数增加
            step++;
            //加载并刷新图片
            initImage();
        //下移
        } else if (code == 40) {
            //越界判断
            if (y==0){
                return;
            }
            //图片位置交换
            Arr[x][y]=Arr[x][y-1];
            Arr[x][y-1]=0;
            y--;
            //每次移动步数增加
            step++;
            //加载并刷新图片
            initImage();
        } else if (code == 65) {
            //返回原先界面
            initImage();
        }else if (code == 87) {
            //作弊直接获胜
            for (int i = 0; i < Arr.length; i++) {
                for (int j = 0; j < Arr[i].length; j++) {
                    int temp = win[i][j];
                    Arr[i][j]=temp;
                    }
                }
            initImage();
        }

    }
    //判断胜利条件是否符合
    private boolean Victory() {
        for (int i = 0; i < Arr.length; i++) {
            for (int j = 0; j < Arr[i].length; j++) {
                if (Arr[i][j]!=win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    //定义一个变量存放上一次的拼图序号,防止重复
    int lastIndex = index;
    private int RandomIndex(int number){
        //随机生成拼图序号
        int index = random.nextInt(number)+1;
        //比较生成的拼图序号与现在的拼图序号是否一致
        //若一致则重新生成
        while(index == lastIndex){
            index = random.nextInt(number)+1;
        }
        //更新目前拼图序号
        lastIndex = index;
        //返回拼图序号
        return index;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击的条目对象
        Object obj = e.getSource();
        //判断
        if (obj==replayItem){
            //重新游戏
            replay();
        } else if (obj == girlImg) {
            //随机选择一个美女拼图
            index = RandomIndex(13);
            //更改路径为生成的拼图
            path ="image/girl/girl"+index+"/";
            //刷新
            replay();
        } else if (obj == animalImg) {
            //随机选择一个动物拼图
            index = RandomIndex(8);
            //更改路径为生成的拼图
            path ="image/animal/animal"+index+"/";
            //刷新
            replay();
        } else if (obj == sportImg) {
            //随机选择一个运动拼图
            index = RandomIndex(10);
            //更改路径为生成的拼图
            path ="image/sport/sport"+index+"/";
            //刷新
            replay();
        } else if (obj==reLoginItem) {
            //重新登录
            //返回登录界面
            this.setVisible(false);
            //打开登录界面
            new LoginJframe();
        } else if (obj==closeItem) {
            //关闭游戏(虚拟机)
            System.exit(0);
        } else if (obj==accountItem) {
            //关于我们
            //创建弹窗
            JDialog DialogAbout = new JDialog();
            //创建图片容器
            JLabel jLabel = new JLabel(new ImageIcon("image/about.jpg"));
            jLabel.setBounds(0,0,258,258);
            //把图片添加到弹框中
            DialogAbout.getContentPane().add(jLabel);
            //设置弹框大小
            DialogAbout.setSize(344,344);
            //设置弹框置顶
            DialogAbout.setAlwaysOnTop(true);
            //设置弹框居中
            DialogAbout.setLocationRelativeTo(null);
            //打开弹框时无法操作其他窗体
            DialogAbout.setModal(true);
            DialogAbout.setTitle("个人微信二维码,感谢支持");
            //弹框可视化
            DialogAbout.setVisible(true);
        } else if (obj==moneyItem) {
            //赞助我们
        }
    }

    private void replay() {
        //再次打乱二维数组
        initDate();
        //步数清零
        step=0;
        //加载并刷新图片
        initImage();

    }
}
