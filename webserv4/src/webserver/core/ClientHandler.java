package webserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import webserver.http.EmptyRequestException;
import webserver.http.HttpRequest;

public class ClientHandler implements Runnable {
       private Socket socket;
       private InputStream in;
	public ClientHandler(Socket socket) {
	
		this.socket = socket;
	
	}
       //处理业务的线程
	@Override
	public void run() {
		try {
			System.out.println("开始处理业务");
			/*
			 * 处理客户端请求的大致流程
			 * 1:解析请求
			 * 2:处理请求
			 * 3:响应客户端
			 * */
			 HttpRequest request=new HttpRequest(socket);
			
		} catch (EmptyRequestException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
