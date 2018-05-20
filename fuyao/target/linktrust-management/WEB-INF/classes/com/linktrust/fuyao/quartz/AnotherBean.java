package com.linktrust.fuyao.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component("anotherBean")
public class AnotherBean {
	public void printAnotherMessage(){
		SimpleDateFormat sf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		String format = sf.format(new Date());
		System.out.println("anotherBean........"+format);
	}
}
