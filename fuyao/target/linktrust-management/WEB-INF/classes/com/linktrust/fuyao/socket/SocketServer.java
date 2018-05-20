package com.linktrust.fuyao.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

//基于TCP	协议的socket通信 实现用户登陆
public class SocketServer {
	public static void main(String[] args) {
		Socket socket = null;
		try {
			//创建一个服务器端socket 绑定并监听一个端口
			ServerSocket serverSocket = new ServerSocket(8888);
			System.out.println("服务端即将启动，等待客户端连接......");
			int count = 0;
			while(true){
				//调用accept()方法开始监听，等待客户端连接
				socket = serverSocket.accept();
				ServerThread serverThread = new ServerThread(socket);
				serverThread.start();
				count ++;
				System.out.println("客户端数量："+ count);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	}
}
