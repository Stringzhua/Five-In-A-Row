package GUI;
import Start.ChessBoard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;
/*
 * 五子棋的主框架，程序启动类
 */
public class GameUI extends JFrame {
    private ChessBoard chessBoard;//对战面板
    private Panel toolbar;//工具条面板
    private Button startButton;//设置开始按钮
    private Button backButton;//设置悔棋按钮
    private Button exitButton;//设置退出按钮
    public GameUI() {
        Date date = new Date();
        DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        setTitle("单机版五子棋" + mediumDateFormat.format(date));//设置标题
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false);//禁止鼠标拉拽窗口大小
        chessBoard = new ChessBoard();//初始化面板对象，创建和添加菜单
        MyItemListener lis = new MyItemListener();//初始化按钮事件监听器内部类
        toolbar = new Panel();//工具面板栏实例化
        startButton = new Button("重新开始");
        backButton = new Button("悔棋");
        exitButton = new Button("退出游戏");//三个按钮初始化
        toolbar.setLayout(new FlowLayout(FlowLayout.CENTER));//将工具面板按钮用FlowLayout布局
        toolbar.add(backButton);
        toolbar.add(startButton);
        toolbar.add(exitButton);//将三个按钮添加到工具面板上
        startButton.addActionListener(lis);
        backButton.addActionListener(lis);
        exitButton.addActionListener(lis);//将三个按钮事件注册监听事件
        add(toolbar, BorderLayout.SOUTH);//将工具面板布局到界面南方也就是下面
        add(chessBoard);//将面板对象添加到窗体上
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置界面关闭事件
        pack();//自适应大小
    }
    private class MyItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();//获取事件源
            if (obj == startButton) {
                String[] options = {"是","否"};
                int response = JOptionPane.showOptionDialog(null, "您确定要重新开始游戏吗？", "重新开始",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        options[0]);
                if(response == 0) {
                    System.out.println("重新开始...");//重新开始
                    chessBoard.restartGame();
                }
                if(response == 1) {
                    System.out.println("游戏继续...");//游戏继续
                }
            } else if (obj == exitButton) {
                String[] options = {"是","否"};
                int response = JOptionPane.showOptionDialog(null, "您确定要退出游戏吗？", "退出游戏",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        options,
                        options[0]);
                if(response == 0) {
                    System.out.println("您已成功退出游戏！");
                    System.exit(0);//结束应用程序
                }
                if(response == 1) {
                    System.out.println("游戏继续...");//游戏继续
                }
            } else if (obj == backButton) {
                chessBoard.Goback();
                String msg=String.format("悔棋", null);
                JOptionPane.showMessageDialog(null, msg);
                System.out.println("悔棋...");//悔棋
            }
        }
    }
}