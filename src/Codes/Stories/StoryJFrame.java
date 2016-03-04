package Codes.Stories;

import java.awt.*;
import javax.swing.*;

public class StoryJFrame extends JFrame{          //读小说窗体实现

	private static final long serialVersionUID = 1L;
	public StoryJFrame() throws Exception{        //构造函数
		setTitle("-——读小说——-");
		setSize(300,550);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container cp=getContentPane();
		Xiaoxiaoshuo xi=new Xiaoxiaoshuo();
		getContentPane().setLayout(new FlowLayout());
		final JTextArea jt=new JTextArea(16,16);
		jt.setText(xi.getXiaoshuo());
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
	/*public static void ReadXiaoshuo() throws Exception
	{
		StoryJFrame l=new StoryJFrame();
		//l.setResizable(false);
	}
	public static void main(String[] args) throws Exception {
		ReadXiaoshuo();
	}*/
}