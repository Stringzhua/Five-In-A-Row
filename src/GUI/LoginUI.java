package GUI;
import Start.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//登录
public class LoginUI extends JPanel {
    private JButton bt1 = null,bt2 = null,bt3 = null;
    private JLabel user = null,password = null;
    private JTextField userName = null;
    private JPasswordField passwordField = null;
    private Login login = null;
    ArrayList <User> users = new ArrayList<User>();

    public LoginUI(Login frame){
        setLayout(null);//设置布局为空布局
        login = frame;
        initbtn();
        //设置标签属性
        user = new JLabel("用户名:");
        password = new JLabel("密码:");
        user.setBounds(110,50,50,30);
        password.setBounds(110,100,50,30);

        //设置文本框和密码框
        userName = new JTextField(10);
        passwordField = new JPasswordField(10);
        passwordField.setEchoChar('*');
        userName.setBounds(160,50,100,30);
        passwordField.setBounds(160,100,100,30);
        //将各个组件添加到面板内
        add(userName);
        add(passwordField);
        add(bt1);
        add(bt2);
        add(bt3);
        add(user);
        add(password);
    }
    public void initbtn(){
        //设置按钮属性
        bt1 = new JButton("登录");
        bt2 = new JButton("注册");
        bt3 = new JButton("修改密码");
        bt1.setSize(80,30);
        bt2.setSize(80,30);
        bt3.setSize(100,30);

        bt1.setLocation(50,150);
        bt2.setLocation(150,150);
        bt3.setLocation(250,150);
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterUI();
            }
        });
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePasswordUI();
            }
        });
        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("userandpassword.txt"));
                    String line = null;
                    while((line = reader.readLine()) != null){
                        String []idAndpwd = line.split(" ");
                        users.add(new User(idAndpwd[0],idAndpwd[1]));
                    }
                    String strId = new String(userName.getText());
                    String strPwd = new String(passwordField.getPassword());
                    if(strId == null || strId.equals("")){
                        JOptionPane.showMessageDialog(null, "用户名不能为空!",
                                "登录失败", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    if(strPwd == null || strPwd.equals("")){
                        JOptionPane.showMessageDialog(null, "密码不能为空!",
                                "登录失败", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    for(User temp : users){
                        if(strId.equals(temp.getId()))
                        {
                            if(temp.check(strPwd)){
                                login.setVisible(false);
                                GameUI f=new GameUI();
                                f.setVisible(true);
                                return;
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "密码错误!",
                                        "登录失败", JOptionPane.INFORMATION_MESSAGE);
                                return;
                            }
                        }
                    }
                    System.out.println("登录失败，用户名不存在!");
                    JOptionPane.showMessageDialog(null, "用户名不存在!",
                            "登录失败", JOptionPane.INFORMATION_MESSAGE);
                    return;
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        });
    }
}
