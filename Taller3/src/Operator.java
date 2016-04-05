
public abstract class Operator {
	abstract double apply(double a, double b);

	private int precedence;
	
	int precedence(){
		return precedence;
	}
}
