package Codes.ChangeBGP;

import java.io.*;
import java.net.URL;

public class Testbat{
	static String p1="D:/Workspaces/MyEclipseProfessional2014/BoringClick/bin/Datas/Wallpapers/1.jpg",
		p2=p1="D:/Workspaces/MyEclipseProfessional2014/BoringClick/bin/Datas/Wallpapers/1.jpg";
	public  String[] getList() throws Exception      //��ȡ���汳��ͼƬ�����б�
	{
		  //File filetemp=new File(".\\src");             //��ȡsrcĿ¼��·��
		  //String srcPath=filetemp.getCanonicalPath();
		  URL gr=getClass().getResource("../../Datas/Wallpapers/");
		  //System.out.println(gr.toURI());
		  //String s=URL.toString();
		  File file=new File(gr.toURI());
		 //System.out.println(file.getAbsolutePath());
		  String test[];
		  test=file.list();
		  return test;
	}
	public static int countFile(String path)               //���㱳��ͼƬ�ļ���Ŀ
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
	public String getFilename() throws Exception    //�����ȡ����ͼƬ����
	{
		int x=(int)(Math.random()*(getList().length));
		//p2=p1;
		p1=getList()[x];
		//System.out.println(p1);
		return p1;
	}
	public static String readFile(File file) throws Exception {  //��ȡbat�ļ�����
		  BufferedReader br = new BufferedReader(new FileReader(file));
		  StringBuffer sbf = new StringBuffer("");
		  String line = null;
		  while ((line = br.readLine()) != null) {
		   sbf.append(line).append("\r\n");// ���ж�ȡ��׷�ӻ���\r\n
		  }
		  br.close();
		  return sbf.toString();
		 }
	public static void WriteTxtToFile(File file, String content) {   // ������д��bat�ļ�
		   if (file.exists()) {
		    file.delete();
		   }
		   byte[] b = content.getBytes();
		   try {
		    @SuppressWarnings("resource")
			OutputStream writer = new FileOutputStream(file);
		    writer.write(b);
		   } catch (FileNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   } catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }
		}
    public void initbat() throws Exception       //��ȡ��ʼ��bat�ļ���
    {
    	//File filetemp=new File(".\\src");             //��ȡsrcĿ¼��·��
		//String srcPath=filetemp.getCanonicalPath();
    	URL gr=getClass().getResource("../../Datas/Wallpapers/");
    	URL gr2=getClass().getResource("../../Datas/");
		String s=gr.toURI().toString();  
		String s2=gr2.toURI().toString();
		s=s.replaceAll("file:", "");
		s2=s2.replaceAll("file:", "");
    	File file=new File(s+"/bat.bat");
    	//System.out.println(file.getAbsolutePath());
    	File file2=new File(s2+"/bat-init.txt");
    	//System.out.println(file2.getPath());
    	String re=readFile(file2);
    	WriteTxtToFile(file,re);
    }
	public  File createFile() throws Exception    //���±���ͼƬ��Ϣp1,p1;
	{
		//File filetemp=new File(".\\src");             //��ȡsrcĿ¼��·��
		URL gr=getClass().getResource("../../Datas/Wallpapers/");
		String s=gr.toURI().toString(); 
		s=s.replaceAll("file:", "");
		File file=new File(s+"/bat.bat");
		//System.out.println(file.getAbsolutePath());
		//FileInputStream in=new FileInputStream("src\\Datas\\Wallpapers\\bat.bat");
		String re=readFile(file);
		 System.out.println(re);
		String p3=getFilename();
		System.out.println(p3);
		if(p3=="bat.bat"||p3=="1.jpg")
		{
			p3="2.jpg";p1=p3;
		}
		String p4=(file.getParentFile()).getAbsolutePath();
		// System.out.println(p4);
		p4=p4.replace('\\', '/');
		// System.out.println(p4);
		String re2=re.replaceAll(p2,p4+'/'+p3);
		//String re2=re.replaceAll(p4+'/'+p2,p4+'/'+p3);
		/*p2=p4+'/'+p3;*/
		 System.out.println(re2);
		//FileInputStream in=new FileInputStream(re);
		WriteTxtToFile(file,re2);
		return file;
	}
	public  void ChangeDesktop()throws Exception             //���������ֽ����ʵ�ֺ���
	{
		initbat();
        String path=createFile().getAbsolutePath();
        String command="cmd.exe /c"+"start /min "+path;
        try{
            Runtime child = Runtime.getRuntime();
                    child.exec(command);  
                    child.exec(command); 
                    child.exec(command); 
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
	}
	/*public static void main(String[] args) throws Exception {     //���������ܼ��
		Testbat te=new Testbat();
		te.ChangeDesktop();
	}*/
}
