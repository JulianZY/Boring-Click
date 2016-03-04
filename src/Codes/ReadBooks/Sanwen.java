package Codes.ReadBooks;
import java.io.*;
import java.net.URL;
import java.util.*;

import javax.swing.*;
public class Sanwen extends JFrame{
	private static final long serialVersionUID = 1L;
	public static Boolean exsits(String s,String x)       //����ַ������ں���
	{
		for(int i=0;i<s.length();i++)
		{
			if(s.indexOf(x)!=-1)
				return true;
		}
		return false;
	}
	public static List<String> readFilein(File file)      //�ü������ȡ���������б�
	{     
		List<String> List = new ArrayList<String>();
		StringBuffer fileContent = new StringBuffer("");     
	    try   
	    {            
	        if(file.isFile()&&file.exists())  
	        {       
	            InputStreamReader read = new InputStreamReader(new FileInputStream(file),"gbk");       
	            BufferedReader reader=new BufferedReader(read);       
	            String line=null;       
	            while ((line = reader.readLine()) != null)
	            {        
	            	if(!exsits(line,"#"))
	                    fileContent.append(line).append("\r\n");
	            	else
	            	{
	            		List.add(fileContent.toString());
	            		fileContent.delete(0,fileContent.toString().length());;
	            	}
	            }         
	            read.close();      
	        }     
	    } catch (Exception e)   
	    {         
	        e.printStackTrace();     
	    }     
	    return List;   
	}   
	public static String readFile(File file)           //��ȡ�ļ����ݺ���
	{         
		StringBuffer sbf = new StringBuffer("");
	    try   
	    {           
	        if(file.isFile()&&file.exists())  
	        {       
	            InputStreamReader read = new InputStreamReader(new FileInputStream(file),"UTF-8");       
	            BufferedReader reader=new BufferedReader(read);   
	            String line;       
	            while ((line = reader.readLine()) != null)   
	            {           
	            	sbf.append(line).append("\r\n");// ���ж�ȡ��׷�ӻ���\r\n
	            }         
	            read.close();      
	        }     
	    } catch (Exception e)   
	    {         
	        e.printStackTrace();     
	    }     
	    return sbf.toString();   
	}   
	/*public static void WriteTxtToFile(File file, String content) {
		   if (file.exists()) {
		    file.delete();
		   }
		   byte[] b = content.getBytes();
		   try {
		    OutputStream writer = new FileOutputStream(file);
		    writer.write(b);
		    //return true;
		   } catch (FileNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   } catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }
		   //return false;
		}*/
	public static void WriteTxtToFile(File file, String fileContent)   //д���ļ����ݺ���
	{     
	    try   
	    {      
	        //File f = new File(fileName);      
	        if (!file.exists())   
	        {       
	            file.createNewFile();      
	        }      
	        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file),"gbk");      
	        BufferedWriter writer=new BufferedWriter(write);          
	        writer.write(fileContent);      
	        writer.close();     
	    } catch (Exception e)   
	    {      
	        e.printStackTrace();     
	    }  
	}  
	public  File getTXT() throws Exception        //ɢ�Ļ�ú���
	{
		//File filetemp=new File(".\\src");             //��ȡsrcĿ¼��·��
		//String srcPath=filetemp.getCanonicalPath();
		//File file1=new File(srcPath+"/Datas/Databases/sanwen.txt");
		//File file2=new File(srcPath+"/Datas/Databases/sanwe.txt");
		URL gr=getClass().getResource("../../Datas/Databases/");
		String s=gr.toURI().toString(); 
		s=s.replaceAll("file:", "");
		File file1=new File(s+"/sanwen.txt");
		File file2=new File(s+"/sanwe.txt");
		String s1=readFile(file1);
		//System.out.println(s1);
		String s2=s1.replaceAll("(\\d)+#","#\r\n");
	    //System.out.println(s2);
	    WriteTxtToFile(file2,s2);
	    return file2;
	}
	public  String getSanwen() throws Exception      //��ƪɢ�Ļ�ù���ʵ�ֺ���    
	{
	    File file=getTXT();
        List<String> sqlList = new ArrayList<String>();
        sqlList=readFilein(file);
        System.out.println();
        int x=(int)(Math.random()*(sqlList.size()-1));
        return sqlList.get(x);
	}
	/*public static void main(String[] args) throws Exception
	{
		Sanwen sa=new Sanwen();
		System.out.println(sa.getSanwen());
	}*/

}