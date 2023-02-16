package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
//注册
public class RegisterUI extends JFrame {
    private JPanel p;
    private JLabel lblName,lblPwd,lblRePwd,lblMsg;
    private JTextField txtName;
    private JPasswordField txtPwd,txtRePwd;
    private JButton btnReg;

    public RegisterUI(){
        super("注册新用户");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(280,250);
        setLocationRelativeTo(null);    //使得窗口出现在屏幕中央
        setVisible(true);

        p = new JPanel(null);
        lblName = new JLabel("用户名");
        lblPwd = new JLabel("密码");
        lblRePwd = new JLabel("确认密码");
        lblMsg = new JLabel();
        lblMsg.setForeground(Color.red);
        txtName = new JTextField(20);
        txtPwd = new JPasswordField(20);
        txtRePwd = new JPasswordField(20);
        txtPwd.setEchoChar('*');
        txtRePwd.setEchoChar('*');
        btnReg = new JButton("注册");

        //注册确定按钮事件的处理
        btnReg.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //设置标签信息为空，清空原来的历史信息
                lblMsg.setText("");
                //获取输入的用户名和密码
                String strName  =  txtName.getText();
                if(strName == null || strName.equals("")){
                    lblMsg.setText("用户名不能为空");
                    return;
                }
                //获取用户输入的密码
                String strPwd = new String(txtPwd.getPassword());
                if(strPwd == null || strPwd.equals("")){
                    lblMsg.setText("密码不能为空!");
                    return;
                }
                //获取用户确认输入密码
                String strRePwd = new String(txtRePwd.getPassword());
                if(strRePwd == null || strRePwd.equals("")){
                    lblMsg.setText("确认密码不能为空!");
                    return;
                }
                //判断密码和确认密码是否一致
                if(!strRePwd.equals(strPwd)){
                    lblMsg.setText("密码与确认密码不一致!");
                    return;
                }
                lblMsg.setText("注册成功!");
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("userandpassword.txt",true));
                    writer.write(strName + " " + strPwd);
                    writer.newLine();
                    writer.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                return;
            }
        });
        lblName.setBounds(30,30,60,25);
        txtName.setBounds(95,30,120,25);
        lblPwd.setBounds(30,60,60,25);
        txtPwd.setBounds(95,60,120,25);
        lblRePwd.setBounds(30,90,60,25);
        txtRePwd.setBounds(95,90,120,25);
        lblMsg.setBounds(90,125,180,25);
        btnReg.setBounds(100,165,60,25);
        //将组件添加到面板上
        p.add(lblName);
        p.add(txtName);
        p.add(lblPwd);
        p.add(txtPwd);
        p.add(lblRePwd);
        p.add(txtRePwd);
        p.add(lblMsg);
        p.add(btnReg);
        this.add(p);
    }
}
