package Codes.ListenMusic;

import java.applet.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import Codes.ListenMusic.SongList;

public class MusicPlayAudio extends JFrame {
	private static final long serialVersionUID = 1L;      
	private JPanel jContentPane = null;                   //���ö�������
	private JTextField filePath = null;                   //�ı��������ļ���Ϣ
	private JButton openFile = null;                      //���ļ�Ŀ¼��ť
	private JButton CloseJF = null;                       //�رղ��Ű�ť
	private JButton playButton = null;                    //���Ű�ť
	private JButton playLoopButton = null;                //ѭ�����Ű�ť
	private File selectedFile;                            //����ѡ�����
	private AudioClip audioClip;                          //�����ಥ�������
	static Point origin = new Point();                    //�����������ȡ���λ��
	public static JPopupMenu SubpopupMenu=new JPopupMenu();   //��������ʵ�ֲ������ر�
	public static JMenuItem cloItem=new JMenuItem("�رղ�����");    //�رղ�����
	
	private JTextField getJTextField() {                  //������Ϣ������
		if (filePath == null) {
			filePath = new JTextField();
			filePath.setPreferredSize(new Dimension(200, 22));
			filePath.setEditable(false);
		}
		return filePath;
	}
	
	// ��ȡѡ���ļ���ť�ķ���
	private JButton getOpenFile(String filepath) {
		final String f=filepath;
		if (openFile == null) {
			openFile = new JButton();
			openFile.setText("ѡ���ļ�");
			openFile.addActionListener(new java.awt.event.ActionListener() {
				
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setFileFilter(new FileNameExtensionFilter(
									"֧�ֵ���Ƶ�ļ���*.mid��*.wav��*.au", "wav",
									"au", "mid"));
					fileChooser.setCurrentDirectory(new File(f));
					fileChooser.showOpenDialog(null);
					selectedFile = fileChooser.getSelectedFile();
					filePath.setText(selectedFile.getName());
				}
			});
		}
		return openFile;
	}
	
	// ��ȡ�������Ű�ť�ķ���
	private JButton getPlayButton(String filepath) {
		final String fil=filepath;
		if (playButton == null) {
			playButton = new JButton();
			playButton.setText("��������");
			playButton
					.addActionListener(new java.awt.event.ActionListener() {
						
						public void actionPerformed(
								java.awt.event.ActionEvent e) {
							if (selectedFile != null) {
								try {
									if (audioClip != null)
										audioClip.stop();
									audioClip = Applet
											.newAudioClip(selectedFile.toURI().toURL());
									audioClip.play();
									selectedFile=null;
								} catch (MalformedURLException e1) {
									e1.printStackTrace();
								}
							}  
								else
								{
									SongList so=new SongList();
									File fi=null;
									try {
										fi = new File(fil+"/"+so.getFilename());
									} catch (Exception e2) {
										e2.printStackTrace();
									}
									try {
										if (audioClip != null)
											audioClip.stop();
										filePath.setText(fi.getName());
										audioClip = Applet
												.newAudioClip(fi.toURI().toURL());
										audioClip.play();
										selectedFile=null;
									} catch (MalformedURLException e1) {
										e1.printStackTrace();
									}
									
								}
						}
					});
		}
		return playButton;
	}
	
	// ��ȡѭ�����Ű�ť�ķ���
    private JButton getPlayLoopButton(String filepath) {
			final String fil=filepath;
			if (playLoopButton == null) {
				playLoopButton = new JButton();
				playLoopButton.setText("ѭ������");
				playLoopButton
						.addActionListener(new java.awt.event.ActionListener() {
							
							public void actionPerformed(
									java.awt.event.ActionEvent e) {
								if (selectedFile != null) {
									try {
										if (audioClip != null)
											audioClip.stop();
										audioClip = Applet
												.newAudioClip(selectedFile.toURI().toURL());
										audioClip.loop();
										selectedFile=null;
									} catch (MalformedURLException e1) {
										e1.printStackTrace();
									}
								}  
									else
									{
										SongList so=new SongList();
										File fi=null;
										try {
											fi = new File(fil+"/"+so.getFilename());
										} catch (Exception e2) {
											e2.printStackTrace();
										}
										try {
											if (audioClip != null)
												audioClip.stop();
											filePath.setText(fi.getName());
											audioClip = Applet
													.newAudioClip(fi.toURI().toURL());
											audioClip.loop();
											selectedFile=null;
										} catch (MalformedURLException e1) {
											e1.printStackTrace();
										}
										
									}
							}
						});
			}
			return playLoopButton;
		}
    //��ȡֹͣ���Ű�ť�ķ���
		private JButton getStopButton() {
			if (CloseJF == null) {
				CloseJF = new JButton();
				CloseJF.setText("ֹͣ����");
				CloseJF.addActionListener(new java.awt.event.ActionListener() {
							
							public void actionPerformed(java.awt.event.ActionEvent e) {
										if (audioClip != null)
											  audioClip.stop();
										filePath.setText("");
							}
				});
			}
			return CloseJF;
		}
		public void stopPlay(){              //�����ֹͣ���Ź���
			if (audioClip != null)
				  audioClip.stop();
			
		}
	    public MusicPlayAudio() throws Exception {          //���캯��1
		     super();
		     initialize();
	    }
	    public MusicPlayAudio(String p) throws Exception {   //���캯��2����
		    super();
		    initialize(p);
	    }
	   // ��ʼ������
	    private void initialize() throws Exception {
		   this.setSize(408, 79);
		   this.setContentPane(getJContentPane("C:/Program Files"));
		   this.setTitle("My Musicplayer");
	    }
	    //��ʼ������2����
	   private void initialize(String pa) throws Exception {
		   this.setSize(220, 130);
		   this.setContentPane(getJContentPane(pa));
		   getJContentPane(pa).addMouseListener(new MouseAdapter() {
		       // ��갴�����ͷ�ʱ�����÷���
		  public void mouseReleased(MouseEvent e) {
			   // �жϴ˴�����¼��Ƿ�Ϊ������ĵ����˵������¼�
			    // ����������ͷ�����λ�õ����˵�
			   if (e.isPopupTrigger())
				       SubpopupMenu.show(e.getComponent(), e.getX(), e.getY());
		   }
	      });
		   this.setTitle("My Musicplayer");
		   this.setResizable(false);
	   }
	   // ��ʼ���������ķ���
	   private JPanel getJContentPane(String path) throws Exception {
		  if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new FlowLayout());
			jContentPane.add(getJTextField(), null);
			jContentPane.add(getOpenFile(path), null);
			jContentPane.add(getPlayButton(path), null);
			jContentPane.add(getPlayLoopButton(path),null);
			jContentPane.add(getStopButton(),null);
			SubpopupMenu.add(cloItem);
			jContentPane.add(SubpopupMenu);
			
		  }
		   return jContentPane;
	   }
	   public  void ListentoMusic() throws Exception          //�����ֹ���ʵ�ֺ���
	   {
		//File filetemp=new File(".\\src");             //��ȡsrcĿ¼��·��
		//String srcPath=filetemp.getCanonicalPath();
		  URL gr=getClass().getResource("../../Datas/Musics/");
		  String s=gr.toURI().toString(); 
		  s=s.replaceAll("file:", "");
		//String s=file.getAbsolutePath();
		final MusicPlayAudio thisClass = new MusicPlayAudio(s);
		thisClass.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE );
		thisClass.setVisible(true);
		cloItem.addActionListener(new ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent e){
				thisClass.stopPlay();
				thisClass.dispose();
				    //Close();
				    //System.exit(0);
			}
		});
		//������ק�����¼������п��ޣ�
		/*thisClass.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {  //���£�mousePressed ���ǵ����������걻����û��̧��
                    origin.x = e.getX();  //����갴�µ�ʱ���ô��ڵ�ǰ��λ��
                    origin.y = e.getY();
            }
        });
		thisClass.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {  //�϶���mouseDragged ָ�Ĳ�������ڴ������ƶ�������������϶���
                
                    Point p =thisClass.getLocation();  //������϶�ʱ��ȡ���ڵ�ǰλ��
                    //���ô��ڵ�λ��
                    //���ڵ�ǰ��λ�� + ��굱ǰ�ڴ��ڵ�λ�� - ��갴�µ�ʱ���ڴ��ڵ�λ��
                    thisClass.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
            }
        });*/
		
	   }
	   /*public static void main(String[] args) throws Exception{   //���������
		   MusicPlayAudio mu=new MusicPlayAudio();
		   mu.ListentoMusic();
	   }*/
}