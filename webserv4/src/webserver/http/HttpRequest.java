package webserver.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
	private Socket socket;
	private InputStream in;
	/*
	 * 请求行相关信息定义
	 */
	// 请求方式
	private String method;
	// 请求的资源路径
	private String url;
	// 请求使用的协议版本
	private String protocol;
//消息头
	private Map<String,String> headers=new HashMap<String,String>();
	public HttpRequest(Socket socket) throws EmptyRequestException {

		try {
			this.socket = socket;
			in = socket.getInputStream();
			/*
			 * 1.解析请求行 2.解析消息头 3.解析消息正文
			 * 
			 */
			// 1
			parseRequestLine();
			// 2
			parseHeaders();
			// 3
			parseContent();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * 1.解析请求行
	 */
	private void parseRequestLine() throws EmptyRequestException {
		String str =readLine();
        String [] arr=str.split(" ");
        System.out.println("请求行"+str);
        if(arr.length>=3){
        	 method=arr[0];
         	// 请求的资源路径
             url=arr[1];
         	// 请求使用的协议版本
         	protocol=arr[2];
        }else{
        	throw new EmptyRequestException("空请求");	
        }
       

	}
	

	// 解析消息头
	private void parseHeaders() {
		String line=null;
		while(true){
			line=readLine();
			System.out.println("消息头line:"+line);
			if("".equals(line)){
				break;
			}
			String [] arr=line.split(": ");
			headers.put(arr[0],arr[1]);
			
		}
		System.out.println("headers:"+headers);
		System.out.println("解析消息头完毕");
	}

	// 解析消息正文
 private void parseContent()  {
		
	}

	

	private String readLine() {
		StringBuilder builder = new StringBuilder();

		int d = -1;
		char c1 = '1', c2 = '1';
		try {
			while ((d = in.read()) != -1) {
				c2 = (char) d;
				if (c1 == 13 && c2 == 10) {
					break;
				}
				builder.append(c2);
				c1 = c2;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return builder.toString().trim();

	}
}
