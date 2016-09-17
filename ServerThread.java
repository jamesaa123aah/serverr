package com.server;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {


	Socket socket = null;
	String string_tem = null;
	public ServerThread(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket=socket;
	}
	

	public void run() {
		
		

		InputStream is = null; 
		InputStreamReader isr = null; 
		BufferedReader br = null;  
		
	
		OutputStream os = null; 
		PrintWriter pw = null;
		
		
		try {
			
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			
			String info = null;
			
			while ((info = br.readLine()) != null) {
				string_tem = info;
				System.out.println("我是服务器，客户端说: " +info);
			}
			
			
			socket.shutdownInput(); 
			
		
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			  char[] ch =string_tem.toCharArray();
			  StringBuffer sb = new StringBuffer();
			  for (int i = ch.length - 1; i >= 0; i--) {
			   sb.append(ch[i]);
			  }
			 
            pw.write(sb.toString());
            
			pw.flush();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			
			try {
				
				if (pw != null) pw.close();
				if (os != null) os.close();
				if (is != null) is.close();
				if (isr != null) isr.close();
				if (br != null) br.close();
				if (socket != null) socket.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
}
