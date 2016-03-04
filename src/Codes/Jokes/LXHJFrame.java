package Codes.Jokes;

import java.awt.*;
import javax.swing.*;

public class LXHJFrame extends JFrame{                  //实现笑话显示窗体
	private static final long serialVersionUID = 1L;
	public LXHJFrame() throws Exception{                //构造函数
		setTitle("-——看笑话——-");
		setSize(300,550);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container cp=getContentPane();
		Lengxiaohua le=new Lengxiaohua();
		getContentPane().setLayout(new FlowLayout());
		final JTextArea jt=new JTextArea(16,16);
		jt.setText(le.getJokes());
		jt.setFont(new Font("标楷体", Font.BOLD, 20));
		jt.setLineWrap(true);
		//jt.setBounds(3, 3,290, 500);
		jt.setEditable(false);
		JScrollPane js=new JScrollPane(jt);
		js.setSize(300, 300);
		//js.add(jt);
		cp.add(js);
		setVisible(true);
	}
	/*public static void ReadJoke() throws Exception   //读笑话功能实现函数
	{
		LXHJFrame l=new LXHJFrame();
		//l.setResizable(false);
	}
	public static void main(String[] args) throws Exception {   //主函数功能测试
		ReadJoke();
	}*/
}
