package tel_ran.library.controller;
import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tel_ran.library.controller.menu.ExitNoSaveNoWaitItem;
import tel_ran.library.controller.menu.ExitNoSaveWaiting;
import tel_ran.library.controller.menu.ExitSaveWait;
import tel_ran.library.controller.menu.LibraryServerItem;
import tel_ran.library.model.*;
import tel_ran.library.protocols.api.LibraryProtocolConstants;
import tel_ran.library.util.LibraryIO;
import tel_ran.view.ConsoleInputOutput;
import tel_ran.view.InputOutput;
import tel_ran.view.Item;
import tel_ran.view.Menu;

public class LibraryTcpServerAppl {

	private static final int PICK_PERIOD = 20;
	private static final int N_THREADS = 100;

	public static void main(String[] args) throws IOException {
		
		ExecutorService clientsPool=Executors.newFixedThreadPool(N_THREADS);
		ServerSocket serverSocket=new ServerSocket(LibraryProtocolConstants.PORT);
		int port=serverSocket.getLocalPort();
		System.out.println("Library server is listening on the port: "+port);
		
		if(args.length!=0)
			LibraryIO.setFileName(args[0]);
		Library library=LibraryIO.restore();
		if(library==null)
			library=new LibraryMaps(PICK_PERIOD);
		Thread serverThread=new Thread(new LibraryServerRunnable(library,
				serverSocket, clientsPool));
		serverThread.setDaemon(true);
		serverThread.start();
		Menu menu=createMenu(clientsPool,library);
		menu.runMenu();
		
		

	}

	private static Menu createMenu( ExecutorService clientsPool, Library library) {
		InputOutput inputOutput=new ConsoleInputOutput();
		LibraryServerItem.setClientsPool(clientsPool);
		LibraryServerItem.setLibrary(library);
		LibraryServerItem.setInputOutput(inputOutput);
		Iterable<Item> items=Arrays.asList(
				new ExitNoSaveNoWaitItem(),
				new ExitNoSaveWaiting(),
				new ExitSaveWait());
		Menu menu=new Menu(inputOutput, items);
		return menu;
		
	}

}
