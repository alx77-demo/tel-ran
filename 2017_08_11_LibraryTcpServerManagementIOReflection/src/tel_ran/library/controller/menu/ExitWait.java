package tel_ran.library.controller.menu;

import java.util.concurrent.TimeUnit;

import tel_ran.library.util.LibraryIO;

public abstract class ExitWait extends LibraryServerItem {
protected boolean flSaving;
	

	@Override
	public void perform() {
		clientsPool.shutdown();
		int timeout=inputOutput.getInteger("Enter timeout value in seconds");
		try {
			clientsPool.awaitTermination(timeout, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			
		}
		
		if(flSaving)
			LibraryIO.save(library);
		
	}

	@Override
	public boolean isExit() {
		return true;
	}

}
