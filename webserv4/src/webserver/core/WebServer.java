package webserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
   private ServerSocket server;
   
	public WebServer() {
	try {
		System.out.println("开始启动服务端");
		server=new ServerSocket(8080);
		System.out.println("启动服务端完毕");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

	public void start(){
		try {
			System.out.println("等待客户端连接");
			
			while(true){
				Socket socket=server.accept();
				//启动一个线程处理该客户端请求
				ClientHandler handler = new ClientHandler(socket);
				Thread t = new Thread(handler);
				t.start();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		WebServer server = new WebServer();
		server.start();

	}

}
