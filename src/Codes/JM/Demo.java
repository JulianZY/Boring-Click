package Codes.JM;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.sun.awt.AWTUtilities;

import Codes.ChangeBGP.Testbat;
import Codes.Jokes.*;
import Codes.ListenMusic.MusicPlayAudio;
import Codes.ReadBooks.*;
import Codes.Stories.*;

@SuppressWarnings({ "unused", "restriction", "serial" })
public class Demo extends IrregularFormSample{                 //ת��������ʵ����
    
	   private static ImagePanel imagePanel1;
       private Thread imageThread1;
       static Point origin = new Point(); 
       public class ButtonActionListener implements ActionListener 
       {
		        private ImagePanel imagePanel=imagePanel1;
                private Thread imageThread=imageThread1;
                private ButtonActionListener(ImagePanel imagePanel) 
                {
                        this.imagePanel = imagePanel;
                }
                public void actionPerformed(final ActionEvent e) 
                {
                    if (imageThread == null || !imageThread.isAlive()) 
                    {
                             imageThread = new Thread(imagePanel);
                             imageThread.start();
                    } 
                    else if (!imageThread.isAlive()) 
                    {
                             imageThread.start();
                    }
                }
        }
        public Demo() throws Exception                               //���캯������ʼ����
        {
              super();
              setTitle("����ʱ����һ��,�ͻ��ÿ���һ��");            //���⣨������������
              setResizable(false);
              setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //�����ô���Ĭ�Ϲر�
              this.setLocationRelativeTo(null);                     //���������λ��
              setLocation(1000, 360);                               //����ת���������λ������
              final ImagePanel imagePanel = new ImagePanel();
              getContentPane().add(imagePanel, BorderLayout.CENTER);
              final JButton button = new JButton();
              final JPopupMenu popupMenu = new JPopupMenu();// ��������ʽ�˵�����
		       // Ϊ����Ķ��������������¼�������
		      getContentPane().addMouseListener(
		       new MouseAdapter() 
		       {// ��갴�����ͷ�ʱ�����÷���
			         public void mouseReleased(MouseEvent e)
			         {
				        // �жϴ˴�����¼��Ƿ�Ϊ������ĵ����˵������¼�
				        // ����������ͷ�����λ�õ����˵�
				        if (e.isPopupTrigger())
					    popupMenu.show(e.getComponent(), e.getX(), e.getY());
			         }
		       }                                );
		       final JMenu SecondMenu = new JMenu("����");  //�����Ӳ˵�
		       popupMenu.add(SecondMenu);
		       
		       final JMenuItem ChangeItem = new JMenuItem("������");   //�������ֽ����
		       ChangeItem.addActionListener(
		    	new ActionListener()
		    	{
				public void actionPerformed(ActionEvent e)
			       {
				       Testbat t=new Testbat();
				      try 
				      {
					       t.ChangeDesktop();
				      } 
				      catch (Exception e1) 
				      {
					       e1.printStackTrace();
				      }
		           }
		        }                          );
		       SecondMenu.add(ChangeItem);
		
		      final JMenuItem MusicItem = new JMenuItem("������");      //�����ֹ���
		      MusicItem.addActionListener(
		      new ActionListener()
		      {
				public void actionPerformed(ActionEvent e)
			       {
				       MusicPlayAudio m=null;
				       try 
				       {
					        m= new MusicPlayAudio();
					        m.ListentoMusic();
				       } 
				       catch(Exception e2)
				       {
					        e2.printStackTrace();
				       }
		           }
		      }                          );
		      SecondMenu.add(MusicItem);
		
		     final JMenuItem ReadSanwenItem = new JMenuItem("����ɢ��");     //��ɢ�Ĺ���
		     ReadSanwenItem.addActionListener(
		      new ActionListener()
		      {
			        public void actionPerformed(ActionEvent e)
			        {
				          try {
					           EssayJFrame rsa=new EssayJFrame();
				              }
				          catch (Exception e1)
				          {
					             e1.printStackTrace();
				          }
		             }
		      }                              );
		      SecondMenu.add(ReadSanwenItem);
		
		     final JMenuItem ReadStoryItem = new JMenuItem("����С˵");       //��С˵����
		     ReadStoryItem.addActionListener(
		     new ActionListener()
		       {
			           public void actionPerformed(java.awt.event.ActionEvent e)
			           {
				       try {
					            StoryJFrame rst=new StoryJFrame();
				           } catch (Exception e1) 
				           {
					         e1.printStackTrace();
				            }
		               }
		      }                             );
		      SecondMenu.add(ReadStoryItem);
		
		      final JMenuItem ReadJokesItem = new JMenuItem("����Ц��");       //��Ц������
		      ReadJokesItem.addActionListener(
		      new ActionListener()
		      {
			       public void actionPerformed(java.awt.event.ActionEvent e)
			       {
				       LXHJFrame rj=null;
				   try {
					       rj = new LXHJFrame();
				    } catch (Exception e2) {
					     e2.printStackTrace();
				    }
		           }
		     }                               );
		     SecondMenu.add(ReadJokesItem);

		     final JMenuItem LittleItem = new JMenuItem("��С��");       //��С��
		     LittleItem.addActionListener(new ActionListener(){
                       public void actionPerformed(ActionEvent e) {
		     setState(JFrame.ICONIFIED);
			}
		    }                 );
		     popupMenu.add(LittleItem);
		
		    final JMenuItem CloseItem = new JMenuItem("�˳�����");        //�˳�������
		    CloseItem.addActionListener(new ActionListener(){
			      public void actionPerformed(ActionEvent e) {
				         System.exit(0);        //�˳�
			      }
		    });
		    popupMenu.add(CloseItem);
		
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            button.setPressedIcon(new ImageIcon(getClass()
            		.getResource("../../Datas/Circles/bt.png")));          //���°�ťЧ��
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.addActionListener(new ButtonActionListener(imagePanel));
            button.setIcon(new ImageIcon(getClass()
            		.getResource("../../Datas/Circles/bt-s.png")));
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorder(null);
            button.setBounds(105, 52, 139, 209);      //���ð�ťλ�ü���С
            imagePanel.add(button);
        }
        public static void main(String args[])        //����������
        {
               EventQueue.invokeLater(
                 new Runnable() 
                 {
                       public void run() 
                       {
                           try 
                           {
                              Demo frame = new Demo();
                              frame.setVisible(true);
                           } 
                           catch (Exception e) 
                           {
                              e.printStackTrace();
                           }
                       }
                 }                   );
        }
}
