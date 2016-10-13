package com.yz.util;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;


public class TestMain {
	
	

	/**
	 * @param args
	 */
	private static String infoExtractionMsg;
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		//System.out.println("系统管理".contains("超级"));
//		System.out.println(NumberUtils.isNumber("adfadf"));
		/*String msg = "提取手机信息,提取银行卡信息,提取DNA,提取指纹,提取鞋印";
			
		handleInfoExtractionMsg(msg);


		System.out.println(infoExtractionMsg);
		
*/
		/*String msg =",";
		boolean isTrue = (msg==",");
		System.out.println(isTrue);*/
		
		
		String ids = "157,240,220,261,92,118,187,115,264,267,185,263,159,94,242,68,265,135,67,136,52,282,266,69,95,96,133,281,76,237,268,152,155,212,117,269,132,239,238,180,213,55,158,161,243,114,260,246,184,53,241,54,48,186,181,93,130,218,179,160,102,245,173,219,72,103,112,233,50,113,131,150,74,215,214,111,177,174,244,175,51,176,211,232,154,283,70,151,80,137,99,134,262,236,77,89,216,119,97,178,156,75,71,138,116,217,280,139,73,98,153,91,63,254,251,143,275,225,230,252,148,127,274,279,141,78,146,272,125,277,271,167,86,64,145,126,270,144,87,79,253,90,88,149,58,122,248,62,172,258,235,249,278,106,234,65,231,107,110,66,147,29,222,247,165,49,82,83,121,170,182,120,183,101,171,257,221,259,256,166,123,224,250,100,164,223,169,28,85,3,105,129,27,1,228,46,255,163,227,104,124,84,276,45,229,226,168,81,109,162,47,140,108,273,128,142,";
		String id = "2122";
		
		System.out.println(isContainID(ids,id));
		
		
	}
	
	
	private static boolean isContainID(String ids,String id)
	{
		
		String[] idString = ids.split(",");
		
		List<String> list = Arrays.asList(idString);  
		
		return  list.contains(id);
		
	}
	
	private static void handleInfoExtractionMsg(String infoExtraction) {
		// TODO Auto-generated method stub
		if(infoExtraction!=null&&infoExtraction.length()>0&&infoExtraction.contains(","))
		{
			String[] infoExtractions = infoExtraction.split(",");
			infoExtractionMsg = "{'";
			for(int i=0;i<infoExtractions.length;i++)
			{
				infoExtractionMsg = infoExtractionMsg+infoExtractions[i]+"','";
			}
			infoExtractionMsg = (infoExtractionMsg.substring(0, infoExtractionMsg.length()-2)+"}").trim();
		}else
		{
			infoExtractionMsg =  "{}";
		}
		
	}

}
