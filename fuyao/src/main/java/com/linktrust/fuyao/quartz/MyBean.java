package com.linktrust.fuyao.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component("myBean")
public class MyBean {
	public void printMessage(){
		SimpleDateFormat sf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String format = sf.format(new Date());
		System.out.println("quartz........"+format);
	}
}
