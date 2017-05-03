package com.yz.job;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.yz.service.JudgeService;

@Component
public class HelloJob extends QuartzJobBean {

	private JudgeService judgeService;

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
	}

	public JudgeService getJudgeService() {
		return judgeService;
	}

	@Resource
	public void setJudgeService(JudgeService judgeService) {
		this.judgeService = judgeService;
	}

}
