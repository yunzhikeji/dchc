package com.yz.util;

import com.yz.vo.AjaxMsgVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Administrator on 2017/4/18.
 */
public class AjaxMsgUtil {

	private AjaxMsgVO ajaxMsgVO;


	private  AjaxMsgUtil() {
	}

	private AjaxMsgUtil(AjaxMsgVO ajaxMsgVO) {
		this.ajaxMsgVO = ajaxMsgVO;
	}



	public static void outputJSONObjectToAjax(HttpServletResponse response, AjaxMsgVO msgVO)
	{
		JSONObject jsonObj = JSONObject.fromObject(msgVO);
		outputJSONOToAjax(response,jsonObj.toString());
	}


	public static void outputJSONArrayToAjax(HttpServletResponse response, List<AjaxMsgVO> ajaxMsgVOList)
	{
		JSONArray jsonArray = JSONArray.fromObject(ajaxMsgVOList);
		outputJSONOToAjax(response,jsonArray.toString());
	}


	public static void outputJSONOToAjax(HttpServletResponse response, String json)
	{
		PrintWriter out;
		try {
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




}
