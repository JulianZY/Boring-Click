package Codes.ListenMusic;
import java.io.*;
import java.net.URL;

//import Codes.ListenMusic.Musicplay;
public class SongList {

	public String[] getList() throws Exception        //获取音乐文件名列表
	{
		  //File filetemp=new File(".\\src");             //获取src目录的路径
		  //String srcPath=filetemp.getCanonicalPath();
		  URL gr=getClass().getResource("../../Datas/Musics/");
		  File file=new File(gr.toURI());
		  String test[];
		  test=file.list();
		  return test;
	}
	public static int countFile(String path)                   //计算文件数目
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
		   System.out.println("找不到指定路径！");
		  }
		  return sum;
	}
	public  String getFilename() throws Exception    //随机获取音乐文件名
	{
		int x=(int)(Math.random()*(getList().length));
		return getList()[x];
	}
	/*public static void main(String[] args) throws InterruptedException {
		for(int i=0;i<getList().length;i++)
		  {
		   System.out.println(getList()[i]);
		  }
		System.out.println(countFile("src\\Datas\\Musics\\"));
		Musicplay mp=new Musicplay("src\\Datas\\Musics\\"+new File(SongList.getFilename()));
		mp.play();
		mp.wait(7000);
		mp.close();
		
	}*/

}
