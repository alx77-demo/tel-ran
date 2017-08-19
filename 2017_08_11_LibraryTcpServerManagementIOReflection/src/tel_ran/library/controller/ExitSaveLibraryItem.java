package tel_ran.library.controller;

import tel_ran.library.util.LibraryIO;
public class ExitSaveLibraryItem extends LibraryItem {

	@Override
	public String displayedName() {
		
		return "Exit and save library";
	}

	@Override
	public void perform() {
		LibraryIO.save(library);
		
	}
	@Override
	public boolean isExit(){
		return true;
	}

}
