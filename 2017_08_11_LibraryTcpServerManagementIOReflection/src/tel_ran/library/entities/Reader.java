package tel_ran.library.entities;

import java.time.LocalDate;

public class Reader implements Comparable<Reader>{
	int readerId;
	 String name;
	 String phone;
	LocalDate birthDate;
	public Reader(int readerId, String name, String phone, LocalDate birthDate) {
		super();
		this.readerId = readerId;
		this.name = name;
		this.phone = phone;
		this.birthDate = birthDate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getReaderId() {
		return readerId;
	}
	public String getName() {
		return name;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	@Override
	public String toString() {
		return "Reader [readerId=" + readerId + ", name=" + name + ", phone=" + phone + ", birthDate=" + birthDate
				+ "]";
	}
	@Override
	public int compareTo(Reader o) {
		return Integer.compare(readerId, o.getReaderId());
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Reader other = (Reader) obj;
		if (readerId != other.readerId)
			return false;
		return true;
	}
	
	
}
