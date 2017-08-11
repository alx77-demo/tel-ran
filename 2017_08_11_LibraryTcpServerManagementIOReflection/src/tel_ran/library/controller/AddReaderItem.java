package tel_ran.library.controller;

import java.time.LocalDate;

import tel_ran.library.entities.Reader;

public class AddReaderItem extends LibraryItem {

	@Override
	public String displayedName() {
		return "Add new reader";
	}

	@Override
	public void perform() {
		Integer readerId=getNewReaderId();
		if(readerId==null){
			return;
		}
		String name=inputOutput.getString("Enter name");
		String phone=inputOutput.getString("Enter phone number");
		LocalDate birthDate=inputOutput.getDate("Enter birthdate "+format, format);
		library.addReader(new Reader(readerId, name, phone, birthDate));

	}

}
