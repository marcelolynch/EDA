import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Infix {
	public String infixToPostfix(String s){

		List<Character> ans = new ArrayList<Character>();
		Deque<Operator> stack = new LinkedList<Operator>();
		for(char c: s.toCharArray()){
			Operator op = OperatorFactory.create(c);
			if(op == null){
				ans.add(c);
			}else{
				switch(c){
				case '(':
					stack.push(op);
					break;
					
				case ')':
					while (!stack.isEmpty() && stack.peek().getChar() != '('){
						ans.add(stack.pop().getChar());
					}
				if(stack.isEmpty()) //No abrio nunca el paren
					throw new IllegalStateException();
				stack.pop();
				break;
				
				default: //Es una operacion
					while(!stack.isEmpty() && stack.peek().precedence() > op.precedence()){
						ans.add(stack.pop().getChar());
					}
					stack.push(op);
					break;
					
				}
			}
			
			while(!stack.isEmpty()){ // los operadores que quedan en la pila cuando se acabo el string
				ans.add(stack.pop().getChar());
			}
			
			return new String(ans);
		}
		
	}
}
