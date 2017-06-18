package tel_ran.library.entities;

public class Book {
	private long isbn;
	private String title;
	private String author;
	private long amount;//Book item may contain amount of the exemplars
	private long amountInUse;//how many readers are keeping the book
	private long picksOverall;//how many times the book was picked

	public Book(long isbn, String title, String author, long amount) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.amount = amount;
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public long getAmountInUse() {
		return amountInUse;
	}

	public void setAmountInUse(long amountInUse) {
		this.amountInUse = amountInUse;
	}

	public long getPicksOverall() {
		return picksOverall;
	}

	public void setPicksOverall(long picksOverall) {
		this.picksOverall = picksOverall;
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
}