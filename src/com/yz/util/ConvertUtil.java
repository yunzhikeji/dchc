package com.yz.util;

public class ConvertUtil {
	
	 public static int[] StringtoInt(String str) {  
		  
			String[] ids = str.split(",");
			
			int ret[] = new int[ids.length]; 
			
		    for(int i=0;i<ids.length;i++)
		    {
		    	 ret[i] = Integer.valueOf(ids[i]);  
		    }
		   return ret;  
		  
		 }  
	 
	 
	 public static void main(String[] args) {
		
		 String ids1 = ",";
		 String ids2 = "1,2,3,4,5,";
		 
		 for(int i=0;i<StringtoInt(ids1).length;i++)
		 {
			 System.out.println(StringtoInt(ids1)[i]);
		 }
		 
		 for(int i=0;i<StringtoInt(ids2).length;i++)
		 {
			 System.out.println(StringtoInt(ids2)[i]+".");
		 }
	}

}
