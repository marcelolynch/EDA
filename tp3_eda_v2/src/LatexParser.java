
public class LatexParser {
	//Version string array
	
	private Stack<String> stack = new Stack<String>();
	
	
	public boolean parse(String[] arr){
		for(String s: arr){
			if(s.startsWith("\\begin{")){
				System.out.print("begin: ");
				System.out.println(s.substring(6));
				stack.push(s.substring(6));
			}
			if(s.startsWith("\\end{")){
				System.out.print("end: ");
				System.out.println(s.substring(4));
				if(stack.isEmpty() || !stack.pop().equals(s.substring(4))){
					return false;
				}
		}

		}
		
		return true;
		
	
	}	
	public static void main(String[] args) {
		String[] strs = new String[]{"\\begin{document}", "chelo", "\\begin{lala}", "\\end{lala}", "\\end{document}"};
		
		System.out.println(new LatexParser().parse(strs));
	}
	
}

