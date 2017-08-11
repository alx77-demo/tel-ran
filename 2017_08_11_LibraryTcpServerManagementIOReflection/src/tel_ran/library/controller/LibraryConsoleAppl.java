package tel_ran.library.controller;

import tel_ran.library.model.*;
import tel_ran.library.util.LibraryIO;
import tel_ran.view.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
public class LibraryConsoleAppl {

	private static final int PICK_PERIOD = 20;

	public static void main(String[] args) {
		InputOutput inputOutput=new ConsoleInputOutput();
		if(args.length!=0)
			LibraryIO.setFileName(args[0]);
		Library library=LibraryIO.restore();
		if(library==null)
			library=new LibraryMaps(PICK_PERIOD);
		LibraryItem.setInputOutput(inputOutput);
		LibraryItem.setLibrary(library);
		Menu menu=createMenu(inputOutput);
		menu.runMenu();

	}

	private static Menu createMenu(InputOutput inputOutput) {
		LibraryItem[]items={
			new AddBookItem(),
			new AddBookExemplarItem(),
			new AddReaderItem(),
			new PickBookItem(),
			new ReturnBookItem(),
			new RemoveBookItem(),
			new DisplayBookData(),
			new DisplayBooksReader(),
			new DisplayReadersBookItem(),
			new DisplayReadersDelayedItem(),
			new ExitSaveLibraryItem()
		};
		return new Menu(inputOutput, Arrays.asList(items));
	}

}
