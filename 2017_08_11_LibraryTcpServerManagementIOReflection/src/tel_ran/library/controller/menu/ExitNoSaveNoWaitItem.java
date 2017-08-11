package tel_ran.library.controller.menu;

public class ExitNoSaveNoWaitItem extends LibraryServerItem {

	@Override
	public String displayedName() {
		return "Exit with no waiting";
	}

	@Override
	public void perform() {
		clientsPool.shutdown();
		clientsPool.shutdownNow();

	}

	@Override
	public boolean isExit() {
		return true;
	}

}
