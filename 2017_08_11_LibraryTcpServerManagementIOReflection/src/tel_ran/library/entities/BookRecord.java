package tel_ran.library.entities;

import java.time.LocalDate;

public class BookRecord implements Comparable<BookRecord>{
	long isbn;
	LocalDate pickDate;
	LocalDate returnDate;
	int readerId;
	public BookRecord(long isbn, LocalDate pickDate, int readerId) {
		super();
		this.isbn = isbn;
		this.pickDate = pickDate;
		this.readerId = readerId;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	public long getIsbn() {
		return isbn;
	}
	public LocalDate getPickDate() {
		return pickDate;
	}
	public int getReaderId() {
		return readerId;
	}
	@Override
	public String toString() {
		return "BookRecord [isbn=" + isbn + ", pickDate=" + pickDate + ", returnDate=" + returnDate + ", readerId="
				+ readerId + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (isbn ^ (isbn >>> 32));
		result = prime * result + readerId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookRecord other = (BookRecord) obj;
		if (isbn != other.isbn)
			return false;
		if (readerId != other.readerId)
			return false;
		return true;
	}
	@Override
	public int compareTo(BookRecord o) {
		int res=Long.compare(isbn, o.getIsbn());
		return res==0?Integer.compare(readerId, o.readerId):res;
	}
	
}
