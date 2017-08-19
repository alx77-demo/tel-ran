package tel_ran.library.controller;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import tel_ran.communication.tcp.TcpClientRunnable;
import tel_ran.library.model.Library;
import tel_ran.library.protocols.LibraryProtocol;

public class LibraryServerRunnable implements Runnable {
private Library library;
private ServerSocket serverSocket;
private ExecutorService clientsPool;
	
	public LibraryServerRunnable(Library library, ServerSocket serverSocket, ExecutorService clientsPool) {
	super();
	this.library = library;
	this.serverSocket = serverSocket;
	this.clientsPool = clientsPool;
}

	@Override
	public void run() {
		while(true){
			try {
				
				Socket socket=serverSocket.accept();
				TcpClientRunnable tcpClient=
				new TcpClientRunnable(socket, new LibraryProtocol(library));
				clientsPool.execute(tcpClient);
			} catch (Throwable e) {
				System.out.println("client error "+e.getMessage());
			}
		}

	}

}
