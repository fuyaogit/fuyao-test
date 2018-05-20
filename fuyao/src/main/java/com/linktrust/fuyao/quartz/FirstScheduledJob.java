package com.linktrust.fuyao.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;


public class FirstScheduledJob extends QuartzJobBean{
	
	private AnotherBean anotherBean;
	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// TODO Auto-generated method stub
		SimpleDateFormat sf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String format = sf.format(new Date());
		System.out.println("executeInternal........"+format);
		anotherBean.printAnotherMessage();
	}
	public AnotherBean getAnotherBean() {
		return anotherBean;
	}
	public void setAnotherBean(AnotherBean anotherBean) {
		this.anotherBean = anotherBean;
	}
	
}
