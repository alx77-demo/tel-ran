package tel_ran.library.protocols;
import java.util.*;
import java.time.LocalDate;
import java.util.function.Function;
import java.util.stream.*;

import tel_ran.library.entities.*;
import tel_ran.library.model.Library;
import tel_ran.library.protocols.api.*;

import tel_ran.protocols.Protocol;

public class LibraryProtocol implements Protocol {
	Library library;
	
	public LibraryProtocol(Library library) {
		this.library=library;
	
	}

	@Override
	public String getResponse(String request) {
		 synchronized(library){
			String[] tokens = request.split(LibraryProtocolConstants.DELIMETER);
			RequestType type;
			try {
				type = RequestType.valueOf(tokens[0]);
			} catch (Throwable e) {
				return ResponseCode.WRONG_REQUEST_TYPE.toString();
			}
			String response = null;
			switch (type) {
			case SET_PICK_PERIOD:
				response = setPickPeriod(tokens);
				break;
			case ADD_BOOK:
				response = addBook(tokens);
				break;
			case ADD_EXEMPLAR:
				response = addExemplar(tokens);
				break;
			case REMOVE_BOOK:
				response = removeBook(tokens);
				break;
			case PICK_BOOK:
				response = pickBook(tokens);
				break;
			case RETURN_BOOK:
				response = returnBook(tokens);
				break;
			case GET_NON_RETURNED_BOOKS:
				response = getNonReturnedBooks(tokens);
				break;
			case GET_NON_RETURNED_RECORDS:
				response = getNonReturnedRecords(tokens);
				break;
			case GET_DELAYED_RECORDS:
				response = getDelayedRecords(tokens);
				break;
			case GET_READERS_DELAYED_BOOKS:
				response = getReadersDelayedBooks(tokens);
				break;
			case ADD_READER:
				response = addReader(tokens);
				break;
			case GET_READERS_BOOK:
				response = getReaders(tokens);
				break;
			case GET_READER:
				response = getReader(tokens);
				break;
			case GET_BOOK:
				response = getBook(tokens);
				break;
			case GET_ALL_READERS:
				response = getAllReaders(tokens);
				break;
			case GET_ALL_RECORDS:
				response = getAllRecords(tokens);
				break;
			case GET_ALL_BOOKS:
				response = getAllBooks(tokens);
			}
			return response;
		}
	}

	private String getAllBooks(String[] tokens) {
		
		return getIterableResponse(LibraryProtocolConversions::bookToString, library);
	}

	private String setPickPeriod(String[] tokens) {
		String response="";
		ResponseCode code=ResponseCode.OK;
		try {
			int pickPeriod=Integer.parseInt(tokens[1]);
			library.setPickPeriod(pickPeriod);
		} catch (NumberFormatException e) {
			code=ResponseCode.ERROR;
		}
		return getResponseWithCode(code, response);
	}

	private String getAllRecords(String[] tokens) {
		Function<BookRecord, String> method=LibraryProtocolConversions::recordToString;
		return getIterableResponse(method,library.getAllRecords());
	}

	private <T>String getIterableResponse
	(Function< T, String> method,Iterable<T> iterable) {
		String response=null;
		ResponseCode responseCode=null;
		try {
			response = StreamSupport.stream(iterable.spliterator(), false)
					.map(method)
					.collect(Collectors.joining(LibraryProtocolConstants.LINE_DELIMETER));
			responseCode=ResponseCode.OK;
		} catch (Exception e) {
			responseCode=ResponseCode.ERROR;
			response=e.getMessage();
		}
		return getResponseWithCode(responseCode,response);
	}
	private String getResponseWithCode(ResponseCode code, String response) {
		return code.toString()+LibraryProtocolConstants.LINE_DELIMETER+response;
	}

	

	private String getAllReaders(String[] tokens) {
		Function<Reader, String> method=LibraryProtocolConversions::readerToString;
		return getIterableResponse(method,library.getAllReaders());
	}

	private String getBook(String[] tokens) {
		String response="";
		ResponseCode responseCode=ResponseCode.OK;
		try {
			long isbn=Long.parseLong(tokens[1]);
			Book book=library.getBookItem(isbn);
			if(book==null){
				responseCode=ResponseCode.ERROR;
				response="No book with isbn "+isbn;
			}
			else {
				response=LibraryProtocolConversions.bookToString(book);
			}
		} catch (Exception e) {
			responseCode=ResponseCode.ERROR;
			response=e.getMessage();
		}
		return getResponseWithCode(responseCode, response);
	}

	
	private String getReader(String[] tokens) {
		ResponseCode code=ResponseCode.OK;
		String response="";
		try {
			int readerId=Integer.parseInt(tokens[1]);
			Reader reader=library.getReader(readerId);
			if(reader==null){
				code = ResponseCode.ERROR;
				response="No reader with id "+tokens[1];
			}
			else
				response=LibraryProtocolConversions.readerToString(reader);
		} catch (Exception e) {
			code=ResponseCode.ERROR;
			response=e.getMessage();
		}
		return getResponseWithCode(code, response);
	}

	private String getReaders(String[] tokens) {
		
		try {
			long isbn=Integer.parseInt(tokens[1]);
			Iterable<Reader> readers=library.getReaders(isbn);
			return getIterableResponse(LibraryProtocolConversions::readerToString, readers);
			
		} catch (Throwable e) {
			return getResponseWithCode(ResponseCode.ERROR,
			"Wrong isbn "+tokens[1]);
		}
		
		
	}
	
	private String addReader(String[] tokens) {
		Reader reader=LibraryProtocolConversions.
		stringToReader(Arrays.copyOfRange(tokens, 1, tokens.length));
		String response="";
		ResponseCode code=ResponseCode.ERROR;
		if(reader==null){
			response="Wrong Reader data";
		}
		else {
			code=ResponseCode.OK;
			response=library.addReader(reader)?
			BooleanResponse.TRUE.toString():BooleanResponse.FALSE.toString();
		}
		return getResponseWithCode(code, response);
	}

	private String getReadersDelayedBooks(String[] tokens) {
		
		try {
			LocalDate currentDate=LocalDate.parse(tokens[1]);
			int byDays=Integer.parseInt(tokens[2]);
			Iterable<Reader> readers=library.getReadersDelayedBooks
					(currentDate, byDays);
			return getIterableResponse(LibraryProtocolConversions::readerToString, readers);
		} catch (Throwable e) {
			return getResponseWithCode(ResponseCode.ERROR, e.getMessage());
		}
		
		
	}

	

	private String getDelayedRecords(String[] tokens) {
		
		try {
			LocalDate currentDate=LocalDate.parse(tokens[1]);
			Iterable<BookRecord> records=library.getDelayedBookRecords(currentDate);
			return getIterableResponse(LibraryProtocolConversions::recordToString, records);
		} catch (Throwable e) {
			return getResponseWithCode(ResponseCode.ERROR, e.getMessage());
		}
		
		
	}

	private String getNonReturnedRecords(String[] tokens) {
		
		try {
			Iterable<BookRecord> records=library.getNonReturnedBookRecords();
			return getIterableResponse(LibraryProtocolConversions::recordToString, records);
		} catch (Throwable e) {
			return getResponseWithCode(ResponseCode.ERROR, e.getMessage());
		}
		
	}

	private String getNonReturnedBooks(String[] tokens) {
		
		try {
			Iterable<Book> books=library.getNonReturnedBooks();
			return getIterableResponse
				(LibraryProtocolConversions::bookToString, books);
		} catch (Throwable e) {
			return getResponseWithCode(ResponseCode.ERROR, e.getMessage());
		}
		
	}

	private String returnBook(String[] tokens) {
		ResponseCode code=ResponseCode.OK;
		String response="";
		try {
			long isbn=Long.parseLong(tokens[1]);
			int readerId=Integer.parseInt(tokens[2]);
			LocalDate returnDate=LocalDate.parse(tokens[3]);
			response=library.returnBook(isbn, readerId, returnDate)?
					BooleanResponse.TRUE.toString():BooleanResponse.FALSE.toString();
		} catch (Throwable e) {
			code=ResponseCode.ERROR;
		}
		return getResponseWithCode(code, response);
	}

	private String pickBook(String[] tokens) {
		ResponseCode code=ResponseCode.OK;
		String response="";
		try {
			long isbn=Long.parseLong(tokens[1]);
			
			LocalDate pickDate=LocalDate.parse(tokens[2]);
			int readerId=Integer.parseInt(tokens[3]);
			response=library.pickBook(isbn, pickDate, readerId)?
					BooleanResponse.TRUE.toString():BooleanResponse.FALSE.toString();
		} catch (Throwable e) {
			code=ResponseCode.ERROR;
		}
		return getResponseWithCode(code, response);
	}

	private String removeBook(String[] tokens) {
		ResponseCode code=ResponseCode.OK;
		String response="";
		try {
			long isbn=Long.parseLong(tokens[1]);
			response=library.removeBook(isbn)?
					BooleanResponse.TRUE.toString():
					BooleanResponse.FALSE.toString();
		} catch (NumberFormatException e) {
			code=ResponseCode.ERROR;
		}
		return getResponseWithCode(code, response);
	}

	private String addExemplar(String[] tokens) {
		ResponseCode code=ResponseCode.OK;
		String response="";
		long isbn=Long.parseLong(tokens[1]);
		response=library.addBookExemplar(isbn)?
				BooleanResponse.TRUE.toString():
					BooleanResponse.FALSE.toString();
		return getResponseWithCode(code, response);
	}

	private String addBook(String[] tokens) {
		ResponseCode code=ResponseCode.ERROR;
		String response="";
		Book book=LibraryProtocolConversions.stringToBook(Arrays.copyOfRange(tokens, 1, tokens.length));
		if(book!=null){
			code=ResponseCode.OK;
			response=library.addBookItem(book)?
					BooleanResponse.TRUE.toString():
					BooleanResponse.FALSE.toString();
		}
		return getResponseWithCode(code, response);
	}

}
