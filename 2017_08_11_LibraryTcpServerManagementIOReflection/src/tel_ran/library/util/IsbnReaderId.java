package tel_ran.library.util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class IsbnReaderId implements Serializable{
private long isbn;
private int readerId;
public IsbnReaderId(long isbn, int readerId) {
	super();
	this.isbn = isbn;
	this.readerId = readerId;
}
public long getIsbn() {
	return isbn;
}
public int getReaderId() {
	return readerId;
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
