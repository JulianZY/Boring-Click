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
    private static Image image;                             //���ⱳ��ͼƬ
    private static Image rotateIcon;                        //ת��ͼƬ
    private double angle = 0;                               //��ת�Ƕȱ�������
    public static String PN=IrregularFormSample.PN2;        //��ȡ����ͼƬ
    private static Toolkit tk = Toolkit.getDefaultToolkit(); //���ù�����
    public ImagePanel() throws Exception{                    //���캯��
        URL bgUrl = getClass().getResource("../../Datas/Theme/"+PN); // ��ȡ����ͼƬURL
        URL rotateUrl = getClass().getResource("../../Datas/Circles/all-zhen-little.png");// ��ȡת��ͼƬURL
        image = tk.createImage(bgUrl);// ���ر���ͼƬ
        rotateIcon = tk.createImage(rotateUrl);// ����ת��ͼƬ
        setOpaque(false);// ʹ���͸��
        setLayout(null);
    }
    protected void paintComponent(Graphics g) {
        int width = 350;//getWidth();// ��ȡ�����
        int height = 350;//getHeight();// ��ȡ�߶�
        if (image != null) {// �������ͼƬ����Ϊ��
            g.drawImage(image, 0, 0, width, height, this);// ��������С����ͼƬ
        }
        Graphics2D g1 = (Graphics2D) g.create();// ��ȡ��ͼ�����ĵĸ���
        // ���û�ͼ������������Ϊ��������ͼƬ���������Ա���ͼƬ��ת�ǵľ��
        g1.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        if (rotateIcon != null) {// ���ת��ͼƬ����Ϊnull
            int iconWidth = 300;//rotateIcon.getWidth(this);// ��ȡת��ͼƬ���
            int iconHeight = 300;//rotateIcon.getHeight(this);// ��ȡת��ͼƬ�߶�
            g1.rotate(Math.toRadians(angle), width / 2,
                    height / 2);// ������ת�Ƕ�
            g1.drawImage(rotateIcon, width / 2 - iconWidth / 2,
                    height / 2 - iconHeight / 2, this);// ������м����ת��ͼƬ
        }
    }
    
    int count = 0;
    int temp = 0;
    int randNum = 0;
    
    @Override
    public void run() {                                  //������������
        double stAngle = Math.random() * 360;
    	//double stAngle=180;
        angle = stAngle;
        //System.out.println(stAngle);
        while (angle +6<=stAngle + 1080) {// �Ƕ�С��1000
            angle += 10;// �ۼӽǶ�ֵ
            repaint();// ��д���ƽ���
            try {
                Thread.sleep(10);// �߳�����
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        double sleepTime = 10;// �������߱���
        while(sleepTime < 90) {// �Ƕ�С��120
        	if(angle!=(int)(stAngle + 1080))
        	{
               angle += 1;// �ۼӽǶ�ֵ
               repaint();// ��д���ƽ���
        	}
            try{
                Thread.sleep((int) (sleepTime+=5));// ���߲��ۼ����߱���ֵ
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
