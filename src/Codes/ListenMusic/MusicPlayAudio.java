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
	private JPanel jContentPane = null;                   //设置顶层容器
	private JTextField filePath = null;                   //文本域：音乐文件信息
	private JButton openFile = null;                      //打开文件目录按钮
	private JButton CloseJF = null;                       //关闭播放按钮
	private JButton playButton = null;                    //播放按钮
	private JButton playLoopButton = null;                //循环播放按钮
	private File selectedFile;                            //歌曲选择变量
	private AudioClip audioClip;                          //调用类播放器组件
	static Point origin = new Point();                    //坐标变量，获取鼠标位置
	public static JPopupMenu SubpopupMenu=new JPopupMenu();   //弹出窗口实现播放器关闭
	public static JMenuItem cloItem=new JMenuItem("关闭播放器");    //关闭播放器
	
	private JTextField getJTextField() {                  //歌曲信息栏设置
		if (filePath == null) {
			filePath = new JTextField();
			filePath.setPreferredSize(new Dimension(200, 22));
			filePath.setEditable(false);
		}
		return filePath;
	}
	
	// 获取选择文件按钮的方法
	private JButton getOpenFile(String filepath) {
		final String f=filepath;
		if (openFile == null) {
			openFile = new JButton();
			openFile.setText("选择文件");
			openFile.addActionListener(new java.awt.event.ActionListener() {
				
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setFileFilter(new FileNameExtensionFilter(
									"支持的音频文件（*.mid、*.wav、*.au", "wav",
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
	
	// 获取单曲播放按钮的方法
	private JButton getPlayButton(String filepath) {
		final String fil=filepath;
		if (playButton == null) {
			playButton = new JButton();
			playButton.setText("单曲播放");
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
	
	// 获取循环播放按钮的方法
    private JButton getPlayLoopButton(String filepath) {
			final String fil=filepath;
			if (playLoopButton == null) {
				playLoopButton = new JButton();
				playLoopButton.setText("循环播放");
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
    //获取停止播放按钮的方法
		private JButton getStopButton() {
			if (CloseJF == null) {
				CloseJF = new JButton();
				CloseJF.setText("停止播放");
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
		public void stopPlay(){              //类调用停止播放功能
			if (audioClip != null)
				  audioClip.stop();
			
		}
	    public MusicPlayAudio() throws Exception {          //构造函数1
		     super();
		     initialize();
	    }
	    public MusicPlayAudio(String p) throws Exception {   //构造函数2含参
		    super();
		    initialize(p);
	    }
	   // 初始化方法
	    private void initialize() throws Exception {
		   this.setSize(408, 79);
		   this.setContentPane(getJContentPane("C:/Program Files"));
		   this.setTitle("My Musicplayer");
	    }
	    //初始化方法2含参
	   private void initialize(String pa) throws Exception {
		   this.setSize(220, 130);
		   this.setContentPane(getJContentPane(pa));
		   getJContentPane(pa).addMouseListener(new MouseAdapter() {
		       // 鼠标按键被释放时触发该方法
		  public void mouseReleased(MouseEvent e) {
			   // 判断此次鼠标事件是否为该组件的弹出菜单触发事件
			    // 如果是则在释放鼠标的位置弹出菜单
			   if (e.isPopupTrigger())
				       SubpopupMenu.show(e.getComponent(), e.getX(), e.getY());
		   }
	      });
		   this.setTitle("My Musicplayer");
		   this.setResizable(false);
	   }
	   // 初始化内容面板的方法
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
	   public  void ListentoMusic() throws Exception          //听音乐功能实现函数
	   {
		//File filetemp=new File(".\\src");             //获取src目录的路径
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
		//设置拖拽窗体事件（可有可无）
		/*thisClass.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {  //按下（mousePressed 不是点击，而是鼠标被按下没有抬起）
                    origin.x = e.getX();  //当鼠标按下的时候获得窗口当前的位置
                    origin.y = e.getY();
            }
        });
		thisClass.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {  //拖动（mouseDragged 指的不是鼠标在窗口中移动，而是用鼠标拖动）
                
                    Point p =thisClass.getLocation();  //当鼠标拖动时获取窗口当前位置
                    //设置窗口的位置
                    //窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
                    thisClass.setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y);
            }
        });*/
		
	   }
	   /*public static void main(String[] args) throws Exception{   //检测主方法
		   MusicPlayAudio mu=new MusicPlayAudio();
		   mu.ListentoMusic();
	   }*/
}