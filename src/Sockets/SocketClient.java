package Sockets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.communication.Client;
import app.communication.ClientCommunication;

public class SocketClient implements Runnable{
	
	private Socket clientSocket;
	private DataOutputStream out;
	private DataInputStream in;
	private int port;
	
	public void setPort(int port) throws UnknownHostException, IOException {
		
		this.port = port;
		
	}
	
	public void timer() throws InterruptedException {
		synchronized(this) {
			Thread.sleep(1000);
			notify();
		}
	}
	
	public void stopConnection() throws IOException {
		
		out.close();
		clientSocket.close();
		
	}

	@Override
	public void run() {
		synchronized (this){
			try {
				ObjectMapper mapper = new ObjectMapper();
				clientSocket = new Socket("127.0.0.1", port);
				out = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));;
				in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
				Thread.sleep(500);
				String str;
				while(true) {
					System.out.println("holaclient");
					out.writeUTF(ClientCommunication.jsonDataSend());
					out.flush();
					Thread.sleep(500);
					System.out.println("holaclient2");
					str = in.readUTF();
					Client client = mapper.readValue(str, Client.class);
					System.out.println("holaclient6");
					System.out.println(str);
					System.out.println(ClientCommunication.jsonDataSend());
					System.out.println("holaclient3");
					wait();
				}	
			}catch(Exception e) {
			System.out.println(e);
			}
		}
	}
}