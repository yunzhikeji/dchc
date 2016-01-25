package com.yz.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import ognl.DefaultTypeConverter;

public class DateTimeConvertor extends DefaultTypeConverter {
	private DateFormat[] dateFormat={
			new SimpleDateFormat("yyyy-MM-dd"),
			new SimpleDateFormat("yyyy/MM/dd"),
			new SimpleDateFormat("yy-MM-dd"),
	};
	private DateFormat[] timeFormat={
			new SimpleDateFormat("HH:mm:ssss"),
			new SimpleDateFormat("HH:mm"),
	};
	@Override
	public Object convertValue(Map context, Object value, Class toType) {
		// TODO Auto-generated method stub
		if(toType.equals(java.sql.Date.class))
		{
			String[] parameterValues = (String[]) value;
			for (DateFormat format : dateFormat) {
				try {
					if(parameterValues[0]!=null&&!parameterValues[0].equals(""))
					{
						return new java.sql.Date(format.parse(parameterValues[0]).getTime());
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}else if(toType.equals(java.sql.Time.class)){
			String[] parameterValues = (String[]) value;
			for (DateFormat format : dateFormat) {
				try {
					if(parameterValues[0]!=null&&!parameterValues[0].equals(""))
					{
						return new java.sql.Time(format.parse(parameterValues[0]).getTime());
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}else if(toType.equals(java.util.Date.class)){
			String[] parameterValues = (String[]) value;
			for (DateFormat format : dateFormat) {
				try {
					if(parameterValues[0]!=null&&!parameterValues[0].equals(""))
					{
						return format.parse(parameterValues[0]);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}else if(toType.equals(java.util.Calendar.class)){
			String[] parameterValues = (String[]) value;
			for (DateFormat format : dateFormat) {
				try {
					if(parameterValues[0]!=null&&!parameterValues[0].equals(""))
					{
						java.util.Date date=format.parse(parameterValues[0]);
						Calendar calendar=Calendar.getInstance();
						calendar.setTime(date);
						return calendar;
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}else if(toType.equals(String.class)){
			if(value instanceof java.sql.Date){
				
			}else if(value instanceof java.sql.Time){
				
			}else if (value instanceof java.util.Date) {
				return dateFormat[0].format((java.util.Date)value);
			}else if(value instanceof java.util.Calendar){
				StringBuffer buffer= new StringBuffer();   
	            Calendar o = (Calendar)value;   
	            buffer.append(o.get(Calendar.YEAR));   
	            buffer.append("-").append(o.get(Calendar.MONTH) + 1);   
	            buffer.append("-").append(o.get(Calendar.DATE)); 
	            buffer.append(" ").append(o.get(Calendar.HOUR)); 
	            buffer.append(":").append(o.get(Calendar.MINUTE)); 
	            buffer.append(":").append(o.get(Calendar.SECOND)); 
	            return buffer.toString();

			}
		}
		return super.convertValue(context, value, toType);
	}

}
