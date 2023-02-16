package GUI;

import Start.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;
//修改密码
public class ChangePasswordUI extends JFrame {
    private JPanel p;
    private JLabel lblName,lblPwd,lblRePwd,lblMsg;
    private JTextField txtName;
    private JPasswordField txtPwd,txtRePwd;
    private JButton btnReg;
    private ArrayList<User> users = null;
    public ChangePasswordUI(){
        super("修改密码");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(280,250);
        setLocationRelativeTo(null);    //使得窗口出现在屏幕中央
        p = new JPanel(null);
        lblName = new JLabel("用户名");
        lblPwd = new JLabel("原密码");
        lblRePwd = new JLabel("新密码");
        lblMsg = new JLabel();//提示信息类
        lblMsg.setForeground(Color.red);
        txtName = new JTextField(20);
        txtPwd = new JPasswordField(20);
        txtRePwd = new JPasswordField(20);
        txtPwd.setEchoChar('*');//密码回显符,用于密码安全
        txtRePwd.setEchoChar('*');
        btnReg = new JButton("确定");
        //注册确定按钮事件的处理
        btnReg.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                users = new ArrayList<User>();
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
                    lblMsg.setText("原密码不能为空!");
                    return;
                }
                //获取用户确认输入密码
                String strRePwd = new String(txtRePwd.getPassword());
                if(strRePwd == null || strRePwd.equals("")){
                    lblMsg.setText("新密码不能为空!");
                    return;
                }
                //将旧用户名和密码读入users中
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("userandpassword.txt"));
                    String line = null;
                    while ((line = reader.readLine()) != null){
                        String []idAndpwd = line.split(" ");
                        users.add(new User(idAndpwd[0],idAndpwd[1]));
                    }
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } catch (IOException exception){
                    exception.printStackTrace();
                }
                boolean flag = false;
                //判断用户名和原密码是否一致
                for(User temp : users){
                    if(strName.equals(temp.getId()))
                    {
                        if(temp.check(strPwd)){
                            //System.out.println("修改成功!");
                            temp.setPwd(strRePwd);
                            lblMsg.setText("修改成功!");
                            flag = true;
                            break;
                        }
                        else{
                            lblMsg.setText("原密码错误!");
                            return;
                        }
                    }
                }
                //把新密码写入到文件中
                if(flag){
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("userandpassword.txt"));
                        for(User temp : users){
                            writer.write(temp.getId() + " " + temp.getPwd());
                            writer.newLine();
                            writer.flush();
                        }
                        writer.close();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }else{
                    lblMsg.setText("用户名不存在!");
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
        setVisible(true);
    }
}