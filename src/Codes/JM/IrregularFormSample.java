package Codes.JM;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Area;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.sun.awt.AWTUtilities;

@SuppressWarnings("restriction")
public class IrregularFormSample extends JFrame {

  private static final long serialVersionUID = 1L;
  private Point origin;                                 //�����ƶ�����
  private Image img;                                    //�����趨���岻������ʽ��ͼƬ
  public static String PN2;                             //��̬������ȡ��������
   //����
  public String[] getList() throws Exception     //��ȡ�����ļ������б�
	{
		  //File filetemp=new File(".\\src");             //��ȡsrcĿ¼��·��
		  //String srcPath=filetemp.getCanonicalPath();
	      URL gr=getClass().getResource("../../Datas/Theme/");
	      //System.out.println(gr.toURI());
	      String s=gr.toURI().toString();
	      s=s.replaceAll("file:/", "");
	      System.out.println(s);
		  File file=new File(s);
		  System.out.println(file.getAbsolutePath());
		  String test[];
		  test=file.list();
		  return test;
	}
	public static int countFile(String path)            //�����ļ���Ŀ
	{
		  int sum = 0;
		  try{
		     File file = new File(path);
		     File[] list = file.listFiles();
		     for (int i = 0;i<list.length;i++){
		        if(list[i].isFile()){
		        sum++;
		       }
		       else{
		       sum+=countFile(list[i].getPath());
		       }
		    }
		  }catch(NullPointerException ne){
		   System.out.println("�Ҳ���ָ��·����");
		  }
		  return sum;
	}
	public String getFilename() throws Exception  //�����ȡ�����ļ���
	{
		int x=(int)(Math.random()*(getList().length));
		String s=getList()[x];
		//System.out.println(s);
		return s;
	}
  
	//����
  public IrregularFormSample() throws Exception {         //���캯��
    super();

    /* ���ȳ�ʼ��һ��ͼƬ�����ǿ���ѡ��һ����͸�����ֵĲ�����ͼƬ
     * (��Ȼ����Ҫѡ��֧��Alpha(͸��)���ͼƬ��ʽ����PNG)������
     * ͼƬ������������������״��ͬ�Ĳ�������
    */
    MediaTracker mt=new MediaTracker(this);
    //img=Toolkit.getDefaultToolkit().createImage("E:/yellow-candle.png");
    //img=Toolkit.getDefaultToolkit().createImage("E:/.png");
     PN2=this.getFilename();
    //System.out.println(PN2);
    //System.out.println(getClass().getResource("../../Datas/Theme/"+PN2));
    System.out.println(IrregularFormSample.class.getResource("../../Datas/Theme/"+PN2).getPath());
   img=Toolkit.getDefaultToolkit().createImage(IrregularFormSample.class.getResource("../../Datas/Theme/"+PN2).getPath());

    mt.addImage(img, 0);
    try {
      mt.waitForAll();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      initialize();//�����ʼ��
    } catch (IOException e) {
      e.printStackTrace();
    }    
  }

    /**
    * �����ʼ��
    * @throws IOException
    */
    private void initialize() throws IOException {
    //�趨�����С��ͼƬһ����
    this.setSize(img.getWidth(null), img.getHeight(null));
    //�趨���ô���װ�Σ�������ȡ����Ĭ�ϵĴ���ṹ
    this.setUndecorated(true);
    //��ʼ�������ƶ������ԭ��
    this.origin=new Point();

    //����AWTUtilities��setWindowShape�����趨������Ϊ�ƶ���Shape��״
    AWTUtilities.setWindowShape(this,getImageShape(img));        
    //�趨����ɼ���
    AWTUtilities.setWindowOpacity(this, 1.0f);

    this.setLocationRelativeTo(null);

    //����ȡ����Ĭ�ϵĴ���ṹ����������Ҫ�ֶ�����һ���ƶ�����ķ���
    addMouseListener( 
        new MouseAdapter(){
          public void mousePressed(MouseEvent e){
            origin.x = e.getX();
            origin.y = e.getY();
          }
          public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
          }
          @Override
          public void mouseEntered(MouseEvent e) {
            repaint();              
          }   
        }
    );

    addMouseMotionListener(
        new MouseMotionAdapter(){
          public void mouseDragged(MouseEvent e){
            Point p =    getLocation();
            setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y );
          }          
        }
    );    
  }

  /**
    * ��Imageͼ��ת��ΪShapeͼ��
    * @param img
    * @param isFiltrate
    * @return Imageͼ���Shapeͼ�α�ʾ
    * @author Hexen
    */
  public Shape getImageShape(Image img) {               //��ȡ������Ϣ����������ͼƬ
    ArrayList<Integer> x=new ArrayList<Integer>();
    ArrayList<Integer> y=new ArrayList<Integer>();    
    int width=img.getWidth(null);//ͼ����
    int height=img.getHeight(null);//ͼ��߶�

    //ɸѡ����
    //���Ȼ�ȡͼ�����е�������Ϣ
    PixelGrabber pgr = new PixelGrabber(img, 0, 0, -1, -1, true);
    try {
      pgr.grabPixels();
    } catch (InterruptedException ex) {
      ex.getStackTrace();
    }
    int pixels[] = (int[]) pgr.getPixels();

    //ѭ������
    for (int i = 0; i < pixels.length; i++) {
      //ɸѡ������͸�������ص�������뵽����ArrayList x��y��      
      int alpha = getAlpha(pixels[i]);
      if (alpha == 0){
        continue;        
      }else{
        x.add(i%width>0 ? i%width-1:0);
        y.add(i%width==0 ? (i==0 ? 0:i/width-1):i/width);
      }      
    }

    //����ͼ����󲢳�ʼ��(0Ϊ͸��,1Ϊ��͸��)
    int[][] matrix=new int[height][width];    
    for(int i=0;i<height;i++){
      for(int j=0;j<width;j++){
        matrix[i][j]=0;
      }
    }

    //��������ArrayList�еĲ�͸��������Ϣ
    for(int c=0;c<x.size();c++){
      matrix[y.get(c)][x.get(c)]=1;
    }

    /* ����Area������ʾ������Խ��кϲ���������һˮƽ"ɨ��"ͼ������ÿһ�У�
     * ����͸������������ΪRectangle���ٽ�ÿһ�е�Rectangleͨ��Area���rec
     * ������кϲ�������γ�һ��������Shapeͼ��
     */
    Area rec=new Area();
    int temp=0;

    for(int i=0;i<height;i++){
      for(int j=0;j<width;j++){
        if(matrix[i][j]==1){
          if(temp==0)
            temp=j;  
          else if(j==width){
            if(temp==0){
              Rectangle rectemp=new Rectangle(j,i,1,1);
              rec.add(new Area(rectemp));
            }else{
              Rectangle rectemp=new Rectangle(temp,i,j-temp,1);
              rec.add(new Area(rectemp));
              temp=0;
            }
          }
        }else{
          if(temp!=0){
            Rectangle rectemp=new Rectangle(temp,i,j-temp,1);
            rec.add(new Area(rectemp));
            temp=0;
          }
        }
      }
      temp=0;
    }
    return rec;
  }

  /**
    * ��ȡ���ص�Alphaֵ
    * @param pixel
    * @return ���ص�Alphaֵ
    */
  private int getAlpha(int pixel) {
//    System.out.println("pixel:"+pixel);
//    System.out.println("alpha:"+((pixel >> 24) & 0xff));
    return (pixel >> 24) & 0xff;
  }

  /* ���ǿ���ѡ���ڴ����ϻ���ͼƬ���Ǵ�����ȫ���ֳ�ͼƬ����ʽ��
    * ��Ȼ����Ҳ���Ը�����Ҫ��������������ȡ��������
    */
  @Override
  public void paint(Graphics g) {
    super.paint(g);
    g.drawImage(img, 0, 0, null);
  }

  /*public static void main(String[] args) throws Exception {       //���������ܲ���
        IrregularFormSample sample = new IrregularFormSample();
        sample.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //ͨ������GraphicsEnvironment��getDefaultScreenDevice������õ�ǰ����Ļ�豸��
        //GraphicsDevice gd = ge.getDefaultScreenDevice();
        // ȫ������
        //gd.setFullScreenWindow(null);
        sample.setVisible(true);
  }*/
}