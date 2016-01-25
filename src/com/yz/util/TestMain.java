package com.yz.util;


public class TestMain {

	/**
	 * @param args
	 */
	private static String infoExtractionMsg;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("系统管理".contains("超级"));
//		System.out.println(NumberUtils.isNumber("adfadf"));
		String msg = "提取手机信息,提取银行卡信息,提取DNA,提取指纹,提取鞋印";
			
		handleInfoExtractionMsg(msg);
		
		System.out.println(infoExtractionMsg);
		
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
