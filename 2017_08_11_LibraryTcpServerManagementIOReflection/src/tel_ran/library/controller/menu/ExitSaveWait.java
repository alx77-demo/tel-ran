package tel_ran.library.controller.menu;

public class ExitSaveWait extends ExitWait {

	@Override
	public String displayedName() {
		return "Exit with waiting and saving";
	}
	public ExitSaveWait(){
		flSaving=true;
	}

}
