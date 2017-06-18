package tel_ran.library.model;

public class IsbnReaderId {
	long isbn;
	int readerId;

	public IsbnReaderId(long isbn, int readerId) {
		super();
		this.isbn = isbn;
		this.readerId = readerId;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
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
		IsbnReaderId other = (IsbnReaderId) obj;
		if (isbn != other.isbn)
			return false;
		if (readerId != other.readerId)
			return false;
		return true;
	}

}
