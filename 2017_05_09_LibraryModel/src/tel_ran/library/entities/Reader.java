package tel_ran.library.entities;

import java.time.LocalDate;

public class Reader {
	private int readerId;
	private String name;
	private String phone;
	private LocalDate birthDate;

	public Reader(int readerId, String name, String phone, LocalDate birthDate) {
		super();
		this.readerId = readerId;
		this.name = name;
		this.phone = phone;
		this.birthDate = birthDate;
	}

	public int getReaderId() {
		return readerId;
	}

	public void setReaderId(int readerId) {
		this.readerId = readerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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

	@Override
	public String toString() {
		return "Reader [readerId=" + readerId + ", name=" + name + ", phone=" + phone + ", birthDate=" + birthDate
				+ "]";
	}

}
