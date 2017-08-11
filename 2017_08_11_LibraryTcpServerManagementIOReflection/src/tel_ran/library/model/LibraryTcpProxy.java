package tel_ran.library.model;

import static tel_ran.library.protocols.api.LibraryProtocolConstants.DELIMETER;
import static tel_ran.library.protocols.api.LibraryProtocolConstants.LINE_DELIMETER;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import tel_ran.library.entities.Book;
import tel_ran.library.entities.BookRecord;
import tel_ran.library.entities.Reader;
import tel_ran.library.protocols.api.LibraryProtocolConversions;
import tel_ran.library.protocols.api.ResponseCode;
@SuppressWarnings("serial")
public class LibraryTcpProxy extends Library {
BufferedReader reader;
 PrintStream writer;
ResponseCode code;
String []responseBody;
private Socket socket;
	public LibraryTcpProxy(int pickPeriod,Socket socket) throws UnknownHostException, IOException {
		super(pickPeriod);
		this.socket=socket;
		reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer=new PrintStream(socket.getOutputStream());
		String request="setPickPeriod"+DELIMETER+pickPeriod;
		writer.println(request);
		reader.readLine();
	}
	private void getResponse(String request) throws IOException{
		writer.println(request);
		String response=reader.readLine();
		String[]tokens=response.split(LINE_DELIMETER);
		code=ResponseCode.valueOf(tokens[0]);
		responseBody=Arrays.copyOfRange(tokens, 1, tokens.length);
	}
	@Override
	public Iterator<Book> iterator() {
		List<Book> books=new ArrayList<>();
		String request=new Object(){}.getClass().getEnclosingMethod().getName();
		
		try {
			getResponse(request);
			if(code==ResponseCode.OK)
				fillListFromResponse(responseBody,books,this::stringToBook);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return books.iterator();
	}
	Book stringToBook(String bookStr){
		String []tokens=bookStr.split(DELIMETER);
		return LibraryProtocolConversions.stringToBook(tokens);
	}
	Reader stringToReader(String readerStr){
		String[]tokens=readerStr.split(DELIMETER);
		return LibraryProtocolConversions.stringToReader(tokens);
	}
	BookRecord stringToRecord(String recordStr){
		String[]tokens=recordStr.split(DELIMETER);
		return LibraryProtocolConversions.stringToRecord(tokens);
		
	}
	static <T> void fillListFromResponse(String tokens[],List<T> list,
		Function<String, T> method){
		Arrays.stream(tokens)
		.map(method)
		.forEach(t->list.add(t));
	}

	@Override
	public boolean addBookItem(Book book) {
		String type=new Object(){}.getClass().getEnclosingMethod().getName();
		String requestBody=LibraryProtocolConversions.bookToString(book);

		return getBooleanRequest(type, requestBody);
	}

	private String getRequest(String type, String requestBody) {
		
		return type+DELIMETER+requestBody;
	}
	@Override
	public boolean addBookExemplar(long isbn)  {

		return getBooleanRequest(new Object(){}.getClass().getEnclosingMethod().getName(),
				Long.toString(isbn));
	}
 private <T>  T getObject(String type,String requestBody,
		 Function<String, T> method){
	 try {
			getResponse(getRequest(type, requestBody));
			if(code==ResponseCode.OK){
				return method.apply(responseBody[0]);
			}
		} catch (Throwable e) {
		}
		return null;
 }
	@Override
	public boolean removeBook(long isbn) {

		return getBooleanRequest(new Object(){}.getClass().getEnclosingMethod().getName(),
				Long.toString(isbn));
	}

	@Override
	public boolean pickBook(long isbn, LocalDate pickDate, int readerId) {

		return getBooleanRequest(new Object(){}.getClass().getEnclosingMethod().getName(),
				getPickBookRequestBody(isbn,pickDate,readerId));
	}

	private String getPickBookRequestBody(long isbn, LocalDate pickDate, int readerId) {
		return isbn+DELIMETER+pickDate+DELIMETER+readerId;
	}
	@Override
	public boolean returnBook(long isbn, int readerId, LocalDate returnDate) {

		return getBooleanRequest(new Object(){}.getClass().getEnclosingMethod().getName(), getReturnBookRequestBody(isbn,readerId,returnDate));
	}

	private String getReturnBookRequestBody(long isbn, int readerId, LocalDate returnDate) {
		return isbn+DELIMETER+readerId+DELIMETER+returnDate;
	}
	@Override
	public Iterable<Book> getNonReturnedBooks() {

		return getIterable(new Object(){}.getClass().getEnclosingMethod().getName(),
				"", this::stringToBook);
	}

	@Override
	public Iterable<BookRecord> getNonReturnedBookRecords() {

		return getIterable(new Object(){}.getClass().getEnclosingMethod().getName(),
				"", this::stringToRecord);
	}

	@Override
	public Iterable<BookRecord> getDelayedBookRecords(LocalDate currentDate) {

		return getIterable(new Object(){}.getClass().getEnclosingMethod().getName(), "",
				this::stringToRecord);
	}

	
	@Override
	public Book getBookItem(long isbn) {
		return getObject(new Object(){}.getClass().getEnclosingMethod().getName(), Long.toString(isbn), this::stringToBook);
		
	}
private <T> Iterable<T> getIterable(String type,
		String requestBody,Function<String, T> method){
	List<T> result=new ArrayList<>();
	try {
		getResponse(getRequest(type, requestBody));
		if(code==ResponseCode.OK){
			fillListFromResponse(responseBody, result, method);
		}
		
	} catch (Throwable e) {
		
	}
	return result;
}
	@Override
	public Iterable<BookRecord> getAllRecords() {

		return getIterable(new Object(){}.getClass().getEnclosingMethod().getName(), "", this::stringToRecord);
	}

	

	@Override
	public Iterable<Reader> getReadersDelayedBooks(LocalDate currentDate, int byDays) {

		return getIterable
		(new Object(){}.getClass().getEnclosingMethod().getName(),
		currentDate+DELIMETER+byDays, this::stringToReader);
	}
	private  boolean getBooleanRequest(String type,String requestBody) {
		
		String request=getRequest(type,requestBody);
		try {
			getResponse(request);
			if(code==ResponseCode.OK){
				return Boolean.parseBoolean(responseBody[0].toLowerCase());
			}
		} catch (Throwable e) {
			
		}
		return false;
	}

	@Override
	public boolean addReader(Reader reader) {
		String type=new Object(){}.getClass().getEnclosingMethod().getName();
		String requestBody=LibraryProtocolConversions.readerToString(reader);

		return getBooleanRequest(type, requestBody);
	}

	@Override
	public Iterable<Reader> getReaders(long isbn) {

		return getIterable(new Object(){}.getClass().getEnclosingMethod().getName(),
				Long.toString(isbn), this::stringToReader);
	}

	@Override
	public Reader getReader(int readerId) {
		return getObject(new Object(){}.getClass().getEnclosingMethod().getName(), Integer.toString(readerId), this::stringToReader);
	}

	@Override
	public Iterable<Reader> getAllReaders() {

		return getIterable(new Object(){}.getClass().getEnclosingMethod().getName(),
				"", this::stringToReader);
	}

	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			
		}
		
	}
}
