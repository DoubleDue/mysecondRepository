package com.example.sharelp_utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import android.util.Log;

/**
 * 
 * �ϴ�������
 * @author spring sky
 *
 */
public class UploadUtil extends Thread{
    private static final String TAG = "uploadFile";
    private static final int TIME_OUT = 10*1000;   //��ʱʱ��
    private static final String CHARSET = "utf-8"; //���ñ���
    private File file ;
    private String RequestURL;
    private String username;
    /**
     * android�ϴ��ļ���������
     * @param file  ��Ҫ�ϴ����ļ�
     * @param RequestURL  �����rul
     * @return  ������Ӧ������
     */
    
    
    
    public UploadUtil(File file,String RequestURL,String username) {
		this.file=file;
		this.RequestURL=RequestURL;
    	this.username=username+".png";
	}
    
    @Override
    public void run() {
    	

        String result = null;
        String  BOUNDARY =  UUID.randomUUID().toString();  //�߽��ʶ   �������
        String PREFIX = "--" , LINE_END = "\r\n"; 
        String CONTENT_TYPE = "multipart/form-data";   //��������
        StringBuilder ssb=new StringBuilder(RequestURL);
        try {	
            URL url = new URL(ssb.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(900);
            conn.setConnectTimeout(900);
            conn.setDoInput(true);  //����������
            conn.setDoOutput(true); //���������
            conn.setUseCaches(false);  //������ʹ�û���
            conn.setRequestMethod("POST");  //����ʽ
          //  conn.setRequestProperty("Charset", CHARSET);  //���ñ���
            conn.setRequestProperty("connection", "keep-alive");   
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);   
        	
            if(file!=null)
            {
            	Log.e("sdffsfs", RequestURL+file.getName());
                /**
                 * ���ļ���Ϊ�գ����ļ���װ�����ϴ�
                 */
                OutputStream dos = conn.getOutputStream();
                Log.e("--------sdffsfs", RequestURL+file.getName());
                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINE_END);
                /**
                 * �����ص�ע�⣺
                 * name�����ֵΪ����������Ҫkey   ֻ�����key �ſ��Եõ���Ӧ���ļ�
                 * filename���ļ������֣�������׺����   ����:abc.png  
                 */
                
                //�˴������޸�������ͼƬ������
                sb.append("Content-Disposition: form-data; name=\"img\"; filename=\""+username+"\""+LINE_END); 
                sb.append("Content-Type: application/octet-stream; charset="+CHARSET+LINE_END);
                sb.append(LINE_END);
                dos.write(sb.toString().getBytes());
            
                InputStream is = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int len = 0;
                while((len=is.read(bytes))!=-1)
                {
                    dos.write(bytes, 0, len);
                }
                is.close();
                dos.write(LINE_END.getBytes());
                byte[] end_data = (PREFIX+BOUNDARY+PREFIX+LINE_END).getBytes();
                dos.write(end_data);
                dos.flush();
                /**
                 * ��ȡ��Ӧ��  200=�ɹ�
                 * ����Ӧ�ɹ�����ȡ��Ӧ����  
                 */
                int res = conn.getResponseCode();  
                Log.e(TAG, "response code:"+res);
//                if(res==200)
//                {
                    Log.e(TAG, "request success");
                    InputStream input =  conn.getInputStream();
                    StringBuffer sb1= new StringBuffer();
                    int ss ;
                    while((ss=input.read())!=-1)
                    {
                        sb1.append((char)ss);
                    }
                    result = sb1.toString();
                    Log.e(TAG, "result : "+ result);
//                }
//                else{
//                    Log.e(TAG, "request error");
//                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    	
    }
    
   
}