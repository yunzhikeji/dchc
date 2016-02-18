package com.yz.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 *
 * @author Administrator
 */
public class Send {

    public static String SMS(String postData, String postUrl) {
        try {
            //发送POST请求
            URL url = new URL(postUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setUseCaches(false);
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Length", "" + postData.length());
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(postData);
            out.flush();
            out.close();

            //获取响应状态
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("connect failed!");
                return "connect failed!";
            }
            //获取响应内容体
            String line, result = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            while ((line = in.readLine()) != null) {
                result += line + "\n";
            }
            in.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return "";
    }
    
    public static String sendSMS(String phones,String content) throws Exception{
    	String PostData = "sname=dlyzzn00&spwd=jsjlj477937&scorpid=&sprdid=1012808&sdst="+phones+"&smsg="+java.net.URLEncoder.encode(content,"utf-8");
        //out.println(PostData);
        String ret = Send.SMS(PostData, "http://cf.lmobile.cn/submitdata/Service.asmx/g_Submit");
        return ret;
    }
    
    public static String parseSmsResultXml(String xmltext){
		Document document;
		try {
			document = DocumentHelper.parseText(xmltext);
		
			Element rootEle=document.getRootElement();
			//listNodes(rootEle);
		    Element StateEle = rootEle.element("State");
		    Element MsgIDEle = rootEle.element("MsgID");
		    Element MsgStateEle = rootEle.element("MsgState");
		    Element ReserveEle = rootEle.element("Reserve");
		    return StateEle.getText()+"-"+MsgIDEle.getText()+"-"+MsgStateEle.getText()+"-"+ReserveEle.getText();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "exception";
		}
		    
		 
	}
}
