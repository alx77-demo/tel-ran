package tel_ran.operations.items;

public class ArithmeticOperation extends OperationItem {

	@Override
	public String displayedName() {
		
		return "Perform any arithmetic operation (-, +, /, *)";
	}

	@Override
	public void perform() {
		int minValue=10;
		int maxValue=100;
		int operand1=inputOutput.getInteger("Enter first number from "+minValue+" to "+maxValue, minValue, maxValue);
		int operand2=inputOutput.getInteger("Enter second number from "+minValue+" to "+maxValue, minValue, maxValue);
		String code=inputOutput.getString("Enter code operation", new String[]{"+","-","/","*"});
		
		int res=0;
		switch (code){
		case "+": res=operand1+operand2;break;
		case "-": res=operand1-operand2;break;
		case "/": res=operand1/operand2;break;
		case "*": res=operand1*operand2;break;
		default: break;
		}
		inputOutput.put(Integer.toString(operand1)+code+Integer.toString(operand2)+"="+res);
	}

}
