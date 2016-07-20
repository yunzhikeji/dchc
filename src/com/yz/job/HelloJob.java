package com.yz.job;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.yz.service.IJudgeService;

@Component
public class HelloJob extends QuartzJobBean {

	private IJudgeService judgeService;

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
	}

	public IJudgeService getJudgeService() {
		return judgeService;
	}

	@Resource
	public void setJudgeService(IJudgeService judgeService) {
		this.judgeService = judgeService;
	}

}
