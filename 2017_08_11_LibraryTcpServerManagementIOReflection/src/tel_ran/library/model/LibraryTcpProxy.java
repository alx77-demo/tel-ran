package tel_ran.library.model;

import java.time.*;
import java.util.*;
import java.util.function.Function;
import java.net.*;
import tel_ran.library.entities.*;
import tel_ran.library.entities.Reader;

import java.io.*;

import tel_ran.library.protocols.api.LibraryProtocolConversions;
import tel_ran.library.protocols.api.RequestType;
import tel_ran.library.protocols.api.ResponseCode;

import static tel_ran.library.protocols.api.LibraryProtocolConstants.*;
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
		String request=RequestType.SET_PICK_PERIOD+DELIMETER+pickPeriod;
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
		String request=RequestType.GET_ALL_BOOKS.toString();
		
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
		RequestType type=RequestType.ADD_BOOK;
		String requestBody=LibraryProtocolConversions.bookToString(book);

		return getBooleanRequest(type, requestBody);
	}

	private String getRequest(RequestType type, String requestBody) {
		
		return type+DELIMETER+requestBody;
	}
	@Override
	public boolean addBookExemplar(long isbn)  {

		return getBooleanRequest(RequestType.ADD_EXEMPLAR,
				Long.toString(isbn));
	}
 private <T>  T getObject(RequestType type,String requestBody,
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

		return getBooleanRequest(RequestType.REMOVE_BOOK,
				Long.toString(isbn));
	}

	@Override
	public boolean pickBook(long isbn, LocalDate pickDate, int readerId) {

		return getBooleanRequest(RequestType.PICK_BOOK,
				getPickBookRequestBody(isbn,pickDate,readerId));
	}

	private String getPickBookRequestBody(long isbn, LocalDate pickDate, int readerId) {
		return isbn+DELIMETER+pickDate+DELIMETER+readerId;
	}
	@Override
	public boolean returnBook(long isbn, int readerId, LocalDate returnDate) {

		return getBooleanRequest(RequestType.RETURN_BOOK, getReturnBookRequestBody(isbn,readerId,returnDate));
	}

	private String getReturnBookRequestBody(long isbn, int readerId, LocalDate returnDate) {
		return isbn+DELIMETER+readerId+DELIMETER+returnDate;
	}
	@Override
	public Iterable<Book> getNonReturnedBooks() {

		return getIterable(RequestType.GET_NON_RETURNED_BOOKS,
				"", this::stringToBook);
	}

	@Override
	public Iterable<BookRecord> getNonReturnedBookRecords() {

		return getIterable(RequestType.GET_NON_RETURNED_RECORDS,
				"", this::stringToRecord);
	}

	@Override
	public Iterable<BookRecord> getDelayedBookRecords(LocalDate currentDate) {

		return getIterable(RequestType.GET_DELAYED_RECORDS, "",
				this::stringToRecord);
	}

	
	@Override
	public Book getBookItem(long isbn) {

		return getObject(RequestType.GET_BOOK, Long.toString(isbn), this::stringToBook);
		
	}
private <T> Iterable<T> getIterable(RequestType type,
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

		return getIterable(RequestType.GET_ALL_RECORDS, "", this::stringToRecord);
	}

	

	@Override
	public Iterable<Reader> getReadersDelayedBooks(LocalDate currentDate, int byDays) {

		return getIterable
		(RequestType.GET_READERS_DELAYED_BOOKS,
		currentDate+DELIMETER+byDays, this::stringToReader);
	}
	private  boolean getBooleanRequest(RequestType type,String requestBody) {
		
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
		RequestType type=RequestType.ADD_READER;
		String requestBody=LibraryProtocolConversions.readerToString(reader);

		return getBooleanRequest(type, requestBody);
	}

	@Override
	public Iterable<Reader> getReaders(long isbn) {

		return getIterable(RequestType.GET_READERS_BOOK,
				Long.toString(isbn), this::stringToReader);
	}

	@Override
	public Reader getReader(int readerId) {
		return getObject(RequestType.GET_READER, Integer.toString(readerId), this::stringToReader);
	}

	@Override
	public Iterable<Reader> getAllReaders() {

		return getIterable(RequestType.GET_ALL_READERS,
				"", this::stringToReader);
	}

	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			
		}
		
	}

}
