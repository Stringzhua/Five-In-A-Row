package Start;
import GUI.Login;
import GUI.LoginUI;

public class Start {
	//主类，游戏以及登录界面启动类
	//用户的密码，账户信息以txt文件的方式进行存储
	//用到的技术：io流\javaswing\java的graphics2d绘图接口，绘制棋盘以及棋子
	//接下来，给大家演示
	public static void main(String[] args){
		Login login = new Login("登录");
		LoginUI loginUI = new LoginUI(login);
		login.add(loginUI);
		login.setVisible(true);
	}
}