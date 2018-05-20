package com.linktrust.fuyao.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ServerThread extends Thread{
	
	Socket socket = null;
	
	public ServerThread(Socket socket){
		this.socket = socket;
	}
	
	public void run(){
		try {
			InetAddress inetAddress = socket.getInetAddress();
			String hostAddress = inetAddress.getHostAddress();
			//获取输入流，读取客户端信息
			InputStream is = socket.getInputStream();//字节输入流
			InputStreamReader isr = new InputStreamReader(is);//字节流转换为字符流
			BufferedReader br = new BufferedReader(isr);//为输入流添加缓冲
			String info = null;
			while((info = br.readLine())!=null){
				System.out.println("服务端接收到客户端"+hostAddress+"的信息："+info);
			}
			socket.shutdownInput();//关闭输入流
			
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("欢迎您！");
			pw.flush();
			
			pw.close();
			os.close();
			br.close();
			isr.close();
			is.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
