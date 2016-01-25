package com.yz.util;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogAspectj {
	
	@Before("execution(public  com.yz.service.imp.* add(..))")  
	public void addLog()
	{
		System.out.println("新增了一条什么记录");
	}
}
