package tel_ran.communication.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import tel_ran.protocols.Protocol;

public class TcpClientRunnable implements Runnable {
	private Socket socket;
	private BufferedReader reader;
	private PrintStream writer;
	private Protocol protocol;
	public TcpClientRunnable(Socket socket,Protocol protocol)throws Exception{
		this.protocol=protocol;
		this.socket=socket;
		this.reader=
		new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.writer=new PrintStream(socket.getOutputStream());
	}
@Override
public void run(){
	try {
	while(true){
		
			String request=reader.readLine();
			if(request==null)
				return;
			String response=protocol.getResponse(request);
			writer.println(response);
	}
		} catch (IOException e) {
			System.out.println(" client error "+e.getMessage());
			
		}finally {
			try {
				socket.close();
			} catch (IOException e) {
				
			}
		}
	}
}
