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
public class Demo extends IrregularFormSample{                 //转盘主函数实现类
    
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
        public Demo() throws Exception                               //构造函数（初始化）
        {
              super();
              setTitle("无聊时轻轻一点,就会变得开心一点");            //标题（反正看不见）
              setResizable(false);
              setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //不设置窗体默认关闭
              this.setLocationRelativeTo(null);                     //不设置相对位置
              setLocation(1000, 360);                               //设置转盘在桌面的位置坐标
              final ImagePanel imagePanel = new ImagePanel();
              getContentPane().add(imagePanel, BorderLayout.CENTER);
              final JButton button = new JButton();
              final JPopupMenu popupMenu = new JPopupMenu();// 创建弹出式菜单对象
		       // 为窗体的顶层容器添加鼠标事件监听器
		      getContentPane().addMouseListener(
		       new MouseAdapter() 
		       {// 鼠标按键被释放时触发该方法
			         public void mouseReleased(MouseEvent e)
			         {
				        // 判断此次鼠标事件是否为该组件的弹出菜单触发事件
				        // 如果是则在释放鼠标的位置弹出菜单
				        if (e.isPopupTrigger())
					    popupMenu.show(e.getComponent(), e.getX(), e.getY());
			         }
		       }                                );
		       final JMenu SecondMenu = new JMenu("功能");  //创建子菜单
		       popupMenu.add(SecondMenu);
		       
		       final JMenuItem ChangeItem = new JMenuItem("换桌面");   //换桌面壁纸功能
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
		
		      final JMenuItem MusicItem = new JMenuItem("听音乐");      //听音乐功能
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
		
		     final JMenuItem ReadSanwenItem = new JMenuItem("读点散文");     //读散文功能
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
		
		     final JMenuItem ReadStoryItem = new JMenuItem("读点小说");       //读小说功能
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
		
		      final JMenuItem ReadJokesItem = new JMenuItem("读点笑话");       //读笑话功能
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

		     final JMenuItem LittleItem = new JMenuItem("最小化");       //最小化
		     LittleItem.addActionListener(new ActionListener(){
                       public void actionPerformed(ActionEvent e) {
		     setState(JFrame.ICONIFIED);
			}
		    }                 );
		     popupMenu.add(LittleItem);
		
		    final JMenuItem CloseItem = new JMenuItem("退出程序");        //退出主程序
		    CloseItem.addActionListener(new ActionListener(){
			      public void actionPerformed(ActionEvent e) {
				         System.exit(0);        //退出
			      }
		    });
		    popupMenu.add(CloseItem);
		
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            button.setPressedIcon(new ImageIcon(getClass()
            		.getResource("../../Datas/Circles/bt.png")));          //按下按钮效果
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.addActionListener(new ButtonActionListener(imagePanel));
            button.setIcon(new ImageIcon(getClass()
            		.getResource("../../Datas/Circles/bt-s.png")));
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorder(null);
            button.setBounds(105, 52, 139, 209);      //设置按钮位置及大小
            imagePanel.add(button);
        }
        public static void main(String args[])        //程序主函数
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
