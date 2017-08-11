package tel_ran.operations.items;

import tel_ran.view.InputOutput;
import tel_ran.view.Item;

public abstract class OperationItem implements Item {
static protected InputOutput inputOutput;
	public static void setInputOutput(InputOutput inputOutput) {
	OperationItem.inputOutput = inputOutput;
}


}
