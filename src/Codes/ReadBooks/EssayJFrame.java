package Codes.ReadBooks;

import java.awt.*;
import javax.swing.*;

public class EssayJFrame extends JFrame{                   //��ɢ�Ĵ���ʵ��
	private static final long serialVersionUID = 1L;
	public EssayJFrame() throws Exception{
		setTitle("-����Ʒɢ�ġ���-");
		setSize(300,550);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		Container cp=getContentPane();
		Sanwen sa=new Sanwen();
		getContentPane().setLayout(new FlowLayout());
		final JTextArea jt=new JTextArea(16,16);
		jt.setText(sa.getSanwen());
		jt.setFont(new Font("�꿬��", Font.BOLD, 20));
		jt.setLineWrap(true);
		//jt.setBounds(3, 3,290, 500);
		jt.setEditable(false);
		JScrollPane js=new JScrollPane(jt);
		js.setSize(300, 300);
		//js.add(jt);
		cp.add(js);
		setVisible(true);
	}
	/*public static void ReadSanwen() throws Exception           //��ɢ�Ĺ���ʵ�ֺ���
	{
		EssayJFrame l=new EssayJFrame();
		//l.setResizable(false);
	}
	public static void main(String[] args) throws Exception {    //���������ܲ���
		ReadSanwen();
	}*/
}
