package tel_ran.library.controller.menu;

public class ExitNoSaveWaiting extends ExitWait {

	@Override
	public String displayedName() {
		return "Exit with waiting";
	}
	public ExitNoSaveWaiting(){
		flSaving=false;
	}

}
