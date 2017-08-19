package tel_ran.library.entities;

public class Book implements Comparable<Book>{
	long isbn;
	String title;
	String author;
	int amount;//Book item may contain amount of the exemplars
	int amountInUse;//how many readers are keeping the book
	int picksOverall;//how many times the book was picked 
	public Book(long isbn, String title, String author, int amount) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.amount = amount;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getAmountInUse() {
		return amountInUse;
	}
	public void setAmountInUse(int amountInUse) {
		this.amountInUse = amountInUse;
	}
	public int getPicksOverall() {
		return picksOverall;
	}
	public void setPicksOverall(int picksOverall) {
		this.picksOverall = picksOverall;
	}
	public long getIsbn() {
		return isbn;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (isbn ^ (isbn >>> 32));
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
		Book other = (Book) obj;
		if (isbn != other.isbn)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", author=" + author + ", amount=" + amount
				+ ", amountInUse=" + amountInUse + ", picksOverall=" + picksOverall + "]";
	}
	
	@Override
	public int compareTo(Book o) {
		
		return Long.compare(isbn, o.getIsbn());
	}
	
}
