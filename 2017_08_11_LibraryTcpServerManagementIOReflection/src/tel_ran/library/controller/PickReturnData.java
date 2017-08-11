package tel_ran.library.controller;

import java.time.LocalDate;

public class PickReturnData {
long isbn;
int readerId;
LocalDate date;
public long getIsbn() {
	return isbn;
}
public int getReaderId() {
	return readerId;
}
public LocalDate getDate() {
	return date;
}
public PickReturnData(long isbn, int readerId, LocalDate date) {
	super();
	this.isbn = isbn;
	this.readerId = readerId;
	this.date = date;
}
}
