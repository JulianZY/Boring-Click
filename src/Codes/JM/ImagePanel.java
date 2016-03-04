package Codes.JM;

import java.awt.*;
import java.net.URL;
import javax.swing.JPanel;

import Codes.ChangeBGP.Testbat;
import Codes.Jokes.*;
import Codes.ListenMusic.MusicPlayAudio;
import Codes.ReadBooks.*;
import Codes.Stories.*;
public class ImagePanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
    private static Image image;                             //主题背景图片
    private static Image rotateIcon;                        //转盘图片
    private double angle = 0;                               //旋转角度变量定义
    public static String PN=IrregularFormSample.PN2;        //获取主题图片
    private static Toolkit tk = Toolkit.getDefaultToolkit(); //调用工具类
    public ImagePanel() throws Exception{                    //构造函数
        URL bgUrl = getClass().getResource("../../Datas/Theme/"+PN); // 获取背景图片URL
        URL rotateUrl = getClass().getResource("../../Datas/Circles/all-zhen-little.png");// 获取转盘图片URL
        image = tk.createImage(bgUrl);// 加载背景图片
        rotateIcon = tk.createImage(rotateUrl);// 加载转盘图片
        setOpaque(false);// 使面板透明
        setLayout(null);
    }
    protected void paintComponent(Graphics g) {
        int width = 350;//getWidth();// 获取面板宽度
        int height = 350;//getHeight();// 获取高度
        if (image != null) {// 如果背景图片对象不为空
            g.drawImage(image, 0, 0, width, height, this);// 根据面板大小绘制图片
        }
        Graphics2D g1 = (Graphics2D) g.create();// 获取绘图上下文的副本
        // 设置绘图上下文以质量为主，绘制图片，这样可以避免图片旋转是的锯齿
        g1.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        if (rotateIcon != null) {// 如果转盘图片对象不为null
            int iconWidth = 300;//rotateIcon.getWidth(this);// 获取转盘图片宽度
            int iconHeight = 300;//rotateIcon.getHeight(this);// 获取转盘图片高度
            g1.rotate(Math.toRadians(angle), width / 2,
                    height / 2);// 设置旋转角度
            g1.drawImage(rotateIcon, width / 2 - iconWidth / 2,
                    height / 2 - iconHeight / 2, this);// 在面板中间绘制转盘图片
        }
    }
    
    int count = 0;
    int temp = 0;
    int randNum = 0;
    
    @Override
    public void run() {                                  //进程启动函数
        double stAngle = Math.random() * 360;
    	//double stAngle=180;
        angle = stAngle;
        //System.out.println(stAngle);
        while (angle +6<=stAngle + 1080) {// 角度小于1000
            angle += 10;// 累加角度值
            repaint();// 重写绘制界面
            try {
                Thread.sleep(10);// 线程休眠
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        double sleepTime = 10;// 定义休眠变量
        while(sleepTime < 90) {// 角度小于120
        	if(angle!=(int)(stAngle + 1080))
        	{
               angle += 1;// 累加角度值
               repaint();// 重写绘制界面
        	}
            try{
                Thread.sleep((int) (sleepTime+=5));// 休眠并累加休眠变量值
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(stAngle<=22.5d||stAngle>337.5d||(stAngle<=202.5d&&stAngle>=157.5d))
            	break;
            if(stAngle<=337.5d&&stAngle>292.5d)
            {
    			try {
    				new EssayJFrame();
    			} catch (Exception e1) {
    				e1.printStackTrace();
    			}
    			break;
            }
            if(stAngle<=292.5d&&stAngle>247.5d)
            {
    			try {
    				new LXHJFrame();
    			} catch (Exception e1) {
    				e1.printStackTrace();
    			}
    			break;
            }
            if(stAngle<=247.5d&&stAngle>202.5d)
            {
            	try {
            		MusicPlayAudio mu=new MusicPlayAudio();
            		mu.ListentoMusic();
    		    } catch (Exception e2) {
    			     e2.printStackTrace();
    		    }
    		     break;
            }
            if(stAngle<=157.5d&&stAngle>112.5d)
            {
    			try {
    				new StoryJFrame();
    			} catch (Exception e1) {
    				e1.printStackTrace();
    			}	
    			break;
            }
            if(stAngle<=112.5d&&stAngle>22.5d)
            {
            	try {
            		Testbat te=new Testbat();
    				te.ChangeDesktop();
    			} catch (Exception e1) {
    				e1.printStackTrace();
    			}
    			break;
            }
            //System.out.println(stAngle);
        }
    }
}
