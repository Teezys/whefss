package com.murong.ecp.netpay.whefss;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpByteTest {
	private static final Integer port = 8888;//HTTP默认端口8888

	public static void main(String[] args) {
		ServerSocket serverSocket;

		try {
			//建立服务器Socket,监听客户端请求
			serverSocket = new ServerSocket(port);
			System.out.println("Server is running on port:"+serverSocket.getLocalPort());
			//死循环不间断监听客户端请求
			while(true){
				Socket socket = serverSocket.accept();
				System.out.println("biuld a new tcp link with client,the cient address:"+
						socket.getInetAddress()+":"+socket.getPort());
				//并发处理HTTP客户端请求
				service(socket);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void service(final Socket socket)
	{
		new Thread(){
			public void run(){
				InputStream inSocket;
				try {
					//获取HTTP请求头
					inSocket = socket.getInputStream();
					int size = inSocket.available();
					byte[] buffer = new byte[size];
					inSocket.read(buffer);
					String request = new String(buffer);
					System.out.println("ClientBrowser:\n"+request+"\n"
							+ "------------------------------------------------------------------");
					String method = request.substring(0,4);
					String firstLineOfRequest = "";
					String[] heads;
					String uri = "/index.html";
					String contentType ="";
					if(request.length() > 0){
						firstLineOfRequest = request.substring(0,request.indexOf("\r\n"));
						heads = firstLineOfRequest.split(" ");
						uri = heads[1];

						if(uri.indexOf("html") != -1){
							contentType = "text/html";
						}else{
							contentType = "application/octet-stream";
						}
					}
					//将响应头发送给客户端
					String responseFirstLine = "HTTP/1.1 200 OK\r\n";

					String responseHead = "Content-Type:" + contentType +";charset=UTF-8"+"\r\n";

					OutputStream outSocket = socket.getOutputStream();
					System.out.println("ServerResponse:\n"+responseFirstLine+"\n"+responseHead+"\n"
							+ "--------------------------------------------------------------------");
					outSocket.write(responseFirstLine.getBytes());
					outSocket.write(responseHead.getBytes());
					//通过HTTP请求中的uri读取相应文件发送给客户端
//                    FileInputStream writehtml = new FileInputStream(new File("http"+uri));
					System.out.println(method);
					FileInputStream writehtml = new FileInputStream(new File("C:\\Users\\hasee\\Desktop\\东亚\\45.武汉升级改造\\http\\"+uri+"\\"+method.trim()+"\\result"));

					outSocket.write("\r\n".getBytes());
					byte[] htmlbuffer = new byte[writehtml.available()];
					if(writehtml !=null){
						int len = 0;
						System.out.println("writeHtml");
						while((len = writehtml.read(htmlbuffer)) != -1){
							outSocket.write(htmlbuffer, 0,len);
						}
					}
					outSocket.close();
					socket.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
}
