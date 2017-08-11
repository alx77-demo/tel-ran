package tel_ran.library.controller.menu;

import java.util.concurrent.ExecutorService;

import tel_ran.library.model.Library;
import tel_ran.view.*;

public abstract class LibraryServerItem implements Item{
protected static ExecutorService clientsPool;
protected static Library library;
protected static InputOutput inputOutput;
public static void setClientsPool(ExecutorService clientsPool) {
	LibraryServerItem.clientsPool = clientsPool;
}
public static void setLibrary(Library library) {
	LibraryServerItem.library = library;
}
public static void setInputOutput(InputOutput inputOutput) {
	LibraryServerItem.inputOutput = inputOutput;
}
}
