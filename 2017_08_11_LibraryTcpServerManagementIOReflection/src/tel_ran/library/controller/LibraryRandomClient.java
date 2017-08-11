package tel_ran.library.controller;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.atomic.AtomicInteger;

import tel_ran.library.entities.*;
import tel_ran.library.model.LibraryTcpProxy;
import tel_ran.library.protocols.api.RequestType;
import tel_ran.view.RandomInputConsoleOutput;

public class LibraryRandomClient extends Thread {
	static RequestType types[]={RequestType.ADD_BOOK,
			RequestType.ADD_READER,
				RequestType.GET_BOOK,
				RequestType.GET_READER,
				RequestType.REMOVE_BOOK,
				RequestType.PICK_BOOK,
				RequestType.RETURN_BOOK,
				RequestType.GET_NON_RETURNED_RECORDS
		};
	private static final int IMITATION_PERIOD = 100;
	private static final int N_AUTHORS = 50;
	private static final int MAX_AMOUNT = 50;
	private static final Object mutex=new Object();
	private static final LocalDate MIN_BIRTH_DATE =
			LocalDate.parse("1930-01-01");
	private static final LocalDate MAX_BIRTH_DATE =
			LocalDate.parse("2005-12-31");
	private static final int PICK_PERIIOD = 20;
	private static final int ISBN_START = 1;
	private static final int READER_ID_START = 1;
	private  LibraryTcpProxy library;
	private Socket socket;
	private static RandomInputConsoleOutput input;
	private static List<PickReturnData> picks=
			Collections.synchronizedList(new ArrayList<>());
	private static AtomicInteger globalIsbn=new AtomicInteger(ISBN_START);
	private static AtomicInteger globalReaderId=new AtomicInteger(READER_ID_START);
	private int nRequestSendings;
	private static ConcurrentSkipListSet<Long> removedIsbns=
			new ConcurrentSkipListSet<>();
	public LibraryRandomClient(int nRequeestSendings,String host,int port) throws UnknownHostException, IOException {
		super();
		
		this.nRequestSendings = nRequeestSendings;
		socket=new Socket(host,port);
		library=new LibraryTcpProxy(PICK_PERIIOD, socket);
		
	}
	
	public static void setInput(RandomInputConsoleOutput input) {
		LibraryRandomClient.input = input;
	}
	@Override
	public void run(){
		for(int i=0;i<nRequestSendings;i++){
			runRequest();
		}
				
	}
	private void runRequest() {
		
		int index=input.getInteger("", 0, types.length);
		switch(types[index]){
		case ADD_BOOK:addBook();break;
		case ADD_READER:addReader();break;
		case GET_BOOK:getBook();break;
		case GET_READER:getReader();break;
		case REMOVE_BOOK:removeBook();break;
		case PICK_BOOK:pickBook();break;
		case RETURN_BOOK:returnBook();break;
		case GET_NON_RETURNED_RECORDS:getNonReturnedRecords();break;
		default: break;
		}
		
	}
	private void getNonReturnedRecords() {
		library.getNonReturnedBookRecords();
	}
	 private void returnBook() {
			PickReturnData pick;
			pick = getPck();
			if (pick != null) {
				LocalDate returnDate = pick.getDate().plusDays(input.getInteger("", 1, 30));
				if (library.returnBook(pick.getIsbn(), pick.getReaderId(), returnDate))
					picks.remove(pick);
				
		}
		
	}
private PickReturnData getPck() {
		PickReturnData pick = null;
		int count=0;
		int size=picks.size();
		if(size==0)
			return null;
		do {
				int index = input.getInteger("", 0, picks.size());
				try {
					pick = picks.get(index);
				} catch (Exception e) {
					return null;
				}
			count++;
			
		} while (removedIsbns.contains(pick.getIsbn())&&count<size);
		return count<size?pick:null;
	}
	 private void pickBook() {
			long isbn = getIsbn();
			if (isbn != 0) {
				int readerId = getReaderId();
				if (readerId != 0) {
					LocalDate pickDate = LocalDate.now().plusDays(input.getInteger("", 0, IMITATION_PERIOD));
					if (library.pickBook(isbn, pickDate, readerId)) {
						picks.add(new PickReturnData(isbn, readerId, pickDate));
					}

				}
		}
		
		
	}
	private int getReaderId() {
		int lastReaderId=globalReaderId.get();
		return lastReaderId==1?0:input.getInteger("", 1, lastReaderId);
	}
	private long getIsbn() {
		int count=0;
		long isbn;
		int lastIsbn=globalIsbn.get();
		if(lastIsbn==1)
			return 0;
		do {
			isbn=input.getInteger("", 1, lastIsbn);
			count++;
		}while(removedIsbns.contains(isbn)&&count<=lastIsbn);
		return count<lastIsbn?isbn:0;
	}
	private void removeBook() {
		long isbn=getIsbn();
		if(isbn!=0){
			if(library.removeBook(isbn))
				removedIsbns.add(isbn);
			
		}
	}
	private void getReader() {
		int readerId=getReaderId();
		if(readerId != 0){
			library.getReader(readerId);
		}
	}
	private void getBook() {
		long isbn=getIsbn();
		if(isbn!=0)
		{
			library.getBookItem(isbn);
		}
	}
	private void addReader() {
		int readerId=globalReaderId.getAndIncrement();
		String name="reader"+readerId;
		String phone=Integer.toString(input.getInteger
				("", 1000000, 10000000));
		LocalDate birthDate=input.getDate("","yyyy-MM-dd",MIN_BIRTH_DATE,
				MAX_BIRTH_DATE);
		if(!library.addReader(new Reader(readerId, name, phone,
				birthDate)))
			System.out.println("error in adding reader "+readerId);
		
	}
	private void addBook() {
		long isbn=globalIsbn.getAndIncrement();
		String title="title"+isbn;
		String author="author"+input.getInteger("", 1,N_AUTHORS+1);
		int amount=input.getInteger("", 1, MAX_AMOUNT);
		if(!library.addBookItem(new Book(isbn, title, author,
				amount)))
			System.out.println("error in adding book "+isbn);
	}
}
