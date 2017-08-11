package tel_ran.library.util;

import tel_ran.library.model.Library;
import tel_ran.view.ConsoleInputOutput;
import tel_ran.view.InputOutput;

import java.io.*;
public class LibraryIO {
	private static final String FILE_NAME_DEFAULT = "library.data";
	static InputOutput inputOutput=new ConsoleInputOutput();
	public static void setInputOutput(InputOutput inputOutput) {
		LibraryIO.inputOutput = inputOutput;
	}
	public static String getFileName() {
		return fileName;
	}
	public static void setFileName(String fileName) {
		LibraryIO.fileName = fileName;
	}
	static String fileName=FILE_NAME_DEFAULT;
static public void save(Library library){
	try(ObjectOutputStream stream=
			new ObjectOutputStream(new FileOutputStream(fileName))){
		stream.writeObject(library);
		
	}catch (IOException e){
		inputOutput.put("IOException "+e.getMessage());
	}
		
}
static public Library restore(){
	try(ObjectInputStream stream=
			new ObjectInputStream(new FileInputStream(fileName))){
		return (Library) stream.readObject();
		
	}catch (Exception e){
		return null;
	}
}
}
