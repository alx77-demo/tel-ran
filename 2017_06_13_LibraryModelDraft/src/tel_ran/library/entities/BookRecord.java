package tel_ran.library.entities;

import java.time.LocalDate;

public class BookRecord {
	private long isbn;
	private LocalDate pickDate;
	private LocalDate returnDate;
	private int readerId;

	public BookRecord(long isbn, LocalDate pickDate, int readerId) {
		super();
		this.isbn = isbn;
		this.pickDate = pickDate;
//		this.returnDate = returnDate;
		this.readerId = readerId;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public LocalDate getPickDate() {
		return pickDate;
	}

	public void setPickDate(LocalDate pickDate) {
		this.pickDate = pickDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public int getReaderId() {
		return readerId;
	}

	public void setReaderId(int readerId) {
		this.readerId = readerId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (isbn ^ (isbn >>> 32));
		result = prime * result + ((pickDate == null) ? 0 : pickDate.hashCode());
		result = prime * result + readerId;
	//	result = prime * result + ((returnDate == null) ? 0 : returnDate.hashCode());
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

}
