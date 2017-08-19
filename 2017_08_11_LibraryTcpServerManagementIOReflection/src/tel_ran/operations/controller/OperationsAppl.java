package tel_ran.operations.controller;

import tel_ran.collections.Array;
import tel_ran.operations.items.AddDays;
import tel_ran.operations.items.ArithmeticOperation;
import tel_ran.operations.items.OperationItem;
import tel_ran.operations.items.PeriodDates;
import tel_ran.operations.items.SubtractDays;
import tel_ran.view.Item;
import tel_ran.view.*;

public class OperationsAppl {

	public static void main(String[] args) {
		Array<Item> items=new Array<>();
		items.add(new AddDays());
		items.add(new SubtractDays());
		items.add(new PeriodDates());
		items.add(new ArithmeticOperation());
		items.add(new ExitItem());
		
		InputOutput inputOutput=new RandomInputConsoleOutput();
		Menu menu=new Menu(inputOutput, items);
		OperationItem.setInputOutput(inputOutput);
		menu.runMenu();

	}

}
