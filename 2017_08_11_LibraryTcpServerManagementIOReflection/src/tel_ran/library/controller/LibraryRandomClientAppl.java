package tel_ran.library.controller;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import tel_ran.library.protocols.api.LibraryProtocolConstants;
import tel_ran.view.RandomInputConsoleOutput;

public class LibraryRandomClientAppl {

	private static final int N_CLIENTS = 100;
	private static final int N_REQUESTS = 100;
	private static final String HOST = "localhost";

	public static void main(String[] args) throws Exception {
		Instant start=Instant.now();
		LibraryRandomClient clients[]=
				new LibraryRandomClient[N_CLIENTS];
		
		RandomInputConsoleOutput input=new RandomInputConsoleOutput();
		input.setFl_print(false);
		LibraryRandomClient.setInput(input);
		for(int i=0;i<N_CLIENTS;i++){
			clients[i]=new LibraryRandomClient(N_REQUESTS, HOST, LibraryProtocolConstants.PORT);
			clients[i].start();
		}
		for(int i=0;i<N_CLIENTS;i++){
			clients[i].join();
		}
		Instant finish=Instant.now();
		System.out.println(String.format
		("number of clients=%d,number of requests=%d,"
				+ "running time=%d",N_CLIENTS,N_REQUESTS,
				ChronoUnit.MILLIS.between(start, finish)));
	}

}
